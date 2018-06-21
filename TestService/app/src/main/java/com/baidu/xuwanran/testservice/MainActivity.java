package com.baidu.xuwanran.testservice;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button button1,button2,button3,button4,button5;
    Intent intent;
    Messenger mMessenger;

    public ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Toast.makeText(MainActivity.this,"onServiceConnected",Toast.LENGTH_SHORT).show();
            mMessenger = new Messenger(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Toast.makeText(MainActivity.this,"onServiceDisconnected",Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);
        button5 = (Button) findViewById(R.id.button5);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button1:
                intent = new Intent(MainActivity.this,MyService.class);
                startService(intent);
                break;
            case R.id.button2:
                intent = new Intent(MainActivity.this,MyService.class);
                stopService(intent);
                break;
            case R.id.button3:
                intent = new Intent(MainActivity.this,MyService.class);
                bindService(intent,connection,BIND_AUTO_CREATE);
                break;
            case R.id.button4:
                intent = new Intent(MainActivity.this,MyService.class);
                unbindService(connection);
                break;
            case R.id.button5:
                Message ms = Message.obtain(null,MyService.HANDLER_REQUEST,0,0);
                try{
                    Log.i("xwr","SendMessage");
                    mMessenger.send(ms);
                }catch (RemoteException e){
                    Log.d("xwr","Exception");
                    e.printStackTrace();
                }
                break;
        }
    }
}
