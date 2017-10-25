package com.gym.shancai.activity;

import android.view.View;
import android.widget.ImageView;

/**
 * Created by Administrator on 2017/9/2 0002.
 * 购物车Item内部控件监听接口
 */

public interface CustomItemListener  {

    void onClickListener();

    /**
     * 增加数量
     *
     * @param position
     * @param showCountView
     * @param isChecked
     */
    void doIncreease(int position, View showCountView, boolean isChecked);

    /**
     * 删减数量
     *
     * @param position
     * @param showCountView
     * @param isChecked
     */
    void doDecrease(int position, View showCountView, boolean isChecked);


}
