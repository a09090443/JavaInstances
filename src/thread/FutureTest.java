package thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class FutureTest {
	public static void main(String[] args) throws InterruptedException,
			ExecutionException {
		int result = new FutureTest().test();
		System.out.println(result);
	}

	private int test() throws InterruptedException, ExecutionException {
		MyCall<Integer> call1 = new MyCall<Integer>("1");
		FutureTask<Integer> task1 = new FutureTask<Integer>(call1);
		new Thread(task1).start();
		MyCall<Integer> call2 = new MyCall<Integer>("2");
		FutureTask<Integer> task2 = new FutureTask<Integer>(call2);
		new Thread(task2).start();

		return task1.get() * task2.get();
	}

	private class MyCall<T> implements Callable<Integer> {
		private String name;

		private MyCall(String name) {
			this.name = name;
		}

		@Override
		public Integer call() throws Exception {
			int i = 10;
			while (i > 0) {
				i--;
				System.out.println("MyCall:[" + name + "] is working.");
				Thread.sleep(1000);
			}

			return Integer.valueOf(12);
		}
	}
}