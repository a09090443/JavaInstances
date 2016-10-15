package serializableExamples;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Registration;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

public class TestKryo {

	private static void test001() {

		Kryo kryo = new Kryo();
		// kryo.setReferences(true);
		// kryo.setRegistrationRequired(true);
		// kryo.setInstantiatorStrategy(new StdInstantiatorStrategy());
		// 注册类
		Registration registration = kryo.register(Student.class);
		long time = System.currentTimeMillis();
		for (int i = 0; i < 2; i++) {
			// 序列化
			Output output = null;
			// ByteArrayOutputStream outStream = new ByteArrayOutputStream();
			// output = new Output( outStream , 4096);
			output = new Output(1, 4096);
			List<Student> students = new ArrayList<Student>();

			students.add(new Student(11, "name_wjh", "北京11"));
			students.add(new Student(12, "name_wjh", "北京"));
			students.add(new Student(13, "name_wjh", "上海"));

			Student myStudent = new Student(10, "xx", "xxx", students);

			kryo.writeObject(output, myStudent);
			byte[] bb = output.toBytes();
			output.flush();

			String str = Base64.getEncoder().encodeToString(bb);
			System.out.println(str);

			// 反序列化
			Input input = new Input(bb);
			Student s = (Student) kryo.readObject(input, registration.getType());
			System.out.println(s);
			input.close();
			// 反序列化为其他类
			input = new Input(bb);
			Teacher teacher = (Teacher) kryo.readObject(input, Teacher.class);
			System.out.println(teacher);
			input.close();
		}
		time = System.currentTimeMillis() - time;
		System.out.println("time:" + time);
	}

	public static void main(String[] args) throws Exception {

		test001();
	}

}