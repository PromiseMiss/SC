package com.gym.shancai.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gym.shancai.R;
import com.gym.shancai.bean.MonthlyBillDetailBean;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/9/12 0012.
 * 账单明细月账单adapter
 */

public class MonthlyBillDetailAdapter extends BaseAdapter {

    private static final int TYPE_TITLE = 0;
    private static final int TYPE_CONTENT = 1;

    private static final int TYPE_ITEM_COUNT = 2;


    Context context;
    LayoutInflater inflater;
    MonthlyBillDetailBean bean;

    public MonthlyBillDetailAdapter(MonthlyBillDetailBean bean) {
        this.bean = bean;
    }

    @Override
    public int getCount() {
        return bean.getResult().size();
    }

    @Override
    public Object getItem(int i) {
        return bean.getResult().get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (context == null) {
            context = viewGroup.getContext();
        }
        if (inflater == null) {
            inflater = LayoutInflater.from(context);
        }
//        switch (getItemViewType(i)) {
//            case TYPE_TITLE:
        ViewHolder holder;
        if (view == null) {
            view = inflater.inflate(R.layout.item_monthly_bill_title, viewGroup, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        if (i==0){
            holder.llTitle.setVisibility(View.VISIBLE);
            holder.tvDate.setText(bean.getTotal().getMonth());
            holder.tvAllMoney.setText("-¥"+bean.getTotal().getMoney()+"");
        }else {
           holder.llTitle.setVisibility(View.GONE);
        }
        holder.tvMonth.setText(bean.getResult().get(i).getOrdercreatedate());
        holder.tvPrice.setText("-¥"+bean.getResult().get(i).getTotalprice());
//                break;

//            case TYPE_CONTENT:
//                ViewHolderCon holderCon;
//                if (view == null) {
//                    view = inflater.inflate(R.layout.item_monthly_bill_content, viewGroup, false);
//                    holderCon = new ViewHolderCon(view);
//                    view.setTag(holderCon);
//                } else {
//                    holderCon = (ViewHolderCon) view.getTag();
//                }
//                TimeUtils.date2TimeStamp(data.getResult().get(i).getOrdercreatedate());
//                holderCon.tvPrice.setText(data.getResult().get(i).getTotalprice());
//                break;
//        }
        return view;
    }
//
//    @Override
//    public int getItemViewType(int position) {
//        if (position == 0)
//            return TYPE_TITLE;
//        else if (position == 1)
//            return TYPE_CONTENT;
//        return super.getItemViewType(position);
//    }
//
//    @Override
//    public int getViewTypeCount() {
//        return TYPE_ITEM_COUNT;
//    }


    static class ViewHolder {
        @BindView(R.id.tvDate)
        TextView tvDate;
        @BindView(R.id.tvAllMoney)
        TextView tvAllMoney;
        @BindView(R.id.tvMonth)
        TextView tvMonth;
        @BindView(R.id.tvPrice)
        TextView tvPrice;
        @BindView(R.id.llTitle)
        LinearLayout llTitle;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
//
//    static class ViewHolderCon {
//        @BindView(R.id.tvMonth)
//        TextView tvMonth;
//        @BindView(R.id.tvPrice)
//        TextView tvPrice;
//        @BindView(R.id.llContent)
//        LinearLayout llContent;
//
//        ViewHolderCon(View view) {
//            ButterKnife.bind(this, view);
//        }
//    }

}
