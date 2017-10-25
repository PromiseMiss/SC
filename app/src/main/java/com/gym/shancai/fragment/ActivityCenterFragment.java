package com.gym.shancai.fragment;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gym.shancai.R;
import com.gym.shancai.adapter.ActivityCenterPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2017/8/22 0022.
 * 活动中心
 */

public class ActivityCenterFragment extends Fragment {

    View v;
    @BindView(R.id.vp)
    ViewPager vp;
    @BindView(R.id.llCenter)
    LinearLayout llCenter;

    ActivityCenterPagerAdapter pagerAdapter;
    FragmentManager manager;
    List<Fragment> list;

    ActivityDayDealsFragment dealsFragment;
    ActivityOriginFragment originFragment;
    ActivityBrandFragment brandFragment;
    @BindView(R.id.tvFirst)
    TextView tvFirst;
    @BindView(R.id.tvSecond)
    TextView tvSecond;
    @BindView(R.id.tvThirdly)
    TextView tvThirdly;
    @BindView(R.id.ivSlide)
    ImageView ivSlide;

    private int index;
    private int imgleth;
    private int offset;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_activity_center, container, false);
         ButterKnife.bind(this, v);
        initData();
        return v;
    }
    public void initData() {
        setImage();
        list = new ArrayList<>();
        dealsFragment = new ActivityDayDealsFragment();
        originFragment = new ActivityOriginFragment();
        brandFragment = new ActivityBrandFragment();
        list.add(dealsFragment);
        list.add(brandFragment);
        list.add(originFragment);
        manager = getChildFragmentManager();// 因为是Fragment中嵌套Fragment，所以要getChildFragmentManager
        pagerAdapter = new ActivityCenterPagerAdapter(manager, list);
        vp.setAdapter(pagerAdapter);
        vp.setCurrentItem(0);
        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            int one = offset * 2 + imgleth;//相邻页面的偏移量


            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                Animation animation = new TranslateAnimation(index * one + offset
                        , position * one + offset, 0, 0);
                index = position;// 当前页面跟着变
                animation.setFillAfter(true);
                animation.setDuration(200);// 动画持续0.2秒
                ivSlide.startAnimation(animation);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    @OnClick({R.id.tvFirst, R.id.tvSecond, R.id.tvThirdly})
    public void OnClick(View v) {
        switch (v.getId()) {
            case R.id.tvFirst:
                vp.setCurrentItem(0);
                break;
            case R.id.tvSecond:
                vp.setCurrentItem(1);
                break;
            case R.id.tvThirdly:
                vp.setCurrentItem(2);
                break;
        }
    }

    public void setImage() {
        imgleth = BitmapFactory.decodeResource(getResources(), R.drawable.icon_slide_).getWidth();
        DisplayMetrics dm = new DisplayMetrics();
        dm = getResources().getDisplayMetrics();
//        WindowManager windowManager = (WindowManager) getResources().getDisplayMetrics();
//        windowManager.getDefaultDisplay().getMetrics(dm);

        int count = dm.widthPixels;
        offset = (count / 3 - imgleth) / 2; // 计算偏移量
        // 平移动画
        Animation animation = new TranslateAnimation(0, offset, 0, 0);
        animation.setFillAfter(true);
        animation.setDuration(200);
        ivSlide.setAnimation(animation);
    }
}
