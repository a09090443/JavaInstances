package compress;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.zip.CRC32;
import java.util.zip.CheckedInputStream;
import java.util.zip.CheckedOutputStream;
import java.util.zip.Deflater;
import java.util.zip.ZipException;
import java.util.zip.ZipInputStream;

import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;
import org.apache.tools.zip.ZipOutputStream;

/**
 * 
 * 提供對單個檔與目錄的壓縮，並支援是否需要創建壓縮原始目錄、中文路徑
 * 
 * @author jzj
 */
public class ZipCompress {

	private static boolean isCreateSrcDir = true;// 是否創建原始目錄

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		String src = "/home/zipe/tmp/33224/";// 指定壓縮源，可以是目錄或檔
		String decompressDir = "e:/tmp/decompress";// 解壓路徑
		String archive = "/home/zipe/tmp/測試.zip";// 壓縮包路徑
		String comment = "Java Zip 測試.";// 壓縮包注釋

		// ----壓縮檔或目錄
//		writeByApacheZipOutputStream(src, archive, comment);

		/*
		 * 讀壓縮檔，注釋掉，因為使用的是apache的壓縮類，所以使用java類庫中 解壓類時出錯，這裡不能運行
		 */
		// readByZipInputStream();
		// ----使用apace ZipFile讀取壓縮檔
		// readByApacheZipFile(archive, decompressDir);
		
	}

	public static void writeByApacheZipOutputStream(String src, String archive,
			String comment) throws FileNotFoundException, IOException {
		// ----壓縮檔：
		FileOutputStream f = new FileOutputStream(archive);
		// 使用指定校驗和創建輸出流
		CheckedOutputStream csum = new CheckedOutputStream(f, new CRC32());

		ZipOutputStream zos = new ZipOutputStream(csum);
		// 支援中文
		zos.setEncoding("UTF-8");
		BufferedOutputStream out = new BufferedOutputStream(zos);
		// 設置壓縮包注釋
		zos.setComment(comment);
		// 啟用壓縮
		zos.setMethod(ZipOutputStream.DEFLATED);
		// 壓縮層級為最強壓縮，但時間要花得多一點
		zos.setLevel(Deflater.BEST_COMPRESSION);

		File srcFile = new File(src);

		if (!srcFile.exists()
				|| (srcFile.isDirectory() && srcFile.list().length == 0)) {
			throw new FileNotFoundException(
					"File must exist and  ZIP file must have at least one entry.");
		}
		// 獲取壓縮源所在父目錄
		src = src.replaceAll("\\\\", "/");
		String prefixDir = null;
		if (srcFile.isFile()) {
			prefixDir = src.substring(0, src.lastIndexOf("/") + 1);
		} else {
			prefixDir = (src.replaceAll("/$", "") + "/");
		}

		// 如果不是根目錄
		if (prefixDir.indexOf("/") != (prefixDir.length() - 1)
				&& isCreateSrcDir) {
			prefixDir = prefixDir.replaceAll("[^/]+/$", "");
		}

		// 開始壓縮
		writeRecursive(zos, out, srcFile, prefixDir);

		out.close();
		// 注：校驗和要在流關閉後才準備，一定要放在流被關閉後使用
		System.out.println("Checksum: " + csum.getChecksum().getValue());
		BufferedInputStream bi;
	}

	/**
	 * 使用 org.apache.tools.zip.ZipFile 解壓文件，它與 java 類庫中的 java.util.zip.ZipFile
	 * 使用方式是一新的，只不過多了設置編碼方式的 介面。
	 * 
	 * 注，apache 沒有提供 ZipInputStream 類，所以只能使用它提供的ZipFile 來讀取壓縮檔。
	 * 
	 * @param archive
	 *            壓縮包路徑
	 * @param decompressDir
	 *            解壓路徑
	 * @throws IOException
	 * @throws FileNotFoundException
	 * @throws ZipException
	 */
	public static void readByApacheZipFile(String archive, String decompressDir)
			throws IOException, FileNotFoundException, ZipException {
		BufferedInputStream bi;

		ZipFile zf = new ZipFile(archive, "UTF-8");// 支援中文

		Enumeration e = zf.getEntries();
		while (e.hasMoreElements()) {
			ZipEntry ze2 = (ZipEntry) e.nextElement();
			String entryName = ze2.getName();
			String path = decompressDir + "/" + entryName;
			if (ze2.isDirectory()) {
				System.out.println("正在創建解壓目錄 - " + entryName);
				File decompressDirFile = new File(path);
				if (!decompressDirFile.exists()) {
					decompressDirFile.mkdirs();
				}
			} else {
				System.out.println("正在創建解壓文件 - " + entryName);
				String fileDir = path.substring(0, path.lastIndexOf("/"));
				File fileDirFile = new File(fileDir);
				if (!fileDirFile.exists()) {
					fileDirFile.mkdirs();
				}
				BufferedOutputStream bos = new BufferedOutputStream(
						new FileOutputStream(decompressDir + "/" + entryName));

				bi = new BufferedInputStream(zf.getInputStream(ze2));
				byte[] readContent = new byte[1024];
				int readCount = bi.read(readContent);
				while (readCount != -1) {
					bos.write(readContent, 0, readCount);
					readCount = bi.read(readContent);
				}
				bos.close();
			}
		}
		zf.close();
	}

	/**
	 * 使用 java api 中的 ZipInputStream 類解壓檔，但如果壓縮時採用了
	 * org.apache.tools.zip.ZipOutputStream時，而不是 java 類庫中的
	 * java.util.zip.ZipOutputStream時，該方法不能使用，原因就是編碼方 式不一致導致，運行時會拋如下異常：
	 * java.lang.IllegalArgumentException at
	 * java.util.zip.ZipInputStream.getUTF8String(ZipInputStream.java:290)
	 * 
	 * 當然，如果壓縮包使用的是java類庫的java.util.zip.ZipOutputStream 壓縮而成是不會有問題的，但它不支持中文
	 * 
	 * @param archive
	 *            壓縮包路徑
	 * @param decompressDir
	 *            解壓路徑
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static void readByZipInputStream(String archive, String decompressDir)
			throws FileNotFoundException, IOException {
		BufferedInputStream bi;
		// ----解壓檔(ZIP檔的解壓縮實質上就是從輸入流中讀取資料):
		System.out.println("開始讀壓縮檔");

		FileInputStream fi = new FileInputStream(archive);
		CheckedInputStream csumi = new CheckedInputStream(fi, new CRC32());
		ZipInputStream in2 = new ZipInputStream(csumi);
		bi = new BufferedInputStream(in2);
		java.util.zip.ZipEntry ze;// 壓縮檔條目
		// 遍歷壓縮包中的檔條目
		while ((ze = in2.getNextEntry()) != null) {
			String entryName = ze.getName();
			if (ze.isDirectory()) {
				System.out.println("正在創建解壓目錄 - " + entryName);
				File decompressDirFile = new File(decompressDir + "/"
						+ entryName);
				if (!decompressDirFile.exists()) {
					decompressDirFile.mkdirs();
				}
			} else {
				System.out.println("正在創建解壓文件 - " + entryName);
				BufferedOutputStream bos = new BufferedOutputStream(
						new FileOutputStream(decompressDir + "/" + entryName));
				byte[] buffer = new byte[1024];
				int readCount = bi.read(buffer);

				while (readCount != -1) {
					bos.write(buffer, 0, readCount);
					readCount = bi.read(buffer);
				}
				bos.close();
			}
		}
		bi.close();
		System.out.println("Checksum: " + csumi.getChecksum().getValue());
	}

	/**
	 * 遞迴壓縮
	 * 
	 * 使用 org.apache.tools.zip.ZipOutputStream 類進行壓縮，它的好處就是支援中文路徑， 而Java類庫中的
	 * java.util.zip.ZipOutputStream 壓縮中文檔案名時壓縮包會出現亂碼。 使用 apache 中的這個類與 java
	 * 類庫中的用法是一新的，只是能設置編碼方式了。
	 * 
	 * @param zos
	 * @param bo
	 * @param srcFile
	 * @param prefixDir
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	private static void writeRecursive(ZipOutputStream zos,
			BufferedOutputStream bo, File srcFile, String prefixDir)
			throws IOException, FileNotFoundException {
		ZipEntry zipEntry;

		String filePath = srcFile.getAbsolutePath().replaceAll("\\\\", "/")
				.replaceAll("//", "/");
		if (srcFile.isDirectory()) {
			filePath = filePath.replaceAll("/$", "") + "/";
		}
		String entryName = filePath.replace(prefixDir, "").replaceAll("/$", "");
		if (srcFile.isDirectory()) {
			if (!"".equals(entryName)) {
				System.out.println("正在創建目錄 - " + srcFile.getAbsolutePath()
						+ "  entryName=" + entryName);

				// 如果是目錄，則需要在寫目錄後面加上 /
				zipEntry = new ZipEntry(entryName + "/");
				zos.putNextEntry(zipEntry);
			}

			File srcFiles[] = srcFile.listFiles();
			for (int i = 0; i < srcFiles.length; i++) {
				writeRecursive(zos, bo, srcFiles[i], prefixDir);
			}
		} else {
			System.out.println("正在寫文件 - " + srcFile.getAbsolutePath()
					+ "  entryName=" + entryName);
			BufferedInputStream bi = new BufferedInputStream(
					new FileInputStream(srcFile));

			// 開始寫入新的ZIP檔條目並將流定位到條目資料的開始處
			zipEntry = new ZipEntry(entryName);
			zos.putNextEntry(zipEntry);
			byte[] buffer = new byte[1024];
			int readCount = bi.read(buffer);

			while (readCount != -1) {
				bo.write(buffer, 0, readCount);
				readCount = bi.read(buffer);
			}
			// 注，在使用緩衝流寫壓縮檔時，一個條件完後一定要刷新一把，不
			// 然可能有的內容就會存入到後面條目中去了
			bo.flush();
			// 文件讀完後關閉
			bi.close();
		}
	}
}
