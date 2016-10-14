package thread;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TaskAssignAndResultCollection {
	private final static int DEFAULT_THREAD_NUM = 5;

	private int threadNum = DEFAULT_THREAD_NUM;
	private Worker[] threads = null;

	public TaskAssignAndResultCollection(int threadNum) {
		super();
		if (threadNum == 0) {
			threadNum = DEFAULT_THREAD_NUM;
		} else {
			this.threadNum = threadNum;
		}

	}

	public Map<String, String> processStringBatchly(String[] datas) {

		if (threads == null) {
			synchronized (this) {
				threads = new Worker[threadNum];

				for (int i = 0; i < threadNum; i++) {
					threads[i] = new Worker();
				}
			}
		}

		// 怎么把domainName分配给线程， 让它们自己运行去？平均分配，
		int domainSize = datas.length;
		int domainNamePerThread = domainSize / threadNum;
		int leftDomainName = domainSize % threadNum;

		List<String> listDomainName = Arrays.asList(datas);

		// 先每个线程平均地分domainNamePerThread个DomainName，
		int endIndex = 0;
		for (int i = 0; i < threadNum; i++) {
			int beginIndex = i * domainNamePerThread;
			int step = domainNamePerThread;
			endIndex = beginIndex + step;
			List<String> subDomainNames = new ArrayList<String>(
					listDomainName.subList(beginIndex, endIndex));

			threads[i].setDomainNameList(subDomainNames);
		}

		// 然后，再把剩下的逐个分配。
		for (int i = 0; i < leftDomainName; i++) {
			threads[i].addDomainName(listDomainName.get(endIndex + i));
		}

		for (Worker thread : threads) {
			thread.start();
			try {
				thread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		Map<String, String> totalResult = new HashMap<String, String>();

		for (Worker thread : threads) {
			totalResult.putAll(thread.getResultCollector());
		}

		return totalResult;
	}

	public static void main(String[] args) {
		String[] datas = new String[] { "baidu.com", "sohu.com", "163.com",
				"iteye.com" };

		TaskAssignAndResultCollection c = new TaskAssignAndResultCollection(3);

		Map<String, String> resultCollector = c.processStringBatchly(datas);
		c.showMsg(resultCollector);
	}

	private void showMsg(Map<String, String> result) {
		for (Map.Entry<String, String> me : result.entrySet()) {
			String data = me.getKey();
			String r = me.getValue();

			String msg = "原始值[" + data + "]" + " 处理后[" + r + "]";

			System.out.println(msg);
		}
	}

}

class Worker extends Thread {
	private List<String> datas;
	private Map<String, String> resultCollector = new HashMap<String, String>();

	public void run() {
		for (String d : datas) {
			String result = d + "@";

			resultCollector.put(d, result);
		}
	}

	public void setDomainNameList(List<String> subDomainNames) {
		datas = subDomainNames;
	}

	public void addDomainName(String domainName) {
		if (datas == null) {
			datas = new ArrayList<String>();
		}
		datas.add(domainName);
	}

	public Map<String, String> getResultCollector() {
		return resultCollector;
	}

}