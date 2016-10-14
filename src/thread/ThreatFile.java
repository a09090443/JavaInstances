package thread;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class ThreatFile {
	private static CountDownLatch cdl;
	private static final int threadCount = 5;
	public static Integer count = 0;
	public static int rowCount = 100000;
	public static void main(String[] args) {
		double startTime, endTime, totTime; // 紀錄程式處理時間

		startTime = System.currentTimeMillis();

		List<String> testList = new ArrayList<String>();
		for (int i = 0; i < rowCount; i++) {
			testList.add(String.valueOf((int) (Math.random() * (999999999 - 100000000 + 1)) + 100000000) + String.valueOf((int) (Math.random() * (999999999 - 100000000 + 1)) + 100000000) + String.valueOf((int) (Math.random() * (999999999 - 100000000 + 1)) + 100000000) + String.valueOf((int) (Math.random() * (999999999 - 100000000 + 1)) + 100000000) + String.valueOf((int) (Math.random() * (999999999 - 100000000 + 1)) + 100000000) + String.valueOf((int) (Math.random() * (999999999 - 100000000 + 1)) + 100000000 + (int) (Math.random() * (999999999 - 100000000 + 1)) + 100000000) + String.valueOf((int) (Math.random() * (999999999 - 100000000 + 1)) + 100000000) + String.valueOf((int) (Math.random() * (999999999 - 100000000 + 1)) + 100000000) + String.valueOf((int) (Math.random() * (999999999 - 100000000 + 1)) + 100000000) + String.valueOf((int) (Math.random() * (999999999 - 100000000 + 1)) + 100000000) + String.valueOf((int) (Math.random() * (999999999 - 100000000 + 1)) + 100000000 + (int) (Math.random() * (999999999 - 100000000 + 1)) + 100000000) + String.valueOf((int) (Math.random() * (999999999 - 100000000 + 1)) + 100000000) + String.valueOf((int) (Math.random() * (999999999 - 100000000 + 1)) + 100000000) + String.valueOf((int) (Math.random() * (999999999 - 100000000 + 1)) + 100000000) + String.valueOf((int) (Math.random() * (999999999 - 100000000 + 1)) + 100000000) + String.valueOf((int) (Math.random() * (999999999 - 100000000 + 1)) + 100000000 + (int) (Math.random() * (999999999 - 100000000 + 1)) + 100000000) + String.valueOf((int) (Math.random() * (999999999 - 100000000 + 1)) + 100000000) + String.valueOf((int) (Math.random() * (999999999 - 100000000 + 1)) + 100000000) + String.valueOf((int) (Math.random() * (999999999 - 100000000 + 1)) + 100000000) + String.valueOf((int) (Math.random() * (999999999 - 100000000 + 1)) + 100000000) + String.valueOf((int) (Math.random() * (999999999 - 100000000 + 1)) + 100000000));
		}
		int times = testList.size() / threadCount;

		System.out.println(times);
		cdl = new CountDownLatch(threadCount);
		System.out.println("================" + cdl.getCount());

		for (int i = 0; i < threadCount; i++) {
			List<String> testList2 = testList.subList(i * times, (i + 1)
					* times);
			new Thread(new Thread1(testList2, i)).start();
		}
		try {
			cdl.await();
			System.out.println("Thread SUCCESS!!");
			System.out.println("COUNT " + count);
			
			
			FileWriter fw = null;
			BufferedWriter bw = null;
			
			fw = new FileWriter(new File("/home/zipe/tmp/final"), true);
			bw = new BufferedWriter(fw, 1024 * 1024);
			try {
				for(int i =0; i<threadCount; i++){
					
					FileReader fr = new FileReader("/home/zipe/tmp/test"
							+ i);

					BufferedReader br = new BufferedReader(fr, 1024 * 1024);
					
					String str;

					while ((str = br.readLine()) != null) {

						bw.write(str + "\n"); // 寫出 Loader File

					}
					br.close();

					fr.close();
					bw.flush();
				}
			} catch (Exception e) {
				throw e;
			} finally {
				if (bw != null) {
					try {
						bw.close();
					} catch (Exception e) {
					} /* end of try/catch */
				}
				if (fw != null) {
					try {
						fw.close();
					} catch (Exception e) {
					} /* end of try/catch */
				}
			}

		} catch (Exception e) {
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
		private int id=0;
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
				FileWriter fw = null;
				BufferedWriter bw = null;
				try {
					for (String test : testList) {

						fw = new FileWriter(new File("/home/zipe/tmp/test"
								+ id), true);
						bw = new BufferedWriter(fw, 1024 * 1024);
						bw.write(test + "\n"); // 寫出 Loader File
						bw.flush();

						i++;
					}
				} catch (Exception e) {
					throw e;
				} finally {
					if (bw != null) {
						try {
							bw.close();
						} catch (Exception e) {
						} /* end of try/catch */
					}
					if (fw != null) {
						try {
							fw.close();
						} catch (Exception e) {
						} /* end of try/catch */
					}
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
