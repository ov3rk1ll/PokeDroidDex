package com.ov3rk1ll.pokedroiddex;

import java.util.ArrayList;
import java.util.List;

public class DamageCalc {
	public static float NONE = 0f;
	public static float LOW = 0.5f;
	public static float NORMAL = 1f;
	public static float STRONG = 2f;
		
	public static float[][] damageTable = {
	{NORMAL,	NORMAL,	NORMAL,	NORMAL,	NORMAL,	LOW,	NORMAL,	NONE,	LOW,	NORMAL,	NORMAL,	NORMAL,	NORMAL,	NORMAL,	NORMAL,	NORMAL,	NORMAL,	NORMAL},	
	{STRONG,	NORMAL,	LOW,	LOW,	NORMAL,	STRONG,	LOW,	NONE,	STRONG,	NORMAL,	NORMAL,	NORMAL,	NORMAL,	LOW,	STRONG,	NORMAL,	STRONG,	LOW},	
	{NORMAL,	STRONG,	NORMAL,	NORMAL,	NORMAL,	LOW,	STRONG,	NORMAL,	LOW,	NORMAL,	NORMAL,	STRONG,	LOW,	NORMAL,	NORMAL,	NORMAL,	NORMAL,	NORMAL},	
	{NORMAL,	NORMAL,	NORMAL,	LOW,	LOW,	LOW,	NORMAL,	LOW,	NONE,	NORMAL,	NORMAL,	STRONG,	NORMAL,	NORMAL,	NORMAL,	NORMAL,	NORMAL,	STRONG},	
	{NORMAL,	NORMAL,	NONE,	STRONG,	NORMAL,	STRONG,	LOW,	NORMAL,	STRONG,	STRONG,	NORMAL,	LOW,	STRONG,	NORMAL,	NORMAL,	NORMAL,	NORMAL,	NORMAL},	
	{NORMAL,	LOW,	STRONG,	NORMAL,	LOW,	NORMAL,	STRONG,	NORMAL,	LOW,	STRONG,	NORMAL,	NORMAL,	NORMAL,	NORMAL,	STRONG,	NORMAL,	NORMAL,	NORMAL},	
	{NORMAL,	LOW,	LOW,	LOW,	NORMAL,	NORMAL,	NORMAL,	LOW,	LOW,	LOW,	NORMAL,	STRONG,	NORMAL,	STRONG,	NORMAL,	NORMAL,	STRONG,	LOW},	
	{NONE,		NORMAL,	NORMAL,	NORMAL,	NORMAL,	NORMAL,	NORMAL,	STRONG,	LOW,	NORMAL,	NORMAL,	NORMAL,	NORMAL,	STRONG,	NORMAL,	NORMAL,	LOW,	NORMAL},	
	{NORMAL,	NORMAL,	NORMAL,	NORMAL,	NORMAL,	STRONG,	NORMAL,	NORMAL,	LOW,	LOW,	LOW,	NORMAL,	LOW,	NORMAL,	STRONG,	NORMAL,	NORMAL,	STRONG},	
	{NORMAL,	NORMAL,	NORMAL,	NORMAL,	NORMAL,	LOW,	STRONG,	NORMAL,	STRONG,	LOW,	LOW,	STRONG,	NORMAL,	NORMAL,	STRONG,	LOW,	NORMAL,	NORMAL},	
	{NORMAL,	NORMAL,	NORMAL,	NORMAL,	STRONG,	STRONG,	NORMAL,	NORMAL,	NORMAL,	STRONG,	LOW,	LOW,	NORMAL,	NORMAL,	NORMAL,	LOW,	NORMAL,	NORMAL},	
	{NORMAL,	NORMAL,	LOW,	LOW,	STRONG,	STRONG,	LOW,	NORMAL,	LOW,	LOW,	STRONG,	LOW,	NORMAL,	NORMAL,	NORMAL,	LOW,	NORMAL,	NORMAL},	
	{NORMAL,	NORMAL,	STRONG,	NORMAL,	NONE,	NORMAL,	NORMAL,	NORMAL,	NORMAL,	NORMAL,	STRONG,	LOW,	LOW,	NORMAL,	NORMAL,	LOW,	NORMAL,	NORMAL},	
	{NORMAL,	STRONG,	NORMAL,	STRONG,	NORMAL,	NORMAL,	NORMAL,	NORMAL,	LOW,	NORMAL,	NORMAL,	NORMAL,	NORMAL,	LOW,	NORMAL,	NORMAL,	NONE,	NORMAL},	
	{NORMAL,	NORMAL,	STRONG,	NORMAL,	STRONG,	NORMAL,	NORMAL,	NORMAL,	LOW,	LOW,	LOW,	STRONG,	NORMAL,	NORMAL,	LOW,	STRONG,	NORMAL,	NORMAL},	
	{NORMAL,	NORMAL,	NORMAL,	NORMAL,	NORMAL,	NORMAL,	NORMAL,	NORMAL,	LOW,	NORMAL,	NORMAL,	NORMAL,	NORMAL,	NORMAL,	NORMAL,	STRONG,	NORMAL,	NONE},	
	{NORMAL,	LOW,	NORMAL,	NORMAL,	NORMAL,	NORMAL,	NORMAL,	STRONG,	LOW,	NORMAL,	NORMAL,	NORMAL,	NORMAL,	STRONG,	NORMAL,	NORMAL,	LOW,	LOW},	
	{NORMAL,	STRONG,	NORMAL,	LOW,	NORMAL,	NORMAL,	NORMAL,	NORMAL,	LOW,	LOW,	NORMAL,	NORMAL,	NORMAL,	NORMAL,	NORMAL,	STRONG,	STRONG,	NORMAL}
};
	
	
	public static float getDamageFactor(Type attacker, Type defender){
		return damageTable[attacker.ordinal()][defender.ordinal()];
	}
	
	public static List<DamageTypeAdapter.Entry> getFactorList(Type defender1, Type defender2){
		List<DamageTypeAdapter.Entry> objects = new ArrayList<DamageTypeAdapter.Entry>();
		Type[] types = Type.values();
		for (Type type : types) {
			
			float f1 = getDamageFactor(type, defender1);
			float f2 = defender2 == null ? 1f : getDamageFactor(type, defender2);
			objects.add(new DamageTypeAdapter.Entry(type, f1 * f2));
		}
		return objects;
	}
	
	public static int[] getFactorIds(float value){
		if(value >= 2f)
			return new int[]{R.string.factor_strong, R.color.factor_strong};
		if(value >= 1f)
			return new int[]{R.string.factor_normal, R.color.factor_normal};
		if(value > 0.1f)
			return new int[]{R.string.factor_low, R.color.factor_low};
		
		return new int[]{R.string.factor_none, R.color.factor_none};
	}
	
	public enum Type {
		NORMAL,
		FIGHTING,
		FLYING,
		POISON,
		GROUND,
		ROCK,
		BUG,
		GHOST,
		STEEL,
		FIRE,
		WATER,
		GRASS,
		ELECTRIC,
		PSYCHIC,
		ICE,
		DRAGON,
		DARK,
		FAIRY
	}	
}
