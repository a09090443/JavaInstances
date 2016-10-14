package instance;

public class MyDog {
	private String name;
	private int age;

	public MyDog() {
		name = "小白";
		age = 1;
	}

	public MyDog(String name, int age) {
		this.name = name;
		this.age = age;
	}

	public void setInfo(String name, int age) {
		this.name = name;
		this.age = age;
	}

	public void showInfo() {
		System.out.println("小狗名字是: " + name);
		System.out.println("小狗年齡是: " + age + " 歲");
	}

	public String toString() {
		return "小狗名字是: " + name + " 小狗年齡是: " + age + " 歲";
	}
}
