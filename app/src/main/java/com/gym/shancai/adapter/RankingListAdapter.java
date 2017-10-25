package com.gym.shancai.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.gym.shancai.R;
import com.gym.shancai.bean.RankingListBean;
import com.gym.shancai.utils.CommonUtils;
import com.gym.shancai.utils.ImageUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/9/9 0009.
 * 排行榜
 */

public class RankingListAdapter extends BaseAdapter {

    RankingListBean bean;
    Context context;
    LayoutInflater inflater;

    public RankingListAdapter(RankingListBean bean) {
        this.bean = bean;
    }

    @Override
    public int getCount() {
        return bean != null ? bean.size() : 0;
    }

    @Override
    public Object getItem(int i) {
        return bean.get(i);
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
        if (inflater == null) {
            inflater = LayoutInflater.from(context);
        }
        ViewHolder holder;
        if (view == null) {
            view = inflater.inflate(R.layout.item_activity_center_list, viewGroup, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        ImageUtils.INSTANCE.loadImage(context, bean.get(i).getThumbnail(), holder.ivHeadIcon);
        holder.tvTitle.setText(bean.get(i).getProdname());
        holder.tvLitterTitle.setText(bean.get(i).getContent());
        holder.tvPrice.setText("￥" + bean.get(i).getPrice());
        holder.tvQuantity.setText("销量:" + bean.get(i).getSalenum());
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CommonUtils.startDetailActivity(context,bean.get(i).getId());
            }
        });
        return view;
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
        TextView tvQuantity;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
