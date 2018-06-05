package com.racing.racing;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.racing.Adapter.MyTodayNumberAdapter;
import com.racing.R;
import com.racing.entity.TodayNumber;
import com.racing.entity.TodayNumberItem;
import com.racing.utils.DateUtil;
import com.racing.view.LoadingDialog;
import com.racing.view.MyToast;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Administrator on 2017/09/24.
 * 今日号码
 */
public class TodayNumberActivity extends AppCompatActivity {
    private TextView tv_header_title;
    private TextView tv_left;
    private String qishu;
    private long shijian;
    private TextView tv_qishu,tv_shijian;
    //recycler
    private ListView recycler_number;
    //adapter
    private MyTodayNumberAdapter adapter;
    private TodayNumber todayNumber;
    private LoadingDialog loadingDialog;

    private boolean isBoolean = true;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.today_number);
        InitView();
        tv_header_title.setText("今日号码");
        if (loadingDialog == null) {
            loadingDialog = new LoadingDialog(TodayNumberActivity.this, R.style.LoadingDialog);
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
        //获取数据
        getData();
    }

    private void InitView() {
        tv_header_title = (TextView) findViewById(R.id.tv_header_title);
        tv_left = (TextView) findViewById(R.id.tv_left);
        tv_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isBoolean = false;
                TodayNumberActivity.this.finish();
            }
        });
        tv_qishu = (TextView) findViewById(R.id.tv_qishu);
        tv_shijian = (TextView) findViewById(R.id.tv_shijian);
        recycler_number = (ListView) findViewById(R.id.recycler_number);
    }
    //handler
    public Handler myHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 200:
                    adapter = new MyTodayNumberAdapter(TodayNumberActivity.this,todayNumber);
                    recycler_number.setAdapter(adapter);
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
    //查询时间期数
    public void getQishuAndTime() {
        //创建网络处理的对象
        OkHttpUtils
                .post()
                .url("http://www.zse6.com/index.php/mobile/pk10api/get_api")
                .build().execute(new StringCallback() {
            @Override
            public void onError(okhttp3.Call call, Exception e, int id) {
                MyToast.getToast(TodayNumberActivity.this,"请求失败");
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
     * 请求今日号码数据
     */
    public void getData() {
        //创建网络处理的对象
        OkHttpUtils
                .post()
                .url("http://www.zse6.com/index.php/mobile/pk10api/get_jinri")
                .build().execute(new StringCallback() {
            @Override
            public void onError(okhttp3.Call call, Exception e, int id) {
                MyToast.getToast(TodayNumberActivity.this,"请求失败");
            }

            @Override
            public void onResponse(String response, int id) {
                try {
                    todayNumber = jsonTo(response);
                    Message message = new Message();
                    message.what = 200;
                    myHandler.sendMessage(message);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }

    public TodayNumber jsonTo(String json) {
        TodayNumber msg = new TodayNumber();
        try {
            JSONObject jsonObject = new JSONObject(json);
            msg.setIng(jsonObject.optString("ing"));
            msg.setMsg(jsonObject.optString("msg"));
            JSONObject jsonObject1 = jsonObject.getJSONObject("data");
            Map<String, Map<String, TodayNumberItem>> mapMap = new HashMap<>();
            Iterator<String> keys = jsonObject1.keys();
            while (keys.hasNext()) {
                String key1 = keys.next();
                JSONObject jsonObject2 = jsonObject1.getJSONObject(key1);
                Iterator<String> keys1 = jsonObject2.keys();
                Map<String, TodayNumberItem > map = new HashMap<>();
                while (keys1.hasNext()) {
                    String key2 = keys1.next();
                    JSONObject jsonObject3 = jsonObject2.getJSONObject(key2);
                    TodayNumberItem  data = new TodayNumberItem ();
                    data.setWeikai(jsonObject3.optString("weikai"));
                    data.setZongkai(jsonObject3.optString("zongkai"));
                    map.put(key2, data);
                }
                mapMap.put(key1, map);
            }
            msg.setData(mapMap);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return msg;
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
