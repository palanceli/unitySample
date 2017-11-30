package com.sample.palance.rollaball;

import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.unity3d.player.UnityPlayer;

public class MainActivity extends AppCompatActivity {
    protected UnityPlayer mUnityPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        mUnityPlayer = findViewById(R.id.unityPlayer);

        mUnityPlayer = new UnityPlayer(this);
        ConstraintLayout layout = (ConstraintLayout)findViewById(R.id.layout);
        layout.addView(mUnityPlayer);

//        Button scanButton = (Button)findViewById(R.id.scanbutton);
//        scanButton.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View view){
//                Toast.makeText(MainActivity.this,
//                        "正在启动扫描程序，请稍等...", Toast.LENGTH_LONG)
//                        .show();
//            }
//        });
    }
}
