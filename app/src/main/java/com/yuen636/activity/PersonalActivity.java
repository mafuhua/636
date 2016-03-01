package com.yuen636.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lidroid.xutils.BitmapUtils;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.yuen636.bean.PersonalBean;
import com.yuen636.test.R;
import com.yuen636.utils.WebUtil;

import java.io.IOException;

public class PersonalActivity extends Activity implements View.OnClickListener {
    private BitmapUtils mBitmapUtils;
    private SharedPreferences sp;
    private Context context;
    private String BASEURL = "http://www.yuenkeji.com/636gongshe/";
    private String url = "User/edit";
    private RelativeLayout mRlUserIcon;
    private ImageView mIvUserIcon;
    private RelativeLayout mRlUsername;
    private TextView mTvUserName;
    private RelativeLayout mRlUserNickName;
    private TextView mTvUserNickname;
    private RelativeLayout mRlUserJob;
    private TextView mTvUserJob;
    private TextView mTvUserTelphone;
    private RelativeLayout mRlUserCompany;
    private TextView mTvUserCompany;
    private RelativeLayout mRlUserWxh;
    private TextView mTvUserWxh;
    private RelativeLayout mRlUserUrl;
    private TextView mTvUserUrl;
    private RelativeLayout mRlUserAddress;
    private TextView mTvUserAddress;
    private RelativeLayout mRlUserDec;
    private TextView mTvUserDec;
    private RelativeLayout mRlUserClass;
    private TextView mTvUserClass;
    private RelativeLayout mRlUserCommite;
    private TextView mTvUserCommite;
    private RelativeLayout mRlUserTrade;
    private TextView mTvUserTrade;
    private RelativeLayout mRlUserTelphone;

    private void assignViews() {
        mRlUserIcon = (RelativeLayout) findViewById(R.id.rl_user_icon);
        mIvUserIcon = (ImageView) findViewById(R.id.iv_user_icon);
        mRlUsername = (RelativeLayout) findViewById(R.id.rl_username);
        mTvUserName = (TextView) findViewById(R.id.tv_user_name);
        mRlUserNickName = (RelativeLayout) findViewById(R.id.rl_user_nickName);
        mTvUserNickname = (TextView) findViewById(R.id.tv_user_nickname);
        mRlUserJob = (RelativeLayout) findViewById(R.id.rl_user_job);
        mTvUserJob = (TextView) findViewById(R.id.tv_user_job);
        mTvUserTelphone = (TextView) findViewById(R.id.tv_user_telphone);
        mRlUserTelphone = (RelativeLayout) findViewById(R.id.rl_user_telphone);
        mRlUserCompany = (RelativeLayout) findViewById(R.id.rl_user_company);
        mTvUserCompany = (TextView) findViewById(R.id.tv_user_company);
        mRlUserWxh = (RelativeLayout) findViewById(R.id.rl_user_wxh);
        mTvUserWxh = (TextView) findViewById(R.id.tv_user_wxh);
        mRlUserUrl = (RelativeLayout) findViewById(R.id.rl_user_url);
        mTvUserUrl = (TextView) findViewById(R.id.tv_user_url);
        mRlUserAddress = (RelativeLayout) findViewById(R.id.rl_user_address);
        mTvUserAddress = (TextView) findViewById(R.id.tv_user_address);
        mRlUserDec = (RelativeLayout) findViewById(R.id.rl_user_dec);
        mTvUserDec = (TextView) findViewById(R.id.tv_user_dec);
        mRlUserClass = (RelativeLayout) findViewById(R.id.rl_user_class);
        mTvUserClass = (TextView) findViewById(R.id.tv_user_class);
        mRlUserCommite = (RelativeLayout) findViewById(R.id.rl_user_commite);
        mTvUserCommite = (TextView) findViewById(R.id.tv_user_commite);
        mRlUserTrade = (RelativeLayout) findViewById(R.id.rl_user_trade);
        mTvUserTrade = (TextView) findViewById(R.id.tv_user_trade);
        mRlUserAddress.setOnClickListener(this);
        mRlUsername.setOnClickListener(this);
        mRlUserNickName.setOnClickListener(this);
        mRlUserClass.setOnClickListener(this);
        mRlUserCommite.setOnClickListener(this);
        mRlUserCompany.setOnClickListener(this);
        mRlUserDec.setOnClickListener(this);
        mRlUserIcon.setOnClickListener(this);
        mRlUserTelphone.setOnClickListener(this);
        mRlUserTrade.setOnClickListener(this);
        mRlUserJob.setOnClickListener(this);
        mRlUserUrl.setOnClickListener(this);
        mRlUserWxh.setOnClickListener(this);
        mBitmapUtils = new BitmapUtils(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal);
        context = this;
        sp = getSharedPreferences("changeUser", MODE_PRIVATE);
        assignViews();
        getData();

    }

