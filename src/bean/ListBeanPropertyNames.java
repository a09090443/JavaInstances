package bean;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.beanutils.PropertyUtils;

public class ListBeanPropertyNames {
	public static void main(String[] args) throws Exception {
		User user = new User();
		user.setId("22");
		user.setName("etest");
		user.setPhone("222222");
		user.setAge(12);
//		Map testMap = describeBean(user.getClass());
//		System.out.println("test");
		
		BeanInfo beanInfo = Introspector.getBeanInfo(user.getClass());
		PropertyDescriptor[] descriptors = beanInfo.getPropertyDescriptors();
		String entityName =  user.getClass().getSimpleName();
		StringBuilder conditionStr = new StringBuilder();

		for (int i = 0; i < descriptors.length; i++) {
			String propName = descriptors[i].getName();
			Class<?> propType = descriptors[i].getPropertyType();
			Object value = PropertyUtils.getProperty(user, propName);
//			System.out.println("Property with Name: " + propName + " and Type: " + propType.getSimpleName() + " and Value:" + value);
			if(!propName.equals("class") && null != value){
				if(propType.getSimpleName().equals("Integer") || propType.getSimpleName().equals("int")){
					conditionStr.append(propName + "=" + value + "");
				}else if (propType.getSimpleName().equals("String")){
					conditionStr.append(propName + "=" + "'" + value + "'");
				}
				conditionStr.append(",");
			}
		}
		conditionStr = conditionStr.deleteCharAt(conditionStr.length() - 1);
		conditionStr = conditionStr.append(" WHERE ");
		Map<String, Object> conditionMap = new HashMap<String, Object>();
		conditionMap.put("userId", 2);
		conditionMap.put("account", "account1");
		
		Integer mapSize = 0;
		for (Object key : conditionMap.keySet()) {
			mapSize ++;

			conditionStr.append(key + "=");
			if(!String.class.isInstance(conditionMap.get(key))){
				conditionStr.append(conditionMap.get(key));
			}else{
				conditionStr.append("'" + conditionMap.get(key) + "'");
			}
			if(conditionMap.size() != mapSize){
				conditionStr.append(" AND ");
			}
        }
		
		System.out.println("UPDATE " + user.getClass().getSimpleName() + " SET " +  conditionStr);
	}
	
	private static Map describeBean(Object o) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
		  Map<?,?> m=PropertyUtils.describe(o);
		  for (  Entry e : m.entrySet()) {
		    Object v=e.getValue();
		    if (v instanceof char[]) {
		      char[] chars=(char[])v;
		      e.setValue(new String(chars));
		    }
		  }
		  return m;
		}
}
