package com.gym.shancai.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gym.shancai.R;
import com.gym.shancai.activity.MainActivity;
import com.gym.shancai.activity.ShopCarActivity;
import com.gym.shancai.adapter.ShopCarListAdapter;
import com.gym.shancai.bean.CarDelBean;
import com.gym.shancai.bean.CarTotalBean;
import com.gym.shancai.bean.CatIndexListBean;
import com.gym.shancai.bean.CreateOrderBean;
import com.gym.shancai.http.CarCreateOredeHttp;
import com.gym.shancai.http.CarDelHttp;
import com.gym.shancai.http.CarTotalHttp;
import com.gym.shancai.http.CatIndexListHttp;
import com.gym.shancai.http.JudgeShopCarNumHttp;
import com.gym.shancai.http.ShopCarNumChangeHttp;
import com.gym.shancai.http.base.BaseBean;
import com.gym.shancai.http.base.HttpCallBackListener;
import com.gym.shancai.utils.CommonUtils;
import com.gym.shancai.utils.Logger;
import com.gym.shancai.utils.MathUtils;
import com.gym.shancai.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2017/8/22 0022.
 * 购物车
 */

public class ShopCarFragment extends Fragment implements ShopCarListAdapter.OnItemCheckBoxChangeListener, CompoundButton.OnCheckedChangeListener {


    @BindView(R.id.head_left_text_button)
    TextView headLeftTextButton;
    @BindView(R.id.head_title)
    TextView headTitle;
    @BindView(R.id.head_right_button)
    ImageView headRightButton;
    @BindView(R.id.head_right_text_button)
    TextView headRightTextButton;
    @BindView(R.id.view_title_line)
    View viewTitleLine;
    @BindView(R.id.ivShopCarNull)
    ImageView ivShopCarNull;
    @BindView(R.id.lvShopCar)
    ListView lvShopCar;
    @BindView(R.id.cbAllCheck)
    CheckBox cbAllCheck;
    @BindView(R.id.tvAllMoney)
    TextView tvAllMoney;
    @BindView(R.id.tvSettleAccounts)
    TextView tvSettleAccounts;
    @BindView(R.id.tvDel)
    TextView tvDel;
    @BindView(android.R.id.empty)
    LinearLayout empty;


    ShopCarListAdapter adapter;
    View rootView;
    String ids;
    CatIndexListBean data;

