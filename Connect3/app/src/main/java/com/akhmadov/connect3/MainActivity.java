package com.akhmadov.connect3;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int activePlayer = 0;  // 0 - yellow 1 - red
    boolean isGameActive = true;
    int[] states = {-1, -1, -1, -1, -1, -1, -1, -1, -1};
    int[][] winningPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void dropIn(View view) {
        ImageView counter = (ImageView) view;
        int position = getPosition(counter);

        StringBuilder statesString = new StringBuilder();

        if (isGameActive && states[position] == -1) {
            // correct position and active the game
            counter.setTranslationY(-1000f);
            counter.setScaleX(0.5f);
            counter.setScaleY(0.5f);
            states[position] = activePlayer;

            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.yellow);
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.red);
                activePlayer = 0;
            }

            counter.animate().translationYBy(1000f).scaleX(1).scaleY(1).setDuration(400);
            for (int pos : states)
                statesString.append(Integer.toString(pos)).append(" ");
            Log.d("myLog", statesString.toString());

            for (int[] winningPosition : winningPositions) {
                for (int pos : states)
                    statesString.append(Integer.toString(pos)).append(" ");
                Log.d("myLog", statesString.toString() + "\n");

                if (states[winningPosition[0]] == states[winningPosition[1]]
                        && states[winningPosition[1]] == states[winningPosition[2]]
                        && states[winningPosition[0]] != -1) {
                    // someone win the game
                    String winnerPlayer = "Red";
                    if (states[winningPosition[0]] == 0)
                        winnerPlayer = "Yellow";

                    Toast.makeText(MainActivity.this, "The " + winnerPlayer + " player win the game",
                            Toast.LENGTH_LONG).show();
                    isGameActive = false;
                }
            }

            Log.d("myLog", "------------------\n");

            boolean isGameOver = true;
            for (int state : states)
                if (state == -1)
                    isGameOver = false;

            if (isGameOver) {
                Toast.makeText(MainActivity.this, "Game over. Noone win the game", Toast.LENGTH_LONG).show();
                isGameActive = false;
            }
        }


    }

    public int getPosition(ImageView counter) {
        int pos = 0;
        switch (counter.getId()) {
            case R.id.firstRowFistColumn:
                pos = 0;
                break;
            case R.id.firstRowSecondColumn:
                pos = 1;
                break;
            case R.id.firstRowThirdColumn:
                pos = 2;
                break;
            case R.id.secondRowFistColumn:
                pos = 3;
                break;
            case R.id.secondRowSecondColumn:
                pos = 4;
                break;
            case R.id.secondRowThirdColumn:
                pos = 5;
                break;
            case R.id.thirdRowFistColumn:
                pos = 6;
                break;
            case R.id.thirdRowSecondColumn:
                pos = 7;
                break;
            case R.id.thirdRowThirdColumn:
                pos = 8;
                break;
        }
        return pos;
    }
}
