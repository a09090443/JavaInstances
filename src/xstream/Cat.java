package xstream;

import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

@XStreamAlias("head")
public class Cat {
	// 名字
	@XStreamAlias("Name")
	private String name;
	// 大小
	@XStreamAlias("AAA")
	private Integer age;
	// 玩具球 球具有颜色属性
	@XStreamImplicit
	private List<Ball> balls;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public List<Ball> getBalls() {
		return balls;
	}

	public void setBalls(List<Ball> balls) {
		this.balls = balls;
	}

	Cat(String name, Integer age, List<Ball> balls) {
		this.name = name;
		this.age = age;
		this.balls = balls;
	}
}
