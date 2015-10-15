package com.ufida.john.myrecycleview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by john on 2015/10/15.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private String[]  mDataset;

    public  static  class  ViewHolder extends  RecyclerView.ViewHolder{
        public TextView mTextView;

        public ViewHolder(View mTextView) {
            super(mTextView);
            this.mTextView = (TextView) mTextView;
        }
    }

    public  MyAdapter(String[] myDataset){
        mDataset =  myDataset;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(android.R.layout.simple_list_item_1,null);
        ViewHolder holder  =  new ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mTextView.setText(mDataset[position]);
    }

    @Override
    public int getItemCount() {
        return mDataset.length;
    }
}
