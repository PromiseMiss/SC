package com.gym.shancai.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.gym.shancai.R;
import com.gym.shancai.bean.DetailBean;
import com.gym.shancai.utils.TimeUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/9/11 0011.
 * 明细
 */

public class DetailAdapter extends BaseAdapter {

    DetailBean bean;
    Context context;
    LayoutInflater inflater;


    public DetailAdapter(DetailBean bean) {
        this.bean = bean;
    }

    @Override
    public int getCount() {
        return bean != null ? bean.getTradedetail().size() : 0;
    }

    @Override
    public Object getItem(int i) {
        return bean.getTradedetail().get(i);
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
        ViewHolder holder;
        if (view == null) {
            view = inflater.inflate(R.layout.item_detail_list, viewGroup, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        switch (bean.getTradedetail().get(i).getPaytype()) {
            case "1":
                holder.tvRefund.setText("余额支付");
                break;
            case "4":
                holder.tvRefund.setText("退款");
                break;
            case "5":
                holder.tvRefund.setText("欠款");
                break;
        }


//        holder.tvRefund.setText(data.getTradedetail().get(i).getPaytype());
        TimeUtils.date2TimeStamp(bean.getTradedetail().get(i).getComtime());
        holder.tvGiveMoney.setText(bean.getTradedetail().get(i).getGivemoney());

        return view;
    }

    static class ViewHolder {
        @BindView(R.id.tvRefund)
        TextView tvRefund;
        @BindView(R.id.tvComTime)
        TextView tvComTime;
        @BindView(R.id.tvGiveMoney)
        TextView tvGiveMoney;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
