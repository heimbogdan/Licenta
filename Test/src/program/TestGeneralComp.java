package program;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import bh.w2optimize.entity.ElementList;
import bh.w2optimize.entity.GeneralComponent;
import bh.w2optimize.entity.GeneralElement;

public class TestGeneralComp {

	private static GeneralComponent corp;
	
	@BeforeClass
	public static void init(){
		ElementList list = new ElementList();
		list.add(new GeneralElement(50, 200, false, 1, 3, 50, 100));
		list.add(new GeneralElement(50, 200, false, 1, 3, 50, 100));
		list.add(new GeneralElement(60, 200, false, 2, 3, 100, 100));
		list.add(new GeneralElement(60, 200, false, 2, 3, 100, 100));
		list.add(new GeneralElement(100, 60, false, 1, 2, 100, 100));
		list.add(new GeneralElement(100, 60, false, 1, 2, 100, 100));
		list.add(new GeneralElement(54, 196, false, 2, 3, 100, 100));
		corp = new GeneralComponent("Sif", "S2U", 100, 60, 200, list);		
	}
	
	@Test
	public void test() {
		corp.rescaleElements(150, 65, 210);
		assertEquals("Length not the same!", 150, corp.getLength(), 0.00001);
		assertEquals("Width not the same!", 65, corp.getWidth(), 0.00001);
		assertEquals("Height not the same!", 210, corp.getHeight(), 0.00001);
		
		assertEquals("1 is bad", 75, corp.getElements().get(0).getLength(), 0.00001);
		assertEquals("1 is bad2", 210, corp.getElements().get(0).getWidth(), 0.00001);
		
		assertEquals("2 is bad", 65, corp.getElements().get(2).getLength(), 0.00001);
		assertEquals("2 is bad2", 210, corp.getElements().get(2).getWidth(), 0.00001);
		
		assertEquals("3 is bad", 150, corp.getElements().get(4).getLength(), 0.00001);
		assertEquals("3 is bad2", 65, corp.getElements().get(4).getWidth(), 0.00001);
		
		assertEquals("4 is bad", 59, corp.getElements().get(6).getLength(), 0.00001);
		assertEquals("4 is bad2", 206, corp.getElements().get(6).getWidth(), 0.00001);
	}

}
