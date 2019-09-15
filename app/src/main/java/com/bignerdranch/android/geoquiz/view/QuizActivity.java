package com.bignerdranch.android.geoquiz.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bignerdranch.android.geoquiz.presenter.IPresenter;
import com.bignerdranch.android.geoquiz.presenter.Presenter;
import com.bignerdranch.android.geoquiz.R;

public class QuizActivity extends AppCompatActivity implements IView{

    private Button mTrueButton;
    private Button mFalseButton;
    private Button mNextButton;
    private TextView mQuestionTextView;
    private TextView mResultTextView;

    private IPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        mPresenter = new Presenter(this);

        this.mQuestionTextView = findViewById(R.id.question_text_view);
        this.mResultTextView = findViewById(R.id.result_text_view);

        this.mTrueButton = findViewById(R.id.true_button);
        this.mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(true);
            }
        });

        this.mFalseButton = findViewById(R.id.false_button);
        this.mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(false);
            }
        });

        this.mNextButton = findViewById(R.id.next_button);
        this.mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToNextQuestion();
            }
        });

        updateQuestionView();
        reset();
    }

    private void goToNextQuestion() {
        mPresenter.goToNextQuestion();
        updateQuestionView();
        reset();
    }

    private void checkAnswer(boolean userPressedTrue) {
        int answerColor;
        boolean answerIsTrue = mPresenter.checkAnswer(userPressedTrue);
        int messageResId;

        if (userPressedTrue == answerIsTrue) {
            messageResId = R.string.correct_toast;
            answerColor = getResources().getColor(R.color.green);
        } else {
            messageResId = R.string.incorrect_toast;
            answerColor = getResources().getColor(R.color.red);
        }

        mResultTextView.setText(messageResId);
        mResultTextView.setTextColor(answerColor);
        onQuestionAnswered();
    }

    private void updateQuestionView() {
        mPresenter.updateQuestionView();
    }

    private void reset() {
        mResultTextView.setText("");
        mTrueButton.setEnabled(true);
        mFalseButton.setEnabled(true);
        mNextButton.setEnabled(false);
    }

    private void onQuestionAnswered() {
        mTrueButton.setEnabled(false);
        mFalseButton.setEnabled(false);
        mNextButton.setEnabled(true);
    }

    @Override
    public void updateWithNextQuestion(int question) {
        this.mQuestionTextView.setText(question);
    }

}