package bean;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;

public class BeanControll {
	public static void main(String[] args){
		
		User user = new User();
		user.setId("1");
		user.setName("test");
		user.setPhone("123456");
		
		Info info = new Info();
		info.setAddress("aaaaaaaaaaaaaaaa");
		info.setBirthday("1234321");
		TestVo testVo = new TestVo();
		try {
			BeanUtils.copyProperties(testVo, user);
			BeanUtils.copyProperties(testVo, info);
			System.out.println(testVo);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
