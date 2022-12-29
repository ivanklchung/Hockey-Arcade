package com.example.arcade;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Objects;

// image citation
// https://www.freepik.com/premium-photo/empty-hockey-arena_17829419.htm#query=hockey&position=12&from_view=search&track=sph

// Game 1 inspiration
// https://play.google.com/store/apps/details?id=com.youdagames.higherlower&hl=en_CA&gl=US

// Game 2 inspiration
// https://play.google.com/store/apps/details?id=com.puzzle1studio.go.bubblepoporiginpuzzlegame&hl=en_CA&gl=US
// https://play.google.com/store/apps/details?id=com.rasterage.micro.breaker&hl=en_CA&gl=US
// https://play.google.com/store/apps/details?id=com.natenai.glowhockey&hl=en_CA&gl=US


// Game 3 inspiration
// https://play.google.com/store/apps/details?id=com.bitmango.go.bubblepop&hl=en_CA&gl=US
// https://play.google.com/store/apps/details?id=org.cocos2d.MD_WhackAMole&hl=en_CA&gl=US

public class MainActivity extends AppCompatActivity
{
    private Button game1_button,
                   game2_button,
                   game3_button,
                   contact_button;

    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // hide top bar
        Objects.requireNonNull(getSupportActionBar()).hide();

        // declaring variables
        game1_button = findViewById(R.id.button_game1);
        game2_button = findViewById(R.id.button_game2);
        game3_button = findViewById(R.id.button_game3);
        contact_button = findViewById(R.id.button_contact);


        // setting up the onClickListener for each button

        // Game 1 - Mystery Jersey
        game1_button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Opening Mystery Jersey", Toast.LENGTH_SHORT).show();
                openActivityGame1();
            }
        });

        // Game 2 - Stick and Puck
        game2_button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Opening Stick and Puck", Toast.LENGTH_SHORT).show();
                openActivityGame2();
            }
        });

        // Game 3 - Tap Tap Puck
        game3_button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Opening Tap Tap Puck", Toast.LENGTH_SHORT).show();
                openActivityGame3();
            }
        });


        // Contact - open gmail
        contact_button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Opening Contact Info", Toast.LENGTH_SHORT).show();
                openActivityContact();

            }
        });



    }



    // Open up and load Game 1
    public void openActivityGame1()
    {
        Intent intent = new Intent(this, Game1.class);
        startActivity(intent);
    }

    // Open up and load Game 2
    public void openActivityGame2()
    {
        Intent intent = new Intent(this, Game2.class);
        startActivity(intent);
    }

    // Open up and load Game 3
    public void openActivityGame3()
    {
        Intent intent = new Intent(this, Game3.class);
        startActivity(intent);
    }

    // Open up and load Contact PAGE
    // https://developer.android.com/guide/components/intents-common
    public void openActivityContact()
    {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        String UriText = "mailto:" + Uri.encode("ichungtru@gmail.com") + "?subjects=" +
                Uri.encode("Feedback") + "$description=" + Uri.encode("");
        Uri uri = Uri.parse(UriText);
        intent.setData(uri);
        startActivity(Intent.createChooser(intent,"send email"));
    }



}