package com.tanyuan.network.Utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by liuxingxing on 2017/11/29.
 */

public class JsonUtils {

    public static Map<String,String> javaBeanToMap(Object javaBean){
        Map<String,String> resultMap = new HashMap<>();
        Method[] methods = javaBean.getClass().getDeclaredMethods();
        for (Method method : methods){
            if (method.getName().startsWith("get")){
                String field = method.getName();
                field = field.substring(field.indexOf("get")+3);

                try {
                    Object value = method.invoke(javaBean,(Object[])null);
                    resultMap.put(field,null!=value?value.toString():"");
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }

        return resultMap;
    }
}
