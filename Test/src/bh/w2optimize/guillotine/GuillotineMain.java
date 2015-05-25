package bh.w2optimize.guillotine;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import bh.w2optimize.entity.Element;
import bh.w2optimize.entity.ElementList;

public class GuillotineMain {

	private static GuillotineMain instance;
	private ExecutorService executorService;
	
	private GuillotineMain(){
		executorService = Executors.newFixedThreadPool(2);
	}
	
	public static GuillotineMain getInstance(){
		if(instance == null){
			instance = new GuillotineMain();
		}
		return instance;
	}
	
	public void start(ElementList elementList, Element root) {
		if(executorService.isShutdown()){
			executorService = Executors.newFixedThreadPool(2);
		}
		executorService.submit(createThread(elementList, root, false));
		executorService.submit(createThread(elementList, root, true));
		executorService.shutdown();
	}

	private static GuillotineThread createThread(final ElementList elementList,
			Element root, boolean h) {
		
		ElementList eList = (ElementList) elementList.clone();
		Element element = root.cloneElement();
		return new GuillotineThread(eList, element, h);
	}
}
