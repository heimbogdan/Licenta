package clase2;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GuillotineMain {

	private static int threads;
	private static ArrayList<FinalElement> results;

	public static void addResult(FinalElement result) {
		results.add(result);
	}

	public static int getThNr() {
		return threads;
	}

	public static void addThread() {
		threads++;
	}

	public static void endThread() {
		threads--;
	}

	public static FinalElement start(ElementList elementList, Element root) {
		threads = 0;
		results = new ArrayList<FinalElement>();
		ExecutorService es = Executors.newFixedThreadPool(2);
		es.submit(createThread(elementList, root, false));
		es.submit(createThread(elementList, root, true));
		es.shutdown();
		while (!es.isTerminated()) {
			// we wait for the threads to end
		}
		FinalElement result1 = results.get(0);
		FinalElement result2 = results.get(1);
		if (result1.getChildrens().size() < result2.getChildrens().size()) {
			return result1;
		} else if (result1.getChildrens().size() > result2.getChildrens()
				.size()) {
			return result2;
		} else {
			ArrayList<Double> r1 = result1.getIndividualLoss();
			ArrayList<Double> r2 = result2.getIndividualLoss();
			for (int i = 0; i < r1.size(); i++) {
				if (r1.get(i) < r2.get(i)) {
					return result1;
				}
			}
			return result2;
		}
	}

	private static GuillotineThread createThread(ElementList elementList,
			Element root, boolean h) {
		ElementList el = (ElementList) elementList.clone();
		Element ro = root.cloneElement();
		GuillotineThread gThread = new GuillotineThread(el, ro, h);
		return gThread;
	}
}
