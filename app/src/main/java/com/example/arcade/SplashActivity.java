package com.example.arcade;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

// https://developer.android.com/reference/android/view/Window
// https://developer.android.com/reference/android/os/Handler
// https://www.tutlane.com/tutorial/android/android-fade-in-out-animations-with-examples
public class SplashActivity extends AppCompatActivity
{

    // how long hold splash screen for
    private static int SPLASH_SCREEN_TIMEOUT = 3000;


    @Override
    protected void onCreate(Bundle savedInstanceState)

    {
        super.onCreate(savedInstanceState);

        // set window of app full screen, can't see anything when on
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.splash_activity);

        // adding an animation to fade to nothing (showing to invisible)
        Animation fadeOut = new AlphaAnimation(1,0);
        fadeOut.setInterpolator(new AccelerateInterpolator());
        // stays for a bit
        fadeOut.setStartOffset(600);
        // set duration
        fadeOut.setDuration(2850);
        ImageView image = findViewById(R.id.imageView_hockeystick);

        // fading
        image.setAnimation(fadeOut);


        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, SPLASH_SCREEN_TIMEOUT);
    }
}
