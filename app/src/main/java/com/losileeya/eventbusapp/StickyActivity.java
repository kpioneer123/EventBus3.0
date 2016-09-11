package com.losileeya.eventbusapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created on 2016/4/14.
 */
public class StickyActivity extends AppCompatActivity {
    private int index = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sticky);

        Button bt_send = (Button) findViewById(R.id.bt_send);
        Button bt_regist = (Button) findViewById(R.id.bt_regist);
        Button bt_unregist = (Button) findViewById(R.id.bt_unregist);

        bt_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("zy", "POSTING---->" + index);
                EventBus.getDefault().postSticky("zy---->" + index++);
            }
        });
        bt_regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().register(StickyActivity.this);
            }
        });
        bt_unregist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().unregister(StickyActivity.this);
            }
        });


    }


    @Subscribe(threadMode = ThreadMode.POSTING,sticky = true)
    public void onPostThread(String msg) {
        Log.i("zy", "onEventPostThread---->" + msg);
    }

    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void onMainThread(String msg) {
        Log.i("zy", "onEventMainThread---->" + msg);
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND,sticky = true)
    public void onBackgroundThread(String msg) {
        Log.i("zy", "onEventBackgroundThread---->" + msg);
    }

    @Subscribe(threadMode = ThreadMode.ASYNC,sticky = true)
    public void onAsyncThread(String msg) {
        Log.i("zy", "onEventAsyncThread---->" +msg);
    }
}
