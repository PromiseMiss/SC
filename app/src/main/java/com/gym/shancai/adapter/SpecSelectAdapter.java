//package com.gym.shancai.adapter;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.BaseAdapter;
//import android.widget.TextView;
//
//import com.gym.zdj.R;
//import com.gym.zdj.data.newbean.IntegralProductDetailBean;
//import com.gym.zdj.data.newbean.ManagerProductDetailBean;
//import com.gym.zdj.data.newbean.ProductDetailBean;
//import com.gym.zdj.data.newbean.ShopMoneyProductDetailBean;
//import com.gym.zdj.customview.NoScrollGridView;
//
//import butterknife.BindView;
//import butterknife.ButterKnife;
//
///**
// * 改版后自由一个类目
// */
//public class SpecSelectAdapter  <T>extends BaseAdapter {
//    private Context context;
//    private T data;
//    private LayoutInflater inflater;
//
//    public SpecSelectAdapter(Context context, T data) {
//        this.context = context;
//        this.data = data;
//        inflater = LayoutInflater.from(context);
//        if (data instanceof IntegralProductDetailBean){
//          adapter=new SpecSelectGridAdapter(context,((IntegralProductDetailBean) data).getGoodsinfo().getStandardlist());
//        }
//        if (data instanceof ProductDetailBean){
//            adapter=new SpecSelectGridAdapter(context, ((ProductDetailBean) data).getStandardlist());
//        }
//        if (data instanceof ManagerProductDetailBean){
//            adapter=new SpecSelectGridAdapter(context, ((ManagerProductDetailBean) data).getStandardlist());
//        }
//    }
//
//
//
//    @Override
//    public int getCount() {
//        // TODO Auto-generated method stub
////        if (data instanceof IntegralProductDetailBean){
////           return ((IntegralProductDetailBean) data).getGoodsinfo().getStandardlist().size();
////        }
////        if (data instanceof ProductDetailBean){
////            return ((ProductDetailBean) data).getStandardlist().size();
////        }
//        return 1;
//    }
//
//    @Override
//    public Object getItem(int arg0) {
//        // TODO Auto-generated method stub
//        if (data instanceof IntegralProductDetailBean){
//            return ((IntegralProductDetailBean) data).getGoodsinfo().getStandardlist().get(arg0);
//        }
//        if (data instanceof ProductDetailBean){
//            return ((ProductDetailBean) data).getStandardlist().get(arg0);
//        }
//        if (data instanceof ManagerProductDetailBean){
//            return ((ManagerProductDetailBean) data).getStandardlist().get(arg0);
//        }
//        return "";
//    }
//    SpecSelectGridAdapter adapter;
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
//            convertView = inflater.inflate(R.layout.item_spec_select, parent, false);
//            holder = new ViewHolder(convertView);
//            convertView.setTag(holder);
//        } else {
//            holder = (ViewHolder) convertView.getTag();
//        }
////        holder.tv.setText(data.getStandardlist().get(position).getDescription());
//
//        if (data instanceof IntegralProductDetailBean){
//            holder.tv.setText("商品规格");
////            adapter=new SpecSelectGridAdapter(context,((IntegralProductDetailBean) data).getGoodsinfo().getStandardlist());
//            holder.ngv.setAdapter(adapter);
//        }
//        if (data instanceof ProductDetailBean){
//            holder.tv.setText("商品规格");
////            adapter=new SpecSelectGridAdapter(context, ((ProductDetailBean) data).getStandardlist());
//            holder.ngv.setAdapter(adapter);
//
//        }
//        if (data instanceof ManagerProductDetailBean){
//            holder.tv.setText("商品规格");
////            adapter=new SpecSelectGridAdapter(context, ((ManagerProductDetailBean) data).getStandardlist());
//            holder.ngv.setAdapter(adapter);
//
//        }
//        if (data instanceof ShopMoneyProductDetailBean){
//            holder.tv.setText("商品规格");
////            adapter=new SpecSelectGridAdapter(context, ((ManagerProductDetailBean) data).getStandardlist());
//            holder.ngv.setAdapter(adapter);
//
//        }
//        return convertView;
//    }
//
//    static class ViewHolder {
//        @BindView(R.id.tv)
//        TextView tv;
//        @BindView(R.id.ngv)
//        NoScrollGridView ngv;
//
//        ViewHolder(View view) {
//            ButterKnife.bind(this, view);
//        }
//    }
//    public void setOnAttrChangeListener(SpecSelectGridAdapter.OnAttrChangeListener listener){
//
//        adapter.setOnAttrChangeListener(listener);
//    }
//}
