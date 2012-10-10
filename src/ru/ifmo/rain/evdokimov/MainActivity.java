package ru.ifmo.rain.evdokimov;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {
	class MainView extends View {
		public MainView(Context context) {
			super(context);
		}
		
		@Override
		public void onDraw(Canvas canvas) {
			Paint paint = new Paint();
			paint.setColor(Color.YELLOW);
			canvas.drawRect(10, 10, 110, 110, paint);
		}
	}
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new MainView(this));
    }
}