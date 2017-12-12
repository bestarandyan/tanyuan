package com.tanyuan.app.utils.preference;

import android.content.Context;
import android.content.SharedPreferences;

import com.tanyuan.app.response.LoginEntity;

/**
 * Created by liuxingxing on 2017/12/8.
 */

public class UserPreferenceUtils {
    public static String PREKEY = "userInfo_preKey";
    public static String USERID = "user_id";
    public static String TOKEN = "token";
    public static String USERNAME = "userName";
    public static String USERPASSWORD = "userPassword";
    public static void commitUserData(Context context,LoginEntity entity){
        SharedPreferences preferences = context.getSharedPreferences(PREKEY,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(USERID,entity.user_id);
        editor.putString(TOKEN,entity.token);
        editor.putString(USERNAME,entity.userName);
        editor.putString(USERPASSWORD,entity.userPassword);
        editor.commit();
    }

    public static LoginEntity getUserData(Context context){
        SharedPreferences preferences = context.getSharedPreferences(PREKEY,Context.MODE_PRIVATE);
        LoginEntity entity = new LoginEntity();
        entity.token = preferences.getString(TOKEN,"");
        entity.user_id = preferences.getString(USERID,"");
        entity.userName = preferences.getString(USERNAME,"");
        entity.userPassword = preferences.getString(USERPASSWORD,"");
        return entity;
    }
}
