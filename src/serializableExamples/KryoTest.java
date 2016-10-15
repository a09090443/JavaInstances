package serializableExamples;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.esotericsoftware.kryo.serializers.CompatibleFieldSerializer;
import com.esotericsoftware.minlog.Log;

public class KryoTest {
	public static void main(String[] args) throws IOException {
		Log.TRACE();
		
		Kryo kryo = new Kryo();
		// Set serializer that can handle added & removed fields (but can't
		// handle type change).
		kryo.setDefaultSerializer(CompatibleFieldSerializer.class);
//		File test = new File("/home/zipe/kryo.dat");
//		if(!test.exists()){
//			test.createNewFile();
//		}
		FileInputStream fileIn = new FileInputStream("/home/zipe/kryo.dat");

		if (false) {
			Output output = new Output(new FileOutputStream("/home/zipe/kryo.dat"));
			kryo.writeObject(output, new TestKryoData());
			output.close();
		} else {

			Input input = new Input(new FileInputStream("/home/zipe/kryo.dat"));
			TestKryoData dataWrapper = kryo.readObject(input, TestKryoData.class);
			input.close();

			System.out.println("ddd value should be 'bbb', got: " + dataWrapper.ddd);
		}
		fileIn.close();
		System.out.println("Done!");
	}

	static public class TestKryoData {
		public String aaa = "aaa";
		// public String bbb = "bbb";
		public String ccc = "ccc";
		// public String ddd = bbb;
		public String ddd = "ddd";
	}

}
