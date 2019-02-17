package com.akhmadov.braintrainer;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class GameActivity extends AppCompatActivity {

    public static final int TIMER_VALUE = 30000;
    public static final int TIMER_COUNTDOWN_TICK = 1000;
    Boolean isTimerActive = false;
    Button playAgainBtn;
    TextView timerTV, answerTV, taskTV, firstCell, secondCell, thirdCell, fourthCell, verdictTV;
    CountDownTimer timer;
    TextView[] cellsTV;
    int summaryAnswers = 0;
    int correctAnswers = 0;
    int currentRightAnswer = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        playAgainBtn = (Button) findViewById(R.id.playAgainBtn);
        timerTV = (TextView) findViewById(R.id.timerTV);
        answerTV = (TextView) findViewById(R.id.answersTV);
        taskTV = (TextView) findViewById(R.id.taskTV);
        firstCell = (TextView) findViewById(R.id.firstCell);
        secondCell = (TextView) findViewById(R.id.secondCell);
        thirdCell = (TextView) findViewById(R.id.thirdCell);
        fourthCell = (TextView) findViewById(R.id.fourthCell);
        verdictTV = (TextView) findViewById(R.id.verdictTV);

        cellsTV = new TextView[]{firstCell, secondCell, thirdCell, fourthCell};

        initialTheGame();
    }

    public void playAgain(View view) {

        initialTheGame();

    }

    private void initialTheGame() {
        generateTask();

        playAgainBtn.setVisibility(View.INVISIBLE);
        verdictTV.setVisibility(View.INVISIBLE);
        isTimerActive = true;
        timer = new CountDownTimer(TIMER_VALUE + 100, TIMER_COUNTDOWN_TICK) {
            @Override
            public void onTick(long millisUntilFinished) {
                timerTV.setText(Long.toString(millisUntilFinished / 1000) + "s");
            }

            @Override
            public void onFinish() {
                isTimerActive = false;
                playAgainBtn.setVisibility(View.VISIBLE);
                verdictTV.setText("Your score: " + answerTV.getText());
                MediaPlayer mediaPlayer = MediaPlayer.create(GameActivity.this, R.raw.airhorn);
                mediaPlayer.start();
                summaryAnswers = 0;
                correctAnswers = 0;

            }
        }.start();
    }


    public void chooseAnswer(View view) {

        MediaPlayer mediaPlayer;
        if (isTimerActive) {
            String getAnswer = ((TextView) view).getText().toString();
            if (Integer.parseInt(getAnswer) == currentRightAnswer) {
                mediaPlayer = MediaPlayer.create(GameActivity.this, R.raw.correct);
                correctAnswers++;
                summaryAnswers++;
                verdictTV.setText("Correct!");
            } else {
                mediaPlayer = MediaPlayer.create(GameActivity.this, R.raw.incorrect);
                summaryAnswers++;
                verdictTV.setText("Wrong!");
            }
            mediaPlayer.start();

            answerTV.setText(Integer.toString(correctAnswers) + "/" + Integer.toString(summaryAnswers));
            generateTask();
        }

    }

    public void generateTask() {
        Random random = new Random();
        int firstOperand = Math.abs(random.nextInt() % 20);
        int secondOperand = Math.abs(random.nextInt() % 20);


        int operation = Math.abs(random.nextInt() % 3);
        if (operation == 0) {
            currentRightAnswer = firstOperand * secondOperand;
            taskTV.setText(Integer.toString(firstOperand) + " * " + Integer.toString(secondOperand));
        } else if (operation == 1) {
            currentRightAnswer = firstOperand + secondOperand;
            taskTV.setText(Integer.toString(firstOperand) + " + " + Integer.toString(secondOperand));
        } else if (operation == 2) {
            currentRightAnswer = firstOperand - secondOperand;
            taskTV.setText(Integer.toString(firstOperand) + " - " + Integer.toString(secondOperand));
        }

        int rightAnswerInCell = Math.abs(random.nextInt() % 4);
        for (int i = 0; i < 4; i++) {
            if (i == rightAnswerInCell) {
                (cellsTV[i]).setText(Integer.toString(currentRightAnswer));
            } else {
                (cellsTV[i]).setText(Integer.toString(random.nextInt() % 400));
            }
        }
        Log.d("myLogs", "FirstOperand: " + firstOperand);
        Log.d("myLogs", "SecondOperand: " + secondOperand);
        Log.d("myLogs", "Operation: " + operation);
        Log.d("myLogs", "RightAnswerCell: " + rightAnswerInCell);
        Log.d("myLogs", "Answer: " + currentRightAnswer);

    }
}
