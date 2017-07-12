package com.example.firstjni;

import android.util.Log;

public class Father {

	private static final String TAG = Father.class.getSimpleName(); 
	
	public String printToString(){
		String print = "father printString";
		Log.i(TAG, print);
		return print;
	}
	
}
