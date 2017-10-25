package com.gym.shancai.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.gym.shancai.activity.MyOrderActivity;
import com.gym.shancai.R;
import com.gym.shancai.bean.AllIndentBean;
import com.gym.shancai.customview.NoScrollListView;
import com.gym.shancai.dialog.BaseDialog;
import com.gym.shancai.http.CancelOrderHttp;
import com.gym.shancai.http.ConfirmOrderHttp;
import com.gym.shancai.http.DelOrderHttp;
import com.gym.shancai.http.TipOrderHttp;
import com.gym.shancai.http.base.HttpCallBackListener;
import com.gym.shancai.utils.CommonUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/9/2 0002.
 * 我的订单适配器
 */

public class  ShopOderItemsAdapter extends BaseAdapter {

    private AllIndentBean data;
    private LayoutInflater mInflater;
    private BaseDialog baseDialog;
    private MyOrderActivity context;
    public ShopOderItemsAdapter(AllIndentBean bean) {
        this.data = bean;

    }

    @Override
    public int getCount() {
        return data != null ? data.size() : 0;
    }

    @Override
    public Object getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        if (context==null){
            context= (MyOrderActivity) viewGroup.getContext();
        }
        if (mInflater == null) {
            mInflater = LayoutInflater.from(viewGroup.getContext());
        }
        ViewHolder holder = null;
        if (view == null) {
            view = mInflater.inflate(R.layout.item_indent_head_bottom, viewGroup, false);
            holder = new ViewHolder(view);
            if (view != null) {
                view.setTag(holder);
            }
        } else {
            holder = (ViewHolder) view.getTag();
        }
        AllIndentBean.AllIndentItemBean itemBean = data.get(i);
        holder.nlv.setAdapter(new AllOrderListCommonDetailAdapter(context,itemBean));
//        holder.llContext.removeAllViews();
//        for (int j = 0; j < 1  ; j++) {
//            View itemView = mInflater.inflate(R.layout.item_indent_content, null, false);
//            ImageView ivShopIcon = itemView.findViewById(R.id.ivShopIcon);
//            TextView tvShopName = itemView.findViewById(R.id.tvShopName);
//            TextView tvDetail = itemView.findViewById(R.id.tvDetail);
//            TextView tvShopMoney = itemView.findViewById(R.id.tvShopMoney);
//            TextView tvShopNum = itemView.findViewById(R.id.tvShopNum);
//
//            ImageUtils.INSTANCE.loadImage(viewGroup.getContext(), data.get(i).getOrderdetail().get(j).getThumbnail(), ivShopIcon);
//            tvShopName.setText(data.get(i).getOrderdetail().get(j).getProdname());
//            tvShopMoney.setText(data.get(i).getOrderdetail().get(j).getPrice());
//            tvDetail.setText(data.get(i).getOrderdetail().get(j).getContent());
//            tvShopNum.setText("x" + data.get(i).getOrderdetail().get(j).getNum());
//
//            holder.llContext.addView(view);
//        }

        holder.tvObligation.setText(data.get(i).getOrderstatus());
        holder.tvSerial.setText(data.get(i).getOrdernum());

        holder.tvFreight.setText("￥" + data.get(i).getPostprice());
        holder.tvShopMoneyNum.setText("￥" + data.get(i).getTotalprice());

