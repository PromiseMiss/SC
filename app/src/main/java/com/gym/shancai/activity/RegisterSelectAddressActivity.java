package com.gym.shancai.activity;

import android.content.Intent;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.gym.shancai.R;
import com.gym.shancai.adapter.RegisterSelectAddressAdapter;
import com.gym.shancai.bean.RegisterSelectAddressBean;
import com.gym.shancai.dialog.BaseDialog;
import com.gym.shancai.http.GetAreaDataListHttp;
import com.gym.shancai.http.GetCityDataListHttp;
import com.gym.shancai.http.GetProvinceDataListHttp;
import com.gym.shancai.http.base.HttpCallBackListener;

import java.util.HashMap;

import butterknife.BindView;

/**
 * Created by gym on 2017/9/8.
 * 选择省市区
 */

public class RegisterSelectAddressActivity extends  BaseActivity implements AdapterView.OnItemClickListener {
    @BindView(R.id.lv)
    ListView lv;
    RegisterSelectAddressBean data;
    int currStep=0;
    String provinceID="";
    String cityID="";
    String areaID="";
    String procinceName="";
    String cityName="";
    String areaName="";
    @Override
    public int returnLayoutResID() {
        return R.layout.activity_register3_province;
    }

    @Override
    public String setTitleInitLayout() {
        requestProvinceData();

      lv.setOnItemClickListener(this);
        return "快速注册";
    }

    private void requestProvinceData() {
        showProgressDialog();
        currStep=0;
        new GetProvinceDataListHttp(new HttpCallBackListener<RegisterSelectAddressBean>() {
            @Override
            public void callBack(int statusCode, RegisterSelectAddressBean bean, String msg) {
                data=bean;
                RegisterSelectAddressAdapter addressAdapter=new RegisterSelectAddressAdapter(bean);
                lv.setAdapter(addressAdapter);
            }

            @Override
            public boolean onUserFail(int statusCode, RegisterSelectAddressBean bean, String msg) {
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
    private void requestCityData() {
        showProgressDialog();
        currStep=1;
        new GetCityDataListHttp(provinceID,new HttpCallBackListener<RegisterSelectAddressBean>() {
            @Override
            public void callBack(int statusCode, RegisterSelectAddressBean bean, String msg) {
                data=bean;
                RegisterSelectAddressAdapter addressAdapter=new RegisterSelectAddressAdapter(bean);
                lv.setAdapter(addressAdapter);
            }

            @Override
            public boolean onUserFail(int statusCode, RegisterSelectAddressBean bean, String msg) {
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
    private void requestAreaData() {
        showProgressDialog();
        currStep=2;
        new GetAreaDataListHttp(cityID,new HttpCallBackListener<RegisterSelectAddressBean>() {
            @Override
            public void callBack(int statusCode, RegisterSelectAddressBean bean, String msg) {
                data=bean;
                RegisterSelectAddressAdapter addressAdapter=new RegisterSelectAddressAdapter(bean);
                lv.setAdapter(addressAdapter);
            }

            @Override
            public boolean onUserFail(int statusCode, RegisterSelectAddressBean bean, String msg) {
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
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (currStep){
            case 0:
                provinceID=data.get(position).getId();
                procinceName=data.get(position).getName();
                requestCityData();
                break;
            case 1:
                cityID=data.get(position).getId();
                cityName=data.get(position).getName();
                requestAreaData();
                break;
            case 2:
                areaID=data.get(position).getId();
                areaName=data.get(position).getName();
                Intent in=new Intent();
                in.putExtra("areaId",areaID);
                in.putExtra("provinceName",procinceName);
                in.putExtra("cityName",cityName);
                in.putExtra("areaName",areaName);
                setResult(RESULT_OK,in);
                finish();
                break;
        }
    }

    @Override
    public void back(View view) {
        backUp();
    }

    /**
     * 返回上一步
     */
    private void backUp() {
        switch (currStep){
            case 0:
                finish();
                break;
            case 1:
                requestProvinceData();
                break;
            case 2:
                requestCityData();
                break;
        }
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
       backUp();
        return true;
    }
}
