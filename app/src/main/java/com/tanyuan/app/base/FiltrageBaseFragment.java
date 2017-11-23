package com.tanyuan.app.base;

import android.support.v4.app.Fragment;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;


/**
 * Created by bestar on 2015/5/11.
 */
public class FiltrageBaseFragment extends Fragment {
    public static final String FILTRATE_BALLFRIENDS_GENDER = "gender";//性别
    public static final String FILTRATE_BALLFRIENDS_USER_TYPE = "userType";//身份
    public static final String FILTRATE_BALLFRIENDS_ONLINE_TYPE = "onlineType";//上次在线时间

    TranslateAnimation animation = null;
    AlphaAnimation alphaAnimation = null;
    public void showFiltrateDialog(final FiltrageBaseFragment fragment,View contentView, final View alphaView, final int visibility) {
        if (visibility == View.VISIBLE) {
            animation = new TranslateAnimation(0, 0, -contentView.getHeight(), 0);
            alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
        } else {
            animation = new TranslateAnimation(0, 0, 0, -contentView.getHeight());
            alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
        }
        animation.setDuration(350);
        animation.setFillAfter(true);
        contentView.startAnimation(animation);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (visibility != View.VISIBLE) {
//                    fragment.remove();
                }else{
                    alphaView.startAnimation(alphaAnimation);
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        alphaAnimation.setDuration(200);
        alphaAnimation.setFillAfter(true);
    }

    public void showFiltrateDialog(final FiltrageBaseFragment fragment,View contentView, final View alphaView, final int visibility, final boolean isGetData) {
        if (visibility == View.VISIBLE) {
            animation = new TranslateAnimation(0, 0, -contentView.getHeight(), 0);
            alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
        } else {
            animation = new TranslateAnimation(0, 0, 0, -contentView.getHeight());
            alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
        }
        animation.setDuration(350);
        animation.setFillAfter(true);
        contentView.startAnimation(animation);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (visibility != View.VISIBLE) {
                    if (isGetData){
//                        notifySelected(null);
                    }else{
//                        fragment.remove();
                    }
                }else{
                    alphaView.startAnimation(alphaAnimation);
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        alphaAnimation.setDuration(200);
        alphaAnimation.setFillAfter(true);
    }

}
