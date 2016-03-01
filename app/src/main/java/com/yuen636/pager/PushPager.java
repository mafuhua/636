package com.yuen636.pager;

import android.content.Context;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.yuen636.test.R;

import java.util.List;

/**
 * Created by Administrator on 2016/2/26.
 */
public class PushPager extends BasePager {
    private Context context;
    private List<String> dataList;
    private MyAdapter myAdapter;
    private ListView mLvActive;

    public PushPager(Context context, List<String> dataList) {
        super(context);
        this.context = context;
        this.dataList = dataList;
    }

    @Override
    public View initView() {
        View view = View.inflate(context, R.layout.push_pager, null);
        mLvActive = (ListView) view.findViewById(R.id.lv_active);
        myAdapter = new MyAdapter();
        mLvActive.setAdapter(myAdapter);
        SystemClock.sleep(100);
        myAdapter.notifyDataSetChanged();
        return view;
    }

    @Override
    public void initData() {

    }

    class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return dataList.size();
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
                convertView = View.inflate(context, R.layout.lv_active_item, null);
                viewHolder = new ViewHolder(convertView);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            viewHolder.tvmypush.setText(dataList.get(position));
            Log.d("MyAdapter", dataList.get(position));
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