    private void getData() {
        Log.d("PersonalActivity", "User/edit");
        WebUtil.posttoserver(url, "user_id", "3", new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {
                String result = response.body().string();
                Log.d("PersonalActivity", result);
                parseJson(result);

            }
        });
    }

    private void parseJson(String json) {
        Gson gson = new Gson();
        final PersonalBean personalBean = gson.fromJson(json, PersonalBean.class);
        Log.d("PersonalActivity", personalBean.data.user_img);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                String imgUrl = BASEURL + personalBean.data.user_img;
                mBitmapUtils.display(mIvUserIcon, imgUrl);
                mTvUserName.setText(personalBean.data.name);
                mTvUserNickname.setText(personalBean.data.nick_name);
                mTvUserJob.setText(personalBean.data.job);
                mTvUserTelphone.setText(personalBean.data.phone);
                mTvUserWxh.setText(personalBean.data.wechat);
                mTvUserCompany.setText(personalBean.data.company);
                mTvUserCommite.setText(personalBean.data.fenweihui);
                mTvUserClass.setText(personalBean.data.classX);
                mTvUserTrade.setText(personalBean.data.trade);
                mTvUserAddress.setText(personalBean.data.address);
                mTvUserUrl.setText(personalBean.data.web);
                mTvUserDec.setText(personalBean.data.intro);
                mTvUserAddress.setText(personalBean.data.nick_name);
                mTvUserAddress.setText(personalBean.data.nick_name);
            }
        });
        sp.edit().putString("nick_name", personalBean.data.nick_name).apply();
        sp.edit().putString("name", personalBean.data.name).apply();
        sp.edit().putString("phone", personalBean.data.phone).apply();
        sp.edit().putString("wechat", personalBean.data.wechat).apply();
        sp.edit().putString("company", personalBean.data.company).apply();
        sp.edit().putString("job", personalBean.data.job).apply();
        sp.edit().putString("jobshow", personalBean.data.jobshow).apply();
        sp.edit().putString("web", personalBean.data.web).apply();
        sp.edit().putString("address2", personalBean.data.address2).apply();
        sp.edit().putString("intro", personalBean.data.intro).apply();

    }

    private void startIntent(Class<? extends Activity> clazz, String title, String type, int requestCode) {
        Intent intent = new Intent(this, clazz);
        intent.putExtra("user", title);
        intent.putExtra("type", type);
        intent.putExtra("requestCode", requestCode);
        startActivityForResult(intent, requestCode);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_user_icon:
                break;
            case R.id.rl_username:
                startIntent(ChangeUserActivity.class, "姓名", "name", 2);
                break;
            case R.id.rl_user_nickName:
                startIntent(ChangeUserActivity.class, "昵称", "nick_name", 3);
                break;
            case R.id.rl_user_job:
                startIntent(ChangeUserActivity.class, "职务", "job", 4);
                break;
            case R.id.rl_user_telphone:
                startIntent(ChangeUserActivity.class, "手机号", "phone", 5);
                break;
            case R.id.rl_user_company:
                startIntent(ChangeUserActivity.class, "公司名称", "company", 6);
                break;
            case R.id.rl_user_wxh:
                startIntent(ChangeUserActivity.class, "微信号", "wechat", 7);
                break;
            case R.id.rl_user_url:
                startIntent(ChangeUserActivity.class, "网址", "web", 8);
                break;
            case R.id.rl_user_address:
                startIntent(ChangeUserActivity.class, "地址", "address2", 9);
                break;
            case R.id.rl_user_dec:
                startIntent(ChangeUserActivity.class, "个人说明", "intro", 10);
                break;
            case R.id.rl_user_class:
                break;
            case R.id.rl_user_commite:
                break;
            case R.id.rl_user_trade:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // 根据上面发送过去的请求吗来区别
      //  super.onActivityResult(requestCode, resultCode, data);
        if (data == null){
            return;
        }
        String change = data.getExtras().getString("newContent");
        Log.d("PersonalActivity", change);
        switch (requestCode) {
            case 2:
                mTvUserName.setText(change);
                break;
            case 3:
                mTvUserNickname.setText(change);
                break;
            case 4:
                mTvUserJob.setText(change);
                break;
            case 5:
                mTvUserTelphone.setText(change);
                break;
            case 6:
                mTvUserCompany.setText(change);
                break;
            case 7:

                mTvUserWxh.setText(change);
                break;
            case 8:
                mTvUserUrl.setText(change);
                break;
            case 9:
                mTvUserAddress.setText(change);
                break;
            case 10:
                mTvUserDec.setText(change);
                break;
            default:
                break;
        }

    }
}
