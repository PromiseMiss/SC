package com.gym.shancai.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.gym.shancai.R;
import com.gym.shancai.bean.BillDetailBean;
import com.gym.shancai.utils.Logger;
import com.gym.shancai.utils.TimeUtils;


/**
 * Created by Administrator on 2017/9/11 0011.
 * 账单明细
 */

public class BillDetailAdapter extends BaseExpandableListAdapter {

    BillDetailBean data;
    Context context;
    String month;

    public BillDetailAdapter(BillDetailBean data, Context context) {
        this.data = data;
        this.context = context;
    }

    @Override
    public int getGroupCount() {
        return data.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return data.get(i).getContent().size();
    }

    @Override
    public Object getGroup(int i) {
        return data.get(i).getMonth();//父元素的月份
    }

    @Override
    public Object getChild(int i, int i1) {
        return data.get(i).getContent().get(i1);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    /**
     * 加载并显示组父元素
     *
     * @param i
     * @param b
     * @param view
     * @param viewGroup
     * @return
     */
    @Override
    public View getGroupView(final int i, boolean b, View view, ViewGroup viewGroup) {
//        View groupView = null;
        ViewTitleHolder titleHolder = null;
        if (view == null) {
            view = View.inflate(context, R.layout.item_bill_detail_title, null);
            titleHolder = new ViewTitleHolder();
            titleHolder.tvDate = view.findViewById(R.id.tvDate);
            view.setTag(titleHolder);
//            groupView = view;
        } else {
            titleHolder = (ViewTitleHolder) view.getTag();
        }
        titleHolder.tvDate.setText(data.get(i).getMonth());
//        view.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });
        return view;
    }

    /**
     * 加载并显示子元素
     *
     * @param i
     * @param i1
     * @param b
     * @param view
     * @param viewGroup
     * @return
     */
    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
//        View childView = null;
        ViewContextHolder contextHolder = null;
        if (view == null) {
            view = View.inflate(context, R.layout.item_bill_detail_content, null);
            contextHolder = new ViewContextHolder();
            contextHolder.tvMonth = view.findViewById(R.id.tvMonth);
            contextHolder.tvPrice = view.findViewById(R.id.tvPrice);
            view.setTag(contextHolder);
//            childView = view;
        } else {
            contextHolder = (ViewContextHolder) view.getTag();
        }
        contextHolder.tvMonth.setText(TimeUtils.timeSecondToyyyyMMdd(Long.parseLong(data.get(i).getContent().get(i1).getOrdercreatedate())));
        Logger.d("~~~~~~~~~~~~" + Long.parseLong(data.get(i).getContent().get(i1).getOrdercreatedate()));
        contextHolder.tvPrice.setText(data.get(i).getContent().get(i1).getTotalprice());
        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }

    class ViewTitleHolder {
        TextView tvDate;
    }

    class ViewContextHolder {
        TextView tvMonth;
        TextView tvPrice;
    }
}
