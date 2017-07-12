package com.example.firstjni;

import android.util.Log;

/**
 * 本地方法实现访问该类中的字段，方法
 * @author Reb
 *
 */
public class NativeInvokeJava extends Father{
	
	private static final String TAG = NativeInvokeJava.class.getSimpleName();
	
	/**
	 * 一个私有的int变量
	 */
	private int val = 1;
	
	/**
	 * 一个公有的静态String变量
	 */
	public static String name = "java Static Filed(init)";

	/**
	 * 这里引用了Log类，导致我们Javah 指令会报错
	 * 查看Readme解决办法
	 */
	public void print() {
		Log.i(TAG, toString());
	}
	
	/**
	 * 
	 * @param name
	 * @return
	 */
	public static int setName(String name) {
		NativeInvokeJava.name = name;
		Log.i(TAG, "setName:" + name);
		return 1;
	}
	
	@Override
	public String printToString() {
		String print = name;
		Log.i(TAG, print);
		return print;
	}
	
	/** 
	 * 这个方法被重写，为了调用父类方法和重写后的子类方法，jni为我们提供了不同的API，见native 方法实现
	 */
	@Override
	public String toString() {
		return "NativeInvokeJava [val=" + val + "],static Filed [name=" + name + "]";
	}
	
	static {
		System.loadLibrary("first_jni");
	}
	
	/**
	 * 这个方法的实现：
	 * 1.首先调用print方法，打印属性值
	 * 2.修改属性值
	 * 3.再次调用print方法，打印属性值
	 * 4.调用静态方法，修改name熟悉值
	 * 5.打印父类toString的返回值
	 * 6.打印本类toString的返回值
	 */
	public native void invokeJava(); 
}
