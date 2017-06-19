package com.atguigu.shoppingmall0224.community.fragment;


import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.alibaba.fastjson.JSON;
import com.atguigu.shoppingmall0224.R;
import com.atguigu.shoppingmall0224.base.BaseFragment;
import com.atguigu.shoppingmall0224.community.adapter.HotPostListViewAdapter;
import com.atguigu.shoppingmall0224.community.bean.HotPostBean;
import com.atguigu.shoppingmall0224.uilts.Constants;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import okhttp3.Call;

import static android.content.ContentValues.TAG;

/**
 * Created by Administrator on 2017/6/17.
 */

public class HotPostFragment extends BaseFragment {

    @BindView(R.id.lv_hot_post)
    ListView lvHotPost;
    Unbinder unbinder;

    private HotPostListViewAdapter adapter;

    /**
     * 初始化控件
     * retur
     */
    @Override
    public View initView() {
        View rootView = View.inflate(mContext, R.layout.fragment_hot_post, null);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void initData() {
        super.initData();
        getDataFromNet(Constants.HOT_POST_URL);
    }

    private void getDataFromNet(String url) {
        System.out.println("url==" + url);
        OkHttpUtils
                .get()
                .url(url)
                .build()
                .execute(new MyStringCallback());
    }

    class MyStringCallback extends StringCallback {

        @Override
        public void onError(Call call, Exception e, int id) {
            Log.e(TAG, "请求成功失败==" + e.getMessage());
        }

        @Override
        public void onResponse(String response, int id) {
            Log.e(TAG, "请求成功==" );
            processData(response);

        }
    }

    private void processData(String response) {
        HotPostBean hotPostBean = JSON.parseObject(response, HotPostBean.class);
        adapter = new HotPostListViewAdapter(mContext,hotPostBean.getResult());
        lvHotPost.setAdapter(adapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}

