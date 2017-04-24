package jaxb;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import serializableExamples.Employee;

public class Test {
	public static void main(String[] args) {
		JaxbUtil util = new JaxbUtil(Head.class);
		try {
			Head head = util.fromFileConvertObject(new File("/home/zipe/tmp/file1.xml"));
			System.out.println("test");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// public static Head jaxbXMLToObject() {
	// try {
	// JAXBContext context = JAXBContext.newInstance(Head.class);
	// Unmarshaller un = context.createUnmarshaller();
	// Head emp = (Head) un.unmarshal(new File("/home/zipe/tmp/file1.xml"));
	// return emp;
	// } catch (JAXBException e) {
	// e.printStackTrace();
	// }
	// return null;
	// }
}
