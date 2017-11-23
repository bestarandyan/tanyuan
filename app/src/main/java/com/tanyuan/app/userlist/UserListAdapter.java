package com.tanyuan.app.userlist;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.android.volley.toolbox.ImageLoader;
import com.tanyuan.app.R;
import com.tanyuan.app.response.BallFriendsModel;

import java.util.List;

/**
 * Created by liuxingxing on 15/10/18.
 */
public class UserListAdapter extends BaseAdapter {
    Context mContext;
    List<BallFriendsModel> mList;
    public UserListAdapter(Context context, List<BallFriendsModel> list){
        this.mContext = context;
        this.mList = list;
        setList(mList);
    }

    public void setList(List<BallFriendsModel> list){
        this.mList = list;
    }
    @Override
    public int getCount() {
        return mList==null?0:mList.size();
    }

    @Override
    public Object getItem(int i) {
        return mList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder ;
        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_ballfriends_list,null);
            holder = new ViewHolder();
            holder.mBirthdayTv = (TextView) view.findViewById(R.id.ballFriendsAge);
            holder.mUserNameTv = (TextView) view.findViewById(R.id.ballFriendsNameTv);
            holder.mUserTypeTv = (TextView) view.findViewById(R.id.ballFriendsType);
            holder.mUserSignTv = (TextView) view.findViewById(R.id.ballFriendsSignTv);
            holder.mUserDistanceTv = (TextView) view.findViewById(R.id.ballFriendsDistanceTv);
            holder.mUserHeadImg = (ImageView) view.findViewById(R.id.ballFriendsImg);
            holder.mZhuoQiuView = (ImageView) view.findViewById(R.id.iconBilliardsView);
            holder.mWangQiuView = (ImageView) view.findViewById(R.id.iconTennisView);
            holder.mYuQiuView = (ImageView) view.findViewById(R.id.icBadmintonView);
            holder.mPingPangQiuView = (ImageView) view.findViewById(R.id.iconTableTennisView);
            holder.mXingZuo = (TextView) view.findViewById(R.id.xingzuoTv);
            holder.mGenderImg = (ImageView) view.findViewById(R.id.genderView);
            holder.mGenderlayout = (LinearLayout) view.findViewById(R.id.genderLayout);
            view.setTag(holder);
        }else{
            holder = (ViewHolder) view.getTag();
        }
        BallFriendsModel model = mList.get(i);
        holder.mUserNameTv.setText(model.getNickname());
        Long  birthday = model.getBirthday();
//        if (birthday!=null){
//            holder.mBirthdayTv.setText(DateUtil.getAgeByBirthday(DateUtil.getDateToDate(birthday)) + "");
//            //星座计算
//            String xingzuo = ConstellationUtil.calculateConstellation(TimeUtils.getCalendarStrBySimpleDateFormat(birthday, TimeUtils.SIMPLEFORMATTYPESTRING7));
//            if (xingzuo != null) {
//                holder.mXingZuo.setText(xingzuo);
//                holder.mXingZuo.setVisibility(View.VISIBLE);
//            } else {
//                holder.mXingZuo.setVisibility(View.GONE);
//            }
//        }else{
//            holder.mBirthdayTv.setText("保密");
//            holder.mXingZuo.setVisibility(View.GONE);
//        }
//
//        if (model.getGender()!=null && model.getGender() == 1){//男
//            holder.mGenderlayout.setBackgroundResource(R.drawable.shape_gender1_bg);
//            holder.mGenderImg.setImageResource(R.drawable.ic_male_w);
//            holder.mXingZuo.setTextColor(mContext.getResources().getColor(R.color.app_action_bar_bg));
//        }else{
//            holder.mGenderImg.setImageResource(R.drawable.ic_female_w);
//            holder.mGenderlayout.setBackgroundResource(R.drawable.shape_gender2_bg);
//            holder.mXingZuo.setTextColor(Color.parseColor("#FF5E96"));
//        }

        if (model.getType() == 2){
            holder.mUserTypeTv.setText("球星");
            holder.mUserTypeTv.setVisibility(View.VISIBLE);
            List<Integer> list = model.getBallType();
            if (list != null && list.size() > 0) {
                holder.mZhuoQiuView.setVisibility(View.GONE);
                holder.mYuQiuView.setVisibility(View.GONE);
                holder.mWangQiuView.setVisibility(View.GONE);
                holder.mPingPangQiuView.setVisibility(View.GONE);
                for (int a : list) {
                    if (a == 1) {
                       holder.mZhuoQiuView.setVisibility(View.VISIBLE);
                    } else if (a == 2) {
                        holder.mYuQiuView.setVisibility(View.VISIBLE);
                    } else if (a == 3) {
                        holder.mWangQiuView.setVisibility(View.VISIBLE);
                    } else if (a == 4) {
                        holder.mPingPangQiuView.setVisibility(View.VISIBLE);
                    }
                }
            }
        }else{
            holder.mUserTypeTv.setVisibility(View.GONE);
        }
//
//        holder.mUserSignTv.setText(model.getMood());
//        Long lastOnline = model.getLastOnLineTime();
//        String lastStr = "";
//        if (lastOnline !=null ){
//            lastOnline = DateUtil.caculateMinute(DateUtil.getDateToDate(lastOnline));
//            lastStr = UnitUtils.getTimeStr(lastOnline) +"前";
//        }
//        Double lat = model.getLat().doubleValue();
//        Double lon = model.getLon().doubleValue();
//        if (lat == null || lat == 0 || lon == null || lon == 0){
//            holder.mUserDistanceTv.setText(lastStr);
//        }else {
//            if (model.getDistance()!=null) {
//                float distance = model.getDistance();
//                if (distance <= 100) {
//                    holder.mUserDistanceTv.setText("0.1km|" + lastStr);
//                } else{
//                    holder.mUserDistanceTv.setText(CommonUtils.getFloat2(distance / 1000l) + "km|" + lastStr);
//                }
//            }else{
//                holder.mUserDistanceTv.setText(lastStr);
//            }
//        }
//        String smallHeadImg = model.getSmallAvatar();
//        if (smallHeadImg!=null && smallHeadImg.trim().length() > 0){
//            ImageLoader.getInstance().displayImage(model.getSmallAvatar(), holder.mUserHeadImg, ImageLoaderConfig.ballFriend, ImageLoaderConfig.ballfriendImgListener, ImageLoaderConfig.imgProgressListener);
//        }else{
//            ImageLoader.getInstance().displayImage(model.getAvatar(), holder.mUserHeadImg, ImageLoaderConfig.ballFriend, ImageLoaderConfig.ballfriendImgListener, ImageLoaderConfig.imgProgressListener);
//        }
        return view;
    }

    class ViewHolder{
        ImageView mUserHeadImg,mZhuoQiuView,mWangQiuView,mYuQiuView,mPingPangQiuView,mGenderImg;
        TextView mUserNameTv,mBirthdayTv,mUserTypeTv,mUserSignTv,mUserDistanceTv,mXingZuo;
        LinearLayout mGenderlayout;

    }
}
