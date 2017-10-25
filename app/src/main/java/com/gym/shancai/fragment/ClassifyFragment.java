package com.gym.shancai.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;

import com.gym.shancai.R;
import com.gym.shancai.activity.MainActivity;
import com.gym.shancai.adapter.ClassificationLeftAdapter;
import com.gym.shancai.adapter.ClassificationRightAdapter;
import com.gym.shancai.adapter.ClassifyHeadAdapter;
import com.gym.shancai.bean.ClassifyHeadBean;
import com.gym.shancai.bean.ClassifyLeftBean;
import com.gym.shancai.bean.ClassifyRightBean;
import com.gym.shancai.http.ClassifyHeadDataHttp;
import com.gym.shancai.http.ClassifyLeftDataHttp;
import com.gym.shancai.http.ClassifyRightDataHttp;
import com.gym.shancai.http.base.HttpCallBackListener;
import com.gym.shancai.utils.ImageUtils;
import com.gym.shancai.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/8/22 0022.
 * 分类页——hjd
 */

public class ClassifyFragment extends Fragment implements AdapterView.OnItemClickListener {
    View rootView;
    @BindView(R.id.leftLv)
    ListView leftLv;
    @BindView(R.id.rightLv)
    ListView rightLv;
    @BindView(R.id.gv)
    GridView gv;
    @BindView(R.id.ivHead)
    ImageView ivHead;

    ClassifyHeadBean headData;
    ClassifyLeftBean leftData;
    ClassifyRightBean rightData;

    ClassifyHeadAdapter headAdapter;

    ClassificationLeftAdapter classificationLeftAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_classify, container, false);
            ButterKnife.bind(this, rootView);
            initView();
        }
        return rootView;
    }

    private void initView() {
        gv.setOnItemClickListener(this);
        leftLv.setOnItemClickListener(this);
        requestHeadData();
    }


    /**
     * 请求一级菜单数据
     */
    private void requestHeadData() {

        new ClassifyHeadDataHttp(new HttpCallBackListener<ClassifyHeadBean>() {
            @Override
            public void callBack(int statusCode, ClassifyHeadBean bean, String msg) {
                headData = bean;
                headAdapter = new ClassifyHeadAdapter(headData);
                gv.setAdapter(headAdapter);
                ImageUtils.INSTANCE.loadImage(MainActivity.getInstence(), headData.getPicture(), ivHead);
                if (gv.getAdapter().getCount() > 0) {
                    gv.performItemClick(gv.getChildAt(0), 0, gv.getItemIdAtPosition(0));
                } else {

                }

            }

            @Override
            public boolean onUserFail(int statusCode, ClassifyHeadBean bean, String msg) {
                if (statusCode == -1) {

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

            }
        }).post();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        switch (adapterView.getId()) {
            case R.id.gv:
                MainActivity.getInstence().showProgressDialog();
                new ClassifyLeftDataHttp(headData.getType().get(position).getId(), new HttpCallBackListener<ClassifyLeftBean>() {
                    @Override
                    public void callBack(int statusCode, ClassifyLeftBean bean, String msg) {
                        leftData = bean;
                        classificationLeftAdapter = new ClassificationLeftAdapter(leftData);
                        leftLv.setAdapter(classificationLeftAdapter);
                        if (leftLv.getAdapter().getCount() > 0) {
                            leftLv.performItemClick(leftLv.getChildAt(0), 0, leftLv.getItemIdAtPosition(0));
                        }
                    }

                    @Override
                    public boolean onUserFail(int statusCode, ClassifyLeftBean bean, String msg) {
                        if (statusCode == -1) {
                            ToastUtils.setErrorToast("当前分类没有商品呦~");
                            leftData.removeAll(leftData);
                            ((ClassificationLeftAdapter) leftLv.getAdapter()).notifyDataSetChanged();
                            rightData.removeAll(rightData);
                            ((ClassificationRightAdapter) rightLv.getAdapter()).notifyDataSetChanged();
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

                break;
            case R.id.leftLv:
                /**
                 * 加载右数据
                 */
                MainActivity.getInstence().showProgressDialog();
                for (int i = 0; i < leftData.size(); i++) {
                    leftData.get(i).setSelect(false);
                    classificationLeftAdapter.notifyDataSetChanged();
                }
                leftData.get(position).setSelect(true);
                new ClassifyRightDataHttp(headData.getType().get(position).getId(), new HttpCallBackListener<ClassifyRightBean>() {
                    @Override
                    public void callBack(int statusCode, ClassifyRightBean bean, String msg) {
                        rightData = bean;
                        rightLv.setAdapter(new ClassificationRightAdapter(rightData));
                    }

                    @Override
                    public boolean onUserFail(int statusCode, ClassifyRightBean bean, String msg) {
                        if (statusCode == -1) {
                            rightData.removeAll(rightData);
                            ((ClassificationRightAdapter) rightLv.getAdapter()).notifyDataSetChanged();
                            ToastUtils.setErrorToast("当前分类没有商品呦~");
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
                break;
            case R.id.rightLv:
//                CommonUtils.startActivity(MainActivity.getInstence(),);
                break;
            default:

                break;
        }

    }


}
