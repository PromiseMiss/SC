package com.gym.shancai.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.gym.shancai.activity.MainActivity;
import com.gym.shancai.R;
import com.gym.shancai.bean.ClassifyHeadBean;
import com.gym.shancai.bean.ClassifyLeftBean;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ClassificationLeftAdapter extends BaseAdapter {
    private ClassifyLeftBean data;
    private Context context;
    private LayoutInflater inflater;

    public ClassificationLeftAdapter(ClassifyLeftBean bean) {
        this.data = bean;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        if (data==null)
            return 0;
        return data.size();
    }

    @Override
    public Object getItem(int arg0) {
        // TODO Auto-generated method stub
        return data.get(arg0);
    }

    @Override
    public long getItemId(int arg0) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        ViewHolder holder;
        if (context==null){
            context= MainActivity.getInstence();
        }
        if (inflater==null){
            inflater = LayoutInflater.from(context);
        }
        if (convertView == null) {

            convertView = inflater.inflate(R.layout.item_classification_left, parent, false);
            holder = new ViewHolder(convertView);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tvItem.setText(data.get(position).getClassname());
        if (data.get(position).isSelect())
        {
            holder.tvItem.setBackgroundColor(Color.parseColor("#f7f7f7"));
            holder.tvItem.setTextColor(context.getResources().getColor(R.color.main_theme));
        }else
        {
            holder.tvItem.setBackgroundColor(context.getResources().getColor(R.color.white));
            holder.tvItem.setTextColor(Color.parseColor("#333333"));
        }
        return convertView;
    }


    static class ViewHolder {
        @BindView(R.id.tv_item)
        TextView tvItem;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
