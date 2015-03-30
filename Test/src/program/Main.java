package program;

import clase.Panel;
import clase2.Element;
import clase2.ElementList;
import clase2.FinalElement;
import clase2.GuillotineCut;
import clase2.GuillotineMain;

public class Main {
	public static void main(String[] args) throws CloneNotSupportedException {

		ElementList elms = new ElementList();
		// elms.addMore(100, 40, 2);
		// elms.addMore(80, 35, 3);
		// elms.addMore(45, 45, 1);
		// elms.addMore(85, 100, 2);
		// elms.addMore(100, 200, 1);
		elms.addMore(100, 200, 1);
		elms.addMore(25, 50, 2);
		elms.addMore(45, 25, 3);
		elms.addMore(60, 40, 1);
		elms.addMore(50, 27, 1);
		// FinalElement incadrare = GuillotineCut.beginCutting(elms, new
		// Element(
		// 207, 280));
		FinalElement incadrare = GuillotineMain.start(elms, new Element(207,
				280));
		new Panel(incadrare);
	}
}
