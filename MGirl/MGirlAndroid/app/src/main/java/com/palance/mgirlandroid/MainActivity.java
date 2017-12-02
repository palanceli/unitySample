package com.palance.mgirlandroid;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.media.MediaActionSound;
import android.os.Build;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.unity3d.player.UnityPlayer;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    protected UnityPlayer mUnityPlayer = null;
    private static String[] PERMISSIONS_REQ = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_APN_SETTINGS,
            Manifest.permission.CAMERA
    };
    private static final int REQUEST_CODE_PERMISSION = 2;
    private CameraPreview mCameraPreview = null;
    private static final String ACTION_UPDATE_MOJI = "com.palance.mgirlandroid.UPDATE_MOJI";

    protected final double[] emotion = new double[10];

    static Intent newMojiIntent(){
        Intent intent = new Intent(ACTION_UPDATE_MOJI);
        return intent;
    }

    private BroadcastReceiver mUpdateMojiReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(context, "Mouth opened", Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(Build.VERSION.SDK_INT <= Build.VERSION_CODES.M){
            ActivityCompat.requestPermissions(this,
                    PERMISSIONS_REQ,
                    REQUEST_CODE_PERMISSION);
        }

        IntentFilter filter = new IntentFilter(ACTION_UPDATE_MOJI);
        registerReceiver(mUpdateMojiReceiver, filter);

        mCameraPreview = (CameraPreview)findViewById(R.id.cam_preview);
        mCameraPreview.init(this);

//        mUnityPlayer = new UnityPlayer(this);
//        ConstraintLayout layout = (ConstraintLayout)findViewById(R.id.mainLayout);
//        layout.addView(mUnityPlayer);

        TimerTask task = new TimerTask(){
            @Override
            public void run(){
                if(emotion[3] > 0.5){
                    Intent intent = newMojiIntent();
                    sendBroadcast(intent);
                }
            }
        };
        Timer timer = new Timer();
        timer.schedule(task, 0, 1000);
    }

    @Override
    protected void onResume(){
        super.onResume();
        if(mUnityPlayer != null) {
            mUnityPlayer.resume();
        }
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus){
        super.onWindowFocusChanged(hasFocus);
        if(mUnityPlayer != null) {
            mUnityPlayer.windowFocusChanged(hasFocus);
        }
    }

    @Override
    protected void onDestroy(){
        unregisterReceiver(mUpdateMojiReceiver);
    }

}