        holder.tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                baseDialog = new BaseDialog(context).setTitle("确定取消订单?").setContent("取消订单后不可恢复").addCancel("我再看看").addOk(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        baseDialog.dismiss();
                        context.showProgressDialog();
                        new CancelOrderHttp( data.get(i).getOrderid(), new HttpCallBackListener() {
                            @Override
                            public void callBack(int statusCode, Object bean, String msg) {
                                context.setOKToast("订单取消成功");
                                context.requestDate();
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
                                context.dismissProgressDialog();
                            }
                        }).post();
                    }
                });
                baseDialog.show();
            }
        });
        //支付
        holder.tvPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommonUtils.startConfigOrderActivity(context,  data.get(i).getOrderid());
            }
        });
        holder.tvDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                baseDialog = new BaseDialog(context).setTitle("确认删除订单?").setContent("删除订单后不可恢复").addCancel("我再看看").addOk(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        baseDialog.dismiss();
                        context.showProgressDialog();
                        new DelOrderHttp(  data.get(i).getOrderid(), new HttpCallBackListener() {
                            @Override
                            public void callBack(int statusCode, Object bean, String msg) {
                                context.setOKToast("订单删除成功");
                                context.requestDate();
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
                                context.dismissProgressDialog();
                            }
                        }).post();
                    }
                });
                baseDialog.show();
            }
        });
        //确认收货
        holder.tvConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ss ;

                    ss = "确定收货后不可申请退货";


                baseDialog = new BaseDialog(context).setTitle("确定收货?").setContent(ss).addCancel().addOk(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        baseDialog.dismiss();
                        context.showProgressDialog();
                        new ConfirmOrderHttp( data.get(i).getOrderid(), new HttpCallBackListener() {
                            @Override
                            public void callBack(int statusCode, Object bean, String msg) {
                                context.setOKToast("订单确认");
                                context.requestDate();
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
                                context.dismissProgressDialog();
                            }
                        }).post();
                    }
                });
                baseDialog.show();
            }
        });
        holder.tvTip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.showProgressDialog();
                new TipOrderHttp( data.get(i).getOrderid(), new HttpCallBackListener() {
                    @Override
                    public void callBack(int statusCode, Object bean, String msg) {
                        context.setOKToast("已提醒卖家发货");
//                        context.reqData();
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
                        context.dismissProgressDialog();
                    }
                }).post();
            }
        });
        holder.tvCancel.setVisibility(View.GONE);
        holder.tvPay.setVisibility(View.GONE);
        holder.tvDel.setVisibility(View.GONE);
        holder.tvConfirm.setVisibility(View.GONE);
        holder.tvTip.setVisibility(View.GONE);
        switch (data.get(i).getOrderstatus())//订单状态   5为取消订单
        {
            case "待付款":
                //  1为未付款
                holder.tvCancel.setVisibility(View.VISIBLE);
                holder.tvPay.setVisibility(View.VISIBLE);
                break;
            case "待收货":
                //2为待收货
                holder.tvConfirm.setVisibility(View.VISIBLE);
                break;
            case "已完成":
                //4为申请退货
                holder.tvDel.setVisibility(View.VISIBLE);

                break;
            case "已取消":
                //5为取消订单
                holder.tvDel.setVisibility(View.VISIBLE);
                break;
            case "待发货":
                //2为待收货
                holder.tvTip.setVisibility(View.VISIBLE);
                break;
        }
        return view;
    }

    public void add(AllIndentBean.AllIndentItemBean shopOrderItems) {
        data.add(shopOrderItems);
        notifyDataSetChanged();
    }

    public void addAll(AllIndentBean shopOrderItemses) {
        data.addAll(shopOrderItemses);
        notifyDataSetChanged();
    }


    class ViewHolder {
        @BindView(R.id.tvSerial)
        TextView tvSerial;
        @BindView(R.id.tvObligation)
        TextView tvObligation;
        @BindView(R.id.tvFreight)
        TextView tvFreight;
        @BindView(R.id.tvShopMoneyNum)
        TextView tvShopMoneyNum;
//        @BindView(R.id.llContent)
//        LinearLayout llContext;
        @BindView(R.id.tvCancel)
        TextView tvCancel;
        @BindView(R.id.nlv)
        NoScrollListView nlv;
        @BindView(R.id.tvDel)
        TextView tvDel;
        @BindView(R.id.tvTip)
        TextView tvTip;
        @BindView(R.id.tvPay)
        TextView tvPay;
        @BindView(R.id.tvConfirm)
        TextView tvConfirm;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

}
