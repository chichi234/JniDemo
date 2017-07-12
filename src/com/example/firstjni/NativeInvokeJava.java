package com.example.firstjni;

import android.util.Log;

/**
 * ���ط���ʵ�ַ��ʸ����е��ֶΣ�����
 * @author Reb
 *
 */
public class NativeInvokeJava extends Father{
	
	private static final String TAG = NativeInvokeJava.class.getSimpleName();
	
	/**
	 * һ��˽�е�int����
	 */
	private int val = 1;
	
	/**
	 * һ�����еľ�̬String����
	 */
	public static String name = "java Static Filed(init)";

	/**
	 * ����������Log�࣬��������Javah ָ��ᱨ��
	 * �鿴Readme����취
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
	 * �����������д��Ϊ�˵��ø��෽������д������෽����jniΪ�����ṩ�˲�ͬ��API����native ����ʵ��
	 */
	@Override
	public String toString() {
		return "NativeInvokeJava [val=" + val + "],static Filed [name=" + name + "]";
	}
	
	static {
		System.loadLibrary("first_jni");
	}
	
	/**
	 * ���������ʵ�֣�
	 * 1.���ȵ���print��������ӡ����ֵ
	 * 2.�޸�����ֵ
	 * 3.�ٴε���print��������ӡ����ֵ
	 * 4.���þ�̬�������޸�name��Ϥֵ
	 * 5.��ӡ����toString�ķ���ֵ
	 * 6.��ӡ����toString�ķ���ֵ
	 */
	public native void invokeJava(); 
}
