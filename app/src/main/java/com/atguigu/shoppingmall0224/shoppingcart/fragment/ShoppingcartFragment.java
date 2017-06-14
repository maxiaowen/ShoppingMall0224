package com.atguigu.shoppingmall0224.shoppingcart.fragment;

import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.atguigu.shoppingmall0224.app.MyApplication;
import com.atguigu.shoppingmall0224.base.BaseFragment;
import com.atguigu.shoppingmall0224.home.bean.GoodsBean;
import com.atguigu.shoppingmall0224.shoppingcart.utils.CartStorage;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/6/12.
 */

public class ShoppingcartFragment extends BaseFragment {
    private static final String TAG = ShoppingcartFragment.class.getSimpleName();
    private TextView textView;

    @Override
    public View initView() {
        Log.e(TAG,"购物车视图被初始化了");
        textView = new TextView(mContext);
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(25);
        textView.setTextColor(Color.RED);
        return textView;
    }
    @Override
    public void initData() {
        super.initData();
        Log.e(TAG,"购物车数据被初始化了");
        textView.setText("购物车");
        ArrayList<GoodsBean> allData = CartStorage.getInstance(MyApplication.getContext()).getAllData();
        for(int i = 0; i < allData.size(); i++) {
            Log.e("TAG",""+allData.get(i).getName());

        }
    }
}
