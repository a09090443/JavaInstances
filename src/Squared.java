
public class Squared {
	static long times;
	long tmpNum;

	public static void main(String[] args) {
		byte test2 = -128;
		Squared test = new Squared();
		test.square(2147483648L);
		System.out.println(test2);
	}

	public boolean square(long num) {
		if (num % 2 == 0) {
			tmpNum = num / 2;
			times++;
			this.square(tmpNum);
		}
		return true;
	}
}
