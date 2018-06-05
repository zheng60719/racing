package com.racing.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.display.CircleBitmapDisplayer;
import com.racing.App.AppSp;
import com.racing.R;
import com.racing.entity.UserBean;
import com.racing.racing.Account1Activity;
import com.racing.racing.EmailActivity;
import com.racing.racing.Find1PwdActivity;
import com.racing.racing.FollowActivity;
import com.racing.racing.HotColdActivity;
import com.racing.racing.IntegralActivity;
import com.racing.racing.LoginActivity;
import com.racing.racing.MyCollectionActivity;
import com.racing.racing.MyDataActivity;
import com.racing.racing.MyFollowActivity;
import com.racing.racing.MyForecastActivity;
import com.racing.racing.SendPredictionSchemeActivity;
import com.racing.racing.UpdatePwdActivity;
import com.racing.view.MyToast;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONObject;

import java.io.File;


/**
 * Created by zjx on 2017/2/16.
 */
public class MenFragment extends Fragment implements View.OnClickListener {
    private View rootView;
    private LinearLayout ll_wodeziliao, ll_jifen, ll_collection, ll_follow, ll_update_pwd, ll_send;
    private TextView all_order, text_jifen, text_collection, text_follow, text_update_pwd, tv_send;
    //退出登录
    private Button btn_exit;
    //UserBean
    private UserBean userBean;
    //CircleImageView
    private ImageView circleImageView;
    private TextView vip_name, vip_tel;
    private String ing;
    private String string_msg;
    private String user;
    private AppSp appSp;
    private LinearLayout ll_my_forecast;
    private TextView tv_my_forecast;
    //站内信
    private LinearLayout ll_email;
    private TextView tv_email;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.men, null);
        InitView(rootView);
        return rootView;
    }

    private void InitView(View rootView) {
        text_follow = (TextView) rootView.findViewById(R.id.text_follow);
        text_follow.setOnClickListener(this);
        //我的关注
        ll_follow = (LinearLayout) rootView.findViewById(R.id.ll_follow);
        ll_follow.setOnClickListener(this);
        ll_wodeziliao = (LinearLayout) rootView.findViewById(R.id.ll_wodeziliao);
        ll_wodeziliao.setOnClickListener(this);
        all_order = (TextView) rootView.findViewById(R.id.all_order);
        all_order.setOnClickListener(this);
        text_jifen = (TextView) rootView.findViewById(R.id.text_jifen);
        text_jifen.setOnClickListener(this);
        ll_jifen = (LinearLayout) rootView.findViewById(R.id.ll_jifen);
        ll_jifen.setOnClickListener(this);
        ll_collection = (LinearLayout) rootView.findViewById(R.id.ll_collection);
        ll_collection.setOnClickListener(this);
        text_collection = (TextView) rootView.findViewById(R.id.text_collection);
        text_collection.setOnClickListener(this);
        btn_exit = (Button) rootView.findViewById(R.id.btn_exit);
        btn_exit.setOnClickListener(this);
        ll_update_pwd = (LinearLayout) rootView.findViewById(R.id.ll_update_pwd);
        ll_update_pwd.setOnClickListener(this);
        text_update_pwd = (TextView) rootView.findViewById(R.id.text_update_pwd);
        text_update_pwd.setOnClickListener(this);
        circleImageView = (ImageView) rootView.findViewById(R.id.vip_headimg);
        //姓名
        vip_name = (TextView) rootView.findViewById(R.id.vip_name);
        //积分
        vip_tel = (TextView) rootView.findViewById(R.id.vip_tel);
        //发布预测方案
        tv_send = (TextView) rootView.findViewById(R.id.tv_send);
        ll_send = (LinearLayout) rootView.findViewById(R.id.ll_send);
        tv_send.setOnClickListener(this);
        ll_send.setOnClickListener(this);
        //我的预测方案
        ll_my_forecast = (LinearLayout) rootView.findViewById(R.id.ll_my_forecast);
        tv_my_forecast = (TextView) rootView.findViewById(R.id.tv_my_forecast);
        ll_my_forecast.setOnClickListener(this);
        tv_my_forecast.setOnClickListener(this);
        //我的站内信
        ll_email = (LinearLayout) rootView.findViewById(R.id.ll_email);
        tv_email = (TextView) rootView.findViewById(R.id.tv_email);
        ll_email.setOnClickListener(this);
        tv_email.setOnClickListener(this);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        appSp = new AppSp();
        userBean = (UserBean) appSp.getObjectFromShare(getActivity(), "user");
        //获取会员资料
        getUserDate(userBean.getId() + "");

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_wodeziliao:
            case R.id.all_order:
                Intent intent_wodeziliao = new Intent();
                intent_wodeziliao.setClass(getActivity(), MyDataActivity.class);
                startActivity(intent_wodeziliao);
                break;
            case R.id.ll_jifen:
            case R.id.text_jifen:
                Intent intent_jifen = new Intent();
                intent_jifen.setClass(getActivity(), IntegralActivity.class);
                startActivity(intent_jifen);
                break;
            case R.id.ll_collection:
            case R.id.text_collection:
                Intent intent_collection = new Intent();
                intent_collection.setClass(getActivity(), MyCollectionActivity.class);
                startActivity(intent_collection);
                break;
            case R.id.text_follow:
            case R.id.ll_follow:
                //我的关注
                Intent intent_follow = new Intent();
                intent_follow.setClass(getActivity(), FollowActivity.class);
                startActivity(intent_follow);
                break;
            case R.id.btn_exit:
                //退出登录
                final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("提示信息");
                builder.setMessage("您确定要退出到登录？");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent_login = new Intent();
                        intent_login.setClass(getActivity(), LoginActivity.class);
                        startActivity(intent_login);
                        File[] files = new File("/data/data/" + getActivity().getPackageName() + "/shared_prefs").listFiles();
                        deleteCache(files);
                        getActivity().finish();
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
                builder.show();
                break;
            case R.id.ll_update_pwd:
            case R.id.text_update_pwd:
                Intent intent_update_pwd = new Intent();
                intent_update_pwd.setClass(getActivity(), UpdatePwdActivity.class);
                startActivity(intent_update_pwd);
                //修改密码
                break;
            case R.id.tv_send:
            case R.id.ll_send:
                Intent intent = new Intent(getActivity(), SendPredictionSchemeActivity.class);
                startActivity(intent);
                //发布预测方案
                break;
            case R.id.ll_my_forecast:
            case R.id.tv_my_forecast:
                Intent intent_my_forecast = new Intent(getActivity(), MyForecastActivity.class);
                startActivity(intent_my_forecast);
                break;
            //我的站内信
            case R.id.ll_email:
            case R.id.tv_email:
                Intent intent_email = new Intent(getActivity(), EmailActivity.class);
                startActivity(intent_email);
                break;
        }
    }

    public void getUserDate(String id) {
        //创建网络处理的对象
        OkHttpUtils
                .post()
                .url("http://www.zse6.com/index.php/mobile/ajax/user_info")
                .addParams("id", id)
                .build().execute(new StringCallback() {
            @Override
            public void onError(okhttp3.Call call, Exception e, int id) {
                MyToast.getToast(getActivity(), "请求失败");
            }

            @Override
            public void onResponse(String response, int id) {
                try {
                    //数据解析
                    JSONObject jsonObj = new JSONObject(response.toString());
                    ing = jsonObj.getString("ing");
                    string_msg = jsonObj.getString("msg");
                    user = jsonObj.getString("data");
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
                case 300:
                    if (ing.equals("1")) {
                        try {
                            JSONObject jsonObject = new JSONObject(user);
                            UserBean userBean1 = new UserBean();
                            userBean1.setId(jsonObject.getInt("id"));
                            userBean1.setName(jsonObject.getString("name"));
                            userBean1.setMobile(jsonObject.getString("mobile"));
                            userBean1.setTx(jsonObject.getString("tx"));
                            userBean1.setPsd(jsonObject.getString("psd"));
                            userBean1.setShijian(jsonObject.getString("shijian"));
                            userBean1.setNicheng(jsonObject.getString("nicheng"));
                            userBean1.setMoney(jsonObject.getString("money"));
                            userBean1.setBeizhu(jsonObject.getString("beizhu"));
                            appSp.setObjectToShare(getActivity(), userBean1, "user");
                            vip_name.setText(jsonObject.getString("nicheng"));
                            vip_tel.setText("积分：" + jsonObject.getString("money"));
                            DisplayImageOptions options = new DisplayImageOptions.Builder()
                                    .cacheInMemory(true)
                                    .cacheOnDisk(true)
                                    .displayer(new CircleBitmapDisplayer())
                                    .build();
                            ImageLoader imageLoader = ImageLoader.getInstance();
                            imageLoader.init(ImageLoaderConfiguration.createDefault(getActivity()));
                            if (jsonObject.getString("tx") != null && jsonObject.getString("tx").length() > 0) {
                                ImageLoader.getInstance().displayImage("http://www.zse6.com" + jsonObject.getString("tx"), circleImageView, options);
                            } else {
                                String imageUri = "assets://img_user_tx.png";
                                ImageLoader.getInstance().displayImage(imageUri, circleImageView, options);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else if (ing.equals("0")) {
                        Toast.makeText(getActivity(), string_msg, Toast.LENGTH_LONG).show();
                    }
                    break;
            }
        }
    };

    /**
     * 删除SharedPreferences数据文件
     *
     * @param files
     */
    public void deleteCache(File[] files) {

        boolean flag;
        for (File itemFile : files) {
            flag = itemFile.delete();
            if (flag == false) {
                deleteCache(itemFile.listFiles());
            }
        }
    }
}
