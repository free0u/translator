package ru.ifmo.rain.evdokimov;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

public class TranslateActivity extends Activity {
	
	TextView tv;
	LinearLayout lin;
	
	private void update() {
		Intent intent = getIntent();
		String translate = intent.getExtras().getString("translate");
		tv.setText(translate);
		ArrayList<String> urls = intent.getExtras().getStringArrayList("picturesUrl");
		
		LayoutInflater lInfater = getLayoutInflater();
		for (int i = 0; i < 10; ++i) {
			View item = lInfater.inflate(R.layout.itemimage, lin, false);
			lin.addView(item);
		}
		
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
