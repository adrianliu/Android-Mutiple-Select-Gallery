package com.pgk.venusgallery;

import com.pgk.venusgallery.opts.VGGallery;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class TestActivity extends Activity {
	
	Activity 	mActivity;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_test);
		mActivity = this;

		
		//
		// Single Select
		//
		((Button) findViewById(R.id.buttonPhotoGallery)).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(mActivity, GalleryActivity.class);
				
				// set the selection config
				intent.putExtra(VGGallery.SELECT_OPTION, VGGallery.SelectOption.MULTIPLE);
				
				// set the number of cols
				intent.putExtra(VGGallery.NUMBER_OF_COLS, 3);
				
				// set the number of selectable item
				intent.putExtra(VGGallery.NUMBER_OF_SELECTABLE_ITEMS, 1);
				
				mActivity.startActivityForResult(intent, 100);
			}
		});
		
		//
		// Multiple Select 3
		//
		((Button) findViewById(R.id.buttonPhotoGallery3)).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(mActivity, GalleryActivity.class);
				
				// set the selection config
				intent.putExtra(VGGallery.SELECT_OPTION, VGGallery.SelectOption.MULTIPLE);
				
				// set the number of cols
				intent.putExtra(VGGallery.NUMBER_OF_COLS, 3);
				
				// set the number of selectable item
				intent.putExtra(VGGallery.NUMBER_OF_SELECTABLE_ITEMS, 3);
				
				mActivity.startActivityForResult(intent, 100);
			}
		});
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
		
		super.onActivityResult(requestCode, resultCode, intent);

		switch (requestCode) {
			case 100: {
				if (resultCode == RESULT_OK) {
					String[] files = (String[])intent.getExtras().get(VGGallery.RETURN_DATA_STRING);
					String toastStr = "";
					for (int i = 0; i < files.length; i++) {
						toastStr += files[i] + " ,";
					}
					Toast.makeText(mActivity, "" + toastStr, Toast.LENGTH_SHORT).show();
				}
				break;
			}
			default: break; 
		}
	}
}