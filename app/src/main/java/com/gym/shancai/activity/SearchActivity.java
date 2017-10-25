package com.gym.shancai.activity;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;

import com.gym.shancai.R;
import com.gym.shancai.adapter.SearchSonAdapter;
import com.gym.shancai.bean.SearchBean;
import com.gym.shancai.http.SearchHttp;
import com.gym.shancai.http.base.HttpCallBackListener;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/8/28 0028.
 * 搜索界面-hjd
 */

public class SearchActivity extends BaseActivity {
    @BindView(R.id.etSearchSon)
    EditText etSearchSon;
    @BindView(R.id.tvCancel)
    TextView tvCancel;
    @BindView(R.id.gvNewMarket)
    GridView gvNewMarket;

    SearchSonAdapter sonAdapter;


    @Override
    public int returnLayoutResID() {
        return R.layout.activity_search;
    }

    @Override
    public String setTitleInitLayout() {
            return null;
    }

    @Override
    protected void onResume() {
        super.onResume();
        requestData();
    }

    private void requestData() {



        etSearchSon.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // 改变时会做的动作
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // 改变之前会执行的动作
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // 文半框改变之后执行的动作
                new SearchHttp(etSearchSon.getText().toString(), new HttpCallBackListener<SearchBean>() {
                    @Override
                    public void callBack(int statusCode, SearchBean bean, String msg) {
                        sonAdapter = new SearchSonAdapter(bean);
                        gvNewMarket.setAdapter(sonAdapter);

                    }

                    @Override
                    public boolean onUserFail(int statusCode, SearchBean bean, String msg) {
                        switch (statusCode) {
                            case -1:
                                return true;
                            case -2:
                                return true;
                            default:
                                setErrorToast("没有符合条件的商品哦");
                                return false;
                        }
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
    }

    @OnClick({R.id.tvCancel})
    public void OnClick(View view) {
        switch (view.getId()) {
            case R.id.tvCancel:
                finish();
                break;

        }
    }
}
