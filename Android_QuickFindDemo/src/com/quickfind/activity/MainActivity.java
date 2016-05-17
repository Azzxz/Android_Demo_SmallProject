package com.quickfind.activity;

import com.quickfind.R;
import com.quickfind.view.QuickFindBar;
import com.quickfind.view.QuickFindBar.OnTouchLetterListener;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends Activity {
	private QuickFindBar letterQFBar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		letterQFBar = (QuickFindBar) findViewById(R.id.qfb_letter);

		letterQFBar.setOnTouchLetterListener(new OnTouchLetterListener() {

			@Override
			public void onTouchLetter(String currentLetter) {
				Log.e("currentLetter", "currentLetter : " + currentLetter);
			}
		});
	}

}
