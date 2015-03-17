package clase;

import java.util.ArrayList;

public class Aranjamente {

	public static Incadrare aranjare(ArrayList<ArrayList<Placa>> best, Placa PAL) {
		Incadrare incadrare = new Incadrare();
		ArrayList<Double> lat = new ArrayList<Double>();
		for (ArrayList<Placa> set : best) {
			double max = 0;
			for (Placa pl : set) {
				double latime = pl.getLatime();
				max = max < latime ? latime : max;
			}
			lat.add(max);
		}
		ArrayList<ArrayList<Placa>> folosit = new ArrayList<ArrayList<Placa>>();
		for (ArrayList<Placa> set : best) {
			if (!folosit.contains(set)) {
				int dim = incadrare.size();
				if (dim == 0) {
					incadrare.add(new ArrayList<ArrayList<Placa>>());
					incadrare.get(0).add(set);
				} else {
					for (int k = 0; k < dim; k++) {
						double latime = incadrare.totalLat(k);
						if (latime + lat.get(best.indexOf(set)) <= PAL
								.getLatime()) {
							incadrare.get(k).add(set);
							folosit.add(set);
							break;
						} else if (k == dim - 1 && !folosit.contains(set)) {
							incadrare.add(new ArrayList<ArrayList<Placa>>());
							incadrare.get(dim).add(set);
							folosit.add(set);
						}
					}
				}
			}
		}
		return incadrare;
	}
}
