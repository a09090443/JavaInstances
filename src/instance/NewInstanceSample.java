package instance;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class NewInstanceSample {
	public static void main(String[] args) {
		try {
			Class c = Class.forName(args[0]);
			// 指定參數型態
			Class[] params = new Class[2];
			params[0] = String.class;
			params[1] = int.class;
			Constructor constructor = c.getConstructor(params);
			// 指定參數內容
			Object[] paramObjs = new Object[2];
			paramObjs[0] = args[1];
			paramObjs[1] = new Integer(args[2]);
			// 實體化class(透過建構子)
			Object classObj = constructor.newInstance(paramObjs);
			System.out.println(classObj);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}
}
