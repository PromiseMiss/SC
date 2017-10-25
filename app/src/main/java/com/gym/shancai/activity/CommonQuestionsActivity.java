package com.gym.shancai.activity;

import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.gym.shancai.APP;
import com.gym.shancai.R;
import com.gym.shancai.bean.CommonQuestionsBean;
import com.gym.shancai.dialog.UpHeadIcoDialog;
import com.gym.shancai.http.CommonQueationsHttp;
import com.gym.shancai.http.base.HttpCallBackListener;
import com.gym.shancai.utils.FileUtils;
import com.gym.shancai.utils.ImageUtils;

import java.io.File;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/8/26 0026.
 * 常见问题       客服中心跳转
 */

public class CommonQuestionsActivity extends BaseActivity implements UpHeadIcoDialog.SelectListener {

    //上传方式 相机 相册
    int how;
    private static final int XC = 1; //相册
    private static final int XJ = 2; //相机
    String fileName = "idtemp.tmp";
    private String IMAGE_FILE_LOCATION = APP.getInstance().getExternalCacheDir().getAbsolutePath();
    UpHeadIcoDialog rechargeWayDialog;
    private File mOutputFile;
    String picUrl;

    private static final int REQUEST_SELECT_AREA = 1;
    private static final int REQUEST_SELECT_PIC = 2;

    @BindView(R.id.etCoupleBack)
    EditText etCoupleBack;
    @BindView(R.id.head_right_text_button)
    TextView headRightTextButton;
    @BindView(R.id.ivPhoto)
    ImageView ivPhoto;
    @BindView(R.id.tvDel)
    TextView tvDel;

    @Override
    public int returnLayoutResID() {
        return R.layout.activity_common_questions;
    }

    @Override
    public String setTitleInitLayout() {
        setRightButtonText("提交");
        rechargeWayDialog = new UpHeadIcoDialog(this);
        rechargeWayDialog.setonSelectListener(this);
        initData();
        return "常见问题";
    }

    private void initData() {

    }


    @OnClick({R.id.head_right_text_button, R.id.tvDel,R.id.ivPhoto})
    public void OnClick(View view) {
        switch (view.getId()) {
            case R.id.ivPhoto:
                rechargeWayDialog.show();
                break;
            case R.id.tvDel:
                ivPhoto.setImageResource(R.drawable.icon_add_photos);
                picUrl="";
                break;
            case R.id.head_right_text_button:
                if (TextUtils.isEmpty(etCoupleBack.getText().toString())) {
                    setErrorToast("您需要给我们反馈什么信息呢？");
                    return;
                }
                showProgressDialog();
                new CommonQueationsHttp(etCoupleBack.getText().toString(),picUrl, new HttpCallBackListener<CommonQuestionsBean>() {
                    @Override
                    public void callBack(int statusCode, CommonQuestionsBean bean, String msg) {
                        setOKToast("提交成功！");
                        etCoupleBack.setText("");
                        finish();
                    }

                    @Override
                    public boolean onUserFail(int statusCode, CommonQuestionsBean bean, String msg) {
                        setErrorToast("提交失败！");
                        return true;
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
                case REQUEST_SELECT_PIC:
                    if (how == XJ) {
                        picUrl = mOutputFile.getAbsolutePath();

                    } else {
                        picUrl = FileUtils.getRealPathFromUri(CommonQuestionsActivity.this, data.getData());
                    }
                    ImageUtils.INSTANCE.loadImage(CommonQuestionsActivity.this, picUrl, ivPhoto);

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

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
