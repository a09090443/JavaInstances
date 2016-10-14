package com.samples;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

@XStreamAlias("INVOICE")
public class Ide005ExportHeader {
	@XStreamAsAttribute
	@XStreamAlias("Version")
	private String version = "1.0" ;

	@XStreamAlias("VoidInvoiceNumber")
	private String invoiceNumber;

	public String getInvoiceNumber() {
		return invoiceNumber;
	}

	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}
	
}
