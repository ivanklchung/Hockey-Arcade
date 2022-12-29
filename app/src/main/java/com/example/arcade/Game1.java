package com.example.arcade;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

import com.google.android.material.snackbar.Snackbar;

import java.util.Objects;

// Mystery Jersey Game
// Try and guess the hockey jersey number in 10 tries

public class Game1 extends AppCompatActivity
{
    private Button button_Home;
    private Intent intent2;


    private Button button_Start;
    private RadioButton twoDigit, threeDigit, fourDigit;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game1);

        // hide top bar
        Objects.requireNonNull(getSupportActionBar()).hide();

        button_Home = findViewById(R.id.button_HomePage);
        button_Home.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                openActivity2();
            }
        });

        // Start Game
        button_Start = findViewById(R.id.button_Start);
        button_Start.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intentR = new Intent(Game1.this, MysteryJerseyActivity.class);

                // no radio selections selected
                if(!twoDigit.isChecked() && !threeDigit.isChecked() && !fourDigit.isChecked() )
                {

                    Snackbar.make(view,"Please select number of digits", Snackbar.LENGTH_LONG).show();
                }
                else
                {
                    // two digit selected
                    if (twoDigit.isChecked())
                    {
                        intentR.putExtra("two",true);
                    }
                    // three digits selected
                    if (threeDigit.isChecked())
                    {
                        intentR.putExtra("three",true);
                    }
                    // four digit selected
                    if (fourDigit.isChecked())
                    {
                        intentR.putExtra("four",true);
                    }

                    // at least  one selected time to start game
                    startActivity(intentR);

                }
            }
        });


        twoDigit = findViewById(R.id.radio_twoJersey);
        threeDigit = findViewById(R.id.radio_threeJersey);
        fourDigit = findViewById(R.id.radio_fourJersey);



    }

    // Go to Main Activity
    public void openActivity2()
    {
        Intent intent2 = new Intent(this, MainActivity.class);
        startActivity(intent2);
    }
}