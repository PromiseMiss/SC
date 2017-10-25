package com.gym.shancai.activity;

import android.graphics.PixelFormat;
import android.support.v4.content.ContextCompat;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.ProgressBar;


import com.gym.shancai.APP;
import com.gym.shancai.R;
import com.gym.shancai.utils.CommonUtils;
import com.gym.shancai.utils.Logger;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;


public class BaseWebActivity extends BaseActivity {
    private WebView webView;
    private LinearLayout llRoot;
    private ProgressBar progressBar;
    WeakReference wrf;

    @Override
    public int returnLayoutResID() {
        return R.layout.base_activity_web_browse;
    }

    @Override
    public String setTitleInitLayout() {
        CommonUtils.changeStateBarColor(getWindow(), ContextCompat.getColor(this,R.color.main_theme));
        llRoot = (LinearLayout) findViewById(R.id.ll_root);
        progressBar= (ProgressBar) findViewById(R.id.progress);
        webView = new WebView(APP.getInstance());
        webView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        wrf = new WeakReference<WebView>(webView);
        llRoot.addView(webView);
        setRightButton(R.drawable.base_web_reload);


        initWebView();
        return getIntent().getStringExtra("title");
    }

    public void reload(View v) {
        webView.reload();
    }

    private void initWebView() {
        //兼容腾讯X5
        getWindow().setFormat(PixelFormat.TRANSLUCENT);
//        webView.setLayerType();
        webView.setDrawingCacheEnabled(true);

        String ua = webView.getSettings().getUserAgentString();
        Logger.e("~~~","aaaaa"+ua);
//        webView.getSettings().setUserAgentString("Mozilla/5.0 (iPhone; CPU iPhone OS 10_3_2 like Mac OS X) AppleWebKit/603.2.2 (KHTML, like Gecko) Mobile/14F5075a QQ/6.7.1.416 V1_IPH_SQ_6.7.1_1_APP_A Pixel/1080 Core/UIWebView NetType/WIFI");
        webView.loadUrl(getIntent().getStringExtra("url"));
        //覆盖WebView默认使用第三方或系统默认浏览器打开网页的行为，使网页用WebView打开
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // TODO Auto-generated method stub
                //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                view.loadUrl(url);
                return true;
            }
        });
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        webView.setWebChromeClient(new WebChromeClient() {

            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                // TODO Auto-generated method stub
                progressBar .setProgress(newProgress);
                if (newProgress == 100) {
                    // 网页加载完成


                } else {
                    // 加载中

                }

            }
        });
    }

    @Override
    public void back(View view) {

        if (webView.canGoBack()) {
            webView.goBack();//返回上一页面
        } else {
            finish();
        }
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (webView.canGoBack()) {
                webView.goBack();//返回上一页面
                return true;
            } else {
                finish();
                //System.exit(0);//退出程序
            }
        }
        return super.onKeyDown(keyCode, event);
    }

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
                llRoot.removeAllViews();
                llRoot = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void close(View view){
        finish();
    }
}
