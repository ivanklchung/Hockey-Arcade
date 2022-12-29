package com.example.arcade;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

// page will open when game over

public class GameOverStickPuck extends AppCompatActivity
{
    TextView points;
    TextView highscore;
    SharedPreferences sharedPreferences;
    ImageView newHighScoreImage;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.game_over);


        points = findViewById(R.id.Points_Value);
        highscore = findViewById(R.id.Highscore_Value);
        newHighScoreImage = findViewById(R.id.imageView_NewHighScoreMedal);


        // POINTS
        int points = getIntent().getExtras().getInt("points");
        this.points.setText("" + points);
        sharedPreferences = getSharedPreferences("my_pref", 0);

        // HIGHSCORE
        int highest = sharedPreferences.getInt("highest", 0);


        // if there is a new high score!
        // set visibility ON
        if (points > highest)
        {
            newHighScoreImage.setVisibility(View.VISIBLE);
            highest = points;
            SharedPreferences.Editor editor = sharedPreferences.edit();
            // NEW HIGHSCORE
            editor.putInt("highest", highest);
            editor.commit();
        }

        // set the NEW HIGHSCORE text to be the score
        highscore.setText("" + highest);

    }

    // restart game
    public void restart(View view)
    {
        Intent intent = new Intent(GameOverStickPuck.this, Game2.class);
        startActivity(intent);
        finish();
    }

    // Go to main page
    public void home(View view)
    {
        Intent intent = new Intent(GameOverStickPuck.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
