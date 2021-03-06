package com.atguigu.shoppingmall0224.community.fragment;


import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.alibaba.fastjson.JSON;
import com.atguigu.shoppingmall0224.R;
import com.atguigu.shoppingmall0224.base.BaseFragment;
import com.atguigu.shoppingmall0224.community.adapter.NewPostListViewAdapter;
import com.atguigu.shoppingmall0224.community.bean.NewPostBean;
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

public class NewPostFragment extends BaseFragment {

    @BindView(R.id.lv_new_post)
    ListView lvNewPost;
    Unbinder unbinder;

    private NewPostListViewAdapter adapter;

    /**
     * 初始化控件
     * retur
     */
    @Override
    public View initView() {
        View rootView = View.inflate(mContext, R.layout.fragment_new_post, null);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void initData() {
        super.initData();
        getDataFromNet(Constants.NEW_POST_URL);
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

    /**
     * 解析数据并且显示
     * @param response
     */
    private void processData(String response) {
        NewPostBean newPostBean = JSON.parseObject(response,NewPostBean.class);
        adapter = new NewPostListViewAdapter(mContext,newPostBean.getResult());
        lvNewPost.setAdapter(adapter);


    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}

