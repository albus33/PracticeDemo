package com.example.horizontalrecycleviewdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    HorizontalRecycleViewAdapter horizontalRecycleViewAdapter;
    List<ItemBean> itemBeanList = new ArrayList<ItemBean>();
    HorizontalRecycleView hrv_recycleview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
        initListener();
    }

    private void initView() {

        hrv_recycleview = (HorizontalRecycleView) findViewById(R.id.hrv_recycleview);
    }

    private void initListener() {
        horizontalRecycleViewAdapter.setOnItemclickListener(new HorizontalRecycleViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                hrv_recycleview.smoothToCenter(position);
            }
        });
    }

    private void initData() {
        for (int i = 0; i < 10; i++) {
            ItemBean itemBean = new ItemBean();
            itemBean.name = "name" + i;
            itemBean.id = "" + i;
            itemBeanList.add(itemBean);
        }
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        hrv_recycleview.setLayoutManager(linearLayoutManager);
        horizontalRecycleViewAdapter = new HorizontalRecycleViewAdapter(itemBeanList, this);
        hrv_recycleview.setAdapter(horizontalRecycleViewAdapter);
    }
}
