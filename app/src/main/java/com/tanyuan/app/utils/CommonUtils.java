package com.tanyuan.app.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.view.inputmethod.InputMethodManager;


import java.security.MessageDigest;
import java.text.DecimalFormat;

public class CommonUtils {
	/**
	 * 关闭键盘
	 * 
	 * @param context
	 * @param view
	 */
	public static void closeKeyBoard(Context context, View view) {
		InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
		if (((Activity) context).getCurrentFocus() != null && ((Activity) context).getCurrentFocus().getWindowToken() != null) {
			imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
		}
	}

	/**
	 * 打开键盘
	 * 
	 * @param context
	 * @param view
	 */
	public static void showKeyBoard(Context context, View view) {
		view.requestFocus();
		InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
	}

	private static AlertDialog mDialog; // GPS设置提示弹框


	/**
	 * 拨打电话
	 */
	public static void dial(Context context, String phoneNum) {
		if (phoneNum != null) {
			Uri phone = Uri.parse("tel:" + phoneNum);
			Intent intent = new Intent(Intent.ACTION_DIAL, phone);
			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			context.startActivity(intent);
		}
	}

	public static String getMD5(String str) {
		MessageDigest md5 = null;
		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
		char[] charArray = str.toCharArray();
		byte[] byteArray = new byte[charArray.length];
		for (int i = 0; i < charArray.length; i++) {
			byteArray[i] = (byte) charArray[i];
		}
		byte[] md5Bytes = md5.digest(byteArray);
		StringBuffer hexValue = new StringBuffer();
		for (int i = 0; i < md5Bytes.length; i++) {
			int val = ((int) md5Bytes[i]) & 0xff;
			if (val < 16) {
				hexValue.append("0");
			}
			hexValue.append(Integer.toHexString(val));
		}
		return hexValue.toString();
	}

	/**
	 * 保留小数点后两位数
	 * @return
	 */
	public static float getFloat2(float f){
		DecimalFormat df = new DecimalFormat(".00");
		return Float.parseFloat(df.format(f));
	}
}
