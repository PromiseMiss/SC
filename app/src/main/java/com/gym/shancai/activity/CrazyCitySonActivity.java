package com.gym.shancai.activity;

import android.widget.ListView;
import android.widget.TextView;

import com.gym.shancai.Constant;
import com.gym.shancai.R;
import com.gym.shancai.adapter.CrazyCityAdapter;
import com.gym.shancai.bean.CoolSummerBean;
import com.gym.shancai.bean.FindGoodsBean;
import com.gym.shancai.http.CoolSummerHttp;
import com.gym.shancai.http.FindGoodsHttp;
import com.gym.shancai.http.PrefectureHttp;
import com.gym.shancai.http.TastyHttp;
import com.gym.shancai.http.TopLineHttp;
import com.gym.shancai.http.base.HttpCallBackListener;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/9/8 0008.
 * 王牌狂欢城子页面
 */

public class CrazyCitySonActivity extends BaseActivity {


    CrazyCityAdapter adapter;
    @BindView(R.id.lvBaseCity)
    ListView lvBaseCity;
    @BindView(R.id.head_title)
    TextView headTitle;

    @Override
    public int returnLayoutResID() {
        return R.layout.activity_base_crazy_city;
    }

    @Override
    public String setTitleInitLayout() {
        initData();
        return null;
    }

    public void initData() {
        switch (getIntent().getIntExtra("type", 0)) {
            case Constant.CRAZY_CITY_SON_FIND:
                showProgressDialog();
                new FindGoodsHttp(new HttpCallBackListener<FindGoodsBean>() {
                    @Override
                    public void callBack(int statusCode, FindGoodsBean bean, String msg) {
                        adapter = new CrazyCityAdapter(bean);
                        lvBaseCity.setAdapter(adapter);
                        headTitle.setText("发现好货");
                    }

                    @Override
                    public boolean onUserFail(int statusCode, FindGoodsBean bean, String msg) {
                        return false;
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
                break;
            case Constant.CRAZY_CITY_SON_GOOD_EAT:
                showProgressDialog();
                new TastyHttp(new HttpCallBackListener<FindGoodsBean>() {
                    @Override
                    public void callBack(int statusCode, FindGoodsBean bean, String msg) {
                        adapter = new CrazyCityAdapter(bean);
                        lvBaseCity.setAdapter(adapter);
                        headTitle.setText("好吃不停");
                    }

                    @Override
                    public boolean onUserFail(int statusCode, FindGoodsBean bean, String msg) {
                        return false;
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
                break;
            case Constant.CRAZY_CITY_SON_TOP_LINE:
                showProgressDialog();
                new TopLineHttp(new HttpCallBackListener<FindGoodsBean>() {
                    @Override
                    public void callBack(int statusCode, FindGoodsBean bean, String msg) {
                        adapter = new CrazyCityAdapter(bean);
                        lvBaseCity.setAdapter(adapter);
                        headTitle.setText("品牌头条");
                    }

                    @Override
                    public boolean onUserFail(int statusCode, FindGoodsBean bean, String msg) {
                        return false;
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
                break;
            case Constant.CRAZY_CITY_SON_PRECUT:
                showProgressDialog();
                new PrefectureHttp(new HttpCallBackListener<FindGoodsBean>() {
                    @Override
                    public void callBack(int statusCode, FindGoodsBean bean, String msg) {
                        adapter = new CrazyCityAdapter(bean);
                        lvBaseCity.setAdapter(adapter);
                        headTitle.setText("预制菜专区");
                    }

                    @Override
                    public boolean onUserFail(int statusCode, FindGoodsBean bean, String msg) {
                        return false;
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
                break;
        }
    }
}
