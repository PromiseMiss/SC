package com.gym.shancai.activity;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gym.shancai.APP;
import com.gym.shancai.R;
import com.gym.shancai.bean.SelfInfoBean;
import com.gym.shancai.dialog.UpHeadIcoDialog;
import com.gym.shancai.http.SelfInfoHttp;
import com.gym.shancai.http.UpHeadHttp;
import com.gym.shancai.http.base.HttpCallBackListener;
import com.gym.shancai.utils.CommonUtils;
import com.gym.shancai.utils.ImageUtils;
import com.gym.shancai.utils.ToastUtils;

import java.io.File;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/8/25 0025.
 * 个人信息-hjd
 */

public class PersonalInfoActivity extends BaseActivity implements UpHeadIcoDialog.SelectListener {

    @BindView(R.id.head_left_text_button)
    TextView headLeftTextButton;
    @BindView(R.id.rlHead)
    RelativeLayout setPhoto;
    @BindView(R.id.tvPhoneNum)
    TextView tvPhoneNum;
    @BindView(R.id.llYouTel)
    LinearLayout llYouTel;
    @BindView(R.id.llYouName)
    LinearLayout llYouName;
    @BindView(R.id.llYouID)
    LinearLayout llYouID;
    @BindView(R.id.llRegisterTime)
    LinearLayout llRegisterTime;
    @BindView(R.id.ivHead)
    ImageView ivHead;
    @BindView(R.id.tvRealName)
    TextView tvRealName;
    @BindView(R.id.tvIdCardNum)
    TextView tvIdCardNum;
    @BindView(R.id.tvRegisterTime)
    TextView tvRegisterTime;
    @BindView(R.id.tvUser)
    TextView tvUser;

    UpHeadIcoDialog rechargeWayDialog2;

    @Override
    public int returnLayoutResID() {
        return R.layout.activity_personal_information;
    }

    @Override
    public String setTitleInitLayout() {
        return "个人信息";
    }

    @OnClick({R.id.head_left_text_button, R.id.rlHead, R.id.tvUser, R.id.llYouTel})
    public void OnClick(View view) {
        switch (view.getId()) {
            case R.id.head_left_text_button:
                finish();
                break;
            case R.id.rlHead:
                rechargeWayDialog2 = new UpHeadIcoDialog(this);
                rechargeWayDialog2.setonSelectListener(this);
                rechargeWayDialog2.show();
                break;
            case R.id.tvUser:
                params = new HashMap();
                params.put("username", data.getUsername());
                CommonUtils.startActivity(this, SetUserNameActivity.class, params);
                break;
            case R.id.llYouTel:
//                params = new HashMap();
//                params.put("phone", data.getPhone());
//                CommonUtils.startActivity(this, SetPhoneNumActivity.class, params);
                break;
        }

    }

    String fileName = "headtemp.tmp";
    private String IMAGE_FILE_LOCATION = APP.getInstance().getExternalCacheDir().getAbsolutePath();
    private File mOutputFile;

    private final int REQUESTCODE_CAR = 3;
    private final int REQUESTCODE_PICK = 1;
    private final int REQUESTCODE_CUTTING = 2;

    @Override
    public void onSelect(int type) {
        switch (type) {
            case 1:
                mOutputFile = new File(IMAGE_FILE_LOCATION, fileName);
                Uri uri = Uri.fromFile(mOutputFile);
                Intent newIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                newIntent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                startActivityForResult(newIntent, REQUESTCODE_CAR);

//          Toast.makeText(UserProfileActivity.this, getString(R.string.toast_no_support),
//                  Toast.LENGTH_SHORT).show();
                break;
            case 2:
                Intent pickIntent = new Intent(Intent.ACTION_PICK, null);
                pickIntent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                startActivityForResult(pickIntent, REQUESTCODE_PICK);
                break;
            default:
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        if (resultCode == Activity.RESULT_CANCELED) {
            return;
        }
        switch (requestCode) {
            case REQUESTCODE_CAR:
                startPhotoZoom(Uri.fromFile(mOutputFile));
                break;
            case REQUESTCODE_PICK:
                if (data == null || data.getData() == null) {
                    return;
                }
                startPhotoZoom(data.getData());
                break;
            case REQUESTCODE_CUTTING:
                if (data != null) {
                    setPicToView(data);
                }
                break;
            default:
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void startPhotoZoom(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", true);
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", 300);
        intent.putExtra("outputY", 300);
        intent.putExtra("return-data", true);
        intent.putExtra("noFaceDetection", true);
        startActivityForResult(intent, REQUESTCODE_CUTTING);
    }

    private void setPicToView(Intent picdata) {
        Bundle extras = picdata.getExtras();
        if (extras != null) {
            final Bitmap photo = extras.getParcelable("data");
//            Drawable drawable = new BitmapDrawable(getResources(), photo);
            ImageUtils.INSTANCE.loadImageCircle(this, ImageUtils.INSTANCE.Bitmap2Bytes(photo), ivHead, true);

            showProgressDialog();
            new UpHeadHttp(photo, new HttpCallBackListener() {
                @Override
                public void callBack(int statusCode, Object bean, String msg) {
                    ToastUtils.setOKToast("上传成功");
                    requestData();
                    photo.recycle();
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

    SelfInfoBean data;

    private void requestData() {
        showProgressDialog();
        new SelfInfoHttp(new HttpCallBackListener<SelfInfoBean>() {
            @Override
            public void callBack(int statusCode, SelfInfoBean bean, String msg) {
                data = bean;
                ImageUtils.INSTANCE.loadImageCircle(PersonalInfoActivity.this, data.getAvatar(), ivHead, true);
                tvPhoneNum.setText(data.getPhone());
                tvRealName.setText(data.getReallyname());
                tvIdCardNum.setText(data.getIdcard());
                tvRegisterTime.setText(data.getRegister_time());

            }

            @Override
            public boolean onUserFail(int statusCode, SelfInfoBean bean, String msg) {
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
    protected void onResume() {
        super.onResume();
        requestData();
    }
}
