package com.atguigu.shoppingmall0224.type.fragment;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.atguigu.shoppingmall0224.R;
import com.atguigu.shoppingmall0224.base.BaseFragment;
import com.flyco.tablayout.SegmentTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2017/6/12.
 */

public class TypeFragment extends BaseFragment {
    private static final String TAG = TypeFragment.class.getSimpleName();
    @BindView(R.id.tl_1)
    SegmentTabLayout tl1;
    @BindView(R.id.iv_type_search)
    ImageView ivTypeSearch;
    Unbinder unbinder;
    Unbinder unbinder1;
    private String[] titls = {"分类", "标签"};

    @Override
    public View initView() {
        Log.e(TAG, "分类视图被初始化了");
        View view = View.inflate(mContext, R.layout.fragment_type, null);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void initData() {
        super.initData();
        Log.e(TAG, "分类数据被初始化了");
        tl1.setTabData(titls);
        tl1.setOnTabSelectListener(new MyOnTabSelectListener());
    }



    class MyOnTabSelectListener implements OnTabSelectListener {
        @Override
        public void onTabSelect(int position) {
            Toast.makeText(TypeFragment.this.getActivity(), "position==" + position, Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onTabReselect(int position) {

        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    @OnClick(R.id.iv_type_search)
    public void onViewClicked() {

        Toast.makeText(TypeFragment.this.getContext(), "搜索", Toast.LENGTH_SHORT).show();
    }
}
