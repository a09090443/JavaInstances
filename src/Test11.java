import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import org.apache.commons.collections.ListUtils;

public class Test11 {
	public static void main(String[] args) {
		int x = -123;
		int result = 0;
		
		while (x != 0) {
			int tail = x % 10;
			int newResult = result * 10 + tail;
			if ((newResult - tail) / 10 != result) {
				break;
			}
			result = newResult;
			x = x / 10;
		}

		System.out.println(result);
	}
}
