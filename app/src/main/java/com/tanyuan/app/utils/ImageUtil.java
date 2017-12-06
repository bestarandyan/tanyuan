package com.tanyuan.app.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by liuxingxing on 2017/12/5.
 */

public class ImageUtil {
    //定义一个HashMap ,保存软应用对象
    private Map<String,SoftReference<Bitmap>> imageCache = new HashMap<>();

    //再来定义一个方法，保存Bitmap的软应用到HashMap；
    public void addBitmapToCache(String path) {
        // 强引用的Bitmap对象
        Bitmap bitmap = BitmapFactory.decodeFile(path);
        // 软引用的Bitmap对象
        SoftReference<Bitmap> softBitmap =new SoftReference<>(bitmap);
        // 添加该对象到Map中使其缓存
        imageCache.put(path, softBitmap);
    }
    //获取的时候，可以通过SoftReference的get()方法得到Bitmap对象
    public Bitmap getBitmapByPath(String path) {
        // 从缓存中取软引用的Bitmap对象
        SoftReference softBitmap = imageCache.get(path);
        // 判断是否存在软引用
        if(softBitmap ==null) {
            return null;
        }
        // 取出Bitmap对象，如果由于内存不足Bitmap被回收，将取得空
        Bitmap bitmap = (Bitmap) softBitmap.get();
        return bitmap;
    }
}
