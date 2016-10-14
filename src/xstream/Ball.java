package xstream;

import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

@XStreamAlias("Main")
public class Ball {
    //颜色  
	@XStreamAlias("Main_color")
    private String color;  
    private String test;
    
    @XStreamImplicit
	private List<Employee> employee;

    public String getTest() {
		return test;
	}

	public void setTest(String test) {
		this.test = test;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	Ball(String color, String test, List<Employee> employee){  
        this.color = color;
        this.test = test;
        this.employee = employee;
    }

	public List<Employee> getEmployee() {
		return employee;
	}

	public void setEmployee(List<Employee> employee) {
		this.employee = employee;
	}
	
}
