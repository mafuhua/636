package com.yuen636.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lidroid.xutils.BitmapUtils;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.yuen636.bean.CollectBean;
import com.yuen636.test.R;
import com.yuen636.utils.WebUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CollectActivity extends Activity {
    private RelativeLayout mRlCollectionTitle;
    private ListView mLvCollection;
    private String url = "User/myCollect";
    private MyAdapter myAdapter;
    private Context context;

    private List<String> userImagList = new ArrayList<>();
    private List<String> userIconContentList = new ArrayList<>();
    private List<String> userNameList = new ArrayList<>();
    private List<String> timeList = new ArrayList<>();
    private List<String> titleList = new ArrayList<>();
    private BitmapUtils mBitmapUtils;
    private String BASEURL = "http://www.yuenkeji.com/636gongshe/";

    private void assignViews() {
        mRlCollectionTitle = (RelativeLayout) findViewById(R.id.rl_collection_title);
        mLvCollection = (ListView) findViewById(R.id.lv_collection);
        mBitmapUtils = new BitmapUtils(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collect);
        context = this;
        assignViews();
        getData();
        SystemClock.sleep(100);
        myAdapter = new MyAdapter();
        mLvCollection.setAdapter(myAdapter);
    }

    private void getData() {
        Log.d("MyActiveActivity", url);
        WebUtil.posttoserver(url, "user_id", "3", new Callback() {
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
        Gson gson = new Gson();
        CollectBean collectBean = gson.fromJson(json, CollectBean.class);
        List<CollectBean.DataEntity> dataList = collectBean.data;
        for (int i = 0; i < dataList.size(); i++) {
            Log.d("ActiveActivity", dataList.get(i).user);
            CollectBean.DataEntity dataEntity = dataList.get(i);
            String imgHeadUrl = BASEURL + dataEntity.user_img;
            String imgContentUrl = BASEURL + dataEntity.user_img;
            titleList.add(dataEntity.collect.title);
            userNameList.add(dataEntity.user);
            userImagList.add(imgHeadUrl);
            userIconContentList.add(imgContentUrl);
            timeList.add(dataEntity.time);
            Log.d("CollectActivity", dataEntity.time);
            Log.d("CollectActivity****", dataList.get(i).user);
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
                convertView = View.inflate(getApplicationContext(), R.layout.lv_collection_item, null);
                viewHolder = new ViewHolder(convertView);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            viewHolder.tvtime.setText(timeList.get(position));
            viewHolder.tvname.setText(userNameList.get(position));
            viewHolder.tvcontent.setText(titleList.get(position));
            mBitmapUtils.display(viewHolder.iconhead, userImagList.get(position));
            mBitmapUtils.display(viewHolder.iconcontent, userIconContentList.get(position));
            return convertView;
        }

        class ViewHolder {
            public final ImageView iconhead;
            public final TextView tvname;
            public final TextView tvtime;
            public final ImageView iconcontent;
            public final TextView tvcontent;
            public final View root;

            public ViewHolder(View root) {
                this.root = root;
                iconhead = (ImageView) root.findViewById(R.id.icon_head);
                tvname = (TextView) root.findViewById(R.id.tv_name);
                tvtime = (TextView) root.findViewById(R.id.tv_time);
                iconcontent = (ImageView) root.findViewById(R.id.icon_content);
                tvcontent = (TextView) root.findViewById(R.id.tv_content);
            }
        }
    }
}