import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.apache.poi.util.SystemOutLogger;

public class Test {
	public static void main(String a[]) throws Exception {
		
		String pattern1 = "%1$-20s";
		System.out.printf("%1$-6s","ABC");
		File test = new File("/home/zipe/test.txt");
		if(!test.exists()){
			test.createNewFile();
		}
		String ttt = null;
		System.out.println("" + ttt==null ? "test":"aaaa");
		List<String> testList = new ArrayList<String>();
		testList.add("0");
		testList.add("4");
		testList.add("2");
		testList.add("1");
		testList.add("6");
		testList.add("777");
		testList.add("446");
		testList.add("3463");
		testList.add("747");
		testList.add("5352");

		Map<String, List<String>> testMap = new HashMap<String, List<String>>();
		testMap.put("1", testList.subList(0, 3));
		testMap.put("2", testList.subList(3, 4));
		testMap.put("3", testList.subList(4, 8));
		
		List<String> testList2 = testMap.get("3");
		System.out.println(testList2.size());
		
		List<String> testList3 = testMap.get("2");
		System.out.println(testList3.size());

		System.out.println(testMap.size());
		
//		File testFile = new File("/home/zipe/tmp/EIVCCDATA/IUO/INV_POS_XML_BAK/20140626/EIVPOS_89589328_20140625_14.zip");
//		System.out.println(new Timestamp(testFile.lastModified()));
//		String test = "0000112345";
//		System.out.println(test.subSequence(4, 10));
//		List<CachePersonalVO> cachePersonalVOList = new ArrayList<CachePersonalVO>();
//
//		cachePersonalVOList.add(new CachePersonalVO("05",
//				"sdfhvsjfhvakjfhvakhevfkavfvoeasv"));
//		cachePersonalVOList.add(new CachePersonalVO("03",
//				"sdfhvsjfhvakjfhvakhevfkavfvoeasv"));
//		cachePersonalVOList.add(new CachePersonalVO("06",
//				"sdfhvsjfhvakjfhvakhevfkavfvoeasv"));
//		cachePersonalVOList.add(new CachePersonalVO("43",
//				"sdfhvsjfhvakjfhvakhevfkavfvoeasv"));
//		cachePersonalVOList.add(new CachePersonalVO("22",
//				"sdfhvsjfhvakjfhvakhevfkavfvoeasv"));
//		cachePersonalVOList.add(new CachePersonalVO("01",
//				"sdfhvsjfhvakjfhvakhevfkavfvoeasv"));
//
//		Collections.sort(cachePersonalVOList,
//				new Comparator<CachePersonalVO>() {
//					public int compare(CachePersonalVO o1, CachePersonalVO o2) {
//						return o1.getLineNo().compareTo(o2.getLineNo());
//					}
//				});
//		
//		for (CachePersonalVO o:cachePersonalVOList) {
//            System.out.println(o.getLineNo());
//        }
	}
}
