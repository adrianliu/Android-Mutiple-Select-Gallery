package com.pgk.venusgallery.fragments;

import com.pgk.venusgallery.R;
import com.pgk.venusgallery.adapter.AlbumAdapter;
import com.pgk.venusgallery.models.VGManager;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

public class AlbumGalleryFragment extends Fragment {
	
	Integer 		fragmentIndex;
	Activity 		mActivity 		= null;
	
	GridView		mGridView		= null;
	VGManager 		mManager		= null;
	AlbumAdapter	mGalleryAdapter = null;
	
	public AlbumGalleryFragment(VGManager mAblumList) {
		this.mManager = mAblumList;
	}
	
	@Override 
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View layoutView = inflater.inflate(R.layout.fragment_album, container, false);
		mActivity = getActivity();
		
		// setup view
		mGalleryAdapter = new AlbumAdapter(mActivity, mManager.getAblumList());
		mGridView = (GridView)layoutView.findViewById(R.id.mGridView);
		mGridView.setNumColumns(mManager.getNumberOfColumns());
		mGridView.setAdapter(mGalleryAdapter);
		mGridView.setOnItemClickListener(gridOnItemClickListener);
		Animation anim = AnimationUtils.loadAnimation(mActivity.getApplicationContext(), R.anim.fly_in_center);
		mGridView.setAnimation(anim);
        anim.start();
		
		return layoutView;
	}
	
	OnItemClickListener gridOnItemClickListener = new OnItemClickListener() {
		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
			openAlbum(arg2);
		}
	};
	
	public void openAlbum(Integer idx) {
		
	}
}
