package com.example.arcade;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class MysteryJerseyActivity extends AppCompatActivity
{

    private TextView lastGuess, remainingGuess, hint;
    private Button buttonConfirm;
    private EditText editTextGuess;

    boolean twoDigits,
            threeDigits,
            fourDigits;

    Random r = new Random();

    int random;
    // Can Change How Many Guesses are Allowed
    int totalGuessAllow = 10;

    ArrayList<Integer> ListOfGuesses = new ArrayList<>();

    int userAttempt = 0;

    int digits = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mystery_jersey);

        // hide top bar
        Objects.requireNonNull(getSupportActionBar()).hide();

        // declare variables
        hint = findViewById(R.id.textView_Hint);
        remainingGuess = findViewById(R.id.textView_RemainingGuesses);
        lastGuess = findViewById(R.id.textView_LastGuess);
        buttonConfirm = findViewById(R.id.button_Confirm);
        editTextGuess = findViewById(R.id.editText_Guess);

        // which radio button selection selected
        twoDigits = getIntent().getBooleanExtra("two",false);
        threeDigits = getIntent().getBooleanExtra("three",false);
        fourDigits = getIntent().getBooleanExtra("four",false);

        // two digit mode selected
        if (twoDigits)
        {
            // if zero generate randomly, 10 value will be transferred to random
            random = r.nextInt(90) +10;
            digits = 2;
        }

        // three digit mode selected
        if (threeDigits)
        {
            // if zero generate randomly, 100 value will be transferred to random
            random = r.nextInt(900) +100;
            digits = 3;
        }

        // 4 digit mode selected
        if (fourDigits)
        {
            // if zero generate randomly, 1000 value will be transferred to random
            random = r.nextInt(9000) +1000;
            digits = 4;
        }


        buttonConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                // getting user entry
                String guess = editTextGuess.getText().toString();



                // check if entry is empty
                if (guess.equals(""))
                {
                    Toast.makeText(MysteryJerseyActivity.this, "Need to enter a guess", Toast.LENGTH_LONG).show();
                }

                // if # digits not same as selected
                if ( (guess.length() < digits ) || (guess.length() > digits ))
                {
                    // too many digit guess
                    if (guess.length() > digits)
                    {
                        Toast.makeText(MysteryJerseyActivity.this, "Warning: TOO MANY DIGITS", Toast.LENGTH_LONG).show();
                    }

                    // too few digit guess
                    else if (guess.length() < digits)
                    {
                        Toast.makeText(MysteryJerseyActivity.this, "Warning: TOO FEW DIGITS", Toast.LENGTH_LONG).show();
                    }
                }


                // if not empty
                // show the 3 TextViews, pop up > Game has officially commenced
                else
                {
                    lastGuess.setVisibility(View.VISIBLE);
                    remainingGuess.setVisibility(View.VISIBLE);
                    hint.setVisibility(View.VISIBLE);

                    // increase value of user attempt
                    userAttempt++;

                    // used up a guess
                    totalGuessAllow--;

                    // convert guess value to an integer type
                    int userGuess = Integer.parseInt(guess);
                    //add value user guess to guess list
                    ListOfGuesses.add(userGuess);

                    // user's last guess
                    lastGuess.setText("Your last guess: " + guess);
                    // update how many guesses left
                    remainingGuess.setText("Remaining guesses left: " + totalGuessAllow);

                    // what if correct answer?
                    // Alert Created
                    // Create message with all the stats
                    // Ask user if want to play again ( YES or NO)
                    //https://developer.android.com/reference/android/app/AlertDialog.Builder

                    if (random == userGuess)
                    {
                        AlertDialog.Builder builder = new AlertDialog.Builder(MysteryJerseyActivity.this);
                        builder.setTitle("Mystery Jersey Guessing Game");
                        builder.setCancelable(false);
                        builder.setMessage("Game Over: CONGRATS!  The number was " + random+
                                "\n\nAttempts used: " + userAttempt +
                                " attempts \n\nPast guesses: "+ ListOfGuesses +
                                 "\n\n Play Again?");

                        // Play Again YES
                        builder.setPositiveButton("YES!", new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i)
                            {
                                Intent intent = new Intent (MysteryJerseyActivity.this, Game1.class);
                                startActivity(intent);
                                finish();
                            }
                        });

                        // Play Again NO
                        // direct to main page activity
                        builder.setNegativeButton("NO!", new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i)
                            {
                                Intent intent = new Intent (MysteryJerseyActivity.this, MainActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        });

                        builder.create().show();
                    }


                    // if user guess is less than
                    if (random < userGuess)
                    {
                        hint.setText("Guess lower");
                    }


                    // if user guess is greater than
                    if (random > userGuess)
                    {
                        hint.setText("Guess Higher");
                    }

                    // used up all guess attempts
                    // GAME OVER
                    if (totalGuessAllow == 0)
                    {
                        AlertDialog.Builder builder = new AlertDialog.Builder(MysteryJerseyActivity.this);
                        builder.setTitle("Mystery Jersey Guessing Game");
                        builder.setCancelable(false);
                        builder.setMessage("Game Over: Ran out of Guesses! The number was " + random+
                                "\n\nAttempts used: " + userAttempt +
                                " attempts \n\nPast guesses: "+ ListOfGuesses +
                                "\n\n Play Again?");

                        // Play Again YES
                        builder.setPositiveButton("YES!", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent = new Intent (MysteryJerseyActivity.this, Game1.class);
                                startActivity(intent);
                                finish();
                            }
                        });

                        // Play Again NO
                        builder.setNegativeButton("NO!", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent = new Intent (MysteryJerseyActivity.this, MainActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        });
                        // SHOW BUILD CREATE
                        builder.create().show();
                    }
                    // clear edit Text
                    editTextGuess.setText("");
                }
            }
        });

    }
}