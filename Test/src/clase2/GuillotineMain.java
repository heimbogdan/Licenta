package clase2;

import java.util.ArrayList;

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
		ElementList el1 = (ElementList) elementList.clone();
		ElementList el2 = (ElementList) elementList.clone();
		Element ro1 = root.cloneElement();
		Element ro2 = root.cloneElement();
		GuillotineThread gThread1 = new GuillotineThread(el1, ro1, false, 1);
		GuillotineThread gThread2 = new GuillotineThread(el2, ro2, true, 2);
		try {
			gThread1.start();
			gThread2.start();
			gThread1.join();
			gThread2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
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
}
