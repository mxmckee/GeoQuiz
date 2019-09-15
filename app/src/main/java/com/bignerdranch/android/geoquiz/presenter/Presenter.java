package com.bignerdranch.android.geoquiz.presenter;

import com.bignerdranch.android.geoquiz.model.Quiz;
import com.bignerdranch.android.geoquiz.view.IView;

public class Presenter implements IPresenter {

    private Quiz model;
    private IView view;

    public Presenter(IView view) {
        this.model = new Quiz();
        this.view = view;
    }

    @Override
    public boolean checkAnswer(boolean userPressedTrue) {
        boolean answerIsTrue = this.model.getCurrentQuestion().isAnswerTrue();
        return answerIsTrue;
    }

    @Override
    public void goToNextQuestion() {
        this.model.nextQuestion();
    }

    @Override
    public void updateQuestionView() {
        int question = this.model.getCurrentQuestion().getTextResId();
        view.updateWithNextQuestion(question);
    }
}