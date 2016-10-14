package annontation;

public class SomeObject {
	public static void main(String[] args) {
		doSomething();
	}
	@Debug(value = "unit test")
	public static void doSomething() {
		// ....
	}
}
