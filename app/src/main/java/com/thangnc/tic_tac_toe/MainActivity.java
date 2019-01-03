package com.thangnc.tic_tac_toe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int activePlayer = 0;

    boolean gameIsActive = true;

    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};

    int[][] winPosition = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void dropIn(View view) {
        ImageView counter = (ImageView) view;

        System.out.println(counter.getTag().toString());

        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        if (gameState[tappedCounter] == 2 && gameIsActive ) {

            gameState[tappedCounter] = activePlayer;
            counter.setTranslationY(-1000f);

            if (activePlayer == 0) {

                counter.setImageResource(R.drawable.yellow);

                activePlayer = 1;

            } else {
                counter.setImageResource(R.drawable.red);

                activePlayer = 0;
            }
            counter.animate().translationYBy(1000f).setDuration(300);

            for (int[] winPosition : winPosition) {
                if (gameState[winPosition[0]] == gameState[winPosition[1]] &&
                        gameState[winPosition[1]] == gameState[winPosition[2]] &&
                        gameState[winPosition[0]] != 2) {

                    gameIsActive = false;

                    String winner = "Red";
                    if (gameState[winPosition[0]] == 0) {
                        winner = "Yellow";
                    }
                    // someone has won!!

                    TextView winnerPlayer = findViewById(R.id.tvWinPlayer);

                    winnerPlayer.setText(winner + " has won!");

                    LinearLayout layout = findViewById(R.id.playAgain);

                    layout.setVisibility(View.VISIBLE);
                } else {
                    boolean gameIsOver = true;
                    for (int counterState : gameState){
                        if (counterState == 2) gameIsOver = false;
                    }
                    if (gameIsOver){
                        TextView winnerPlayer = findViewById(R.id.tvWinPlayer);

                        winnerPlayer.setText("DRAW!!!");

                        LinearLayout layout = findViewById(R.id.playAgain);

                        layout.setVisibility(View.VISIBLE);
                    }
                }
            }
        }
    }

    public void playAgain(View view){

        gameIsActive = true;

        LinearLayout layout = findViewById(R.id.playAgain);

        layout.setVisibility(View.INVISIBLE);

        activePlayer = 0;

        for (int i = 0; i < gameState.length; i++){
            gameState[i] = 2;
        }

        GridLayout gridLayout = findViewById(R.id.gridLayout);

        for (int i = 0; i < gridLayout.getChildCount(); i ++){

            ((ImageView)gridLayout.getChildAt(i)).setImageResource(0);
        }
    }
}
