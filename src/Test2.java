import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

public class Test2 {
	private static CountDownLatch cdl;
	private static final int threadCount = 5;
	public static Integer count = 0;
	public static int rowCount = 100000;

	public static void main(String[] args) {
		double startTime, endTime, totTime; // 紀錄程式處理時間

		startTime = System.currentTimeMillis();

		Map<String, List<String>> testMap = new HashMap<String, List<String>>();
		testMap.put("1", new ArrayList<String>());
		testMap.put("2", new ArrayList<String>());
		testMap.put("3", new ArrayList<String>());
		testMap.put("4", new ArrayList<String>());
		testMap.put("5", new ArrayList<String>());

		for (int i = 1; i <= testMap.size(); i++) {
			List<String> testList = new ArrayList<String>();

			for (int x = 0; x < rowCount; x++) {

				testList.add(String.valueOf((int) (Math.random() * (999999999 - 100000000 + 1)) + 100000000)
						+ String.valueOf((int) (Math.random() * (999999999 - 100000000 + 1)) + 100000000)
						+ String.valueOf((int) (Math.random() * (999999999 - 100000000 + 1)) + 100000000)
						+ String.valueOf((int) (Math.random() * (999999999 - 100000000 + 1)) + 100000000)
						+ String.valueOf((int) (Math.random() * (999999999 - 100000000 + 1)) + 100000000)
						+ String.valueOf((int) (Math.random() * (999999999 - 100000000 + 1))
								+ 100000000
								+ (int) (Math.random() * (999999999 - 100000000 + 1))
								+ 100000000)
						+ String.valueOf((int) (Math.random() * (999999999 - 100000000 + 1)) + 100000000)
						+ String.valueOf((int) (Math.random() * (999999999 - 100000000 + 1)) + 100000000)
						+ String.valueOf((int) (Math.random() * (999999999 - 100000000 + 1)) + 100000000)
						+ String.valueOf((int) (Math.random() * (999999999 - 100000000 + 1)) + 100000000)
						+ String.valueOf((int) (Math.random() * (999999999 - 100000000 + 1))
								+ 100000000
								+ (int) (Math.random() * (999999999 - 100000000 + 1))
								+ 100000000)
						+ String.valueOf((int) (Math.random() * (999999999 - 100000000 + 1)) + 100000000)
						+ String.valueOf((int) (Math.random() * (999999999 - 100000000 + 1)) + 100000000)
						+ String.valueOf((int) (Math.random() * (999999999 - 100000000 + 1)) + 100000000)
						+ String.valueOf((int) (Math.random() * (999999999 - 100000000 + 1)) + 100000000)
						+ String.valueOf((int) (Math.random() * (999999999 - 100000000 + 1))
								+ 100000000
								+ (int) (Math.random() * (999999999 - 100000000 + 1))
								+ 100000000)
						+ String.valueOf((int) (Math.random() * (999999999 - 100000000 + 1)) + 100000000)
						+ String.valueOf((int) (Math.random() * (999999999 - 100000000 + 1)) + 100000000)
						+ String.valueOf((int) (Math.random() * (999999999 - 100000000 + 1)) + 100000000)
						+ String.valueOf((int) (Math.random() * (999999999 - 100000000 + 1)) + 100000000)
						+ String.valueOf((int) (Math.random() * (999999999 - 100000000 + 1)) + 100000000));
			}
			testMap.put(String.valueOf(i), testList);
		}

		cdl = new CountDownLatch(testMap.size());
		System.out.println("================" + cdl.getCount());
		new Thread(new Thread1(testMap.get("1"), 1)).start();
		new Thread(new Thread1(testMap.get("2"), 2)).start();
		new Thread(new Thread1(testMap.get("3"), 3)).start();
		new Thread(new Thread1(testMap.get("4"), 4)).start();
		new Thread(new Thread1(testMap.get("5"), 5)).start();
		
		try {
			cdl.await();
			System.out.println("Thread SUCCESS!!");
			System.out.println("COUNT " + count);
		}catch(Exception e){
			e.printStackTrace();
		}
		// ==================================================
		endTime = System.currentTimeMillis();
		// 取得程式結束的時間
		totTime = endTime - startTime;
		System.out.println("總時間 Time: " + totTime / 1000 + " sec");
		System.out.println("總時間 Time: " + totTime + " ms");
		System.out.println("總共處理 " + rowCount + "筆資料");
		// ==================================================
	}

	private static class Thread1 implements Runnable {
		private List<String> testList;
		private int id = 0;

		public Thread1(List<String> testList, int id) {
			this.testList = testList;
			this.id = id;
		}

		@Override
		public void run() {
			try {
				int i = 0;
				System.out.println("Thread_Number : "
						+ Thread.currentThread().getName() + ", Count:"
						+ testList.size());

				try {
					for (String test : testList) {

						i++;
					}
				} catch (Exception e) {
					throw e;
				}
				synchronized (count) { // 設計同步區塊程式
					// Thread.sleep(1);
					// System.out.println(count);
					count = count + i;
					// System.out.println(count);
				}
				System.out.println("Thread_Number : "
						+ Thread.currentThread().getName() + ", Count:" + i);
			} catch (Exception e) {

			} finally {
				System.out.println("test" + Thread.currentThread().getName());
				cdl.countDown();
				System.out.println("================" + cdl.getCount());

			}

		}
	}

}
