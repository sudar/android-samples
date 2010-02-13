package com.sudarmuthu.android.homework1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class DialerActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        // Add a Button	
        Button dialerButton = (Button)findViewById(R.id.dialer_button);
        dialerButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//open the phone dialer on clicking the button
				Intent intent = new Intent(Intent.ACTION_DIAL);
				startActivity(intent);
			}
		});
    }
}