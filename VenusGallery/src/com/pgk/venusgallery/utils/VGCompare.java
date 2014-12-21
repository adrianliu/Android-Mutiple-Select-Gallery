package com.pgk.venusgallery.utils;

import java.util.List;

import com.pgk.venusgallery.models.VGAlbum;

public class VGCompare {
	
	public static boolean doesListContainAlbum(List<VGAlbum> albumList, VGAlbum album) {
		
		for(VGAlbum a : albumList) {
			if (a.getIdx().contains(album.getIdx())) {
				return true;
			}
		}
		
		return false;
	}
	
	public static Integer getIndexWhereAlbum(List<VGAlbum> albumList, VGAlbum album) {
		
		for(int i = 0; i < albumList.size(); i++) {
			
			VGAlbum a = albumList.get(i);
			
			if (a.getIdx().contains(album.getIdx())) {
				return i;
			}
		}
		
		return -1;
	}
}
