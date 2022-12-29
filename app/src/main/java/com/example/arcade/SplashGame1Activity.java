package com.example.arcade;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashGame1Activity extends AppCompatActivity {

    private ImageView imageView;
    private TextView textView;

    Animation animationImage, animationText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_game1);


        textView = findViewById(R.id.textView2);
        imageView = findViewById(R.id.imageView2);


        animationImage = AnimationUtils.loadAnimation(this,R.anim.image_animation);
        animationText = AnimationUtils.loadAnimation(this,R.anim.text_animation);


        imageView.setAnimation(animationImage);
        textView.setAnimation(animationText);

        new CountDownTimer(5000, 1000) {
            @Override
            // what happen in each second
            public void onTick(long l) {

            }

            @Override
            // what happen when code expire
            public void onFinish() {
                startActivity(new Intent(SplashGame1Activity.this, Game1.class));
                finish();
            }
        }.start();
    }
}