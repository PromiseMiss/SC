package com.gym.shancai.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.gym.shancai.R;
import com.gym.shancai.bean.ClassifyHeadBean;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by gym on 2017/9/7.
 */

public class ClassifyHeadAdapter extends BaseAdapter {
    ClassifyHeadBean data;
    Context context;

    public ClassifyHeadAdapter(ClassifyHeadBean dataBean) {
        this.data = dataBean;
    }

    @Override
    public int getCount() {
        return data.getType().size();
    }

    @Override
    public Object getItem(int i) {
        return data.getType().get(i);
    }

    @Override
    public long getItemId(int i) {
        return Long.parseLong(data.getType().get(i).getId());
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (context == null) {
            context = viewGroup.getContext();
        }
        ViewHolder holder;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_classify_head, viewGroup, false);
            holder=new ViewHolder(view);
            viewGroup.setTag(holder);
        } else {
            holder = (ViewHolder) viewGroup.getTag();
        }
        holder.tv.setText(data.getType().get(i).getClassname());
        return view;
    }

    static class ViewHolder {
        @BindView(R.id.tv)
        TextView tv;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
