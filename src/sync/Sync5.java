package sync;

public class Sync5 {
	public static void main(String[] args) {
		Sync5A s = new Sync5A();
		(new Thread(s)).start();
		(new Thread(s)).start();
	}
}

class Sync5A implements Runnable {
	private int value;

	public void run() {
		printValue();
	}

	synchronized void printValue() { // 設計同步方法
		for (int count = 0; count < 3; count++) {
			value++;
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}
			System.out.println(Thread.currentThread().getName() + "：Value="
					+ value);
		}
	}
}
