package com.gym.shancai.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.gym.shancai.R;
import com.gym.shancai.bean.ClassifyRightBean;
import com.gym.shancai.utils.CommonUtils;
import com.gym.shancai.utils.ImageUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ClassificationRightAdapter extends BaseAdapter {
    private Context context;
    private ClassifyRightBean data;
    private LayoutInflater inflater;

    public ClassificationRightAdapter(ClassifyRightBean rightData) {
        this.data = rightData;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
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
        if (context == null) {
            context = parent.getContext();
        }
        if (inflater == null) {
            inflater = LayoutInflater.from(context);
        }
        ViewHolder holder;
        if (convertView == null) {

            convertView = inflater.inflate(R.layout.item_classify_right, parent, false);
            holder = new ViewHolder(convertView);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        ImageUtils.INSTANCE.loadImage(context, data.get(position).getThumbnail(), holder.ivHeadIcon);
        holder.tvTitle.setText(data.get(position).getProdname());
        holder.tvLitterTitle.setText(data.get(position).getContent());
        holder.tvPrice.setText("￥" + data.get(position).getPrice());
        holder.tvQuantity.setText("库存:" + data.get(position).getLeft());

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CommonUtils.startDetailActivity(context,  data.get(position).getId());
            }
        });

        return convertView;
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
