package xstream;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.XStream;

public class Test {
public static void main(String[] args) throws FileNotFoundException{
	 //初始化cat  
    List<Ball> balls = new ArrayList<Ball>();  
    List<Employee> employee = new ArrayList<Employee>();
    employee.add(new Employee("123", "456", "2314"));
    employee.add(new Employee("222", "53535", "12314"));
    employee.add(new Employee("333", "45734", "23346346214"));
    balls.add(new Ball("red", "test1", employee));  
    Cat cat = new Cat("測試",1,balls);  
    //初始化结束  
    //为了方便查找 将文件制定到D盘cat.xml中  
    FileOutputStream fout = new FileOutputStream("/home/zipe/tmp/test.xml");  
    XStream xs = new XStream();
	xs.processAnnotations(cat.getClass());

    xs.toXML(cat,fout);
}
}
