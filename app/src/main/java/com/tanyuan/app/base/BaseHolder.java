package com.tanyuan.app.base;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.tanyuan.app.interfaces.AdapterClickListener;

/**
 * Created by liuxingxing on 2017/12/20.
 */

public class BaseHolder extends RecyclerView.ViewHolder{
    public AdapterClickListener mClickListener;
    public BaseHolder(View itemView) {
        super(itemView);
    }

    public void setClickListener(AdapterClickListener clickListener){
        this.mClickListener = clickListener;
    }


}
