package com.racing.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.racing.R;
import com.racing.entity.CanKao;
import com.racing.root.CommonRecyclerAdapter;
import com.racing.root.RecyclerViewHolder;

import java.util.List;

/**
 * Created by k41 on 2017/9/30.
 */

public class ReferenceResourcesAdapter extends CommonRecyclerAdapter<CanKao> {
    private Context mContext;
    private List<CanKao> dataList;
    public ReferenceResourcesAdapter(Context mContext, List<CanKao> dataList, int resId) {
        super(mContext, dataList, resId);
        this.mContext = mContext;
        this.dataList = dataList;
    }
    @Override
    public void setData(RecyclerViewHolder holder, int position) {
        CanKao canKao = dataList.get(position);
        LinearLayout linearLayout = holder.findView(R.id.linearLayout);
        if (position%2==1){
            linearLayout.setBackgroundColor(Color.parseColor("#FFFFFF"));
        }else{
            linearLayout.setBackgroundColor(Color.parseColor("#d9d9d9"));
        }
        holder.setTextView(R.id.tv_qishu,canKao.getQishu()+"期  免费参考");
            holder.setTextView(R.id.tv_11,"冠军");
            holder.setTextView(R.id.tv_44,"亚军");
            holder.setTextView(R.id.tv_77,"季军");
            holder.setTextView(R.id.tv_1010,"第4名");
            holder.setTextView(R.id.tv_1313,"第5名");
            holder.setTextView(R.id.tv_1616,"第6名");
            holder.setTextView(R.id.tv_1919,"第7名");
            holder.setTextView(R.id.tv_2222,"第8名");
            holder.setTextView(R.id.tv_2525,"第9名");
            holder.setTextView(R.id.tv_2828,"第10名");
        if (canKao.getList().get(0).getNuming().equals("0")) {
            holder.setTextView(R.id.tv_22, canKao.getList().get(0).getNum());
            holder.setVisibility(R.id.tv_22_red,0);
        }else{
            holder.setTextView(R.id.tv_22_red,"(贏)");
            holder.setTextView(R.id.tv_22, canKao.getList().get(0).getNum());
        }
        if (canKao.getList().get(0).getDaxiaoing().equals("0")){
            holder.setTextView(R.id.tv_33,canKao.getList().get(0).getDaxiao());
            holder.setVisibility(R.id.tv_33_red,0);
        }else{
            holder.setTextView(R.id.tv_33,canKao.getList().get(0).getDaxiao());
            holder.setTextView(R.id.tv_33_red,"(贏)");
        }
        if (canKao.getList().get(1).getNuming().equals("0")) {
            holder.setTextView(R.id.tv_55, canKao.getList().get(1).getNum());
            holder.setVisibility(R.id.tv_55_red,0);
        }else{
            holder.setTextView(R.id.tv_55_red,"(贏)");
            holder.setTextView(R.id.tv_55, canKao.getList().get(1).getNum());
        }
        if (canKao.getList().get(1).getDaxiaoing().equals("0")) {
            holder.setTextView(R.id.tv_66, canKao.getList().get(1).getDaxiao());
            holder.setVisibility(R.id.tv_66_red,0);
        }else{
            holder.setTextView(R.id.tv_66_red,"(贏)");
            holder.setTextView(R.id.tv_66, canKao.getList().get(1).getDaxiao());
        }
        if (canKao.getList().get(2).getNuming().equals("0")) {
            holder.setVisibility(R.id.tv_88_red,0);
            holder.setTextView(R.id.tv_88, canKao.getList().get(2).getNum());
        }else{
            holder.setTextView(R.id.tv_88_red,"(贏)");
            holder.setTextView(R.id.tv_88, canKao.getList().get(2).getNum());
        }
        if (canKao.getList().get(2).getDaxiaoing().equals("0")) {
            holder.setVisibility(R.id.tv_99_red,0);
            holder.setTextView(R.id.tv_99, canKao.getList().get(2).getDaxiao());
        }else{
            holder.setTextView(R.id.tv_99_red,"(贏)");
            holder.setTextView(R.id.tv_99, canKao.getList().get(2).getDaxiao());
        }
        if (canKao.getList().get(3).getNuming().equals("0")) {
            holder.setVisibility(R.id.tv_1111_red,0);
            holder.setTextView(R.id.tv_1111, canKao.getList().get(3).getNum());
        }else{
            holder.setTextView(R.id.tv_1111_red,"(贏)");
            holder.setTextView(R.id.tv_1111, canKao.getList().get(3).getNum());
        }
        if (canKao.getList().get(3).getDaxiaoing().equals("0")) {
            holder.setVisibility(R.id.tv_1212_red,0);
            holder.setTextView(R.id.tv_1212, canKao.getList().get(3).getDaxiao());
        }else{
            holder.setTextView(R.id.tv_1212_red,"(贏)");
            holder.setTextView(R.id.tv_1212, canKao.getList().get(3).getDaxiao());
        }
        if (canKao.getList().get(4).getNuming().equals("0")) {
            holder.setVisibility(R.id.tv_1414_red,0);
            holder.setTextView(R.id.tv_1414, canKao.getList().get(4).getNum());
        }else{
            holder.setTextView(R.id.tv_1414_red,"(贏)");
            holder.setTextView(R.id.tv_1414, canKao.getList().get(4).getNum());
        }
        if (canKao.getList().get(4).getDaxiaoing().equals("0")) {
            holder.setVisibility(R.id.tv_1515_red,0);
            holder.setTextView(R.id.tv_1515, canKao.getList().get(4).getDaxiao());
        }else{
            holder.setTextView(R.id.tv_1515_red,"(贏)");
            holder.setTextView(R.id.tv_1515, canKao.getList().get(4).getDaxiao());
        }
        if (canKao.getList().get(5).getNuming().equals("0")) {
            holder.setVisibility(R.id.tv_1717_red,0);
            holder.setTextView(R.id.tv_1717, canKao.getList().get(5).getNum());
        }else{
            holder.setTextView(R.id.tv_1717_red,"(贏)");
            holder.setTextView(R.id.tv_1717, canKao.getList().get(5).getNum());
        }
        if (canKao.getList().get(5).getDaxiaoing().equals("0")) {
            holder.setVisibility(R.id.tv_1818_red,0);
            holder.setTextView(R.id.tv_1818, canKao.getList().get(5).getDaxiao());
        }else {
            holder.setTextView(R.id.tv_1818_red,"(贏)");
            holder.setTextView(R.id.tv_1818, canKao.getList().get(5).getDaxiao());
        }
        if (canKao.getList().get(6).getNuming().equals("0")) {
            holder.setVisibility(R.id.tv_2020_red,0);
            holder.setTextView(R.id.tv_2020, canKao.getList().get(6).getNum());
        }else{
            holder.setTextView(R.id.tv_2020_red,"(贏)");
            holder.setTextView(R.id.tv_2020, canKao.getList().get(6).getNum());
        }
        if (canKao.getList().get(6).getDaxiaoing().equals("0")) {
            holder.setVisibility(R.id.tv_2121_red,0);
            holder.setTextView(R.id.tv_2121, canKao.getList().get(6).getDaxiao());
        }else{
            holder.setTextView(R.id.tv_2121_red,"(贏)");
            holder.setTextView(R.id.tv_2121, canKao.getList().get(6).getDaxiao());
        }
        if (canKao.getList().get(7).getNuming().equals("0")) {
            holder.setVisibility(R.id.tv_2323_red,0);
            holder.setTextView(R.id.tv_2323, canKao.getList().get(7).getNum());
        }else{
            holder.setTextView(R.id.tv_2323_red,"(贏)");
            holder.setTextView(R.id.tv_2323, canKao.getList().get(7).getNum());
        }
        if (canKao.getList().get(7).getDaxiaoing().equals("0")) {
            holder.setVisibility(R.id.tv_2424_red,0);
            holder.setTextView(R.id.tv_2424, canKao.getList().get(7).getDaxiao());
        }else{
            holder.setTextView(R.id.tv_2424_red,"(贏)");
            holder.setTextView(R.id.tv_2424, canKao.getList().get(7).getDaxiao());
        }
        if (canKao.getList().get(8).getNuming().equals("0")) {
            holder.setVisibility(R.id.tv_2626_red,0);
            holder.setTextView(R.id.tv_2626, canKao.getList().get(8).getNum());
        }else{
            holder.setTextView(R.id.tv_2626_red,"(贏)");
            holder.setTextView(R.id.tv_2626, canKao.getList().get(8).getNum());
        }
        if (canKao.getList().get(8).getDaxiaoing().equals("0")) {
            holder.setVisibility(R.id.tv_2727_red,0);
            holder.setTextView(R.id.tv_2727, canKao.getList().get(8).getDaxiao());
        }else{
            holder.setTextView(R.id.tv_2727_red,"(贏)");
            holder.setTextView(R.id.tv_2727, canKao.getList().get(8).getDaxiao());
        }
        if (canKao.getList().get(9).getNuming().equals("0")) {
            holder.setVisibility(R.id.tv_2929_red,0);
            holder.setTextView(R.id.tv_2929, canKao.getList().get(9).getNum());
        }else{
            holder.setTextView(R.id.tv_2929_red,"(贏)");
            holder.setTextView(R.id.tv_2929, canKao.getList().get(9).getNum());
        }
        if (canKao.getList().get(9).getDaxiaoing().equals("0")) {
            holder.setVisibility(R.id.tv_3030_red,0);
            holder.setTextView(R.id.tv_3030, canKao.getList().get(9).getDaxiao());
        }else{
            holder.setTextView(R.id.tv_3030_red,"(贏)");
            holder.setTextView(R.id.tv_3030, canKao.getList().get(9).getDaxiao());
        }
    }
}
