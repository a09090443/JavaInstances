import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.commons.lang.StringUtils;


/**
 * create_date : 2009-6-16
 */
public class TestBean extends ParentTest{


	public String uid;
	public String uname;
	public String sex;
	

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}
	public TestBean(String uid, String uname, String sex){
		this.uid = uid;
		this.uname = uname;
		this.sex = sex;
		
	}
	public String check() {
		Method[] methods = this.getClass().getMethods();
		for (int i = 0; i < methods.length; i++) {
			Method method = methods[i];
			if (method.getName().startsWith("get")) {
				try {
					if (StringUtils.isBlank((String) method.invoke(this))) {
						return method.getName().substring(3);
					}
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}
	public TestBean(){
		
	}
//
//	public static void main(String[] args) {
//		TestBean tBean = new TestBean();
//		tBean.setUid("1234");
//		System.out.println(tBean.check());
//	}
}