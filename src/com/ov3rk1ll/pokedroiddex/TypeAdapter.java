package com.ov3rk1ll.pokedroiddex;

import com.ov3rk1ll.pokedroiddex.DamageCalc.Type;
import com.ov3rk1ll.pokedroiddex.DamageTypeAdapter.CustomDrawable;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

@SuppressLint("DefaultLocale")
public class TypeAdapter extends ArrayAdapter<TypeAdapter.Entry> {	
	
	
	
	public TypeAdapter(Context context, int resource, int textViewResourceId, Entry[] objects) {
		super(context, resource, textViewResourceId, objects);
	}

	public TypeAdapter(Context context, int resource, int textViewResourceId) {
		super(context, resource, textViewResourceId);
	}
	
	@Override
	public View getDropDownView(int position, View convertView, ViewGroup parent) {
		View view = null;
		if(getItem(position).isEnabled()){
			view = super.getView(position, null, parent);	
			applyStyle(position, view);
		}else{
			TextView tv = new TextView(getContext());
			tv.setHeight(0);
            tv.setVisibility(View.GONE);
			view = tv;
		}	
		parent.setVerticalScrollBarEnabled(false);
		return view;	
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = super.getView(position, convertView, parent);		
		applyStyle(position, view);
		return view;
	}

	@SuppressWarnings("deprecation")
	private void applyStyle(int position, View view){		
		((TextView)view.findViewById(android.R.id.text1)).setBackgroundDrawable(new CustomDrawable(getItem(position).getColor(), true));
		((TextView)view.findViewById(android.R.id.text1)).setTypeface(MainActivity.pokemon_pixel_font);
		((TextView)view.findViewById(android.R.id.text1)).setTextColor(Color.WHITE);
		((TextView)view.findViewById(android.R.id.text1)).setTextSize(12);
	}
	
	public int find(String type){
		if(TextUtils.isEmpty(type)) return 0;
		return find(Type.valueOf(type.toUpperCase()));
	}
	
	public int find(Type type){
		for (int i = 0; i < getCount(); i++) {
			if(getItem(i).getType() == type)
				return i;
		}
		return 0;
	}
	
	public void setAllEnabled(boolean enabled){
		for (int i = 0; i < getCount(); i++) {
			getItem(i).setEnabled(enabled);
		}
	}
	
	public static class Entry{
		private Type type;
		private String name;
		private int color;
		private boolean enabled = true;
				
		public Entry(String name, Type type, int color) {
			super();
			this.name = name;
			this.type = type;
			this.color = color;
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
		public Type getType() {
			return type;
		}

		public void setType(Type type) {
			this.type = type;
		}

		public boolean isEnabled() {
			return enabled;
		}

		public void setEnabled(boolean enabled) {
			this.enabled = enabled;
		}

		@Override
		public String toString() {
			return getName();
		}
		
		
	}

}
