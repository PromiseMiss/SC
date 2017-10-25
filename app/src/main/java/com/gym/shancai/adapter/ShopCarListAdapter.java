package com.gym.shancai.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gym.shancai.activity.CustomItemListener;
import com.gym.shancai.R;
import com.gym.shancai.bean.CatIndexListBean;
import com.gym.shancai.fragment.ShopCarFragment;
import com.gym.shancai.utils.ImageUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/8/29 0029.
 * 购物车适配器
 */

public class ShopCarListAdapter extends BaseAdapter {

    Context context;
    LayoutInflater inflater;
    CatIndexListBean data;
    private CustomItemListener customItemListener;
    ShopCarFragment fragment;
    public ShopCarListAdapter(ShopCarFragment shopCarFragment,CatIndexListBean carBean) {
        this.data = carBean;
        fragment=shopCarFragment;
    }

    public void setCustomItemListener(CustomItemListener listener) {
        this.customItemListener = listener;
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
        final ViewHolder holder;
        if (view == null) {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_shop_car_edit, viewGroup, false);
            holder = new ViewHolder(view);

            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        ImageUtils.INSTANCE.loadImage(context, data.get(i).getThumbnail(), holder.ivHeadIcon);
        holder.tvTitle.setText(data.get(i).getProdname());
        holder.tvLitterTitle.setText(data.get(i).getContent());
        holder.tvPrice.setText(data.get(i).getGoodsprice() + "");
        holder.tvShopNumber.setText("x " + data.get(i).getGoodsnum());
        holder.etNumber.setText(data.get(i).getGoodsnum()+"");
        holder.cbNoChose.setChecked(data.get(i).isSelect());
//        holder.etNumber.setClickable(true);
//        holder.etNumber.setFocusable(true);
        //单选
        holder.cbNoChose.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                data.get(i).setSelect(b);
                if (onItemCheckBoxChangeListener != null) {
                    onItemCheckBoxChangeListener.onItemCheckBoxChange();
                }

            }
        });

        //增加
        holder.ivPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                data.get(i).setGoodsnum(data.get(i).getGoodsnum()+1);
                holder.etNumber.setText(data.get(i).getGoodsnum()+"");
                holder.tvShopNumber.setText("x " + data.get(i).getGoodsnum());
            }
        });

        //删减
        holder.ivMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (data.get(i).getGoodsnum()>1){
                data.get(i).setGoodsnum(data.get(i).getGoodsnum()-1);
                holder.etNumber.setText(data.get(i).getGoodsnum()+"");
                holder.tvShopNumber.setText("x " + data.get(i).getGoodsnum());
                }
            }
        });

        //是否编辑
        if (fragment.isEditMode) {
            holder.rlEdit.setVisibility(View.VISIBLE);
            holder.tvShopNumber.setVisibility(View.GONE);
        } else {
            holder.rlEdit.setVisibility(View.GONE);
            holder.tvShopNumber.setVisibility(View.VISIBLE);
        }


        return view;
    }

    class ViewHolder {
        @BindView(R.id.cbNoChose)
        CheckBox cbNoChose;
        @BindView(R.id.ivHeadIcon)
        ImageView ivHeadIcon;
        @BindView(R.id.tvTitle)
        TextView tvTitle;
        @BindView(R.id.tvLitterTitle)
        TextView tvLitterTitle;
        @BindView(R.id.tvPrice)
        TextView tvPrice;
        @BindView(R.id.tvShopNumber)
        TextView tvShopNumber;
        @BindView(R.id.etNumber)
        EditText etNumber;
        @BindView(R.id.ivPlus)
        ImageView ivPlus;
        @BindView(R.id.ivMinus)
        ImageView ivMinus;
        @BindView(R.id.rlEdit)
        RelativeLayout rlEdit;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    public void setOnItemCheckBoxChangeListener(OnItemCheckBoxChangeListener listener) {
        onItemCheckBoxChangeListener = listener;
    }

    OnItemCheckBoxChangeListener onItemCheckBoxChangeListener;

    public interface OnItemCheckBoxChangeListener {
        void onItemCheckBoxChange();
    }

}


