package report;

public class DataBean {
	private String name;
	private String occupation;
	private String place;
	private String country;
	private String test1;
	private Integer test1Val;

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getPlace() {
		return place;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCountry() {
		return country;
	}

	public String getTest1() {
		return test1;
	}

	public void setTest1(String test1) {
		this.test1 = "test1";
	}

	public Integer getTest1Val() {
		return test1Val;
	}

	public void setTest1Val(Integer test1Val) {
		this.test1Val = 50;
	}
	
}
