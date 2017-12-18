package com.tanyuan.app.findyuan;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.tanyuan.app.R;
import com.tanyuan.app.response.CircleModel;
import com.tanyuan.app.utils.CommonUtil;
import com.tanyuan.app.utils.DateUtil;
import com.view.scalpel.widget.roundedimageview.RoundedImageView;

/**
 * Created by liuxingxing on 2017/12/14.
 */

public class CircleHolder extends RecyclerView.ViewHolder {
    public CircleHolder(View itemView) {
        super(itemView);
    }

    public void setView(CircleModel model){
        RoundedImageView headView = itemView.findViewById(R.id.ballFriendsImg);
        ImageView cirlceImg = itemView.findViewById(R.id.circleImg)
                ,msgImg = itemView.findViewById(R.id.msgImg)
                ,zanImg = itemView.findViewById(R.id.zanImg)
                ,moneyImg = itemView.findViewById(R.id.moneyImg);
        TextView msgCountTv = itemView.findViewById(R.id.msgCountTv)
                ,zanCountTv = itemView.findViewById(R.id.zanCountTv)
                ,moneyCountTv = itemView.findViewById(R.id.moneyCountTv)
                ,signTv = itemView.findViewById(R.id.ballFriendsSignTv)
                ,nameTv = itemView.findViewById(R.id.ballFriendsNameTv)
                ,ageTv = itemView.findViewById(R.id.ballFriendsAge)
                ,genderTv = itemView.findViewById(R.id.ballFriendsGender);

        nameTv.setText(model.getNickname());
        Long  birthday = model.getBirthday();
        if (birthday!=null){
            ageTv.setText(DateUtil.getAgeByBirthday(DateUtil.getDateToDate(birthday)) + "");
        }else{
            ageTv.setText("保密");
        }

        if (model.getGender()!=null && model.getGender() == 1){//男
            genderTv.setText("男");
        }else{
            genderTv.setText("女");
        }

        signTv.setText(model.getContent());
        msgCountTv.setText(CommonUtil.getInt(model.getCommentCount())+"");
        zanCountTv.setText(CommonUtil.getInt(model.getLikeCount())+"");
        moneyCountTv.setText(CommonUtil.getInt(model.getAwardCount())+"");

    }

}
