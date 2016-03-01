package com.yuen636.pager;

import android.content.Context;
import android.os.SystemClock;
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
public class CheckPager extends BasePager {

    private Context context;
    private List<String> dataList2;
    private MyAdapter myAdapter;
    private ListView mLvActive;

    public CheckPager(Context context, List<String> dataList2) {
        super(context);
        this.context = context;
        this.dataList2 = dataList2;
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
            return dataList2.size();
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
            viewHolder.tvmypush.setText(dataList2.get(position));
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
