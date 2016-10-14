package interfaceTest;

public class TestInterfaceRun implements InterfaceTest, InterfaceTest2 {
	static int a = 0;
	int b = 0;

	public TestInterfaceRun() {
		this.test1();
	}

	public static void main(String[] args) {
		TestInterfaceRun run = new TestInterfaceRun();

		System.out.println(a);
	}

	@Override
	public void test1() {
		System.out.println("test1");
		a = 5;
		this.test2();
	}

	@Override
	public void test2() {
		b = 7;
		System.out.println("test2");
	}

	@Override
	public void test3() {
		// TODO Auto-generated method stub

	}

	@Override
	public void test4() {
		// TODO Auto-generated method stub

	}
}
