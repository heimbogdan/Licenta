package bh.w2optimize.guillotine;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import bh.w2optimize.entity.Element;
import bh.w2optimize.entity.ElementList;

public class GuillotineMain {

	private static GuillotineMain instance;
	//private ExecutorService executorService;
	private GuillotineThread thread1,thread2;
	private boolean stop;
	
	
	public boolean isStop() {
		return stop;
	}

	private GuillotineMain(){
		
	}
	
	public static GuillotineMain getInstance(){
		if(instance == null){
			instance = new GuillotineMain();
		}
		return instance;
	}
	
	public void start(ElementList elementList, Element root) {
		if(thread1 != null && thread1.isAlive() && thread2 != null && thread2.isAlive()){
			stopCurrentThreads();
		}
		try {
			Thread.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		synchronized(this){
			stop = false;
		}
		thread1 = new GuillotineThread(false);
		thread2 = new GuillotineThread(true);
		thread1.setElements(elementList);
		thread2.setElements(elementList);
		thread1.setRoot(root);
		thread2.setRoot(root);
		thread1.start();
		thread2.start();
	}

	
	public void stopCurrentThreads(){
		synchronized(this){
			stop = true;
		}
	}
}
