package com.racing.racing;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.racing.App.AppSp;
import com.racing.R;
import com.racing.entity.UserBean;
import com.racing.utils.GlideCircleTransform;
import com.racing.utils.ToastUtil;
import com.racing.view.LoadingDialog;
import com.racing.view.MyToast;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Created by Administrator on 2017/09/24.
 * 历史开奖
 */
public class YuCeDetailActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView tv_header_title;
    public TextView tv_left;
    private LoadingDialog loadingDialog;
    private ImageView img_user;
    private TextView tv_name, tv_fensi, tv_fanan, tv_ok, tv_beizhu, tv_qishu, tv_type2, tv_time;
    private TextView btn_guanzhu;
    private TextView tv_title;
    private String img_user_url = null;
    private String name = null, fensi = null, fanan = null, ok = null, beizhu = null, title = null;
    private Long shijian = null;
    private String qishu = null;
    private UserBean userBean;
    private AppSp app;
    private int id;
    //预测期数
    private TextView tv_yuce_qishu;
    private String yuce_qishu;
    //杀码类型
    private TextView tv_ma;
    private int numtype;
    //预测类型
    private TextView tv_yuce_type;
    private int yece_type;
    //预测位置
    private TextView tv_yuce_weizhi;
    private int yuce_weizhi;
    //发布时间
    private TextView tv_yuce_time;
    private Long yuce_time;
    private String qishu1;
    //预测内容
    private TextView tv_1;
    private String tv_neirong;
    private TextView tv_zhankai;

    //查看
    private LinearLayout btn_query;

    private String now_msg = null;

    private LinearLayout ll_yuce_detail;

    private TextView tv_money;

    //金额
    private String money;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.yuce_detail);
        InitView();
        tv_header_title.setText("预测详情");
        if (loadingDialog == null) {
            loadingDialog = new LoadingDialog(YuCeDetailActivity.this, R.style.LoadingDialog);
        }
        loadingDialog.show();
        getQishuAndTime();
        app = new AppSp();
        userBean = (UserBean) app.getObjectFromShare(YuCeDetailActivity.this, "user");
        id = getIntent().getIntExtra("id", -1);
        tv_name.setText(getIntent().getStringExtra("name"));
        if (getIntent().getStringExtra("fensi")!=null) {
            if (Integer.parseInt(getIntent().getStringExtra("fensi")) > 0) {
                tv_fensi.setText("粉丝数：" + getIntent().getStringExtra("fensi"));
            } else {
                tv_fensi.setText("粉丝数：0");
            }
        }else{
            tv_fensi.setText("粉丝数：0");
        }
        tv_fanan.setText(getIntent().getStringExtra("fanan"));
        tv_ok.setText(getIntent().getStringExtra("ok"));
        tv_beizhu.setText(getIntent().getStringExtra("beizhu"));
        img_user_url = getIntent().getStringExtra("tx");
        Glide.with(YuCeDetailActivity.this).load(img_user_url).centerCrop().placeholder(R.drawable.img_user_tx)
                .transform(new GlideCircleTransform(YuCeDetailActivity.this))
                .diskCacheStrategy(DiskCacheStrategy.SOURCE).into(img_user);

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
                MyToast.getToast(YuCeDetailActivity.this, "请求失败");
            }

            @Override
            public void onResponse(String response, int id) {
                try {
                    //数据解析
                    JSONObject jsonObj = new JSONObject(response).getJSONObject("next");
                    qishu1 = jsonObj.getString("qishu");
                    Message message = new Message();
                    message.what = 30000;
                    myHandler.sendMessage(message);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void getDate(int id, int uid, String caizhong) {
        OkHttpUtils
                .post()
                .url("http://www.zse6.com/index.php/mobile/ajax/yuce_info")
                .addParams("id", id + "")
                .addParams("uid", uid + "")
                .addParams("caizhong", caizhong + "")
                .build().execute(new StringCallback() {
            @Override
            public void onError(okhttp3.Call call, Exception e, int id) {
                MyToast.getToast(YuCeDetailActivity.this, "请求失败");
            }

            @Override
            public void onResponse(String response, int id) {
                try {
                    JSONObject json_ing = new JSONObject(response);
                    JSONObject jsonObj = new JSONObject(response).getJSONObject("yuce");
                    yuce_qishu = jsonObj.getString("qishu");
                    numtype = jsonObj.getInt("numtype");
                    yece_type = jsonObj.getInt("type");
                    yuce_weizhi = jsonObj.getInt("weizhi");
                    yuce_time = jsonObj.getLong("shijian");
                    money = jsonObj.getString("money");
                    Log.i("aaa",money);
                    tv_neirong = jsonObj.getString("yuceinfo");
                    int ing = json_ing.getInt("ing");
                    if (ing == 1) {
                        Message message = new Message();
                        message.what = 100;
                        myHandler.sendMessage(message);
                    } else {
                        Message message = new Message();
                        message.what = 300;
                        myHandler.sendMessage(message);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void InitView() {
        tv_header_title = (TextView) findViewById(R.id.tv_header_title);
        tv_left = (TextView) findViewById(R.id.tv_left);
        tv_left.setOnClickListener(this);
        img_user = (ImageView) findViewById(R.id.img_user);
        tv_name = (TextView) findViewById(R.id.tv_name);
        tv_fensi = (TextView) findViewById(R.id.tv_fensi);
        btn_guanzhu = (TextView) findViewById(R.id.btn_guanzhu);
        btn_guanzhu.setOnClickListener(this);
        tv_fanan = (TextView) findViewById(R.id.tv_fanan);
        tv_ok = (TextView) findViewById(R.id.tv_ok);
        tv_beizhu = (TextView) findViewById(R.id.tv_beizhu);
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_qishu = (TextView) findViewById(R.id.tv_qishu);
        tv_type2 = (TextView) findViewById(R.id.tv_type2);
        //
        tv_yuce_qishu = (TextView) findViewById(R.id.tv_yuce_qishu);
        //杀码类型
        tv_ma = (TextView) findViewById(R.id.tv_ma);
        //预测类型
        tv_yuce_type = (TextView) findViewById(R.id.tv_yuce_type);
        //预测位置
        tv_yuce_weizhi = (TextView) findViewById(R.id.tv_yuce_weizhi);
        //预测时间
        tv_yuce_time = (TextView) findViewById(R.id.tv_yuce_time);
        //预测内容
        tv_1 = (TextView) findViewById(R.id.tv_1);
        //展开
        tv_zhankai = (TextView) findViewById(R.id.tv_zhankai);
        tv_zhankai.setOnClickListener(this);

        btn_query = (LinearLayout) findViewById(R.id.btn_query);
        btn_query.setOnClickListener(this);
        //预测详情
        ll_yuce_detail = (LinearLayout) findViewById(R.id.ll_yuce_detail);
        ll_yuce_detail.setOnClickListener(this);
        //金额
        tv_money = (TextView) findViewById(R.id.tv_money);

    }


    //handler
    public Handler myHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 30000:
                    getDate(id, userBean.getId(), "pk10");
                    break;
                case 200:
                    Glide.with(YuCeDetailActivity.this).load(img_user_url).centerCrop().placeholder(R.color.white)
                            .transform(new GlideCircleTransform(YuCeDetailActivity.this))
                            .diskCacheStrategy(DiskCacheStrategy.SOURCE).into(img_user);
                    tv_name.setText(name);
                    tv_fensi.setText(fensi);
                    tv_fanan.setText(getIntent().getStringExtra("fanan"));
                    tv_ok.setText(ok);
                    tv_beizhu.setText(beizhu);
                    tv_title.setText(title);
                    tv_qishu.setText("预测期数:" + "");
                    tv_type2.setText("");
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    //前面的lSysTime是秒数，先乘1000得到毫秒数，再转为java.util.Date类型
                    Date dt = new Date(shijian * 1000);
                    String sDateTime = sdf.format(dt);
                    tv_time.setText(sDateTime);
                    if (loadingDialog != null) {
                        loadingDialog.cancel();
                    }
                    break;
                case 300:
                    ToastUtil.showToast(YuCeDetailActivity.this, "无数据");
                    if (loadingDialog != null) {
                        loadingDialog.cancel();
                    }
                    break;
                case 400:
                    btn_guanzhu.setText("已关注");
                    btn_guanzhu.setBackgroundResource(R.drawable.btn_red_black_selector_update1);
                    ToastUtil.showToast(YuCeDetailActivity.this, "关注成功");
                    if (loadingDialog != null) {
                        loadingDialog.cancel();
                    }
                    break;
                case 100:
                    tv_money.setText("花费"+money+"积分查看");
                    tv_yuce_qishu.setText("预测期数: " + yuce_qishu+"期");
                    if (Long.parseLong(qishu1)>=Long.parseLong(yuce_qishu)){
                        ll_yuce_detail.setVisibility(View.GONE);
                        btn_query.setVisibility(View.VISIBLE);
                    }else{
                        ll_yuce_detail.setVisibility(View.VISIBLE);
                        btn_query.setVisibility(View.GONE);
                    }
                    if (numtype == 1) {
                        tv_ma.setText("定码");
                    } else if (numtype == 0) {
                        tv_ma.setText("杀码");
                    }
                    if (yece_type == 1) {
                        tv_yuce_type.setText("预测类型：号码定位胆");
                    } else if (yece_type == 2) {
                        tv_yuce_type.setText("预测类型：大小系列");
                    } else if (yece_type == 3) {
                        tv_yuce_type.setText("预测类型：单双系列");
                    } else if (yece_type == 4) {
                        tv_yuce_type.setText("预测类型：质和系列");
                    } else if (yece_type == 5) {
                        tv_yuce_type.setText("预测类型：龙虎系列");
                    } else if (yece_type == 6) {
                        tv_yuce_type.setText("预测类型：冠亚和系列");
                    }
                    if (yece_type == 1) {
                        if (yuce_weizhi == 1) {
                            tv_yuce_weizhi.setText("预测位置：冠军");
                        } else if (yuce_weizhi == 2) {
                            tv_yuce_weizhi.setText("预测位置：亚军");
                        } else if (yuce_weizhi == 3) {
                            tv_yuce_weizhi.setText("预测位置：季军");
                        } else if (yuce_weizhi == 4) {
                            tv_yuce_weizhi.setText("预测位置：第四名");
                        } else if (yuce_weizhi == 5) {
                            tv_yuce_weizhi.setText("预测位置：第五名");
                        } else if (yuce_weizhi == 6) {
                            tv_yuce_weizhi.setText("预测位置：第六名");
                        } else if (yuce_weizhi == 7) {
                            tv_yuce_weizhi.setText("预测位置：第七名");
                        } else if (yuce_weizhi == 8) {
                            tv_yuce_weizhi.setText("预测位置：第八名");
                        } else if (yuce_weizhi == 9) {
                            tv_yuce_weizhi.setText("预测位置：第九名");
                        } else if (yuce_weizhi == 10) {
                            tv_yuce_weizhi.setText("预测位置：第十名");
                        }
                    } else if (yece_type == 2) {
                        if (yuce_weizhi == 1) {
                            tv_yuce_weizhi.setText("预测位置：冠军");
                        } else if (yuce_weizhi == 2) {
                            tv_yuce_weizhi.setText("预测位置：亚军");
                        } else if (yuce_weizhi == 3) {
                            tv_yuce_weizhi.setText("预测位置：季军");
                        } else if (yuce_weizhi == 4) {
                            tv_yuce_weizhi.setText("预测位置：第四名");
                        } else if (yuce_weizhi == 5) {
                            tv_yuce_weizhi.setText("预测位置：第五名");
                        } else if (yuce_weizhi == 6) {
                            tv_yuce_weizhi.setText("预测位置：第六名");
                        } else if (yuce_weizhi == 7) {
                            tv_yuce_weizhi.setText("预测位置：第七名");
                        } else if (yuce_weizhi == 8) {
                            tv_yuce_weizhi.setText("预测位置：第八名");
                        } else if (yuce_weizhi == 9) {
                            tv_yuce_weizhi.setText("预测位置：第九名");
                        } else if (yuce_weizhi == 10) {
                            tv_yuce_weizhi.setText("预测位置：第十名");
                        }
                    } else if (yece_type == 3) {
                        if (yuce_weizhi == 1) {
                            tv_yuce_weizhi.setText("预测位置：冠军");
                        } else if (yuce_weizhi == 2) {
                            tv_yuce_weizhi.setText("预测位置：亚军");
                        } else if (yuce_weizhi == 3) {
                            tv_yuce_weizhi.setText("预测位置：季军");
                        } else if (yuce_weizhi == 4) {
                            tv_yuce_weizhi.setText("预测位置：第四名");
                        } else if (yuce_weizhi == 5) {
                            tv_yuce_weizhi.setText("预测位置：第五名");
                        } else if (yuce_weizhi == 6) {
                            tv_yuce_weizhi.setText("预测位置：第六名");
                        } else if (yuce_weizhi == 7) {
                            tv_yuce_weizhi.setText("预测位置：第七名");
                        } else if (yuce_weizhi == 8) {
                            tv_yuce_weizhi.setText("预测位置：第八名");
                        } else if (yuce_weizhi == 9) {
                            tv_yuce_weizhi.setText("预测位置：第九名");
                        } else if (yuce_weizhi == 10) {
                            tv_yuce_weizhi.setText("预测位置：第十名");
                        }
                    } else if (yece_type == 4) {
                        if (yuce_weizhi == 1) {
                            tv_yuce_weizhi.setText("预测位置：冠军");
                        } else if (yuce_weizhi == 2) {
                            tv_yuce_weizhi.setText("预测位置：亚军");
                        } else if (yuce_weizhi == 3) {
                            tv_yuce_weizhi.setText("预测位置：季军");
                        } else if (yuce_weizhi == 4) {
                            tv_yuce_weizhi.setText("预测位置：第四名");
                        } else if (yuce_weizhi == 5) {
                            tv_yuce_weizhi.setText("预测位置：第五名");
                        } else if (yuce_weizhi == 6) {
                            tv_yuce_weizhi.setText("预测位置：第六名");
                        } else if (yuce_weizhi == 7) {
                            tv_yuce_weizhi.setText("预测位置：第七名");
                        } else if (yuce_weizhi == 8) {
                            tv_yuce_weizhi.setText("预测位置：第八名");
                        } else if (yuce_weizhi == 9) {
                            tv_yuce_weizhi.setText("预测位置：第九名");
                        } else if (yuce_weizhi == 10) {
                            tv_yuce_weizhi.setText("预测位置：第十名");
                        }
                    } else if (yece_type == 5) {
                        if (yuce_weizhi == 1) {
                            tv_yuce_weizhi.setText("预测位置：冠军");
                        } else if (yuce_weizhi == 2) {
                            tv_yuce_weizhi.setText("预测位置：亚军");
                        } else if (yuce_weizhi == 3) {
                            tv_yuce_weizhi.setText("预测位置：季军");
                        } else if (yuce_weizhi == 4) {
                            tv_yuce_weizhi.setText("预测位置：第四名");
                        } else if (yuce_weizhi == 5) {
                            tv_yuce_weizhi.setText("预测位置：第五名");
                        } else if (yuce_weizhi == 6) {
                            tv_yuce_weizhi.setText("预测位置：第六名");
                        } else if (yuce_weizhi == 7) {
                            tv_yuce_weizhi.setText("预测位置：第七名");
                        } else if (yuce_weizhi == 8) {
                            tv_yuce_weizhi.setText("预测位置：第八名");
                        } else if (yuce_weizhi == 9) {
                            tv_yuce_weizhi.setText("预测位置：第九名");
                        } else if (yuce_weizhi == 10) {
                            tv_yuce_weizhi.setText("预测位置：第十名");
                        }
                    } else if (yece_type == 6) {
                        if (yuce_weizhi == 1) {
                            tv_yuce_weizhi.setText("预测位置：冠军");
                        } else if (yuce_weizhi == 2) {
                            tv_yuce_weizhi.setText("预测位置：亚军");
                        } else if (yuce_weizhi == 3) {
                            tv_yuce_weizhi.setText("预测位置：季军");
                        } else if (yuce_weizhi == 4) {
                            tv_yuce_weizhi.setText("预测位置：第四名");
                        } else if (yuce_weizhi == 5) {
                            tv_yuce_weizhi.setText("预测位置：第五名");
                        } else if (yuce_weizhi == 6) {
                            tv_yuce_weizhi.setText("预测位置：第六名");
                        } else if (yuce_weizhi == 7) {
                            tv_yuce_weizhi.setText("预测位置：第七名");
                        } else if (yuce_weizhi == 8) {
                            tv_yuce_weizhi.setText("预测位置：第八名");
                        } else if (yuce_weizhi == 9) {
                            tv_yuce_weizhi.setText("预测位置：第九名");
                        } else if (yuce_weizhi == 10) {
                            tv_yuce_weizhi.setText("预测位置：第十名");
                        }
                    }

                    SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    //前面的lSysTime是秒数，先乘1000得到毫秒数，再转为java.util.Date类型
                    java.util.Date dt1 = new Date(yuce_time * 1000);
                    String sDateTime1 = sdf1.format(dt1);  //得到精确到秒的表示：08/31/2006 21:08:00
                    tv_yuce_time.setText("发布时间：" + sDateTime1);
                    tv_1.setText(tv_neirong);
                    if (loadingDialog != null) {
                        loadingDialog.cancel();
                    }
                    break;
                case 1000:
                    ToastUtil.showToast(YuCeDetailActivity.this, now_msg + "");
                    if (loadingDialog != null) {
                        loadingDialog.cancel();
                    }
                    ll_yuce_detail.setVisibility(View.GONE);
                    btn_query.setVisibility(View.VISIBLE);
                    break;
                case 2000:
                    ll_yuce_detail.setVisibility(View.VISIBLE);
                    btn_query.setVisibility(View.GONE);
                    break;
                case 3000:
                    ToastUtil.showToast(YuCeDetailActivity.this, "余额不足");
                    ll_yuce_detail.setVisibility(View.GONE);
                    btn_query.setVisibility(View.VISIBLE);
                    break;
                case 4000:
                    ll_yuce_detail.setVisibility(View.VISIBLE);
                    btn_query.setVisibility(View.GONE);
                    break;

            }
        }
    };

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_left:
                YuCeDetailActivity.this.finish();
                break;
            case R.id.btn_guanzhu:
                //关注
                getFollow(userBean.getId(), getIntent().getIntExtra("id", -1));
                break;
            case R.id.tv_time:
                //预测详情

                break;
            case R.id.tv_zhankai:
                tv_beizhu.setMaxLines(100);
                break;
            case R.id.btn_query:
                final AlertDialog.Builder builder = new AlertDialog.Builder(YuCeDetailActivity.this);
                builder.setTitle("提示信息");
                builder.setMessage("您确定要购买一个预测方案?");
                builder.setNegativeButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //花费积分查看
                        getBuy(userBean.getId(),id);
                    }
                });
                builder.setPositiveButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        builder.create().cancel();
                    }
                });
                builder.show();

                break;
        }
    }

    /**
     * 关注专家
     */
    private void getFollow(int uid, int manid) {
        OkHttpUtils
                .post()
                .url("http://www.zse6.com/index.php/mobile/ajax/guanzhu")
                .addParams("uid", uid + "")
                .addParams("manid", manid + "")
                .build().execute(new StringCallback() {
            @Override
            public void onError(okhttp3.Call call, Exception e, int id) {
                MyToast.getToast(YuCeDetailActivity.this, "请求失败");
            }

            @Override
            public void onResponse(String response, int id) {
                try {
                    JSONObject json_ing = new JSONObject(response);
                    int ing = json_ing.getInt("ing");
                    if (ing == 1) {
                        Message message = new Message();
                        message.what = 400;
                        myHandler.sendMessage(message);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    //购买一个预测方案接口
    private void getBuy(int uid, int id) {
        OkHttpUtils
                .post()
                .url("http://www.zse6.com/index.php/mobile/ajax/buy")
                .addParams("uid", uid + "")
                .addParams("id", id + "")
                .addParams("caizhong","pk10")
                .build().execute(new StringCallback() {
            @Override
            public void onError(okhttp3.Call call, Exception e, int id) {
                MyToast.getToast(YuCeDetailActivity.this, "请求失败");
            }

            @Override
            public void onResponse(String response, int id) {
                try {
                    JSONObject json_ing = new JSONObject(response);
                    int ing = json_ing.getInt("ing");
                    now_msg = json_ing.getString("msg");
                    if (ing == -2) {
                        Message message = new Message();
                        message.what = 1000;
                        myHandler.sendMessage(message);
                    } else if (ing == 1) {
                        Message message = new Message();
                        message.what = 2000;
                        myHandler.sendMessage(message);
                    } else if (ing == -1) {
                        Message message = new Message();
                        message.what = 3000;
                        myHandler.sendMessage(message);
                    } else if (ing == 0) {
                        Message message = new Message();
                        message.what = 4000;
                        myHandler.sendMessage(message);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }


}
