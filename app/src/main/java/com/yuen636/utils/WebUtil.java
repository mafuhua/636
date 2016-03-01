package com.yuen636.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class WebUtil {
	final private static String IP = "http://www.yuenkeji.com/636gongshe/" + "Home/";
	// final private static String IP = ToolApplication.IP+"Home/";
	final private static String TAG = WebUtil.class.getSimpleName();

	public static void posttoserver(String aname, HashMap<String, String> amap, Callback acallback) {

		Log.d(TAG, "" + amap.toString() + "!!!!!!!!!!!!!!!!");

		OkHttpClient mOkHttpClient = new OkHttpClient();
		FormEncodingBuilder builder = new FormEncodingBuilder();

		Iterator<?> iter = amap.entrySet().iterator();
		while (iter.hasNext()) {
			Map.Entry<?, ?> entry = (Entry<?, ?>) iter.next();
			String key = entry.getKey().toString();
			String val = entry.getValue().toString();
			builder.add(key, val);
		}

		RequestBody formBody = builder.build();

		Request request = new Request.Builder().url(IP + aname).post(formBody).build();
		mOkHttpClient.newCall(request).enqueue(acallback);

	}

	public static void posttoserver(String aname, String key, String val, Callback acallback) {

		Log.d(TAG, "key==" + key + ";val==" + val + "!!!!!!!!!!!!!!!!");

		OkHttpClient mOkHttpClient = new OkHttpClient();
		RequestBody formBody = new FormEncodingBuilder().add(key, val).build();
		Request request = new Request.Builder().url(IP + aname).post(formBody).build();
		mOkHttpClient.newCall(request).enqueue(acallback);

	}

	public static void postimagetoserver(String aname, String imagepath, String imagetype, Callback acallback) {

		Imagedto imagedto=encode(imagepath);

		OkHttpClient mOkHttpClient = new OkHttpClient();
		RequestBody formBody = new FormEncodingBuilder().add("src_w", imagedto.imageW).add("src_h", imagedto.imageH).add("naho", imagetype).add("base64_image_content", imagedto.imagecode).build();
		Request request = new Request.Builder().url(IP + aname).post(formBody).build();
		mOkHttpClient.newCall(request).enqueue(acallback);

	}

	public static Imagedto encode(String path) {
		// decode to bitmap
		Bitmap bitmap = BitmapFactory.decodeFile(path);
		Log.d(TAG, "bitmap width: " + bitmap.getWidth() + " height: " + bitmap.getHeight());
		// convert to byte array
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
		byte[] bytes = baos.toByteArray();

		// base64 encode
		byte[] encode = Base64.encode(bytes, Base64.DEFAULT);
		String encodeString = new String(encode);
		Imagedto imagedto=new Imagedto(encodeString, bitmap.getWidth()+"", bitmap.getHeight()+"");
		return imagedto;
	}
	
	public static class Imagedto{
		public String imagecode;
		public String imageW;
		public String imageH;
		public Imagedto(String imagecode, String imageW, String imageH) {
			super();
			this.imagecode = imagecode;
			this.imageW = imageW;
			this.imageH = imageH;
		}
		
		
	}
}
