package com.gym.shancai.utils;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Adapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.gym.shancai.APP;
import com.gym.shancai.activity.BaseWebActivity;
import com.gym.shancai.activity.CommodityDetailsActivity;
import com.gym.shancai.activity.CrazyCitySonActivity;
import com.gym.shancai.activity.ConfirmOrderActivity;
import com.gym.shancai.activity.LoginActivity;
import com.gym.shancai.activity.StoreInfoActivity;
import com.gym.shancai.bean.WxRechargeBean;
import com.gym.shancai.dialog.BaseDialog;
import com.tencent.mm.opensdk.constants.Build;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


//import com.gym.zdj.activity.ForgetPassword;

public class CommonUtils {
    /**
     * 无参数打开一个activi
     *
     * @author guoyi
     * @title 修改跳转页的标题
     */
    public static <T> void startActivity(Context context, Class<T> clazz, String... title) {
        Intent intent = new Intent(context, clazz);
        if (title != null) {
            intent.putExtra("titleString", title);
        }
        context.startActivity(intent);
        intent = null;
    }

    /**
     * 参数打开一个activi
     *
     * @author guoyi
     * @params 参数
     */
    public static <T> void startActivity(Context context, Class<T> clazz, HashMap<String, String> params) {
        Intent intent = new Intent(context, clazz);
        Iterator iterator = params.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry entry = (Map.Entry) iterator.next();
            intent.putExtra((String) entry.getKey(), (String) entry.getValue());
        }

        context.startActivity(intent);
        intent = null;
    }


    /**
     * 清空通知栏
     *
     * @param context
     */
    public void clearnAllNotify(Context context) {
        /** Notification管理 */
        NotificationManager mNotificationManager;
        mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.cancelAll();
    }

    /**
     * 启动注册填写店铺信息页面
     *
     * @param context
     * @String userId
     */
    public static void startStoreInfoWithUserId(Context context, String userId) {
        Intent intent = new Intent(context, StoreInfoActivity.class);
        intent.putExtra("userId", userId);
        context.startActivity(intent);
    }

    /**
     * 启动狂欢城子页面
     *
     * @param context
     * @String userId
     */
    public static void startCrazyCitySonActivity(Context context, int type) {
        Intent intent = new Intent(context, CrazyCitySonActivity.class);
        intent.putExtra("type", type);
        context.startActivity(intent);
    }


    /**
     * 启动内部浏览器
     *
     * @param context
     * @param title
     * @param url
     */
    public static void startWebActivity(Context context, String title, String url) {
        Intent intent = new Intent(context, BaseWebActivity.class);
        intent.putExtra("title", title);
        intent.putExtra("url", url);
        context.startActivity(intent);
    }

    public static void startLogin(Context context) {
        startActivity(context, LoginActivity.class);
    }

    /**
     * 解决ScrollView嵌套ListView只显示一行的问题
     *
     * @param listView
     */
    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }

        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }


