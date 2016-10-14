package thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ThreadTest implements Runnable{
	private static int count=0;
	
	private static Boolean check = false;
	
	public static void main(String[] args) throws InterruptedException {
		int threadNumber = 10;
		final CountDownLatch countDownLatch = new CountDownLatch(threadNumber);
		final List<String> testList = new ArrayList<String>();
		for (int i = 0; i < threadNumber; i++) {
			final int threadID = i;
			System.out.println(i);
			new Thread() {
				public void run() {
					List<String> test2 = new ArrayList<String>();
					try {
						for(int x=threadID*10000; x<(threadID+1)*10000; x++){
							if (check){
								Thread.interrupted();
							}
							test2.add("threadID:["+threadID+"]" + x );
							if(threadID == 5 && x == 59999){
								check = true;
							}
						}
						Thread.sleep((long) (Math.random() * 1000));
						System.out.println(Thread.currentThread().getName());

						testList.addAll(test2);
					} catch (InterruptedException e) {
						testList.clear();
						e.printStackTrace();
					}
//					System.out.println(String.format("threadID:[%s] finished!!", threadID));
//					System.out.println(String.format("threadID:[%s] list:" + test2.size() , threadID));
					count=count+1;
//					System.out.println(count);
					countDownLatch.countDown();
					System.out.println(Thread.currentThread().getName());

				}
			}.start();
		}
		countDownLatch.await();
		System.out.println(testList.size());
		for(String list:testList){
//			System.out.println(list);
		}
		System.out.println("main thread finished!!");
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
}
