package com.tanyuan.app.findyuan;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.tanyuan.app.R;
import com.tanyuan.app.base.BaseHolder;
import com.tanyuan.app.imageloader.ImageLoaderUtil;
import com.tanyuan.app.response.CircleModel;
import com.tanyuan.app.utils.CommonUtil;
import com.tanyuan.app.utils.DateUtil;
import com.view.scalpel.widget.roundedimageview.RoundedImageView;

/**
 * Created by liuxingxing on 2017/12/14.
 */

public class CircleHolder extends BaseHolder {
    Context mContext;

    public CircleHolder(Context context, View itemView) {
        super(itemView);
        this.mContext = context;
    }

    @SuppressLint("SetTextI18n")
    public void setView(CircleModel model) {
        RoundedImageView headView = itemView.findViewById(R.id.ballFriendsImg);
        ImageView circleImg = itemView.findViewById(R.id.circleImg)
                , msgImg = itemView.findViewById(R.id.msgImg)
                , zanImg = itemView.findViewById(R.id.zanImg)
                , moneyImg = itemView.findViewById(R.id.moneyImg);
        TextView msgCountTv = itemView.findViewById(R.id.msgCountTv)
                , zanCountTv = itemView.findViewById(R.id.zanCountTv)
                , moneyCountTv = itemView.findViewById(R.id.moneyCountTv)
                , ballFriendsNameTv = itemView.findViewById(R.id.ballFriendsNameTv)
                , signTv = itemView.findViewById(R.id.ballFriendsSignTv);

        if (TextUtils.isEmpty(model.getContent())) {
            signTv.setVisibility(View.GONE);
        } else {
            signTv.setVisibility(View.VISIBLE);
            signTv.setText(model.getContent());
        }

        int msgCount = CommonUtil.getInt(model.getCommentCount());
        int likeCount = CommonUtil.getInt(model.getLikeCount());
        int awardCount = CommonUtil.getInt(model.getAwardCount());
        msgCountTv.setText((msgCount > 0 ? msgCount : "") + "");
        zanCountTv.setText((likeCount > 0 ? likeCount : "") + "");
        moneyCountTv.setText((awardCount > 0 ? awardCount : "") + "");
        ballFriendsNameTv.setText(model.getNickname());
        ImageLoaderUtil.getInstance(mContext).loadImage(model.getSmallAvatar(), headView, true);
        ImageLoaderUtil.getInstance(mContext).loadImage(model.getImgUrls().get(0), circleImg, true);

        ballFriendsNameTv.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                if (mClickListener!=null) {
                    mClickListener.OnClick(0, getAdapterPosition());
                }
                return false;
            }
        });

    }

}
