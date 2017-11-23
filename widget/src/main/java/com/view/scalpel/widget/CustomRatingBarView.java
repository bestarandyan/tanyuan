package com.view.scalpel.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * Created by liuxingxing on 15/10/31.
 */
public class CustomRatingBarView extends LinearLayout {
    public CustomRatingBarView(Context context) {
        super(context);
    }

    public CustomRatingBarView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomRatingBarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        addRatingBar(context, attrs);
    }

    private void addRatingBar(Context context , AttributeSet attrs){
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.myRatingBar);
        int  orientation = typedArray.getInt(R.styleable.myRatingBar_orientation, 0);
        float star = Float.parseFloat(typedArray.getString(R.styleable.myRatingBar_star));
        int all_select_star = typedArray.getInt(R.styleable.myRatingBar_all_select_star, 0);
        int half_select_star = typedArray.getInt(R.styleable.myRatingBar_half_select_star, 0);
        int not_select_star = typedArray.getInt(R.styleable.myRatingBar_half_select_star, 0);
        int start_int = (int) star;

        this.setOrientation(orientation == 1 ? LinearLayout.HORIZONTAL: LinearLayout.VERTICAL);
        for (int i=0;i<start_int;i++){
            ImageView imageView = new ImageView(context);
            imageView.setImageResource(all_select_star);
            addView(imageView);
        }

        if (star > (float)start_int){
            ImageView imageView = new ImageView(context);
            imageView.setImageResource(half_select_star);
            addView(imageView);
        }

        if (getChildCount() < 5){
            for (int j = getChildCount();j<5;j++){
                ImageView imageView = new ImageView(context);
                imageView.setImageResource(not_select_star);
                addView(imageView);
            }
        }
    }

    public void addRatingBar(Context context,int orientation,float star,int resourceId1,int resourceId2,int resourceId3){
        if (star > 5f){
            star = 5f;
        }
        if (getChildCount()>=5){
            return;
        }
        int start_int = (int) star;
        this.setOrientation(orientation);
        for (int i=0;i<start_int;i++){
            ImageView imageView = new ImageView(context);
            imageView.setImageResource(resourceId1);
            LinearLayout.LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.rightMargin = 10;
            imageView.setLayoutParams(params);
            addView(imageView);
        }

        if (star > (float)start_int){
            ImageView imageView = new ImageView(context);
            imageView.setImageResource(resourceId2);
            LinearLayout.LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.rightMargin = 10;
            imageView.setLayoutParams(params);
            addView(imageView);
        }

        if (getChildCount() < 5){
            for (int j = getChildCount();j<5;j++){
                ImageView imageView = new ImageView(context);
                imageView.setImageResource(resourceId3);
                LinearLayout.LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                params.rightMargin = 10;
                imageView.setLayoutParams(params);
                addView(imageView);
            }
        }
    }

}
