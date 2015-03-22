package clase;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Permutari {

	static Comparator<Placa> c = new Comparator<Placa>() {

		@Override
		public int compare(Placa o1, Placa o2) {
			if (o1.getLungime() == o2.getLungime()) {
				if (o1.getLatime() == o2.getLatime()) {
					return 0;
				} else if (o1.getLatime() > o2.getLatime()) {
					return -1;
				} else {
					return 1;
				}
			} else if (o1.getLungime() > o2.getLungime()) {
				return -1;
			} else {
				return 1;
			}
		}
	};

	public static ArrayList<ArrayList<Placa>> permuta(ArrayList<Placa> placi,
			Placa PAL) throws CloneNotSupportedException {
		placi.sort(c);
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
							if (cur.size()==0) {
								adaugaInSet(folosite, placa, cur);
								break;
								
							} else if (cur.get(0).getLatime() >= placa.getLatime()) {
								adaugaInSet(folosite, placa, cur);
								break;
							}
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

	private static void adaugaInSet(ArrayList<Placa> folosite, Placa placa,
			ArrayList<Placa> cur) {
		cur.add(placa);
		folosite.add(placa);
	}
}
