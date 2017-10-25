package com.gym.shancai.activity;

import android.graphics.Color;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.gym.shancai.R;
import com.gym.shancai.adapter.ShopOderItemsAdapter;
import com.gym.shancai.bean.AllIndentBean;
import com.gym.shancai.customview.SlidingTabStrip;
import com.gym.shancai.http.AllIndentHttp;
import com.gym.shancai.http.base.HttpCallBackListener;
import com.gym.shancai.utils.CommonUtils;
import com.gym.shancai.utils.MathUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/8/31 0031.
 * 我的订单
 */

public class MyOrderActivity extends BaseActivity implements SlidingTabStrip.TabChangeListener {
    @BindView(R.id.head_left_text_button)
    TextView headLeftTextButton;
    @BindView(R.id.head_right_text_button)
    TextView headRightTextButton;
    @BindView(R.id.lv)
    ListView lv;
    @BindView(R.id.tabs)
    com.gym.shancai.customview.SlidingTabStrip tabs;
    public int currType = 0;

    //    FragmentManager manager;
//    List<Fragment> fragmentList;
//    List<String> strings;
//    MyIndentAllFragemnt allFragemnt;
//    MyIndentAllFragemnt sendFragment;
//    MyIndentAllFragemnt payFragment;
//    MyIndentAllFragemnt goodsFragment;
    AllIndentBean data;

    @Override
    public int returnLayoutResID() {
        return R.layout.activity_my_indent;
    }

    @Override
    public String setTitleInitLayout() {
        initData();
        setRightButtonText("账单明细");
        return "我的订单";
    }

    public void initData() {
        setTitleLineColor(getResources().getColor(R.color.gray));
        //选中下划线颜色
        tabs.setIndicatorColor(getResources().getColor(R.color.main_theme));
        tabs.setIndicatorHeight(MathUtils.dip2px(2.67f));
        tabs.setDividerPadding(MathUtils.dip2px(0));
        tabs.setShouldExpand(true);
        tabs.setTextColor(Color.parseColor("#999999"));
        tabs.setDividerColor(Color.parseColor("#00000000"));
        tabs.setTabPaddingLeftRight(MathUtils.dip2px(0));
        tabs.setAllCaps(false);
        tabs.setUnderlineColor(Color.parseColor("#00000000"));
        tabs.setTextSize(MathUtils.dip2px(14f));
        tabs.setMarginBotton(MathUtils.dip2px(0));
        tabs.setMarginLeftAndRight(MathUtils.dip2px(0));
        tabs.setSelectedTextColor(getResources().getColor(R.color.main_theme));
        tabs.setOnTabChangeListener(this);
        String[] ppp = {"全部", "待付款", "待发货", "待收货"};
//        String[] ppp = {"商城商品", "积分商城商品", "创客专区商品"};
        tabs.setDataArray(ppp);


    }


    @OnClick({R.id.head_right_text_button})
    public void OnClick(View v) {
        switch (v.getId()) {
            case R.id.head_right_text_button:
                CommonUtils.startActivity(this, BillDetailActivity.class);
                break;
        }

    }

    @Override
    public void onTabChange(int position) {
        currType = position;
        requestDate();
    }

    public void requestDate() {
        showProgressDialog("");
        new AllIndentHttp(currType, new HttpCallBackListener<AllIndentBean>() {
            @Override
            public void callBack(int statusCode, AllIndentBean bean, String msg) {
                data = bean;
                ShopOderItemsAdapter shopOderItemsAdapter = new ShopOderItemsAdapter(data);
//        ListActivity listActivity = new ListActivity();
//        listActivity.setListAdapter(shopOderItemsAdapter);
                lv.setAdapter(shopOderItemsAdapter);
            }

            @Override
            public boolean onUserFail(int statusCode, AllIndentBean bean, String msg) {

                setErrorToast("暂无订单哦~");
                if (statusCode == -1) {
//                    data.removeAll(data);
//                    ((ShopOderItemsAdapter) lv.getAdapter()).notifyDataSetChanged();
                }
                return true;
            }

            @Override
            public boolean onNetError(int statusCode, String msg) {
                return false;
            }

            @Override
            public void onComplete(boolean isError) {
                dismissProgressDialog();
            }
        }).post();


    }

    @Override
    public void onResume() {
        super.onResume();
        requestDate();
    }

}
