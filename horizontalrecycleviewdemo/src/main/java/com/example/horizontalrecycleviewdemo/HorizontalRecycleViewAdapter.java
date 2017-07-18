package com.example.horizontalrecycleviewdemo;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by Administrator on 2017/7/18.
 */

public class HorizontalRecycleViewAdapter extends RecyclerView.Adapter {
    private static final String TAG = "HorizontalRecycleViewAdapter";
    private List<ItemBean> itemBeanList;
    private Context context;
    private OnItemClickListener onItemclickListener;

    public HorizontalRecycleViewAdapter(List<ItemBean> itemBeanList, Context context) {

        this.itemBeanList = itemBeanList;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_horizontalrecycleview, parent, false);

        HorizontalRecycleViewHolder viewHolder = new HorizontalRecycleViewHolder(view);
        viewHolder.tv = view.findViewById(R.id.tv_name);
        viewHolder.iv = view.findViewById(R.id.iv_head);
        return viewHolder;
    }
     interface OnItemClickListener {
        void onItemClick(View v, int layoutPosition);
    }
    public void setOnItemclickListener (OnItemClickListener onItemclickListener){

        this.onItemclickListener = onItemclickListener;
    }
    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        final HorizontalRecycleViewHolder viewHolder = (HorizontalRecycleViewHolder) holder;
        viewHolder.iv.setBackgroundResource(R.mipmap.ic_launcher);
        viewHolder.tv.setText(itemBeanList.get(position).name);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               onItemclickListener .onItemClick(v,viewHolder.getLayoutPosition());
            }
        });


    }

    @Override
    public int getItemCount() {
        return itemBeanList.size();
    }

    public class HorizontalRecycleViewHolder extends RecyclerView.ViewHolder {
        public ImageView iv;
        public TextView tv;

        public HorizontalRecycleViewHolder(View itemView) {
            super(itemView);
        }
    }

}
