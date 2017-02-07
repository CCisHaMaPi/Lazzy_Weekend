package com.example.tt.lazzy_weekend.utils;

import android.content.Context;

public class SharedPreferencesTools {

	private static final String FILE_NAME = "shared";
	
	public static String getString(Context context,String key){
		
		String text = context.getSharedPreferences(FILE_NAME,Context.MODE_PRIVATE)
				.getString(key, "");
		return text;
	}
	
	public static int getInt(Context context,String key){
		
		int i = context.getSharedPreferences(FILE_NAME,Context.MODE_PRIVATE)
				.getInt(key, 0);
		return i;
	}

	public static long getLong(Context context,String key){

		long i = context.getSharedPreferences(FILE_NAME,Context.MODE_PRIVATE)
				.getLong(key, 0);
		return i;
	}
	
	public static float getFloat(Context context,String key){
		
		float i = context.getSharedPreferences(FILE_NAME,Context.MODE_PRIVATE)
				.getFloat(key, 0);
		return i;
	}
	
	public static Boolean getBoolean(Context context,String key){
		
		boolean flag = context.getSharedPreferences(FILE_NAME,Context.MODE_PRIVATE)
				.getBoolean(key, false);
		return flag;
	}
	
	public static void putString(Context context,String key,String value){
		context.getSharedPreferences(FILE_NAME,Context.MODE_PRIVATE)
			.edit()
			.putString(key, value)
			.commit();
	}
	
	public static void putInt(Context context,String key,int value){
		context.getSharedPreferences(FILE_NAME,Context.MODE_PRIVATE)
			.edit()
			.putInt(key, value)
			.commit();
	}

	public static void putLong(Context context,String key,long value){
		context.getSharedPreferences(FILE_NAME,Context.MODE_PRIVATE)
				.edit()
				.putLong(key, value)
				.commit();
	}
	
	public static void putFloat(Context context,String key,float value){
		context.getSharedPreferences(FILE_NAME,Context.MODE_PRIVATE)
			.edit()
			.putFloat(key, value)
			.commit();
	}
	
	public static void putBoolean(Context context,String key,boolean value){
		context.getSharedPreferences(FILE_NAME,Context.MODE_PRIVATE)
			.edit()
			.putBoolean(key, value)
			.commit();
	}

	public static void putDouble(Context context,String key,double value){
		context.getSharedPreferences(FILE_NAME,Context.MODE_PRIVATE)
				.edit()
				.putLong(key, Double.doubleToRawLongBits(value));
	}

	public static double getDouble(Context context,String key){
		long value = context.getSharedPreferences(FILE_NAME,Context.MODE_PRIVATE)
				.getLong(key, 0);
		return Double.longBitsToDouble(value);
	}
}	
