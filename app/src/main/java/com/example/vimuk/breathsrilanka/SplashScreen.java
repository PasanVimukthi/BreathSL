package com.example.vimuk.breathsrilanka;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashScreen extends AppCompatActivity {

    private int SLEEP_TIMER = 4;
    private TextView tv1;
    private TextView tv2;
    private EditText et1;
    private ImageView iv1;
    private ImageView iv2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_splash_screen);

        tv1 = (TextView) findViewById(R.id.textView);
        tv2 = (TextView) findViewById(R.id.textView2);
        et1 = (EditText) findViewById(R.id.editText);
        iv1 = (ImageView)  findViewById(R.id.imageView);
        iv2 = (ImageView)  findViewById(R.id.imageView2);

        Animation myanim = AnimationUtils.loadAnimation(this, R.anim.splashanimation);
        tv1.startAnimation(myanim);
        tv2.setAnimation(myanim);
        et1.setAnimation(myanim);
        iv1.setAnimation(myanim);
        iv2.setAnimation(myanim);

        getSupportActionBar().hide();

        LogoLauncher logolauncher = new LogoLauncher();
        logolauncher.start();

    }


    private class LogoLauncher extends Thread {

        public void run() {

            try {
                sleep(1000 * SLEEP_TIMER);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Intent intent = new Intent(SplashScreen.this, MainActivity.class);
            startActivity(intent);
            SplashScreen.this.finish();

        }

    }
}