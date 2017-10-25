package com.gym.shancai.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.gym.shancai.R;
import com.gym.shancai.bean.ActivityImageBean;
import com.gym.shancai.utils.CommonUtils;
import com.gym.shancai.utils.ImageUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/9/12 0012.
 */

public class ActivityImageAdapter extends BaseAdapter {

    Context context;
    LayoutInflater inflater;
    ActivityImageBean data;

    public ActivityImageAdapter(ActivityImageBean data) {
        this.data = data;
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
        if (context == null) {
            context = viewGroup.getContext();
        }
        if (inflater == null) {
            inflater = LayoutInflater.from(context);
        }
        ViewHolderIm holderIm;
        if (view == null) {
            view = inflater.inflate(R.layout.item_home_list, viewGroup, false);
            holderIm = new ViewHolderIm(view);
            view.setTag(holderIm);
        } else {
            holderIm = (ViewHolderIm) view.getTag();
        }
        ImageUtils.INSTANCE.loadImage(context, data.get(i).getThumbnail(), holderIm.ivHeadIcon);
        holderIm.tvTitle.setText(data.get(i).getProdname());
        holderIm.tvLitterTitle.setText(data.get(i).getContent());
        holderIm.tvInventory.setText("销量:" + data.get(i).getSalenum());
        holderIm.tvPrice.setText("￥" + data.get(i).getPrice());
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CommonUtils.startDetailActivity(context, data.get(i).getId());
            }
        });

        return view;
    }

    static class ViewHolderIm {
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

        ViewHolderIm(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
