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
	private static void init(){
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
	
	public void test() {
		corp.rescaleElements(150, 65, 210);
		assertEquals("Length not the same!", 150, corp.getLength(), 0.00001);
		assertEquals("Width not the same!", 65, corp.getWidth(), 0.00001);
		assertEquals("Height not the same!", 210, corp.getHeight(), 0.00001);
	}

}
