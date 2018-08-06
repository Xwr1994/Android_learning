package com.baidu.xuwanran.geoquiz;

public class Question {
    private int mTextResId;
    private boolean mAnswerTrue;
    private boolean mhasAnswer;

    public Question(int textResId, boolean answerTrue){
        this.mTextResId = textResId;
        this.mAnswerTrue = answerTrue;
        this.mhasAnswer = false;
    }

    public int getmTextResId() {
        return mTextResId;
    }

    public void setmTextResId(int mTextResId) {
        this.mTextResId = mTextResId;
    }

    public boolean ismAnswerTrue() {
        return mAnswerTrue;
    }

    public void setmAnswerTrue(boolean mAnswerTrue) {
        this.mAnswerTrue = mAnswerTrue;
    }

    public boolean isHasAnswer() {
        return mhasAnswer;
    }

    public void setHasAnswer(boolean hasAnswer) {
        this.mhasAnswer = hasAnswer;
    }
}

