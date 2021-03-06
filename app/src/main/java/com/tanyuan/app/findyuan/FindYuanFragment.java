package com.tanyuan.app.findyuan;

import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.tanyuan.app.R;
import com.tanyuan.app.interfaces.AdapterClickListener;
import com.tanyuan.app.request.CircleListRequest;
import com.tanyuan.app.request.DeleteDongtaiRequest;
import com.tanyuan.app.response.BallFriendCirlceResponse;
import com.tanyuan.app.response.CircleModel;
import com.tanyuan.app.response.QiuBaseResponse;
import com.tanyuan.app.utils.preference.UserPreferenceUtils;
import com.tanyuan.app.widget.MyItemDecoration;
import com.tanyuan.network.interfaces.RequestInterface;
import com.tanyuan.network.request.RequestManager;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * Created by liuxingxing on 2017/11/15.
 */

public class FindYuanFragment extends Fragment {
    RecyclerView mListView;
    View mView;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_findyuan,null);
        initView();
        return mView;
    }

    private void initView(){
        mListView = mView.findViewById(R.id.dongtaiList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        mListView.setLayoutManager(layoutManager);
        mListView.addItemDecoration(new MyItemDecoration(getActivity(),20));
    }

    private void setView(final ArrayList<CircleModel> models){
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                CircleAdapter adapter = new CircleAdapter(getActivity(),models);
                adapter.setClickListener(new AdapterClickListener() {
                    @Override
                    public void OnClick(int type, int positon) {
                        if (type == 0){
                            Toast.makeText(getActivity(),"这是第" + positon +"条数据", Toast.LENGTH_LONG).show();
                            deleteDongtaiRequest(models.get(positon).getUserStateId(),models.get(positon).getUserId());
                        }
                    }
                });
                mListView.setAdapter(adapter);

            }
        });
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
                        if (response!=null && response.succeeded()) {
                            setView(response.data);
                        }
                    }

                    @Override
                    public void onErrorData(BallFriendCirlceResponse response) {
                        Log.e("获取动态返回结果 =====" ,response.getMessage());
                    }
                }).requestByGet(request);
    }

    private void deleteDongtaiRequest(int stateid,int userid){
        DeleteDongtaiRequest request = new DeleteDongtaiRequest(getActivity());
        request.setUserStateId(stateid);
        request.setUserId(userid);
       RequestManager.builder().setResponse(QiuBaseResponse.class)
               .setRequestListener(new RequestInterface<QiuBaseResponse>() {
                   @Override
                   public void onReceivedData(QiuBaseResponse response) {
                       getCircleRequest();
                   }

                   @Override
                   public void onErrorData(QiuBaseResponse response) {

                   }
               })
               .requestByGet(request);
    }

}
