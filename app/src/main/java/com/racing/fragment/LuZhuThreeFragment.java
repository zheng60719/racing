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

import com.racing.Adapter.MyQianHouAdapter;
import com.racing.R;
import com.racing.utils.DateUtil;
import com.racing.view.LoadingDialog;
import com.racing.view.MyToast;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Administrator
 * 专家分析
 */
public class LuZhuThreeFragment extends Fragment {
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
    private GridView gridview, gridview2, gridview3, gridview4, gridview5, gridview6, gridview7, gridview8, gridview9, gridview10;
    private MyQianHouAdapter adapter;
    private MyQianHouAdapter adapter2;
    private MyQianHouAdapter adapter3;
    private MyQianHouAdapter adapter4;
    private MyQianHouAdapter adapter5;
    private MyQianHouAdapter adapter6;
    private MyQianHouAdapter adapter7;
    private MyQianHouAdapter adapter8;
    private MyQianHouAdapter adapter9;
    private MyQianHouAdapter adapter10;
    private LoadingDialog loadingDialog;
    private List<String> list_qianhou1;
    private List<String> list_qianhou2;
    private List<String> list_qianhou3;
    private List<String> list_qianhou4;
    private List<String> list_qianhou5;
    private List<String> list_qianhou6;
    private List<String> list_qianhou7;
    private List<String> list_qianhou8;
    private List<String> list_qianhou9;
    private List<String> list_qianhou10;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.luzhu_three, null);
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
                    tv_1.setText("冠军前后今日累计 前(" + list_da.size() + ")" + " 后(" + list_xiao.size() + ")");
                    tv_2.setText("亚军前后今日累计 前(" + list_da1.size() + ")" + " 后(" + list_xiao1.size() + ")");
                    tv_3.setText("季军前后今日累计 前(" + list_da2.size() + ")" + " 后(" + list_xiao2.size() + ")");
                    tv_4.setText("第4名前后今日累计 前(" + list_da3.size() + ")" + " 后(" + list_xiao3.size() + ")");
                    tv_5.setText("第5名前后今日累计 前(" + list_da4.size() + ")" + " 后(" + list_xiao4.size() + ")");
                    tv_6.setText("第6名前后今日累计 前(" + list_da5.size() + ")" + " 后(" + list_xiao5.size() + ")");
                    tv_7.setText("第7名前后今日累计 前(" + list_da6.size() + ")" + " 后(" + list_xiao6.size() + ")");
                    tv_8.setText("第8名前后今日累计 前(" + list_da7.size() + ")" + " 后(" + list_xiao7.size() + ")");
                    tv_9.setText("第9名前后今日累计 前(" + list_da8.size() + ")" + " 后(" + list_xiao8.size() + ")");
                    tv_10.setText("第10名前后今日累计 前(" + list_da9.size() + ")" + " 后(" + list_xiao9.size() + ")");
                    int size = list_qianhou1.size();
                    int count = 1;
                    List<List<String>> list_data = new ArrayList<>();
                    for (int i = 0; i < list_qianhou1.size(); i++) {
                        if (i != list_qianhou1.size() - 1) {
                            if (!list_qianhou1.get(i).equals(list_qianhou1.get(i + 1))) {
                                count++;
                            }
                        }
                    }
                    for (int i = 0; i < count; i++) {
                        list_data.add(new ArrayList<String>());
                    }
                    count = 0;
                    for (int i = 0; i < list_qianhou1.size(); i++) {
                        if (i != list_qianhou1.size() - 1) {
                            if (!list_qianhou1.get(i).equals(list_qianhou1.get(i + 1))) {
                                list_data.get(count).add(list_qianhou1.get(i));
                                count++;
                            } else {
                                list_data.get(count).add(list_qianhou1.get(i));
                            }
                        } else {
                            list_data.get(count).add(list_qianhou1.get(i));
                        }
                    }
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                            list_data.size() * 68, LinearLayout.LayoutParams.FILL_PARENT);
                    gridview.setLayoutParams(params); // 设置GirdView布局参数,横向布局的关键
                    gridview.setColumnWidth(68); // 设置列表项宽
                    gridview.setHorizontalSpacing(0); // 设置列表项水平间距
                    gridview.setStretchMode(GridView.NO_STRETCH);
                    gridview.setNumColumns(size); // 设置列数量=列表集合数
                    adapter = new MyQianHouAdapter(getActivity(), list_data);
                    gridview.setAdapter(adapter);

                    /**
                     *2
                     */
                    int size1 = list_qianhou2.size();
                    int count1 = 1;
                    List<List<String>> list_data1 = new ArrayList<>();
                    for (int i = 0; i < list_qianhou2.size(); i++) {
                        if (i != list_qianhou2.size() - 1) {
                            if (!list_qianhou2.get(i).equals(list_qianhou2.get(i + 1))) {
                                count1++;
                            }
                        }
                    }
                    for (int i = 0; i < count1; i++) {
                        list_data1.add(new ArrayList<String>());
                    }
                    count1 = 0;
                    for (int i = 0; i < list_qianhou2.size(); i++) {
                        if (i != list_qianhou2.size() - 1) {
                            if (!list_qianhou2.get(i).equals(list_qianhou2.get(i + 1))) {
                                list_data1.get(count1).add(list_qianhou2.get(i));
                                count1++;
                            } else {
                                list_data1.get(count1).add(list_qianhou2.get(i));
                            }
                        } else {
                            list_data1.get(count1).add(list_qianhou2.get(i));
                        }
                    }
                    LinearLayout.LayoutParams params2 = new LinearLayout.LayoutParams(
                            list_data1.size() * 68, LinearLayout.LayoutParams.FILL_PARENT);
                    gridview2.setLayoutParams(params2); // 设置GirdView布局参数,横向布局的关键
                    gridview2.setColumnWidth(68); // 设置列表项宽
                    gridview2.setHorizontalSpacing(0); // 设置列表项水平间距
                    gridview2.setStretchMode(GridView.NO_STRETCH);
                    gridview2.setNumColumns(size1); // 设置列数量=列表集合数
                    adapter2 = new MyQianHouAdapter(getActivity(), list_data1);
                    gridview2.setAdapter(adapter2);

                    /**
                     *3
                     */
                    int size2 = list_qianhou3.size();
                    int count2 = 1;
                    List<List<String>> list_data2 = new ArrayList<>();
                    for (int i = 0; i < list_qianhou3.size(); i++) {
                        if (i != list_qianhou3.size() - 1) {
                            if (!list_qianhou3.get(i).equals(list_qianhou3.get(i + 1))) {
                                count2++;
                            }
                        }
                    }
                    for (int i = 0; i < count2; i++) {
                        list_data2.add(new ArrayList<String>());
                    }
                    count2 = 0;
                    for (int i = 0; i < list_qianhou3.size(); i++) {
                        if (i != list_qianhou3.size() - 1) {
                            if (!list_qianhou3.get(i).equals(list_qianhou3.get(i + 1))) {
                                list_data2.get(count2).add(list_qianhou3.get(i));
                                count2++;
                            } else {
                                list_data2.get(count2).add(list_qianhou3.get(i));
                            }
                        } else {
                            list_data2.get(count2).add(list_qianhou3.get(i));
                        }
                    }
                    LinearLayout.LayoutParams params3 = new LinearLayout.LayoutParams(
                            list_data2.size() * 68, LinearLayout.LayoutParams.FILL_PARENT);
                    gridview3.setLayoutParams(params3); // 设置GirdView布局参数,横向布局的关键
                    gridview3.setColumnWidth(68); // 设置列表项宽
                    gridview3.setHorizontalSpacing(0); // 设置列表项水平间距
                    gridview3.setStretchMode(GridView.NO_STRETCH);
                    gridview3.setNumColumns(size2); // 设置列数量=列表集合数
                    adapter3 = new MyQianHouAdapter(getActivity(), list_data2);
                    gridview3.setAdapter(adapter3);

                    /**
                     *4
                     */
                    int size3 = list_qianhou4.size();
                    int count3 = 1;
                    List<List<String>> list_data3 = new ArrayList<>();
                    for (int i = 0; i < list_qianhou4.size(); i++) {
                        if (i != list_qianhou4.size() - 1) {
                            if (!list_qianhou4.get(i).equals(list_qianhou4.get(i + 1))) {
                                count3++;
                            }
                        }
                    }
                    for (int i = 0; i < count3; i++) {
                        list_data3.add(new ArrayList<String>());
                    }
                    count3 = 0;
                    for (int i = 0; i < list_qianhou4.size(); i++) {
                        if (i != list_qianhou4.size() - 1) {
                            if (!list_qianhou4.get(i).equals(list_qianhou4.get(i + 1))) {
                                list_data3.get(count3).add(list_qianhou4.get(i));
                                count3++;
                            } else {
                                list_data3.get(count3).add(list_qianhou4.get(i));
                            }
                        } else {
                            list_data3.get(count3).add(list_qianhou4.get(i));
                        }
                    }
                    LinearLayout.LayoutParams params4 = new LinearLayout.LayoutParams(
                            list_data3.size() * 68, LinearLayout.LayoutParams.FILL_PARENT);
                    gridview4.setLayoutParams(params4); // 设置GirdView布局参数,横向布局的关键
                    gridview4.setColumnWidth(68); // 设置列表项宽
                    gridview4.setHorizontalSpacing(0); // 设置列表项水平间距
                    gridview4.setStretchMode(GridView.NO_STRETCH);
                    gridview4.setNumColumns(size3); // 设置列数量=列表集合数
                    adapter4 = new MyQianHouAdapter(getActivity(), list_data3);
                    gridview4.setAdapter(adapter4);
                    /**
                     *5
                     */
                    int size4 = list_qianhou5.size();
                    int count4 = 1;
                    List<List<String>> list_data4 = new ArrayList<>();
                    for (int i = 0; i < list_qianhou5.size(); i++) {
                        if (i != list_qianhou5.size() - 1) {
                            if (!list_qianhou5.get(i).equals(list_qianhou5.get(i + 1))) {
                                count4++;
                            }
                        }
                    }
                    for (int i = 0; i < count4; i++) {
                        list_data4.add(new ArrayList<String>());
                    }
                    count4 = 0;
                    for (int i = 0; i < list_qianhou5.size(); i++) {
                        if (i != list_qianhou5.size() - 1) {
                            if (!list_qianhou5.get(i).equals(list_qianhou5.get(i + 1))) {
                                list_data4.get(count4).add(list_qianhou5.get(i));
                                count4++;
                            } else {
                                list_data4.get(count4).add(list_qianhou5.get(i));
                            }
                        } else {
                            list_data4.get(count4).add(list_qianhou5.get(i));
                        }
                    }
                    LinearLayout.LayoutParams params5 = new LinearLayout.LayoutParams(
                            list_data4.size() * 68, LinearLayout.LayoutParams.FILL_PARENT);
                    gridview5.setLayoutParams(params5); // 设置GirdView布局参数,横向布局的关键
                    gridview5.setColumnWidth(68); // 设置列表项宽
                    gridview5.setHorizontalSpacing(0); // 设置列表项水平间距
                    gridview5.setStretchMode(GridView.NO_STRETCH);
                    gridview5.setNumColumns(size4); // 设置列数量=列表集合数
                    adapter5 = new MyQianHouAdapter(getActivity(), list_data4);
                    gridview5.setAdapter(adapter5);

                    /**
                     *5
                     */
                    int size5 = list_qianhou6.size();
                    int count5 = 1;
                    List<List<String>> list_data5 = new ArrayList<>();
                    for (int i = 0; i < list_qianhou6.size(); i++) {
                        if (i != list_qianhou6.size() - 1) {
                            if (!list_qianhou6.get(i).equals(list_qianhou6.get(i + 1))) {
                                count5++;
                            }
                        }
                    }
                    for (int i = 0; i < count5; i++) {
                        list_data5.add(new ArrayList<String>());
                    }
                    count5 = 0;
                    for (int i = 0; i < list_qianhou6.size(); i++) {
                        if (i != list_qianhou6.size() - 1) {
                            if (!list_qianhou6.get(i).equals(list_qianhou6.get(i + 1))) {
                                list_data5.get(count5).add(list_qianhou6.get(i));
                                count5++;
                            } else {
                                list_data5.get(count5).add(list_qianhou6.get(i));
                            }
                        } else {
                            list_data5.get(count5).add(list_qianhou6.get(i));
                        }
                    }
                    LinearLayout.LayoutParams params6 = new LinearLayout.LayoutParams(
                            list_data5.size() * 68, LinearLayout.LayoutParams.FILL_PARENT);
                    gridview6.setLayoutParams(params6); // 设置GirdView布局参数,横向布局的关键
                    gridview6.setColumnWidth(68); // 设置列表项宽
                    gridview6.setHorizontalSpacing(0); // 设置列表项水平间距
                    gridview6.setStretchMode(GridView.NO_STRETCH);
                    gridview6.setNumColumns(size5); // 设置列数量=列表集合数
                    adapter6 = new MyQianHouAdapter(getActivity(), list_data5);
                    gridview6.setAdapter(adapter6);
                    /**
                     *6
                     */
                    int size6 = list_qianhou7.size();
                    int count6 = 1;
                    List<List<String>> list_data6 = new ArrayList<>();
                    for (int i = 0; i < list_qianhou7.size(); i++) {
                        if (i != list_qianhou7.size() - 1) {
                            if (!list_qianhou7.get(i).equals(list_qianhou7.get(i + 1))) {
                                count6++;
                            }
                        }
                    }
                    for (int i = 0; i < count6; i++) {
                        list_data6.add(new ArrayList<String>());
                    }
                    count6 = 0;
                    for (int i = 0; i < list_qianhou7.size(); i++) {
                        if (i != list_qianhou7.size() - 1) {
                            if (!list_qianhou7.get(i).equals(list_qianhou7.get(i + 1))) {
                                list_data6.get(count6).add(list_qianhou7.get(i));
                                count6++;
                            } else {
                                list_data6.get(count6).add(list_qianhou7.get(i));
                            }
                        } else {
                            list_data6.get(count6).add(list_qianhou7.get(i));
                        }
                    }
                    LinearLayout.LayoutParams params7 = new LinearLayout.LayoutParams(
                            list_data6.size() * 68, LinearLayout.LayoutParams.FILL_PARENT);
                    gridview7.setLayoutParams(params7); // 设置GirdView布局参数,横向布局的关键
                    gridview7.setColumnWidth(68); // 设置列表项宽
                    gridview7.setHorizontalSpacing(0); // 设置列表项水平间距
                    gridview7.setStretchMode(GridView.NO_STRETCH);
                    gridview7.setNumColumns(size); // 设置列数量=列表集合数
                    adapter7 = new MyQianHouAdapter(getActivity(), list_data6);
                    gridview7.setAdapter(adapter7);

                    /**
                     *7
                     */
                    int size7 = list_qianhou8.size();
                    int count7 = 1;
                    List<List<String>> list_data7 = new ArrayList<>();
                    for (int i = 0; i < list_qianhou8.size(); i++) {
                        if (i != list_qianhou8.size() - 1) {
                            if (!list_qianhou8.get(i).equals(list_qianhou8.get(i + 1))) {
                                count7++;
                            }
                        }
                    }
                    for (int i = 0; i < count7; i++) {
                        list_data7.add(new ArrayList<String>());
                    }
                    count7 = 0;
                    for (int i = 0; i < list_qianhou8.size(); i++) {
                        if (i != list_qianhou8.size() - 1) {
                            if (!list_qianhou8.get(i).equals(list_qianhou8.get(i + 1))) {
                                list_data7.get(count7).add(list_qianhou8.get(i));
                                count7++;
                            } else {
                                list_data7.get(count7).add(list_qianhou8.get(i));
                            }
                        } else {
                            list_data7.get(count7).add(list_qianhou8.get(i));
                        }
                    }
                    LinearLayout.LayoutParams params8 = new LinearLayout.LayoutParams(
                            list_data7.size() * 68, LinearLayout.LayoutParams.FILL_PARENT);
                    gridview8.setLayoutParams(params8); // 设置GirdView布局参数,横向布局的关键
                    gridview8.setColumnWidth(68); // 设置列表项宽
                    gridview8.setHorizontalSpacing(0); // 设置列表项水平间距
                    gridview8.setStretchMode(GridView.NO_STRETCH);
                    gridview8.setNumColumns(size7); // 设置列数量=列表集合数
                    adapter8 = new MyQianHouAdapter(getActivity(), list_data7);
                    gridview8.setAdapter(adapter8);

                    /**
                     *8
                     */
                    int size8 = list_qianhou9.size();
                    int count8 = 1;
                    List<List<String>> list_data8 = new ArrayList<>();
                    for (int i = 0; i < list_qianhou9.size(); i++) {
                        if (i != list_qianhou9.size() - 1) {
                            if (!list_qianhou9.get(i).equals(list_qianhou9.get(i + 1))) {
                                count8++;
                            }
                        }
                    }
                    for (int i = 0; i < count8; i++) {
                        list_data8.add(new ArrayList<String>());
                    }
                    count8 = 0;
                    for (int i = 0; i < list_qianhou9.size(); i++) {
                        if (i != list_qianhou9.size() - 1) {
                            if (!list_qianhou9.get(i).equals(list_qianhou9.get(i + 1))) {
                                list_data8.get(count8).add(list_qianhou9.get(i));
                                count8++;
                            } else {
                                list_data8.get(count8).add(list_qianhou9.get(i));
                            }
                        } else {
                            list_data8.get(count8).add(list_qianhou9.get(i));
                        }
                    }
                    LinearLayout.LayoutParams params9 = new LinearLayout.LayoutParams(
                            list_data8.size() * 68, LinearLayout.LayoutParams.FILL_PARENT);
                    gridview9.setLayoutParams(params9); // 设置GirdView布局参数,横向布局的关键
                    gridview9.setColumnWidth(68); // 设置列表项宽
                    gridview9.setHorizontalSpacing(0); // 设置列表项水平间距
                    gridview9.setStretchMode(GridView.NO_STRETCH);
                    gridview9.setNumColumns(size8); // 设置列数量=列表集合数
                    adapter9 = new MyQianHouAdapter(getActivity(), list_data8);
                    gridview9.setAdapter(adapter9);


                    /**
                     *9
                     */
                    int size9 = list_qianhou10.size();
                    int count9 = 1;
                    List<List<String>> list_data9 = new ArrayList<>();
                    for (int i = 0; i < list_qianhou10.size(); i++) {
                        if (i != list_qianhou10.size() - 1) {
                            if (!list_qianhou10.get(i).equals(list_qianhou10.get(i + 1))) {
                                count9++;
                            }
                        }
                    }
                    for (int i = 0; i < count9; i++) {
                        list_data9.add(new ArrayList<String>());
                    }
                    count9 = 0;
                    for (int i = 0; i < list_qianhou10.size(); i++) {
                        if (i != list_qianhou10.size() - 1) {
                            if (!list_qianhou10.get(i).equals(list_qianhou10.get(i + 1))) {
                                list_data9.get(count9).add(list_qianhou10.get(i));
                                count9++;
                            } else {
                                list_data9.get(count9).add(list_qianhou10.get(i));
                            }
                        } else {
                            list_data9.get(count9).add(list_qianhou10.get(i));
                        }
                    }
                    LinearLayout.LayoutParams params10 = new LinearLayout.LayoutParams(
                            list_data9.size() * 68, LinearLayout.LayoutParams.FILL_PARENT);
                    gridview10.setLayoutParams(params10); // 设置GirdView布局参数,横向布局的关键
                    gridview10.setColumnWidth(68); // 设置列表项宽
                    gridview10.setHorizontalSpacing(0); // 设置列表项水平间距
                    gridview10.setStretchMode(GridView.NO_STRETCH);
                    gridview10.setNumColumns(size9); // 设置列数量=列表集合数
                    adapter10 = new MyQianHouAdapter(getActivity(), list_data9);
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
                .url("http://www.zse6.com/index.php/mobile/pk10api/get_qianhou_luzhu")
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
                    list_da5 = new ArrayList<>();
                    list_xiao5 = new ArrayList<>();
                    list_da6 = new ArrayList<>();
                    list_xiao6 = new ArrayList<>();
                    list_da7 = new ArrayList<>();
                    list_xiao7 = new ArrayList<>();
                    list_da8 = new ArrayList<>();
                    list_xiao8 = new ArrayList<>();
                    list_da9 = new ArrayList<>();
                    list_xiao9 = new ArrayList<>();
                    list_qianhou1 = new ArrayList<>();
                    list_qianhou2 = new ArrayList<>();
                    list_qianhou3 = new ArrayList<>();
                    list_qianhou4 = new ArrayList<>();
                    list_qianhou5 = new ArrayList<>();
                    list_qianhou6 = new ArrayList<>();
                    list_qianhou7 = new ArrayList<>();
                    list_qianhou8 = new ArrayList<>();
                    list_qianhou9 = new ArrayList<>();
                    list_qianhou10 = new ArrayList<>();
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject o = (JSONObject) jsonArray.get(i);
                        if (o.getString("qianhou1").equals("前")) {
                            list_da.add(o.getString("qianhou1"));
                        } else {
                            list_xiao.add(o.getString("qianhou1"));
                        }
                        if (o.getString("qianhou2").equals("前")) {
                            list_da1.add(o.getString("qianhou2"));
                        } else {
                            list_xiao1.add(o.getString("qianhou2"));
                        }
                        if (o.getString("qianhou3").equals("前")) {
                            list_da2.add(o.getString("qianhou3"));
                        } else {
                            list_xiao2.add(o.getString("qianhou3"));
                        }
                        if (o.getString("qianhou4").equals("前")) {
                            list_da3.add(o.getString("qianhou4"));
                        } else {
                            list_xiao3.add(o.getString("qianhou4"));
                        }
                        if (o.getString("qianhou5").equals("前")) {
                            list_da4.add(o.getString("qianhou5"));
                        } else {
                            list_xiao4.add(o.getString("qianhou5"));
                        }
                        if (o.getString("qianhou6").equals("前")) {
                            list_da5.add(o.getString("qianhou6"));
                        } else {
                            list_xiao5.add(o.getString("qianhou6"));
                        }
                        if (o.getString("qianhou7").equals("前")) {
                            list_da6.add(o.getString("qianhou7"));
                        } else {
                            list_xiao6.add(o.getString("qianhou7"));
                        }
                        if (o.getString("qianhou8").equals("前")) {
                            list_da7.add(o.getString("qianhou8"));
                        } else {
                            list_xiao7.add(o.getString("qianhou8"));
                        }
                        if (o.getString("qianhou9").equals("前")) {
                            list_da8.add(o.getString("qianhou9"));
                        } else {
                            list_xiao8.add(o.getString("qianhou9"));
                        }
                        if (o.getString("qianhou10").equals("前")) {
                            list_da9.add(o.getString("qianhou10"));
                        } else {
                            list_xiao9.add(o.getString("qianhou10"));
                        }
                        list_qianhou1.add(o.getString("qianhou1"));
                        list_qianhou2.add(o.getString("qianhou1"));
                        list_qianhou3.add(o.getString("qianhou1"));
                        list_qianhou4.add(o.getString("qianhou1"));
                        list_qianhou5.add(o.getString("qianhou1"));
                        list_qianhou6.add(o.getString("qianhou1"));
                        list_qianhou7.add(o.getString("qianhou1"));
                        list_qianhou8.add(o.getString("qianhou1"));
                        list_qianhou9.add(o.getString("qianhou1"));
                        list_qianhou10.add(o.getString("qianhou1"));
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
