package com.samples;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import com.thoughtworks.xstream.*;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class ConfigReader {

	String datasourcename = null;
	String ipaddress = null;
	String logfilename = null;
	String appender = null;
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Datasource Name : "+datasourcename+
			" \nIP Address : "+ipaddress+
			" \nLogfilename : "+logfilename+
			" \nAppender : "+appender;
	}
	/**
	 * @param args
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException {
		XStream xs = new XStream(new DomDriver());
		
		FileInputStream fis = new FileInputStream("/home/zipe/tmp/sample.xml");
		xs.aliasField("datasource-name", ConfigReader.class, "datasourcename");
		xs.alias("config", ConfigReader.class);
		ConfigReader r = (ConfigReader)xs.fromXML(fis);
		System.out.println(r.toString());
	}

}