    public boolean isEditMode = false;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_shop_car, container, false);
            ButterKnife.bind(this, rootView);
            headTitle.setText("购物车");
            if (getActivity() instanceof MainActivity) {
                headLeftTextButton.setVisibility(View.GONE);
            }
            if (getActivity() instanceof ShopCarActivity) {
                headLeftTextButton.setVisibility(View.VISIBLE);
            }
            headRightTextButton.setVisibility(View.VISIBLE);
            headRightTextButton.setText("编辑");

            lvShopCar.setEmptyView(empty);
            cbAllCheck.setOnCheckedChangeListener(this);
        }
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        requestData();
    }

    public void requestData() {

        MainActivity.getInstence().showProgressDialog();
        new CatIndexListHttp(new HttpCallBackListener<CatIndexListBean>() {
            @Override
            public void callBack(int statusCode, CatIndexListBean bean, String msg) {
                data = bean;
                lvShopCar.setVisibility(View.VISIBLE);
                headRightTextButton.setText("编辑");
                isEditMode = false;
                empty.setVisibility(View.GONE);
                adapter = new ShopCarListAdapter(ShopCarFragment.this, data);
                adapter.setOnItemCheckBoxChangeListener(ShopCarFragment.this);
                lvShopCar.setAdapter(adapter);
                cbAllCheck.setChecked(false);
                reAddCarPrice();

            }

            @Override
            public boolean onUserFail(int statusCode, CatIndexListBean bean, String msg) {
                if (statusCode == -1) {
                    data = new CatIndexListBean();

                    lvShopCar.setVisibility(View.GONE);
                    empty.setVisibility(View.VISIBLE);
                    return true;
                }
                return false;
            }

            @Override
            public boolean onNetError(int statusCode, String msg) {
                return false;
            }

            @Override
            public void onComplete(boolean isError) {
                MainActivity.getInstence().dismissProgressDialog();
            }
        }).post();
    }


    @OnClick({R.id.tvSettleAccounts, R.id.head_right_text_button, R.id.cbAllCheck, R.id.tvDel})
    public void OnClick(View v) {
        switch (v.getId()) {
            //编辑
            case R.id.head_right_text_button:
                if (isEditMode) {
                    //完成状态下界面
                    headRightTextButton.setText("编辑");
                    tvDel.setVisibility(View.GONE);
                    tvSettleAccounts.setVisibility(View.VISIBLE);
                    isEditMode = false;
                    saveNumData();
                } else {
                    //编辑状态下界面
                    headRightTextButton.setText("完成");
                    tvSettleAccounts.setVisibility(View.GONE);
                    tvDel.setVisibility(View.VISIBLE);
                    isEditMode = true;
                }
                if (data != null && data.size() != 0) {
                    adapter.notifyDataSetChanged();
                }
                break;
            //结算
            case R.id.tvSettleAccounts:
                ids = "";
                for (int i = 0; i < data.size(); i++) {
                    if (data.get(i).isSelect()) {
                        ids += data.get(i).getId() + ",";
                    }
                }
                if (ids.length() < 1) {
                    ToastUtils.setErrorToast("至少选择一个");
                    return;
                }
                ids = ids.substring(0, ids.length() - 1);
                MainActivity.getInstence().showProgressDialog();
                new CarCreateOredeHttp(ids, new HttpCallBackListener<CreateOrderBean>() {
                    @Override
                    public void callBack(int statusCode, CreateOrderBean bean, String msg) {
                        CommonUtils.startConfigOrderActivity(MainActivity.getInstence(), bean.getOrderid());
                    }

                    @Override
                    public boolean onUserFail(int statusCode, CreateOrderBean bean, String msg) {
                        return false;
                    }

                    @Override
                    public boolean onNetError(int statusCode, String msg) {
                        return false;
                    }

                    @Override
                    public void onComplete(boolean isError) {
                        MainActivity.getInstence().dismissProgressDialog();
                    }
                }).post();
                break;

            //删除商品
            case R.id.tvDel:
                ids = "";
                for (int i = 0; i < data.size(); i++) {
                    if (data.get(i).isSelect()) {
                        ids += data.get(i).getId() + ",";
                    }
                }
                if (ids.length() < 1) {
                    ToastUtils.setErrorToast("至少选择一个");
                    return;
                }
                ids = ids.substring(0, ids.length() - 1);
                MainActivity.getInstence().showProgressDialog();
                new CarDelHttp(ids, new HttpCallBackListener<CarDelBean>() {
                    @Override
                    public void callBack(int statusCode, CarDelBean bean, String msg) {
                        for (int i = 0; i < data.size(); i++) {
                            if (data.get(i).isSelect()) {
                                data.remove(i);
                            }
                        }
                        adapter.notifyDataSetChanged();
                    }


                    @Override
                    public boolean onUserFail(int statusCode, CarDelBean bean, String msg) {
                        return false;
                    }

                    @Override
                    public boolean onNetError(int statusCode, String msg) {
                        return false;
                    }

                    @Override
                    public void onComplete(boolean isError) {
                        MainActivity.getInstence().dismissProgressDialog();
                    }
                }).post();
                break;
        }
    }

    int callBackNum;

    /**
     * 保存数量数据到服务器
     */
    private void saveNumData() {
        callBackNum = 0;
        MainActivity.getInstence().showProgressDialog();
        ids = "";
        for (int i = 0; i < data.size(); i++) {
            ids += data.get(i).getId() + "#" + data.get(i).getGoodsnum() + ",";
//            new JudgeShopCarNumHttp(data.get(i).getId() + "#" + data.get(i).getGoodsnum(), new HttpCallBackListener<BaseBean>() {
//                @Override
//                public void callBack(int statusCode, BaseBean bean, String msg) {
//
//                }
//
//                @Override
//                public boolean onUserFail(int statusCode, BaseBean bean, String msg) {
//                    return false;
//                }
//
//                @Override
//                public boolean onNetError(int statusCode, String msg) {
//                    return false;
//                }
//
//                @Override
//                public void onComplete(boolean isError) {
//                    callBackNum++;
//                    if (!isError&&callBackNum>=data.size()){
//                        MainActivity.getInstence().dismissProgressDialog();
//                    }else {
//
//                    }
//                }
//            }).post();
        }
        if (TextUtils.isEmpty(ids)) {
            return;
        }
        ids = ids.substring(0, ids.length() - 1);
        new ShopCarNumChangeHttp(ids, new HttpCallBackListener<BaseBean>() {
            @Override
            public void callBack(int statusCode, BaseBean bean, String msg) {

            }

            @Override
            public boolean onUserFail(int statusCode, BaseBean bean, String msg) {
                return false;
            }

            @Override
            public boolean onNetError(int statusCode, String msg) {
                return false;
            }

            @Override
            public void onComplete(boolean isError) {
                MainActivity.getInstence().dismissProgressDialog();
            }
        }).post();
    }


    @Override
    public void onItemCheckBoxChange() {
        reAddCarPrice();
    }

    /**
     * 计算购物车的选中商品价格
     */
    private void reAddCarPrice() {
        float allPrice = 0;
        int j = 0;
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).isSelect()) {
                allPrice += data.get(i).getGoodsprice() * data.get(i).getGoodsnum();
                j++;
            }
        }
        if (j == 0) {
            cbAllCheck.setChecked(false);
        }
        if (j == data.size()) {
            cbAllCheck.setChecked(true);
        }
        tvAllMoney.setText("￥" + MathUtils.get4down5up2d(allPrice));
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        for (int i = 0; i < data.size(); i++) {
            data.get(i).setSelect(b);
        }
        adapter.notifyDataSetChanged();

        reAddCarPrice();
//        reAddCarPrice();
    }
}
