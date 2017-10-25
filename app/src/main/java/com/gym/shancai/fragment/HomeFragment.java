package com.gym.shancai.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.gym.shancai.R;
import com.gym.shancai.activity.ActivityImageActivity;
import com.gym.shancai.activity.CrazyCityActivity;
import com.gym.shancai.activity.MainActivity;
import com.gym.shancai.activity.NewProductActivity;
import com.gym.shancai.activity.RankingListActivity;
import com.gym.shancai.activity.SearchActivity;
import com.gym.shancai.activity.ShanCaiInfoActivity;
import com.gym.shancai.adapter.HomeAdapter;
import com.gym.shancai.bean.HomeBean;
import com.gym.shancai.http.HomeHttp;
import com.gym.shancai.http.base.HttpCallBackListener;
import com.gym.shancai.utils.CommonUtils;
import com.gym.shancai.utils.ImageUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2017/8/22 0022.
 * 首页——hjd
 */

public class HomeFragment extends Fragment implements View.OnClickListener {

    @BindView(R.id.etSearch)
    EditText etSearch;
    @BindView(R.id.lyRefresh)
    SwipeRefreshLayout swipeRefreshLayout;
    private long SLIDE_DELY = 3000;
    View rootView;
    HomeBean data;
    Unbinder unbinder;
    View headView;
    TimerTask task;
    @BindView(R.id.lv)
    ListView lv;
    ImageView iv;
    String id, title;
    ConvenientBanner banner;
    TextView tvNewProduct, tvCrazyCity, tvArrange, tvShanCaiInfo;
    HashMap params;

    ArrayList<String> titleList = new ArrayList<String>();
    private boolean inittop = true;
    LayoutInflater inflater;

    //号外相关
    private LinearLayout notice_parent_ll;
    private ViewFlipper notice_vf;
    private int mCurrPos;

