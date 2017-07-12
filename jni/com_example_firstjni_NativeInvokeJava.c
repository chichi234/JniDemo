#include <utils.h>
#include <jni.h>


char* jstringTostring(JNIEnv*, jstring);

/*
 * Class:     com_example_firstjni_NativeInvokeJava
 * Method:    invokeJava
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_com_example_firstjni_NativeInvokeJava_invokeJava
  (JNIEnv* env, jobject jobject){
	// jobject是调用这个方法的对象，此例中表示NativeInvokeJava类的对象
	// 获取NativeInvokeJava这个类的方法，字段
	jclass NativeInvokeJava_clazz = (*env)->GetObjectClass(env, jobject);
	jclass father_clazz = (*env)->FindClass(env,"com/example/firstjni/Father");
	jfieldID fieldID_val = (*env)->GetFieldID(env, NativeInvokeJava_clazz, "val", "I");
	jmethodID methodId_print = (*env)->GetMethodID(env, NativeInvokeJava_clazz, "print", "()V");
	jmethodID methodId_toString = (*env)->GetMethodID(env, father_clazz, "printToString", "()Ljava/lang/String;");
	jmethodID methodId_setName = (*env)->GetStaticMethodID(env, NativeInvokeJava_clazz, "setName", "(Ljava/lang/String;)I");
	// 首先调用print方法，打印属性值
	(*env)->CallVoidMethod(env, jobject, methodId_print);
	// 修改属性值
	(*env)->SetIntField(env, jobject, fieldID_val, 10);
	// 再次调用print方法，打印属性值
	(*env)->CallVoidMethod(env, jobject, methodId_print);
	// 调用静态方法，修改name熟悉值
	jint result = (*env)->CallStaticIntMethod(env, NativeInvokeJava_clazz, methodId_setName, (*env)->NewStringUTF(env, "jni setName"));
	LOGI("result = %d", result);
    // 打印父类toString的返回值(返回的jstring 需要转换成char*)
    jstring toString1 = (*env)->CallNonvirtualObjectMethod(env, jobject, NativeInvokeJava_clazz, methodId_toString);
	LOGI("toString1 = %s", jstringTostring(env, toString1));
    // 打印本类toString的返回值
	jstring toString2 = (*env)->CallObjectMethod(env, jobject, methodId_toString);
	LOGI("toString2 = %s", jstringTostring(env, toString2));
}

char* jstringTostring(JNIEnv* env, jstring jstr) {
      char* rtn = NULL;
      jclass clsstring = (*env)->FindClass(env, "java/lang/String");
      jstring strencode = (*env)->NewStringUTF(env, "utf-8");
      jmethodID mid = (*env)->GetMethodID(env, clsstring, "getBytes", "(Ljava/lang/String;)[B");
      jbyteArray barr = (jbyteArray) (*env)->CallObjectMethod(env, jstr, mid, strencode);
      jsize alen = (*env)->GetArrayLength(env, barr);
      jbyte* ba = (*env)->GetByteArrayElements(env, barr, JNI_FALSE);
      if (alen > 0) {
	    rtn = (char*) malloc(alen + 1);
	    memcpy(rtn, ba, alen);
	    rtn[alen] = 0;
      }
      (*env)->ReleaseByteArrayElements(env, barr, ba, 0);
      return rtn;
}
