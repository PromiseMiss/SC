package com.gym.shancai.adapter;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gym.shancai.activity.AddShippingAddressActivity;
import com.gym.shancai.activity.AddressListActivity;
import com.gym.shancai.R;
import com.gym.shancai.bean.AddressListBean;
import com.gym.shancai.http.DelAddressHttp;
import com.gym.shancai.http.OrderSetAddressHttp;
import com.gym.shancai.http.base.HttpCallBackListener;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewAddressAdapter extends BaseAdapter {
    private AddressListBean data;
    private LayoutInflater inflater;
    private AddressListActivity context;
    public NewAddressAdapter(AddressListActivity activity, AddressListBean bean) {
        this.data = bean;
        context=activity;
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
        return Long.parseLong(data.get(arg0).getId());
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (inflater==null){
            inflater=LayoutInflater.from(context);
        }
        // TODO Auto-generated method stub
        final ViewHolder holder;
        if (convertView == null) {

            convertView = inflater.inflate(R.layout.item_shipping_list, parent,
                    false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tvName.setText(data.get(position).getReceivename());
        holder.tvPhoneNum.setText(data.get(position).getPhonenum());
        holder.tvContent.setText(data.get(position).getAddressinfo());
        holder.rlSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (context.isSelect) {
                    //订单入口选择地址
                    context.showProgressDialog();
                    new OrderSetAddressHttp(context.ordertext, data.get(position).getId(), new HttpCallBackListener() {
                        @Override
                        public void callBack(int statusCode, Object bean, String msg) {
                            context.finish();
                        }

                        @Override
                        public boolean onUserFail(int statusCode, Object bean, String msg) {
                            if (statusCode == -1) {
                                context.finish();
                                return true;
                            }
                            return false;
                        }

                        @Override
                        public boolean onNetError(int statusCode, String msg) {
                            return false;
                        }

                        @Override
                        public void onComplete(boolean isError) {
                            context.dismissProgressDialog();
                        }
                    }).post();
                } else {
                    if (data.get(position).getIs_default().equals("1")) {
                        return;
                    } else {
                        if (defChangeListener != null)
                            defChangeListener.onDefChange(position, data.get(position));
                    }
                }

            }
        });
        //入口不一样 是否可修改也不一样
//        if (context.isSelect){
//            holder.cb.setVisibility(View.GONE);
//        }else
//        {
//            holder.cb.setVisibility(View.VISIBLE);
//        }
        if (data.get(position).getIs_default() != null && data.get(position).getIs_default().equals("1"))//1默认
        {
            holder.tvContent.setText("[默认] " + data.get(position).getAddressinfo());
            SpannableStringBuilder builder = new SpannableStringBuilder(holder.tvContent.getText().toString());
            ForegroundColorSpan redSpan = new ForegroundColorSpan(ContextCompat.getColor(context, R.color.main_theme));
            builder.setSpan(redSpan, 0, 4, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            holder.tvContent.setText(builder);
//            holder.cb.setChecked(true);
        } else//1不默认
        {
            holder.tvContent.setText(data.get(position).getAddressinfo());
//            holder.cb.setChecked(false);
        }
        holder.btDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.showProgressDialog();

                new DelAddressHttp(data.get(position).getId(), new HttpCallBackListener() {
                    @Override
                    public void callBack(int statusCode, Object bean, String msg) {
                        context.setOKToast("删除成功");
                        data.remove(position);
                        notifyDataSetChanged();
                    }

                    @Override
                    public boolean onUserFail(int statusCode, Object bean, String msg) {
                        return false;
                    }

                    @Override
                    public boolean onNetError(int statusCode, String msg) {
                        return false;
                    }

                    @Override
                    public void onComplete(boolean isError) {
                        context.dismissProgressDialog();
                    }
                }).post();
            }
        });
        holder.btEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, AddShippingAddressActivity.class);
                intent.putExtra("isEdit", true);
                intent.putExtra("data", data.get(position));
                context.startActivity(intent);
            }
        });
        return convertView;
    }


    DefChangeListener defChangeListener;

    public interface DefChangeListener {
        void onDefChange(int position, AddressListBean.AddressListItemBean data);
    }

    public void setDefChangeListener(DefChangeListener defChangeListener) {
        this.defChangeListener = defChangeListener;
    }

    static class ViewHolder {
        @BindView(R.id.tvName)
        TextView tvName;
        @BindView(R.id.tvPhoneNum)
        TextView tvPhoneNum;
        @BindView(R.id.tvContent)
        TextView tvContent;
        @BindView(R.id.btDel)
        Button btDel;
        @BindView(R.id.btEdit)
        Button btEdit;
        @BindView(R.id.rlSelect)
        RelativeLayout rlSelect;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
