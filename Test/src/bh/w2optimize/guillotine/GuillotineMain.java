package bh.w2optimize.guillotine;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.hibernate.hql.ast.tree.FromClause;

import bh.w2optimize.elements.Element;
import bh.w2optimize.elements.ElementList;
import bh.w2optimize.gui.CutPanel;
import bh.w2optimize.gui.FrontInterfaceGUI;

public class GuillotineMain {

	private static GuillotineMain instance;
	//private ExecutorService executorService;
	private GuillotineThread thread1,thread2;
	private boolean stop;
	private int constValue;
	private Date startTime;
	private int triesNumber;
	private GuillotineConstraints constraint;
	private CutPanel panel = FrontInterfaceGUI.panel;
	
	public boolean isStop() {
		switch (constraint) {
		case NONE:
			return stop;
		case TIME:
			return ((new Date().getTime()- startTime.getTime())/1000) >= constValue ? true : false ;
		case TRIES:
			return triesNumber >= constValue ? true : false;
		case BESTSOLUTION:
			boolean set;
			synchronized (panel){
				set = panel.getNrIncadrare() >= constValue ? true : false;
			}
			return set;
		default:
			return stop;
		}
	}

	public void setTriesNumber(int number){
		this.triesNumber += number;
	}
	private GuillotineMain(){
		
	}
	
	public static GuillotineMain getInstance(){
		if(instance == null){
			instance = new GuillotineMain();
		}
		return instance;
	}
	
	public void start(ElementList elementList, Element root,GuillotineConstraints type, String value) {
		if(thread1 != null && thread1.isAlive() && thread2 != null && thread2.isAlive()){
			stopCurrentThreads();
		}
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		synchronized(this){
			stop = false;
		}
		// constraints
		if (value != null && !value.isEmpty()) {
			synchronized (this) {
				this.constValue = Integer.parseInt(value);
				this.constraint = constraint;
			}
		}else{
			this.constraint = GuillotineConstraints.NONE;
		}
			
		thread1 = new GuillotineThread(false);
		thread2 = new GuillotineThread(true);
		thread1.setElements(elementList);
		thread2.setElements(elementList);
		thread1.setRoot(root);
		thread2.setRoot(root);
		thread1.start();
		thread2.start();
		this.startTime = new Date();
	}

	
	public void stopCurrentThreads(){
		synchronized(this){
			stop = true;
		}
	}
}
