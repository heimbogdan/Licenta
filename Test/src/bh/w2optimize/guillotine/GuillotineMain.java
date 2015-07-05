package bh.w2optimize.guillotine;

import bh.w2optimize.elements.Element;
import bh.w2optimize.elements.ElementList;
import bh.w2optimize.gui.CutPanel;
import bh.w2optimize.gui.FrontInterfaceGUI;

public class GuillotineMain {

	private static GuillotineMain instance;
	private GuillotineThread thread1,thread2;
	private boolean stop;
	private CutPanel panel = FrontInterfaceGUI.panel;
	
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
	
	public void start(ElementList elementList, Element root,GuillotineConstraints type, int time) {
		if(thread1 != null && thread1.isAlive() && thread2 != null && thread2.isAlive()){
			stopCurrentThreads();
		}
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			// TODO de pus log4j
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
		
		// time restriction
		if (time != 0) {
			Thread toStop = new Thread(new Runnable() {

				@Override
				public void run() {
					try {
						Thread.sleep(time*1000);
						synchronized(instance){
							instance.stop = true;
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			});
			toStop.start();
		}
	}

	
	public void stopCurrentThreads(){
		synchronized(this){
			this.stop = true;
		}
	}
}
