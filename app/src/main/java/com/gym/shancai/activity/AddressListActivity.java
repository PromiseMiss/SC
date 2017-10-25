package com.gym.shancai.activity;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.gym.shancai.R;
import com.gym.shancai.adapter.NewAddressAdapter;
import com.gym.shancai.bean.AddressListBean;
import com.gym.shancai.http.AddressListHttp;
import com.gym.shancai.http.DefAddressSetHttp;
import com.gym.shancai.http.base.HttpCallBackListener;
import com.gym.shancai.utils.CommonUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/8/28 0028.
 * 收货地址
 */

public class AddressListActivity extends BaseActivity implements NewAddressAdapter.DefChangeListener {
    @BindView(R.id.llAddLocation)
    LinearLayout llAddLocation;
    @BindView(R.id.lsAddShippingLocation)
    ListView lsAddShippingLocation;
    public boolean isSelect=false;
    public String ordertext;
    NewAddressAdapter adapter;
    boolean isFirst=true;
    @Override
    public int returnLayoutResID() {
        return R.layout.activity_address_list;
    }

    @Override
    public String setTitleInitLayout() {
        isSelect=getIntent().getBooleanExtra("isSelect",false);
        ordertext=getIntent().getStringExtra("ordertext");
        return "收货地址";
    }
    @OnClick({R.id.llAddLocation})
    public void OnClick(View v) {
        switch (v.getId()) {
            case R.id.llAddLocation:
                CommonUtils.startActivity(this, AddShippingAddressActivity.class);
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        requestData();
    }

    /**
     * 获取列表数据并且加载到界面
     */
    private void requestData() {
        showProgressDialog();
        new AddressListHttp(new HttpCallBackListener<AddressListBean>() {
            @Override
            public void callBack(int statusCode, AddressListBean bean, String msg) {
                adapter = new NewAddressAdapter(AddressListActivity.this, bean);
                adapter.setDefChangeListener(AddressListActivity.this);
                lsAddShippingLocation.setAdapter(adapter);
                if (bean.size()==0){
                    if (isFirst) {
//                        rlNo.setVisibility(View.VISIBLE);
                        CommonUtils.startActivity(AddressListActivity.this, AddShippingAddressActivity.class);
                        isFirst = false;
                    }
                }else
                {
//                    rlNo.setVisibility(View.GONE);
                }
            }

            @Override
            public boolean onUserFail(int statusCode, AddressListBean bean, String msg) {
                if (statusCode==-1){
                    if (isFirst) {
//                        rlNo.setVisibility(View.VISIBLE);
                        CommonUtils.startActivity(AddressListActivity.this, AddShippingAddressActivity.class);
                        isFirst = false;
                    }
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

    @Override
    public void onDefChange(int position, AddressListBean.AddressListItemBean data) {
        showProgressDialog();
        new DefAddressSetHttp(data.getId(), new HttpCallBackListener() {
            @Override
            public void callBack(int statusCode, Object bean, String msg) {
                setOKToast( "修改成功");
                requestData();
            }

            @Override
            public boolean onUserFail(int statusCode, Object bean, String msg) {
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
}
