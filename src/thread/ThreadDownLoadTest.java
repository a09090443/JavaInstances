package thread;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class ThreadDownLoadTest {

	/**
	 * 总人数
	 */
	private static final int P_COUNT = 5;

	/**
	 * 裁判人数
	 */
	private static final int TOTAL = 1;

	public static void main(String[] args) {

		/**
		 * 准备总人数，每准备好一个人，就减一
		 */
		final CountDownLatch readyCount = new CountDownLatch(P_COUNT);

		/**
		 * 跑完人数计数，完成一个人，计数减一
		 */
		final CountDownLatch runCount = new CountDownLatch(P_COUNT);

		/**
		 * 发出命令开始跑
		 */
		final CountDownLatch startRecordCount = new CountDownLatch(TOTAL);

		for (int i = 1; i <= P_COUNT; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						Thread.sleep(new Random().nextInt(5000));
						System.out.println("运动员"
								+ Thread.currentThread().getName()
								+ "已经准备完毕,还有" + readyCount.getCount()
								+ "个人正在准备中.....");
						readyCount.countDown();
						startRecordCount.await();
						Thread.sleep(new Random().nextInt(10000));
						System.out.println("运动员"
								+ Thread.currentThread().getName() + "到达终点,还有"
								+ runCount.getCount() + "个人在路上");
						runCount.countDown();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}).start();
		}

		try {
			readyCount.await();
			System.out.println("裁判:所有运动员准备完毕，开始跑,等待结果");
			startRecordCount.countDown();
			runCount.await();
			System.out.println("裁判:跑步完毕，统计结果...");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
