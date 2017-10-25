package com.gym.shancai.activity;

import android.os.Bundle;
import android.widget.FrameLayout;

import com.gym.shancai.R;
import com.gym.shancai.fragment.ShopCarFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by gym on 2017/9/25.
 */

public class ShopCarActivity extends BaseActivity {
    @BindView(R.id.fl)
    FrameLayout fl;

    /**
     * return一个布局文件 用来设置当前的activity
     *
     * @return
     */
    @Override
    public int returnLayoutResID() {
        return R.layout.activity_shop_car;
    }

    /**
     * 设置一个标题
     *
     * @return
     */
    @Override
    public String setTitleInitLayout() {
        getSupportFragmentManager().beginTransaction().replace(R.id.fl, new ShopCarFragment()).commitAllowingStateLoss();
        return "购物车";
    }

}
