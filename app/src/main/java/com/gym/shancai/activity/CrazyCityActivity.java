package com.gym.shancai.activity;

import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gym.shancai.Constant;
import com.gym.shancai.R;
import com.gym.shancai.adapter.CoolSummerAdapter;
import com.gym.shancai.bean.CoolSummerBean;
import com.gym.shancai.http.CoolSummerHttp;
import com.gym.shancai.http.base.HttpCallBackListener;
import com.gym.shancai.utils.CommonUtils;
import com.gym.shancai.utils.ImageUtils;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/8/30 0030.
 * 王牌狂欢城
 */

public class CrazyCityActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.head_left_text_button)
    TextView headLeftTextButton;
    @BindView(R.id.lvCrazyCity)
    ListView lvCrazyCity;

    ImageView ivCool;
    RelativeLayout rlFindGoods;
    RelativeLayout rlTasty;
    RelativeLayout rlTopLine;
    RelativeLayout rlPrefecture;
    CoolSummerAdapter cityAdapter;

    View headView;
    @BindView(R.id.srlRefresh)
    SwipeRefreshLayout srlRefresh;

    @Override
    public int returnLayoutResID() {
        return R.layout.activity_crazy_city;
    }

    @Override
    public String setTitleInitLayout() {
        headView = LayoutInflater.from(MainActivity.getInstence()).inflate(R.layout.item_crazy_head, null);
        lvCrazyCity.addHeaderView(headView);

        ivCool = headView.findViewById(R.id.ivCool);
        rlFindGoods = headView.findViewById(R.id.rlFindGoods);
        rlTasty = headView.findViewById(R.id.rlTasty);
        rlTopLine = headView.findViewById(R.id.rlTopLine);
        rlPrefecture = headView.findViewById(R.id.rlPrefecture);
        initData();

        return "王牌狂欢城";
    }

    public void initData() {
        srlRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                requestData();
            }
        });
        srlRefresh.setRefreshing(true);
        requestData();


        ivCool.setOnClickListener(this);
        rlFindGoods.setOnClickListener(this);
        rlTasty.setOnClickListener(this);
        rlTopLine.setOnClickListener(this);
        rlPrefecture.setOnClickListener(this);


    }

    private void requestData() {
        new CoolSummerHttp(new HttpCallBackListener<CoolSummerBean>() {
            @Override
            public void callBack(int statusCode, CoolSummerBean bean, String msg) {

                cityAdapter = new CoolSummerAdapter(bean);
                lvCrazyCity.setAdapter(cityAdapter);
                cityAdapter.notifyDataSetChanged();
                ImageUtils.INSTANCE.loadImage(MainActivity.getInstence(), bean.getPicture(), ivCool);

            }

            @Override
            public boolean onUserFail(int statusCode, CoolSummerBean bean, String msg) {
                return false;
            }

            @Override
            public boolean onNetError(int statusCode, String msg) {
                return false;
            }

            @Override
            public void onComplete(boolean isError) {
                srlRefresh.setRefreshing(false);
            }
        }).post();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rlFindGoods:
                CommonUtils.startCrazyCitySonActivity(CrazyCityActivity.this, Constant.CRAZY_CITY_SON_FIND);
                break;
            case R.id.rlTasty:
                CommonUtils.startCrazyCitySonActivity(CrazyCityActivity.this, Constant.CRAZY_CITY_SON_GOOD_EAT);
                break;
            case R.id.rlTopLine:
                CommonUtils.startCrazyCitySonActivity(CrazyCityActivity.this, Constant.CRAZY_CITY_SON_TOP_LINE);
                break;
            case R.id.rlPrefecture:
                CommonUtils.startCrazyCitySonActivity(CrazyCityActivity.this, Constant.CRAZY_CITY_SON_PRECUT);
                break;
        }
    }

}
