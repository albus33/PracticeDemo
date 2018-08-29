package com.example.administrator.pulltorefreshlistviewdemo;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {
    ListView lv_listView;
    List<String> data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv_listView = (ListView) findViewById(R.id.lv_listview);

        data = new ArrayList<String>();

        MyAdapter myAdapter = new MyAdapter(this, data);
        lv_listView.setAdapter(myAdapter);
        for (int i = 0; i < 10; i++) {
            data.add("数据" + i);

        }
        myAdapter.notifyDataSetChanged();
    }
}
