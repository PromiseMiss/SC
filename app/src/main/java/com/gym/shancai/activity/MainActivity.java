package com.gym.shancai.activity;


import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gym.shancai.APP;
import com.gym.shancai.R;
import com.gym.shancai.dialog.BaseLoadingDialog;
import com.gym.shancai.fragment.ActivityCenterFragment;
import com.gym.shancai.fragment.ClassifyFragment;
import com.gym.shancai.fragment.HomeFragment;
import com.gym.shancai.fragment.MyFragment;
import com.gym.shancai.fragment.ShopCarFragment;
import com.gym.shancai.utils.CommonUtils;
import com.gym.shancai.utils.SharedPreferencesSetting;
import com.gym.shancai.utils.ToastUtils;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends FragmentActivity {
    public static final int INDEX_TYPE_HOME = 0;
    public static final int INDEX_TYPE_CLASSIFY = 1;
    public static final int INDEX_TYPE_ACTIVITY = 2;
    public static final int INDEX_TYPE_SHOP_CAR = 3;
    public static final int INDEX_TYPE_MY = 4;
    public static MainActivity instence;
    @BindView(R.id.ivHome)
    ImageView ivHome;
    @BindView(R.id.tvHome)
    TextView tvHome;
    @BindView(R.id.ivClassify)
    ImageView ivClassify;
    @BindView(R.id.tvClassify)
    TextView tvClassify;
    @BindView(R.id.ivActivity)
    ImageView ivActivity;
    @BindView(R.id.tvActivity)
    TextView tvActivity;
    @BindView(R.id.ivShopCar)
    ImageView ivShopCar;
    @BindView(R.id.tvShopCar)
    TextView tvShopCar;
    @BindView(R.id.ivMy)
    ImageView ivMy;
    @BindView(R.id.tvMy)
    TextView tvMy;

    private ArrayList<TextView> tvArrayList;
    private ArrayList<ImageView> ivArrayList;
    private int bottomIconRes[] = {
            R.drawable.home_index_icon_home,
            R.drawable.home_index_icon_classify,
            R.drawable.home_index_icon_activity,
            R.drawable.home_index_icon_car,
            R.drawable.home_index_icon_my
    };
    private int bottomCheckIconRes[] = {
            R.drawable.check_home,
            R.drawable.home_index_icon_classify_,
            R.drawable.home_index_icon_activity_,
            R.drawable.home_index_icon_car_,
            R.drawable.check_my
    };
    //当前fragment
    private int currFragment = -1;


    @BindView(R.id.llHome)
    LinearLayout llHome;
    @BindView(R.id.llClassify)
    LinearLayout llClassify;
    @BindView(R.id.llActivity)
    LinearLayout llActivity;
    @BindView(R.id.llShopCar)
    LinearLayout llShopCar;
    @BindView(R.id.llMy)
    LinearLayout llMy;
    @BindView(R.id.mainActivityBottomNavigationBar)
    LinearLayout mainActivityBottomNavigationBar;
    @BindView(R.id.mainActivityFrameLayout)
    FrameLayout mainActivityFrameLayout;

    HashMap<String, Fragment> fragmentMap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 防止在Android7.0以上传送一个 旧的file://格式的URI时崩溃，谷歌官方认为目标app可能不具有文件权限，
        // 会造成潜在的问题，新的方式是content：//格式的URI
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
            StrictMode.setVmPolicy(builder.build());
        }

        instence = this;
        initView();
    }

    /**
     * 初始化
     */
    private void initView() {
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        tvArrayList = new ArrayList<>();
        ivArrayList = new ArrayList<>();
        tvArrayList.add(tvHome);
        tvArrayList.add(tvClassify);
        tvArrayList.add(tvActivity);
        tvArrayList.add(tvShopCar);
        tvArrayList.add(tvMy);

        ivArrayList.add(ivHome);
        ivArrayList.add(ivClassify);
        ivArrayList.add(ivActivity);
        ivArrayList.add(ivShopCar);
        ivArrayList.add(ivMy);


        setFragment(INDEX_TYPE_HOME);
    }

    @OnClick({R.id.llHome, R.id.llClassify, R.id.llActivity, R.id.llShopCar, R.id.llMy})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.llHome:

                setFragment(INDEX_TYPE_HOME);
                break;
            case R.id.llClassify:
                setFragment(INDEX_TYPE_CLASSIFY);
                break;
            case R.id.llActivity:
                setFragment(INDEX_TYPE_ACTIVITY);
                break;
            case R.id.llShopCar:
                if (!APP.isLogin()) {
                    CommonUtils.startActivity(this, LoginActivity.class);
                    return;
                }
                setFragment(INDEX_TYPE_SHOP_CAR);
                break;
            case R.id.llMy:
                if (!APP.isLogin()) {
                    CommonUtils.startActivity(this, LoginActivity.class);
                    return;
                }
                setFragment(INDEX_TYPE_MY);
                break;
        }
    }

    /**
     * 切换fragment
     *
     * @param fragmentId
     */
    public void setFragment(int fragmentId) {
        if (currFragment == fragmentId) {
            return;
        }
        currFragment = fragmentId;
        if (fragmentMap == null) {
            fragmentMap = new HashMap<>();
        }
        for (TextView textView : tvArrayList) {
            textView.setTextColor(ContextCompat.getColor(this, R.color.no_check_font_color));
        }
        tvArrayList.get(fragmentId).setTextColor(ContextCompat.getColor(this, R.color.check_font_color));
        for (int i = 0; i < ivArrayList.size(); i++) {
            ivArrayList.get(i).setImageResource(bottomIconRes[i]);
        }
        ivArrayList.get(fragmentId).setImageResource(bottomCheckIconRes[fragmentId]);
        Fragment mFragment = null;
        switch (fragmentId) {
            case INDEX_TYPE_HOME:
                mFragment = fragmentMap.get(HomeFragment.class.getName());
                break;
            case INDEX_TYPE_CLASSIFY:
                mFragment = fragmentMap.get(ClassifyFragment.class.getName());
                break;
            case INDEX_TYPE_ACTIVITY:
                mFragment = fragmentMap.get(ActivityCenterFragment.class.getName());
                break;
            case INDEX_TYPE_SHOP_CAR:
                mFragment = fragmentMap.get(ShopCarFragment.class.getName());
                break;
            case INDEX_TYPE_MY:
                mFragment = fragmentMap.get(MyFragment.class.getName());
                break;
        }
        if (mFragment == null) {
            switch (fragmentId) {
                case INDEX_TYPE_HOME:
                    fragmentMap.put(HomeFragment.class.getName(), new HomeFragment());
                    mFragment = fragmentMap.get(HomeFragment.class.getName());
                    break;
                case INDEX_TYPE_CLASSIFY:
                    fragmentMap.put(ClassifyFragment.class.getName(), new ClassifyFragment());
                    mFragment = fragmentMap.get(ClassifyFragment.class.getName());
                    break;
                case INDEX_TYPE_ACTIVITY:
                    fragmentMap.put(ActivityCenterFragment.class.getName(), new ActivityCenterFragment());
                    mFragment = fragmentMap.get(ActivityCenterFragment.class.getName());
                    break;
                case INDEX_TYPE_SHOP_CAR:
                    fragmentMap.put(ShopCarFragment.class.getName(), new ShopCarFragment());
                    mFragment = fragmentMap.get(ShopCarFragment.class.getName());
                    break;
                case INDEX_TYPE_MY:
                    fragmentMap.put(MyFragment.class.getName(), new MyFragment());
                    mFragment = fragmentMap.get(MyFragment.class.getName());
                    break;
            }
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.mainActivityFrameLayout, mFragment).commitAllowingStateLoss();
    }

    public static MainActivity getInstence() {
        return instence;
    }

    private BaseLoadingDialog progressDialog;

    public BaseLoadingDialog showProgressDialog() {
        return showProgressDialog("");
    }

    public BaseLoadingDialog showProgressDialog(String content) {
        if (progressDialog == null) {
            progressDialog = new BaseLoadingDialog(this).setContent(content);
        }
        progressDialog.setContent(content);

        progressDialog.setCancelable(true);
        if (!progressDialog.isShowing()) {
            progressDialog.show();
        }
        return progressDialog;
    }

    public void dismissProgressDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    /**
     * 哭脸吐司
     *
     * @param content 内容
     */
    public static void setErrorToast(String content) {
        ToastUtils.SetToast(R.drawable.base_toast_face_sad, content, 1000);
    }

    /**
     * 笑脸吐司
     *
     * @param content 文字
     */
    public static void setOKToast(String content) {
        ToastUtils.SetToast(R.drawable.base_toast_face_ok, content, 1000);

    }

    public void logout() {
        setFragment(INDEX_TYPE_HOME);
        SharedPreferencesSetting.getInstance().clearnLoginInfo();
    }


}