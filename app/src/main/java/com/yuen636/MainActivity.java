package com.yuen636;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.yuen636.activity.ActiveActivity;
import com.yuen636.activity.CollectActivity;
import com.yuen636.activity.PersonalActivity;
import com.yuen636.bean.ActiveBean;
import com.yuen636.test.R;
import com.yuen636.utils.WebUtil;

import java.io.IOException;

public class MainActivity extends Activity implements View.OnClickListener {
    private Button mButton;
    private Button mButton2;
    private Button mButton4;
    private Context context;

    // private String
    private void assignViews() {
        mButton = (Button) findViewById(R.id.button1);
        mButton2 = (Button) findViewById(R.id.button2);
        mButton4 = (Button) findViewById(R.id.button4);
        mButton.setOnClickListener(this);
        mButton2.setOnClickListener(this);
        mButton4.setOnClickListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        assignViews();
        context = this;
        WebUtil.posttoserver("User/myActivity11", "1", "3", new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {
                String result = response.body().string();
                Log.d("MainActivity****", result);
                parseJson(result);
            }
        });


    }

    private void parseJson(String json) {
        Gson gson = new Gson();
        ActiveBean activeBean = gson.fromJson(json, ActiveBean.class);
        Log.d("ActiveActivity", activeBean.data.get(0).title);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button1:
                Intent intent = new Intent(context, PersonalActivity.class);
                startActivity(intent);
                break;
            case R.id.button2:
                Intent intent1 = new Intent(context, CollectActivity.class);
                startActivity(intent1);
                break;
            case R.id.button4:
                Intent intent3 = new Intent(context, ActiveActivity.class);
                startActivity(intent3);
                break;
        }
    }
}
