package program;

import bh.w2optimize.entity.Element;
import bh.w2optimize.entity.ElementList;
import bh.w2optimize.guillotine.GuillotineMain;
import clase.Panel;

public class Main {

	public static Panel Panel;

	public static void main(String[] args) throws CloneNotSupportedException {

		ElementList elms = new ElementList();
		elms.addMore(100, 40, 2);
		elms.addMore(80, 35, 3);
		elms.addMore(45, 45, 1);
		elms.addMore(85, 100, 2);
		elms.addMore(100, 200, 1);
		elms.addMore(100, 200, 1);
		elms.addMore(25, 50, 2);
		elms.addMore(45, 25, 3);
		elms.addMore(60, 40, 3);
		elms.addMore(50, 27, 2);
		// FinalElement incadrare = GuillotineCut.beginCutting(elms, new
		// Element(
		// 207, 280));
		Panel = new Panel(null);
		// FinalElement incadrare =
		GuillotineMain.start(elms, new Element(207, 280));
		// new Panel(incadrare);
	}
}
