package com.ov3rk1ll.pokedroiddex;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.ov3rk1ll.pokedroiddex.DamageCalc.Type;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.graphics.drawable.shapes.Shape;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

@SuppressLint("DefaultLocale")
public class DamageTypeAdapter extends ArrayAdapter<DamageTypeAdapter.Entry> {	
	

	public DamageTypeAdapter(Context context, int resource,	int textViewResourceId, List<Entry> objects) {
		super(context, resource, textViewResourceId, objects);
		List<Entry> namedObjects = new ArrayList<DamageTypeAdapter.Entry>();
		for(int i = 0; i < objects.size(); i++){
			Entry e = getItem(i);
			int resId = context.getResources().getIdentifier("type_" + e.getType().name().toLowerCase(), "string", context.getPackageName());
			e.setName(context.getString(resId));
			resId = context.getResources().getIdentifier("type_" + e.getType().name().toLowerCase(), "color", context.getPackageName());
			e.setColor(context.getResources().getColor(resId));
			
			int factor[] = DamageCalc.getFactorIds(e.getFactor());			
			resId = context.getResources().getIdentifier("factor_" + e.getFactor(), "string", context.getPackageName());
			e.setFactorText(context.getString(factor[0]));
			e.setFactorColor(context.getResources().getColor(factor[1]));
			namedObjects.add(e);
		}
		clear();
		for(int i = 0; i < namedObjects.size(); i++){
			add(namedObjects.get(i));
		}
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = super.getView(position, convertView, parent);
		
		Entry item = getItem(position);
		
		((TextView)view.findViewById(android.R.id.text1)).setBackgroundDrawable(new CustomDrawable(item.getColor(), true));
		((TextView)view.findViewById(android.R.id.text1)).setTextColor(Color.WHITE);
		((TextView)view.findViewById(android.R.id.text1)).setTypeface(MainActivity.pokemon_pixel_font);
		((TextView)view.findViewById(android.R.id.text1)).setText(item.getName());
		
		((TextView)view.findViewById(android.R.id.text2)).setBackgroundDrawable(new CustomDrawable(item.getFactorColor(), true));
		((TextView)view.findViewById(android.R.id.text2)).setTextColor(Color.WHITE);
		((TextView)view.findViewById(android.R.id.text2)).setTypeface(MainActivity.pokemon_pixel_font);
		((TextView)view.findViewById(android.R.id.text2)).setText(item.getFactorText() + " " + item.getFactor() + "x");
		return view;
	}
	
	public void sort() {
		super.sort(new Comparator<DamageTypeAdapter.Entry>() {                
	        @Override
	        public int compare(Entry item1, Entry item2) {
	            return item2.compareTo(item1);
	        }
	    });
	}
	
	@Override
	public boolean isEnabled(int position) {
		return false;
	}
	
	
	public static class Entry{
		private Type type;
		private String name;
		private int color;
		private float factor;
		private String factorText;
		private int factorColor;
				
		public Entry(Type type, float factor) {
			super();
			this.type = type;
			this.factor = factor;
		}

		public int compareTo(Entry item) {
			int cmp = Double.compare(getFactor(), item.getFactor());
			if(cmp == 0){
				return item.getName().compareTo(getName());
			}else{
				return cmp;
			}
		}

		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public int getColor() {
			return color;
		}
		public void setColor(int color) {
			this.color = color;
		}
		public float getFactor() {
			return factor;
		}
		public void setFactor(float factor) {
			this.factor = factor;
		}
		public Type getType() {
			return type;
		}

		public void setType(Type type) {
			this.type = type;
		}

		public String getFactorText() {
			return factorText;
		}

		public void setFactorText(String factorText) {
			this.factorText = factorText;
		}

		public int getFactorColor() {
			return factorColor;
		}

		public void setFactorColor(int factorColor) {
			this.factorColor = factorColor;
		}

		@Override
		public String toString() {
			return getName() + "   " + getFactor() + "x";
		}
	}
		
	public static class CustomDrawable extends ShapeDrawable{
		private static float[] r = new float[]{8,8,8,8,8,8,8,8};
	    Paint fillpaint;
		private int color;

	    public CustomDrawable(int color, boolean rounded){
			super(new RoundRectShape(rounded ? r : null, null, null));
			this.color = color;
		}
	    
		@Override
		protected void onDraw(Shape shape, Canvas canvas, Paint paint) {
		    fillpaint = this.getPaint();
		    fillpaint.setColor(this.color);	
		    shape.draw(canvas, fillpaint);	
		}
	}

}
