package com.yuen636.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.google.gson.Gson;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.yuen636.bean.ActiveBean;
import com.yuen636.pager.BasePager;
import com.yuen636.pager.CheckPager;
import com.yuen636.pager.MyViewPager;
import com.yuen636.pager.PushPager;
import com.yuen636.test.R;
import com.yuen636.utils.CacheUtils;
import com.yuen636.utils.WebUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MyActiveActivity2 extends AppCompatActivity implements View.OnClickListener {
    private LinearLayout mLlSecTitle;
    private LinearLayout mLlPushed;
    private View mLineLeft;
    private LinearLayout mLlCheck;
    private View mLineRight;
    private String url2 = "User/myActivity11";
    private MyViewPager myViewPager;
    private List<BasePager> pagers = new ArrayList<BasePager>();
    private String url = "User/myActivity1";
    private Context context;
    private void assignViews() {
        mLlSecTitle = (LinearLayout) findViewById(R.id.ll_sec_title);
        mLlPushed = (LinearLayout) findViewById(R.id.ll_pushed);
        mLineLeft = findViewById(R.id.line_left);
        mLlCheck = (LinearLayout) findViewById(R.id.ll_check);
        mLineRight = findViewById(R.id.line_right);
        myViewPager = (MyViewPager) findViewById(R.id.vp_myViewPager);
        mLlPushed.setOnClickListener(this);
        mLlCheck.setOnClickListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_active2);
        context = this;
        assignViews();
        List<String> dataList = getData(url);
        pagers.add(new PushPager(this, dataList));
        List<String> dataList2 = getData(url2);
        pagers.add(new CheckPager(this, dataList2));
        myViewPager.setAdapter(new MyAdapter());
        myViewPager.setCurrentItem(0);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_pushed:
                mLineRight.setVisibility(View.GONE);
                mLineLeft.setVisibility(View.VISIBLE);
                myViewPager.setCurrentItem(0);
                break;
            case R.id.ll_check:
                mLineLeft.setVisibility(View.GONE);
                mLineRight.setVisibility(View.VISIBLE);
                myViewPager.setCurrentItem(1);
                break;
        }
    }

    private List<String> getData(final String url) {
        final List<String> titleList = new ArrayList<>();
        Log.d("PushPager", url);
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
                Log.d("PushPager", result);
                CacheUtils.saveCache(context, url, result);
                Gson gson = new Gson();
                ActiveBean activeBean = gson.fromJson(result, ActiveBean.class);
                for (int i = 0; i < activeBean.data.size(); i++) {
                    Log.d("PushPager", activeBean.data.get(i).title);
                    titleList.add(activeBean.data.get(i).title);
                }

            }
        });
        return titleList;
    }
    class MyAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return pagers.size();
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            BasePager currentPager = pagers.get(position);
            View currentPagerView = currentPager.initView();
            container.addView(currentPagerView);
            currentPager.initData();
            return currentPagerView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }


    }

}
