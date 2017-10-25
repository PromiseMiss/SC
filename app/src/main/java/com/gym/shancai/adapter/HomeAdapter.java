package com.gym.shancai.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.gym.shancai.R;
import com.gym.shancai.bean.HomeBean;
import com.gym.shancai.utils.CommonUtils;
import com.gym.shancai.utils.ImageUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/9/5 0005.
 */

public class HomeAdapter extends BaseAdapter {
    Context context;

    public HomeAdapter(HomeBean bean) {
        this.data = bean;
    }

    HomeBean data;
    private LayoutInflater mLayoutInflater;


    @Override
    public int getCount() {
        if (data==null||data.getRecommend()==null){
            return 0;
        }
        return data.getRecommend().size();
    }

    @Override
    public Object getItem(int i) {
        return data.getRecommend().get(i);
    }

    @Override
    public long getItemId(int i) {
        return Long.parseLong(data.getRecommend().get(i).getId());
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        HomeViewHolder viewHolder = null;
        if (context == null) {
            context = viewGroup.getContext();
        }
        if (mLayoutInflater == null) {
            mLayoutInflater = LayoutInflater.from(context);
        }
        if (view == null) {
            view = mLayoutInflater.inflate(R.layout.item_home_list, viewGroup, false);
            viewHolder = new HomeViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (HomeViewHolder) view.getTag();
        }
        HomeBean.RecommendBean recommendBean = (HomeBean.RecommendBean) getItem(i);
        ImageUtils.INSTANCE.loadImage(context, data.getRecommend().get(i).getThumbnail(), viewHolder.ivHeadIcon);
        viewHolder.tvTitle.setText(recommendBean.getProdname());
        viewHolder.tvLitterTitle.setText(recommendBean.getContent());
        viewHolder.tvPrice.setText("￥" + recommendBean.getPrice());
        viewHolder.tvQuantity.setText("库存:" + recommendBean.getLeft());
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CommonUtils.startDetailActivity(context, getItemId(i) + "");
            }
        });
        return view;
    }

    class HomeViewHolder {
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

        HomeViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
