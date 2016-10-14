import java.io.File;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.commons.lang3.StringUtils;

public class Test3 {
	public static void main(String[] args) {
		int[] nums = { 1, 0, 5, 6 };
		Test3 test = new Test3();
		int[] testResult = test.result(nums);
		System.out.println(testResult);
	}

	public int[] result(int[] nums) {

		int[] res = new int[nums.length];

		for (int i = 0; i < nums.length; i++) {
			if (nums[i] != 0) {
				for (int x = 0; x < res.length; x++) {
					if (res[x] == 0) {
						res[x] = nums[i];
						break;
					}
				}

			}
		}
		return res;
	}
}
