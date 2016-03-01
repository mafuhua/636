package com.yuen636.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.yuen636.test.R;
import com.yuen636.utils.WebUtil;

import java.io.IOException;
import java.util.HashMap;

public class ChangeUserActivity extends AppCompatActivity {
    private LinearLayout mLayoutChangeTitle;
    private TextView mTvChangeTitle;
    private Button mBtnChangeCommit;
    private EditText mEtChangeBody;
    private String type;
    private int requestCode;
    private SharedPreferences sp;
    private String nick_name;
    private String name;
    private String phone;
    private String wechat;
    private String company;
    private String job;
    private String jobshow;
    private String web;
    private String address2;
    private String intro;

    private void assignViews() {
        mEtChangeBody = (EditText) findViewById(R.id.et_change_body);
        mLayoutChangeTitle = (LinearLayout) findViewById(R.id.layout_change_title);
        mTvChangeTitle = (TextView) findViewById(R.id.tv_change_title);
        mBtnChangeCommit = (Button) findViewById(R.id.btn_change_commit);
        mBtnChangeCommit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                String newContent = mEtChangeBody.getText().toString().trim();
                if (!TextUtils.isEmpty(newContent)) {
                    Log.d("ChangeUserActivity", "newContent" + newContent);
                    intent.putExtra("newContent", newContent);
                    sp.edit().putString(type, newContent).apply();
                    setResult(requestCode, intent);
                    HashMap<String, String> map = new HashMap<>();
                    map.put("user_id", "3");
                    map.put("nick_name", nick_name);
                    map.put("name", name);
                    map.put("phone", phone);
                    map.put("wechat", wechat);
                    map.put("company", company);
                    map.put("job", job);
                    map.put("jobshow", jobshow);
                    map.put("web", web);
                    map.put("address2", address2);
                    map.put("intro", intro);
                    map.put(type, newContent);
                    WebUtil.posttoserver("User/update", map, new Callback() {
                        @Override
                        public void onFailure(Request request, IOException e) {

                        }

                        @Override
                        public void onResponse(Response response) throws IOException {

                        }
                    });
                    finish();
                } else {
                    Toast.makeText(ChangeUserActivity.this, "不能为空!", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_user);
        sp = getSharedPreferences("changeUser", MODE_PRIVATE);
        assignViews();
        Intent intent = getIntent();
        String stringExtra = intent.getStringExtra("user");
        mTvChangeTitle.setText("更改" + stringExtra);
        type = intent.getStringExtra("type");
        requestCode = intent.getIntExtra("requestCode", 0);
        nick_name = sp.getString("nick_name", "");
        name = sp.getString("name", "");
        phone = sp.getString("phone", "");
        wechat = sp.getString("wechat", "");
        company = sp.getString("company", "");
        job = sp.getString("job", "");
        jobshow = sp.getString("jobshow", "");
        web = sp.getString("web", "");
        address2 = sp.getString("address2", "");
        intro = sp.getString("intro", "");
    }

}
