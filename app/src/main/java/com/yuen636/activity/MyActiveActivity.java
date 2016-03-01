package com.yuen636.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.yuen636.bean.ActiveBean;
import com.yuen636.test.R;
import com.yuen636.utils.WebUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MyActiveActivity extends Activity {
    private LinearLayout mLlPushed;
    private View mLineLeft;
    private LinearLayout mLlCheck;
    private View mLineRight;
    private MyAdapter myAdapter;
    private ListView mLvActive;
    private String url;
    private List<String> titleList = new ArrayList<>();
    private int position;
    private LinearLayout mLlSecTitle;
    private Context context;

    private void assignViews() {
        mLlSecTitle = (LinearLayout) findViewById(R.id.ll_sec_title);
        mLlPushed = (LinearLayout) findViewById(R.id.ll_pushed);
        mLineLeft = findViewById(R.id.line_left);
        mLlCheck = (LinearLayout) findViewById(R.id.ll_check);
        mLineRight = findViewById(R.id.line_right);
        mLvActive = (ListView) findViewById(R.id.lv_active);

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_active);
        context = this;
        assignViews();
        Bundle bundle;
        bundle = getIntent().getExtras();
        position = bundle.getInt("position");
        Log.d("MyActiveActivity", position + "");
        checkUrl(position);
        getData();
        SystemClock.sleep(100);
        myAdapter = new MyAdapter();
        mLvActive.setAdapter(myAdapter);
        mLvActive.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        break;
                    case 1:
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                }
            }
        });

    }


    private void checkUrl(int position) {
        switch (position) {
            case 0:
                url = "User/myActivity1";
               /* getData();
                myAdapter = new MyAdapter();
                mLvActive.setAdapter(myAdapter);*/
                break;
            case 2:
                url = "User/myActivity3";
               /* getData();
                myAdapter = new MyAdapter();
                mLvActive.setAdapter(myAdapter);*/
                break;
            case 3:
                url = "User/myActivity4";
                /*getData();
                myAdapter = new MyAdapter();
                mLvActive.setAdapter(myAdapter);*/
                break;

        }
    }

    private void getData() {
        Log.d("MyActiveActivity", url);
        HashMap<String, String> map = new HashMap<>();
        map.put("page", "1");
        map.put("user_id", "3");
        WebUtil.posttoserver(url, map, new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {
                String result = response.body().string();
                Log.d("MyActiveActivity00", result);
                parseJson(result);

            }
        });
    }


    private void parseJson(String json) {
        titleList.clear();
        Gson gson = new Gson();
        ActiveBean activeBean = gson.fromJson(json, ActiveBean.class);
        for (int i = 0; i < activeBean.data.size(); i++) {
            Log.d("ActiveActivity", activeBean.data.get(i).title);
            titleList.add(activeBean.data.get(i).title);
        }

    }

    class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return titleList.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            if (convertView == null) {
                convertView = View.inflate(getApplicationContext(), R.layout.lv_active_item, null);
                viewHolder = new ViewHolder(convertView);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            viewHolder.tvmypush.setText(titleList.get(position));
            return convertView;
        }

        public class ViewHolder {
            public final TextView tvmypush;
            public final View root;

            public ViewHolder(View root) {
                this.root = root;
                tvmypush = (TextView) root.findViewById(R.id.tv_mypush);
            }
        }
    }

}
