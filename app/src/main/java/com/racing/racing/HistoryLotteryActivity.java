package com.racing.racing;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import com.bigkoo.pickerview.TimePickerView;
import com.racing.Adapter.HistoryLotteryAdapter;
import com.racing.Adapter.Pk10Adapter;
import com.racing.R;
import com.racing.entity.HistoryLottery;
import com.racing.utils.DateUtil;
import com.racing.utils.ToastUtil;
import com.racing.view.LoadingDialog;
import com.racing.view.MyToast;
import com.racing.widget.LoadMoreRecyclerView;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


/**
 * Created by Administrator on 2017/09/24.
 * 历史开奖
 */
public class HistoryLotteryActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView tv_header_title;
    public LoadMoreRecyclerView recyclerView;
    public String data;
    public TextView tv_left;
    //存储数据
    public List<HistoryLottery> list;
    public TextView tv_right;
    //aapter
    public HistoryLotteryAdapter historyLotteryAdapter;
    private TextView tv_qishu,tv_shijian;
    private String qishu;
    private long shijian;
    private LoadingDialog loadingDialog;

    private boolean isBoolean = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history_lottery);
        InitView();
        tv_header_title.setText("历史开奖");
        if (loadingDialog == null) {
            loadingDialog = new LoadingDialog(HistoryLotteryActivity.this, R.style.LoadingDialog);
        }
        loadingDialog.show();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (isBoolean) {
                        getQishuAndTime();
                        Thread.sleep(500);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        //获取当前时间
        SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date = sDateFormat.format(new java.util.Date());
        //获取数据
        getData(date);
    }

    private void InitView() {
        tv_header_title = (TextView) findViewById(R.id.tv_header_title);
        recyclerView = (LoadMoreRecyclerView) findViewById(R.id.recycler_history_hottery);
        tv_left = (TextView) findViewById(R.id.tv_left);
        tv_left.setOnClickListener(this);
        tv_qishu = (TextView) findViewById(R.id.tv_qishu);
        tv_shijian = (TextView) findViewById(R.id.tv_shijian);
        tv_right = (TextView) findViewById(R.id.tv_right);
        tv_right.setOnClickListener(this);
    }


    //handler
    public Handler myHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 200:
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(HistoryLotteryActivity.this);
                    recyclerView.setLayoutManager(linearLayoutManager);
                    recyclerView.setHasFixedSize(true);
                    recyclerView.setAutoLoadMoreEnable(false);
                    historyLotteryAdapter = new HistoryLotteryAdapter(HistoryLotteryActivity.this,list,R.layout.history_lottery_item);
                    recyclerView.setAdapter(historyLotteryAdapter);
                    if (loadingDialog != null) {
                        loadingDialog.cancel();
                    }
                    break;
                case 300:
                    tv_qishu.setText(qishu);
                    long sj = shijian+182;
                    if (sj<0){
                        tv_shijian.setText("开奖中");
                    }else {
                        tv_shijian.setText(DateUtil.timeParse(sj));
                    }
                    break;
            }
        }
    };

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_left:
                isBoolean = false;
                HistoryLotteryActivity.this.finish();
                break;
            case R.id.tv_right:
                TimePickerView pvTime = new TimePickerView.Builder(HistoryLotteryActivity.this, new TimePickerView.OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date2, View v) {//选中事件回调
                        String time = getTime(date2);
                        //获取数据
                        getData(time);
                    }
                })
                        .setType(TimePickerView.Type.YEAR_MONTH_DAY)//默认全部显示
                        .setCancelText("取消")//取消按钮文字
                        .setSubmitText("确定")//确认按钮文字
                        .setContentSize(20)//滚轮文字大小
                        .setTitleSize(20)//标题文字大小
                        .setTitleText("请选择时间")//标题文字
                        .setOutSideCancelable(true)//点击屏幕，点在控件外部范围时，是否取消显示
                        .isCyclic(true)//是否循环滚动
                        .setTextColorCenter(Color.BLACK)//设置选中项的颜色
                        .setTitleColor(Color.BLACK)//标题文字颜色
                        .setSubmitColor(Color.BLUE)//确定按钮文字颜色
                        .setCancelColor(Color.BLUE)//取消按钮文字颜色
                        .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                        .build();
                pvTime.setDate(Calendar.getInstance());//注：根据需求来决定是否使用该方法（一般是精确到秒的情况），此项可以在弹出选择器的时候重新设置当前时间，避免在初始化之后由于时间已经设定，导致选中时间与当前时间不匹配的问题。
                pvTime.show();
                break;
        }
    }

    public String getTime(Date date) {//可根据需要自行截取数据显示
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }

    //查询时间期数
    public void getQishuAndTime() {
        //创建网络处理的对象
        OkHttpUtils
                .post()
                .url("http://www.zse6.com/index.php/mobile/pk10api/get_api")
                .build().execute(new StringCallback() {
            @Override
            public void onError(okhttp3.Call call, Exception e, int id) {
                MyToast.getToast(HistoryLotteryActivity.this,"请求失败");
            }

            @Override
            public void onResponse(String response, int id) {
                try {
                    JSONObject jsonObj = new JSONObject(response).getJSONObject("next");
                    qishu = jsonObj.getString("qishu");
                    shijian = jsonObj.getLong("shijian_chazhi");
                    Message message = new Message();
                    message.what = 300;
                    myHandler.sendMessage(message);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * 历史开奖
     */
    public void getData(String shijian) {
        //创建网络处理的对象
        OkHttpUtils
                .post()
                .url("http://www.zse6.com/index.php/mobile/pk10api/get_lishi")
                .addParams("shijian",shijian)
                .build().execute(new StringCallback() {
            @Override
            public void onError(okhttp3.Call call, Exception e, int id) {
                MyToast.getToast(HistoryLotteryActivity.this,"请求失败");
            }

            @Override
            public void onResponse(String response, int id) {
                try {
                    JSONArray jsonObj = new JSONObject(response).getJSONArray("data");
                    list = new ArrayList<>();
                    for (int i = 0;i<jsonObj.length();i++){
                        JSONObject o = (JSONObject) jsonObj.get(i);
                        HistoryLottery historyLottery = new HistoryLottery();
                        historyLottery.setQishu(o.getInt("qishu"));
                        historyLottery.setNum1(o.getInt("num1"));
                        historyLottery.setNum2(o.getInt("num2"));
                        historyLottery.setNum3(o.getInt("num3"));
                        historyLottery.setNum4(o.getInt("num4"));
                        historyLottery.setNum5(o.getInt("num5"));
                        historyLottery.setNum6(o.getInt("num6"));
                        historyLottery.setNum7(o.getInt("num7"));
                        historyLottery.setNum8(o.getInt("num8"));
                        historyLottery.setNum9(o.getInt("num9"));
                        historyLottery.setNum10(o.getInt("num10"));
                        historyLottery.setShijian(o.getLong("shijian"));
                        list.add(historyLottery);
                    }
                    Message message = new Message();
                    message.what = 200;
                    myHandler.sendMessage(message);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN){
            finish();
            isBoolean = false;
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
