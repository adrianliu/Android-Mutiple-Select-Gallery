package com.pgk.venusgallery.adapter;

import java.util.List;

import com.pgk.venusgallery.R;
import com.pgk.venusgallery.models.VGAlbum;
import com.pgk.venusgallery.models.VGPhoto;
import com.pgk.venusgallery.utils.VGSquareImageView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class AlbumAdapter extends BaseAdapter {
	
	Context mContext;
	List<VGAlbum> mData;
	LayoutInflater mlayoutInflator;

	public AlbumAdapter(Context context, List<VGAlbum> data)  {
		super();
		mContext = context;
		mData = data;
		mlayoutInflator = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	
	@SuppressLint("InflateParams")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		if (convertView == null) {
			convertView = mlayoutInflator.inflate(R.layout.item_album, null);
		} 
		
		VGAlbum 			album 		= mData.get(position);
		VGPhoto 		preview 	= null;
		TextView 			name;
		TextView 			count;
		VGSquareImageView 	previewImage;
		ProgressBar 		progressBar;
		
		if (album.getItemList().size() > 0) {
			preview = album.getItemList().get( (album.getItemList().size() - 1) );
		}
		
		name 			= (TextView)convertView.findViewById(R.id.name);
		count 			= (TextView)convertView.findViewById(R.id.count);
		previewImage 	= (VGSquareImageView)convertView.findViewById(R.id.previewImage);
		progressBar		= (ProgressBar)convertView.findViewById(R.id.progressBar);
		
		name.setText(album.getName());
		count.setText("("+ album.getItemList().size() +")");
		previewImage.setVisibility(View.GONE);
		progressBar.setVisibility(View.VISIBLE);
		
		if (preview != null) {
			new ImageLoadTask(previewImage, preview.getFilepath(), progressBar, album).execute();
		}
		
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
    	private String path;
    	private ProgressBar progressBar;
    	private VGAlbum album;
    	
    	public ImageLoadTask(ImageView imv, String path, ProgressBar progressBar, VGAlbum album) {
             this.image = imv;
             this.path = path;
             this.progressBar = progressBar;
             this.album = album;
        }
    	
        protected Bitmap doInBackground(String... param) {
        	
        	if (album.getPreviewImage() == null) {
        		
            	BitmapFactory.Options options = new BitmapFactory.Options();
            	options.inSampleSize = 12;

            	Bitmap bitmap = BitmapFactory.decodeFile(path, options);
                return bitmap;        		
        	}
        	
        	return album.getPreviewImage();
        }
 
        protected void onPostExecute(Bitmap ret) {
        	
        	if (album.getPreviewImage() == null) {
        		album.setPreviewImage(ret);
        	}
        	
        	image.setImageBitmap(ret);
        	image.setVisibility(View.VISIBLE);
        	
        	progressBar.setVisibility(View.GONE);
        }
    }
}