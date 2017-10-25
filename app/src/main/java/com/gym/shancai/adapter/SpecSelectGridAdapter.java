//package com.gym.shancai.adapter;
//
//import android.content.Context;
//import android.graphics.Color;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.BaseAdapter;
//import android.widget.TextView;
//
//import com.gym.zdj.R;
//import com.gym.zdj.data.newbean.ProductDetailBean;
//
//import java.util.List;
//
//import butterknife.BindView;
//import butterknife.ButterKnife;
//
//public class SpecSelectGridAdapter extends BaseAdapter {
//    private Context context;
//    private List<ProductDetailBean.StandardlistBean> data;
//    private LayoutInflater inflater;
//
//    public SpecSelectGridAdapter(Context context, List<ProductDetailBean.StandardlistBean> data) {
//        this.context = context;
//        this.data = data;
//        inflater = LayoutInflater.from(context);
//    }
//
//    @Override
//    public int getCount() {
//        // TODO Auto-generated method stub
//        if (data == null)
//            return 0;
//        return data.size();
//    }
//
//    @Override
//    public Object getItem(int arg0) {
//        // TODO Auto-generated method stub
//        return data.get(arg0);
//    }
//
//    @Override
//    public long getItemId(int arg0) {
//        // TODO Auto-generated method stub
//        return 0;
//    }
//
//    @Override
//    public View getView(final int position, View convertView, ViewGroup parent) {
//        // TODO Auto-generated method stub
//        ViewHolder holder;
//        if (convertView == null) {
//
//            convertView = inflater.inflate(R.layout.item_spec_item, parent, false);
//            holder = new ViewHolder(convertView);
//            convertView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    for (int i =0 ;i<data.size();i++){
//                        data.get(i).setSelect(false);
//                    }
//                    data.get(position).setSelect(true);
//                    if (onAttrChangeListener!=null){
//                        onAttrChangeListener.onAttrChangeListener(position);
//                    }
//                    notifyDataSetChanged();
//                }
//            });
//            convertView.setTag(holder);
//        } else {
//            holder = (ViewHolder) convertView.getTag();
//        }
//        holder.tv.setText(data.get(position).getDescription());
//        if (data.get(position).isSelect())
//        {
//            holder.tv.setTextColor(context.getResources().getColor(R.color.white));
//            holder.tv.setBackgroundResource(R.drawable.bg_item_spec_select_);
//        }else{
//            holder.tv.setTextColor(Color.parseColor("#231815"));
//            holder.tv.setBackgroundResource(R.drawable.bg_item_spec_select);
//        }
//        return convertView;
//    }
//
//    static class ViewHolder {
//        @BindView(R.id.tv)
//        TextView tv;
//
//        ViewHolder(View view) {
//            ButterKnife.bind(this, view);
//        }
//    }
//    OnAttrChangeListener onAttrChangeListener;
//    public interface OnAttrChangeListener{
//        void onAttrChangeListener(int position);
//    }
//    public void setOnAttrChangeListener(OnAttrChangeListener listener){
//        onAttrChangeListener=listener;
//
//    }
//}
