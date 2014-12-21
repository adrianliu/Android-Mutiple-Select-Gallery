package com.pgk.venusgallery.utils;

import java.util.ArrayList;
import java.util.List;

import com.pgk.venusgallery.models.VGAlbum;
import com.pgk.venusgallery.models.VGPhoto;
import com.pgk.venusgallery.opts.VGModel;

import android.app.Activity;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.provider.MediaStore.MediaColumns;

public class VGMediaStore {

	public static List<VGAlbum> getPhotoGallery(Activity activity) {
		
		Uri 			uri;
		Cursor 			cursor;
		List<VGAlbum> 	albumList;
		String[] 		projection = { MediaStore.Images.Media._ID, MediaStore.Images.Media.BUCKET_ID, MediaStore.Images.Media.BUCKET_DISPLAY_NAME, MediaColumns.DATA };
		
	    uri 		= MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
	    cursor 		= activity.getContentResolver().query(uri, projection, null, null, null);
	    albumList 	= new ArrayList<VGAlbum>();
	    
		while (cursor.moveToNext()) {

			VGAlbum album = new VGAlbum();

			album.setIdx(cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.BUCKET_ID)));
			album.setName(cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.BUCKET_DISPLAY_NAME)));

			if (!VGCompare.doesListContainAlbum(albumList, album)) {
				albumList.add(album);
			}
			
			Integer idx = VGCompare.getIndexWhereAlbum(albumList, album);
			
			// make we found the propper index
			if (idx == -1) {
				
			} else {
				
				VGPhoto photo = new VGPhoto();
				
				photo.setType(VGModel.AlbumItemType.PHOTO);
				photo.setFilepath(cursor.getString(cursor.getColumnIndexOrThrow(MediaColumns.DATA)));
				
				VGAlbum currentAlbum = albumList.get(idx);
				currentAlbum.addAlbumItem(photo);
			}
		}
		
		cursor.close();
		
		return albumList;
	}
}
