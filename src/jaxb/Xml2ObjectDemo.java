package jaxb;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

//Unmarshaller  
public class Xml2ObjectDemo {
	public static void main(String[] args) {
		try {
			File file = new File("/home/zipe/tmp/file1.xml");
			JAXBContext jaxbContext = JAXBContext.newInstance(Head.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			Head head = (Head) jaxbUnmarshaller.unmarshal(file);
			System.out.println(head);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}

}