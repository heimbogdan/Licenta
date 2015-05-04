package program;

import java.util.List;

import bh.w2optimize.db.connection.SQLiteConnection;
import bh.w2optimize.entity.Component;
import bh.w2optimize.entity.Element;
import bh.w2optimize.entity.ElementList;
import bh.w2optimize.gui.FrontInterfaceGUI;
import bh.w2optimize.gui.Panel;
import bh.w2optimize.guillotine.GuillotineMain;




import org.hibernate.SQLQuery;
import org.hibernate.Session;  
import org.hibernate.SessionFactory;  
import org.hibernate.Transaction;  
import org.hibernate.cfg.Configuration;

public class Main {

	public static FrontInterfaceGUI Panel;
	public static GuillotineMain guillotineMain;
	public static void main(String[] args) throws CloneNotSupportedException {

		ElementList elms = new ElementList();
		elms.addMore(40, 100, true, 2);
//		elms.addMore(80, 35, true, 3);
//		elms.addMore(45, 45, false, 1);
//		elms.addMore(85, 100, false, 2);
//		elms.addMore(100, 200, false, 1);
//		elms.addMore(100, 200, false, 1);
//		elms.addMore(25, 50, true, 2);
//		elms.addMore(45, 25, false, 3);
//		elms.addMore(60, 40, false, 3);
//		elms.addMore(50, 27, true, 2);
		
		Panel = new FrontInterfaceGUI();
		Panel.setVisible(true);
//		GuillotineMain.start(elms, new Element(207, 280, false));
		
//		//SQLiteConnection conn = SQLiteConnection.getInstance();
//		Configuration cfg=new Configuration();  
//	    cfg.configure("hibernate.cfg.xml");//populates the data of the configuration file  
//	      
//	    //creating seession factory object  
//	    SessionFactory factory=cfg.buildSessionFactory();  
//	      
//	    //creating session object  
//	    Session session=factory.openSession();  
//	      
//	    //creating transaction object  
//	    Transaction t=session.beginTransaction();  
//	    
//	    session.persist(new Component("aaa", "SW2"));//persisting the object  
//	    session.persist(new Component("bbb", "XB2"));
//	    session.persist(new Element(100, 20));
//	    t.commit();//transaction is committed 
//	    SQLQuery q =  session.createSQLQuery("select * from component");
//	    q.addEntity(Component.class);
//	    List<Component> list = q.list();
//	    session.close();
//	    
//	    for (Component c : list){
//	    	System.out.println(c.toString());
//	    }
	}
}
