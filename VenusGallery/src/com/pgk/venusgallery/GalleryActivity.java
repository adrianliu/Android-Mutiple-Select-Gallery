package com.pgk.venusgallery;

import com.pgk.venusgallery.fragments.AlbumGalleryFragment;
import com.pgk.venusgallery.fragments.PhotoGalleryFragment;
import com.pgk.venusgallery.models.VGManager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

public class GalleryActivity extends FragmentActivity {

	Activity 	mActivity 	= null;
	VGManager	mManager	= null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gallery);
		mActivity = this;
		mManager = new VGManager(mActivity);
		
		// load albm gallery
		loadAlbumGallery();
	}

	private void changeFragment(Fragment targetFragment) {
		/*
		 * getSupportFragmentManager() .beginTransaction() .setCustomAnimations(
		 * R.anim.slide_in_left, 0, 0, R.anim.slide_out_left)
		 * .replace(R.id.main_fragment, targetFragment, "TAG_FRAGMENT")
		 * .commit();
		 */
		getSupportFragmentManager().beginTransaction()
				.replace(R.id.main_fragment, targetFragment, "TAG_FRAGMENT")
				.setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
				.commit();
	}

	private void loadAlbumGallery() {

		changeFragment(new AlbumGalleryFragment(mManager) {
			@Override
			public void openAlbum(Integer idx) {

				// load albm gallery
				changeFragment(new PhotoGalleryFragment(mManager, idx));
			}
		});
	}

	@Override
	public void onBackPressed() {
		
		Fragment fragment = (Fragment) getSupportFragmentManager().findFragmentByTag("TAG_FRAGMENT");

		if (fragment.getClass().equals(PhotoGalleryFragment.class)) {
			loadAlbumGallery();
			return;
		}
		
		Intent i = new Intent();
		mActivity.setResult(Activity.RESULT_CANCELED, i);
		mActivity.finish();
	}
}
