package com.baidu.xuwanran.testservice;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by xuwanran on 2018/6/11.
 */

public class MyService extends Service {
    public static final int HANDLER_REQUEST = 0;
    public static final String TAG = "xwr";

    public class MyHandler extends Handler{
        @Override
        public void handleMessage(Message msg) {
            Log.d("xwr","GetMessage");
            switch (msg.what){
                case HANDLER_REQUEST:
                    Log.d(TAG,"message get sucessful");
                    break;
                default:
            }
        }
    }

    final Messenger mMessenger = new Messenger(new MyHandler());

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mMessenger.getBinder();
    }

}
