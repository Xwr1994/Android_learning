package com.baidu.xuwanran.criminalintent;

import android.text.format.DateFormat;

import java.util.Date;
import java.util.UUID;

/**
 * Created by xuwanran on 2018/8/6.
 */

public class Crime {
    private UUID mId;
    private String mTitle;
    private Date mDate;
    private boolean mSolved;
    private boolean mRequiresPolice;

    public String getFormatDate() {
        return mFormatDate;
    }

    private String mFormatDate;

    public Crime(){
        mId = UUID.randomUUID();  //Android中的工具类，用于产生随机的Id值
        mDate = new Date();
        //E:Day_of_week   M:Month  d:Day  y:Year
        mFormatDate = DateFormat.format("EE, MM, dd, yyyy",mDate).toString();  //不清楚为什么会输出中文
    }

    public UUID getmId() {
        return mId;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public Date getmDate() {
        return mDate;
    }

    public void setmDate(Date mDate) {
        this.mDate = mDate;
    }

    public boolean isSolved() {
        return mSolved;
    }

    public void setSolved(boolean Solved) {
        this.mSolved = Solved;
    }

    public boolean isRequiresPolice() {
        return mRequiresPolice;
    }

    public void setRequiresPolice(boolean mRequiresPolice) {
        this.mRequiresPolice = mRequiresPolice;
    }

}
