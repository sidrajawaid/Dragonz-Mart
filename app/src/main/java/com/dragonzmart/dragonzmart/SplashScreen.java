package com.dragonzmart.dragonzmart;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashScreen extends AppCompatActivity {
    ImageView logo_image;
    TextView logo_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        logo_image=findViewById(R.id.logoimage);
        logo_text=findViewById(R.id.logotext);
        Animation splashanimation= AnimationUtils.loadAnimation(this,R.anim.splashscreenfile);
        logo_image.startAnimation(splashanimation);
        logo_text.startAnimation(splashanimation);
        final Intent home_screen=new Intent(this,HomeScreen.class);
        final Thread thread=new Thread(){
            public void run()
            {
                try {
                    sleep(4500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                finally {
                    startActivity(home_screen);
                    finish();
                }
            }
        };
        thread.start();


    }
}

