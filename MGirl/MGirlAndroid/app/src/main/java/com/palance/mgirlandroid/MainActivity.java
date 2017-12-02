package com.palance.mgirlandroid;

import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.unity3d.player.UnityPlayer;

public class MainActivity extends AppCompatActivity {
    protected UnityPlayer mUnityPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mUnityPlayer = new UnityPlayer(this);
        ConstraintLayout layout = (ConstraintLayout)findViewById(R.id.mainLayout);
        layout.addView(mUnityPlayer);
    }

    @Override
    protected void onResume(){
        super.onResume();
        mUnityPlayer.resume();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus){
        super.onWindowFocusChanged(hasFocus);
        mUnityPlayer.windowFocusChanged(hasFocus);
    }
}
