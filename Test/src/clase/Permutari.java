package clase;

import java.util.ArrayList;
import java.util.List;

public class Permutari {

	public static void perm(ArrayList<Integer> numere, int poz,
			ArrayList<Integer> p) {
		if (p == null) {
			p = new ArrayList<Integer>();
		}
		for (int i = 0; i < numere.size(); i++) {
			if (!p.contains(numere.get(i))) {
				p.add(numere.get(i));
				perm(numere, (poz + 1), p);
				p.remove(p.size() - 1);
			}
		}
		if (p.size() == numere.size()) {
			System.out.println(p.toString());
		}
	}

	public static ArrayList<ArrayList<Placa>> permuta(ArrayList<Placa> placi,
			Placa PAL) throws CloneNotSupportedException {
		ArrayList<ArrayList<Placa>> best = new ArrayList<ArrayList<Placa>>();
		int x = placi.size() / 10 + 1;
		for (int i = 0; i < x; i++) {
			if (i == x - 1) {
				best = permPlaci(placi.subList(i * 10, placi.size()), 0, best,
						null, PAL, null);
			} else {
				best = permPlaci(placi.subList(i * 10, (i + 1) * 10), 0, best,
						null, PAL, null);
			}
		}
		return best;
	}

	public static ArrayList<ArrayList<Placa>> permPlaci(List<Placa> placi,
			int poz, ArrayList<ArrayList<Placa>> incadrari,
			ArrayList<ArrayList<Placa>> Best, Placa PAL,
			ArrayList<Placa> folosite) throws CloneNotSupportedException {
		if (incadrari == null) {
			incadrari = new ArrayList<ArrayList<Placa>>();
		}
		if (Best == null) {
			Best = new ArrayList<ArrayList<Placa>>();
		}
		if (folosite == null) {
			folosite = new ArrayList<Placa>();
		}
		for (Placa placa : placi) {
			if (!folosite.contains(placa)) {
				if (incadrari.size() == 0) {
					incadrari.add(new ArrayList<Placa>());
				}
				for (int i = 0; i < incadrari.size(); i++) {
					if (!folosite.contains(placa)) {
						ArrayList<Placa> cur = incadrari.get(i);
						double L = 0;
						for (Placa pl : cur) {
							L += pl.getLungime();
						}
						if (L + placa.getLungime() <= PAL.getLungime()) {
							cur.add(placa);
							folosite.add(placa);
							break;
						}
						if (i == incadrari.size() - 1) {
							incadrari.add(new ArrayList<Placa>());
							incadrari.get(i + 1).add(placa);
							folosite.add(placa);
						}
					}
				}
				Best = permPlaci(placi, (poz + 1), incadrari, Best, PAL,
						folosite);
				folosite.remove(folosite.size() - 1);
				for (ArrayList<Placa> set : incadrari) {
					if (set.contains(placa)) {
						if (set.size() == 1) {
							incadrari.remove(incadrari.indexOf(set));
						} else {
							set.remove(set.indexOf(placa));
						}
						break;
					}
				}
			}
		}
		if (folosite.size() == placi.size()) {
			double rest = -1;
			double aux = 0;
			for (ArrayList<Placa> set : Best) {
				double sum = 0;
				for (Placa pl : set) {
					sum += pl.getLungime();
				}
				rest += PAL.getLungime() - sum;
			}
			for (ArrayList<Placa> list : incadrari) {
				double sum = 0;
				for (Placa pl : list) {
					sum += pl.getLungime();
				}
				aux += PAL.getLungime() - sum;
			}
			rest = rest != -1 ? ++rest : rest;
			if (aux < rest || rest == -1) {
				int index = 0;
				Best.clear();
				Best = new ArrayList<ArrayList<Placa>>();
				for (ArrayList<Placa> set : incadrari) {
					Best.add(new ArrayList<Placa>());
					for (Placa pl : set) {
						Best.get(index).add((Placa) pl.clone());
					}
					index++;
				}
			}
		}
		return Best;
	}
}
