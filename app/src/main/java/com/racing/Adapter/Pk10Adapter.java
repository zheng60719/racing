package com.racing.Adapter;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.racing.R;
import com.racing.entity.FenXi;
import com.racing.entity.Pk10;
import com.racing.root.CommonRecyclerAdapter;
import com.racing.root.RecyclerViewHolder;
import com.racing.utils.DateUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


/**
 * Created by k41 on 2017/9/26.
 */

public class Pk10Adapter extends CommonRecyclerAdapter<Pk10> {
    private Context mContext;
    private List<Pk10> dataList;
    public Pk10Adapter(Context mContext, List<Pk10> dataList, int resId) {
        super(mContext, dataList, resId);
        this.mContext = mContext;
        this.dataList = dataList;
    }
    @Override
    public void setData(RecyclerViewHolder holder,int position) {
        Pk10 pk10 = getItem(position);
        holder.setTextView(R.id.tv_title, pk10.getTitle());
        SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //前面的lSysTime是秒数，先乘1000得到毫秒数，再转为java.util.Date类型
        java.util.Date dt = new Date(pk10.getShijian()*1000);
        String sDateTime = sdf.format(dt);
        String[] split = sDateTime.split(" ");
        String[] split1 = split[0].split("-");
        holder.setTextView(R.id.tv_time, split1[1]+"-"+split1[2]);
    }
}
