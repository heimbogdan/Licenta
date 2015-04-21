package bh.w2optimize.guillotine;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import bh.w2optimize.entity.Element;
import bh.w2optimize.entity.ElementList;
import bh.w2optimize.entity.FinalElement;

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

	public static void start(ElementList elementList, Element root) {
		threads = 0;
		results = new ArrayList<FinalElement>();
		final ExecutorService executorService = Executors.newFixedThreadPool(2);
		executorService.submit(createThread(elementList, root, false));
		executorService.submit(createThread(elementList, root, true));
		executorService.shutdown();
		while (!executorService.isTerminated()) {
			// we wait for the threads to end
		}
		// FinalElement result1 = results.get(0);
		// FinalElement result2 = results.get(1);
		// if (result1.getChildrens().size() < result2.getChildrens().size()) {
		// return result1;
		// } else if (result1.getChildrens().size() > result2.getChildrens()
		// .size()) {
		// return result2;
		// } else {
		// ArrayList<Double> r1 = result1.getIndividualLoss();
		// ArrayList<Double> r2 = result2.getIndividualLoss();
		// for (int i = 0; i < r1.size(); i++) {
		// if (r1.get(i) < r2.get(i)) {
		// return result1;
		// }
		// }
		// return result2;
		// }
	}

	private static GuillotineThread createThread(final ElementList elementList,
			Element root, boolean h) {
		ElementList eList = (ElementList) elementList.clone();
		Element element = root.cloneElement();
		return new GuillotineThread(eList, element, h);
	}
}
