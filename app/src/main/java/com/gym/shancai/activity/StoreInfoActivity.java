package com.gym.shancai.activity;

import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.gym.shancai.APP;
import com.gym.shancai.R;
import com.gym.shancai.bean.SelfInfoBean;
import com.gym.shancai.dialog.UpHeadIcoDialog;
import com.gym.shancai.http.StoreInfoHttp;
import com.gym.shancai.http.base.HttpCallBackListener;
import com.gym.shancai.utils.ActivityStackManager;
import com.gym.shancai.utils.FileUtils;
import com.gym.shancai.utils.ImageUtils;

import java.io.File;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/9/7 0007.
 * 店铺信息
 * 必须传入一个 userid
 */

public class StoreInfoActivity extends BaseActivity implements UpHeadIcoDialog.SelectListener {
    private static final int REQUEST_SELECT_AREA = 1;
    private static final int REQUEST_SELECT_PIC = 2;
    @BindView(R.id.head_left_text_button)
    TextView headLeftTextButton;
    @BindView(R.id.etStoreName)
    EditText etStoreName;
    @BindView(R.id.etTel)
    EditText etTel;
    @BindView(R.id.etName)
    EditText etName;
    @BindView(R.id.tvLocation)
    TextView tvLocation;
    @BindView(R.id.tvDel)
    TextView tvDel;
    @BindView(R.id.btnRegStore)
    Button btnRegStore;
    @BindView(R.id.etID)
    EditText etID;
    @BindView(R.id.etLicense)
    EditText etLicense;
    @BindView(R.id.iv)
    ImageView iv;
    @BindView(R.id.etAddressDetail)
            EditText etAddressDetail;

    String userid;
    //上传方式 相机 相册
    int how;
    private static final int XC = 1; //相册
    private static final int XJ = 2; //相机
    String fileName = "idtemp.tmp";
    private String IMAGE_FILE_LOCATION = APP.getInstance().getExternalCacheDir().getAbsolutePath();
    UpHeadIcoDialog rechargeWayDialog;
    private File mOutputFile;
    String picUrl;
    public String addressId;
    @Override
    public int returnLayoutResID() {
        return R.layout.activity_store_info;
    }

    @Override
    public String setTitleInitLayout() {
        ActivityStackManager.getAppManager().addActivity(this);
        userid=getIntent().getStringExtra("userId");
        setLeftDrawable(R.drawable.icon_close);

        rechargeWayDialog = new UpHeadIcoDialog(this);
        rechargeWayDialog.setonSelectListener(this);
        return "店铺信息";
    }

    @OnClick({R.id.btnRegStore,R.id.iv,R.id.tvDel,R.id.tvLocation})
    public void OnClick(View view) {
        switch (view.getId()) {
            case R.id.iv:
                rechargeWayDialog.show();
                break;
            case R.id.tvDel:
                iv.setImageResource(R.drawable.icon_add_photos);
                picUrl="";
                break;
            case R.id.tvLocation:
                Intent intent = new Intent(this, RegisterSelectAddressActivity.class);
                startActivityForResult(intent, REQUEST_SELECT_AREA);
                break;
            case R.id.btnRegStore:
                if (isEmp(userid)){
                    setErrorToast("userId为空");
                    return;
                }
                if (isEmp(etStoreName.getText().toString())){
                    setErrorToast("店铺名称不能为空");
                    return;
                }
                if (isEmp(etTel.getText().toString())){
                    setErrorToast("联系电话不能为空");
                    return;
                }
                if (etTel.getText().length()<7){
                    setErrorToast("电话号不正确");
                    return;
                }
                if (isEmp(etName.getText().toString())){
                    setErrorToast("名字不能为空");
                    return;
                }
                if (isEmp(addressId)){
                    setErrorToast("店铺地址不能为空");
                    return;
                }
                if (isEmp(etID.getText().toString())){
                    setErrorToast("请填写身份证号");
                    return;
                }
                if (isEmp(etLicense.getText().toString()))
                {
                    setErrorToast("请填写营业执照编号");
                    return;
                }
                if (isEmp(picUrl)){
                    setErrorToast("请上传营业执照");
                    return;
                }
                showProgressDialog();
                new StoreInfoHttp(userid, etStoreName.getText().toString(), etTel.getText().toString(),
                        etLicense.getText().toString(),addressId,  etName.getText().toString()
                        , etID.getText().toString(),picUrl,etAddressDetail.getText().toString(),  new HttpCallBackListener<SelfInfoBean>() {
                    @Override
                    public void callBack(int statusCode, SelfInfoBean bean, String msg) {
                        finish();
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
                break;
        }

    }

    @Override
    protected void onActivityResult(final int requestCode, int resultCode, final Intent data) {
        if (resultCode == RESULT_OK) {
            switch (requestCode){
                case REQUEST_SELECT_AREA:
                    addressId=data.getStringExtra("areaId");
                    tvLocation.setText(data.getStringExtra("provinceName")+" "+data.getStringExtra("cityName")+" "+data.getStringExtra("areaName"));
                    break;
                case REQUEST_SELECT_PIC:
                    if (how == XJ) {
                        picUrl = mOutputFile.getAbsolutePath();

                    } else {
                        picUrl = FileUtils.getRealPathFromUri(StoreInfoActivity.this, data.getData());
                    }

                    ImageUtils.INSTANCE.loadImage(StoreInfoActivity.this, picUrl, iv);

                    break;
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    /**
     * 0取消
     * 1拍照
     * 2相册
     *
     * @param type
     */
    @Override
    public void onSelect(int type) {
        switch (type) {
            case 1:
                how = XJ;
                mOutputFile = new File(IMAGE_FILE_LOCATION, fileName);
                Uri uri = Uri.fromFile(mOutputFile);
                Intent newIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                newIntent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                startActivityForResult(newIntent, REQUEST_SELECT_PIC);

//          Toast.makeText(UserProfileActivity.this, getString(R.string.toast_no_support),
//                  Toast.LENGTH_SHORT).show();
                break;
            case 2:
                how = XC;
                Intent pickIntent = new Intent(Intent.ACTION_PICK, null);
                pickIntent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                startActivityForResult(pickIntent, REQUEST_SELECT_PIC);
                break;
            default:
                break;
        }
    }

}