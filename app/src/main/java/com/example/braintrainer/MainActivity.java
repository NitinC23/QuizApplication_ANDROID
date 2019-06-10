package com.example.braintrainer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button startButton,button0,button1,button2,button3,playAgainButton;
    TextView resultTextView,pointsTextView,sumTextView,timerTextView;
    RelativeLayout gameRelativeLayout;
    int locationOfCorrectAnswer;
    int score = 0;
    int numberOfQuestions = 0;
    ArrayList<Integer> answers = new ArrayList<>();
    public void playAgain(View view){
        button0.setEnabled(true);
        button1.setEnabled(true);
        button2.setEnabled(true);
        button3.setEnabled(true);
        score = 0;
        numberOfQuestions = 0;
        timerTextView.setText("60s");
        pointsTextView.setText("0/0");
        resultTextView.setText("");
        playAgainButton.setVisibility(View.INVISIBLE);
        generateQuestion();
        new CountDownTimer(60500, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timerTextView.setText(String.valueOf(millisUntilFinished/1000)+"s");
            }

            @Override
            public void onFinish() {
                button0.setEnabled(false);
                button1.setEnabled(false);
                button2.setEnabled(false);
                button3.setEnabled(false);
                playAgainButton.setVisibility(View.VISIBLE);
                timerTextView.setText("0s");
                resultTextView.setText("Your Score :"+Integer.toString(score)+"/"+Integer.toString(numberOfQuestions));
            }
        }.start();
    }
    public void generateQuestion(){
        Random random = new Random();
        int a = random.nextInt(51);
        int b = random.nextInt(51);
        sumTextView.setText(Integer.toString(a)+" + "+Integer.toString(b));
        locationOfCorrectAnswer = random.nextInt(4);
        answers.clear();
        int incorrectAnswer;
        for(int i = 0 ; i < 4 ; i++){
            if(i==locationOfCorrectAnswer){
                answers.add(a+b);
            }else{
                incorrectAnswer = random.nextInt(101);
                while(incorrectAnswer == a+b ){
                    incorrectAnswer = random.nextInt(101);
                }
                answers.add(incorrectAnswer);
            }
        }
        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));
    }
    public void chooseAnswer(View view){
        button0.setEnabled(true);
        button1.setEnabled(true);
        button2.setEnabled(true);
        button3.setEnabled(true);
        if(view.getTag().toString().equals(Integer.toString(locationOfCorrectAnswer))){
            score++;
            resultTextView.setText("CORRECT!");
        }else{
            resultTextView.setText("WRONG!");
        }
        numberOfQuestions++;
        pointsTextView.setText(Integer.toString(score)+"/"+Integer.toString(numberOfQuestions));
        generateQuestion();
    }
    public void start(View view){
        startButton.setVisibility(View.INVISIBLE);
        gameRelativeLayout.setVisibility(RelativeLayout.VISIBLE);
        playAgain(findViewById(R.id.startButton));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pointsTextView = (TextView)findViewById(R.id.pointsTextView);
        resultTextView = (TextView)findViewById(R.id.resultTextView);
        timerTextView = (TextView)findViewById(R.id.timerTextView);
        button0 = (Button)findViewById((R.id.button2));
        button1 = (Button)findViewById((R.id.button3));
        button2 = (Button)findViewById((R.id.button4));
        button3 = (Button)findViewById((R.id.button5));
        playAgainButton = (Button)findViewById(R.id.playAgainButton);
        sumTextView = (TextView)findViewById(R.id.sumTextView);
        gameRelativeLayout = (RelativeLayout)findViewById(R.id.gameRelativeLayout);
        startButton = (Button)findViewById(R.id.startButton);


    }
}
