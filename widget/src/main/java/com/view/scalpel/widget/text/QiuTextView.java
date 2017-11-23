package com.view.scalpel.widget.text;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import com.view.scalpel.widget.R;

/**
 * @ClassName: LFTextView
 * @Description:封装TextView，实现指定CompoundDrawable显示大小的控件
 * 由于TextView设置CompoundDrawable之后，无法再设置其大小。 所以绕过TextView原生属性，重新定义了属性，使用者可指定： CompoundDrawable的资源、及其显示方向和显示宽高。
 * @author xiongwei
 * @date 2014年6月11日 下午12:17:00
 * 
 */
public class QiuTextView extends android.widget.TextView {

	public QiuTextView(Context context) {
		this(context, null);
	}

	public QiuTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		setGravity(Gravity.CENTER);
		initFromAttributes(context, attrs);
	}

	private void initFromAttributes(Context context, AttributeSet attrs) {
		TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.QiuTextView);

		Drawable drawable = a.getDrawable(R.styleable.QiuTextView_text_drawable_src);
		if (drawable != null) {
			int direction = a.getInt(R.styleable.QiuTextView_text_drawable_direction, 0);
			if (direction < 0 || direction > 3) {
				direction = 0;
			}

			int width = a.getDimensionPixelSize(R.styleable.QiuTextView_text_drawable_width, 0);
			int height = a.getDimensionPixelSize(R.styleable.QiuTextView_text_drawable_height, 0);

			setCompoundDrawable(drawable, direction, width, height);
		}

		a.recycle();
	}

	/**
	 * 设置TextView的CompoundDrawable于默认方向（左）
	 * 
	 * @param drawable
	 *            CompoundDrawable对象
	 */
	public void setCompoundDrawable(Drawable drawable) {
		setCompoundDrawable(drawable, 0, 0, 0);
	}

	/**
	 * 设置TextView的CompoundDrawable
	 * 
	 * @param drawable
	 *            CompoundDrawable对象
	 * @param direction
	 *            显示方向
	 * @param width
	 *            显示宽度, 等于0则按drawable实际宽度显示
	 * @param height
	 *            显示高度, 等于0则按drawable实际高度显示
	 */
	public void setCompoundDrawable(Drawable drawable, int direction, int width, int height) {
		if (drawable != null) {
			drawable.setBounds(0, 0, width == 0 ? drawable.getIntrinsicWidth() : width, height == 0 ? drawable.getIntrinsicHeight() : height);
		}
		switch (direction) {
		case 0:
			setCompoundDrawables(drawable, null, null, null);
			break;
		case 1:
			setCompoundDrawables(null, drawable, null, null);
			break;
		case 2:
			setCompoundDrawables(null, null, drawable, null);
			break;
		case 3:
			setCompoundDrawables(null, null, null, drawable);
			break;
		default:
			break;
		}
	}
}