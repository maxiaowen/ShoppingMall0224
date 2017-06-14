package com.atguigu.addsubview;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.TintTypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.atguigu.addsubview.R.id.iv_add;
import static com.atguigu.addsubview.R.id.iv_sub;

/**
 * Created by Administrator on 2017/6/14.
 */

public class AddSubView extends LinearLayout {

    private final Context context;
    @BindView(iv_sub)
    ImageView ivSub;
    @BindView(R.id.tv_value)
    TextView tvValue;
    @BindView(iv_add)
    ImageView ivAdd;

    private int value = 1;
    private int maxvalue = 10;
    private int minvalue = 1;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
        tvValue.setText(value+"");
    }

    public int getMaxvalue() {
        return maxvalue;
    }

    public void setMaxvalue(int maxvalue) {
        this.maxvalue = maxvalue;
    }

    public int getMinvalue() {
        return minvalue;
    }

    public void setMinvalue(int minvalue) {
        this.minvalue = minvalue;
    }

    public AddSubView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        ButterKnife.bind(this, View.inflate(context, R.layout.add_sub_view, this));

        if (attrs != null) {
            //取出属性
            TintTypedArray tintTypedArray = TintTypedArray.obtainStyledAttributes(context, attrs, R.styleable.AddSubView);
            int value = tintTypedArray.getInt(R.styleable.AddSubView_value, 0);
            if (value > 0) {
                setValue(value);
            }
            int minValue = tintTypedArray.getInt(R.styleable.AddSubView_minValue, 0);
            if (minvalue > 0) {
                setMinvalue(minValue);
            }
            int maxValue = tintTypedArray.getInt(R.styleable.AddSubView_maxValue, 0);
            if (value > 0) {
                setMaxvalue(maxValue);
            }
            Drawable addDrawable = tintTypedArray.getDrawable(R.styleable.AddSubView_numberAddBackground);
            if (addDrawable != null) {
                ivAdd.setImageDrawable(addDrawable);
            }
            Drawable subDrawable = tintTypedArray.getDrawable(R.styleable.AddSubView_numberSubBackground);
            if (subDrawable != null) {
                ivSub.setImageDrawable(subDrawable);
            }
        }
    }

    @OnClick({iv_sub, iv_add})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case iv_sub:
                if(value > minvalue) {
                    value--;
                }
                tvValue.setText(value+"");
                break;
            case iv_add:
                if(value < maxvalue) {
                    value++;
                }
                tvValue.setText(value+"");
                break;
        }
        if(changeListener != null) {
            changeListener.numberChange(value);
        }
    }

    public interface OnNumberChangeListener {
        public void numberChange(int value);
    }

    private OnNumberChangeListener changeListener;

    public void setOnNumberChangeListener(OnNumberChangeListener changeListener){
        this.changeListener = changeListener;
    }
}
