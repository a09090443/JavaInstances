package gpg;

import java.io.BufferedInputStream;
import java.io.IOException;

public class KeySearch {
	public static void main(String[] args) {
		String cmd = "gpg --encrypt -a --yes --batch --quiet --no-verbose --output --encrypt --always-trust -o /home/zipe/EIVPOS_20130614171833.xml.asc --recipient 969B243E /home/zipe/EIVPOS_20130614171833.xml";
		try {
			Process ps = Runtime.getRuntime().exec(
					new String[] { "/bin/bash", "-c", cmd });
			BufferedInputStream bis = new BufferedInputStream(
					ps.getInputStream());
			BufferedInputStream bes = new BufferedInputStream(
					ps.getErrorStream());

			int i = ps.waitFor();

			if (i == 0) {
				System.out.println("運行加密命令成功!");
			} else {
				System.out.println("運行加密命令失敗 返回code為" + i);
			}
			System.out.println("return code" + i);

			System.out.println("InputStream:");
			int ch = -1;
			while ((ch = bis.read()) != -1) {
				System.out.print((char) ch);
			}
			System.out.println("ErrorStream:");
			while ((ch = bes.read()) != -1) {
				System.out.print((char) ch);
			}

		} catch (IOException e) {
			System.out.println("加密命令運行失敗!");
		} catch (InterruptedException e) {
			System.out.println("加密命令運行失敗!");
		}
	}
}
