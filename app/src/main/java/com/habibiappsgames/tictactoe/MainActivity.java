package com.habibiappsgames.tictactoe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // 0-Red, 1-Blue, 2-Empty

    //Default Game State
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};

    //Winning Positions Array
    int[][] winningPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};

    //Active Player Turn
    int activePlayer = 0;

    boolean gameActive = true;

    // Counter Dropping Function
    public void dropIn(View view){

        //Counter Image
        ImageView counter = (ImageView) view;

        //Getting Counter Tag
        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        //Counter Dropping Condition
        if (gameState[tappedCounter] == 2 && gameActive) {
            gameState[tappedCounter] = activePlayer;
            counter.setTranslationY(-1500);
            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.blue);
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.red);
                activePlayer = 0;
            }

            //Counter Dropping Animation
            counter.animate().translationYBy(1500).rotation(3600).setDuration(300);

            // Loop through winning arrays
            for (int[] winningPosition : winningPositions) {
                if (gameState[winningPosition[0]] == gameState[winningPosition[1]] && gameState[winningPosition[1]] == gameState[winningPosition[2]] && gameState[winningPosition[0]] != 2) {

                    gameActive = false;
                    String winner = "";
                    if (activePlayer == 1) {
                        winner = "blue";
                    } else {
                        winner = "Red";
                    }
                    Toast.makeText(this, winner + " has won", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    public  void playAgain (View view){

        //Game's GridLayout
        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);

        //Loop Through gridlayout Child's
        for (int i=0; i<gridLayout.getChildCount(); i++) {
            ImageView counter = (ImageView) gridLayout.getChildAt(i);
            counter.setImageDrawable(null);
        }

        //Setting Game State
        for (int i=0; i<gameState.length; i++) {
            gameState[i]= 2;
        }

        activePlayer = 0;
        gameActive = true;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}