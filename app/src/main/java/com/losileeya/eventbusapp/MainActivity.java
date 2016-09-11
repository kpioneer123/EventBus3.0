package com.losileeya.eventbusapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.losileeya.eventbusapp.event.EventBusEvents;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btn_first, btn_second, btn_third, btn_sticky;
    private TextView tv_toast;
    private TextView tv_default, tv_main, tv_background, tv_asy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EventBus.getDefault().register(this);

        btn_first = (Button) findViewById(R.id.btn_first);
        btn_second = (Button) findViewById(R.id.btn_second);
        btn_third = (Button) findViewById(R.id.btn_third);
        btn_sticky = (Button) findViewById(R.id.btn_sticky);
        tv_toast = (TextView) findViewById(R.id.tv_toast);
        tv_default = (TextView) findViewById(R.id.tv_default);
        tv_main = (TextView) findViewById(R.id.tv_main);
        tv_background = (TextView) findViewById(R.id.tv_background);
        tv_asy = (TextView) findViewById(R.id.tv_asy);
        btn_first.setOnClickListener(this);
        btn_second.setOnClickListener(this);
        btn_third.setOnClickListener(this);
        btn_sticky.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.btn_first:
                intent = new Intent(MainActivity.this, FirstActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_second:
                intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_third:
                intent = new Intent(MainActivity.this, ThirdActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_sticky:
                intent = new Intent(MainActivity.this, StickyActivity.class);
                startActivity(intent);
                break;
        }
    }


    @Subscribe(threadMode = ThreadMode.MAIN, priority = 1)
    public void onMessageMain(EventBusEvents.FirstEvent firstEvent) {
        tv_toast.setText(firstEvent.getValue());
        Toast.makeText(this, firstEvent.getValue(), Toast.LENGTH_SHORT).show();
        Log.e("zy", "onEventMainThread-->" + "priority = 1," + Thread.currentThread().getId());
        tv_main.setText(Thread.currentThread().getName() + "---->" + Thread.currentThread().getId());
    }


    /**
     * 使用onEvent来接收事件，那么接收事件和分发事件在一个线程中执行
     * param event
     */
    @Subscribe(threadMode = ThreadMode.POSTING, priority = 2)
    public void onPost(EventBusEvents.FirstEvent firstEvent) {
        Log.e("zy", "onEventPost-->" + "priority = 2," + Thread.currentThread().getId());
        tv_default.setText(Thread.currentThread().getName() + "---->" + Thread.currentThread().getId());
    }


    /**
     * 使用onEventBackgroundThread来接收事件，如果分发事件在子线程运行，那么接收事件直接在同样线程
     * 运行，如果分发事件在UI线程，那么会启动一个子线程运行接收事件
     * param event
     */
    @Subscribe(threadMode = ThreadMode.BACKGROUND, priority = 3)
    public void onBackgroundThread(EventBusEvents.FirstEvent firstEvent) {
        Log.e("zy", "onEventBackgroundThread-->" + "priority = 3," + Thread.currentThread().getId());
        tv_background.setText(Thread.currentThread().getName() + "---->" + Thread.currentThread().getId());
    }


    /**
     * 使用onEventAsync接收事件，无论分发事件在（UI或者子线程）哪个线程执行，接收都会在另外一个子线程执行
     * param event
     */
    @Subscribe(threadMode = ThreadMode.ASYNC, priority = 4)
    public void onAsync(EventBusEvents.FirstEvent firstEvent) {
        Log.e("zy", "onEventAsync-->" + "priority = 4," + Thread.currentThread().getId());
        tv_asy.setText(Thread.currentThread().getName() + "---->" + Thread.currentThread().getId());
    }

    @Subscribe(threadMode = ThreadMode.ASYNC, priority = 2)
    public void onAsync1(EventBusEvents.FirstEvent firstEvent) {
        Log.e("zy", "onEventAsync1-->" + "priority = 2," + Thread.currentThread().getId());
        tv_asy.setText(Thread.currentThread().getName() + "---->" + Thread.currentThread().getId());
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
