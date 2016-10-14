package xstream;

import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

@XStreamAlias("Detial")
public class Employee {
	
	@XStreamAsAttribute
	@XStreamAlias("Version")
	private String version = "1.0" ;
	
	@XStreamAlias("VoidInvoiceNumber")
	private String name;
	private String designation;
	private String department;
	
	@XStreamImplicit
	public List<Student> student;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}

	public List<Student> getStudent() {
		return student;
	}
	public void setStudent(List<Student> student) {
		this.student = student;
	}
	@Override
	public String toString() {
		return "Name : "+this.name+
		"\nDesignation : "+this.designation+
		"\nDepartment : "+this.department;
	}
	public Employee(String name, String designation, String department){
		this.name = name;
		this.designation = designation;
		this.department = department;
	}
}