package com.atguigu.shoppingmall0224.community.fragment;



import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.atguigu.shoppingmall0224.base.BaseFragment;

/**
 * Created by Administrator on 2017/6/17.
 */

public class NewPostFragment extends BaseFragment {
    private TextView textView;

    /**
     * 初始化控件
     * retur
     */
    @Override
    public View initView() {
        textView = new TextView(mContext);
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(25);
        textView.setTextColor(Color.RED);
        return textView;
    }

    @Override
    public void initData() {
        super.initData();
        textView.setText("我是新帖内容");
    }
}

