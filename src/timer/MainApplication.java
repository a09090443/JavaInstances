package timer;

import java.util.Calendar;
import java.util.Timer;

public class MainApplication {
	public static void main(String[] args) {
		Timer timer = new Timer();
		Calendar date = Calendar.getInstance();
		date.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		date.set(Calendar.HOUR, 0);
		date.set(Calendar.MINUTE, 0);
		date.set(Calendar.SECOND, 0);
		date.set(Calendar.MILLISECOND, 0);
		// 每週日午夜零時執行一次
		timer.schedule(new Test(), date.getTime(), 1000 * 60 * 60 * 24 * 7);
	}
}
