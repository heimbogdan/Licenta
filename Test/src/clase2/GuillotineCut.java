package clase2;

import java.util.ArrayList;

import program.Main;

/**
 * Clasa ce contine algoritmul de taiere de tip ghilotina.
 * 
 * @author bogdan.heim
 *
 */
public class GuillotineCut {

	// static
	private FinalElement CutElement;
	private Element PAL;
	private boolean horizontal;

	// fara get/set
	public FinalElement getCutElement() {
		return CutElement;
	}

	public void setCutElement(FinalElement cutElement) {
		CutElement = cutElement;
	}

	public Element getPAL() {
		return PAL;
	}

	public void setPAL(Element pAL) {
		PAL = pAL;
	}

	public boolean isHorizontal() {
		return horizontal;
	}

	public void setHorizontal(boolean horizontal) {
		this.horizontal = horizontal;
	}

	public GuillotineCut(boolean h) {
		this.CutElement = new FinalElement(0, 0);
		this.horizontal = h;
	}

	/**
	 * Functie ce porneste procesul de executie a algoritmului.
	 * 
	 * @param elements
	 *            - Lista ce contine placiile dorite.
	 * @param Root
	 *            - Placa pe care se vor face taieturile (se va multiplica).
	 * @return Incadrarea finala ({@link FinalElement})
	 */

	public FinalElement beginCutting(ElementList elements, Element Root) {
		elements.sort(ElementList.c);
		permute(elements, Root, 1);
		return CutElement;
	}

	/**
	 * Functie ce face permutarile posibile asupra listei de placi dorita
	 * (functie recursiva).
	 * 
	 * @param elements
	 *            - Lista ce contine placiile dorite.
	 * @param Root
	 *            - Placa pe care se vor face taieturile (se va multiplica).
	 * @param k
	 *            - Indica pozitia din lista de la care sa inceapa permutarea
	 *            (de regula 0).
	 */
	private void permute(ElementList elements, Element Root, int k) {
		for (int i = k; i < elements.size(); i++) {
			java.util.Collections.swap(elements, i, k);
			permute(elements, Root, k + 1);
			java.util.Collections.swap(elements, k, i);
		}
		if (k == elements.size() - 1) {
			PAL = new Element(0, 0);
			int i = 0;
			while (!elements.isAllUsed()) {
				PAL.addRoot(new Element(Root.getLength(), Root.getWidth()));
				cut(elements, PAL.getChildrens().get(i));
				i++;
			}
			for (Element el : elements) {
				el.setUsed(false);
			}
		}
	}

	/**
	 * Functia care aplica algoritmul de tatiere de tip ghilotina.
	 * 
	 * @param elements
	 *            - Lista ce contine placiile dorite.
	 * @param Root
	 *            - Placa pe care se vor face taieturile.
	 * @return Placa initiala transmisa catre parametrul Root cu modificarile
	 *         efectuate.
	 */
	private Element cut(ElementList elements, Element Root) {
		for (int i = 0; i < elements.size(); i++) {
			Element element = elements.get(i);
			if (!element.isUsed()) {
				if (!Root.isUsed()) {
					double elx = element.getLength();
					double ely = element.getWidth();
					double rx = Root.getLength();
					double ry = Root.getWidth();
					if (elx <= rx && ely <= ry) {
						if (horizontal) { // first horizontal , then vertical
							if (ely < ry) {
								horizontalCut(elements, Root, ely, rx, ry);
								break;
							} else if (elx < rx) {
								verticalCut(elements, Root, elx, rx, ry);
								break;
							}
						} else { // first vertical , then horizontal
							if (elx < rx) {
								verticalCut(elements, Root, elx, rx, ry);
								break;
							} else if (ely < ry) {
								horizontalCut(elements, Root, ely, rx, ry);
								break;
							}
						}
						if (elx == rx && ely == ry && !Root.isUsed()) {
							element.setUsed(true);
							Root.setUsed(true);
							Root.getParent().setLoss(true);
							break;
						}
					}
				}
			}
		}
		if (elements.isAllUsed()) {
			FinalElement R = FinalElement.deepCopy(PAL);
			R.calculateArea();
			R.calculateLostArea();
			R.calculateIndividualLoss();
			if (CutElement.getChildrens().isEmpty()) {
				CutElement = R;
			} else {
				int initial = CutElement.getChildrens().size();
				int r = R.getChildrens().size();
				if (initial > r) {
					CutElement = R;
				} else if (initial == r) {
					int better = 0;
					ArrayList<Double> oldLoss = CutElement.getIndividualLoss();
					ArrayList<Double> newLoss = R.getIndividualLoss();
					for (int i = 0; i < r; i++) {
						if (oldLoss.get(i) < newLoss.get(i)) {
							break;
						}
						better++;
					}
					if (better == r) {
						CutElement = R;
					}
				}
			}
		}
		return Root;
	}

	private void horizontalCut(ElementList elements, Element Root, double ely,
			double rx, double ry) {
		Element cut1 = new Element(rx, ely);
		Element cut2 = new Element(rx, ry - ely);
		addResultElements(elements, Root, cut1, cut2, 'H');
	}

	private void verticalCut(ElementList elements, Element Root, double elx,
			double rx, double ry) {
		Element cut1 = new Element(elx, ry);
		Element cut2 = new Element(rx - elx, ry);
		addResultElements(elements, Root, cut1, cut2, 'V');
	}

	private void addResultElements(ElementList elements, Element Root,
			Element cut1, Element cut2, char p) {
		Root.setPosition(p);
		Root.addChild(cut1);
		Root.addChild(cut2);
		cut1 = cut(elements, cut1);
		cut2 = cut(elements, cut2);
	}

}
