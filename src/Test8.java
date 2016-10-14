public class Test8 {
	public static void main(String[] args) {
		int[] nums = { 1,5,6,5,8 };
		Test8 test = new Test8();

		for (int i : test.twoSum(nums, 10)) {
			System.out.println(i);
		}
	}

	public int[] twoSum(int[] nums, int target) {
		for (int i = 0; i < nums.length; i++) {

			for (int x = 1; x < nums.length; x++) {
				if (nums[i] + nums[x] == target && i < x) {
					return new int[] { i, x };
				}
			}
		}
		return nums;

	}
}
