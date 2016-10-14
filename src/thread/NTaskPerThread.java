package thread;

import java.util.ArrayList;
import java.util.List;

/**
 * N個任務,平均分给M線程處理
 * 
 * @author XF
 */
public class NTaskPerThread {
	int task_num = 12;
	int thread_num = 3;
	List<Task> list = new ArrayList<NTaskPerThread.Task>();
	long total = 0;// 任務運行時間,用於比較不通線程數量的效率

	public static void main(String[] args) {
		NTaskPerThread perThread = new NTaskPerThread();
		perThread.test();
	}

	public NTaskPerThread() {
	}

	public void test() {

		for (int i = 0; i < task_num; i++) {
			list.add(new Task(i));
		}

		// 给每個線程分配任務,應list從索引0開始,所以分配任務編號從0開始
		int num = task_num / thread_num;// 這样子可能還有餘數,應該把餘數也分攤
		if (task_num % thread_num != 0) {
			num++;// 如果有餘數(一定小於thread_num),則前面的線程分攤下,每個線程多做一個任務
		}

		for (int i = 0; i < thread_num; i++) {
			int start = i * num;
			int end = Math.min((i + 1) * num, list.size());// 最後一個線程任務可能不夠
			new TaskThread(start, end).start();
		}

	}

	public class Task {
		private int n;

		public Task(int n) {
			this.n = n;
		}

		public void run() {
			System.out.println("run task num : " + n);
			for (int i = 0; i < 10000000; i++) {
				int s = 0;
				s += i;
			}
		}
	}

	public class TaskThread extends Thread {
		int start;
		int end;

		public TaskThread(int start, int end) {
			this.start = start;
			this.end = end;

		}

		@Override
		public void run() {
			long s = System.currentTimeMillis();
			for (; start < end; start++) {
				list.get(start).run();
			}
			total += (System.currentTimeMillis() - s);
			System.out.println(total);
		}
	}

}