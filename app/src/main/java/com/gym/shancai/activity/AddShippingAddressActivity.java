package com.gym.shancai.activity;

import android.os.Bundle;
import android.text.style.TtsSpan;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.gym.shancai.R;
import com.gym.shancai.bean.AddAddressBean;
import com.gym.shancai.bean.AddressListBean;
import com.gym.shancai.dialog.AddressSelecterDialog;
import com.gym.shancai.http.AddAddressHttp;
import com.gym.shancai.http.DefAddressSetHttp;
import com.gym.shancai.http.ModiAddressHttp;
import com.gym.shancai.http.base.BaseBean;
import com.gym.shancai.http.base.HttpCallBackListener;
import com.gym.shancai.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/8/28 0028.
 * 添加收货地址
 */

public class AddShippingAddressActivity extends BaseActivity implements AddressSelecterDialog.OnSelectArea {

    @BindView(R.id.etName)
    EditText etName;
    @BindView(R.id.etPhone)
    EditText etPhone;
    @BindView(R.id.tvAddress)
    TextView tvAddress;
    @BindView(R.id.etAddressDetail)
    EditText etAddressDetail;
    @BindView(R.id.toggle)
    ToggleButton toggle;
    AddressSelecterDialog addressSelecterDialog;
    boolean isEdit = false;

    @Override
    public int returnLayoutResID() {
        return R.layout.activity_shipping_address_add;
    }

    @Override
    public String setTitleInitLayout() {
        isEdit = getIntent().getBooleanExtra("isEdit", false);
        addressSelecterDialog = new AddressSelecterDialog(this);
        addressSelecterDialog.setOnSelectArea(this);
        if (isEdit) {
            data = (AddressListBean.AddressListItemBean) getIntent().getSerializableExtra("data");
            etName.setText(data.getReceivename());
            etPhone.setText(data.getPhonenum());
            etAddressDetail.setText(data.getAddressinfo());
            tvAddress.setText(data.getAddress());
            if (data.getIs_default() != null)
                toggle.setChecked(data.getIs_default().equals("1"));
            isSelectArea = true;
            if (toggle.isChecked()) {
                toggle.setClickable(false);
            } else {
                toggle.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showProgressDialog();
                        new DefAddressSetHttp(data.getId(), new HttpCallBackListener<BaseBean>() {
                            @Override
                            public void callBack(int statusCode, BaseBean bean, String msg) {
                                setOKToast("修改成功");
                                toggle.setChecked(true);
                                toggle.setClickable(false);
                                dismissProgressDialog();
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
                                dismissProgressDialog();
                            }
                        }).post();
                    }
                });
            }
            return "收货地址";
        } else {
            //添加
            return "收货地址";
        }
    }

    AddressListBean.AddressListItemBean data;

    @Override
    public void initView() {

        setRightButtonText("保存");
    }

    @OnClick({R.id.tvAddress})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tvAddress:
                addressSelecterDialog.show();
                break;

        }
    }

    private void editData() {
        showProgressDialog();

        new ModiAddressHttp(data.getId(),
                tvAddress.getText().toString(),
                etAddressDetail.getText().toString(),
                etName.getText().toString(),
                etPhone.getText().toString(),
                new HttpCallBackListener<BaseBean>() {
                    @Override
                    public void callBack(int statusCode, BaseBean bean, String msg) {
                        setOKToast("修改成功");
                        finish();
                    }

                    @Override
                    public boolean onUserFail(int statusCode, BaseBean bean, String msg) {
                        if (statusCode==-1){
                            finish();
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
                        dismissProgressDialog();
                    }
                }).post();
    }

    private void addData() {
        showProgressDialog();

        new AddAddressHttp(tvAddress.getText().toString(),
                etAddressDetail.getText().toString(),
                etName.getText().toString(),
                etPhone.getText().toString(), new HttpCallBackListener<AddAddressBean>() {
            @Override
            public void callBack(int statusCode, AddAddressBean bean, String msg) {

                setOKToast("添加成功");

                if (toggle.isChecked()) {
                    new DefAddressSetHttp(bean.getAddressid(), new HttpCallBackListener<BaseBean>() {
                        @Override
                        public void callBack(int statusCode, BaseBean bean, String msg) {
                            AddShippingAddressActivity.this.finish();
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
                } else {
                    AddShippingAddressActivity.this.finish();
                }


            }

            @Override
            public boolean onUserFail(int statusCode, AddAddressBean bean, String msg) {
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

    }

    boolean isSelectArea = false;

    //地址选择器
    @Override
    public void select(boolean isOk, String proviceName, String cityName, String disstrctName, String zipCode) {
        if (isOk) {
            tvAddress.setText(proviceName + " " + cityName + " " + disstrctName);
            isSelectArea = true;
        }

    }

    @Override
    public void rightClick(View v) {
        if (isEmp(etName.getText().toString())) {
            setErrorToast("名字不能为空");
            return;
        }
        if (isEmp(etPhone.getText().toString())) {
            setErrorToast("手机号不能为空");
            return;
        }
        if (!isSelectArea) {
            setErrorToast("请选择省市区");
            return;
        }
        if (isEmp(etAddressDetail.getText().toString())) {
            setErrorToast("详细地址不能为空");
            return;
        }
        if (isEdit) {
            editData();
        } else {
            addData();
        }

    }
}
