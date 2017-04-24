package extentedsTest;

public class ExtendsTest extends Test1 {
	public static void main(String[] args) {
		ExtendsTest test = new ExtendsTest();
		test.test1();
		Test1 test2 = new ExtendsTest();
		test2.test1();
	}

	public void test2() {
		System.out.println("testesteste2");
	}
}
