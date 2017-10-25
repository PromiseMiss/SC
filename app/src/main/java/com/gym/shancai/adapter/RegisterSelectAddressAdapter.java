package com.gym.shancai.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.gym.shancai.R;
import com.gym.shancai.bean.RegisterSelectAddressBean;

/**
 * Created by gym on 2017/5/18.
 */

public class RegisterSelectAddressAdapter extends BaseAdapter {
    private RegisterSelectAddressBean data;

    public RegisterSelectAddressAdapter(RegisterSelectAddressBean bean) {

        this.data = bean;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position).getName();
    }

    @Override
    public long getItemId(int position) {
        return Long.parseLong(data.get(position).getId());
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder=null;
        if (convertView==null){
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_register_select_area,null);
            holder=new Holder();
            holder.tv= (TextView) convertView.findViewById(R.id.tv);
            convertView.setTag(holder);
        }else
        {
           holder= (Holder) convertView.getTag();
        }
        holder.tv.setText(getItem(position).toString());
        return convertView;
    }
    class Holder{
    TextView tv;
    }
}
