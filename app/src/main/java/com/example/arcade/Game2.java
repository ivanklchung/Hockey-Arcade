package com.example.arcade;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;

// used stickpng.com for access for photos
// used freesound.org for access for audio files
// used onlinepngtool.com to resize png files

public class Game2 extends AppCompatActivity
{
    SharedPreferences sharedPreferences;
    Boolean audioState;
    ImageButton puckAudio, puckHome;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game2);

        // make forever full screen
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        // declare variables
        puckAudio = findViewById(R.id.imageButton_Audio);
        puckHome = findViewById(R.id.imageButton_HOME);

        // my_pref and private mode
        sharedPreferences = getSharedPreferences("my_pref",0);

        // start the audio as always on
        audioState = sharedPreferences.getBoolean("audioState",true);

        // when audio will be played
        // true = ON
        // false = OFF
        if (audioState)
        {
            puckAudio.setImageResource(R.drawable.audio_on);
        }
        else
        {
            puckAudio.setImageResource(R.drawable.audio_off);
        }
    }

    // Game Starts when click the play button (onClick)
    public void startGame(View view)
    {
        StickAndPuckActivity shootActivity = new StickAndPuckActivity(this);
        setContentView(shootActivity);
    }

    // Audio button (think of as toggle button)
    public void audioPref(View view)
    {
        // already on and clicked >>> set to off
        if (audioState)
        {
            audioState = false;
            puckAudio.setImageResource(R.drawable.audio_off);
        }
        // off and clicked >>> set to on
        else
        {
            audioState = true;
            puckAudio.setImageResource(R.drawable.audio_on);
        }

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("audioState",audioState);
        editor.commit();
    }

    // Home button
    public void home(View view)
    {
        Intent intent = new Intent(Game2.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}