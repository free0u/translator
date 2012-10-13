package ru.ifmo.rain.evdokimov;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {
//	class MainView extends View {
//		public MainView(Context context) {
//			super(context);
//		}
//		
//		@Override
//		public void onDraw(Canvas canvas) {
//			Paint paint = new Paint();
//			paint.setColor(Color.YELLOW);
//			canvas.drawRect(10, 10, 110, 110, paint);
//		}
//	}
	Button btnTranslate;
	TextView tView;
	EditText eText;
	boolean f = true;
	
	// stub for translate
	private String getTranslate(String s) {
		return s;
	}
	
	// stub for pictures
	private ArrayList<String> getPicturesUrl(String s) {
		ArrayList<String> res = new ArrayList<String>();
		String[] arr = {"http://rodnik-dobra.ru/wp-content/uploads/2011/10/%D0%BA%D0%BE%D1%82%D1%8F%D1%82%D0%B0.jpg",
			"http://www.google.com/logos/2012/alicia-moreau-de-justo-12-hp.jpg",
			"http://www.google.com/logos/2012/Ivo_Andric-2012-hp.jpg",
			"http://www.google.com/logos/2012/uganda12-hp.jpg",
			"http://www.google.com/logos/2012/janusz-korczak-2012-hp.jpg",
			"http://www.google.com/logos/2012/Brazil_Elections-2012-hp.jpgs", 
			"http://www.google.com/logos/2012/david-unaipon-12-hp.jpg",
			"http://www.google.com/logos/2012/Moon_Festival-2012-hp.jpg",
			"http://www.google.com/logos/2012/chuseok12-hp.jpg"
		};
		for (int i = 0; i < 10; ++i) {
			res.add(arr[i % arr.length]);
		}
		return res;
	}
	
	
	// clear input form
	@Override
	public void onResume() {
		super.onResume();
		eText.setText("");
	}
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main);
        
        btnTranslate = (Button)findViewById(R.id.button1);
        tView = (TextView)findViewById(R.id.textView1);
        eText = (EditText)findViewById(R.id.editText1);
        
        btnTranslate.setOnClickListener(this);
    }


	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.button1:
			Intent intent = new Intent(this, TranslateActivity.class);
			String textToTranslate = eText.getText().toString();
			
			if (textToTranslate.length() == 0) {
				Toast.makeText(getApplicationContext(), R.string.emptystring, Toast.LENGTH_SHORT).show();
				return;
			}
			
			intent.putExtra("translate", getTranslate(textToTranslate));
			intent.putExtra("picturesUrl", getPicturesUrl(textToTranslate));
			
			startActivity(intent);
			break;
		default:
			break;
		}
	}
}