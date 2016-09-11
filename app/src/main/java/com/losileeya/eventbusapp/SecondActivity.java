package com.losileeya.eventbusapp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.losileeya.eventbusapp.event.EventBusEvents;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;



public class SecondActivity extends AppCompatActivity {
    private ListView listView;
    private Button btn_addData;
    private MyAdapter adapter;
    private List<String> data = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        setContentView(R.layout.activity_second);
        listView = (ListView) findViewById(R.id.list_second);
        btn_addData = (Button) findViewById(R.id.btn_addData);
        btn_addData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //向SecondActvity 向 DetailActivity接收数据
                Intent intent = new Intent(SecondActivity.this, DetailActivity.class);
                startActivity(intent);
            }
        });
        adapter = new MyAdapter();
        listView.setAdapter(adapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    //向SecondActvity 向 DetailActivity接收数据
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageMain(EventBusEvents.ListViewEvent listViewEvent){
        data.add(listViewEvent.getValue());
        adapter.notifyDataSetChanged();
    }

    class MyAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public Object getItem(int position) {
            return data.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView textView = new TextView(SecondActivity.this);
            textView.setText(data.get(position));
            textView.setTextSize(17);
            textView.setPadding(10,10,10,10);
            textView.setTextColor(Color.parseColor("#ff4500"));
            textView.setGravity(Gravity.CENTER_HORIZONTAL);
            return textView;
        }
    }
}
