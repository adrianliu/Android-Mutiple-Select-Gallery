package com.pgk.venusgallery.models;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.pgk.venusgallery.opts.VGGallery;
import com.pgk.venusgallery.opts.VGGallery.SelectOption;
import com.pgk.venusgallery.utils.VGMediaStore;

public class VGManager {
	
	/**
	 * the current activity
	 */
	private Activity mActivity = null;
	
	/**
	 * list of albums
	 */
	private List<VGAlbum> mAblumList = null;
	
	/**
	 * list the holds the user select item
	 */
	private List<VGPhoto> selectedItemList = new ArrayList<VGPhoto>();
	
	/**
	 * the total number of selectable items count
	 */
	private Integer numberOfSelectableItem = 1;
	
	/**
	 * the method the user selects the images
	 */
	private SelectOption	SELECT_OPTION 	= VGGallery.SelectOption.SINGLE;
	
	/**
	 * the number of cols
	 * min >= 1
	 */
	private Integer 		NUMBER_OF_COLS 	= 2;
	
	public VGManager(Activity activity) {
		this.mActivity = activity;
		Intent mIntent = this.mActivity.getIntent();
		Bundle mBundle = mIntent.getExtras();
		
		// config selection option 
		VGGallery.SelectOption optsSelect = (VGGallery.SelectOption)mBundle.get(VGGallery.SELECT_OPTION);
		if (optsSelect != null) {
			if (optsSelect.toString().equalsIgnoreCase( VGGallery.SelectOption.SINGLE.toString())) {
				SELECT_OPTION = VGGallery.SelectOption.SINGLE;
			} else if (optsSelect.toString().equalsIgnoreCase( VGGallery.SelectOption.MULTIPLE.toString())) {
				SELECT_OPTION = VGGallery.SelectOption.SINGLE;
			}
		}
		
		// config number of cols 
		Integer optsColsCount = (Integer)mBundle.get(VGGallery.NUMBER_OF_COLS);
		if (optsColsCount != null) {
			if (optsColsCount >= 1) {
				NUMBER_OF_COLS = optsColsCount;
			}
		}
		
		// config number of selectable items
		Integer optsSelectCount = (Integer)mBundle.get(VGGallery.NUMBER_OF_SELECTABLE_ITEMS);
		if (optsSelectCount != null) {
			if (optsSelectCount >= 1) {
				numberOfSelectableItem = optsSelectCount;
			}
		}
		
		// get gallery list
		this.mAblumList = VGMediaStore.getPhotoGallery(mActivity);
	}

	/**
	 * get the number of select items
	 * @return Integer
	 */
	public Integer getNumberOfSelectableItem() {
		return numberOfSelectableItem;
	}

	/**
	 * set the number of selectable item
	 * @param numberOfSelectableItem minimum is 1
	 */
	public void setNumberOfSelectableItem(Integer numberOfSelectableItem) {
		if (numberOfSelectableItem >= 1) {
			this.numberOfSelectableItem = numberOfSelectableItem;
		}
	}

	/**
	 * return the list of selected item
	 * @return
	 */
	public List<VGPhoto> getSelectedItemList() {
		return selectedItemList;
	}

	/**
	 * add a select album to the selected list
	 * @param selectedItemList
	 */
	public void addToSelectedItemList(VGPhoto selectedItemList) {
		if (this.selectedItemList.size() < numberOfSelectableItem) {
			this.selectedItemList.add(selectedItemList);
		}
	}

	public List<VGAlbum> getAblumList() {
		return mAblumList;
	}
	
	public String[] returnString() {
		
		List<String> list = new ArrayList<String>();
		
		for (VGPhoto photo : selectedItemList) {
			list.add(photo.getFilepath());
		}
		
		String[] strarray = list.toArray(new String[0]);
		
		return strarray;
	}

	public void setAblumList(List<VGAlbum> mAblumList) {
		this.mAblumList = mAblumList;
	}

	public Integer getNumberOfColumns() {
		return NUMBER_OF_COLS;
	}

	public SelectOption getSelectOptions() {
		return SELECT_OPTION;
	}
}
