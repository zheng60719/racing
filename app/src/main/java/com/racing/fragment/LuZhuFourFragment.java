package com.racing.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.racing.Adapter.MyLongHuAdapter;
import com.racing.R;
import com.racing.entity.TrendOne;
import com.racing.utils.DateUtil;
import com.racing.view.LoadingDialog;
import com.racing.view.MyToast;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/**
 * Created by Administrator
 * 专家分析
 */
public class LuZhuFourFragment extends Fragment {
    private View rootView;
    private TextView tv_qishu, tv_shijian;
    private String qishu;
    private long shijian;
    //list
    private String date;
    private List<String> list_da;
    private List<String> list_xiao;
    private List<String> list_da1;
    private List<String> list_xiao1;
    private List<String> list_da2;
    private List<String> list_xiao2;
    private List<String> list_da3;
    private List<String> list_xiao3;
    private List<String> list_da4;
    private List<String> list_xiao4;
    private TextView tv_1, tv_2, tv_3, tv_4, tv_5;
    private GridView gridview,gridview2,gridview3,gridview4,gridview5;
    private MyLongHuAdapter adapter;
    private MyLongHuAdapter adapter2;
    private MyLongHuAdapter adapter3;
    private MyLongHuAdapter adapter4;
    private MyLongHuAdapter adapter5;
    private LoadingDialog loadingDialog;
    private List<String> list_longhu1;
    private List<String> list_longhu2;
    private List<String> list_longhu3;
    private List<String> list_longhu4;
    private List<String> list_longhu5;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.luzhu_four, null);
        InitView(rootView);
        return rootView;
    }

    private void InitView(View rootView) {
        tv_qishu = (TextView) rootView.findViewById(R.id.tv_qishu);
        tv_shijian = (TextView) rootView.findViewById(R.id.tv_shijian);
        tv_1 = (TextView) rootView.findViewById(R.id.tv_1);
        tv_2 = (TextView) rootView.findViewById(R.id.tv_2);
        tv_3 = (TextView) rootView.findViewById(R.id.tv_3);
        tv_4 = (TextView) rootView.findViewById(R.id.tv_4);
        tv_5 = (TextView) rootView.findViewById(R.id.tv_5);
        gridview = (GridView) rootView.findViewById(R.id.gridview);
        gridview2 = (GridView) rootView.findViewById(R.id.gridview2);
        gridview3 = (GridView) rootView.findViewById(R.id.gridview3);
        gridview4 = (GridView) rootView.findViewById(R.id.gridview4);
        gridview5 = (GridView) rootView.findViewById(R.id.gridview5);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (loadingDialog == null) {
            loadingDialog = new LoadingDialog(getActivity(), R.style.LoadingDialog);
        }
        loadingDialog.show();
        //获取数据
        getData();
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
                MyToast.getToast(getActivity(),"请求失败");
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
                }catch (Exception e){
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
                    tv_1.setText("冠军龙虎今日累计 龙(" + list_da.size() + ")" + " 虎(" + list_xiao.size() + ")");
                    tv_2.setText("亚军龙虎今日累计 龙(" + list_da1.size() + ")" + " 虎(" + list_xiao1.size() + ")");
                    tv_3.setText("季军龙虎今日累计 龙(" + list_da2.size() + ")" + " 虎(" + list_xiao2.size() + ")");
                    tv_4.setText("第4名龙虎今日累计 龙(" + list_da3.size() + ")" + " 虎(" + list_xiao3.size() + ")");
                    tv_5.setText("第5名龙虎今日累计 龙(" + list_da4.size() + ")" + " 虎(" + list_xiao4.size() + ")");
                    int size1 = list_longhu1.size();
                    int count1 = 1;
                    List<List<String>>  list_data1 = new ArrayList<>();
                    for (int i = 0; i < list_longhu1.size(); i++) {
                        if (i!=list_longhu1.size()-1) {
                            if (!list_longhu1.get(i).equals(list_longhu1.get(i+1))) {
                                count1++;
                            }
                        }
                    }
                    for (int i = 0; i < count1; i++) {
                        list_data1.add(new ArrayList<String>());
                    }
                    count1=0;
                    for (int i = 0; i < list_longhu1.size(); i++) {
                        if (i!=list_longhu1.size()-1) {
                            if (!list_longhu1.get(i).equals(list_longhu1.get(i+1))) {
                                list_data1.get(count1).add(list_longhu1.get(i));
                                count1++;
                            }else{
                                list_data1.get(count1).add(list_longhu1.get(i));
                            }
                        }else{
                            list_data1.get(count1).add(list_longhu1.get(i));
                        }
                    }
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                            list_data1.size() * 68, LinearLayout.LayoutParams.FILL_PARENT);
                    gridview.setLayoutParams(params); // 设置GirdView布局参数,横向布局的关键
                    gridview.setColumnWidth(68); // 设置列表项宽
                    gridview.setHorizontalSpacing(0); // 设置列表项水平间距
                    gridview.setStretchMode(GridView.NO_STRETCH);
                    gridview.setNumColumns(size1); // 设置列数量=列表集合数
                    adapter = new MyLongHuAdapter(getActivity(), list_data1);
                    gridview.setAdapter(adapter);

                    /**
                     *3
                     */
                    int size2 = list_longhu2.size();
                    int count2 = 1;
                    List<List<String>>  list_data2 = new ArrayList<>();
                    for (int i = 0; i < list_longhu2.size(); i++) {
                        if (i!=list_longhu2.size()-1) {
                            if (!list_longhu2.get(i).equals(list_longhu2.get(i+1))) {
                                count2++;
                            }
                        }
                    }
                    for (int i = 0; i < count2; i++) {
                        list_data2.add(new ArrayList<String>());
                    }
                    count2=0;
                    for (int i = 0; i < list_longhu2.size(); i++) {
                        if (i!=list_longhu2.size()-1) {
                            if (!list_longhu2.get(i).equals(list_longhu2.get(i+1))) {
                                list_data2.get(count2).add(list_longhu2.get(i));
                                count2++;
                            }else{
                                list_data2.get(count2).add(list_longhu2.get(i));
                            }
                        }else{
                            list_data2.get(count2).add(list_longhu2.get(i));
                        }
                    }
                    LinearLayout.LayoutParams params2 = new LinearLayout.LayoutParams(
                            list_data2.size() * 68, LinearLayout.LayoutParams.FILL_PARENT);
                    gridview2.setLayoutParams(params2); // 设置GirdView布局参数,横向布局的关键
                    gridview2.setColumnWidth(68); // 设置列表项宽
                    gridview2.setHorizontalSpacing(0); // 设置列表项水平间距
                    gridview2.setStretchMode(GridView.NO_STRETCH);
                    gridview2.setNumColumns(size2); // 设置列数量=列表集合数
                    adapter2 = new MyLongHuAdapter(getActivity(), list_data2);
                    gridview2.setAdapter(adapter2);

                    /**
                     *4
                     */
                    int size3 = list_longhu3.size();
                    int count3 = 1;
                    List<List<String>>  list_data3 = new ArrayList<>();
                    for (int i = 0; i < list_longhu3.size(); i++) {
                        if (i!=list_longhu3.size()-1) {
                            if (!list_longhu3.get(i).equals(list_longhu3.get(i+1))) {
                                count3++;
                            }
                        }
                    }
                    for (int i = 0; i < count3; i++) {
                        list_data3.add(new ArrayList<String>());
                    }
                    count3=0;
                    for (int i = 0; i < list_longhu3.size(); i++) {
                        if (i!=list_longhu3.size()-1) {
                            if (!list_longhu3.get(i).equals(list_longhu3.get(i+1))) {
                                list_data3.get(count3).add(list_longhu3.get(i));
                                count3++;
                            }else{
                                list_data3.get(count3).add(list_longhu3.get(i));
                            }
                        }else{
                            list_data3.get(count3).add(list_longhu3.get(i));
                        }
                    }
                    LinearLayout.LayoutParams params3 = new LinearLayout.LayoutParams(
                            list_data3.size() * 68, LinearLayout.LayoutParams.FILL_PARENT);
                    gridview3.setLayoutParams(params3); // 设置GirdView布局参数,横向布局的关键
                    gridview3.setColumnWidth(68); // 设置列表项宽
                    gridview3.setHorizontalSpacing(0); // 设置列表项水平间距
                    gridview3.setStretchMode(GridView.NO_STRETCH);
                    gridview3.setNumColumns(size3); // 设置列数量=列表集合数
                    adapter3 = new MyLongHuAdapter(getActivity(), list_data3);
                    gridview3.setAdapter(adapter3);

                    /**
                     *5
                     */
                    int size4 = list_longhu4.size();
                    int count4 = 1;
                    List<List<String>>  list_data4 = new ArrayList<>();
                    for (int i = 0; i < list_longhu4.size(); i++) {
                        if (i!=list_longhu4.size()-1) {
                            if (!list_longhu4.get(i).equals(list_longhu4.get(i+1))) {
                                count4++;
                            }
                        }
                    }
                    for (int i = 0; i < count4; i++) {
                        list_data4.add(new ArrayList<String>());
                    }
                    count4=0;
                    for (int i = 0; i < list_longhu4.size(); i++) {
                        if (i!=list_longhu4.size()-1) {
                            if (!list_longhu4.get(i).equals(list_longhu4.get(i+1))) {
                                list_data4.get(count4).add(list_longhu4.get(i));
                                count4++;
                            }else{
                                list_data4.get(count4).add(list_longhu4.get(i));
                            }
                        }else{
                            list_data4.get(count4).add(list_longhu4.get(i));
                        }
                    }
                    LinearLayout.LayoutParams params4 = new LinearLayout.LayoutParams(
                            list_data4.size() * 68, LinearLayout.LayoutParams.FILL_PARENT);
                    gridview4.setLayoutParams(params4); // 设置GirdView布局参数,横向布局的关键
                    gridview4.setColumnWidth(68); // 设置列表项宽
                    gridview4.setHorizontalSpacing(0); // 设置列表项水平间距
                    gridview4.setStretchMode(GridView.NO_STRETCH);
                    gridview4.setNumColumns(size4); // 设置列数量=列表集合数
                    adapter4 = new MyLongHuAdapter(getActivity(), list_data4);
                    gridview4.setAdapter(adapter4);
                    /**
                     *5
                     */
                    int size5 = list_longhu5.size();
                    int count5 = 1;
                    List<List<String>>  list_data5 = new ArrayList<>();
                    for (int i = 0; i < list_longhu5.size(); i++) {
                        if (i!=list_longhu5.size()-1) {
                            if (!list_longhu5.get(i).equals(list_longhu5.get(i+1))) {
                                count5++;
                            }
                        }
                    }
                    for (int i = 0; i < count5; i++) {
                        list_data5.add(new ArrayList<String>());
                    }
                    count5=0;
                    for (int i = 0; i < list_longhu5.size(); i++) {
                        if (i!=list_longhu5.size()-1) {
                            if (!list_longhu5.get(i).equals(list_longhu5.get(i+1))) {
                                list_data5.get(count5).add(list_longhu5.get(i));
                                count5++;
                            }else{
                                list_data5.get(count5).add(list_longhu5.get(i));
                            }
                        }else{
                            list_data5.get(count5).add(list_longhu5.get(i));
                        }
                    }
                    LinearLayout.LayoutParams params5 = new LinearLayout.LayoutParams(
                            list_data4.size() * 68, LinearLayout.LayoutParams.FILL_PARENT);
                    gridview5.setLayoutParams(params5); // 设置GirdView布局参数,横向布局的关键
                    gridview5.setColumnWidth(68); // 设置列表项宽
                    gridview5.setHorizontalSpacing(0); // 设置列表项水平间距
                    gridview5.setStretchMode(GridView.NO_STRETCH);
                    gridview5.setNumColumns(size5); // 设置列数量=列表集合数
                    adapter5 = new MyLongHuAdapter(getActivity(),list_data4);
                    gridview5.setAdapter(adapter5);
                    if (loadingDialog != null) {
                        loadingDialog.cancel();
                    }
                    break;
                case 100:
                    MyToast.getToast(getActivity(), "请求失败");
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

    /**
     * 历史开奖
     */
    public void getData() {
        //创建网络处理的对象
        OkHttpUtils
                .post()
                .url("http://www.zse6.com/index.php/mobile/pk10api/get_longhu_luzhu")
                .build().execute(new StringCallback() {
            @Override
            public void onError(okhttp3.Call call, Exception e, int id) {
                MyToast.getToast(getActivity(), "请求失败");
            }

            @Override
            public void onResponse(String response, int id) {
                date = response;
                try {
                    //数据解析
                    JSONArray jsonArray = new JSONObject(date).getJSONArray("data");
                    list_da = new ArrayList<>();
                    list_xiao = new ArrayList<>();
                    list_da1 = new ArrayList<>();
                    list_xiao1 = new ArrayList<>();
                    list_da2 = new ArrayList<>();
                    list_xiao2 = new ArrayList<>();
                    list_da3 = new ArrayList<>();
                    list_xiao3 = new ArrayList<>();
                    list_da4 = new ArrayList<>();
                    list_xiao4 = new ArrayList<>();
                    list_longhu1 = new ArrayList<>();
                    list_longhu2 = new ArrayList<>();
                    list_longhu3 = new ArrayList<>();
                    list_longhu4 = new ArrayList<>();
                    list_longhu5 = new ArrayList<>();
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject o = (JSONObject) jsonArray.get(i);
                        list_longhu1.add(o.getString("longhu1"));
                        list_longhu2.add(o.getString("longhu2"));
                        list_longhu3.add(o.getString("longhu3"));
                        list_longhu4.add(o.getString("longhu4"));
                        list_longhu5.add(o.getString("longhu5"));
                        if (o.getString("longhu1").equals("龙")) {
                            list_da.add(o.getString("longhu1"));
                        } else {
                            list_xiao.add(o.getString("longhu1"));
                        }
                        if (o.getString("longhu2").equals("龙")) {
                            list_da1.add(o.getString("longhu2"));
                        } else {
                            list_xiao1.add(o.getString("longhu2"));
                        }
                        if (o.getString("longhu3").equals("龙")) {
                            list_da2.add(o.getString("longhu3"));
                        } else {
                            list_xiao2.add(o.getString("longhu3"));
                        }
                        if (o.getString("longhu4").equals("龙")) {
                            list_da3.add(o.getString("longhu4"));
                        } else {
                            list_xiao3.add(o.getString("longhu4"));
                        }
                        if (o.getString("longhu5").equals("龙")) {
                            list_da4.add(o.getString("longhu5"));
                        } else {
                            list_xiao4.add(o.getString("longhu5"));
                        }
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
    public void onDestroy() {
        super.onDestroy();
    }
}
