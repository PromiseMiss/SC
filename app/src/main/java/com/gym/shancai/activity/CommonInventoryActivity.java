package com.gym.shancai.activity;


import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gym.shancai.R;
import com.gym.shancai.adapter.CommonInventoryAdapter;
import com.gym.shancai.bean.CommonInventoryBean;
import com.gym.shancai.dialog.BaseDialog;
import com.gym.shancai.http.CommonInventoryHttp;
import com.gym.shancai.http.DeleteCommonInventiryHttp;
import com.gym.shancai.http.base.BaseBean;
import com.gym.shancai.http.base.HttpCallBackListener;
import com.gym.shancai.utils.SharedPreferencesSetting;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/8/25 0025.
 * 常用清单-hjd
 */

public class CommonInventoryActivity extends BaseActivity implements CompoundButton.OnCheckedChangeListener {
    @BindView(R.id.lv)
    ListView lv;
    @BindView(R.id.ll_no)
    LinearLayout llNo;
    @BindView(R.id.tv_delete)
    TextView tvDelete;
    @BindView(R.id.rl_edit)
    RelativeLayout rlEdit;
    @BindView(R.id.cb_all)
    CheckBox cbAll;
    CommonInventoryAdapter adapter;
    BaseDialog baseDialog;

    CommonInventoryBean data;
    String usersid;

//    @BindView(android.R.id.empty)
//    LinearLayout empty;

    @Override
    public int returnLayoutResID() {
        return R.layout.activity_common_inventory;
    }

    @Override
    public String setTitleInitLayout() {
        cbAll.setOnCheckedChangeListener(this);
        setRightButtonText("编辑");
        return "常用清单";
    }

    private void initData() {
        usersid = SharedPreferencesSetting.getInstance().getUserId();
//        lv.setEmptyView(empty);
//        showProgressDialog();
        new CommonInventoryHttp(new HttpCallBackListener<CommonInventoryBean>() {
            @Override
            public void callBack(int statusCode, CommonInventoryBean bean, String msg) {

                llNo.setVisibility(View.GONE);
                data = bean;
                adapter = new CommonInventoryAdapter(CommonInventoryActivity.this, data);
                lv.setAdapter(adapter);
                if (data.size() == 0) {
                    llNo.setVisibility(View.VISIBLE);
                }
                lv.setAdapter(adapter);
            }

            @Override
            public boolean onUserFail(int statusCode, CommonInventoryBean bean, String msg) {
                return true;
            }

            @Override
            public boolean onNetError(int statusCode, String msg) {
                return false;
            }

            @Override
            public void onComplete(boolean isError) {
//                dismissProgressDialog();
            }
        }).post();

    }

    public boolean isEdit = false;

    public void rightClick(View view) {
        if (isEdit) {
            setRightButtonText("编辑");
            isEdit = false;
            rlEdit.setVisibility(View.GONE);
        } else {
            setRightButtonText("取消");
            rlEdit.setVisibility(View.VISIBLE);
            isEdit = true;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        initData();
    }

    String ids;

    @OnClick({R.id.tv_delete})
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.tv_delete:
                ids = "";
                int num = 0;
                for (int i = 0; i < data.size(); i++) {
                    if (data.get(i).isSelect()) {
                        ids += data.get(i).getId() + ",";
                        num++;
                    }
                }
                if (TextUtils.isEmpty(ids)) {
                    setErrorToast("请至少选择一个");
                    return;
                }
//                showProgressDialog();
                baseDialog = new BaseDialog(this).setTitle("确认删除" + num + "个收藏吗?").addCancel("我再看看").addOk(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        baseDialog.dismiss();
//                        showProgressDialog();

                        new DeleteCommonInventiryHttp(usersid, ids, new HttpCallBackListener<BaseBean>() {
                            @Override
                            public void callBack(int statusCode, BaseBean bean, String msg) {
                                setOKToast("删除成功");
                                adapter.notifyDataSetChanged();
                                initData();
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
                            }
                        }).post();
                    }
                });
                baseDialog.show();

                break;
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (data != null && adapter != null) {
            for (int i = 0; i < data.size(); i++) {
                data.get(i).setSelect(isChecked);
            }
            adapter.notifyDataSetChanged();
        }
    }

}
