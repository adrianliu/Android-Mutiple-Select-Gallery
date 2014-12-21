package com.pgk.venusgallery.opts;

public class VGGallery {
	
	/**
	 * allows the user to select the selection option
	 */
	public static final String SELECT_OPTION = "SelectOptionKey";
	public enum SelectOption {
	    SINGLE {
	        public String toString() {
	            return "SingleSelect";
	        }
	    }, MULTIPLE {
	        public String toString() {
	            return "MultipleSelect";
	        }
	    }
	}
	
	/**
	 * the user may set the number of columns inside the grid
	 */
	public static final String NUMBER_OF_COLS = "NumberOfColsKey";
	
	/**
	 * the number of images the user can select
	 */
	public static final String NUMBER_OF_SELECTABLE_ITEMS = "NumberOfSelectableItemsKey";
	
	/**
	 * the return string 
	 */
	public static final String RETURN_DATA_STRING = "ReturnStringKey";
}
