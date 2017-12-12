package com.tanyuan.app.response;

import java.io.Serializable;

/**
 * Created by Administrator on 2015/3/13.
 */
public class LoginEntity implements Serializable{
    public String user_id;
    public String token;
    public String userName;//非接口返回
    public String userPassword;//非接口返回
}