//    public static void startLogin(Context context) {
//        startActivity(context, LoginActivity.class);
//    }

    /**
     * 获取版本
     *
     * @return 当前应用的版本
     */
    public static String getVersion() {
        try {
            PackageManager manager = APP.instance.getPackageManager();
            PackageInfo info = manager.getPackageInfo(APP.instance.getPackageName(), 0);
            String version = info.versionName;
            return version;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * 获取版本数字
     *
     * @return 当前应用的版本号
     */
    public static int getVersionCode() {
        try {
            PackageManager manager = APP.instance.getPackageManager();
            PackageInfo info = manager.getPackageInfo(APP.instance.getPackageName(), 0);
            return info.versionCode;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static void callPhone(final Context context, final String phonenumber) {
        final BaseDialog myDialog = new BaseDialog(context);
        myDialog.setCanceledOnTouchOutside(true);

        myDialog.addCancel().addButton("拨号", new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Uri uri = Uri.parse("tel:" + phonenumber);
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_CALL);
                intent.setData(uri);
                context.startActivity(intent);
                myDialog.dismiss();
            }
        }).setContent("客服工作时间：周一至周五早9点至晚5点\n客服电话:" + phonenumber);
        myDialog.show();
    }


    /**
     * 判断最后listView中最后一个item是否完全显示出来
     *
     * @return
     */
    public static boolean isLastItemVisible(ListView listView) {
        final Adapter adapter = listView.getAdapter();

        if (null == adapter || adapter.isEmpty()) {
            return true;
        }

        final int lastItemPosition = adapter.getCount() - 1;
        final int lastVisiblePosition = listView.getLastVisiblePosition();


        if (lastVisiblePosition >= lastItemPosition - 1) {
            final int childIndex = lastVisiblePosition - listView.getFirstVisiblePosition();
            final int childCount = listView.getChildCount();
            final int index = Math.min(childIndex, childCount - 1);
            final View lastVisibleChild = listView.getChildAt(index);
            if (lastVisibleChild != null) {
                return lastVisibleChild.getBottom() <= listView.getBottom();
            }
        }

        return false;
    }


    /**
     * root权限吓 静默安装
     *
     * @return
     */
    public static boolean isRoot() {
        boolean result = false;
        Process process = null;
        OutputStream os = null;
        BufferedReader br = null;
        StringBuffer sb = null;
        try {
            //成功代表root
            process = Runtime.getRuntime().exec("su");
            os = process.getOutputStream();
            os.write("pm install -r".getBytes());
            os.flush();
            os.write("exit\n".getBytes());
            process.waitFor();
            br = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            sb = new StringBuffer();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            if (!sb.toString().contains("Failure")) {
                result = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            result = false;
        } finally {
            try {
                if (os != null) {
                    os.close();
                }
                if (br != null) {
                    br.close();
                }
            } catch (Exception e) {
                os = null;
                br = null;
                process.destroy();
            }

        }

        return result;
    }

    /**
     * 改变状态栏颜色
     *
     * @param window
     * @param color
     */
    public static void changeStateBarColor(Window window, int color) {

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
//        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
//                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(color);   //这里动态修改颜色
        }
    }

    /**
     * 改变textview指定位置文字颜色(前景色)
     *
     * @param textView
     * @param start
     * @param end
     * @param color
     */
    public static void changeTextColor(TextView textView, int start, int end, int color) {

        SpannableStringBuilder builder = new SpannableStringBuilder(textView.getText().toString());
//ForegroundColorSpan 为文字前景色，BackgroundColorSpan为文字背景色
        ForegroundColorSpan redSpan = new ForegroundColorSpan(color);
        builder.setSpan(redSpan, 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.setText(builder);
    }

    /**
     * 打开详情页面
     *
     * @author guoyi
     * @title 修改跳转页的标题
     */
    public static  void startDetailActivity(Context context,  String goodsid)  {
        Intent intent;
        intent = new Intent(context, CommodityDetailsActivity.class);
        intent.putExtra("goodsid", goodsid);
        context.startActivity(intent);
    }
    /**
     * 打开一个订单确认页面
     *
     * @author guoyi
     * @title 修改跳转页的标题
     */
    public static void startConfigOrderActivity(Context context, String ordertext) {
        Intent intent;
        intent = new Intent(context, ConfirmOrderActivity.class);
        intent.putExtra("ordertext", ordertext);
        context.startActivity(intent);
    }

    /**
     *
     * @param context
     * @param bean
     */
    public static void WxRecharge(Context context, WxRechargeBean bean) {

        IWXAPI api = WXAPIFactory.createWXAPI(context,null);
        api.registerApp(bean.getAppid());
        if (api.getWXAppSupportAPI() < Build.PAY_SUPPORTED_SDK_INT) {
            Toast.makeText(context, "微信版本低", Toast.LENGTH_SHORT).show();
        }
        Toast.makeText(context, "获取订单中...", Toast.LENGTH_SHORT).show();
        PayReq req = new PayReq();
        req.appId = bean.getAppid();
        req.partnerId = bean.getPartnerid();
        req.prepayId = bean.getPrepayid();
        req.nonceStr = bean.getNoncestr();
        req.timeStamp = bean.getTimestamp()+"";
        req.packageValue = "Sign=WXPay";
        req.sign = bean.getSign();
        req.extData = "app data"; // optional
//                Toast.makeText(context, "正常调起支付", Toast.LENGTH_SHORT).show();
        // 在支付之前，如果应用没有注册到微信，应该先调用IWXMsg.registerApp将应用注册到微信
        api.sendReq(req);


    }


}