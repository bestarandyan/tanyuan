package com.tanyuan.app.findyuan;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tanyuan.app.R;
import com.tanyuan.app.response.CircleModel;

import java.util.ArrayList;

/**
 * Created by liuxingxing on 2017/12/14.
 */

public class CircleAdapter extends RecyclerView.Adapter {
    ArrayList<CircleModel> models;
    Context mContext;
    CircleAdapter(Context context,ArrayList<CircleModel> models){
        mContext = context;
        this.models = models;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.item_dongtai_list,null);
        return new CircleHolder(mContext,itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder!=null && holder instanceof CircleHolder){
            CircleHolder circleHolder = (CircleHolder) holder;
            circleHolder.setView(models.get(position));
        }
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        return models.size();
    }
}
