package thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ThreadTest2 {
	private static int count=0;
	public static void main(String[] args) throws InterruptedException {
		List<String> listTest = new ArrayList<String>();
		for(int i=0; i<11; i++){
			listTest.add("TEST:" + i);
		}
		
		int threadNumber = 10;
		int a = listTest.size()/threadNumber;
		System.out.println(a);
		if(listTest.size()%threadNumber !=0){
			a++;
		}
		System.out.println(a);

		final CountDownLatch countDownLatch = new CountDownLatch(a);
		final List<String> testList = new ArrayList<String>();
		for (int i = 0; i < a; i++) {
			final int threadID = i;
			System.out.println(i);
			new Thread() {
				public void run() {
					List<String> test2 = new ArrayList<String>();
					
					try {
						for(int x=threadID*1000; x<(threadID+1)*1000; x++){
							test2.add("threadID:["+threadID+"]" + x );
						}
						Thread.sleep((long) (Math.random() * 10000));
						
						testList.addAll(test2);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println(String.format("threadID:[%s] finished!!", threadID));
					System.out.println(String.format("threadID:[%s] list:" + test2.size() , threadID));
					count=count+1;
					System.out.println(count);
					countDownLatch.countDown();
				}
			}.start();
		}
		countDownLatch.await();
		System.out.println(testList.size());
		for(String list:testList){
			System.out.println(list);
		}
		System.out.println("main thread finished!!");
	}
}
