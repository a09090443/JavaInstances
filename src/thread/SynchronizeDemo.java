package thread;

public class SynchronizeDemo {

	public static void main(String[] args) {
		Account acnt = new Account();
		Thread prod = new Thread(new Producer(acnt));
		Thread con = new Thread(new Consumer(acnt));
		Thread mon = new Thread(new Monitor(acnt));
		prod.start();
		con.start();
		mon.start();
	}

}

class Account {

	int sum = 50;

	public synchronized void withDraw() {
		try {
			if (sum <= 0) {
				this.wait();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if (sum >= 10) {
			sum -= 10;
			System.out.println("Withdraw 10");
		} else {
			System.out.println("Withdraw " + sum);
			sum -= sum;
		}
	}

	public synchronized void Deposit() {
		sum++;
		System.out.println("Deposit 1");
		this.notify();
	}

	public synchronized void printBalance() {
		if (sum <= 0) {
			System.out.println("No money now.");
		} else {
			System.out.println("Balance :" + sum);
		}
	}

}

class Producer implements Runnable {
	Account account;

	Producer(Account act) {
		this.account = act;
	}

	public void run() {
		while (true) {
			account.Deposit();
			try {
				Thread.sleep(6000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
	}
}

class Consumer implements Runnable {
	Account account;

	Consumer(Account act) {
		this.account = act;
	}

	public void run() {
		while (true) {
			account.withDraw();
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
	}
}

class Monitor implements Runnable {
	Account account;

	Monitor(Account act) {
		this.account = act;
	}

	public void run() {
		while (true) {
			account.printBalance();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
	}
}
