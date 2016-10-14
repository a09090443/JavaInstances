package jaxb;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

//Marshaller  
public class Object2XmlDemo {
	public static void main(String[] args) {

		Customer customer = new Customer();
		customer.setId(100);
		customer.setName("suo");
		customer.setAge(29);
		
		Clienter clienter = new Clienter();
		clienter.setId(12);
		clienter.setName("gary");
		clienter.setAge(33);
		
		Book book = new Book();
		book.setId("1");
		book.setName("哈里波特");
		book.setPrice(100);

		Book book2 = new Book();
		book2.setId("2");
		book2.setName("苹果");
		book2.setPrice(50);

		Ebook ebook = new Ebook();
		ebook.setId("66");
		ebook.setName("AAAA");
		ebook.setPrice(100);
		
		Ebook ebook2 = new Ebook();
		ebook.setId("4444");
		ebook.setName("BBBBBB");
		ebook.setPrice(100);
		
		List<Book> bookList = new ArrayList<Book>();
		bookList.add(book);
		bookList.add(book2);
		List<Ebook> ebookList = new ArrayList<Ebook>();
		ebookList.add(ebook);
		ebookList.add(ebook2);
		
		Head head = new Head();
		customer.setBook(bookList);
		clienter.setEbook(ebookList);
		head.setCustomer(customer);
		head.setClienter(clienter);
		try {
			File file = new File("/home/zipe/tmp/file1.xml");
			JAXBContext jaxbContext = JAXBContext.newInstance(Head.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			jaxbMarshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);
			
			// output pretty printed
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jaxbMarshaller.marshal(head, file);
			jaxbMarshaller.marshal(head, System.out);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}
}