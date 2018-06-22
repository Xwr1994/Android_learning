package com.baidu.xuwanran.geoquiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

public class QuizActivity extends AppCompatActivity {
    private static final String TAG = "QuizActivity";
    private static final String KEY_INDEX = "index";

    private Button mTrueButton;
    private Button mFalseButton;
    private ImageButton mNextButton;
    private ImageButton mPrevButton;
    private TextView mQuestionTextView;

    private Question[] mQuestionBank = new Question[]{
            new Question(R.string.question_australia,true),
            new Question(R.string.question_oceans,true),
            new Question(R.string.question_mideast,false),
            new Question(R.string.question_africa,false),
            new Question(R.string.question_americas,true),
            new Question(R.string.question_asia,true)
    };

    private int mCurrentIndex = 0;
    private int numOfAnswer = 0;
    private int numOfRight = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        Log.d(TAG,"OnCreate Called");

        if(savedInstanceState != null){
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX);
        }

        mQuestionTextView = (TextView) findViewById(R.id.question_text_view);
        mQuestionTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
                updateQuestion();
            }
        });

        mTrueButton = (Button) findViewById(R.id.true_button);
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //设定Toast显示在其他位置的方法，注意不能直接new一个Toast而是要使用下面方式
                //Toast toast = Toast.makeText(QuizActivity.this,R.string.correct_toast,Toast.LENGTH_SHORT);
                //toast.setGravity(Gravity.TOP,0,0);
                //toast.show();
                checkAnswer(true);
                mQuestionBank[mCurrentIndex].setHasAnswer(true);
                setButtonClickable();
            }
        });

        mFalseButton = (Button) findViewById(R.id.false_button);
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(false);
                mQuestionBank[mCurrentIndex].setHasAnswer(true);
                setButtonClickable();
            }
        });

        mNextButton = (ImageButton) findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
                updateQuestion();
            }
        });

        mPrevButton = (ImageButton) findViewById(R.id.prev_button);
        mPrevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mCurrentIndex <= 0){
                    mCurrentIndex = mQuestionBank.length;
                    mCurrentIndex = (mCurrentIndex -1) % mQuestionBank.length;
                }else {
                    mCurrentIndex = (mCurrentIndex - 1) % mQuestionBank.length;
                }
                updateQuestion();
            }
        });

        updateQuestion();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG,"OnStart Called");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG,"OnPause Called");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG,"OnResume Called");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG,"OnStop Called");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"OnDestory Called");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(TAG,"OnSaveInstanceState Called");
        outState.putInt(KEY_INDEX,mCurrentIndex);
    }

    private void updateQuestion(){
        int question = mQuestionBank[mCurrentIndex].getmTextResId();
        mQuestionTextView.setText(question);
        setButtonClickable();
    }

    private void checkAnswer(boolean userPressedTrue){
        boolean answerIsTrue = mQuestionBank[mCurrentIndex].ismAnswerTrue();

        int messageResId = 0;

        if(userPressedTrue == answerIsTrue){
            messageResId = R.string.correct_toast;
            numOfRight = numOfRight + 1;
            numOfAnswer = numOfAnswer + 1;
        }else {
            messageResId = R.string.incorrect_toast;
            numOfAnswer = numOfAnswer + 1;
        }

        Toast.makeText(QuizActivity.this,messageResId,Toast.LENGTH_SHORT).show();

        if(numOfAnswer == mQuestionBank.length){
            double rightratio = (double) numOfRight/(double) numOfAnswer;
            NumberFormat nt = NumberFormat.getPercentInstance();
            nt.setMinimumFractionDigits(2);
            Toast.makeText(QuizActivity.this,"Your Answer Correct Ratio is" + nt.format(rightratio),Toast.LENGTH_SHORT).show();
        }
    }

    private void setButtonClickable(){
        if(mQuestionBank[mCurrentIndex].isHasAnswer() == true){
            mTrueButton.setEnabled(false);
            mFalseButton.setEnabled(false);
        }else {
            mTrueButton.setEnabled(true);
            mFalseButton.setEnabled(true);
        }
    }
}
