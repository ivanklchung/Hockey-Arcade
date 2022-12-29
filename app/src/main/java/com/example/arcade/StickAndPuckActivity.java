package com.example.arcade;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.media.MediaPlayer;
import android.os.Handler;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;

import java.util.Random;


// creating game and drawing the rink that includes score and health
// import sound and animation that include collision techniques

public class StickAndPuckActivity extends View
{
    Context context;

    // X and Y coordinates of puck
    float puckX, puckY;

    // speed of puck
    PuckVelocity velocity = new PuckVelocity(20,25);

    // Handler of events, helps execute later
    Handler handler;

    // delay in milliseconds (30)
    final long updateMillisec = 30;

    // run define later!
    Runnable runnable;

    // Draw points
    Paint scorePaint = new Paint();
    // How many lives left
    Paint healthbarPaint = new Paint();

    // text size
    float TEXT_SIZE = 120;

    // keep track of stick position on screen
    // corrdinates x and y used
    float stickX, stickY;
    float oldStickX, oldStickY;

    // Point system score
    // start at 0
    int score = 0;

    // Health Lives (3 bars)
    // Green 3 left
    // Yellow 2 left
    // Red 1 left
    // Game over when none left
    int health_lives = 3;


    // store puck and stick images
    Bitmap puck,
           stick;

    // screen rink width and screen height
    int screenWidth, screenHeight;

    // sounds of puck hitting stick and puck missing stick
    MediaPlayer hitStickSound, missStickSound;

    Random random;
    SharedPreferences sharedPreferences;

    // On or Off state
    Boolean audioState;


    public StickAndPuckActivity(Context context)
    {
        super(context);
        this.context = context;

        // Puck and Hockey Stick
        // https://developer.android.com/reference/android/graphics/BitmapFactory
        puck = BitmapFactory.decodeResource(getResources(),R.drawable.puck);
        stick = BitmapFactory.decodeResource(getResources(),R.drawable.stick);

        handler = new Handler();
        runnable = new Runnable()
        {
            @Override
            public void run()
            {
                // refreshing the page by redrawing
                invalidate();

            }
        };

        // sound effects
        // hit
        hitStickSound = MediaPlayer.create(context,R.raw.hit);
        // miss
        missStickSound = MediaPlayer.create(context,R.raw.miss);

        // setting up the text dimensions and visual
        scorePaint.setColor(Color.BLUE);
        scorePaint.setTextSize(TEXT_SIZE);
        scorePaint.setTextAlign(Paint.Align.LEFT);
        // start green but will change
        healthbarPaint.setColor(Color.GREEN);

        // height and width of rink screen
        // https://developer.android.com/reference/android/graphics/Point
        Display display = ((Activity) getContext()).getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        screenWidth = size.x;
        screenHeight = size.y;

        // instantiate random object
        random = new Random();
        // Initial value: 0 -> width # - 1
        puckX = random.nextInt(screenWidth);
        // Initial value for stick
        // little above the bottom of screen measurement
        stickY = (screenHeight * 4) /5;
        // CENTER: want to try and use half of stick width
        stickX = screenWidth / 2 - stick.getWidth() / 2;

        sharedPreferences = context.getSharedPreferences("my_pref", 0);
        audioState = sharedPreferences.getBoolean("audioState",true);

    }

    // drawing the game
    // https://developer.android.com/reference/android/graphics/Canvas

    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        // make background white = ice sheet
        canvas.drawColor(Color.WHITE);

        // getting the Velocity
        puckX += velocity.getX();
        puckY += velocity.getY();

        // _____________________________________________
        // conditions for puck hit edge of screen

        // Collision check: checking left and right it hit wall
        if ((puckX >= screenWidth - puck.getWidth()) || puckX <= 0)
        {
            // switch direction if hit
            velocity.setX(velocity.getX() * -1);
        }

        // Collision check: checking hit top wall
        if (puckY <= 0)
        {
            // switch direction if hit
            velocity.setY(velocity.getY() * -1);
        }

