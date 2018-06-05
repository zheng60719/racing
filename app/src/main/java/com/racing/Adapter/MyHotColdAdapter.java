package com.racing.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.racing.R;
import com.racing.entity.HotCold;
import com.racing.entity.HotColdItem;
import com.racing.entity.TodayNumber;
import com.racing.entity.TodayNumberItem;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/1/4.
 */
public class MyHotColdAdapter extends BaseAdapter{
    public Context context;
    public List<HotCold> list ;
    public MyHotColdAdapter(Context context, List<HotCold> list ){
        this.context = context;
        this.list = list ;
    }
    @Override
    public int getCount() {

        return list.size();
    }

    @Override
    public HotCold getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(
                    R.layout.hot_cold_item, null);
            holder.tv_1 = (TextView) convertView.findViewById(R.id.tv_1);
            holder.tv_2 = (TextView) convertView.findViewById(R.id.tv_2);
            holder.tv_3 = (TextView) convertView.findViewById(R.id.tv_3);
            holder.tv_4 = (TextView) convertView.findViewById(R.id.tv_4);
            holder.tv_5 = (TextView) convertView.findViewById(R.id.tv_5);
            holder.tv_6 = (TextView) convertView.findViewById(R.id.tv_6);
            holder.tv_7 = (TextView) convertView.findViewById(R.id.tv_7);
            holder.tv_8 = (TextView) convertView.findViewById(R.id.tv_8);
            holder.tv_9 = (TextView) convertView.findViewById(R.id.tv_9);
            holder.tv_10 = (TextView) convertView.findViewById(R.id.tv_10);
            holder.tv_11 = (TextView) convertView.findViewById(R.id.tv_11);
            holder.tv_12 = (TextView) convertView.findViewById(R.id.tv_12);
            holder.tv_13 = (TextView) convertView.findViewById(R.id.tv_13);
            holder.tv_14 = (TextView) convertView.findViewById(R.id.tv_14);
            holder.tv_15 = (TextView) convertView.findViewById(R.id.tv_15);
            holder.tv_16 = (TextView) convertView.findViewById(R.id.tv_16);
            holder.tv_17 = (TextView) convertView.findViewById(R.id.tv_17);
            holder.tv_18 = (TextView) convertView.findViewById(R.id.tv_18);
            holder.tv_19 = (TextView) convertView.findViewById(R.id.tv_19);
            holder.tv_20 = (TextView) convertView.findViewById(R.id.tv_20);
            holder.tv_55 = (TextView) convertView.findViewById(R.id.tv_55);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        HotCold hotCold = list.get(position);
        Map<String, List<HotColdItem>> list_re = hotCold.getList_re();
        if (position==0){
            holder.tv_1.setText("冠军");
        }else if (position==1){
            holder.tv_1.setText("亚军");
        }else if (position==2){
            holder.tv_1.setText("季军");
        }else if (position==3){
            holder.tv_1.setText("第4名");
        }else if (position==4){
            holder.tv_1.setText("第5名");
        }else if (position==5){
            holder.tv_1.setText("第6名");
        }else if (position==6){
            holder.tv_1.setText("第7名");
        }else if (position==7){
            holder.tv_1.setText("第8名");
        }else if (position==8){
            holder.tv_1.setText("第9名");
        }else if (position==9){
            holder.tv_1.setText("第10名");
        }
        if (list_re!=null) {
            if (list.get(position).getList_re().size()>0) {
                    if (list.get(position).getList_re().get(position+ 1+"").size() == 1) {
                        holder.tv_2.setText(list.get(position).getList_re().get(position+1+"").get(0).getNum1());
                        TextViewBg(list.get(position).getList_re().get(position+1+"").get(0).getNum1(), holder.tv_2);
                    } else if (list.get(position).getList_re().get(position+1+"").size() == 2) {
                        holder.tv_2.setText(list.get(position).getList_re().get(position+1+"").get(0).getNum1());
                        TextViewBg(list.get(position).getList_re().get(position+1+"").get(0).getNum1(), holder.tv_2);
                        holder.tv_3.setText(list.get(position).getList_re().get(position + 1+"").get(1).getNum1());
                        TextViewBg(list.get(position).getList_re().get(position+1+"").get(1).getNum1(), holder.tv_3);
                    } else if (list.get(position).getList_re().get(position+1+"").size() == 3) {
                        holder.tv_2.setText(list.get(position).getList_re().get(position + 1+"").get(0).getNum1());
                        TextViewBg(list.get(position).getList_re().get(position + 1+"").get(0).getNum1(), holder.tv_2);
                        holder.tv_3.setText(list.get(position).getList_re().get(position + 1+"").get(1).getNum1());
                        TextViewBg(list.get(position).getList_re().get(position + 1+"").get(1).getNum1(), holder.tv_3);
                        holder.tv_4.setText(list.get(position).getList_re().get(position + 1+"").get(2).getNum1());
                        TextViewBg(list.get(position).getList_re().get(position + 1+"").get(2).getNum1(), holder.tv_4);
                    } else if (list.get(position).getList_re().get(position+1+"").size() == 4) {
                        holder.tv_2.setText(list.get(position).getList_re().get(position + 1+"").get(0).getNum1());
                        TextViewBg(list.get(position).getList_re().get(position + 1+"").get(0).getNum1(), holder.tv_2);
                        holder.tv_3.setText(list.get(position).getList_re().get(position + 1+"").get(1).getNum1());
                        TextViewBg(list.get(position).getList_re().get(position + 1+"").get(1).getNum1(), holder.tv_3);
                        holder.tv_4.setText(list.get(position).getList_re().get(position + 1+"").get(2).getNum1());
                        TextViewBg(list.get(position).getList_re().get(position + 1+"").get(2).getNum1(), holder.tv_4);
                        holder.tv_5.setText(list.get(position).getList_re().get(position + 1+"").get(3).getNum1());
                        TextViewBg(list.get(position).getList_re().get(position + 1+"").get(3).getNum1(), holder.tv_5);
                    } else if (list.get(position).getList_re().get(position+1+"").size() == 5) {
                        holder.tv_2.setText(list.get(position).getList_re().get(position + 1+"").get(0).getNum1());
                        TextViewBg(list.get(position).getList_re().get(position + 1+"").get(0).getNum1(), holder.tv_2);
                        holder.tv_3.setText(list.get(position).getList_re().get(position + 1+"").get(1).getNum1());
                        TextViewBg(list.get(position).getList_re().get(position + 1+"").get(1).getNum1(), holder.tv_3);
                        holder.tv_4.setText(list.get(position).getList_re().get(position + 1+"").get(2).getNum1());
                        TextViewBg(list.get(position).getList_re().get(position + 1+"").get(2).getNum1(), holder.tv_4);
                        holder.tv_5.setText(list.get(position).getList_re().get(position + 1+"").get(3).getNum1());
                        TextViewBg(list.get(position).getList_re().get(position + 1+"").get(3).getNum1(), holder.tv_5);
                        holder.tv_55.setText(list.get(position).getList_re().get(position + 1+"").get(4).getNum1());
                        TextViewBg(list.get(position).getList_re().get(position + 1+"").get(4).getNum1(), holder.tv_55);
                    }
                //温
                if (list.get(position).getList_wen().get(position+1+"").size() == 1) {
               holder.tv_6.setText(list.get(position).getList_wen().get(position + 1+"").get(0).getNum1());
               TextViewBg(list.get(position).getList_wen().get(position + 1+"").get(0).getNum1(), holder.tv_6);
           } else if (list.get(position).getList_wen().get(position+1+"").size() == 2) {
               holder.tv_6.setText(list.get(position).getList_wen().get(position + 1+"").get(0).getNum1());
               TextViewBg(list.get(position).getList_wen().get(position + 1+"").get(0).getNum1(), holder.tv_6);
               holder.tv_7.setText(list.get(position).getList_wen().get(position + 1+"").get(1).getNum1());
               TextViewBg(list.get(position).getList_wen().get(position + 1+"").get(1).getNum1(), holder.tv_7);
           } else if (list.get(position).getList_wen().get(position+1+"").size() == 3) {
               holder.tv_6.setText(list.get(position).getList_wen().get(position + 1+"").get(0).getNum1());
               TextViewBg(list.get(position).getList_wen().get(position + 1+"").get(0).getNum1(), holder.tv_6);
               holder.tv_7.setText(list.get(position).getList_wen().get(position + 1+"").get(1).getNum1());
               TextViewBg(list.get(position).getList_wen().get(position + 1+"").get(1).getNum1(), holder.tv_7);
               holder.tv_8.setText(list.get(position).getList_wen().get(position + 1+"").get(2).getNum1());
               TextViewBg(list.get(position).getList_wen().get(position + 1+"").get(2).getNum1(), holder.tv_8);
           } else if (list.get(position).getList_wen().get(position+1+"").size() == 4) {
               holder.tv_6.setText(list.get(position).getList_wen().get(position + 1+"").get(0).getNum1());
               TextViewBg(list.get(position).getList_wen().get(position + 1+"").get(0).getNum1(), holder.tv_6);
               holder.tv_7.setText(list.get(position).getList_wen().get(position + 1+"").get(1).getNum1());
               TextViewBg(list.get(position).getList_wen().get(position + 1+"").get(1).getNum1(), holder.tv_7);
               holder.tv_8.setText(list.get(position).getList_wen().get(position + 1+"").get(2).getNum1());
               TextViewBg(list.get(position).getList_wen().get(position + 1+"").get(2).getNum1(), holder.tv_8);
               holder.tv_9.setText(list.get(position).getList_wen().get(position + 1+"").get(3).getNum1());
               TextViewBg(list.get(position).getList_wen().get(position + 1+"").get(3).getNum1(), holder.tv_9);
           } else if (list.get(position).getList_wen().get(position+1+"").size() == 5) {
               holder.tv_6.setText(list.get(position).getList_wen().get(position + 1+"").get(0).getNum1());
               TextViewBg(list.get(position).getList_wen().get(position + 1+"").get(0).getNum1(), holder.tv_6);
               holder.tv_7.setText(list.get(position).getList_wen().get(position + 1+"").get(1).getNum1());
               TextViewBg(list.get(position).getList_wen().get(position + 1+"").get(1).getNum1(), holder.tv_7);
               holder.tv_8.setText(list.get(position).getList_wen().get(position + 1+"").get(2).getNum1());
               TextViewBg(list.get(position).getList_wen().get(position + 1+"").get(2).getNum1(), holder.tv_8);
               holder.tv_9.setText(list.get(position).getList_wen().get(position + 1+"").get(3).getNum1());
               TextViewBg(list.get(position).getList_wen().get(position + 1+"").get(3).getNum1(), holder.tv_9);
               holder.tv_10.setText(list.get(position).getList_wen().get(position + 1+"").get(4).getNum1());
               TextViewBg(list.get(position).getList_wen().get(position + 1+"").get(4).getNum1(), holder.tv_10);
           } else if (list.get(position).getList_wen().get(position+1+"").size() == 6) {
               holder.tv_6.setText(list.get(position).getList_wen().get(position + 1+"").get(0).getNum1());
               TextViewBg(list.get(position).getList_wen().get(position + 1+"").get(0).getNum1(), holder.tv_6);
               holder.tv_7.setText(list.get(position).getList_wen().get(position + 1+"").get(1).getNum1());
               TextViewBg(list.get(position).getList_wen().get(position + 1+"").get(1).getNum1(), holder.tv_7);
               holder.tv_8.setText(list.get(position).getList_wen().get(position + 1+"").get(2).getNum1());
               TextViewBg(list.get(position).getList_wen().get(position + 1+"").get(2).getNum1(), holder.tv_8);
               holder.tv_9.setText(list.get(position).getList_wen().get(position + 1+"").get(3).getNum1());
               TextViewBg(list.get(position).getList_wen().get(position + 1+"").get(3).getNum1(), holder.tv_9);
               holder.tv_10.setText(list.get(position).getList_wen().get(position + 1+"").get(4).getNum1());
               TextViewBg(list.get(position).getList_wen().get(position + 1+"").get(4).getNum1(), holder.tv_10);
               holder.tv_11.setText(list.get(position).getList_wen().get(position + 1+"").get(5).getNum1());
               TextViewBg(list.get(position).getList_wen().get(position + 1+"").get(5).getNum1(), holder.tv_11);
           }
            }
            if (hotCold.getList_leng().get(position+1+"").size()>0) {
                if (list.get(position).getList_leng().get(position + 1 + "").size() == 1) {
                    holder.tv_12.setText(list.get(position).getList_leng().get(position + 1 + "").get(0).getNum1());
                    TextViewBg(list.get(position).getList_leng().get(position + 1 + "").get(0).getNum1(), holder.tv_12);
                } else if (list.get(position).getList_leng().get(position + 1 + "").size() == 2) {
                    holder.tv_12.setText(list.get(position).getList_leng().get(position + 1 + "").get(0).getNum1());
                    TextViewBg(list.get(position).getList_leng().get(position + 1 + "").get(0).getNum1(), holder.tv_12);
                    holder.tv_13.setText(list.get(position).getList_leng().get(position + 1 + "").get(1).getNum1());
                    TextViewBg(list.get(position).getList_leng().get(position + 1 + "").get(1).getNum1(), holder.tv_13);
                } else if (list.get(position).getList_leng().get(position + 1 + "").size() == 3) {
                    holder.tv_12.setText(list.get(position).getList_leng().get(position + 1 + "").get(0).getNum1());
                    TextViewBg(list.get(position).getList_leng().get(position + 1 + "").get(0).getNum1(), holder.tv_12);
                    holder.tv_13.setText(list.get(position).getList_leng().get(position + 1 + "").get(1).getNum1());
                    TextViewBg(list.get(position).getList_leng().get(position + 1 + "").get(1).getNum1(), holder.tv_13);
                    holder.tv_14.setText(list.get(position).getList_leng().get(position + 1 + "").get(2).getNum1());
                    TextViewBg(list.get(position).getList_leng().get(position + 1 + "").get(2).getNum1(), holder.tv_14);
                } else if (list.get(position).getList_leng().get(position + 1 + "").size() == 4) {
                    holder.tv_12.setText(list.get(position).getList_leng().get(position + 1 + "").get(0).getNum1());
                    TextViewBg(list.get(position).getList_leng().get(position + 1 + "").get(0).getNum1(), holder.tv_12);
                    holder.tv_13.setText(list.get(position).getList_leng().get(position + 1 + "").get(1).getNum1());
                    TextViewBg(list.get(position).getList_leng().get(position + 1 + "").get(1).getNum1(), holder.tv_13);
                    holder.tv_14.setText(list.get(position).getList_leng().get(position + 1 + "").get(2).getNum1());
                    TextViewBg(list.get(position).getList_leng().get(position + 1 + "").get(2).getNum1(), holder.tv_14);
                    holder.tv_15.setText(list.get(position).getList_leng().get(position + 1 + "").get(3).getNum1());
                    TextViewBg(list.get(position).getList_leng().get(position + 1 + "").get(3).getNum1(), holder.tv_15);
                } else if (list.get(position).getList_leng().get(position + 1 + "").size() == 5) {
                    holder.tv_12.setText(list.get(position).getList_leng().get(position + 1 + "").get(0).getNum1());
                    TextViewBg(list.get(position).getList_leng().get(position + 1 + "").get(0).getNum1(), holder.tv_12);
                    holder.tv_13.setText(list.get(position).getList_leng().get(position + 1 + "").get(1).getNum1());
                    TextViewBg(list.get(position).getList_leng().get(position + 1 + "").get(1).getNum1(), holder.tv_13);
                    holder.tv_14.setText(list.get(position).getList_leng().get(position + 1 + "").get(2).getNum1());
                    TextViewBg(list.get(position).getList_leng().get(position + 1 + "").get(2).getNum1(), holder.tv_14);
                    holder.tv_15.setText(list.get(position).getList_leng().get(position + 1 + "").get(3).getNum1());
                    TextViewBg(list.get(position).getList_leng().get(position + 1 + "").get(3).getNum1(), holder.tv_15);
                    holder.tv_16.setText(list.get(position).getList_leng().get(position + 1 + "").get(4).getNum1());
                    TextViewBg(list.get(position).getList_leng().get(position + 1 + "").get(4).getNum1(), holder.tv_16);
                } else if (list.get(position).getList_leng().get(position + 1 + "").size() == 6) {
                    holder.tv_12.setText(list.get(position).getList_leng().get(position + 1 + "").get(0).getNum1());
                    TextViewBg(list.get(position).getList_leng().get(position + 1 + "").get(0).getNum1(), holder.tv_12);
                    holder.tv_13.setText(list.get(position).getList_leng().get(position + 1 + "").get(1).getNum1());
                    TextViewBg(list.get(position).getList_leng().get(position + 1 + "").get(1).getNum1(), holder.tv_13);
                    holder.tv_14.setText(list.get(position).getList_leng().get(position + 1 + "").get(2).getNum1());
                    TextViewBg(list.get(position).getList_leng().get(position + 1 + "").get(2).getNum1(), holder.tv_14);
                    holder.tv_15.setText(list.get(position).getList_leng().get(position + 1 + "").get(3).getNum1());
                    TextViewBg(list.get(position).getList_leng().get(position + 1 + "").get(3).getNum1(), holder.tv_15);
                    holder.tv_16.setText(list.get(position).getList_leng().get(position + 1 + "").get(4).getNum1());
                    TextViewBg(list.get(position).getList_leng().get(position + 1 + "").get(4).getNum1(), holder.tv_16);
                    holder.tv_17.setText(list.get(position).getList_leng().get(position + 1 + "").get(5).getNum1());
                    TextViewBg(list.get(position).getList_leng().get(position + 1 + "").get(5).getNum1(), holder.tv_17);
                } else if (list.get(position).getList_leng().get(position + 1 + "").size() == 7) {
                    holder.tv_12.setText(list.get(position).getList_leng().get(position + 1 + "").get(0).getNum1());
                    TextViewBg(list.get(position).getList_leng().get(position + 1 + "").get(0).getNum1(), holder.tv_12);
                    holder.tv_13.setText(list.get(position).getList_leng().get(position + 1 + "").get(1).getNum1());
                    TextViewBg(list.get(position).getList_leng().get(position + 1 + "").get(1).getNum1(), holder.tv_13);
                    holder.tv_14.setText(list.get(position).getList_leng().get(position + 1 + "").get(2).getNum1());
                    TextViewBg(list.get(position).getList_leng().get(position + 1 + "").get(2).getNum1(), holder.tv_14);
                    holder.tv_15.setText(list.get(position).getList_leng().get(position + 1 + "").get(3).getNum1());
                    TextViewBg(list.get(position).getList_leng().get(position + 1 + "").get(3).getNum1(), holder.tv_15);
                    holder.tv_16.setText(list.get(position).getList_leng().get(position + 1 + "").get(4).getNum1());
                    TextViewBg(list.get(position).getList_leng().get(position + 1 + "").get(4).getNum1(), holder.tv_16);
                    holder.tv_17.setText(list.get(position).getList_leng().get(position + 1 + "").get(5).getNum1());
                    TextViewBg(list.get(position).getList_leng().get(position + 1 + "").get(5).getNum1(), holder.tv_17);
                    holder.tv_18.setText(list.get(position).getList_leng().get(position + 1 + "").get(6).getNum1());
                    TextViewBg(list.get(position).getList_leng().get(position + 1 + "").get(6).getNum1(), holder.tv_18);
                } else if (list.get(position).getList_leng().get(position + 1 + "").size() == 8) {
                    holder.tv_12.setText(list.get(position).getList_leng().get(position + 1 + "").get(0).getNum1());
                    TextViewBg(list.get(position).getList_leng().get(position + 1 + "").get(0).getNum1(), holder.tv_12);
                    holder.tv_13.setText(list.get(position).getList_leng().get(position + 1 + "").get(1).getNum1());
                    TextViewBg(list.get(position).getList_leng().get(position + 1 + "").get(1).getNum1(), holder.tv_13);
                    holder.tv_14.setText(list.get(position).getList_leng().get(position + 1 + "").get(2).getNum1());
                    TextViewBg(list.get(position).getList_leng().get(position + 1 + "").get(2).getNum1(), holder.tv_14);
                    holder.tv_15.setText(list.get(position).getList_leng().get(position + 1 + "").get(3).getNum1());
                    TextViewBg(list.get(position).getList_leng().get(position + 1 + "").get(3).getNum1(), holder.tv_15);
                    holder.tv_16.setText(list.get(position).getList_leng().get(position + 1 + "").get(4).getNum1());
                    TextViewBg(list.get(position).getList_leng().get(position + 1 + "").get(4).getNum1(), holder.tv_16);
                    holder.tv_17.setText(list.get(position).getList_leng().get(position + 1 + "").get(5).getNum1());
                    TextViewBg(list.get(position).getList_leng().get(position + 1 + "").get(5).getNum1(), holder.tv_17);
                    holder.tv_18.setText(list.get(position).getList_leng().get(position + 1 + "").get(6).getNum1());
                    TextViewBg(list.get(position).getList_leng().get(position + 1 + "").get(6).getNum1(), holder.tv_18);
                    holder.tv_19.setText(list.get(position).getList_leng().get(position + 1 + "").get(7).getNum1());
                    TextViewBg(list.get(position).getList_leng().get(position + 1 + "").get(7).getNum1(), holder.tv_19);
                } else if (list.get(position).getList_leng().get(position + 1 + "").size() == 9) {
                    holder.tv_12.setText(list.get(position).getList_leng().get(position + 1 + "").get(0).getNum1());
                    TextViewBg(list.get(position).getList_leng().get(position + 1 + "").get(0).getNum1(), holder.tv_12);
                    holder.tv_13.setText(list.get(position).getList_leng().get(position + 1 + "").get(1).getNum1());
                    TextViewBg(list.get(position).getList_leng().get(position + 1 + "").get(1).getNum1(), holder.tv_13);
                    holder.tv_14.setText(list.get(position).getList_leng().get(position + 1 + "").get(2).getNum1());
                    TextViewBg(list.get(position).getList_leng().get(position + 1 + "").get(2).getNum1(), holder.tv_14);
                    holder.tv_15.setText(list.get(position).getList_leng().get(position + 1 + "").get(3).getNum1());
                    TextViewBg(list.get(position).getList_leng().get(position + 1 + "").get(3).getNum1(), holder.tv_15);
                    holder.tv_16.setText(list.get(position).getList_leng().get(position + 1 + "").get(4).getNum1());
                    TextViewBg(list.get(position).getList_leng().get(position + 1 + "").get(4).getNum1(), holder.tv_16);
                    holder.tv_17.setText(list.get(position).getList_leng().get(position + 1 + "").get(5).getNum1());
                    TextViewBg(list.get(position).getList_leng().get(position + 1 + "").get(5).getNum1(), holder.tv_17);
                    holder.tv_18.setText(list.get(position).getList_leng().get(position + 1 + "").get(6).getNum1());
                    TextViewBg(list.get(position).getList_leng().get(position + 1 + "").get(6).getNum1(), holder.tv_18);
                    holder.tv_19.setText(list.get(position).getList_leng().get(position + 1 + "").get(7).getNum1());
                    TextViewBg(list.get(position).getList_leng().get(position + 1 + "").get(7).getNum1(), holder.tv_19);
                    holder.tv_20.setText(list.get(position).getList_leng().get(position + 1 + "").get(8).getNum1());
                    TextViewBg(list.get(position).getList_leng().get(position + 1 + "").get(8).getNum1(), holder.tv_20);
                }
            }
        }
        return convertView;
    }
    class ViewHolder{
        TextView tv_1,tv_2,tv_3,tv_4,tv_6,tv_7,tv_5,tv_8,tv_9,tv_10,tv_11,tv_12,tv_13,tv_14,tv_15,tv_16,tv_17,tv_18,tv_19,tv_20,tv_55;
    }
    //设置每个模块的颜色
    public void TextViewBg(String num,TextView textView){
        if (num.equals("1")){
            textView.setBackgroundColor(Color.parseColor("#FFD700"));
        }else if (num.equals("2")){
            textView.setBackgroundColor(Color.parseColor("#4169E1"));
        }else if (num.equals("3")){
            textView.setBackgroundColor(Color.parseColor("#22292c"));
        }else if (num.equals("4")){
            textView.setBackgroundColor(Color.parseColor("#FF8C00"));
        }else if (num.equals("5")){
            textView.setBackgroundColor(Color.parseColor("#40E0D0"));
        }else if (num.equals("6")){
            textView.setBackgroundColor(Color.parseColor("#0000FF"));
        }else if (num.equals("7")){
            textView.setBackgroundColor(Color.parseColor("#bbbbbb"));
        }else if (num.equals("8")){
            textView.setBackgroundColor(Color.parseColor("#FF4500"));
        }else if (num.equals("9")){
            textView.setBackgroundColor(Color.parseColor("#A0522D"));
        }else if (num.equals("10")){
            textView.setBackgroundColor(Color.parseColor("#66CDAA"));
        }
    }
}
