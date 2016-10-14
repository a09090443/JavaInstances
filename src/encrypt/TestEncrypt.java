package encrypt;

public class TestEncrypt {
	public static void main(String[] args) {
		try {
			String test = "jIAn";
			DES des = new DES("22331");// 自定義密鑰
			System.out.println("加密前的字符：" + test);
			System.out.println("加密後的字符：" + des.encrypt(test));
			System.out.println("解密後的字符：" + des.decrypt(des.encrypt(test)));
		} catch (Exception e) {

			e.printStackTrace();
		}

	}
}
