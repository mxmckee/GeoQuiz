package com.bignerdranch.android.geoquiz.presenter;

public interface IPresenter {
    boolean checkAnswer(boolean userPressedTrue);
    void goToNextQuestion();
    void updateQuestionView();
}