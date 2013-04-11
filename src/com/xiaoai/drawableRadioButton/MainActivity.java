package com.xiaoai.drawableRadioButton;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.xiaoai.drawableRadioButton.R;

public class MainActivity extends Activity {

	private RadioGroup rg;

	private Context mContext;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		this.mContext = this;

		rg = (RadioGroup) findViewById(R.id.rg);
		rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				switch (checkedId) {
				case R.id.btn1:
					Toast.makeText(mContext, "checked btn1", Toast.LENGTH_SHORT)
							.show();
					break;
				case R.id.btn2:
					Toast.makeText(mContext, "checked btn2", Toast.LENGTH_SHORT)
							.show();
					break;
				default:
					break;
				}
			}
		});
	}

}
