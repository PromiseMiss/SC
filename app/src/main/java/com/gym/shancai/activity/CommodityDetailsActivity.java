package com.gym.shancai.activity;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.gym.shancai.APP;
import com.gym.shancai.R;
import com.gym.shancai.bean.CreateOrderBean;
import com.gym.shancai.bean.ProductDetailBean;
import com.gym.shancai.http.AddShopCarMallHttp;
import com.gym.shancai.http.AddToCommonListHttp;
import com.gym.shancai.http.OneCreateOrderHttp;
import com.gym.shancai.http.ProductDetailHttp;
import com.gym.shancai.http.base.BaseBean;
import com.gym.shancai.http.base.HttpCallBackListener;
import com.gym.shancai.utils.CommonUtils;
import com.gym.shancai.utils.ImageUtils;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 商品详情页
 * 在 积分 普通 创客中都调用该activity
 * SpecSelectDialog.OnSelect,
 */
public class CommodityDetailsActivity extends BaseActivity implements OnItemClickListener {

    @BindView(R.id.tvCall)
    TextView tvCall;
    @BindView(R.id.tvShop)
    TextView tvShop;

    @BindView(R.id.btnBuy)
    Button btnBuy;
    @BindView(R.id.btnAddShop)
    Button btnAddShop;
    @BindView(R.id.banner)
    ConvenientBanner banner;//轮播
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.tvContent)
    TextView tvContent;
    @BindView(R.id.tvPrice)
    TextView tvPrice;

    WebView webView;
    @BindView(R.id.llSpecSelect)
    LinearLayout llSpecSelect;
    @BindView(R.id.tvSpecSelect)
    TextView tvSpecSelect;
    @BindView(R.id.llContent)
    LinearLayout llContent;
    @BindView(R.id.tvAddCommon)
    TextView tvAddCommon;
    ProductDetailBean data;
    //    SpecSelectDialog specSelectDialog;
    String goodsid;
    WeakReference wrf;

    @Override
    public int returnLayoutResID() {
        return R.layout.activity_commodity_details;
    }

    @Override
    public String setTitleInitLayout() {
        webView = new WebView(APP.getInstance());
        webView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        wrf = new WeakReference<WebView>(webView);
        llContent.addView(webView);
        return "商品详情";
    }

    @Override
    public void initView() {
        goodsid = getIntent().getStringExtra("goodsid");
        new ProductDetailHttp(false, goodsid, new HttpCallBackListener<ProductDetailBean>() {
            @Override
            public void callBack(int statusCode, ProductDetailBean bean, String msg) {
                CommodityDetailsActivity.this.data = bean;
                /**
                 * 设置收藏状态
                 */
//                if (data.getCollectid()==null||data.getCollectid().equals("0"))
//                {
//                    setCollViewState(2);
//                }else {
//                    setCollViewState(1);
//                }
//
//                arrtList = new int[1];
//                for (int i = 0; i < arrtList.length; i++) {
//                //属性置空
//                    arrtList[i] = NO_CURRDISPATH;
//                }
//                specSelectDialog = new SpecSelectDialog(CommodityDetailsActivity.this, data);
//                specSelectDialog.setOnSelect(CommodityDetailsActivity.this);
                initData();
            }

            @Override
            public boolean onUserFail(int statusCode, ProductDetailBean bean, String msg) {
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

    //-----------------------------------------------WebView--------------------------------------------------------------------------------------------
    private void loadWebView(String aaa) {

        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(aaa);
        // 添加js交互接口类，并起别名 imagelistner
        webView.addJavascriptInterface(new JavascriptInterface(this), "imagelistner");
        webView.setWebViewClient(new MyWebViewClient());
    }

    String[] imageUrls;

    /**
     * 轮播图点击
     *
     * @param position
     */
    @Override
    public void onItemClick(int position) {
        if (imageUrls == null) {
            imageUrls = new String[data.getLoop().size()];
            for (int i = 0; i < data.getLoop().size(); i++) {
                imageUrls[i] = data.getLoop().get(i).getPicture();
            }
        }
        ImageUtils.INSTANCE.lookPic(CommodityDetailsActivity.this, position, imageUrls);
    }

    // 监听
    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {

            return super.shouldOverrideUrlLoading(view, url);
        }

        @Override
        public void onPageFinished(WebView view, String url) {

            view.getSettings().setJavaScriptEnabled(true);

            super.onPageFinished(view, url);
            // html加载完成之后，添加监听图片的点击js函数
            addImageClickListner();

        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            view.getSettings().setJavaScriptEnabled(true);

            super.onPageStarted(view, url, favicon);
        }

        @Override
        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {

            super.onReceivedError(view, errorCode, description, failingUrl);

        }
    }

    // js通信接口
    public class JavascriptInterface {

        private Context context;

        public JavascriptInterface(Context context) {
            this.context = context;
        }

        @android.webkit.JavascriptInterface
        public void openImage(String img) {
            ImageUtils.INSTANCE.lookPic(context, 0, img);
        }
    }

    // 注入js函数监听
    private void addImageClickListner() {
        // 这段js函数的功能就是，遍历所有的img几点，并添加onclick函数，在还是执行的时候调用本地接口传递url过去
        webView.loadUrl("javascript:(function(){" +
                "var objs = document.getElementsByTagName(\"img\"); " +
                "for(var i=0;i<objs.length;i++)  " +
                "{"
                + "    objs[i].onclick=function()  " +
                "    {  "
                + "        window.imagelistner.openImage(this.src);  " +
                "    }  " +
                "}" +
                "})()");
    }

    //-------------------------------------------------------------------------------------------------------------------------------------------
    ArrayList<View> dispathArray;

    static final int NO_CURRDISPATH = 9999;

    //数据显示
    private void initData() {
        //轮播图初始化
        initSlideView();
        loadWebView(data.getImagetext().getImagetext());//+"?id="+goodsid
        tvTitle.setText(data.getGoodsdetail().getProdname());
        tvContent.setText(data.getGoodsdetail().getContent());
        tvPrice.setText(data.getGoodsdetail().getPrice());
    }

    private void initSlideView() {
        banner.setPages(
                new CBViewHolderCreator<NetworkImageHolderView>() {
                    @Override
                    public NetworkImageHolderView createHolder() {
                        return new NetworkImageHolderView();
                    }
                }, data.getLoop())
                //设置两个点图片作为翻页指示器，不设置则没有指示器，可以根据自己需求自行配合自己的指示器,不需要圆点指示器可用不设
                .setPageIndicator(new int[]{R.drawable.home_index_icon_slideshow, R.drawable.home_index_icon_slideshow_});
        banner.setOnItemClickListener(this);
    }


    @OnClick({R.id.tvAddCommon,R.id.tvShop, R.id.tvCall, R.id.btnBuy, R.id.btnAddShop,})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tvCall:
                CommonUtils.callPhone(this, data.getServerphone());
                break;
            case R.id.btnBuy:
                buyProduct();
                break;
            case R.id.btnAddShop:
                addToCar();
                break;
            case R.id.tvAddCommon:
                addToCommonList();
                break;
            case R.id.tvShop:
                if (!APP.isLogin()){
                    CommonUtils.startLogin(this);
                    return;
                }
                CommonUtils.startActivity(this,ShopCarActivity.class);
//                break;
//            case R.id.rl_spec:
//                if (data != null) {
//
//                    specSelectDialog.show(SpecSelectDialog.TYPE_SELECT);
//                }
//
                break;
//            case R.id.rl_coupons:
//                 //
//                CommonUtils.startWebActivity(this,"优惠券","http://www.zhuandj.com/zdjProject/interface.php/home/Index/goodluck?goodsid="+goodsid);
//                break;
        }
    }

    /**
     * 添加到常用清单
     */
    private void addToCommonList() {
        new AddToCommonListHttp(goodsid, new HttpCallBackListener<BaseBean>() {
            @Override
            public void callBack(int statusCode, BaseBean bean, String msg) {
                setOKToast("添加到清单成功");
            }

            @Override
            public boolean onUserFail(int statusCode, BaseBean bean, String msg) {
                setErrorToast("不可以重复添加哦~");
                return true;
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

    private void addToCar() {
        if (!APP.isLogin()) {
            CommonUtils.startLogin(this);
            return;
        }
//        if (reviewSelectAttrIsOK() == false) {
//            setErrorToast("请选择产品规格");
//            specSelectDialog.show(SpecSelectDialog.TYPE_BUY);
//
//            return;
//        }
//        String attrString1 = "";
//        for (int i = 0; i < arrtList.length; i++) {
//            attrString1 = data.getStandardlist().get(arrtList[i]).getId();
//        }
        showProgressDialog();
        new AddShopCarMallHttp(goodsid, new HttpCallBackListener<BaseBean>() {
            @Override
            public void callBack(int statusCode, BaseBean bean, String msg) {
                setOKToast("添加购物车成功");
            }

            @Override
            public boolean onUserFail(int statusCode, BaseBean bean, String msg) {
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

    /**
     * 买商品
     */
    private void buyProduct() {
        if (!APP.isLogin()) {
            CommonUtils.startLogin(this);
            return;
        }


//        if (reviewSelectAttrIsOK() == false) {
//            setErrorToast("请选择产品规格");
//            specSelectDialog.show(SpecSelectDialog.TYPE_BUY);
//
//            return;
//        }
//        String attrString = "";
//        for (int i = 0; i < arrtList.length; i++) {
//        attrString = data.getStandardlist().get(arrtList[0]).getId();
//        }
        showProgressDialog();
        new OneCreateOrderHttp(goodsid,  new HttpCallBackListener<CreateOrderBean>() {
            @Override
            public void callBack(int statusCode, CreateOrderBean bean, String msg) {
                CommonUtils.startConfigOrderActivity(CommodityDetailsActivity.this,  bean.getOrderid());
            }

            @Override
            public boolean onUserFail(int statusCode, CreateOrderBean bean, String msg) {
                if (statusCode == -7) {
                    setToastAutoCancel(false);
                    setErrorToast("请先设置一个默认地址");
                    CommonUtils.startActivity(CommodityDetailsActivity.this, AddressListActivity.class);
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
//    int arrtList[];//规格选择记录

//    /**
//     * 弹出框相关
//     */
//    @Override
//    public void selectClose() {
//        ergodic();
//    }
//
//    @Override
//    public void selectBuy() {
//        ergodic();
//        buyProduct();
//    }
//
//    @Override
//    public void selectAddCar() {
//        ergodic();
//        addToCar();
//    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {
            if (webView != null) {
                ViewParent parent = webView.getParent();
                if (parent != null) {
                    ((ViewGroup) parent).removeView(webView);
                }

                webView.stopLoading();
                // 退出时调用此方法，移除绑定的服务，否则某些特定系统会报错
                webView.getSettings().setJavaScriptEnabled(false);
                webView.clearHistory();
                webView.clearView();
                webView.removeAllViews();

                webView.destroy();
                webView = null;
                wrf.clear();
                wrf = null;
                llContent.removeAllViews();
                llContent = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

//    @Override
//    public void selectOk() {
//        ergodic();
//    }

    /**
     * 遍历商品规格 过滤出选定选项给arrtList
     */
//    void ergodic() {
//        String text = "";
//        for (int i = 0; i < arrtList.length; i++) {
//            for (int j = 0; j < data.getStandardlist().size(); j++) {
//                if (data.getStandardlist().get(j).isSelect()) {
//                    arrtList[i] = j;
//                    break;
//                }
//            }
//            if (arrtList[i] == NO_CURRDISPATH) {
//                return;
//            }
////            text += data.getAttrList().get(i).getAttrname() + ":" + data.getAttrList().get(i).getAttrListInfo().get(arrtList[i]).getAttrinfoname() + " ";
//        }
//        text=data.getStandardlist().get(arrtList[0]).getDescription();
//        if (text.isEmpty()) {
//            return;
//
//        }
//        llSpecSelect.setTextColor(getResources().getColor(R.color.main_theme));
//        llSpecSelect.setText(text);
//    }

    /**
     * 检查选择产品规格是否合法
     * 如果不合法 选择默认第一个规格
     *
     * @return
     */
//    boolean reviewSelectAttrIsOK() {
//        boolean select = true;
//        for (int i = 0; i < arrtList.length; i++) {
//            if (arrtList[i] == NO_CURRDISPATH) {
//                arrtList[i] = 0;
//                data.getStandardlist().get(0).setSelect(true);
//                select = false;
//            }
//        }
//        return select;
//    }

    int collStatus = 2;//2未搜藏  1 已经收藏

    /**
     * 设置收藏状态
     * //2未搜藏  1 已经收藏
     * //     * @param status
     * //
     */
//    public void setCollViewState(int status) {
//        collStatus = status;
//        switch (status) {
//            case 1:
//
//                Drawable drawable = getResources().getDrawable(R.drawable.commodity_detail_co_);
//                drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());//必须设置图片大小，否则不显示
//                tvCo.setCompoundDrawables(null, drawable, null, null);
//                break;
//            case 2:
//                Drawable drawable2 = getResources().getDrawable(R.drawable.commodity_detail_co);
//                drawable2.setBounds(0, 0, drawable2.getMinimumWidth(), drawable2.getMinimumHeight());//必须设置图片大小，否则不显示
//                tvCo.setCompoundDrawables(null, drawable2, null, null);
//                break;
//            default:
//                break;
//        }
//    }
    @Override
    protected void onResume() {
        super.onResume();
        if (!APP.isLogin())
            return;


    }

    public class NetworkImageHolderView implements Holder<ProductDetailBean.LoopBean> {
        private ImageView imageView;


        @Override
        public View createView(Context context) {
            imageView = new ImageView(context);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            return imageView;
        }

        @Override
        public void UpdateUI(Context context, int position, ProductDetailBean.LoopBean data) {
            ImageUtils.INSTANCE.loadImage(context, data.getPicture(), imageView);
        }
    }
}
