package com.ov3rk1ll.pokedroiddex.db;

import java.io.IOException;

import com.ov3rk1ll.pokedroiddex.R;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.widget.SimpleCursorAdapter;
import android.widget.FilterQueryProvider;
import android.widget.ImageView;

public class IconCursorAdapter extends SimpleCursorAdapter {

	Sprite sprite = null;
	
	public IconCursorAdapter(Context context, int layout, Cursor c, String[] from, int[] to, int flags) {
		super(context, layout, c, from, to, flags);
		sprite = new Sprite(context);
		setFilterQueryProvider(new FilterQueryProvider() {
			public Cursor runQuery(CharSequence constraint) {
				Cursor cur = DataSource.getInstance().getFilteredCursor(constraint);
				return cur;
			}
		});
	}
	
	@Override
	public void setViewImage(ImageView v, String id) {
		v.setImageBitmap(sprite.getDrawable(Integer.valueOf(id)));
	}
	
	
	public static class Sprite{
		private int cols = 0;
		private int size = 96;
		
		Bitmap bitmap;
		Bitmap fallback;
		
		public Sprite(Context context){
			try {
				fallback = BitmapFactory.decodeResource(context.getResources(), R.drawable.missingno);
				BitmapFactory.Options options = new BitmapFactory.Options();
				options.inPurgeable = true;
				bitmap = BitmapFactory.decodeStream(context.getAssets().open("sprite.png"), null, options);
				cols = bitmap.getWidth() / size;
			} catch (IOException e) {
				e.printStackTrace();
			} catch (OutOfMemoryError e) {
				e.printStackTrace();
				cols = 1;
			}
		}
		
		public Bitmap getDrawable(int index){
			index = index - 1;
			int x = index % cols;
			int y = (int) Math.floor((index-x) / cols);
			Bitmap b = null;
			try{
				b = Bitmap.createBitmap(bitmap, x * size, y * size, size, size);
			}catch(Exception e){
				return fallback;
			}
			return b;
		}
	}




}
