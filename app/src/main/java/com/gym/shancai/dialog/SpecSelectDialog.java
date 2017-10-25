//package com.gym.shancai.dialog;
//
//import android.app.Dialog;
//import android.content.Context;
//import android.util.DisplayMetrics;
//import android.view.Gravity;
//import android.view.View;
//import android.view.Window;
//import android.view.WindowManager;
//import android.widget.ImageView;
//import android.widget.LinearLayout;
//import android.widget.ListView;
//import android.widget.RelativeLayout;
//import android.widget.TextView;
//
//
//import com.gym.shancai.R;
//import com.gym.shancai.customview.AmountView;
//import com.gym.shancai.customview.addressselector.WheelView;
//
//import butterknife.OnClick;
//
///**
// * 创建:gym
// * 日期:2016-11-15.
// * 说明:规格选择
// * 备注:
// */
//
//public class SpecSelectDialog<T>extends Dialog implements View.OnClickListener, SpecSelectGridAdapter.OnAttrChangeListener {
//    private WheelView wV;
//
//    private TextView mTvConfirm, mTvCancel;
//    private ImageView ivclose;
//    private ImageView iv;
//    private TextView tvprice;
//    private TextView tvcontent;
//    private ListView lv;
//    private AmountView av;
//    private TextView tvbuy;
//    private TextView tvaddcar;
//    private LinearLayout llbottom;
//    private  TextView tvRedPack;
//    private TextView tvPriceUnit;
//    private RelativeLayout rlPath;
//    private TextView tvOK;
//    private T data;
//    int type;
//    /**
//     * 购买按钮类型
//     */
//   public  static final int TYPE_BUY=0;
//    /**
//     * 选择规格确定类型
//     */
//    public static final int TYPE_SELECT=1;
//    SpecSelectAdapter adapter;
//    public SpecSelectDialog(Context context, T data) {
//
//        this(context, R.style.Theme_Dialog_From_Bottom,data);
//
//    }
//
//    public SpecSelectDialog(Context context, int theme, T data) {
//
//
//        super(context, theme);
//        this.data = data;
//        this.type=type;
//        setCanceledOnTouchOutside(true);
//        setCancelable(true);
//        setContentView(R.layout.dialog_spec_selector);
//
//        this.llbottom = (LinearLayout) findViewById(R.id.ll_bottom);
//
//        this.tvOK = (TextView) findViewById(R.id.tv_ok);
//
//        this.tvaddcar = (TextView) findViewById(R.id.tv_add_car);
//        this.tvbuy = (TextView) findViewById(R.id.tv_buy);
//        this.av = (AmountView) findViewById(R.id.av);
//        this.lv = (ListView) findViewById(R.id.lv);
//        this.tvcontent = (TextView) findViewById(R.id.tv_rl_share_content);
//        this.tvprice = (TextView) findViewById(R.id.tv_price);
//        this.iv = (ImageView) findViewById(R.id.iv);
//        this.tvRedPack= (TextView) findViewById(R.id.tv_redpack);
//        this.rlPath= (RelativeLayout) findViewById(R.id.rl_path);
//        this.ivclose = (ImageView) findViewById(R.id.iv_rl_share_close);
//        this.tvPriceUnit=findViewById(R.id.tvPriceUnit);
//        ivclose.setOnClickListener(this);
//        tvbuy.setOnClickListener(this);
//        tvaddcar.setOnClickListener(this);
//        tvOK.setOnClickListener(this);
//        if (data instanceof ProductDetailBean){
//            ProductDetailBean productDetailBean= (ProductDetailBean) data;
//            tvprice.setText(productDetailBean.getPrice());
//            adapter=new SpecSelectAdapter(context,productDetailBean);
//            tvRedPack.setText(productDetailBean.getStandardlist().get(0).getCycletitle());
//            lv.setAdapter(adapter);
//            ImageUtils.INSTANCE.loadImage(context,productDetailBean.getThumbnail(),iv);
//            tvPriceUnit.setText("家宝");
//        }
//        if (data instanceof IntegralProductDetailBean){
//            IntegralProductDetailBean productDetailBean= (IntegralProductDetailBean) data;
//            tvprice.setText(productDetailBean.getGoodsinfo().getIntegral());
//            adapter=new SpecSelectAdapter(context,productDetailBean);
//            lv.setAdapter(adapter);
//            ImageUtils.INSTANCE.loadImage(context,productDetailBean.getGoodsinfo().getThumbnail(),iv);
//            rlPath.setVisibility(View.GONE);
//            tvPriceUnit.setText("积分");
//        }
//        if (data instanceof ManagerProductDetailBean){
//            ManagerProductDetailBean productDetailBean= (ManagerProductDetailBean) data;
//            tvprice.setText(productDetailBean.getStandardlist().get(0).getIntegral());
//            adapter=new SpecSelectAdapter(context,productDetailBean);
//            lv.setAdapter(adapter);
//            ImageUtils.INSTANCE.loadImage(context,productDetailBean.getLooplist().get(0).getPicurl(),iv);
//            rlPath.setVisibility(View.GONE);
//            tvPriceUnit.setText("储值金");
//        }
//        if (data instanceof ShopMoneyProductDetailBean){
//            ShopMoneyProductDetailBean productDetailBean= (ShopMoneyProductDetailBean) data;
//            tvprice.setText(productDetailBean.getStandard().get(0).getPrice());
//            adapter=new SpecSelectAdapter(context,productDetailBean);
//            lv.setAdapter(adapter);
//            ImageUtils.INSTANCE.loadImage(context,productDetailBean.getThumbnail(),iv);
//            rlPath.setVisibility(View.GONE);
//            tvPriceUnit.setText("购物金");
//
//        }
//        adapter.setOnAttrChangeListener(this);
//        Window window = getWindow();
//        WindowManager.LayoutParams lp = window.getAttributes();
//        DisplayMetrics dm = context.getResources().getDisplayMetrics();
//        lp.width = dm.widthPixels;// 让dialog的宽占满屏幕的宽
//        lp.gravity = Gravity.BOTTOM;// 出现在底部
//        window.setAttributes(lp);
//
//    }
//    OnSelect onSelect;
//
//    @OnClick({R.id.iv_rl_share_close, R.id.tv_buy, R.id.tv_add_car,R.id.tv_ok})
//    public void onClick(View view) {
//        switch (view.getId()) {
//            case R.id.iv_rl_share_close:
//                onSelect.selectClose();
//                dismiss();
//                break;
//            case R.id.tv_buy:
//                onSelect.selectBuy();
//                dismiss();
//                break;
//            case R.id.tv_add_car:
//                onSelect.selectAddCar();
//                dismiss();
//                break;
//            case R.id.tv_ok:
//                onSelect.selectOk();
//                dismiss();
//                break;
//        }
//    }
//
//    /**
//     * 属性变换
//     * @param position
//     */
//    @Override
//    public void onAttrChangeListener(int position) {
//        if (data instanceof ManagerProductDetailBean){
//            ManagerProductDetailBean productDetailBean= (ManagerProductDetailBean) data;
//            tvprice.setText(productDetailBean.getStandardlist().get(position).getIntegral());
//        }
//        if (data instanceof ProductDetailBean){
//            ProductDetailBean productDetailBean= (ProductDetailBean) data;
//            tvprice.setText(productDetailBean.getStandardlist().get(position).getPrice());
//            tvRedPack.setText(productDetailBean.getStandardlist().get(position).getCycletitle());
//        }
//        if (data instanceof IntegralProductDetailBean){
//            IntegralProductDetailBean productDetailBean= (IntegralProductDetailBean) data;
//            tvprice.setText(productDetailBean.getGoodsinfo().getStandardlist().get(position).getIntegral());
////            tvRedPack.setText(productDetailBean.getStandardlist().get(position).getCycletitle());
//        }
//        if (data instanceof ShopMoneyProductDetailBean){
//            ShopMoneyProductDetailBean productDetailBean= (ShopMoneyProductDetailBean) data;
//            tvprice.setText(productDetailBean.getStandard().get(position).getPrice());
////            tvRedPack.setText("积分:"+productDetailBean.getStandard().get(position).getIntegral());
//        }
//    }
//
//    public interface OnSelect{
//        void selectClose();
//        void selectBuy();
//        void selectAddCar();
//        void selectOk();
//    }
//
//    public void setOnSelect(OnSelect onSelect) {
//        this.onSelect = onSelect;
//    }
//
//    public void show(int type) {
//        super.show();
//        if (type==TYPE_BUY){
//            llbottom.setVisibility(View.VISIBLE);
//            tvOK.setVisibility(View.GONE);
//        }
//        if (type==TYPE_SELECT){
//            tvOK.setVisibility(View.VISIBLE);
//            llbottom.setVisibility(View.GONE);
//        }
//    }
//
//    @Override
//    public void dismiss() {
//        super.dismiss();
//        onSelect.selectClose();
//    }
//    public int getAmount(){
//       return av.getAmount();
//    }
//}
