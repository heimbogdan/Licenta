package program;

import java.util.List;

import bh.w2optimize.db.connection.SQLiteConnection;
import bh.w2optimize.entity.Accessory;
import bh.w2optimize.entity.Component;
import bh.w2optimize.entity.Element;
import bh.w2optimize.entity.ElementList;
import bh.w2optimize.entity.GeneralComponent;
import bh.w2optimize.entity.GeneralElement;
import bh.w2optimize.entity.WoodBoard;
import bh.w2optimize.gui.FrontInterfaceGUI;
import bh.w2optimize.gui.Panel;
import bh.w2optimize.guillotine.GuillotineMain;










import org.apache.log4j.BasicConfigurator;
import org.hibernate.SQLQuery;
import org.hibernate.Session;  
import org.hibernate.SessionFactory;  
import org.hibernate.Transaction;  
import org.hibernate.cfg.Configuration;

import com.sun.org.apache.bcel.internal.generic.NEW;

public class Main {

	public static FrontInterfaceGUI Panel;
	public static GuillotineMain guillotineMain;
	public static void main(String[] args) throws CloneNotSupportedException {

		ElementList elms = new ElementList();
		elms.addMore(40, 100, true, 2);
		elms.addMore(80, 35, true, 3);
		elms.addMore(45, 45, false, 1);
		elms.addMore(85, 100, false, 2);
		elms.addMore(100, 200, false, 1);
		elms.addMore(100, 200, false, 1);
		elms.addMore(25, 50, true, 2);
		elms.addMore(45, 25, false, 3);
		elms.addMore(60, 40, false, 3);
		elms.addMore(50, 27, true, 2);
		
//		Panel = new FrontInterfaceGUI();
//		Panel.setVisible(true);
//		GuillotineMain.start(elms, new Element(207, 280, false));
//		
		
//		//TestBaza_DATE
//		BasicConfigurator.configure();
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
//	    Element elem = new Element(100, 20,false);
//	    elem.setComponentCode("XB2");
//	    elem.setName("Usa");
//	    session.persist(elem);
//	    session.persist(new Accessory("EUR_S", "Euro Surub", 0.09));
//	    session.persist(new WoodBoard("NUCP", "NUC", "PAL", 285, 207, 270));
//	    session.persist(new GeneralComponent("Birou 2 usi", "G_B2U", 120, 75, 85));
//	    GeneralElement gel = new GeneralElement(120, 75 , false, 1, 2, 100, 100);
//	    gel.setComponentCode("G_B2U");
//	    gel.setName("Blat Birou");
//	    session.persist(gel);
//	    Element elfrgel = gel.toElement();
//	    session.persist(elfrgel);
//	    t.commit();//transaction is committed 
//	    
//	    //-- testare Update / delete
////	    t.begin();
////	    elfrgel.setName("AAAAAAA");
////	    session.update(elfrgel);
////	    session.delete(elem);
////	    t.commit();
//	    SQLQuery q =  session.createSQLQuery("select * from component");
//	    q.addEntity(Component.class);
//	    List<Component> list = q.list();
//	    //session.close();
//	    
//	    for (Component c : list){
//	    	System.out.println(c.toString());
//	    }
//	    
//	    SQLQuery q2 = session.createSQLQuery("select * from element");
//	    q2.addEntity(Element.class);
//	    List<Element> ellist = q2.list();
//	    for(Element e : ellist){
//	    	System.out.println(e);
//	    }
//	    SQLQuery q3 = session.createSQLQuery("select * from accessory");
//	    q3.addEntity(Accessory.class);
//	    List<Accessory> acclist = q3.list();
//	    for(Accessory e : acclist){
//	    	System.out.println(e);
//	    }
//	    
//	    SQLQuery q4 = session.createSQLQuery("select * from woodboard");
//	    q4.addEntity(WoodBoard.class);
//	    List<WoodBoard> wblist = q4.list();
//	    for(WoodBoard e : wblist){
//	    	System.out.println(e);
//	    }
//	    SQLQuery q5 = session.createSQLQuery("select * from generalcomponent");
//	    q5.addEntity(GeneralComponent.class);
//	    List<GeneralComponent> gclist = q5.list();
//	    for(GeneralComponent e : gclist){
//	    	System.out.println(e);
//	    }
//	    
//	    SQLQuery q6 = session.createSQLQuery("select * from generalelement");
//	    q6.addEntity(GeneralElement.class);
//	    List<GeneralElement> gelist = q6.list();
//	    for(GeneralElement e : gelist){
//	    	System.out.println(e);
//	    }
//	    session.close();
	}
}
