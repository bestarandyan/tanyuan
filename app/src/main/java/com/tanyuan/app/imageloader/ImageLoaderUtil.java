package com.tanyuan.app.imageloader;

import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.tanyuan.app.R;


/**
 * Created by liuxingxing on 2017/12/19.
 */

public class ImageLoaderUtil {

    public static ImageLoaderUtil loaderUtil;

    public static Context mContext;

    public static ImageLoaderUtil getInstant(Context context){
        mContext = context;
        if (loaderUtil == null){
            loaderUtil = new ImageLoaderUtil();
        }
        return loaderUtil;
    }

    public void loadImage(String url, ImageView imageView,boolean isCache){
        Glide.with(mContext).load(url).apply(getOptions(R.drawable.ease_default_image,R.drawable.em_chat_error_item_bg,isCache)).into(imageView);
    }

    public void loadImage(String url, ImageView imageView, int defRes, int errorRes,boolean isCache){
        Glide.with(mContext).load(url).apply(getOptions(defRes,errorRes,isCache)).into(imageView);
    }

    public void loadCircleImage(String url, ImageView imageView, int defRes, int errorRes,boolean isCache){
        Glide.with(mContext).load(url).apply(getOptionsCircle(defRes,errorRes,isCache)).into(imageView);
    }


    public void loadRoundImage(String url, ImageView imageView, int defRes, int errorRes,boolean isCache){
        Glide.with(mContext).load(url).apply(getOptionsRound(defRes,errorRes,isCache)).into(imageView);
    }


    @SuppressLint("CheckResult")
    private RequestOptions getOptions(int defRes, int errorRes,boolean iscache){
        RequestOptions options = new RequestOptions();
        options.placeholder(defRes);
        options.error(errorRes);
        options.centerCrop();
        options.priority(Priority.HIGH);
        options.diskCacheStrategy(DiskCacheStrategy.RESOURCE);//仅仅只缓存原来的全分辨率的图像
        options.skipMemoryCache(true);//跳过内存缓存
        return options;
    }
    @SuppressLint("CheckResult")
    private RequestOptions getOptionsCircle(int defRes, int errorRes,boolean iscache){
        RequestOptions options = new RequestOptions();
        options.placeholder(defRes);
        options.error(errorRes);
        options.centerCrop();
        options.transform(new GlideCircleTransform(mContext));
        options.priority(Priority.HIGH);
        if (iscache) {
            options.diskCacheStrategy(DiskCacheStrategy.RESOURCE);//仅仅只缓存原来的全分辨率的图像
        }
        options.skipMemoryCache(true);//跳过内存缓存
        return options;
    }

    @SuppressLint("CheckResult")
    private RequestOptions getOptionsRound(int defRes, int errorRes,boolean iscache){
        RequestOptions options = new RequestOptions();
        options.placeholder(defRes);
        options.error(errorRes);
        options.centerCrop();
        options.transform(new GlideRoundTransform(mContext));
        options.priority(Priority.HIGH);
        if (iscache) {
            options.diskCacheStrategy(DiskCacheStrategy.RESOURCE);//仅仅只缓存原来的全分辨率的图像
        }
        options.skipMemoryCache(true);//跳过内存缓存
        return options;
    }
}
