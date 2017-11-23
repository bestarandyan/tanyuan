package com.view.scalpel.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CompoundButton;

/**
 * Created by HKC-pc on 2015/4/2.
 */
public class QiuSimpleSwitchView extends CompoundButton {

    private static final int SWIPE_MIN_DISTANCE = 8;
    private static final int SWIPE_MAX_OFF_PATH = 240;
    private static final int SWIPE_THRESHOLD_VELOCITY = 100;
    private GestureDetector gestureDetector;
    private OnTouchListener gestureListener;

    private Context context;

    public QiuSimpleSwitchView(Context context) {
        this(context, null);
        this.context = context;
    }

    public QiuSimpleSwitchView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        setChecked(isChecked());

        setEnabled(true);
        setClickable(true);
        setFocusable(true);

        Drawable drawable = getResources().getDrawable(R.drawable.simple_switch_selector);
        if (drawable != null) {
            drawable.setBounds(0, 0, getPixelFromDip(44), getPixelFromDip(24));
            setCompoundDrawables(drawable, null, null, null);

        }

        // Gesture detection
        gestureDetector = new GestureDetector(context, new SimpleSwitchGestureDetector());
        gestureListener = new OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                return gestureDetector.onTouchEvent(event);
            }
        };
        setOnTouchListener(gestureListener);
    }


    private class SimpleSwitchGestureDetector extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            return true;
        }

        public boolean onDown(MotionEvent e) {
            return super.onDown(e);
        }

        public boolean onSingleTapUp(MotionEvent e){
            return super.onSingleTapUp(e);
        }

        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY){

            if (e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE){
                setChecked(false);
                return true;
            }else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE){
                setChecked(true);
                return true;
            }
            return false;
        }
    }

    @Override
    public void setChecked(boolean checked) {
        super.setChecked(checked);
    }

    private int getPixelFromDip(float dip) {
        return (int) (TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dip, context.getResources().getDisplayMetrics()) + 0.5f);
    }

}
