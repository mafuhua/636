package com.yuen636.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.yuen636.test.R;


public class ActiveActivity extends Activity {
    private ListView mLvActive;
    private String[] str = new String[]{"我参与的", "我发布的", "即将开始", "已经结束"};
    private Context context;

    private void assignViews() {
        mLvActive = (ListView) findViewById(R.id.lv_active);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_active);
        context = this;
        assignViews();

        mLvActive.setAdapter(new MyAdapter());
        mLvActive.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position != 1) {
                    Intent intent = new Intent(ActiveActivity.this, MyActiveActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt("position", position);
                    intent.putExtras(bundle);
                    ActiveActivity.this.startActivity(intent);
                }else {
                    Intent intent = new Intent(ActiveActivity.this, MyActiveActivity2.class);
                    context.startActivity(intent);
                }


            }
        });
    }


    class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return str.length;
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
            viewHolder.tvmypush.setText(str[position]);
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
