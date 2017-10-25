package com.gym.shancai.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.gym.shancai.R;
import com.gym.shancai.activity.ConfirmOrderActivity;
import com.gym.shancai.bean.ConfigOrderBean;
import com.gym.shancai.utils.ImageUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

//import com.gym.zdj.http.ModiSendTypeHttp;

/**
 * 确认订单
 */
public class ConfigCommonOrderAdapter extends BaseAdapter {
    private ConfirmOrderActivity context;
    private ConfigOrderBean data;
    private LayoutInflater inflater;

    public ConfigCommonOrderAdapter(ConfirmOrderActivity context, ConfigOrderBean bean) {
        this.context = context;
        this.data = bean;
        inflater = LayoutInflater.from(context);
    }


    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        if (data == null)
            return 0;
        return data.getGoodsmessage().size();
    }

    @Override
    public Object getItem(int arg0) {
        // TODO Auto-generated method stub
        return data.getGoodsmessage().get(arg0);
    }

    @Override
    public long getItemId(int arg0) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        final ViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_confirm_order, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
//        holder.tvOrderNum.setText("订单编号:"+con);
        holder.tvTitle.setText(data.getGoodsmessage().get(position).getProdname());
        holder.tvLitterTitle.setText(data.getGoodsmessage().get(position).getContent());
        holder.tvPrice.setText("￥"+data.getGoodsmessage().get(position).getPrice());
        holder.tvInventory.setText("x"+data.getGoodsmessage().get(position).getNum());
        ImageUtils.INSTANCE.loadImage(context,data.getGoodsmessage().get(position).getThumbnail(),holder.ivHeadIcon);

        return convertView;
    }
    static class ViewHolder {
        @BindView(R.id.ivHeadIcon)
        ImageView ivHeadIcon;
        @BindView(R.id.tvTitle)
        TextView tvTitle;
        @BindView(R.id.tvLitterTitle)
        TextView tvLitterTitle;
        @BindView(R.id.tvPrice)
        TextView tvPrice;
        @BindView(R.id.tvInventory)
        TextView tvInventory;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
