package com.pgk.venusgallery.adapter;

import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.pgk.venusgallery.R;
import com.pgk.venusgallery.models.VGPhoto;
import com.pgk.venusgallery.utils.VGSquareImageView;

public class PhotoAdapter extends BaseAdapter {
	
	Context mContext;
	List<VGPhoto> mData;
	LayoutInflater mlayoutInflator;
	
	public PhotoAdapter(Context context, List<VGPhoto> data)  {
		super();
		mContext = context;
		mData = data;
		mlayoutInflator = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	
	@SuppressLint("InflateParams")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		if (convertView == null) {
			convertView = mlayoutInflator.inflate(R.layout.item_photo, null);
		} 
		
		VGPhoto 			photo 		= mData.get(position);
		VGSquareImageView 	previewImage;
		ProgressBar 		progressBar;
		CheckBox			selectCheckbox;
		
		previewImage 	= (VGSquareImageView)convertView.findViewById(R.id.previewImage);
		progressBar		= (ProgressBar)convertView.findViewById(R.id.progressBar);
		selectCheckbox 	= (CheckBox)convertView.findViewById(R.id.selectCheckbox);

		selectCheckbox.setVisibility(View.GONE);
		previewImage.setVisibility(View.GONE);

		new ImageLoadTask(previewImage, selectCheckbox, photo, progressBar).execute();
		
		selectCheckbox.setChecked(photo.isSelected());
		
		return convertView;
	}

	@Override
	public int getCount() {
		return mData.size();
	}

	@Override
	public Object getItem(int arg0) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}
	
    private class ImageLoadTask extends AsyncTask<String, String, Bitmap> {
 
    	private ImageView image;
    	private ProgressBar progressBar;
    	private VGPhoto photo;
    	private CheckBox cb;
    	
    	public ImageLoadTask(ImageView imv, CheckBox cb, VGPhoto photo, ProgressBar progressBar) {
             this.image = imv;
             this.cb = cb;
             this.progressBar = progressBar;
             this.photo = photo;
        }
    	
        protected Bitmap doInBackground(String... param) {
        	
        	if (photo.getImageBitmap() == null) {
        		
            	BitmapFactory.Options options = new BitmapFactory.Options();
            	options.inSampleSize = 12;

            	Bitmap bitmap = BitmapFactory.decodeFile(photo.getFilepath(), options);
                return bitmap;        		
        	}
        	
        	return photo.getImageBitmap();
        }
 
        protected void onPostExecute(Bitmap ret) {
        	
        	if (photo.getImageBitmap() == null) {
        		photo.setImageBitmap(ret);
        	}
        	
        	image.setImageBitmap(ret);
        	image.setVisibility(View.VISIBLE);
        	cb.setVisibility(View.VISIBLE);
        	progressBar.setVisibility(View.GONE);
        }
    }
}