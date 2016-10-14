package thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorEx {

	public static void main(String[] args) {

		// Creating executor service with 3 thread pool.
		ExecutorService exec = Executors.newFixedThreadPool(4);

		// creating worker thread and adding to the pool.
		Thread t1 = new Thread(new WorkerTh(), "worker1");
		exec.execute(t1);
		Thread t2 = new Thread(new WorkerTh(), "worker2");
		exec.execute(t2);
		Thread t3 = new Thread(new WorkerTh(), "worker3");
		exec.execute(t3);
		Thread t4 = new Thread(new WorkerTh(), "worker4");
		exec.execute(t4);

		// calling shutdown will not allow no more thread.
		exec.shutdown();

		// Waiting until all threads finish the work..
		while (!exec.isTerminated()) {
		}

		System.out.println("All thread completed job..");
	}

}

// Worker thread implementation.
class WorkerTh implements Runnable {

	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName());
		System.out.println("Waiting for 3000 milli seconds...");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
}
