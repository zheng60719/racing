package com.racing.Adapter;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.racing.R;
import com.racing.entity.FenXi;
import com.racing.root.CommonRecyclerAdapter;
import com.racing.root.RecyclerViewHolder;

import java.util.List;


/**
 * Created by k41 on 2017/9/26.
 */

public class FenXiAdapter extends CommonRecyclerAdapter<FenXi> {
    private Context mContext;
    private List<FenXi> dataList;

    public FenXiAdapter(Context mContext, List<FenXi> dataList, int resId) {
        super(mContext, dataList, resId);
        this.mContext = mContext;
        this.dataList = dataList;
    }

    @Override
    public void setData(RecyclerViewHolder holder, final int position) {
        FenXi fenXi = getItem(position);
        ImageView img_main = holder.findView(R.id.img_user);
        ImageView img_vip = holder.findView(R.id.img_vip);
        TextView tv_zhuanjia = holder.findView(R.id.tv_zhuanjia);
        tv_zhuanjia.setText(fenXi.getList_user().get(0).getNicheng());
        TextView tv_fenan = holder.findView(R.id.tv_fenan);
        tv_fenan.setText(fenXi.getYuceinfo());
        TextView beizhu = holder.findView(R.id.tv_beizhu);
        beizhu.setText("简介："+fenXi.getList_user().get(0).getBeizhu());
        try {
            Glide.with(mContext).load(fenXi.getList_user().get(0).getTx()).placeholder(new ColorDrawable(mContext.getResources().getColor(R.color.color_content_bg))).//加载中显示的图片
                    error(new ColorDrawable(mContext.getResources().getColor(R.color.color_content_bg)))//加载失败时显示的图片
                    .into(img_main);
            Glide.with(mContext).load(fenXi.getList_level().get(0).getLogo()).placeholder(new ColorDrawable(mContext.getResources().getColor(R.color.color_content_bg))).//加载中显示的图片
                    error(new ColorDrawable(mContext.getResources().getColor(R.color.color_content_bg)))//加载失败时显示的图片
                    .into(img_vip);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
