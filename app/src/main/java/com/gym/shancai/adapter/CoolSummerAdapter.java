package com.gym.shancai.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.gym.shancai.R;
import com.gym.shancai.bean.CoolSummerBean;
import com.gym.shancai.utils.CommonUtils;
import com.gym.shancai.utils.ImageUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/9/13 0013.
 * 清凉一夏适配器
 */

public class CoolSummerAdapter extends BaseAdapter {

    CoolSummerBean been;
    Context context;
    LayoutInflater layoutInflater;

    public CoolSummerAdapter(CoolSummerBean been) {
        this.been = been;
    }

    @Override
    public int getCount() {
        return been != null ? been.getContent().size() : 0;
    }

    @Override
    public Object getItem(int i) {
        return been.getContent().get(i);
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
        CrazyCityAdapter.ViewHolder holder;
        if (view == null) {
            view = layoutInflater.inflate(R.layout.item_home_list, viewGroup, false);
            holder = new CrazyCityAdapter.ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (CrazyCityAdapter.ViewHolder) view.getTag();
        }
        ImageUtils.INSTANCE.loadImage(context, been.getContent().get(i).getThumbnail(), holder.ivHeadIcon);
        holder.tvTitle.setText(been.getContent().get(i).getProdname());
        holder.tvLitterTitle.setText(been.getContent().get(i).getContent());
        holder.tvPrice.setText("￥" + been.getContent().get(i).getPrice());
        holder.tvQuantity.setText("库存：" + been.getContent().get(i).getLeft());
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CommonUtils.startDetailActivity(context,  been.getContent().get(i).getId());
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
