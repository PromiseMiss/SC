package com.gym.shancai.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.gym.shancai.R;
import com.gym.shancai.activity.MyOrderActivity;
import com.gym.shancai.bean.AllIndentBean;
import com.gym.shancai.utils.ImageUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 所有订单详情
 */
public class AllOrderListCommonDetailAdapter extends BaseAdapter {
    private MyOrderActivity context;
    private AllIndentBean.AllIndentItemBean data;
    private LayoutInflater inflater;

    public AllOrderListCommonDetailAdapter(MyOrderActivity context, AllIndentBean.AllIndentItemBean itemBean) {
        this.context = context;
        this.data = itemBean;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
//        if (data == null)
//            return 0;

        return data.getOrderdetail().size();

    }

    @Override
    public Object getItem(int arg0) {
        // TODO Auto-generated method stub

        return data.getOrderdetail().size();

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
            convertView = inflater.inflate(R.layout.item_indent_content, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        ImageUtils.INSTANCE.loadImage(parent.getContext(), data.getOrderdetail().get(position).getThumbnail(),holder.ivShopIcon);
        holder.tvShopName.setText(data.getOrderdetail().get(position).getProdname());
        holder.tvShopMoney.setText(data.getOrderdetail().get(position).getPrice());
        holder.tvDetail.setText(data.getOrderdetail().get(position).getContent());
        holder.tvShopNum.setText("x" + data.getOrderdetail().get(position).getNum());

        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.ivShopIcon)
        ImageView ivShopIcon;
        @BindView(R.id.tvShopName)
        TextView tvShopName;
        @BindView(R.id.tvDetail)
        TextView tvDetail;
        @BindView(R.id.tvShopMoney)
        TextView tvShopMoney;
        @BindView(R.id.tvShopNum)
        TextView tvShopNum;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);        }
    }
}
