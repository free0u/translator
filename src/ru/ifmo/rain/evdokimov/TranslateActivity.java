package ru.ifmo.rain.evdokimov;

import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

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
import android.widget.TextView;

public class TranslateActivity extends Activity {
	
	TextView tv;
	LinearLayout lin;
	ImageView imageView;
	
	private void update() {
		Intent intent = getIntent();
		String translate = intent.getExtras().getString("translate");
		tv.setText(translate);
		ArrayList<String> urls = intent.getExtras().getStringArrayList("picturesUrl");
		if (urls == null) return;
		
		LayoutInflater lInflater = getLayoutInflater();
		for (int i = 0; i < urls.size(); ++i) {
			String url = urls.get(i);
			View item = lInflater.inflate(R.layout.itemimage, lin, false);
			imageView = (ImageView)item.findViewById(R.id.itemImageView);
			imageView.setImageResource(R.drawable.ic_launcher);
			lin.addView(imageView);
			updateImage(url, imageView);
		}
	}
	
	private void updateImage(final String url, final ImageView imageView) {

		final Handler handler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				Bitmap bm = (Bitmap)msg.obj;
				imageView.setImageBitmap(bm);
			}
		};
		
		Thread th = new Thread(new Runnable() {
			public void run() {
				// TODO Auto-generated method stub
				Bitmap bm = getBitmapFromUrl(url);
				if (bm != null) {
					handler.sendMessage(handler.obtainMessage(0, bm));
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
		update();
	}
}
