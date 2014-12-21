package com.pgk.venusgallery.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;

import com.pgk.venusgallery.R;
import com.pgk.venusgallery.adapter.PhotoAdapter;
import com.pgk.venusgallery.models.VGAlbum;
import com.pgk.venusgallery.models.VGManager;
import com.pgk.venusgallery.models.VGPhoto;
import com.pgk.venusgallery.opts.VGGallery;

public class PhotoGalleryFragment extends Fragment {
	
	Integer 		fragmentIndex;
	Activity 		mActivity 		= null;
	
	GridView		mGridView		= null;
	PhotoAdapter	mGalleryAdapter = null;
	TextView 		album_name 		= null;
	VGManager 		mManager		= null;
	Integer			mIndex 			= 0;
	
	public PhotoGalleryFragment(VGManager mManager, Integer mIndex) {
		this.mManager = mManager;
		this.mIndex = mIndex; 
	}
	
	@Override 
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View layoutView = inflater.inflate(R.layout.fragment_photo, container, false);
		mActivity = getActivity();
		
		VGAlbum album = mManager.getAblumList().get(mIndex);
		
		// setup view
		mGalleryAdapter = new PhotoAdapter(mActivity, album.getItemList());
		mGridView = (GridView)layoutView.findViewById(R.id.mGridView);
		mGridView.setNumColumns(mManager.getNumberOfColumns());
		mGridView.setAdapter(mGalleryAdapter);
		Animation anim = AnimationUtils.loadAnimation(mActivity.getApplicationContext(), R.anim.fly_in_center);
		mGridView.setAnimation(anim);
		mGridView.setOnItemClickListener(gridOnItemClickListener);
        anim.start();
		
		album_name = (TextView) layoutView.findViewById(R.id.album_name);
		album_name.setText(album.getName() + " (" + album.getItemList().size() + ")");
		album_name.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				mActivity.onBackPressed();
			}
		});
		
		((Button) layoutView.findViewById(R.id.btn_finish_select)).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent i = new Intent();
				i.putExtra(VGGallery.RETURN_DATA_STRING, mManager.returnString());
				mActivity.setResult(Activity.RESULT_OK, i);
				mActivity.finish();
			}
		});
		
		return layoutView;
	}
	
	OnItemClickListener gridOnItemClickListener = new OnItemClickListener() {
		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
			
			if (mManager.getSelectedItemList().size() >= mManager.getNumberOfSelectableItem()) {
				Toast.makeText(mActivity, "Reached Maximum Selectable Items", Toast.LENGTH_SHORT).show();
				return;
			} 
			
			CheckBox selectCheckbox = (CheckBox)arg1.findViewById(R.id.selectCheckbox);
			
			VGAlbum album = mManager.getAblumList().get(mIndex);
			VGPhoto photo = album.getItemList().get(arg2);
			photo.setSelected( (photo.isSelected()) ? false : true);
			
			selectCheckbox.setChecked(photo.isSelected());
			
			mManager.addToSelectedItemList(photo);
		}
	};
}