package compress;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class GZIPcompress {
	public static void main(String[] args) throws IOException {
		// 做準備壓縮一個字元檔，注，這裡的字元檔要是UTF-8編碼方式的
		BufferedReader in = new BufferedReader(new InputStreamReader(
				new FileInputStream("e:/tmp/source.txt"), "UTF-8"));
		// 使用GZIPOutputStream包裝OutputStream流，使其具體壓縮特性，最後會生成test.txt.gz壓縮包
		// 並且裡面有一個名為test.txt的文件
		BufferedOutputStream out = new BufferedOutputStream(
				new GZIPOutputStream(new FileOutputStream("test.txt.gz")));
		System.out.println("開始寫壓縮檔...");
		int c;
		while ((c = in.read()) != -1) {

			/*
			 * 注，這裡是壓縮一個字元檔，前面是以字元流來讀的，不能直接存入c，因為c已是Unicode
			 * 碼，這樣會丟掉資訊的（當然本身編碼格式就不對），所以這裡要以UTF-8來解後再存入。
			 */
			out.write(String.valueOf((char) c).getBytes("UTF-8"));
		}
		in.close();
		out.close();
		System.out.println("開始讀壓縮檔...");
		// 使用GZIPInputStream包裝InputStream流，使其具有解壓特性
		BufferedReader in2 = new BufferedReader(new InputStreamReader(
				new GZIPInputStream(new FileInputStream("test.txt.gz")), "UTF-8"));
		String s;
		// 讀取壓縮檔裡的內容
		while ((s = in2.readLine()) != null) {
			System.out.println(s);
		}
		in2.close();
	}
}
