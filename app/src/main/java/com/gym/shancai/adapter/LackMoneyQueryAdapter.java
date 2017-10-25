package com.gym.shancai.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.gym.shancai.R;
import com.gym.shancai.activity.ReceiptActivity;
import com.gym.shancai.bean.LackMoneyQueryBean;
import com.gym.shancai.utils.TimeUtils;


/**
 * Created by Administrator on 2017/9/12 0012.
 * 差款查询
 */

public class LackMoneyQueryAdapter extends BaseExpandableListAdapter {

    LackMoneyQueryBean data;
    Context context;
    LayoutInflater inflater;

    public LackMoneyQueryAdapter(LackMoneyQueryBean data, Context context) {
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
        return data.get(i).getMonth();
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


    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        ViewTitleHolder titleHolder = null;
        if (view == null) {
            view = View.inflate(context, R.layout.item_lack_money_title, null);
            titleHolder = new ViewTitleHolder();
            titleHolder.tvTime = view.findViewById(R.id.tvTime);
            view.setTag(titleHolder);
        } else {
            titleHolder = (ViewTitleHolder) view.getTag();
        }
        titleHolder.tvTime.setText(data.get(i).getMonth());
        view.setEnabled(false);
        return view;
    }

    @Override
    public View getChildView(final int i, final int i1, boolean b, View view, ViewGroup viewGroup) {
        ViewContentHolder contentHolder = null;
        if (inflater == null) {
            inflater = LayoutInflater.from(context);
        }
        if (view == null) {
            view = inflater.inflate(R.layout.item_lack_money_content, null);
            contentHolder = new ViewContentHolder();
            contentHolder.tvDone = view.findViewById(R.id.tvDone);
            contentHolder.tvDebt = view.findViewById(R.id.tvDebt);
            contentHolder.tvGiveMoney = view.findViewById(R.id.tvGiveMoney);
            contentHolder.tvCause = view.findViewById(R.id.tvCause);
            contentHolder.tvTime = view.findViewById(R.id.tvTime);
            view.setTag(contentHolder);
        } else {
            contentHolder = (ViewContentHolder) view.getTag();
        }
        contentHolder.tvDone.setText(data.get(i).getContent().get(i1).getIscomplete());
        contentHolder.tvDebt.setText(data.get(i).getContent().get(i1).getPaytype());
        contentHolder.tvGiveMoney.setText(data.get(i).getContent().get(i1).getGivemoney());
        contentHolder.tvTime.setText(TimeUtils.timeToyyyyMMdd(Long.parseLong(data.get(i).getContent().get(i1).getActtime())));
        contentHolder.tvCause.setText("原因：上次订单预付款" + data.get(i).getContent().get(i1)
                .getPremoney() + "元，订单实际金额" + data.get(i).getContent().get(i1).getReallymoney()
                + "元，差额" + data.get(i).getContent().get(i1).getGivemoney() + "元");

        if (data.get(i).getContent().get(i1).getIscomplete().equals("1")) {
            contentHolder.tvDone.setText("已完成");
            view.setEnabled(false);
        } else {
            contentHolder.tvDone.setText("待处理");
            view.setEnabled(true);
            contentHolder.tvDone.setBackgroundColor(context.getResources().getColor(R.color.money_font));
        }
        if (data.get(i).getContent().get(i1).getPaytype().equals("4")) {
            contentHolder.tvDebt.setText("退款");
        } else {
            contentHolder.tvDebt.setText("欠款");
        }
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ReceiptActivity.class);
                intent.putExtra("iscomplete", data.get(i).getContent().get(i1).getIscomplete());
                intent.putExtra("id", data.get(i).getContent().get(i1).getId());
                intent.putExtra("money", data.get(i).getContent().get(i1).getGivemoney());

                context.startActivity(intent);
            }
        });

        return view;
    }


    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }

    static class ViewTitleHolder {
        TextView tvTime;

    }

    static class ViewContentHolder {
        TextView tvDone;
        TextView tvDebt;
        TextView tvGiveMoney;
        TextView tvCause;
        TextView tvTime;

    }
}
