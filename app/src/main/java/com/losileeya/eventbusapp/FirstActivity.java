package com.losileeya.eventbusapp;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.losileeya.eventbusapp.event.EventBusEvents;

import org.greenrobot.eventbus.EventBus;


public class FirstActivity extends AppCompatActivity {
    private Button btn_showDownLoad;


    private static Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg)
        {
            EventBus.getDefault().post(new EventBusEvents.FirstEvent("我是从网络下载的文本"));
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        btn_showDownLoad = (Button) findViewById(R.id.btn_toast);
        btn_showDownLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                EventBus.getDefault().post(new EventBusEvents.FirstEvent("我是从网络下载的文本"));

//                new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//                        try {
//                            Thread.sleep(1000);
//                            mHandler.sendEmptyMessage(0);
//
//
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
//
//                    }
//                }).start();
            }
        });
    }




}
