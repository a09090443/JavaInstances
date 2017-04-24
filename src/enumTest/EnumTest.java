package enumTest;

public enum EnumTest {
	USER("USER"),
	DBA("DBA"),
	ADMIN("ADMIN");
	String userProfileType;

	private EnumTest(String userProfileType) {
		this.userProfileType = userProfileType;
	}

	public String getUserProfileType() {
		return userProfileType;
	}
	
}
