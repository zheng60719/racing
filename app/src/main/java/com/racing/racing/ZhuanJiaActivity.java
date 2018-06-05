package com.racing.racing;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.TextView;

import com.racing.Adapter.HistoryLotteryAdapter;
import com.racing.Adapter.ZhuanJiaAdapter;
import com.racing.R;
import com.racing.entity.FenXi;
import com.racing.entity.HistoryLottery;
import com.racing.entity.Level;
import com.racing.entity.User;
import com.racing.entity.ZhuanJiaFenxi;
import com.racing.view.LoadingDialog;
import com.racing.view.MyToast;
import com.racing.widget.LoadMoreRecyclerView;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Administrator on 2017/09/24.
 * 历史开奖
 */
public class ZhuanJiaActivity extends AppCompatActivity implements View.OnClickListener,ZhuanJiaAdapter.ZhuanJiaOnClick {
    private TextView tv_header_title;
    public LoadMoreRecyclerView recycler_zhuanjia_list;
    public TextView tv_left;
    private LoadingDialog loadingDialog;
    private ZhuanJiaAdapter zhuanJiaAdapter;
    private List<ZhuanJiaFenxi> list_zhuanjiafenxi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zhuanjia);
        InitView();
        tv_header_title.setText("专家列表");
        if (loadingDialog == null) {
            loadingDialog = new LoadingDialog(ZhuanJiaActivity.this, R.style.LoadingDialog);
        }
        loadingDialog.show();
        getData();
    }

    private void InitView() {
        tv_header_title = (TextView) findViewById(R.id.tv_header_title);
        recycler_zhuanjia_list = (LoadMoreRecyclerView) findViewById(R.id.recycler_zhuanjia_list);
        tv_left = (TextView) findViewById(R.id.tv_left);
        tv_left.setOnClickListener(this);
    }


    //handler
    public Handler myHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 200:
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ZhuanJiaActivity.this);
                    recycler_zhuanjia_list.setLayoutManager(linearLayoutManager);
                    recycler_zhuanjia_list.setHasFixedSize(true);
                    recycler_zhuanjia_list.setAutoLoadMoreEnable(false);
                    zhuanJiaAdapter = new ZhuanJiaAdapter(ZhuanJiaActivity.this,list_zhuanjiafenxi,R.layout.fenxi_item);
                    recycler_zhuanjia_list.setAdapter(zhuanJiaAdapter);
                    if (loadingDialog != null) {
                        loadingDialog.cancel();
                    }
                    break;
                case 300:
                    break;
            }
        }
    };

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_left:
                ZhuanJiaActivity.this.finish();
                break;
        }
    }


    /**
     * 获取数据
     */
    private void getData() {
        //创建网络处理的对象
        OkHttpUtils
                .post()
                .url("http://www.zse6.com/index.php/mobile/ajax/zhuanjia_list")
                .build().execute(new StringCallback() {
            @Override
            public void onError(okhttp3.Call call, Exception e, int id) {
                MyToast.getToast(ZhuanJiaActivity.this, "请求失败");
            }

            @Override
            public void onResponse(String response, int id) {
                try {
                    list_zhuanjiafenxi = new ArrayList<>();
                    JSONArray jsonObj = new JSONObject(response).getJSONArray("data");
                    for (int i = 0; i < jsonObj.length(); i++) {
                        JSONObject o = (JSONObject) jsonObj.get(i);
                        ZhuanJiaFenxi fenxi = new ZhuanJiaFenxi();
                        String level_str = o.getString("level");
                        fenxi.setNicheng(o.getString("nicheng"));
                        fenxi.setId(o.getInt("id"));
                        fenxi.setTx(o.getString("tx"));
                        fenxi.setMobile(o.getString("mobile"));
                        fenxi.setXingming(o.getString("xingming"));
                        fenxi.setBeizhu(o.getString("beizhu"));
                        fenxi.setAll_yuce_num(o.getString("all_yuce_num"));
                        fenxi.setZaishou_yuce_num(o.getString("zaishou_yuce_num"));
                        fenxi.setYuce_ok_num(o.getString("yuce_ok_num"));
                        fenxi.setFensi_num(o.getString("fensi_num"));
                        List<Level> list_level = new ArrayList<>();
                        Level level = new Level();
                        JSONObject jsonObject = new JSONObject(level_str);
                        String logo = jsonObject.getString("logo");
                        level.setLogo(logo);
                        list_level.add(level);
                        fenxi.setList_level(list_level);
                        list_zhuanjiafenxi.add(fenxi);
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

    @Override
    public void onItemClick(int position) {
        //专家详情
        Intent intent = new Intent(ZhuanJiaActivity.this,ZhuanJiaDetailActivity.class);
        intent.putExtra("id",list_zhuanjiafenxi.get(position).getId());
        startActivity(intent);
    }
}
