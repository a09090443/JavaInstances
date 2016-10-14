import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class YouXiangHuoQu {
	public static void main(String[] args) throws Exception {
		getMail();
	}

	public static void getMail() throws Exception {
		URL url = new URL("http://tw.yahoo.com");// 網頁位址
		URLConnection conn = url.openConnection();
		BufferedReader bufin = new BufferedReader(new InputStreamReader(
				conn.getInputStream()));
		String line = null;
		String mailreg = "\\w+@\\w+(\\.\\w+)+";// 正則匹配
		Pattern p = Pattern.compile(mailreg);
		while ((line = bufin.readLine()) != null) {
			Matcher m = p.matcher(line);
			while (m.find()) {
				System.out.print(m.group() + "\r\n");
			}
		}
	}
}
