package com.example.zadanie1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import lombok.SneakyThrows;

public class MainActivity extends AppCompatActivity {
    private static final String KEY_CURRENT_INDEX = "currentIndex";
    private static final String QUIZ_TAG = "MainActivity";
    private Button trueButton;
    private Button falseButton;
    private Button nextButton;
    private TextView questionTextView;

    private Question[] questions = new Question[] {
            new Question(R.string.q_1, true),
            new Question(R.string.q_2, false),
            new Question(R.string.q_3, true),
            new Question(R.string.q_4, true),
            new Question(R.string.q_5, false),
    };
    private int currentIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(QUIZ_TAG, "Wywołana została metoda cyklu życia: onCreate | currentIndex: " + currentIndex);
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null) {
            currentIndex = savedInstanceState.getInt(KEY_CURRENT_INDEX);
        }
        Log.d(QUIZ_TAG, "Wywołana została metoda cyklu życia: onCreate2 | currentIndex: " + currentIndex);


        trueButton = findViewById(R.id.true_button);
        falseButton = findViewById(R.id.false_button);
        nextButton = findViewById(R.id.next_button);
        questionTextView = findViewById(R.id.question_text_view);



        trueButton.setOnClickListener(new View.OnClickListener() {
            @SneakyThrows
            @Override
            public void onClick(View v) {
                checkAnswerCorrectness(true);
            }
        });

        falseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswerCorrectness(false);
            }
        });


        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentIndex = (currentIndex + 1)%questions.length;
                Log.d(QUIZ_TAG, "Wywołana została metoda nextButton | currentIndexNext: " + currentIndex);

                setNextQuestion();
            }
        });
        questionTextView.setText(questions[currentIndex].getQuestionId());
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(QUIZ_TAG, "Wywołana została metoda cyklu życia: onStart");

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(QUIZ_TAG, "Wywołana została metoda cyklu życia: onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(QUIZ_TAG, "Wywołana została metoda cyklu życia: onPause | currentIndex: " + currentIndex);

    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(QUIZ_TAG, "Wywołana została metoda cyklu życia: onStop | currentIndex: " + currentIndex);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(QUIZ_TAG, "Wywołana została metoda cyklu życia: onDestroy | currentIndex: "+ currentIndex);

    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(QUIZ_TAG, "Wywołana została metoda: onSaveInstanceState | " + "currentIndexNext: " + currentIndex);
        outState.putInt(KEY_CURRENT_INDEX, currentIndex);

    }


    private void checkAnswerCorrectness(boolean userAnswer) {
        boolean correctAnswer = questions[currentIndex].isTrueAnswer();
        int resultMessageId = 0;
        if (userAnswer == correctAnswer) {
            resultMessageId = R.string.correct_answer;
        } else {
            resultMessageId = R.string.incorrect_answer;
        }
        Toast.makeText(this, resultMessageId, Toast.LENGTH_SHORT).show();
    };

    private void setNextQuestion() {
        questionTextView.setText(questions[currentIndex].getQuestionId());
    }

}
