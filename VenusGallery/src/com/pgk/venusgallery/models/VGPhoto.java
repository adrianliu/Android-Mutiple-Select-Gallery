package com.pgk.venusgallery.models;

import android.graphics.Bitmap;

import com.pgk.venusgallery.opts.VGModel.AlbumItemType;

public class VGPhoto {
	
	private AlbumItemType type;
	private String id;
	private String filepath;
	private String filename;
	private Bitmap imageBitmap = null;
	private Boolean selected = false;
	
	public VGPhoto() {
		
	}
	
	public AlbumItemType getType() {
		return type;
	}
	
	public void setType(AlbumItemType type) {
		this.type = type;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getFilepath() {
		return filepath;
	}
	
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
	
	public String getFilename() {
		return filename;
	}
	
	public void setFilename(String filename) {
		this.filename = filename;
	}

	public Bitmap getImageBitmap() {
		return imageBitmap;
	}

	public void setImageBitmap(Bitmap imageBitmap) {
		this.imageBitmap = imageBitmap;
	}

	public Boolean isSelected() {
		return selected;
	}

	public void setSelected(Boolean selected) {
		this.selected = selected;
	}
}
