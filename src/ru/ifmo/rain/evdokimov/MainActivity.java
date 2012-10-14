package ru.ifmo.rain.evdokimov;

import java.io.IOException;
import java.util.ArrayList;
import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {
	ArrayList<String> ans;

	class MainView extends View {
		Paint paint = new Paint();
		public MainView(Context context) throws IOException {
			super(context);

			ImageParser parser = new ImageParser();
			String query = "cat";
			ans = parser.parse(query);
		}

		@Override
		public void onDraw(Canvas canvas) {
			for (int i = 0; i < 10; i++) {
				canvas.drawText(i +  ") "+ ans.get(i), 5, 20 + i * 50, paint);
			}
		}
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		try {
			setContentView(new MainView(this));
		} catch (IOException e) {
		}
	}
}