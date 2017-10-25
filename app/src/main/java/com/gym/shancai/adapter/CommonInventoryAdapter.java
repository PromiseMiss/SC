package com.gym.shancai.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.gym.shancai.activity.CommonInventoryActivity;
import com.gym.shancai.R;
import com.gym.shancai.bean.CommonInventoryBean;
import com.gym.shancai.utils.CommonUtils;
import com.gym.shancai.utils.ImageUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/9/11 0011.
 * 常用清单适配器
 */

public class CommonInventoryAdapter extends BaseAdapter {

    CommonInventoryActivity context;
    LayoutInflater inflater;
    CommonInventoryBean data;


    public CommonInventoryAdapter(CommonInventoryActivity context, CommonInventoryBean bean) {
        this.context = context;
        this.data = bean;
        notifyDataSetChanged();
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
        if (inflater == null) {
            inflater = LayoutInflater.from(context);
        }
        ViewHolder holder;
        if (view == null) {
            view = inflater.inflate(R.layout.item_inventory_list, viewGroup, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        ImageUtils.INSTANCE.loadImage(context, data.get(i).getThumbnail(), holder.ivIcon);
        holder.tvTitle.setText(data.get(i).getProdname());
        holder.tvPrice.setText("￥" + data.get(i).getPrice());
        holder.tvRepertory.setText("库存：" + data.get(i).getLeftnum());
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CommonUtils.startDetailActivity(context, data.get(i).getProductid());
            }
        });
        if (context.isEdit) {
            holder.cb.setVisibility(View.VISIBLE);
        } else {
            holder.cb.setVisibility(View.GONE);
        }
        if (data.get(i).isSelect()) {
            holder.cb.setChecked(true);
        } else {
            holder.cb.setChecked(false);
        }
        return view;
    }

    static class ViewHolder {
        @BindView(R.id.ivIcon)
        ImageView ivIcon;
        @BindView(R.id.tvTitle)
        TextView tvTitle;
        @BindView(R.id.tvLitterTitle)
        TextView tvLitterTitle;
        @BindView(R.id.tvPrice)
        TextView tvPrice;
        @BindView(R.id.tvRepertory)
        TextView tvRepertory;
        @BindView(R.id.cb)
        CheckBox cb;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
