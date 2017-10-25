package com.gym.shancai.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.gym.shancai.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 创建:gym
 * 日期:2016-11-10.
 * 说明:
 * 备注:
 */

public class BottomListAdapter extends BaseAdapter {
    Context context;
    LayoutInflater inflater;
    ArrayList<String> items;

    public int getTextColor() {
        return textColor;
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;
    }

    private int textColor;

     public BottomListAdapter(Context context, ArrayList<String> items) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_bottom_list, parent, false);
            holder=new ViewHolder(convertView);
            if (textColor!=0){
                holder.tv.setTextColor(textColor);
            }
            convertView.setTag(holder);
        }else {
            holder= (ViewHolder) convertView.getTag();
        }

        holder.tv.setText(items.get(position));

        if (position==getCount()-1)
            holder.line.setVisibility(View.GONE);
        else
            holder.line.setVisibility(View.VISIBLE);
        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.tv)
        TextView tv;
        @BindView(R.id.line)
        View line;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
