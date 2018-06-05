package com.racing.Adapter;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.racing.R;
import com.racing.entity.FenXi;
import com.racing.entity.MyForecast;
import com.racing.root.CommonRecyclerAdapter;
import com.racing.root.RecyclerViewHolder;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


/**
 * Created by k41 on 2017/9/26.
 */

public class MyForecastAdapter extends CommonRecyclerAdapter<MyForecast> {
    private Context mContext;
    private List<MyForecast> dataList;
    private String qishu;
    public OnItemOnClick onItemOnClick;

    public MyForecastAdapter(Context mContext, List<MyForecast> dataList, int resId,String qishu) {
        super(mContext, dataList, resId);
        this.mContext = mContext;
        this.dataList = dataList;
        this.qishu = qishu;
        onItemOnClick = (OnItemOnClick) mContext;
    }

    @Override
    public void setData(RecyclerViewHolder holder, final int position) {
        MyForecast item = getItem(position);
         holder.setTextView(R.id.tv_qishu,item.getQishu());
        holder.setTextView(R.id.tv_title,item.getTitle());
        SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //前面的lSysTime是秒数，先乘1000得到毫秒数，再转为java.util.Date类型
        java.util.Date dt = new Date(item.getShijian()*1000);
        String sDateTime = sdf.format(dt);
        holder.setTextView(R.id.tv_time,sDateTime);
        String qishu1 = item.getQishu();
        if (Integer.parseInt(qishu1)<Integer.parseInt(qishu)) {
            if (item.getIs_ok() == 0) {
                holder.setTextView(R.id.tv_state, "未中奖");
            } else {
                holder.setTextView(R.id.tv_state, "已中奖");
            }
        }else{
            holder.setTextView(R.id.tv_state, "未开奖");
        }
        holder.setTextView(R.id.tv_content,item.getYuceinfo());
        if (item.getType()==1) {
            if (item.getWeizhi()==1) {
                if (item.getType()==1) {
                    holder.setTextView(R.id.tv_num_type, "号码定位胆 定码 " + " 冠军");
                }else{
                    holder.setTextView(R.id.tv_num_type, "号码定位胆 杀码 " + " 冠军");
                }
            }else if (item.getWeizhi()==2){
                if (item.getType()==1) {
                    holder.setTextView(R.id.tv_num_type, "号码定位胆 定码 " + " 亚军");
                }else{
                    holder.setTextView(R.id.tv_num_type, "号码定位胆 杀码 " + " 亚军");
                }
            }else if (item.getWeizhi()==3){
                if (item.getType()==1) {
                    holder.setTextView(R.id.tv_num_type, "号码定位胆 定码 " + " 季军");
                }else{
                    holder.setTextView(R.id.tv_num_type, "号码定位胆 杀码 " + " 季军");
                }
            }else if (item.getWeizhi()==4){
                if (item.getType()==1) {
                    holder.setTextView(R.id.tv_num_type, "号码定位胆 定码 " + " 第四名");
                }else{
                    holder.setTextView(R.id.tv_num_type, "号码定位胆 杀码 " + " 第四名");
                }
            }else if (item.getWeizhi()==5){
                if (item.getType()==1) {
                    holder.setTextView(R.id.tv_num_type, "号码定位胆 定码 " + " 第五名");
                }else{
                    holder.setTextView(R.id.tv_num_type, "号码定位胆 杀码 " + " 第五名");
                }
            }else if (item.getWeizhi()==6){
                if (item.getType()==1) {
                    holder.setTextView(R.id.tv_num_type, "号码定位胆 定码 " + " 第六名");
                }else{
                    holder.setTextView(R.id.tv_num_type, "号码定位胆 杀码 " + " 第六名");
                }
            }else if (item.getWeizhi()==7){
                if (item.getType()==1) {
                    holder.setTextView(R.id.tv_num_type, "号码定位胆 定码 " + " 第七名");
                }else{
                    holder.setTextView(R.id.tv_num_type, "号码定位胆 杀码 " + " 第七名");
                }
            }else if (item.getWeizhi()==8){
                if (item.getType()==1) {
                    holder.setTextView(R.id.tv_num_type, "号码定位胆 定码 " + " 第八名");
                }else{
                    holder.setTextView(R.id.tv_num_type, "号码定位胆 杀码 " + " 第八名");
                }
            }else if (item.getWeizhi()==9){
                if (item.getType()==1) {
                    holder.setTextView(R.id.tv_num_type, "号码定位胆 定码 " + " 第九名");
                }else{
                    holder.setTextView(R.id.tv_num_type, "号码定位胆 杀码 " + " 第九名");
                }
            }else if (item.getWeizhi()==10){
                if (item.getType()==1) {
                    holder.setTextView(R.id.tv_num_type, "号码定位胆 定码 " + " 第十名");
                }else{
                    holder.setTextView(R.id.tv_num_type, "号码定位胆 杀码 " + " 第十名");
                }
            }
        }else if (item.getType()==2){
            if (item.getType()==1) {
                if (item.getWeizhi()==1) {
                    holder.setTextView(R.id.tv_num_type, "大小系列 定码 冠军");
                }else if (item.getWeizhi()==2) {
                    holder.setTextView(R.id.tv_num_type, "大小系列 定码 亚军");
                }else if (item.getWeizhi()==3) {
                    holder.setTextView(R.id.tv_num_type, "大小系列 定码 季军");
                }else if (item.getWeizhi()==4) {
                    holder.setTextView(R.id.tv_num_type, "大小系列 定码 第四名");
                }else if (item.getWeizhi()==5) {
                    holder.setTextView(R.id.tv_num_type, "大小系列 定码 第五名");
                }else if (item.getWeizhi()==6) {
                    holder.setTextView(R.id.tv_num_type, "大小系列 定码 第六名");
                }else if (item.getWeizhi()==7) {
                    holder.setTextView(R.id.tv_num_type, "大小系列 定码 第七名");
                }else if (item.getWeizhi()==8) {
                    holder.setTextView(R.id.tv_num_type, "大小系列 定码 第八名");
                }else if (item.getWeizhi()==9) {
                    holder.setTextView(R.id.tv_num_type, "大小系列 定码 第九名");
                }else if (item.getWeizhi()==10) {
                    holder.setTextView(R.id.tv_num_type, "大小系列 定码 第十名");
                }
            }else{
                if (item.getWeizhi()==1) {
                    holder.setTextView(R.id.tv_num_type, "大小系列 杀码 冠军");
                }else if (item.getWeizhi()==2) {
                    holder.setTextView(R.id.tv_num_type, "大小系列 杀码 亚军");
                }else if (item.getWeizhi()==3) {
                    holder.setTextView(R.id.tv_num_type, "大小系列 杀码 季军");
                }else if (item.getWeizhi()==4) {
                    holder.setTextView(R.id.tv_num_type, "大小系列 杀码 第四名");
                }else if (item.getWeizhi()==5) {
                    holder.setTextView(R.id.tv_num_type, "大小系列 杀码 第五名");
                }else if (item.getWeizhi()==6) {
                    holder.setTextView(R.id.tv_num_type, "大小系列 杀码 第六名");
                }else if (item.getWeizhi()==7) {
                    holder.setTextView(R.id.tv_num_type, "大小系列 杀码 第七名");
                }else if (item.getWeizhi()==8) {
                    holder.setTextView(R.id.tv_num_type, "大小系列 杀码 第八名");
                }else if (item.getWeizhi()==9) {
                    holder.setTextView(R.id.tv_num_type, "大小系列 杀码 第九名");
                }else if (item.getWeizhi()==10) {
                    holder.setTextView(R.id.tv_num_type, "大小系列 杀码 第十名");
                }
            }
        }else if (item.getType()==3){
            if (item.getType()==1) {
                if (item.getWeizhi()==1) {
                    holder.setTextView(R.id.tv_num_type, "单双系列 定码 冠军");
                }else if (item.getWeizhi()==2) {
                    holder.setTextView(R.id.tv_num_type, "单双系列 定码 亚军");
                }else if (item.getWeizhi()==3) {
                    holder.setTextView(R.id.tv_num_type, "单双系列 定码 季军");
                }else if (item.getWeizhi()==4) {
                    holder.setTextView(R.id.tv_num_type, "单双系列 定码 第四名");
                }else if (item.getWeizhi()==5) {
                    holder.setTextView(R.id.tv_num_type, "单双系列 定码 第五名");
                }else if (item.getWeizhi()==6) {
                    holder.setTextView(R.id.tv_num_type, "单双系列 定码 第六名");
                }else if (item.getWeizhi()==7) {
                    holder.setTextView(R.id.tv_num_type, "单双系列 定码 第七名");
                }else if (item.getWeizhi()==8) {
                    holder.setTextView(R.id.tv_num_type, "单双系列 定码 第八名");
                }else if (item.getWeizhi()==9) {
                    holder.setTextView(R.id.tv_num_type, "单双系列 定码 第九名");
                }else if (item.getWeizhi()==10) {
                    holder.setTextView(R.id.tv_num_type, "单双系列 定码 第十名");
                }
            }else{
                if (item.getWeizhi()==1) {
                    holder.setTextView(R.id.tv_num_type, "单双系列 杀码 冠军");
                }else if (item.getWeizhi()==2) {
                    holder.setTextView(R.id.tv_num_type, "单双系列 杀码 亚军");
                }else if (item.getWeizhi()==3) {
                    holder.setTextView(R.id.tv_num_type, "单双系列 杀码 季军");
                }else if (item.getWeizhi()==4) {
                    holder.setTextView(R.id.tv_num_type, "单双系列 杀码 第四名");
                }else if (item.getWeizhi()==5) {
                    holder.setTextView(R.id.tv_num_type, "单双系列 杀码 第五名");
                }else if (item.getWeizhi()==6) {
                    holder.setTextView(R.id.tv_num_type, "单双系列 杀码 第六名");
                }else if (item.getWeizhi()==7) {
                    holder.setTextView(R.id.tv_num_type, "单双系列 杀码 第七名");
                }else if (item.getWeizhi()==8) {
                    holder.setTextView(R.id.tv_num_type, "单双系列 杀码 第八名");
                }else if (item.getWeizhi()==9) {
                    holder.setTextView(R.id.tv_num_type, "单双系列 杀码 第九名");
                }else if (item.getWeizhi()==10) {
                    holder.setTextView(R.id.tv_num_type, "单双系列 杀码 第十名");
                }
            }
        }else if(item.getType()==4){
            if (item.getType()==1) {
                if (item.getWeizhi()==1) {
                    holder.setTextView(R.id.tv_num_type, "值和系列 定码 冠军");
                }else if (item.getWeizhi()==2) {
                    holder.setTextView(R.id.tv_num_type, "值和系列 定码 亚军");
                }else if (item.getWeizhi()==3) {
                    holder.setTextView(R.id.tv_num_type, "值和系列 定码 季军");
                }else if (item.getWeizhi()==4) {
                    holder.setTextView(R.id.tv_num_type, "值和系列 定码 第四名");
                }else if (item.getWeizhi()==5) {
                    holder.setTextView(R.id.tv_num_type, "值和系列 定码 第五名");
                }else if (item.getWeizhi()==6) {
                    holder.setTextView(R.id.tv_num_type, "值和系列 定码 第六名");
                }else if (item.getWeizhi()==7) {
                    holder.setTextView(R.id.tv_num_type, "值和系列 定码 第七名");
                }else if (item.getWeizhi()==8) {
                    holder.setTextView(R.id.tv_num_type, "值和系列 定码 第八名");
                }else if (item.getWeizhi()==9) {
                    holder.setTextView(R.id.tv_num_type, "值和系列 定码 第九名");
                }else if (item.getWeizhi()==10) {
                    holder.setTextView(R.id.tv_num_type, "值和系列 定码 第十名");
                }
            }else{
                if (item.getWeizhi()==1) {
                    holder.setTextView(R.id.tv_num_type, "值和系列 杀码 冠军");
                }else if (item.getWeizhi()==2) {
                    holder.setTextView(R.id.tv_num_type, "值和系列 杀码 亚军");
                }else if (item.getWeizhi()==3) {
                    holder.setTextView(R.id.tv_num_type, "值和系列 杀码 季军");
                }else if (item.getWeizhi()==4) {
                    holder.setTextView(R.id.tv_num_type, "值和系列 杀码 第四名");
                }else if (item.getWeizhi()==5) {
                    holder.setTextView(R.id.tv_num_type, "值和系列 杀码 第五名");
                }else if (item.getWeizhi()==6) {
                    holder.setTextView(R.id.tv_num_type, "值和系列 杀码 第六名");
                }else if (item.getWeizhi()==7) {
                    holder.setTextView(R.id.tv_num_type, "值和系列 杀码 第七名");
                }else if (item.getWeizhi()==8) {
                    holder.setTextView(R.id.tv_num_type, "值和系列 杀码 第八名");
                }else if (item.getWeizhi()==9) {
                    holder.setTextView(R.id.tv_num_type, "值和系列 杀码 第九名");
                }else if (item.getWeizhi()==10) {
                    holder.setTextView(R.id.tv_num_type, "值和系列 杀码 第十名");
                }
            }
        }else if (item.getType()==5){
            if (item.getType()==1) {
                if (item.getWeizhi()==1) {
                    holder.setTextView(R.id.tv_num_type, "龙虎系列 定码 1V10");
                }else if(item.getWeizhi()==2) {
                    holder.setTextView(R.id.tv_num_type, "龙虎系列 定码 2V9");
                }else if(item.getWeizhi()==3) {
                    holder.setTextView(R.id.tv_num_type, "龙虎系列 定码 3V8");
                }else if(item.getWeizhi()==4) {
                    holder.setTextView(R.id.tv_num_type, "龙虎系列 定码 4V7");
                }else if(item.getWeizhi()==5) {
                    holder.setTextView(R.id.tv_num_type, "龙虎系列 定码 5V6");
                }
            }else{
                if (item.getWeizhi()==1) {
                    holder.setTextView(R.id.tv_num_type, "龙虎系列 杀码 1V10");
                }else if(item.getWeizhi()==2) {
                    holder.setTextView(R.id.tv_num_type, "龙虎系列 杀码 2V9");
                }else if(item.getWeizhi()==3) {
                    holder.setTextView(R.id.tv_num_type, "龙虎系列 杀码 3V8");
                }else if(item.getWeizhi()==4) {
                    holder.setTextView(R.id.tv_num_type, "龙虎系列 杀码 4V7");
                }else if(item.getWeizhi()==5) {
                    holder.setTextView(R.id.tv_num_type, "龙虎系列 杀码 5V6");
                }
            }
        }else if (item.getType()==6){
            if (item.getType()==1) {
                holder.setTextView(R.id.tv_num_type, "冠亚和系列 定码 ");
            }else{
                holder.setTextView(R.id.tv_num_type, "冠亚和系列 杀码 ");
            }
        }
        LinearLayout onItem = holder.findView(R.id.onItem);
        onItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemOnClick.onItem(position);
            }
        });
    }
    public interface OnItemOnClick{
        void onItem(int position);
    }
}
