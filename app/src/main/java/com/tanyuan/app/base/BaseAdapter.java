package com.tanyuan.app.base;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.tanyuan.app.interfaces.AdapterClickListener;

/**
 * Created by liuxingxing on 2017/12/20.
 */

public class BaseAdapter extends RecyclerView.Adapter {

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public AdapterClickListener mClickListener;
    public void setClickListener(AdapterClickListener clickListener){
        this.mClickListener = clickListener;
    }
}