        // Collision check: checking hit base floor
        if (puckY > stickY + stick.getHeight())
        {
            // hits stick
            puckX = 1 + random.nextInt(screenWidth - puck.getWidth() - 1);
            puckY = 0;

            // Missed the stick
            if (missStickSound != null && audioState)
            {
                // if still lives left, make a miss sound
                missStickSound.start();
            }


            // Condition: if x and y same velocity, just make a new Velocity to keep it moving
            velocity.setX(VelocityX());
            velocity.setY(40);

            // keep subtracting life
            // when life = 0, launch Game Over Activity
            health_lives--;

            // check how many lives are left
            // if no more lives
            // record score and open the game over results page
            if (health_lives == 0)
            {
                Intent intent = new Intent(context, GameOverStickPuck.class);
                intent.putExtra("points", score);
                context.startActivity(intent);
                ((Activity) context).finish();
            }
        }

        // Conditions for hitting the puck on stick

        // Condition 1: RIGHT side of puck hits LEFT side of stick
        // Condition 2: LEFT side of puck hits RIGHT side of stick
        // Condition 3: BOTTOM of puck overlaps the TOP of the stick
        // Condition 4: BOTTOM of puck should not go BELOW top of stick

        if (((puckX + puck.getWidth()) >= stickX) &&
        (puckX <= stickX + stick.getWidth()) &&
        (puckY + puck.getHeight() >= stickY) &&
        (puckY + puck.getHeight() <= stickY + stick.getHeight()))
        {
            // meets above requirements = collision occurs
            if (hitStickSound != null && audioState)
            {
                hitStickSound.start();
            }

            // bounce puck from stick
            velocity.setX(velocity.getX() + 1);
            velocity.setY((velocity.getY() + 1) * -1);

            // increase point for a hit
            score++;
        }

        // PUCK
        canvas.drawBitmap(puck, puckX, puckY,null);
        // STICK
        canvas.drawBitmap(stick, stickX, stickY, null);
        // SCORE
        canvas.drawText("" + score, 20, TEXT_SIZE, scorePaint);


        // HEALTH conditions

        // 2 lives left
        if (health_lives == 2)
        {
            healthbarPaint.setColor(Color.YELLOW);
        }

        // 1 lives left
        else if (health_lives ==1)
        {
            healthbarPaint.setColor(Color.RED);
        }

        // Drawing the Life Bar (Health)
        // want to put it top right
        // void drawRect(float left, float top, float right, float bottom, Paint paint)
        // https://docs.oracle.com/javase/7/docs/api/java/awt/Graphics.html
        canvas.drawRect(screenWidth -200, 30, screenWidth - 200 + 60 * health_lives, 80, healthbarPaint);
        // create infinite game Loop
        handler.postDelayed(runnable, updateMillisec);



    }


    // Detecting Touch Events
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float touchX = event.getX();
        float touchY = event.getY();

        if (touchY > stickY)
        {
            // store the action
            int action = event.getAction();
            // touch screen = action_down action is activated
            if (action == MotionEvent.ACTION_DOWN)
            {
                oldStickX = event.getX();
                oldStickY = stickX;
            }
            // check action
            // will be continuous as long as user is moving finger on screen
            if (action == MotionEvent.ACTION_MOVE)
            {
                float shift = oldStickX - touchX;
                float newStickX = oldStickY - shift;
                if (newStickX <= 0)
                    stickX = 0;
                else if (newStickX >= screenWidth - stick.getWidth())
                    stickX = screenWidth - stick.getWidth();
                else
                    stickX = newStickX;
            }

        }

        return true;
    }

    private int VelocityX()
    {
        // integer array hold values for x velocity values
        // pick some random integers both negative and positive
        int[] values = {-35, -30, -25, 25, 30, 35};
        // randomly generate index from the above array (Index 0-5)
        int index = random.nextInt(6);
        return values[index];
    }
}
