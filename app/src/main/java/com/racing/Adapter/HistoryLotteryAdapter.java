package com.racing.Adapter;

import android.content.Context;
import android.util.Log;

import com.racing.R;
import com.racing.entity.HistoryLottery;
import com.racing.root.CommonRecyclerAdapter;
import com.racing.root.RecyclerViewHolder;
import com.racing.utils.DateUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


/**
 * Created by k41 on 2017/9/26.
 */

public class HistoryLotteryAdapter extends CommonRecyclerAdapter<HistoryLottery> {
    private Context mContext;
    private List<HistoryLottery> dataList;

    public HistoryLotteryAdapter(Context mContext, List<HistoryLottery> dataList, int resId) {
        super(mContext, dataList, resId);
        this.mContext = mContext;
        this.dataList = dataList;
    }

    @Override
    public void setData(RecyclerViewHolder holder, int position) {
        HistoryLottery historyLottery = getItem(position);
        holder.setTextView(R.id.tv1, historyLottery.getQishu() + "");
        holder.setTextView(R.id.tv2, historyLottery.getNum1() + "");
        holder.setTextView(R.id.tv3, historyLottery.getNum2() + "");
        holder.setTextView(R.id.tv4, historyLottery.getNum3() + "");
        holder.setTextView(R.id.tv5, historyLottery.getNum4() + "");
        holder.setTextView(R.id.tv6, historyLottery.getNum5() + "");
        holder.setTextView(R.id.tv7, historyLottery.getNum6() + "");
        holder.setTextView(R.id.tv8, historyLottery.getNum7() + "");
        holder.setTextView(R.id.tv9, historyLottery.getNum8() + "");
        holder.setTextView(R.id.tv10, historyLottery.getNum9() + "");
        holder.setTextView(R.id.tv11, historyLottery.getNum10() + "");
        String s = DateUtil.formatData("HH:mm", historyLottery.getShijian() + 3600 * 8);
        holder.setTextView(R.id.tv12, s);
    }
}
