package ru.ifmo.rain.evdokimov;

import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

public class TranslateActivity extends Activity {
	
	TextView tv;
	LinearLayout lin;
	ImageView imageView;
	ProgressBar progressBar;
	
	Handler handler;
	final int STATUS_TRANSLATE = 0;
	final int STATUS_PICTURE = 1;
	int indexOfPicture = 0;
	LayoutInflater lInflater;
	ImageParser imageParser;
	Message msg;
	
	private String getTranslate(String s) {
		return "hello";
	}
	
	private void update() {
		Intent intent = getIntent();
		final String translate = intent.getExtras().getString("text");
		
		lInflater = getLayoutInflater();
		imageParser = new ImageParser();
		
		handler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				if (msg.what == STATUS_TRANSLATE) {
					tv.setText((String)msg.obj);
				} else if (msg.what == STATUS_PICTURE) {
					View item = lInflater.inflate(R.layout.itemimage, lin, false);
					imageView = (ImageView)item.findViewById(R.id.itemImageView);
					imageView.setImageBitmap((Bitmap)msg.obj);
					lin.addView(imageView);
					progressBar.setVisibility(View.GONE);
				}
			}
		};
		
		Thread th = new Thread(new Runnable() {
			public void run() {
				String res = getTranslate(translate);
				msg = handler.obtainMessage(STATUS_TRANSLATE, res);
				handler.sendMessage(msg);
				
				ArrayList<String> urls = imageParser.parse(translate);
				if (urls == null) return;
				
				for (String url : urls) {
					Bitmap bm = getBitmapFromUrl(url);
					msg = handler.obtainMessage(STATUS_PICTURE, bm);
					handler.sendMessage(msg);
				}
			}
		});
		th.start();
	}

	private Bitmap getBitmapFromUrl(String url) {
		Bitmap bm = null;
		try {
	        URLConnection conn = new URL(url).openConnection();
	        conn.connect();
	        bm = BitmapFactory.decodeStream(conn.getInputStream());
	    } catch (Exception ignore) {
	    }
	    return bm;
	}
	
	@Override
	public void onCreate(Bundle state) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(state);
		setContentView(R.layout.translate);
		tv = (TextView)findViewById(R.id.textViewTranslate);
		lin = (LinearLayout)findViewById(R.id.linLayout);
		progressBar = (ProgressBar)findViewById(R.id.progressBar1);
		
		update();
	}
}
