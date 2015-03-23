package program;

import clase.Panel;
import clase2.Element;
import clase2.ElementList;
import clase2.FinalElement;
import clase2.GuillotineCut;

public class Main {

	public static void main(String[] args) throws CloneNotSupportedException {

		ElementList elms = new ElementList();
		elms.addMore(100, 20, 2);
		elms.addMore(50, 20, 3);
		elms.addMore(43.5, 20, 1);
		elms.addMore(85, 100, 2);
		FinalElement incadrare = GuillotineCut.beginCutting(elms, new Element(
				285, 250));
		new Panel(incadrare);
	}
}
