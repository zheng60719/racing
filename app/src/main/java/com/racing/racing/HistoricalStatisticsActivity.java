package com.racing.racing;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.racing.Adapter.MyHistoricalStatisticsAdapter;
import com.racing.R;
import com.racing.entity.HistoricalStatistics;
import com.racing.entity.HistoricalStatisticsItem;
import com.racing.utils.DateUtil;
import com.racing.view.LoadingDialog;
import com.racing.view.MyToast;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/09/24.
 * 历史统计
 */
public class HistoricalStatisticsActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView tv_header_title;
    private TextView tv_left;
    private String qishu;
    private long shijian;
    private TextView tv_qishu, tv_shijian;
    private String num1, num2, num3, num4, num5, num6, num7, num8, num9, num10, qishu1;
    private Long shijian1;
    private TextView tv_num1, tv_num2, tv_num3, tv_num4, tv_num5, tv_num6, tv_num7, tv_num8, tv_num9, tv_num10, tv_qishu1, tv_shijian1;
    private ListView recycler_history_statistics;
    //adapter
    private MyHistoricalStatisticsAdapter adapter;
    private RadioGroup rgBottomBar;
    private RadioButton radio_danshuang;
    private RadioButton radio_daxiao;
    private RadioButton radio_longhu;
    //单双，大小，龙虎
    private TextView tv_1, tv_2, tv_3, tv_4, tv_5, tv_6;
    //historicalStatistics
    private List<HistoricalStatistics> historicalStatisticses;
    private LoadingDialog loadingDialog;
    private boolean isBoolean = true;

    //历史开奖
    private TextView tv_right;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.historicalstatistics);
        InitView();
        tv_header_title.setText("历史统计");
        if (loadingDialog == null) {
            loadingDialog = new LoadingDialog(HistoricalStatisticsActivity.this, R.style.LoadingDialog);
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
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH,-7);
        Date date1 = calendar.getTime();
        SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date = sDateFormat.format(date1);

        getData(date);
        //默认选中第一个
        radio_danshuang.setChecked(true);
        //点击单双
        radio_danshuang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv_1.setText("单");
                tv_2.setText("双");
                tv_3.setText("单");
                tv_4.setText("双");
                tv_5.setText("单");
                tv_6.setText("双");
                adapter = new MyHistoricalStatisticsAdapter(HistoricalStatisticsActivity.this, historicalStatisticses, 1);
                recycler_history_statistics.setAdapter(adapter);
            }
        });
        //点击大小
        radio_daxiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv_1.setText("大");
                tv_2.setText("小");
                tv_3.setText("大");
                tv_4.setText("小");
                tv_5.setText("大");
                tv_6.setText("小");
                adapter = new MyHistoricalStatisticsAdapter(HistoricalStatisticsActivity.this, historicalStatisticses, 2);
                recycler_history_statistics.setAdapter(adapter);
            }
        });
        //点击龙虎
        radio_longhu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv_1.setText("龙");
                tv_2.setText("虎");
                tv_3.setText("龙");
                tv_4.setText("虎");
                tv_5.setText("龙");
                tv_6.setText("虎");
                adapter = new MyHistoricalStatisticsAdapter(HistoricalStatisticsActivity.this, historicalStatisticses, 3);
                recycler_history_statistics.setAdapter(adapter);
            }
        });
    }

    private void InitView() {
        rgBottomBar = (RadioGroup) findViewById(R.id.rgBottomBar);
        radio_danshuang = (RadioButton) findViewById(R.id.radio_danshuang);
        radio_daxiao = (RadioButton) findViewById(R.id.radio_daxiao);
        radio_longhu = (RadioButton) findViewById(R.id.radio_longhu);
        recycler_history_statistics = (ListView) findViewById(R.id.recycler_history_statistics);
        tv_header_title = (TextView) findViewById(R.id.tv_header_title);
        tv_left = (TextView) findViewById(R.id.tv_left);
        tv_left.setOnClickListener(this);
        tv_qishu = (TextView) findViewById(R.id.tv_qishu);
        tv_shijian = (TextView) findViewById(R.id.tv_shijian);
        tv_num1 = (TextView) findViewById(R.id.tv_num1);
        tv_num2 = (TextView) findViewById(R.id.tv_num2);
        tv_num3 = (TextView) findViewById(R.id.tv_num3);
        tv_num4 = (TextView) findViewById(R.id.tv_num4);
        tv_num5 = (TextView) findViewById(R.id.tv_num5);
        tv_num6 = (TextView) findViewById(R.id.tv_num6);
        tv_num7 = (TextView) findViewById(R.id.tv_num7);
        tv_num8 = (TextView) findViewById(R.id.tv_num8);
        tv_num9 = (TextView) findViewById(R.id.tv_num9);
        tv_num10 = (TextView) findViewById(R.id.tv_num10);
        tv_qishu1 = (TextView) findViewById(R.id.tv_jieguo);
        tv_shijian1 = (TextView) findViewById(R.id.tv_shijian1);
        tv_1 = (TextView) findViewById(R.id.tv_1);
        tv_2 = (TextView) findViewById(R.id.tv_2);
        tv_3 = (TextView) findViewById(R.id.tv_3);
        tv_4 = (TextView) findViewById(R.id.tv_4);
        tv_5 = (TextView) findViewById(R.id.tv_5);
        tv_6 = (TextView) findViewById(R.id.tv_6);
        tv_right = (TextView) findViewById(R.id.tv_right);
        tv_right.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_left:
                isBoolean = false;
                HistoricalStatisticsActivity.this.finish();
                break;
            case R.id.tv_right:
                Intent intent = new Intent();
                intent.setClass(HistoricalStatisticsActivity.this, HistoryLotteryActivity.class);
                startActivity(intent);
                break;
        }
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
                MyToast.getToast(HistoricalStatisticsActivity.this, "请求失败");
            }

            @Override
            public void onResponse(String response, int id) {
                try {
                    JSONObject jsonObj = new JSONObject(response).getJSONObject("next");
                    qishu = jsonObj.getString("qishu");
                    shijian = jsonObj.getLong("shijian_chazhi");
                    JSONObject jsonObj1 = new JSONObject(response).getJSONObject("now");
                    num1 = jsonObj1.getString("num1");
                    num2 = jsonObj1.getString("num2");
                    num3 = jsonObj1.getString("num3");
                    num4 = jsonObj1.getString("num4");
                    num5 = jsonObj1.getString("num5");
                    num6 = jsonObj1.getString("num6");
                    num7 = jsonObj1.getString("num7");
                    num8 = jsonObj1.getString("num8");
                    num9 = jsonObj1.getString("num9");
                    num10 = jsonObj1.getString("num10");
                    qishu1 = jsonObj1.getString("qishu");
                    shijian1 = jsonObj1.getLong("shijian");
                    Message message = new Message();
                    message.what = 300;
                    myHandler.sendMessage(message);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    //handler
    public Handler myHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 200:
                    adapter = new MyHistoricalStatisticsAdapter(HistoricalStatisticsActivity.this, historicalStatisticses, 1);
                    recycler_history_statistics.setAdapter(adapter);
                    if (loadingDialog != null) {
                        loadingDialog.cancel();
                    }
                    break;
                case 100:
                    MyToast.getToast(HistoricalStatisticsActivity.this, "请求失败");
                    break;
                case 300:
                    tv_qishu.setText(qishu);
                    long sj = shijian+182;
                    if (sj<0){
                        tv_shijian.setText("开奖中");
                    }else {
                        tv_shijian.setText(DateUtil.timeParse(sj));
                    }
                    tv_num1.setText(num1);
                    tv_num2.setText(num2);
                    tv_num3.setText(num3);
                    tv_num4.setText(num4);
                    tv_num5.setText(num5);
                    tv_num6.setText(num6);
                    tv_num7.setText(num7);
                    tv_num8.setText(num8);
                    tv_num9.setText(num9);
                    tv_num10.setText(num10);
                    tv_qishu1.setText(qishu1 + "开奖结果");
                    String s = DateUtil.formatData("yyyy-MM-dd HH:mm:ss", shijian1 + 8 * 3600);
                    tv_shijian1.setText(s);
                    break;
            }
        }
    };

    public void getData(String shijian) {
        //创建网络处理的对象
        OkHttpUtils
                .post()
                .url("http://www.zse6.com/index.php/mobile/pk10api/get_lishi_tongji")
                .addParams("shijian", shijian)
                .build().execute(new StringCallback() {
            @Override
            public void onError(okhttp3.Call call, Exception e, int id) {
                MyToast.getToast(HistoricalStatisticsActivity.this, "请求失败");
            }

            @Override
            public void onResponse(String response, int id) {
                try {
                    //数据解析
                    historicalStatisticses = jsonTo(response);
                    Message message = new Message();
                    message.what = 200;
                    myHandler.sendMessage(message);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }


    //解析数据
    public List<HistoricalStatistics> jsonTo(String json) {
        List<HistoricalStatistics> list1 = null;
        try {
            JSONArray jsonArray = new JSONObject(json).getJSONArray("data");
            list1 = new ArrayList<>();
            for (int i = 0; i < jsonArray.length(); i++) {
                HistoricalStatistics data1 = new HistoricalStatistics();
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                data1.setDay(jsonObject.getString("day"));
                data1.setGyh_da(jsonObject.getString("gyh_da"));
                data1.setGyh_xiao(jsonObject.getString("gyh_xiao"));
                data1.setGyh_dan(jsonObject.getString("gyh_dan"));
                data1.setGyh_shuang(jsonObject.getString("gyh_shuang"));
                Iterator<String> keys = jsonObject.keys();
                Map<String, HistoricalStatisticsItem> map = new HashMap<>();
                while (keys.hasNext()) {
                    String key1 = keys.next();
                    Object o = null;
                    if (isNumeric(key1)) {
                        JSONObject jsonObject2 = jsonObject.getJSONObject(key1);
                        HistoricalStatisticsItem data2 = new HistoricalStatisticsItem();
                        data2.setDa(jsonObject2.optString("da"));
                        data2.setDan(jsonObject2.optString("dan"));
                        data2.setHs_hu(jsonObject2.optString("hu"));
                        data2.setHs_long(jsonObject2.optString("long"));
                        data2.setShuang(jsonObject2.optString("shuang"));
                        data2.setXiao(jsonObject2.optString("xiao"));
                        map.put(key1, data2);
                    }
                    if (map != null) {
                        data1.setMap(map);
                    }
                }
                list1.add(data1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list1;
    }

    public static boolean isNumeric(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            finish();
            isBoolean = false;
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
