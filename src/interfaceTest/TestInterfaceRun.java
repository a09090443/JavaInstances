package interfaceTest;

public class TestInterfaceRun extends AbstractImInterfaceTest {

	public static void main(String[] args) {
		InterfaceTest test1 = (AbstractImInterfaceTest) new TestInterfaceRun();
		test1.test1();
	}
	public void test1() {
		System.out.println("test2222");
	}
	
	@Override
	public void test2() {

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
