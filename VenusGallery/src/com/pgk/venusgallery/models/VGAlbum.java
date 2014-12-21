package com.pgk.venusgallery.models;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Bitmap;

public class VGAlbum {
	
	private String idx;
	private String name;
	private List<VGPhoto> albumItemList = new ArrayList<VGPhoto>();
	private Bitmap previewImage = null;
	
	public VGAlbum() {
		
	}

	public String getIdx() {
		return idx;
	}

	public void setIdx(String idx) {
		this.idx = idx;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<VGPhoto> getItemList() {
		return albumItemList;
	}

	public void addAlbumItem(VGPhoto item) {
		albumItemList.add(item);
	}

	public Bitmap getPreviewImage() {
		return previewImage;
	}

	public void setPreviewImage(Bitmap previewImage) {
		this.previewImage = previewImage;
	}
}
