package com.xiaoai.ImageRadioButton;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;

public class MainActivity extends Activity {

//	private RadioGroup rg;
//
//	private RadioButton btn1;
//	private RadioButton btn2;

	private CheckBoxGroup cg;
	
	private ImageCheckBox btn1;
	private ImageCheckBox btn2;
	
	private Context mContext;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		this.mContext = this;
		
		cg = (CheckBoxGroup) findViewById(R.id.rg);
		
		btn1 = (ImageCheckBox) findViewById(R.id.btn1);
		btn1.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				Log.w("test", "btn1 isChecked: " + isChecked);
			}
		});
		
		btn2 = (ImageCheckBox) findViewById(R.id.btn2);
		btn2.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				Log.w("test", "btn2 isChecked: " + isChecked);
			}
		});
		
		cg.setOnCheckedChangeListener(new CheckBoxGroup.OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CheckBoxGroup group, int checkedId) {
				// TODO Auto-generated method stub
				Log.w("test1", "**** btn1 isChecked: " + btn1.isChecked());
				Log.w("test1", "**** btn2 isChecked: " + btn2.isChecked());
			}
		});

//		btn1 = (RadioButton) findViewById(R.id.btn1);
//		btn1.setOnCheckedChangeListener(new OnCheckedChangeListener() {
//			
//			@Override
//			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//				Toast.makeText(mContext, isChecked + "", 0).show();
//			}
//		});
//		btn2 = (RadioButton) findViewById(R.id.btn2);
//
//		rg = (RadioGroup) findViewById(R.id.rg);
//		rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//
//			@Override
//			public void onCheckedChanged(RadioGroup group, int checkedId) {
//				switch (checkedId) {
//				case R.id.btn1:
//					Toast.makeText(mContext,
//							"btn1 isChecked: " + btn1.isChecked(),
//							Toast.LENGTH_SHORT).show();
//					break;
//				case R.id.btn2:
//					Toast.makeText(mContext,
//							"btn2 isChecked: " + btn2.isChecked(),
//							Toast.LENGTH_SHORT).show();
//					break;
//				default:
//					break;
//				}
//			}
//		});
	}

}
