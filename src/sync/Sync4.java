package sync;

public class Sync4 {
	public static void main(String[] args) {
		Sync4A s = new Sync4A();
		(new Thread(s)).start();
		(new Thread(s)).start();
	}
}

class Sync4A implements Runnable {
	private int value;

	public void run() {
		for (int count = 0; count < 3; count++) {
			synchronized (this) { // 設計同步區塊程式
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
}
