package com.gym.shancai.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.gym.shancai.R;
import com.gym.shancai.bean.NewProductBean;
import com.gym.shancai.utils.CommonUtils;
import com.gym.shancai.utils.ImageUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/9/9 0009.
 * 新品上市
 */

public class NewProductAdapter extends BaseAdapter {

    NewProductBean productBean;
    Context context;
    LayoutInflater layoutInflater;

    public NewProductAdapter(NewProductBean productBean) {
        this.productBean = productBean;
    }

    @Override
    public int getCount() {
        return productBean != null ? productBean.size() : 0;
    }

    @Override
    public Object getItem(int i) {
        return productBean.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        if (context == null) {
            context = viewGroup.getContext();
        }
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(context);
        }
        ViewHolder viewHolder;
        if (view == null) {
            view = layoutInflater.inflate(R.layout.item_new_product, viewGroup, false);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        ImageUtils.INSTANCE.loadImage(context, productBean.get(i).getThumbnail(), viewHolder.ivCommodity);
        viewHolder.tvTit.setText(productBean.get(i).getProdname());
        viewHolder.tvSynopsis.setText(productBean.get(i).getContent());
        viewHolder.tvPrice.setText("￥" + productBean.get(i).getPrice());
        viewHolder.tvInventory.setText("库存：" + productBean.get(i).getLeft());
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CommonUtils.startDetailActivity(context, productBean.get(i).getId());
            }
        });

        return view;
    }

    static class ViewHolder {
        @BindView(R.id.ivCommodity)
        ImageView ivCommodity;
        @BindView(R.id.tvTit)
        TextView tvTit;
        @BindView(R.id.tvSynopsis)
        TextView tvSynopsis;
        @BindView(R.id.tvPrice)
        TextView tvPrice;
        @BindView(R.id.tvInventory)
        TextView tvInventory;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
