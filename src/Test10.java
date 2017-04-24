import java.util.*;
import java.text.*;

public class Test10 {
	public static void main(String[] args) {
		System.out.println(getWeekOfYear()); // 輸出 14 (今天是 2014-04-02)
		System.out.println(getWeekOfYear("2012-01-01")); // 輸出 1
		System.out.println(getWeekOfYear("2012-12-28")); // 輸出 52
		System.out.println(getWeekOfYear("2012-07-01")); // 輸出 27
		System.out.println(getWeekOfYear("2014-01-01")); // 輸出 1
		System.out.println(getWeekOfYear("2014-12-28")); // 輸出 1
		System.out.println(getWeekOfYear("2014-07-01")); // 輸出 27
	}

	public static String getDate() { // 傳回今日日期字串
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(new Date());
	}

	public static int[] parseDate(String date) { // 將日期字串轉成陣列
		String[] d = date.split("-");
		int[] dt = new int[3];
		dt[0] = Integer.parseInt(d[0]);
		dt[1] = Integer.parseInt(d[1]);
		dt[2] = Integer.parseInt(d[2]);
		return dt;
	}

	public static int getWeekOfYear() {
		return getWeekOfYear(getDate()); // 傳入今日日期字串
	}

	public static int getWeekOfYear(String date) {
		int[] d = parseDate(date); // 將日期字串轉成陣列
		Calendar c = Calendar.getInstance();
		c.set(d[0], d[1] - 1, d[2]); // 設定日曆物件之日期 (注意月份要減 1)
		return c.get(Calendar.WEEK_OF_YEAR);
	}
}
