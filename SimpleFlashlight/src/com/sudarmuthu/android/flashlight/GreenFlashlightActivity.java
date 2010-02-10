package com.sudarmuthu.android.flashlight;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class GreenFlashlightActivity extends Activity {
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.green);
		
		Button redButton = (Button)findViewById(R.id.red_button);
		redButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
			}
		});
	}
}
