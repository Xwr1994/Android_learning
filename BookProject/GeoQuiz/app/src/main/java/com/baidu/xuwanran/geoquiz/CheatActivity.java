package com.baidu.xuwanran.geoquiz;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class CheatActivity extends AppCompatActivity {
    private static final String EXTRA_ANSWER_IS_TRUE = "com.baidu.xuwanran.geoquiz.answer_is_true";
    private static final String EXTRA_ANSWER_SHOWN = "com.baidu.xuwanran.geoquiz.answer_shown";
    private static final String EXTRA_CHEAT_REMAIN = "com.baidu.xuwanran.geoquiz.cheat_remain";
    private static final String KEY_ANSWER_SHOWN = "Answer_shown";
    private static final String KEY_CHEAT_REMAIN = "Cheat_remain";

    private Button mShowAnswerButton;
    private TextView mAnswerTextView;
    private TextView mVersionTextView;
    private TextView mRemainTextView;

    private boolean mAnswerIsTrue;
    private boolean mIsCheated;
    private int mVersion;
    private int mRemainNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);

        if(getIntent().getIntExtra(EXTRA_CHEAT_REMAIN,3) == -1){
            mRemainNum = 3;
        }else {
            mRemainNum = getIntent().getIntExtra(EXTRA_CHEAT_REMAIN,3);
        }

        if(savedInstanceState != null){
            mIsCheated = savedInstanceState.getBoolean(KEY_ANSWER_SHOWN);
            mRemainNum = savedInstanceState.getInt(KEY_CHEAT_REMAIN);
        }

        mAnswerIsTrue = getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE,false);

        mAnswerTextView = (TextView) findViewById(R.id.answer_text_view);
        mVersionTextView = (TextView) findViewById(R.id.version_text_view) ;

        mVersion = Build.VERSION.SDK_INT;
        mVersionTextView.setText(new StringBuilder().append("API Level ").append(mVersion));

        mRemainTextView = (TextView) findViewById(R.id.remain_text_view);
        mRemainTextView.setText(new StringBuilder().append("Remain: ").append(mRemainNum));

        mShowAnswerButton = (Button) findViewById(R.id.show_answer_button);
        mShowAnswerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mRemainNum <= 0){
                    Toast.makeText(CheatActivity.this,"You Have No Cheat Num",Toast.LENGTH_SHORT).show();
                }else {
                    mIsCheated = true;
                    if(mAnswerIsTrue){
                        mAnswerTextView.setText(R.string.true_button);
                    }else {
                        mAnswerTextView.setText(R.string.false_button);
                    }
                    mRemainNum -= 1;
                    setAnswerShownResult(mIsCheated,mRemainNum);
                    mRemainTextView.setText(new StringBuilder().append("Remain: ").append(mRemainNum));

                      //按钮动画
//                    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
//                        int cx = mShowAnswerButton.getWidth() / 2;
//                        int cy = mShowAnswerButton.getHeight() / 2;
//                        float radius = mShowAnswerButton.getWidth();
//                        Animator anim = ViewAnimationUtils.createCircularReveal(mShowAnswerButton,cx,cy,radius,0);
//                        anim.addListener(new AnimatorListenerAdapter() {
//                            @Override
//                            public void onAnimationEnd(Animator animation) {
//                                super.onAnimationEnd(animation);
//                                mShowAnswerButton.setVisibility(View.INVISIBLE);
//                            }
//                        });
//                        anim.start();
//                    }
                }
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(KEY_ANSWER_SHOWN,mIsCheated);
        outState.putInt(KEY_CHEAT_REMAIN,mRemainNum);
    }

    private void setAnswerShownResult(boolean isAnswerShown, int mRemainNum){
        Intent data = new Intent();
        data.putExtra(EXTRA_ANSWER_SHOWN,isAnswerShown);
        data.putExtra(EXTRA_CHEAT_REMAIN,mRemainNum);
        setResult(RESULT_OK,data);
    }

    public static Intent newIntent(Context packageContext, boolean answerIsTrue, int mRemainNum){
        Intent intent = new Intent(packageContext,CheatActivity.class);
        intent.putExtra(EXTRA_ANSWER_IS_TRUE,answerIsTrue);
        intent.putExtra(EXTRA_CHEAT_REMAIN,mRemainNum);
        return intent;
    }

    public static boolean wasAnswerShown(Intent result){
        return result.getBooleanExtra(EXTRA_ANSWER_SHOWN,false);
    }

    public static int getCheatRemainNum(Intent result){
        return result.getIntExtra(EXTRA_CHEAT_REMAIN,3);
    }
}
