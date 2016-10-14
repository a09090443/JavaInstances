package com.samples;

import java.util.ArrayList;
import java.util.List;

public class ListTest {
	public static void main(String[] args) {
	    List<Person> listA = new ArrayList<Person>();
	 
	    for (int i = 0; i < 6; i++) {
	        Person p = new Person();
	        p.setName(String.valueOf(i));
	        listA.add(p);
	    }
	 
	    listA = new ArrayList<Person>(subList(listA));
	 
	    for (int i = 0; i < listA.size(); i++) {
	        System.out.println(listA.get(i).getName());
	    }
	}
	 
	public static List<Person> subList(List<Person> L) {
	 
	    return L.subList(0, 4);
	}
}
