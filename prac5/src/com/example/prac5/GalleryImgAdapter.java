package com.example.prac5;

import pritha.bhabra.R;
import pritha.bhabra.R.drawable;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

public class GalleryImgAdapter extends BaseAdapter{
	private Context context;
	public Integer[] Image={R.drawable.p1,
							R.drawable.p2,
							R.drawable.p3,
							R.drawable.p4,
							R.drawable.p5,
							R.drawable.p6,
							R.drawable.p7,
							R.drawable.p8};
	
	GalleryImgAdapter(Context context)
	{
		this.context=context;
	}
	
	public View getView(int index, View view, ViewGroup viewGroup){
		
		ImageView i=new ImageView(context);
		i.setImageResource(Image[index]);
		i.setLayoutParams(new Gallery.LayoutParams(200,200));
		i.setScaleType(ImageView.ScaleType.FIT_XY);

		return i;
			
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return Image.length;
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

}
