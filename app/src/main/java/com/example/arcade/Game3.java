package com.example.arcade;

import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Objects;
import java.util.Random;

// used stickpng.com for access for photos
// used freesound.org for access for audio files
// used onlinepngtool.com to resize png files


public class Game3 extends AppCompatActivity
{
    private TextView textView_countdown,
            textView_timer, textViewScore,
            textViewTitle3;

    private ImageView puck_1, puck_2, puck_3,
                    puck_4, puck_5, puck_6,
                    puck_7, puck_8, puck_9;

    // Grid Rink
    private GridLayout gridLayout_Rink;

    // Array of all the pucks in the rink grid
    ImageView[] pucksInArray;

    // pass Score
    int score = 0;

    // Runnable + Handler
    Runnable runnable;
    Handler handler;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game3);

        // hide top bar
        Objects.requireNonNull(getSupportActionBar()).hide();


        // declare variables
        // don't need highscore, show that in the end game activity
        textViewScore = findViewById(R.id.textView_score);
        textView_timer = findViewById(R.id.textView_timer);
        textViewScore = findViewById(R.id.textView_score);
        textViewTitle3 = findViewById(R.id.textView_titleAndDescription);
        textView_countdown = findViewById(R.id.textView_countDown);

        // The pucks
        puck_1 = findViewById(R.id.puck_1);
        puck_2 = findViewById(R.id.puck_2);
        puck_3 = findViewById(R.id.puck_3);
        puck_4 = findViewById(R.id.puck_4);
        puck_5 = findViewById(R.id.puck_5);
        puck_6 = findViewById(R.id.puck_6);
        puck_7 = findViewById(R.id.puck_7);
        puck_8 = findViewById(R.id.puck_8);
        puck_9 = findViewById(R.id.puck_9);

        // 3 by 3 grid of a rink of pucks
        pucksInArray = new ImageView[]
                {puck_1, puck_2, puck_3,
                 puck_4, puck_5, puck_6,
                 puck_7, puck_8, puck_9};

        // Rink
        gridLayout_Rink = findViewById(R.id.gridLayout_Rink);


        // Count down from 3 seconds
        // make 4000 because 3 disappear too fast
        new CountDownTimer(4000, 1000) {
            @Override
            // determine what happens when time decrease
            // the long is how many seconds till it is over
            public void onTick(long millis)
            {
                textView_countdown.setText(String.valueOf(millis/1000));

            }

            // determine what happens when time is over
            // timer should disappear when finished counting down
            @Override
            public void onFinish()
            {
                controlPucks();

                // make a new count down timer for the game itself
                new CountDownTimer(30000, 1000) {
                    @Override
                    public void onTick(long millis)

                    {
                        textView_timer.setText("Time Remaining: "+ millis/1000);
                    }

                    @Override
                    public void onFinish()
                    {
                        Intent intent = new Intent(Game3.this, Game3EndActivity.class);
                        intent.putExtra("score", score);
                        startActivity(intent);
                        finish();
                    }
                }
                .start();
            }
        }
        // start
        .start();
    }

    // Click any puck, will increase score
    // Limit number of repetitiveness of individual click on each image
    // view is each puck
    // don't use onclick listener
    public void increaseScore(View view)
    {
        // increase score
        score++;
        textViewScore.setText("Score: " + score);


        // Know what puck was clicked to change image to respective goal horn
        // Position Puck 1-9
        if (view.getId() == puck_1.getId())
        {
            puck_1.setImageResource(R.drawable.light);
        }

        if (view.getId() == puck_2.getId())
        {
            puck_2.setImageResource(R.drawable.light);
        }

        if (view.getId() == puck_3.getId())
        {
            puck_3.setImageResource(R.drawable.light);
        }

        if (view.getId() == puck_4.getId())
        {
            puck_4.setImageResource(R.drawable.light);
        }

        if (view.getId() == puck_5.getId())
        {
            puck_5.setImageResource(R.drawable.light);
        }

        if (view.getId() == puck_6.getId())
        {
            puck_6.setImageResource(R.drawable.light);
        }

        if (view.getId() == puck_7.getId())
        {
            puck_7.setImageResource(R.drawable.light);
        }

        if (view.getId() == puck_8.getId())
        {
            puck_8.setImageResource(R.drawable.light);
        }

        if (view.getId() == puck_9.getId())
        {
            puck_9.setImageResource(R.drawable.light);
        }

    }

    // controlling the pucks
    public void controlPucks()
    {
        // Count Down timer disappear
        textView_countdown.setVisibility(View.INVISIBLE);

        // start the timer for overall game
        textView_timer.setVisibility(View.VISIBLE);
        // Show Score
        textViewScore.setVisibility(View.VISIBLE);




        handler = new Handler();
        runnable = new Runnable()
        {
            @Override
            public void run()
            {
                // make all the pucks invisible
                for (ImageView puck : pucksInArray)
                {
                    puck.setVisibility(View.INVISIBLE);
                    puck.setImageResource(R.drawable.tap);
                }

                // Show Rink Grid full of pucks
                gridLayout_Rink.setVisibility(View.VISIBLE);

                // make puck appear at random
                Random random = new Random();

                // random number up to 8 (0-9)
                int p = random.nextInt(pucksInArray.length);
                pucksInArray[p].setVisibility(View.VISIBLE);

                // how long wait
                // increases depends on how user does

                // original speed 1.5 sec
                if (score <=10)
                {
                    handler.postDelayed(runnable, 1500);
                }

                // 1.3 sec
                else if (score > 10 && score <= 20)
                {
                    handler.postDelayed(runnable, 1300);
                }

                // 1 sec
                else
                {
                    handler.postDelayed(runnable, 1000);
                }
            }
        };

        // put a handler post OUTSIDE the method
        handler.post(runnable);
    }
}