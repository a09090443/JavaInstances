package instance;

import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;

public class InvokeMethodSample {
	public static void main(String[] args) {
		try {
			// 實體化class
			Class c = Class.forName(args[0]);
			Object classObj = c.newInstance();
			// 產生method並呼叫執行(有參數)
			Class[] params = { String.class, int.class };
			Method setInfo = c.getMethod("setInfo", params);
			Object[] paramObjs = { args[1], new Integer(args[2]) };
			setInfo.invoke(classObj, paramObjs);
			// 產生method並呼叫執行(無參數)
			Method showInfo = c.getMethod("showInfo", null);
			showInfo.invoke(classObj, null);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		}
	}
}
