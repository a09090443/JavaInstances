package thread;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import java.util.concurrent.CountDownLatch;

public class SplitSort {
	private CountDownLatch cdl;
	private static final int threadCount = 5;
	private List<List> tempSortList;

	public List sort(List inputSortList) {
		cdl = new CountDownLatch(threadCount);
		tempSortList = Collections.synchronizedList(new ArrayList<List>());
		int avg = inputSortList.size()/threadCount;
		int remainder = inputSortList.size()%threadCount;
		for (int i = 0; i < threadCount; i++) {
			int threadID = i + 1;
//			new Thread(new Worker(inputSortList.subList(i * threadCount, (i + 1) * threadCount))).start();
			if(threadID < threadCount){
				new Thread(new Worker(inputSortList.subList(i * avg, (i + 1) * avg))).start();
			}else if(threadID == threadCount){
				new Thread(new Worker(inputSortList.subList(i * avg, ((i + 1) * avg) + remainder))).start();
			}
		}
		try {
			cdl.await();
			System.out.println("SUCCESS!!");
			System.out.println(tempSortList.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return merge(tempSortList);
	}

	private List merge(List<List> l) {
		// add your merge code here
		return null;
	}

	private List workerSort(List l) {
		// add each thread sort code here
		return null;
	}

	class Worker implements Runnable {
		private List threadSortList;

		public Worker(List threadsortList) {
			threadSortList = threadsortList;
		}

		public void run() {
			try {
//				System.out.println(threadSortList.size());
				for(int i=0; i < threadSortList.size(); i++){
					System.out.println(threadSortList.get(i));
				}
				tempSortList.addAll(threadSortList);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				cdl.countDown();
			}
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		List inputList = new ArrayList();
		for(int i = 0; i<=100; i++){
			inputList.add(i);
		}
		SplitSort ss = new SplitSort();
		System.out.println(ss.sort(inputList));
	}
}
