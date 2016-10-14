package com.samples;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import com.thoughtworks.xstream.*;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class Writer {

	public static void main(String[] args) {
//		Employee e = new Employee();
//		
//		//Set the properties using the setter methods
//		//Note: This can also be done with a constructor.
//		//Since we want to show that XStream can serialize
//		//even without a constructor, this approach is used.
//		e.setName("Jack");
//		e.setDesignation("Manager");
//		e.setDepartment("Finance");
//		
//		//Serialize the object
//		XStream xs = new XStream(new DomDriver());
//		
//		//Write to a file in the file system
//		try {
//			FileOutputStream fs = new FileOutputStream("/home/zipe/tmp/employeedata.xml");
//			//xs.alias("test", e.getClass());
//			xs.processAnnotations(Employee.class);
//
//			xs.toXML(e, fs);
//		} catch (FileNotFoundException e1) {
//			e1.printStackTrace();
//		}
	}
}