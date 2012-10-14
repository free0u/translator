package ru.ifmo.rain.evdokimov;

import java.io.IOException;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {
	ArrayList<String> ans;

	class MainView extends View {
		public MainView(Context context) throws IOException {
			super(context);

			ImageParser parser = new ImageParser();
			String query = "cat wiki";
			ans = parser.parse(query);
		}

		@Override
		public void onDraw(Canvas canvas) {
			Paint paint = new Paint();
			paint.setColor(Color.BLACK);
			canvas.drawRect(10, 10, 110, 110, paint);

			for (int i = 0; i < 10; i++) {
				canvas.drawText(ans.get(i), 5, i * 50 + 5, paint);
			}

		}
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		try {
			setContentView(new MainView(this));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}