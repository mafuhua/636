package com.yuen636.pager;

import android.content.Context;
import android.view.View;

public abstract class BasePager {
	public Context context;
	
	public BasePager(Context context) {
		this.context = context;
	}
	public abstract View initView();
	public abstract void initData();
}
