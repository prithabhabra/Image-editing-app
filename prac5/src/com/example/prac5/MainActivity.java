package com.example.prac5;


import pritha.bhabra.R;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Toast;

public class MainActivity extends Activity {

	ImageView imgV;
	Bitmap originalbitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        Gallery gallery=(Gallery)findViewById(R.id.gallery1);
        imgV=(ImageView)findViewById(R.id.img1);
        imgV.setOnCreateContextMenuListener(this);
      
        gallery.setSpacing(3);
        
        final GalleryImgAdapter galleryAdap=new GalleryImgAdapter(this);
        gallery.setAdapter(galleryAdap);
        gallery.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				imgV.setImageResource(galleryAdap.Image[arg2]);
				
			}
        });
    
    }
    
    public void onCreateContextMenu(ContextMenu menu, View view, ContextMenuInfo menuInfo){
    	super.onCreateContextMenu(menu, view, menuInfo);
    	CreateMenu(menu);
    }

    private void CreateMenu(ContextMenu menu) {
		// TODO Auto-generated method stub
    	MenuItem mnu0=menu.add(0,0,0,"Rotation");
    	MenuItem mnu1=menu.add(0,1,1,"Brightness");
    	MenuItem mnu2=menu.add(0,2,2,"Contrast");
	}
    public boolean onContextItemSelected(MenuItem item){
    	
    	Dialog yourDialog = new Dialog(this);
    	originalbitmap = ((BitmapDrawable)imgV.getDrawable()).getBitmap();
    	switch(item.getItemId())
    	{
    	case 0:Matrix matrix = new Matrix();
    	 	   matrix.postRotate(90);
    		   Bitmap rotated = Bitmap.createBitmap(originalbitmap, 0, 0, originalbitmap.getWidth(), originalbitmap.getHeight(),matrix, true);
    		   imgV.setImageBitmap(rotated);
    		   return true;
    		   
    	case 1:LayoutInflater inflater = (LayoutInflater)this.getSystemService(LAYOUT_INFLATER_SERVICE);
    		   View layout = inflater.inflate(R.layout.dialog, null);
    	       yourDialog.setContentView(layout);
    	       yourDialog.setTitle("Brightness");
    	       yourDialog.show();
    	       SeekBar seekbar=(SeekBar)yourDialog.findViewById(R.id.seekBar1);
    	       seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){
    	    	   @Override
    	    	   public void onStopTrackingTouch(SeekBar arg0) {
    	    		   // TODO Auto-generated method stub
    	    	   }
    	    	   @Override
    	           public void onStartTrackingTouch(SeekBar arg0) {
    	    		   // TODO Auto-generated method stub
    	    	   }
    	    	   @Override
    	    	   public void onProgressChanged(SeekBar arg0, int progress, boolean arg2) {
    	    		   // TODO Auto-generated method stub
    	    		   int brightness=progress;
    	    		   Bitmap bitmap=doBrightness(originalbitmap,brightness);
    	    		   imgV.setImageBitmap(bitmap);
    	    	   }
    	       });
    		   return true;
			   
    	case 2:LayoutInflater inflater1 = (LayoutInflater)this.getSystemService(LAYOUT_INFLATER_SERVICE);
		       View layout1 = inflater1.inflate(R.layout.dialog, null);
	           yourDialog.setContentView(layout1);
	           yourDialog.setTitle("Contrast");
	           yourDialog.show();
	           SeekBar seekbar1=(SeekBar)yourDialog.findViewById(R.id.seekBar1);
	           seekbar1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){
	        	   @Override
	        	   public void onStopTrackingTouch(SeekBar arg0) {
	        		   // TODO Auto-generated method stub
	        	   }
	        	   @Override
	        	   public void onStartTrackingTouch(SeekBar arg0) {
	        		   // TODO Auto-generated method stub
	        	   }
	        	   @Override
	        	   public void onProgressChanged(SeekBar arg0, int progress, boolean arg2) {
	        		   // TODO Auto-generated method stub
	        		   int contrast=progress;
	        		   Bitmap originalbitmap = ((BitmapDrawable)imgV.getDrawable()).getBitmap();
	        		   Bitmap bitmap=doContrast(originalbitmap,contrast);
	        		   imgV.setImageBitmap(bitmap);
	        	   }
	           });
		       return true;
    	}
		return false;
    }
    
    public static Bitmap doBrightness(Bitmap src, int value) {
        // image size
        int width = src.getWidth();
        int height = src.getHeight();
        // create output bitmap
        Bitmap bmOut = Bitmap.createBitmap(width, height, src.getConfig());
        // color information
        int A, R, G, B;
        int pixel;
     
        // scan through all pixels
        for(int x = 0; x < width; ++x) {
            for(int y = 0; y < height; ++y) {
                // get pixel color
                pixel = src.getPixel(x, y);
                A = Color.alpha(pixel);
                R = Color.red(pixel);
                G = Color.green(pixel);
                B = Color.blue(pixel);
     
                // increase/decrease each channel
                R += value;
                if(R > 255) { R = 255; }
                else if(R < 0) { R = 0; }
     
                G += value;
                if(G > 255) { G = 255; }
                else if(G < 0) { G = 0; }
     
                B += value;
                if(B > 255) { B = 255; }
                else if(B < 0) { B = 0; }
     
                // apply new pixel color to output bitmap
                bmOut.setPixel(x, y, Color.argb(A, R, G, B));
            }
        }
     
        // return final image
        return bmOut;
    }
    private Bitmap doContrast(Bitmap src, double value)
    {
        // image size
        int width = src.getWidth();
        int height = src.getHeight();
        // create output bitmap

        // create a mutable empty bitmap
        Bitmap bmOut = Bitmap.createBitmap(width, height, src.getConfig());

        // create a canvas so that we can draw the bmOut Bitmap from source bitmap
        Canvas c = new Canvas();
        c.setBitmap(bmOut);

        // draw bitmap to bmOut from bitmap so we can modify it
        c.drawBitmap(src, 0, 0, new Paint(Color.BLACK));


        // color information
        int A, R, G, B;
        int pixel;
        // get contrast value
        double contrast = Math.pow((100 + value) / 100, 2);

        // scan through all pixels
        for(int x = 0; x < width; ++x) {
            for(int y = 0; y < height; ++y) {
                // get pixel color
                pixel = src.getPixel(x, y);
                A = Color.alpha(pixel);
                // apply filter contrast for every channel R, G, B
                R = Color.red(pixel);
                R = (int)(((((R / 255.0) - 0.5) * contrast) + 0.5) * 255.0);
                if(R < 0) { R = 0; }
                else if(R > 255) { R = 255; }

                G = Color.green(pixel);
                G = (int)(((((G / 255.0) - 0.5) * contrast) + 0.5) * 255.0);
                if(G < 0) { G = 0; }
                else if(G > 255) { G = 255; }

                B = Color.blue(pixel);
                B = (int)(((((B / 255.0) - 0.5) * contrast) + 0.5) * 255.0);
                if(B < 0) { B = 0; }
                else if(B > 255) { B = 255; }

                // set new pixel color to output bitmap
                bmOut.setPixel(x, y, Color.argb(A, R, G, B));
            }
        }
        return bmOut;
    }
    
    
}
