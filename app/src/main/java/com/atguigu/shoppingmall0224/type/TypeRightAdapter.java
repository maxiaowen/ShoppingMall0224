package com.atguigu.shoppingmall0224.type;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.atguigu.shoppingmall0224.R;
import com.atguigu.shoppingmall0224.app.GoodsInfoActivity;
import com.atguigu.shoppingmall0224.home.adapter.HomeAdapter;
import com.atguigu.shoppingmall0224.home.bean.GoodsBean;
import com.atguigu.shoppingmall0224.type.bean.TypeBean;
import com.atguigu.shoppingmall0224.uilts.Constants;
import com.atguigu.shoppingmall0224.uilts.DensityUtil;
import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/6/17.
 */

public class TypeRightAdapter extends RecyclerView.Adapter {
    private final Context mContext;
    /**
     * 热卖的数据集合
     */
    private final List<TypeBean.ResultBean.HotProductListBean> hot_product_list;
    /**
     * 常用的数据集合
     */
    private final List<TypeBean.ResultBean.ChildBean> child;

    /**
     * 热卖推荐
     */
    private static final int HOT = 0;
    /**
     * 常用分类
     */
    private static final int COMMON = 1;



    /**
     * 当前类型
     */
    private int currentType = HOT;


    public TypeRightAdapter(Context mContext, List<TypeBean.ResultBean> result) {
        this.mContext = mContext;
        this.hot_product_list = result.get(0).getHot_product_list();
        this.child = result.get(0).getChild();

    }

    @Override
    public int getItemCount() {
        return 1 + child.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position == HOT) {
            currentType = HOT;
        } else {
            currentType = COMMON;
        }
        return currentType;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == HOT) {
            View itemView = View.inflate(mContext, R.layout.item_hot_right, null);
            return new HotViewHolder(itemView);
        } else if (viewType == COMMON) {
            View itemView = View.inflate(mContext, R.layout.item_common_right, null);
            return new CommonViewHolder(itemView);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == HOT) {
            HotViewHolder hotViewHolder = (HotViewHolder) holder;
            hotViewHolder.setData(hot_product_list);
        } else {
            int realPostion = position - 1;
            CommonViewHolder commonViewHolder = (CommonViewHolder) holder;
            commonViewHolder.setData(child.get(realPostion));
        }

    }


    class HotViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.ll_hot_right)
        LinearLayout llHotRight;
        @BindView(R.id.hsl_hot_right)
        HorizontalScrollView hslHotRight;

        public HotViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setData(final List<TypeBean.ResultBean.HotProductListBean> hot_product_list) {
            for (int i = 0; i < hot_product_list.size(); i++) {
                TypeBean.ResultBean.HotProductListBean listBean = hot_product_list.get(i);

                //创建线性布局

                /**
                 * 线性布局
                 */
                final LinearLayout myLinear = new LinearLayout(mContext);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(-2, -2);

                params.setMargins(DensityUtil.dip2px(mContext, 5), 0, DensityUtil.dip2px(mContext, 5), DensityUtil.dip2px(mContext, 20));

                //竖直方向
                myLinear.setOrientation(LinearLayout.VERTICAL);
                //居中对齐
                myLinear.setGravity(Gravity.CENTER);

                //要注意，根据tag取数据
                myLinear.setTag(i);


                //创建图片，设置图片，并且添加到线性布局

                ImageView imageView = new ImageView(mContext);
                LinearLayout.LayoutParams ivParams = new LinearLayout.LayoutParams(DensityUtil.dip2px(mContext, 80), DensityUtil.dip2px(mContext, 80));
                ivParams.setMargins(0, 0, 0, DensityUtil.dip2px(mContext, 10));

                //请求图片
                Glide.with(mContext).load(Constants.BASE_URL_IMAGE + listBean.getFigure()).into(imageView);
                myLinear.addView(imageView, ivParams);


                //创建文本，，设置文本信息，并且添加到线性布局

                LinearLayout.LayoutParams tvParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

                TextView textView = new TextView(mContext);
                textView.setGravity(Gravity.CENTER);
//                textView.setTextColor(Color.RED);
                textView.setTextColor(Color.parseColor("#ed3f3f"));
                textView.setText("￥" + listBean.getCover_price());

                myLinear.addView(textView, tvParams);


//

                //要把当前的线性布局添加到外部的线性布局里面

                llHotRight.addView(myLinear, params);

                //设置点击事件

                myLinear.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int position = (int) myLinear.getTag();
//                        TypeBean.ResultBean.HotProductListBean hotProductListBean = hot_product_list.get(position);
//                        Toast.makeText(mContext, "name=" + hotProductListBean.getName() + "," + hotProductListBean.getCover_price(), Toast.LENGTH_SHORT).show();
                        String cover_price = hot_product_list.get(position).getCover_price();
                        String name = hot_product_list.get(position).getName();
                        String figure = hot_product_list.get(position).getFigure();
                        String product_id = hot_product_list.get(position).getProduct_id();
                        GoodsBean goodsBean = new GoodsBean();
                        goodsBean.setProduct_id(product_id);
                        goodsBean.setFigure(figure);
                        goodsBean.setCover_price(cover_price);
                        goodsBean.setName(name);

                        Intent intent = new Intent(mContext, GoodsInfoActivity.class);
                        intent.putExtra(HomeAdapter.GOODS_BEAN, goodsBean);
                        mContext.startActivity(intent);

                    }
                });


            }
        }
    }

    class CommonViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_ordinary_right)
        ImageView ivOrdinaryRight;
        @BindView(R.id.tv_ordinary_right)
        TextView tvOrdinaryRight;
        @BindView(R.id.ll_root)
        LinearLayout llRoot;

        public CommonViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        public void setData(final TypeBean.ResultBean.ChildBean childBean) {
            //设置图片
            Glide.with(mContext).load(Constants.BASE_URL_IMAGE + childBean.getPic()).placeholder(R.drawable.new_img_loading_2)
                    .error(R.drawable.new_img_loading_2).into(ivOrdinaryRight);

            //设置名称
            tvOrdinaryRight.setText(childBean.getName());

            //设置点击事件
            llRoot.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, "name=="+childBean.getName(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
