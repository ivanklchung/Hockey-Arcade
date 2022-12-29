package com.example.arcade;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Objects;

public class Game3EndActivity extends AppCompatActivity
{

    private TextView textView_info;
    private TextView textView_score, textView_highscore;

    private Button PlayAgainButton,
                   HomeButton;

    SharedPreferences sharedPreferences;

    int score;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game3end);

        // hide top bar
        Objects.requireNonNull(getSupportActionBar()).hide();

        // score + highscore
        textView_score = findViewById(R.id.textView_Score);
        textView_highscore = findViewById(R.id.textView_Highscore);

        // not applicable
        textView_info = findViewById(R.id.textView_Description);

        // Play again button restarts game
        // countdown -> start game
        PlayAgainButton = findViewById(R.id.button_PlayAgain);
        PlayAgainButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                Intent intentR = new Intent(Game3EndActivity.this, Game3.class);
                startActivity(intentR);
            }
        });

        // Go Main Activity
        HomeButton = findViewById(R.id.button_GoHome);
        HomeButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                openActivityHome();
            }
        });


        // transfer user score
        score = getIntent().getIntExtra("score",0);
        // print score on the textView
        textView_score.setText("Score: " + score);

        sharedPreferences = this.getSharedPreferences("Score", Context.MODE_PRIVATE);
        // saving data
        int highestScore = sharedPreferences.getInt("highestScore",0);

        // checking if highestscore recorded is a HIGHSCORE?
        if (score >= highestScore)
        {
            // replacing highestScore with new user HIGHSCORE
            sharedPreferences.edit().putInt("highestScore", score).apply();
            // printing highscore on textview
            textView_highscore.setText("Highscore: " + score);
        }
        // highscore has NOT been beat
        // show past highscore recorded
        else
        {
            textView_highscore.setText("Highscore: " + highestScore);
        }

    }

    // for home button method
    // lead to Main Activity page
    public void openActivityHome()
    {
        Intent intent2 = new Intent(this, MainActivity.class);
        startActivity(intent2);
    }
}