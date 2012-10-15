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
	String ans;

	class MainView extends View {
		Paint paint = new Paint();
		public MainView(Context context) throws IOException {
			super(context);
			Translate tr = new Translate();
			ans = tr.getTranslate("sword");
			
		}

		@Override
		public void onDraw(Canvas canvas) {
			canvas.drawText(ans, 10, 10, new Paint());
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