    HomeAdapter adapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_home_listview, container, false);

            ButterKnife.bind(this, rootView);
            this.inflater = inflater;
            initView();
            initData();
        }
        return rootView;
    }

    private void initView() {
        unbinder = ButterKnife.bind(this, rootView);
        headView = LayoutInflater.from(MainActivity.getInstence()).inflate(R.layout.head_home, null);

        etSearch.setFocusable(false);
        etSearch.setEnabled(true);


        banner = headView.findViewById(R.id.banner);
        tvNewProduct = headView.findViewById(R.id.tvNewProduct);
        tvCrazyCity = headView.findViewById(R.id.tvCrazyCity);
        tvArrange = headView.findViewById(R.id.tvArrange);
        tvShanCaiInfo = headView.findViewById(R.id.tvShanCaiInfo);
        iv = headView.findViewById(R.id.iv);

        lv.addHeaderView(headView);
    }

    public void initData() {
        tvNewProduct.setOnClickListener(this);
        tvCrazyCity.setOnClickListener(this);
        tvArrange.setOnClickListener(this);
        tvShanCaiInfo.setOnClickListener(this);
        iv.setOnClickListener(this);


        params = new HashMap();
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                requestData();
            }
        });
        swipeRefreshLayout.setRefreshing(true);
        requestData();
    }


    private void requestData() {
//        MainActivity.getInstence().showProgressDialog();
        new HomeHttp(new HttpCallBackListener<HomeBean>() {
            @Override
            public void callBack(int statusCode, HomeBean bean, String msg) {
                data = bean;
                ImageUtils.INSTANCE.loadImage(MainActivity.getInstence(), data.getActivity().getApicture(), iv);
                id = data.getActivity().getAid();
                title = data.getActivity().getTitle();

                adapter = new HomeAdapter(data);
                lv.setAdapter(adapter);
                initDataSlideView();
                initTop();
            }

            @Override
            public boolean onUserFail(int statusCode, HomeBean bean, String msg) {
                return true;
            }

            @Override
            public boolean onNetError(int statusCode, String msg) {
                return false;
            }

            @Override
            public void onComplete(boolean isError) {
                swipeRefreshLayout.setRefreshing(false);
//                MainActivity.getInstence().dismissProgressDialog();
            }
        }).post();
    }

    /**
     * 头条数据获取 初始化
     * 最后调用了 我的消息初始化
     */
    private void initTop() {

        titleList.clear();
        for (int i = 0; i < data.getFontLoop().size(); i++) {

//            titleList.add("[" + data.getFontLoop().get(i).getFontcontent() + "]" +data.getFontLoop().get(i).getNewcontent());
            titleList.add(data.getFontLoop().get(i).getFontcontent());
        }
        if (inittop) {
            initRollNotice(headView);
            inittop = false;
        }
//        reqMyMessage();

    }

    private void initRollNotice(View headview) {
        FrameLayout main_notice = (FrameLayout) headview.findViewById(R.id.main_notice);
        notice_parent_ll = (LinearLayout) inflater.inflate(R.layout.layout_notice, null);
        notice_vf = ((ViewFlipper) this.notice_parent_ll.findViewById(R.id.homepage_notice_vf));
        notice_vf.setOnClickListener(this);
        main_notice.addView(notice_parent_ll);
        task = new TimerTask() {
            @Override
            public void run() {
                if (getActivity() == null) {
//                    task.cancel();
//                    task = null;
                    return;
                }
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        moveNext();
                    }
                });

            }
        };
        Timer timer = new Timer();
        timer.schedule(task, 0, 4000);
    }

    private void moveNext() {
        if (titleList.size() == 0) {
            return;
        }
        View noticeView = inflater.inflate(R.layout.notice_item, null);
        TextView notice_tv = (TextView) noticeView.findViewById(R.id.notice_tv);

        if (mCurrPos >= titleList.size())
            mCurrPos = 0;
        notice_tv.setText(titleList.get(mCurrPos));

        notice_tv.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
//                CommonUtils.startActivity(getActivity(), NewsListActivity.class);
            }
        });
        if (notice_vf.getChildCount() > 1) {
            notice_vf.removeViewAt(0);
        }
        notice_vf.addView(noticeView, notice_vf.getChildCount());
        mCurrPos++;

        this.notice_vf.setInAnimation(MainActivity.getInstence(), R.anim.in_bottomtop);
        this.notice_vf.setOutAnimation(MainActivity.getInstence(), R.anim.out_bottomtop);
        this.notice_vf.showNext();
    }

    /**
     * 对搜索框进行监听
     *
     * @param rootView
     */
    @OnClick({R.id.etSearch})
    public void OnClick(View rootView) {
        switch (rootView.getId()) {
            case R.id.etSearch:

                CommonUtils.startActivity(MainActivity.getInstence(), SearchActivity.class);
                break;
        }
    }

    /**
     * 轮播图初始化
     */
    private void initDataSlideView() {

        // TODO Auto-generated method stub
        //自定义你的Holder，实现更多复杂的界面，不一定是图片翻页，其他任何控件翻页亦可。
        banner.setPages(
                new CBViewHolderCreator<HomeFragmentHolderView>() {
                    @Override
                    public HomeFragmentHolderView createHolder() {
                        return new HomeFragmentHolderView();
                    }
                }, data.getPictureLoop())
                //设置两个点图片作为翻页指示器，不设置则没有指示器，可以根据自己需求自行配合自己的指示器,不需要圆点指示器可用不设
                .setPageIndicator(new int[]{R.drawable.home_index_icon_slideshow, R.drawable.home_index_icon_slideshow_})
                //设置指示器的方向
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL);
        //设置翻页的效果，不需要翻页效果可用不设
        //.setPageTransformer(Transformer.DefaultTransformer);    集成特效之后会有白屏现象，新版已经分离，如果要集成特效的例子可以看Demo的点击响应。
//        MyConvenientBanner.setManualPageable(false);//设置不能手动影响
//                banner.startTurning(5000);


    }

    /**
     * 新品上市之类的点击事件处理
     *
     * @param view
     */
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.homepage_notice_vf:
//                CommonUtils.startActivity(getActivity(), NewsListActivity.class);
                break;
            case R.id.tvNewProduct:
                CommonUtils.startActivity(MainActivity.getInstence(), NewProductActivity.class);
                break;
            case R.id.tvCrazyCity:
                CommonUtils.startActivity(MainActivity.getInstence(), CrazyCityActivity.class);
                break;
            case R.id.tvArrange:
                CommonUtils.startActivity(MainActivity.getInstence(), RankingListActivity.class);
                break;
            case R.id.tvShanCaiInfo:
                CommonUtils.startActivity(MainActivity.getInstence(), ShanCaiInfoActivity.class);
                break;
            case R.id.iv:
                params.put("key", id);
                params.put("type", title);
                CommonUtils.startActivity(MainActivity.getInstence(), ActivityImageActivity.class, params);
                break;

        }
    }

    public class HomeFragmentHolderView implements Holder<HomeBean.PictureLoopBean> {
        private ImageView imageView;


        @Override
        public View createView(Context context) {
            imageView = new ImageView(context);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            return imageView;
        }

        @Override
        public void UpdateUI(Context context, int position, HomeBean.PictureLoopBean data) {
            ImageUtils.INSTANCE.loadImage(context, data.getPicurl(), imageView);

        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (banner != null)
            banner.startTurning(SLIDE_DELY);
//        if (banner != null) {
//            reqMyMessage();
//        }
    }
}
