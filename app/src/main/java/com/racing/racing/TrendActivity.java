package com.racing.racing;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.racing.Adapter.MyDaxiaoAdapter;
import com.racing.R;
import com.racing.fragment.LuZhuFourFragment;
import com.racing.fragment.LuZhuOneFragment;
import com.racing.fragment.LuZhuThreeFragment;
import com.racing.fragment.LuZhuTwoFragment;
import com.racing.utils.DateUtil;
import com.racing.view.MyToast;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Administrator on 2017/09/24.
 * 路珠走势
 */
public class TrendActivity extends AppCompatActivity implements View.OnClickListener {
    private String qishu;
    private long shijian;
    private TextView tv_header_title;
    private TextView tv_qishu, tv_shijian;
    public TextView tv_left;
    private RadioButton one;
    private RadioButton two;
    private RadioButton three;
    private RadioButton four;
    public static FragmentManager fm;
    private RadioGroup rgBottomBar;
    private boolean isBoolean = true;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_zoushi);
        InitView();
        tv_header_title.setText("路珠走势");
        initListener();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (isBoolean) {
                        getQishuAndTime();
                        Thread.sleep(800);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
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
                MyToast.getToast(TrendActivity.this, "请求失败");
            }

            @Override
            public void onResponse(String response, int id) {
                try {
                    //数据解析
                    JSONObject jsonObj = new JSONObject(response).getJSONObject("next");
                    qishu = jsonObj.getString("qishu");
                    shijian = jsonObj.getLong("shijian_chazhi");
                    Message message = new Message();
                    message.what = 300;
                    myHandler.sendMessage(message);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    private void InitView() {
        rgBottomBar = (RadioGroup) findViewById(R.id.rgBottomBar);
        tv_header_title = (TextView) findViewById(R.id.tv_header_title);
        tv_left = (TextView) findViewById(R.id.tv_left);
        tv_qishu = (TextView) findViewById(R.id.tv_qishu);
        tv_shijian = (TextView) findViewById(R.id.tv_shijian);
        tv_left.setOnClickListener(this);
        one = (RadioButton) findViewById(R.id.one);
        two = (RadioButton) findViewById(R.id.two);
        three = (RadioButton) findViewById(R.id.three);
        four = (RadioButton) findViewById(R.id.four);
        fm = getSupportFragmentManager();
        initFragment(new LuZhuOneFragment());
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_left:
                TrendActivity.this.finish();
                break;
        }
    }


    // 初始化Fragment(FragmentActivity中呼叫)
    public static void initFragment(Fragment f) {
        changeFragment(f, true);
    }

    private static void changeFragment(Fragment f, boolean init) {
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.frame, f);
        if (!init)
            ft.addToBackStack(null);
        ft.commit();
    }
    public void initListener() {
        one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeFragment(new LuZhuOneFragment());
            }
        });
        two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeFragment(new LuZhuTwoFragment());
            }
        });
        three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeFragment(new LuZhuThreeFragment());
            }
        });
        four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeFragment(new LuZhuFourFragment());
            }
        });
    }
    // 切換Fragment
    public static void changeFragment(Fragment f) {
        changeFragment(f, false);
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN){
                TrendActivity.this.finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    //handler
    public Handler myHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 300:
                    tv_qishu.setText(qishu);
                    long sj = shijian+182;
                    long sj1 = shijian;
                    if (shijian<0&&shijian>60){
                        tv_shijian.setText("开奖中");
                    }else {
                        tv_shijian.setText(DateUtil.timeParse(sj));
                    }
                    break;
            }
        }
    };
}
