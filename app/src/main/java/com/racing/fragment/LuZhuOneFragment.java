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

import com.racing.Adapter.MyDaxiaoAdapter;
import com.racing.R;
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
 */
public class LuZhuOneFragment extends Fragment {
    private View rootView;
    private TextView tv_qishu, tv_shijian;
    private String qishu;
    private long shijian;
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
    private List<String> list_da5;
    private List<String> list_xiao5;
    private List<String> list_da6;
    private List<String> list_xiao6;
    private List<String> list_da7;
    private List<String> list_xiao7;
    private List<String> list_da8;
    private List<String> list_xiao8;
    private List<String> list_da9;
    private List<String> list_xiao9;
    private TextView tv_1, tv_2, tv_3, tv_4, tv_5, tv_6, tv_7, tv_8, tv_9, tv_10;
    private GridView gridview,gridview2,gridview3,gridview4,gridview5,gridview6,gridview7,gridview8,gridview9,gridview10;
    private MyDaxiaoAdapter adapter;
    private MyDaxiaoAdapter adapter2;
    private MyDaxiaoAdapter adapter3;
    private MyDaxiaoAdapter adapter4;
    private MyDaxiaoAdapter adapter5;
    private MyDaxiaoAdapter adapter6;
    private MyDaxiaoAdapter adapter7;
    private MyDaxiaoAdapter adapter8;
    private MyDaxiaoAdapter adapter9;
    private MyDaxiaoAdapter adapter10;
    private List<String> list_daxiao;
    private List<String> list_daxiao1;
    private List<String> list_daxiao2;
    private List<String> list_daxiao3;
    private List<String> list_daxiao4;
    private List<String> list_daxiao5;
    private List<String> list_daxiao6;
    private List<String> list_daxiao7;
    private List<String> list_daxiao8;
    private List<String> list_daxiao9;
    private LoadingDialog loadingDialog;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.luzhu_one, null);
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
        tv_6 = (TextView) rootView.findViewById(R.id.tv_6);
        tv_7 = (TextView) rootView.findViewById(R.id.tv_7);
        tv_8 = (TextView) rootView.findViewById(R.id.tv_8);
        tv_9 = (TextView) rootView.findViewById(R.id.tv_9);
        tv_10 = (TextView) rootView.findViewById(R.id.tv_10);
        gridview = (GridView) rootView.findViewById(R.id.gridview);
        gridview2 = (GridView) rootView.findViewById(R.id.gridview2);
        gridview3 = (GridView) rootView.findViewById(R.id.gridview3);
        gridview4 = (GridView) rootView.findViewById(R.id.gridview4);
        gridview5 = (GridView) rootView.findViewById(R.id.gridview5);
        gridview6 = (GridView) rootView.findViewById(R.id.gridview6);
        gridview7 = (GridView) rootView.findViewById(R.id.gridview7);
        gridview8 = (GridView) rootView.findViewById(R.id.gridview8);
        gridview9 = (GridView) rootView.findViewById(R.id.gridview9);
        gridview10 = (GridView) rootView.findViewById(R.id.gridview10);
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
                MyToast.getToast(getActivity(), "请求失败");
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

    //handler
    public Handler myHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 200:
                    int size = list_daxiao.size();
                    tv_1.setText("冠军大小今日累计 大(" + list_da.size() + ")" + " 小(" + list_xiao.size() + ")");
                    tv_2.setText("亚军大小今日累计 大(" + list_da1.size() + ")" + " 小(" + list_xiao1.size() + ")");
                    tv_3.setText("季军大小今日累计 大(" + list_da2.size() + ")" + " 小(" + list_xiao2.size() + ")");
                    tv_4.setText("第4名大小今日累计 大(" + list_da3.size() + ")" + " 小(" + list_xiao3.size() + ")");
                    tv_5.setText("第5名大小今日累计 大(" + list_da4.size() + ")" + " 小(" + list_xiao4.size() + ")");
                    tv_6.setText("第6名大小今日累计 大(" + list_da5.size() + ")" + " 小(" + list_xiao5.size() + ")");
                    tv_7.setText("第7名大小今日累计 大(" + list_da6.size() + ")" + " 小(" + list_xiao6.size() + ")");
                    tv_8.setText("第8名大小今日累计 大(" + list_da7.size() + ")" + " 小(" + list_xiao7.size() + ")");
                    tv_9.setText("第9名大小今日累计 大(" + list_da8.size() + ")" + " 小(" + list_xiao8.size() + ")");
                    tv_10.setText("第10名大小今日累计 大(" + list_da9.size() + ")" + " 小(" + list_xiao9.size() + ")");
                    int count = 1;
                    List<List<String>>  list_data = new ArrayList<>();
                    for (int i = 0; i < list_daxiao.size(); i++) {
                        if (i!=list_daxiao.size()-1) {
                            if (!list_daxiao.get(i).equals(list_daxiao.get(i+1))) {
                                count++;
                            }
                        }
                    }
                    for (int i = 0; i < count; i++) {
                        list_data.add(new ArrayList<String>());
                    }
                    count=0;
                    for (int i = 0; i < list_daxiao.size(); i++) {
                        if (i!=list_daxiao.size()-1) {
                            if (!list_daxiao.get(i).equals(list_daxiao.get(i+1))) {
                                list_data.get(count).add(list_daxiao.get(i));
                                count++;
                            }else{
                                list_data.get(count).add(list_daxiao.get(i));
                            }
                        }else{
                            list_data.get(count).add(list_daxiao.get(i));
                        }
                    }
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                            list_data.size() * 68, LinearLayout.LayoutParams.FILL_PARENT);
                    gridview.setLayoutParams(params); // 设置GirdView布局参数,横向布局的关键
                    gridview.setColumnWidth(68); // 设置列表项宽
                    gridview.setHorizontalSpacing(0); // 设置列表项水平间距
                    gridview.setStretchMode(GridView.NO_STRETCH);
                    gridview.setNumColumns(size); // 设置列数量=列表集合数
                    adapter = new MyDaxiaoAdapter(getActivity(), list_data);
                    gridview.setAdapter(adapter);
                    /**
                     *2
                     */
                    int size1 = list_daxiao1.size();
                    int count1 = 1;
                    List<List<String>>  list_data1 = new ArrayList<>();
                    for (int i = 0; i < list_daxiao1.size(); i++) {
                        if (i!=list_daxiao1.size()-1) {
                            if (!list_daxiao1.get(i).equals(list_daxiao1.get(i+1))) {
                                count1++;
                            }
                        }
                    }
                    for (int i = 0; i < count1; i++) {
                        list_data1.add(new ArrayList<String>());
                    }
                    count1=0;
                    for (int i = 0; i < list_daxiao1.size(); i++) {
                        if (i!=list_daxiao1.size()-1) {
                            if (!list_daxiao1.get(i).equals(list_daxiao1.get(i+1))) {
                                list_data1.get(count1).add(list_daxiao1.get(i));
                                count1++;
                            }else{
                                list_data1.get(count1).add(list_daxiao1.get(i));
                            }
                        }else{
                            list_data1.get(count1).add(list_daxiao1.get(i));
                        }
                    }
                    LinearLayout.LayoutParams params2 = new LinearLayout.LayoutParams(
                            list_data1.size() * 68, LinearLayout.LayoutParams.FILL_PARENT);
                    gridview2.setLayoutParams(params2); // 设置GirdView布局参数,横向布局的关键
                    gridview2.setColumnWidth(68); // 设置列表项宽
                    gridview2.setHorizontalSpacing(0); // 设置列表项水平间距
                    gridview2.setStretchMode(GridView.NO_STRETCH);
                    gridview2.setNumColumns(size1); // 设置列数量=列表集合数
                    adapter2 = new MyDaxiaoAdapter(getActivity(), list_data1);
                    gridview2.setAdapter(adapter2);
                    /**
                     *3
                     */
                    int size2 = list_daxiao2.size();
                    int count2 = 1;
                    List<List<String>>  list_data2 = new ArrayList<>();
                    for (int i = 0; i < list_daxiao2.size(); i++) {
                        if (i!=list_daxiao2.size()-1) {
                            if (!list_daxiao2.get(i).equals(list_daxiao2.get(i+1))) {
                                count2++;
                            }
                        }
                    }
                    for (int i = 0; i < count2; i++) {
                        list_data2.add(new ArrayList<String>());
                    }
                    count2=0;
                    for (int i = 0; i < list_daxiao2.size(); i++) {
                        if (i!=list_daxiao2.size()-1) {
                            if (!list_daxiao2.get(i).equals(list_daxiao2.get(i+1))) {
                                list_data2.get(count2).add(list_daxiao2.get(i));
                                count2++;
                            }else{
                                list_data2.get(count2).add(list_daxiao2.get(i));
                            }
                        }else{
                            list_data2.get(count2).add(list_daxiao2.get(i));
                        }
                    }
                    LinearLayout.LayoutParams params3 = new LinearLayout.LayoutParams(
                            list_data2.size() * 68, LinearLayout.LayoutParams.FILL_PARENT);
                    gridview3.setLayoutParams(params3); // 设置GirdView布局参数,横向布局的关键
                    gridview3.setColumnWidth(68); // 设置列表项宽
                    gridview3.setHorizontalSpacing(0); // 设置列表项水平间距
                    gridview3.setStretchMode(GridView.NO_STRETCH);
                    gridview3.setNumColumns(size2); // 设置列数量=列表集合数
                    adapter3 = new MyDaxiaoAdapter(getActivity(), list_data2);
                    gridview3.setAdapter(adapter3);
                    /**
                     *4
                     */
                    int size3 = list_daxiao3.size();
                    int count3 = 1;
                    List<List<String>>  list_data3 = new ArrayList<>();
                    for (int i = 0; i < list_daxiao3.size(); i++) {
                        if (i!=list_daxiao3.size()-1) {
                            if (!list_daxiao3.get(i).equals(list_daxiao3.get(i+1))) {
                                count3++;
                            }
                        }
                    }
                    for (int i = 0; i < count3; i++) {
                        list_data3.add(new ArrayList<String>());
                    }
                    count3=0;
                    for (int i = 0; i < list_daxiao3.size(); i++) {
                        if (i!=list_daxiao3.size()-1) {
                            if (!list_daxiao3.get(i).equals(list_daxiao3.get(i+1))) {
                                list_data3.get(count3).add(list_daxiao3.get(i));
                                count3++;
                            }else{
                                list_data3.get(count3).add(list_daxiao3.get(i));
                            }
                        }else{
                            list_data3.get(count3).add(list_daxiao3.get(i));
                        }
                    }
                    LinearLayout.LayoutParams params4 = new LinearLayout.LayoutParams(
                            list_data3.size() * 68, LinearLayout.LayoutParams.FILL_PARENT);
                    gridview4.setLayoutParams(params4); // 设置GirdView布局参数,横向布局的关键
                    gridview4.setColumnWidth(68); // 设置列表项宽
                    gridview4.setHorizontalSpacing(0); // 设置列表项水平间距
                    gridview4.setStretchMode(GridView.NO_STRETCH);
                    gridview4.setNumColumns(size3); // 设置列数量=列表集合数
                    adapter4 = new MyDaxiaoAdapter(getActivity(), list_data3);
                    gridview4.setAdapter(adapter4);
                    /**
                     *5
                     */
                    int size4 = list_daxiao4.size();
                    int count4 = 1;
                    List<List<String>>  list_data4 = new ArrayList<>();
                    for (int i = 0; i < list_daxiao4.size(); i++) {
                        if (i!=list_daxiao4.size()-1) {
                            if (!list_daxiao4.get(i).equals(list_daxiao4.get(i+1))) {
                                count4++;
                            }
                        }
                    }
                    for (int i = 0; i < count4; i++) {
                        list_data4.add(new ArrayList<String>());
                    }
                    count4=0;
                    for (int i = 0; i < list_daxiao4.size(); i++) {
                        if (i!=list_daxiao4.size()-1) {
                            if (!list_daxiao4.get(i).equals(list_daxiao4.get(i+1))) {
                                list_data4.get(count4).add(list_daxiao4.get(i));
                                count4++;
                            }else{
                                list_data4.get(count4).add(list_daxiao3.get(i));
                            }
                        }else{
                            list_data4.get(count4).add(list_daxiao3.get(i));
                        }
                    }

                    LinearLayout.LayoutParams params5 = new LinearLayout.LayoutParams(
                            list_data4.size() * 68, LinearLayout.LayoutParams.FILL_PARENT);
                    gridview5.setLayoutParams(params5); // 设置GirdView布局参数,横向布局的关键
                    gridview5.setColumnWidth(68); // 设置列表项宽
                    gridview5.setHorizontalSpacing(0); // 设置列表项水平间距
                    gridview5.setStretchMode(GridView.NO_STRETCH);
                    gridview5.setNumColumns(size4); // 设置列数量=列表集合数
                    adapter5 = new MyDaxiaoAdapter(getActivity(), list_data4);
                    gridview5.setAdapter(adapter5);


                    /**
                     *5
                     */
                    int size5 = list_daxiao5.size();
                    int count5 = 1;
                    List<List<String>>  list_data5 = new ArrayList<>();
                    for (int i = 0; i < list_daxiao5.size(); i++) {
                        if (i!=list_daxiao5.size()-1) {
                            if (!list_daxiao5.get(i).equals(list_daxiao5.get(i+1))) {
                                count5++;
                            }
                        }
                    }
                    for (int i = 0; i < count5; i++) {
                        list_data5.add(new ArrayList<String>());
                    }
                    count5=0;
                    for (int i = 0; i < list_daxiao5.size(); i++) {
                        if (i!=list_daxiao5.size()-1) {
                            if (!list_daxiao5.get(i).equals(list_daxiao5.get(i+1))) {
                                list_data5.get(count5).add(list_daxiao5.get(i));
                                count5++;
                            }else{
                                list_data5.get(count5).add(list_daxiao5.get(i));
                            }
                        }else{
                            list_data5.get(count5).add(list_daxiao5.get(i));
                        }
                    }
                    LinearLayout.LayoutParams params6 = new LinearLayout.LayoutParams(
                            list_data5.size() * 68, LinearLayout.LayoutParams.FILL_PARENT);
                    gridview6.setLayoutParams(params6); // 设置GirdView布局参数,横向布局的关键
                    gridview6.setColumnWidth(68); // 设置列表项宽
                    gridview6.setHorizontalSpacing(0); // 设置列表项水平间距
                    gridview6.setStretchMode(GridView.NO_STRETCH);
                    gridview6.setNumColumns(size5); // 设置列数量=列表集合数
                    adapter6 = new MyDaxiaoAdapter(getActivity(), list_data5);
                    gridview6.setAdapter(adapter6);
                    /**
                     *6
                     */
                    int size6 = list_daxiao6.size();
                    int count6 = 1;
                    List<List<String>>  list_data6 = new ArrayList<>();
                    for (int i = 0; i < list_daxiao6.size(); i++) {
                        if (i!=list_daxiao6.size()-1) {
                            if (!list_daxiao6.get(i).equals(list_daxiao6.get(i+1))) {
                                count6++;
                            }
                        }
                    }
                    for (int i = 0; i < count6; i++) {
                        list_data6.add(new ArrayList<String>());
                    }
                    count6=0;
                    for (int i = 0; i < list_daxiao6.size(); i++) {
                        if (i!=list_daxiao6.size()-1) {
                            if (!list_daxiao6.get(i).equals(list_daxiao6.get(i+1))) {
                                list_data6.get(count6).add(list_daxiao6.get(i));
                                count6++;
                            }else{
                                list_data6.get(count6).add(list_daxiao6.get(i));
                            }
                        }else{
                            list_data6.get(count6).add(list_daxiao6.get(i));
                        }
                    }
                    LinearLayout.LayoutParams params7 = new LinearLayout.LayoutParams(
                            list_data6.size() * 68, LinearLayout.LayoutParams.FILL_PARENT);
                    gridview7.setLayoutParams(params7); // 设置GirdView布局参数,横向布局的关键
                    gridview7.setColumnWidth(68); // 设置列表项宽
                    gridview7.setHorizontalSpacing(0); // 设置列表项水平间距
                    gridview7.setStretchMode(GridView.NO_STRETCH);
                    gridview7.setNumColumns(size6); // 设置列数量=列表集合数
                    adapter7 = new MyDaxiaoAdapter(getActivity(), list_data6);
                    gridview7.setAdapter(adapter7);


                    /**
                     *7
                     */
                    int size7 = list_daxiao7.size();
                    int count7 = 1;
                    List<List<String>>  list_data7 = new ArrayList<>();
                    for (int i = 0; i < list_daxiao7.size(); i++) {
                        if (i!=list_daxiao7.size()-1) {
                            if (!list_daxiao7.get(i).equals(list_daxiao7.get(i+1))) {
                                count7++;
                            }
                        }
                    }
                    for (int i = 0; i < count7; i++) {
                        list_data7.add(new ArrayList<String>());
                    }
                    count7=0;
                    for (int i = 0; i < list_daxiao7.size(); i++) {
                        if (i!=list_daxiao7.size()-1) {
                            if (!list_daxiao7.get(i).equals(list_daxiao7.get(i+1))) {
                                list_data7.get(count7).add(list_daxiao7.get(i));
                                count7++;
                            }else{
                                list_data7.get(count7).add(list_daxiao7.get(i));
                            }
                        }else{
                            list_data7.get(count7).add(list_daxiao7.get(i));
                        }
                    }
                    LinearLayout.LayoutParams params8 = new LinearLayout.LayoutParams(
                            list_data7.size() * 68, LinearLayout.LayoutParams.FILL_PARENT);
                    gridview8.setLayoutParams(params8); // 设置GirdView布局参数,横向布局的关键
                    gridview8.setColumnWidth(68); // 设置列表项宽
                    gridview8.setHorizontalSpacing(0); // 设置列表项水平间距
                    gridview8.setStretchMode(GridView.NO_STRETCH);
                    gridview8.setNumColumns(size7); // 设置列数量=列表集合数
                    adapter8 = new MyDaxiaoAdapter(getActivity(), list_data7);
                    gridview8.setAdapter(adapter8);


                    /**
                     *8
                     */
                    int size8 = list_daxiao8.size();
                    int count8 = 1;
                    List<List<String>>  list_data8 = new ArrayList<>();
                    for (int i = 0; i < list_daxiao8.size(); i++) {
                        if (i!=list_daxiao8.size()-1) {
                            if (!list_daxiao8.get(i).equals(list_daxiao8.get(i+1))) {
                                count8++;
                            }
                        }
                    }
                    for (int i = 0; i < count8; i++) {
                        list_data8.add(new ArrayList<String>());
                    }
                    count8=0;
                    for (int i = 0; i < list_daxiao8.size(); i++) {
                        if (i!=list_daxiao8.size()-1) {
                            if (!list_daxiao8.get(i).equals(list_daxiao8.get(i+1))) {
                                list_data8.get(count8).add(list_daxiao8.get(i));
                                count8++;
                            }else{
                                list_data8.get(count8).add(list_daxiao8.get(i));
                            }
                        }else{
                            list_data8.get(count8).add(list_daxiao8.get(i));
                        }
                    }
                    LinearLayout.LayoutParams params9 = new LinearLayout.LayoutParams(
                            list_data8.size() * 68, LinearLayout.LayoutParams.FILL_PARENT);
                    gridview9.setLayoutParams(params9); // 设置GirdView布局参数,横向布局的关键
                    gridview9.setColumnWidth(68); // 设置列表项宽
                    gridview9.setHorizontalSpacing(0); // 设置列表项水平间距
                    gridview9.setStretchMode(GridView.NO_STRETCH);
                    gridview9.setNumColumns(size8); // 设置列数量=列表集合数
                    adapter9 = new MyDaxiaoAdapter(getActivity(), list_data8);
                    gridview9.setAdapter(adapter9);
                    /**
                     *9
                     */
                    int size9 = list_daxiao9.size();
                    int count9 = 1;
                    List<List<String>>  list_data9 = new ArrayList<>();
                    for (int i = 0; i < list_daxiao9.size(); i++) {
                        if (i!=list_daxiao9.size()-1) {
                            if (!list_daxiao9.get(i).equals(list_daxiao9.get(i+1))) {
                                count9++;
                            }
                        }
                    }
                    for (int i = 0; i < count9; i++) {
                        list_data9.add(new ArrayList<String>());
                    }
                    count9=0;
                    for (int i = 0; i < list_daxiao9.size(); i++) {
                        if (i!=list_daxiao9.size()-1) {
                            if (!list_daxiao9.get(i).equals(list_daxiao9.get(i+1))) {
                                list_data9.get(count9).add(list_daxiao9.get(i));
                                count9++;
                            }else{
                                list_data9.get(count9).add(list_daxiao9.get(i));
                            }
                        }else{
                            list_data9.get(count9).add(list_daxiao9.get(i));
                        }
                    }
                    LinearLayout.LayoutParams params10 = new LinearLayout.LayoutParams(
                            list_data9.size() * 68, LinearLayout.LayoutParams.FILL_PARENT);
                    gridview10.setLayoutParams(params10); // 设置GirdView布局参数,横向布局的关键
                    gridview10.setColumnWidth(68); // 设置列表项宽
                    gridview10.setHorizontalSpacing(0); // 设置列表项水平间距
                    gridview10.setStretchMode(GridView.NO_STRETCH);
                    gridview10.setNumColumns(size9); // 设置列数量=列表集合数
                    adapter10 = new MyDaxiaoAdapter(getActivity(), list_data9);
                    gridview10.setAdapter(adapter10);
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

    /**
     * 历史开奖
     */
    public void getData() {
        //创建网络处理的对象
        OkHttpUtils
                .post()
                .url("http://www.zse6.com/index.php/mobile/pk10api/get_dsdx_luzhu")
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
                    JSONObject jsonObject = new JSONObject(date);
                    JSONObject jsonObject1 = jsonObject.getJSONObject("data");
                    String dsdx = jsonObject1.getString("dsdx");
                    JSONArray jsonObj = new JSONArray(dsdx);
//                    Iterator<String> keys = jsonObject1.keys();
                    list_da = new ArrayList<String>();
                    list_xiao = new ArrayList<String>();
                    list_da1 = new ArrayList<String>();
                    list_xiao1 = new ArrayList<String>();
                    list_da2 = new ArrayList<String>();
                    list_xiao2 = new ArrayList<String>();
                    list_da3 = new ArrayList<String>();
                    list_xiao3 = new ArrayList<String>();
                    list_da4 = new ArrayList<String>();
                    list_xiao4 = new ArrayList<String>();
                    list_da5 = new ArrayList<String>();
                    list_xiao5 = new ArrayList<String>();
                    list_da6 = new ArrayList<String>();
                    list_xiao6 = new ArrayList<String>();
                    list_da7 = new ArrayList<String>();
                    list_xiao7 = new ArrayList<String>();
                    list_da8 = new ArrayList<String>();
                    list_xiao8 = new ArrayList<String>();
                    list_da9 = new ArrayList<String>();
                    list_xiao9 = new ArrayList<String>();
                    list_daxiao = new ArrayList<String>();
                    list_daxiao1 = new ArrayList<String>();
                    list_daxiao2 = new ArrayList<String>();
                    list_daxiao3 = new ArrayList<String>();
                    list_daxiao4 = new ArrayList<String>();
                    list_daxiao5 = new ArrayList<String>();
                    list_daxiao6 = new ArrayList<String>();
                    list_daxiao7 = new ArrayList<String>();
                    list_daxiao8 = new ArrayList<String>();
                    list_daxiao9 = new ArrayList<String>();
                    for (int i = 0; i < jsonObj.length(); i++) {
                        JSONObject o = (JSONObject) jsonObj.get(i);
                        String daxiao1 = o.getString("daxiao1");
                        if (o.getString("daxiao1").equals("大")) {
                            list_da.add(o.getString("daxiao1"));
                        } else {
                            list_xiao.add(o.getString("daxiao1"));
                        }
                        if (o.getString("daxiao2").equals("大")) {
                            list_da1.add(o.getString("daxiao2"));
                        } else {
                            list_xiao1.add(o.getString("daxiao2"));
                        }
                        if (o.getString("daxiao3").equals("大")) {
                            list_da2.add(o.getString("daxiao3"));
                        } else {
                            list_xiao2.add(o.getString("daxiao3"));
                        }
                        if (o.getString("daxiao4").equals("大")) {
                            list_da3.add(o.getString("daxiao4"));
                        } else {
                            list_xiao3.add(o.getString("daxiao4"));
                        }
                        if (o.getString("daxiao5").equals("大")) {
                            list_da4.add(o.getString("daxiao5"));
                        } else {
                            list_xiao4.add(o.getString("daxiao5"));
                        }
                        if (o.getString("daxiao6").equals("大")) {
                            list_da5.add(o.getString("daxiao6"));
                        } else {
                            list_xiao5.add(o.getString("daxiao6"));
                        }
                        if (o.getString("daxiao7").equals("大")) {
                            list_da6.add(o.getString("daxiao7"));
                        } else {
                            list_xiao6.add(o.getString("daxiao7"));
                        }
                        if (o.getString("daxiao8").equals("大")) {
                            list_da7.add(o.getString("daxiao8"));
                        } else {
                            list_xiao7.add(o.getString("daxiao8"));
                        }
                        if (o.getString("daxiao9").equals("大")) {
                            list_da8.add(o.getString("daxiao9"));
                        } else {
                            list_xiao8.add(o.getString("daxiao9"));
                        }
                        if (o.getString("daxiao10").equals("大")) {
                            list_da9.add(o.getString("daxiao10"));
                        } else {
                            list_xiao9.add(o.getString("daxiao10"));
                        }
                        list_daxiao.add(o.getString("daxiao1"));
                        list_daxiao1.add(o.getString("daxiao2"));
                        list_daxiao2.add(o.getString("daxiao3"));
                        list_daxiao3.add(o.getString("daxiao4"));
                        list_daxiao4.add(o.getString("daxiao5"));
                        list_daxiao5.add(o.getString("daxiao6"));
                        list_daxiao6.add(o.getString("daxiao7"));
                        list_daxiao7.add(o.getString("daxiao8"));
                        list_daxiao8.add(o.getString("daxiao9"));
                        list_daxiao9.add(o.getString("daxiao10"));
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
