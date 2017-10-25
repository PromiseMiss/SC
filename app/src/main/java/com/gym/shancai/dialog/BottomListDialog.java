package com.gym.shancai.dialog;

import android.app.Dialog;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;


import com.gym.shancai.R;
import com.gym.shancai.adapter.BottomListAdapter;

import java.util.ArrayList;

/**
 * @author guoyi
 */
public class BottomListDialog extends Dialog implements View.OnClickListener, AdapterView.OnItemClickListener {


    ListView lv;
    TextView tvCancel;
    private Context context;

    public int getTextColor() {
        return textColor;
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;
        ((BottomListAdapter)lv.getAdapter()).setTextColor(textColor);
    }

    private int textColor;

    public boolean isClickCancle() {
        return clickCancle;
    }

    public void setClickCancle(boolean clickCancle) {
        this.clickCancle = clickCancle;
    }

    private boolean clickCancle=true;

    ArrayList<String> data=new ArrayList<String>();
    ArrayList<Object> tags=new ArrayList<Object>();
    public BottomListDialog(Context context) {
        this(context, R.style.Theme_Dialog_From_Bottom);
        // this.toChatUsername = ContactsDao.getDisplayUserName(toChatUsername);
    }

    public BottomListDialog(Context context, int theme) {
        super(context, theme);
        this.context = context;
        this.setCanceledOnTouchOutside(true);
        this.setCancelable(true);
        setContentView(R.layout.dialog_bottom_list);
        tvCancel= (TextView) findViewById(R.id.tv_cancel);
        tvCancel.setOnClickListener(this);
        Window window = getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        lp.width = dm.widthPixels;// 让dialog的宽占满屏幕的宽
        lp.gravity = Gravity.BOTTOM;// 出现在底部
        window.setAttributes(lp);
        lv= (ListView) findViewById(R.id.lv);
        lv.setOnItemClickListener(this);
        lv.setAdapter(new BottomListAdapter(context,data));
    }

    @Override
    public void show() {
        super.show();
        if (textColor!=0){

            tvCancel.setTextColor(textColor);
        }
    }

    public  SelectListener selectListener;


    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_cancel:
                if (selectListener!=null)
//                selectListener.onSelect(99,view);
                dismiss();
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (selectListener!=null)
            selectListener.onSelect(position,view,getTag(position));
        if (clickCancle)
        dismiss();
    }

    public void goneCancel() {
        tvCancel.setVisibility(View.GONE);
        setCanceledOnTouchOutside(false);
    }

    public interface SelectListener {
        /**
         * @param
         */
        void onSelect(int position, View view, Object tag);

    }

    public void setonSelectListener(SelectListener selectListener) {
        this.selectListener = selectListener;
    }

    /**
     *增加选项
     * @param s
     * @return 返回项index
     */
    public int addItem(String s){
        data.add(s);
        return data.size()-1;
    }
    /**
     *删除所有选项
     * @param
     * @return 返回项index
     */
    public void removeAllItem(){
        data.removeAll(data);
    }
    /**
     *选项数
     * @param
     * @return 返回项index
     */
    public int itemCount(){
        return data.size();
    }
    /**
     *增加选项
     * @param s
     * @return 返回项index
     */
    public int addItem(String s,Object tag){
        data.add(s);
        tags.add(tag);
        return data.size()-1;
    }
    public void clearnItem(){
        data.clear();
        tags.clear();
    }
    public String getItemString(int posi){

        return data.get(posi);
    }
    public Object getTag(int i){
        if (tags.size()!=0)
        return tags.get(i);
        else
            return null;
    }
    public String getItemName(int i){
        return data.get(i);
    }
    public void setHeadText(String string){
        findViewById(R.id.tv_head).setVisibility(View.VISIBLE);
        findViewById(R.id.line).setVisibility(View.VISIBLE);
        ((TextView)findViewById(R.id.tv_head)).setText(string);

    }

}