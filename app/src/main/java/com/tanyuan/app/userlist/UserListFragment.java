package com.tanyuan.app.userlist;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;

import com.tanyuan.app.R;
import com.tanyuan.app.base.FiltrageBaseFragment;
import com.tanyuan.app.request.BallFriendsListRequest;
import com.tanyuan.app.response.BallFriendsListResponse;
import com.tanyuan.app.response.BallFriendsModel;
import com.tanyuan.network.request.RequestManager;
import com.view.scalpel.widget.listview.BottomRefreshListView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.List;

/**
 * Created by liuxingxing on 15/9/5.
 */
@EFragment(R.layout.fragment_ballfriens_list)
public class UserListFragment extends FiltrageBaseFragment implements AdapterView.OnItemClickListener{
    @ViewById(R.id.ballListView)
    BottomRefreshListView mListView;

    @ViewById(R.id.ball_list_refresh)
    SwipeRefreshLayout mRefreshLayout;

    @ViewById(R.id.noDataView)
    LinearLayout mDataView;

    private List<BallFriendsModel> mList;
    UserListAdapter mAdapter;
    public Bundle mSelectInfoBundle = new Bundle();
    BallFriendsListRequest request = null;
    BallFriendsListResponse mResponse;

    @AfterViews
    void init() {
        initListView();
        getBallfriends(0,30,true);
    }

    private void initListView() {
        mRefreshLayout.setOnRefreshListener(mOnRefreshListener);
        /**设置下拉刷新的转圈色*/
        mRefreshLayout.setColorSchemeResources(android.R.color.holo_red_light, android.R.color.holo_green_light,
                android.R.color.holo_orange_light);
        mListView.setOnLoadMoreListener(mOnLoadMoreListener);
        mListView.setOnItemClickListener(this);


    }
    private void notifyListView(){
        if (mAdapter!=null){
            mAdapter.setList(mList);
            mAdapter.notifyDataSetChanged();
        }else{
            mAdapter = new UserListAdapter(getActivity(), mList);
            mListView.setAdapter(mAdapter);
        }
        mRefreshLayout.setVisibility(View.VISIBLE);
        mRefreshLayout.setRefreshing(false);
        mListView.onLoadMoreComplete();
        mListView.setEmptyView(mDataView);
    }
    /**
     * 下拉的监听
     */
    private SwipeRefreshLayout.OnRefreshListener mOnRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
                getBallfriends(0, 30,false);
        }
    };
    /**
     * 加载更多监听
     */
    private BottomRefreshListView.OnLoadMoreListener mOnLoadMoreListener = new BottomRefreshListView.OnLoadMoreListener() {
        @Override
        public void onLoadMore() {
            getBallfriends(mList.size(),30,false);
        }
    };
    private void getBallfriends(final int start,int size,boolean isShowLoading) {
        request = new BallFriendsListRequest(getActivity());
        request.setGender(1);
//        RequestManager.loadData("https://www.baidu.com/",request);
        RequestManager manager = new RequestManager();
        manager.requestByGet(request);
    }



    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
    }


    @Click(R.id.noDataView)
    void clickReGetData(){
        getBallfriends(0, 30, true);
    }


}
