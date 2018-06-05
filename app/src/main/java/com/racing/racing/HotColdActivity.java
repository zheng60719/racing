package com.racing.racing;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.racing.Adapter.MyHotColdAdapter;
import com.racing.R;
import com.racing.entity.HotCold;
import com.racing.entity.HotColdItem;
import com.racing.utils.DateUtil;
import com.racing.view.LoadingDialog;
import com.racing.view.MyToast;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/09/24.
 * 冷热分析
 */
public class HotColdActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView tv_qishu,tv_shijian;
    private TextView tv_header_title;
    public TextView tv_left;
    private String num1,num2,num3,num4,num5,num6,num7,num8,num9,num10,qishu1;
    private Long shijian1;
    private TextView tv_num1,tv_num2,tv_num3,tv_num4,tv_num5,tv_num6,tv_num7,tv_num8,tv_num9,tv_num10,tv_qishu1,tv_shijian1;
    private String qishu;
    private long shijian;
    //冷热分析数据
    private ListView recycler_hot_cold;
    //list
    private List<HotCold> list_hot_cold;
    //adapter
    private MyHotColdAdapter adapter;
    //hotcat
    private LoadingDialog loadingDialog;

    private boolean isBoolean = true;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hot_cold);
        InitView();
        tv_header_title.setText("冷热分析");
        if (loadingDialog == null) {
            loadingDialog = new LoadingDialog(HotColdActivity.this, R.style.LoadingDialog);
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
        tv_qishu = (TextView) findViewById(R.id.tv_qishu);
        tv_shijian = (TextView) findViewById(R.id.tv_shijian);
        tv_header_title = (TextView) findViewById(R.id.tv_header_title);
        recycler_hot_cold = (ListView) findViewById(R.id.recycler_hot_cold);
        tv_left = (TextView) findViewById(R.id.tv_left);
        tv_left.setOnClickListener(this);
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
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_left:
                isBoolean = false;
                HotColdActivity.this.finish();
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
                MyToast.getToast(HotColdActivity.this,"请求失败");
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
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }
    //handler
    public Handler myHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 200:
                    adapter = new MyHotColdAdapter(HotColdActivity.this,list_hot_cold);
                    recycler_hot_cold.setAdapter(adapter);
                    if (loadingDialog != null) {
                        loadingDialog.cancel();
                    }
                    break;
                case 100:
                    MyToast.getToast(HotColdActivity.this,"请求失败");
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
                    tv_qishu1.setText(qishu1+"开奖结果");
                    String s = DateUtil.formatData("yyyy-MM-dd HH:mm:ss", shijian1 + 8 * 3600);
                    tv_shijian1.setText(s);
                    break;
            }
        }
    };

    public void getData() {
        //创建网络处理的对象
        OkHttpUtils
                .post()
                .url("http://www.zse6.com/index.php/mobile/pk10api/get_lengre")
                .build().execute(new StringCallback() {
            @Override
            public void onError(okhttp3.Call call, Exception e, int id) {
                MyToast.getToast(HotColdActivity.this,"请求失败");
            }

            @Override
            public void onResponse(String response, int id) {
                try {
                    jsonTo(response);
                    Message message = new Message();
                    message.what = 200;
                    myHandler.sendMessage(message);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }

    private void jsonTo(String json) {
        list_hot_cold = new ArrayList<>();
        int k = 0;
        int p = 0;
        int o = 0;
        try{
            JSONObject jsonObject = new JSONObject(json);
            JSONObject jsonObject1 = jsonObject.getJSONObject("data");
            Iterator<String> keys = jsonObject1.keys();
            Map<String,List<HotColdItem>> map_wen  = new HashMap<>();
            Map<String,List<HotColdItem>> map_re = new HashMap<>();
            Map<String,List<HotColdItem>> map_leng = new HashMap<>();
            HotCold hotCold = new HotCold();
            while (keys.hasNext()) {
                k++;
                p++;
                o++;
                String key1 = keys.next();
                JSONObject jsonObject2 = jsonObject1.getJSONObject(key1);
                JSONArray jsonArray_re = jsonObject2.getJSONArray("re");
                List<HotColdItem> list_re = new ArrayList<>();
                for (int i = 0; i < jsonArray_re.length(); i++) {
                    HotColdItem hotColdItem_re = new HotColdItem();
                    JSONObject jsonObject3 = (JSONObject) jsonArray_re.get(i);
                    hotColdItem_re.setCount(jsonObject3.getString("count"));
                    hotColdItem_re.setNum1(jsonObject3.getString("num" + k));
                    list_re.add(hotColdItem_re);
                }
                map_re.put(key1, list_re);
                hotCold.setList_re(map_re);
                JSONArray jsonArray_wen = jsonObject2.getJSONArray("wen");
                List<HotColdItem> list_wen = new ArrayList<>();
                for (int j = 0; j < jsonArray_wen.length(); j++) {
                    HotColdItem hotColdItem_wen = new HotColdItem();
                    JSONObject jsonObject3 = (JSONObject) jsonArray_wen.get(j);
                    hotColdItem_wen.setCount(jsonObject3.getString("count"));
                    hotColdItem_wen.setNum1(jsonObject3.getString("num" + p));
                    list_wen.add(hotColdItem_wen);
                }
                map_wen.put(key1, list_wen);
                hotCold.setList_wen(map_wen);
                JSONArray jsonArray_leng = jsonObject2.getJSONArray("leng");
                Log.i("aaa",jsonArray_leng.length()+"oooooooooooooo");
                if (jsonArray_leng.length() > 0) {
                    List<HotColdItem> list_leng = new ArrayList<>();
                    for (int ko = 0; ko < jsonArray_leng.length(); ko++) {
                        HotColdItem hotColdItem_leng = new HotColdItem();
                        JSONObject jsonObject3 = (JSONObject) jsonArray_wen.get(ko);
                        hotColdItem_leng.setCount(jsonObject3.getString("count"));
                        hotColdItem_leng.setNum1(jsonObject3.getString("num"+o));
                        list_leng.add(hotColdItem_leng);
                    }
                    map_leng.put(key1, list_leng);
                    hotCold.setList_leng(map_leng);
                }else{
                    List<HotColdItem> list_leng = new ArrayList<>();
                    map_leng.put(key1, list_leng);
                    hotCold.setList_leng(map_leng);
                }
                list_hot_cold.add(hotCold);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
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
