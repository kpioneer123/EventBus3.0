package com.losileeya.eventbusapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.losileeya.eventbusapp.event.EventBusEvents;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class DetailActivity extends AppCompatActivity {
    private Button btn_detail;
    private EditText et_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        btn_detail = (Button) findViewById(R.id.btn_detail);
        et_text = (EditText) findViewById(R.id.et_text);

        EventBus.getDefault().register(this);
        btn_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EventBus.getDefault().post(new EventBusEvents.ListViewEvent(et_text.getText().toString()));
                DetailActivity.this.finish();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    //接收ThirdActivity 向 DetailActivity传过来的数据
    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void onMessageMain(EventBusEvents.ThirdEvent eventBusEvents) {
        et_text.setText(eventBusEvents.getValue());
    }

}
