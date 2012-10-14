package ru.ifmo.rain.evdokimov;

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
	Button btnTranslate;
	TextView tView;
	EditText eText;
	
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
			
			intent.putExtra("text", textToTranslate);
			
			startActivity(intent);
			break;
		default:
			break;
		}
	}
}