package com.racing.racing;

import android.net.http.SslError;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.racing.Adapter.HistoryLotteryAdapter;
import com.racing.R;
import com.racing.entity.HistoryLottery;
import com.racing.utils.DateUtil;
import com.racing.view.LoadingDialog;
import com.racing.view.MyToast;
import com.racing.widget.LoadMoreRecyclerView;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/09/24.
 * 开奖直播
 */
public class LiveLotteryActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView tv_header_title;
    private TextView tv_left;
    private WebView webview;
    private String num1, num2, num3, num4, num5, num6, num7, num8, num9, num10, qishu1;
    private Long shijian1;
    private TextView tv_num1, tv_num2, tv_num3, tv_num4, tv_num5, tv_num6, tv_num7, tv_num8, tv_num9, tv_num10, tv_qishu1, tv_shijian1;
    public LoadMoreRecyclerView recycler_liveLottery;
    //存储数据
    public List<HistoryLottery> list;
    //apter
    public HistoryLotteryAdapter historyLotteryAdapter;
    private LoadingDialog loadingDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.live_lottery);
        InitView();
        tv_header_title.setText("开奖直播");
        if (loadingDialog == null) {
            loadingDialog = new LoadingDialog(LiveLotteryActivity.this, R.style.LoadingDialog);
        }
        loadingDialog.show();
        getQishuAndTime();
        //获取数据
        //获取当前时间
        SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date = sDateFormat.format(new java.util.Date());
        getData(date);
        WebSettings webSettings = webview.getSettings();
        // 让WebView能够执行javaScript
        webSettings.setJavaScriptEnabled(true);
        // 让JavaScript可以自动打开windows
        webSettings.setDisplayZoomControls(false);
        webSettings.setLoadWithOverviewMode(true);
//        webSettings.setDomStorageEnabled(true);
        webSettings.setUseWideViewPort(true);
        //覆盖WebView默认使用第三方或系统默认浏览器打开网页的行为，使网页用WebView打开
        webview.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // TODO Auto-generated method stub
                //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                view.loadUrl(url);
                return true;
            }
        });
        webview.loadUrl("http://www.zse6.com//index.php/home/bjpk10/tv/zoom/28");
    }

    private void InitView() {
        recycler_liveLottery = (LoadMoreRecyclerView) findViewById(R.id.recycler_liveLottery);
        webview = (WebView) findViewById(R.id.webview);
        tv_header_title = (TextView) findViewById(R.id.tv_header_title);
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
        switch (view.getId()) {
            case R.id.tv_left:
                LiveLotteryActivity.this.finish();
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
                MyToast.getToast(LiveLotteryActivity.this, "请求失败");
            }

            @Override
            public void onResponse(String response, int id) {
                try {
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

    public Handler myHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 200:
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(LiveLotteryActivity.this);
                    recycler_liveLottery.setLayoutManager(linearLayoutManager);
                    recycler_liveLottery.setHasFixedSize(true);
                    recycler_liveLottery.setAutoLoadMoreEnable(false);
                    historyLotteryAdapter = new HistoryLotteryAdapter(LiveLotteryActivity.this, list, R.layout.history_lottery_item);
                    recycler_liveLottery.setAdapter(historyLotteryAdapter);
                    if (loadingDialog != null) {
                        loadingDialog.cancel();
                    }
                    break;
                case 100:
                    break;
                case 300:
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

    /**
     * 历史开奖
     */
    public void getData(String shijian) {
        //创建网络处理的对象
        OkHttpUtils
                .post()
                .url("http://www.zse6.com/index.php/mobile/pk10api/get_lishi")
                .addParams("shijian", shijian)
                .build().execute(new StringCallback() {
            @Override
            public void onError(okhttp3.Call call, Exception e, int id) {
                MyToast.getToast(LiveLotteryActivity.this, "请求失败");
            }

            @Override
            public void onResponse(String response, int id) {
                try {
                    JSONArray jsonObj = new JSONObject(response).getJSONArray("data");
                    list = new ArrayList<>();
                    for (int i = 0; i < jsonObj.length(); i++) {
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
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
