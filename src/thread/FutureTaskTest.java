package thread;

import java.util.Vector;
import java.util.concurrent.*;

public class FutureTaskTest {
	private Vector<String> threadNames = new Vector<String>();

	public static void main(String[] args) {
		FutureTaskTest test = new FutureTaskTest();
		test.threadTest(Integer.parseInt(args[0]));
		System.out.println(test.threadNames);
	}

	private void threadTest(int numOfThreads) {
		Thread[] threads = new Thread[numOfThreads];
		FutureTask<String>[] futureTasks = new FutureTask[numOfThreads];
		for (int i = 0; i < threads.length; i++) {
			futureTasks[i] = new FutureTask<String>(new MyCallable());
			threads[i] = new Thread(futureTasks[i]);
			threads[i].start();
		}
		for (FutureTask<String> f : futureTasks) {
			try {
				threadNames.add(f.get());
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		}
	}

	private static class MyCallable implements Callable<String> {
		public String call() {
			for (int i = 0; i < 1000000; i++) {
				i = i + 0;
			}
			return Thread.currentThread().getName();
		}
	}
}