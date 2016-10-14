package xstream;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("STUDENT")
public class Student {
	@XStreamAlias("VoidInvoiceNumber")
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
