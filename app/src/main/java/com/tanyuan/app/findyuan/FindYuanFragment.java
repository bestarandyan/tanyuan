package com.tanyuan.app.findyuan;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tanyuan.app.R;
import com.tanyuan.app.request.CircleListRequest;
import com.tanyuan.app.response.BallFriendCirlceResponse;
import com.tanyuan.app.utils.preference.UserPreferenceUtils;
import com.tanyuan.network.interfaces.RequestInterface;
import com.tanyuan.network.request.RequestManager;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;

import java.math.BigDecimal;

/**
 * Created by liuxingxing on 2017/11/15.
 */

@EFragment(R.layout.fragment_findyuan)
public class FindYuanFragment extends Fragment {

    @AfterViews
    void init(){

    }

    @Override
    public void onResume() {
        super.onResume();
        getCircleRequest();
    }

    private void getCircleRequest(){
        CircleListRequest request = new CircleListRequest(getActivity());
        request.setLat(new BigDecimal(0));
        request.setLon(new BigDecimal(0));
        request.setOpUserId(0);
        request.setUserId(Integer.parseInt(UserPreferenceUtils.getUserData(getActivity()).user_id));
        request.setPageIndex(0);
        request.setPageSize(30);

        RequestManager.builder().setResponse(BallFriendCirlceResponse.class)
                .setRequestListener(new RequestInterface<BallFriendCirlceResponse>() {
                    @Override
                    public void onReceivedData(BallFriendCirlceResponse response) {
                        Log.e("获取动态返回结果 =====" ,response.getMessage());
                    }

                    @Override
                    public void onErrorData(BallFriendCirlceResponse response) {
                        Log.e("获取动态返回结果 =====" ,response.getMessage());
                    }
                }).requestByGet(request);
    }



}
