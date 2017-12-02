package com.palance.mgirlandroid;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ImageFormat;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.YuvImage;
import android.hardware.Camera;
import android.graphics.SurfaceTexture;
import com.tzutalin.dlib.FaceDet;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.jar.Attributes;

/**
 * Created by palance on 2017/12/2.
 */

public class CameraPreview extends SurfaceView
                            implements SurfaceHolder.Callback{
    private static final String TAG = "CameraPerview";
    private Context mContext;
    private MainActivity mActivity;
    private Camera mCamera = null;
    private SurfaceHolder mSurfaceHolder = null;
    private SurfaceTexture mSurfaceTexture = null;
    private FaceDetector mFaceDetector;

    private static final int WIDTH = 352;
    private static final int HEIGHT = 288;
    public int mBufferSize;
    public byte[] mBuffer;

    public CameraPreview(Context context, AttributeSet attrs, int defStyleAttr){
        super(context, attrs, defStyleAttr);
        this.mContext = context;
    }

    public CameraPreview(Context context, AttributeSet attrs){
        super(context, attrs);
        this.mContext = context;
    }

    public CameraPreview(Context context){
        super(context);
        this.mContext = context;
    }

    public void init(MainActivity activity){
        this.mActivity = activity;
        mSurfaceHolder = this.getHolder();
        mSurfaceHolder.setKeepScreenOn(true);
        mSurfaceHolder.addCallback(this);

        mSurfaceTexture = new SurfaceTexture(10);
        mFaceDetector = new FaceDetector(this.mContext, this.mActivity);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder){
        try{
            if(mCamera == null){
                mCamera = Camera.open(1);
            }
        }catch (Exception e){
            Log.d(TAG, "Failed to open Camera");
        }
        try{
            if(mCamera == null){
                mCamera = Camera.open();
            }
        }catch (Exception e){
            Log.d(TAG, "Failed to open Camera");
        }
        try{
            mCamera.setPreviewTexture(mSurfaceTexture);
        }catch (Exception e){
            Log.d(TAG, "Failed to open Camera");
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width,
                               int height){
        if(mCamera == null){
            Log.e(TAG,"Camera not work correctly!");
            return;
        }
        try{
            Camera.Parameters params = mCamera.getParameters();
            List<Camera.Size> previewSizes = params.getSupportedPreviewSizes();
            for(int i=0; i<previewSizes.size(); i++){
                Camera.Size s = previewSizes.get(i);
                Log.d(TAG, "PreviewSize,width: " + s.width + " height " + s.height);
            }
            List<int[]> previewFps = params.getSupportedPreviewFpsRange();
            for (int i = 0; i < previewFps.size(); i++) {
                int[] s = previewFps.get(i);
                Log.d(TAG, "Preview FPS range: " + s[0] + " -> " + s[1]);
            }

            params.setPreviewSize(WIDTH, HEIGHT); // 指定preview的大小
            //params.setPreviewFpsRange(7000, 30000);
            params.setPreviewFormat(ImageFormat.NV21);
            mCamera.setParameters(params);

            mBufferSize = WIDTH * HEIGHT * ImageFormat.getBitsPerPixel(params.getPreviewFormat()) / 8;
            mBuffer = new byte[mBufferSize];

            mCamera.addCallbackBuffer(mBuffer);
            mCamera.setPreviewCallbackWithBuffer(new FrameCallback());
            mCamera.startPreview();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder){
        if(mCamera == null){
            Log.e(TAG, "Camera not work correctly!");
            return;
        }
        mCamera.setPreviewCallbackWithBuffer(null); //这个必须在前，不然退出出错
        mCamera.stopPreview();
        mCamera.release();
        mCamera = null;
    }

    private class FrameCallback implements Camera.PreviewCallback{
        @Override
        public void onPreviewFrame(byte[] data, Camera camera){

            camera.addCallbackBuffer(mBuffer);
            final YuvImage image = new YuvImage(data, ImageFormat.NV21, WIDTH, HEIGHT, null);
            ByteArrayOutputStream os = new ByteArrayOutputStream(data.length);

            if (image.compressToJpeg(new Rect(0, 0, WIDTH, HEIGHT), 100, os)) {
                byte[] tmp = os.toByteArray();
                Bitmap src = BitmapFactory.decodeByteArray(tmp, 0, tmp.length);
                //rotate 90 degree
                Matrix matrix = new Matrix();
                matrix.postScale(-1, 1);
                matrix.postTranslate(src.getWidth(), 0);
                final Bitmap dst = Bitmap.createBitmap(src.getWidth(), src.getHeight(), src.getConfig());
                new Canvas(dst).drawBitmap(src, matrix, new Paint());

                synchronized (mSurfaceHolder) {
                    mFaceDetector.getLandmarks(dst);
                    Canvas canvas = mSurfaceHolder.lockCanvas();
                    canvas.drawBitmap(dst, 0, 0, null);
                    mSurfaceHolder.unlockCanvasAndPost(canvas);
                }

            }
        }
    }
}
