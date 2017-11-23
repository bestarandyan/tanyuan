package com.view.scalpel.widget.pickerview;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.PopupWindow;

import com.view.scalpel.widget.R;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

/**
 * 时间选择器
 * 
 * @author Sai
 * 
 */
public class TimePopupWindow extends PopupWindow implements OnClickListener {
	public enum Type {
		ALL, YEAR_MONTH_DAY,YEAR_MONTH_DAY_HOURS, HOURS_MINS, MONTH_DAY_HOUR_MIN , YEAR_MONTH,NUMBER
	}// 四种选择模式，年月日时分，年月日，时分，月日时分

	private View rootView; // 总的布局
	WheelTime wheelTime;
	private View btnSubmit, btnCancel;
	private static final String TAG_SUBMIT = "submit";
	private static final String TAG_CANCEL = "cancel";
	private OnTimeSelectListener timeSelectListener;
	View mAlphaLayout;

	public TimePopupWindow(Context context, Type type) {
		super(context);
		this.setWidth(LayoutParams.MATCH_PARENT);
		this.setHeight(LayoutParams.MATCH_PARENT);
		setFocusable(true);
		this.setBackgroundDrawable(new BitmapDrawable());// 这样设置才能点击屏幕外dismiss窗口
		this.setOutsideTouchable(true);
		this.setAnimationStyle(R.style.timepopwindow_anim_style);

		LayoutInflater mLayoutInflater = LayoutInflater.from(context);
		rootView = mLayoutInflater.inflate(R.layout.pw_time, null);
		//透明背景
		mAlphaLayout = rootView.findViewById(R.id.alphaLayout_share);
		mAlphaLayout.setOnClickListener(this);
		handler.sendEmptyMessageDelayed(0,500);
		// -----确定和取消按钮
		btnSubmit = rootView.findViewById(R.id.btnSubmit);
		btnSubmit.setTag(TAG_SUBMIT);
		btnCancel = rootView.findViewById(R.id.btnCancel);
		btnCancel.setTag(TAG_CANCEL);
		btnSubmit.setOnClickListener(this);
		btnCancel.setOnClickListener(this);
		// ----时间转轮
		final View timepickerview = rootView.findViewById(R.id.timepicker);
		ScreenInfo screenInfo = new ScreenInfo((Activity) context);
		wheelTime = new WheelTime(timepickerview, type);
		wheelTime.screenheight = screenInfo.getHeight();
		//默认选中当前时间
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(System.currentTimeMillis());
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		int hours = calendar.get(Calendar.HOUR_OF_DAY);
		int minute = calendar.get(Calendar.MINUTE);
		wheelTime.setPicker(year, month, day, hours, minute);
		setContentView(rootView);
	}
	Handler handler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f,1.0f);
			alphaAnimation.setDuration(500);
			alphaAnimation.setFillAfter(true);
			alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
				@Override
				public void onAnimationStart(Animation animation) {
					mAlphaLayout.setVisibility(View.VISIBLE);
				}

				@Override
				public void onAnimationEnd(Animation animation) {

				}

				@Override
				public void onAnimationRepeat(Animation animation) {

				}
			});
			mAlphaLayout.startAnimation(alphaAnimation);

		}
	};

	public void setDismiss(){
		AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f,0.0f);
		alphaAnimation.setDuration(100);
		alphaAnimation.setFillAfter(true);
		alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
			@Override
			public void onAnimationStart(Animation animation) {

			}

			@Override
			public void onAnimationEnd(Animation animation) {
				mAlphaLayout.setVisibility(View.INVISIBLE);
				dismiss();
			}

			@Override
			public void onAnimationRepeat(Animation animation) {

			}
		});
		mAlphaLayout.startAnimation(alphaAnimation);
	}
	/**
	 * 设置可以选择的时间范围
	 * 
	 * @param START_YEAR
	 * @param END_YEAR
	 */
	public void setRange(int START_YEAR, int END_YEAR) {
		WheelTime.setSTART_YEAR(START_YEAR);
		WheelTime.setEND_YEAR(END_YEAR);
	}

	/**
	 * 设置选中时间
	 * @param date
	 */
	public void setTime(Date date) {
		Calendar calendar = Calendar.getInstance();
		if (date == null)
			calendar.setTimeInMillis(System.currentTimeMillis());
		else
			calendar.setTime(date);
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		int hours = calendar.get(Calendar.HOUR_OF_DAY);
		int minute = calendar.get(Calendar.MINUTE);
		wheelTime.setPicker(year, month, day, hours, minute);
	}

	/**
	 * 指定选中的时间，显示选择器
	 * 
	 * @param parent
	 * @param gravity
	 * @param x
	 * @param y
	 * @param date
	 */
	public void showAtLocation(View parent, int gravity, int x, int y, Date date) {
		Calendar calendar = Calendar.getInstance();
		if (date == null)
			calendar.setTimeInMillis(System.currentTimeMillis());
		else
			calendar.setTime(date);
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		int hours = calendar.get(Calendar.HOUR_OF_DAY);
		int minute = calendar.get(Calendar.MINUTE);
		wheelTime.setPicker(year, month, day, hours, minute);
		update();
		super.showAtLocation(parent, gravity, x, y);
	}

	/**
	 * 设置是否循环滚动
	 * 
	 * @param cyclic
	 */
	public void setCyclic(boolean cyclic) {
		wheelTime.setCyclic(cyclic);
	}

	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.alphaLayout_share){
			setDismiss();
			return;
		}
		String tag = (String) v.getTag();
		if (tag.equals(TAG_CANCEL)) {
			setDismiss();
			return;
		} else {
			if (timeSelectListener != null) {
				try {
					Date date = WheelTime.dateFormat.parse(wheelTime.getTime());
					timeSelectListener.onTimeSelect(date);
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			setDismiss();
			return;
		}
	}

	public interface OnTimeSelectListener {
		public void onTimeSelect(Date date);
	}

	public void setOnTimeSelectListener(OnTimeSelectListener timeSelectListener) {
		this.timeSelectListener = timeSelectListener;
	}

}
