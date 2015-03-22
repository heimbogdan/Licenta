package program;

import java.util.ArrayList;

import clase.Aranjamente;
import clase.Incadrare;
import clase.Panel;
import clase.Permutari;
import clase.Placa;
import clase2.Element;
import clase2.ElementList;
import clase2.GuillotineCut;

public class Main {

	public static void main(String[] args) throws CloneNotSupportedException {
		// ArrayList<Integer> numere = new ArrayList<Integer>();
		// for (int i = 1; i <= 5; i++) {
		// numere.add(i);
		// }
		// Permutari.perm(numere, 0, null);

		// ArrayList<Placa> placi = new ArrayList<Placa>();
		// placi.add(new Placa(50, 20));
		// placi.add(new Placa(142, 80));
		// placi.add(new Placa(100, 50));
		// placi.add(new Placa(75, 100));
		// placi.add(new Placa(90, 60));
		// placi.add(new Placa(100, 40));
		// placi.add(new Placa(85, 30));
		// placi.add(new Placa(43, 38));
		// placi.add(new Placa(20, 15));
		// placi.add(new Placa(20, 14)); // ~ 4 sec9
		// placi.add(new Placa(23, 60)); // ~30 sec
		// placi.add(new Placa(20, 20)); // ~ 4 min
		// placi.add(new Placa(60, 100));
		// placi.add(new Placa(25.5, 21.5));
		// placi.add(new Placa(50.6, 33.4));
		// placi.add(new Placa(20, 30.6));
		// placi.add(new Placa(100, 85.7));
		// placi.add(new Placa(23, 15)); // ~5 sec daca iau perechi de 10
		//
		// Placa PAL = new Placa(285, 250);
		// ArrayList<ArrayList<Placa>> best = Permutari.permuta(placi, PAL);
		//
		// System.out.println("Cea mai buna permutare: \n");
		// for (int nr = 1; nr <= best.size(); nr++) {
		// ArrayList<Placa> pl = best.get(nr - 1);
		// System.out.println("Set " + nr + ": " + pl);
		// }
		// Incadrare incadrare = Aranjamente.aranjare(best, PAL);
		// System.out.println("\n" + incadrare);
		//
		// new Panel(incadrare,PAL);

		ElementList elms = new ElementList();
		elms.addMore(100, 20, 2);
		elms.addMore(50, 20, 3);
		GuillotineCut.beginCutting(elms, new Element(200, 40));

	}
}
