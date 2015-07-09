package bh.w2optimize.guillotine.algorithm;

import java.util.ArrayList;
import java.util.List;

import bh.w2optimize.elements.Element;
import bh.w2optimize.elements.ElementList;
import bh.w2optimize.elements.FinalElement;
import bh.w2optimize.entity.WoodBoardPice;
import bh.w2optimize.gui.CutPanel;
import bh.w2optimize.gui.FrontInterfaceGUI;
import bh.w2optimize.guillotine.GuillotineMain;

/**
 * Clasa ce contine algoritmul de taiere de tip ghilotina.
 * 
 * @author bogdan.heim
 *
 */
public class GuillotineCut {

	private FinalElement cutElement;
	private Element pal;
	private boolean horizontal;
	private boolean keepGoing;
	private boolean boardUsed;
	private ArrayList<WoodBoardPice> boardList = null;
	
	public FinalElement getCutElement() {
		return cutElement;
	}

	public void setCutElement(final FinalElement cutElement) {
		this.cutElement = cutElement;
	}

	public Element getPAL() {
		return pal;
	}

	public void setPAL(final Element pAL) {
		pal = pAL;
	}

	public boolean isHorizontal() {
		return horizontal;
	}

	public void setHorizontal(final boolean horizontal) {
		this.horizontal = horizontal;
	}

	public GuillotineCut(final boolean h) {
		this.cutElement = new FinalElement(0, 0);
		this.horizontal = h;
		this.keepGoing = true;
	}

	/**
	 * Functie ce porneste procesul de executie a algoritmului.
	 * 
	 * @param elements
	 *            - Lista ce contine placiile dorite.
	 * @param root
	 *            - Placa pe care se vor face taieturile (se va multiplica).
	 * @return Incadrarea finala ({@link FinalElement})
	 */

	public FinalElement beginCutting(final ElementList elements,List<WoodBoardPice> list) {
		elements.sort(ElementList.comparator);
		permute(elements, list, 0);
		return cutElement;
	}

	/**
	 * Functie ce face permutarile posibile asupra listei de placi dorita
	 * (functie recursiva).
	 * 
	 * @param elements
	 *            - Lista ce contine placiile dorite.
	 * @param root
	 *            - Placa pe care se vor face taieturile (se va multiplica).
	 * @param k
	 *            - Indica pozitia din lista de la care sa inceapa permutarea
	 *            (de regula 0).
	 */
	private void permute(final ElementList elements, List<WoodBoardPice> list,
			final int k) {
		if (keepGoing) {
			GuillotineMain main = GuillotineMain.getInstance();
			synchronized (main) {
				keepGoing = !main.isStop();
			}
		}
		if (keepGoing) {
			for (int i = k; i < elements.size(); i++) {
				if (keepGoing) {
					java.util.Collections.swap(elements, i, k);
					permute(elements, list, k + 1);
					java.util.Collections.swap(elements, k, i);
					if (elements.get(i).isRotate()) {
						rotate(elements.get(i));
						java.util.Collections.swap(elements, i, k);
						permute(elements, list, k + 1);
						java.util.Collections.swap(elements, k, i);
						rotate(elements.get(i));
					}
				}
			}

			if (keepGoing) {
				if (k == elements.size()) {
					pal = new Element(0, 0, false);
					int index = 0;
					int wbId = 0;
					boardUsed = false;
					boardList = getList(list);
					boardList.sort(WoodBoardPice.comparator);
					WoodBoardPice board = null;
					while (!elements.isAllUsed()) {
						if(board == null){
							board = boardList.get(wbId);
						} else if (boardUsed) {
							board.setNumber(board.getNumber() - 1);
							if (board.getNumber() == 0) {
								wbId++;
								if (wbId < boardList.size()) {
									board = boardList.get(wbId);
								} else {
									board = boardList.get(boardList.size()-1);
									board.setNumber(board.getNumber()-1);
								}
							}
						} else {
							wbId++;
							if (wbId < boardList.size()) {
								board = boardList.get(wbId);
							} else {
								board = boardList.get(boardList.size()-1);
								board.setNumber(board.getNumber()-1);
							}
						}
						Element newRoot = board.toElement();
						newRoot.setLoss(false);
						pal.addRoot(newRoot);
						boardUsed = false;
						cut(elements, pal.getChildrens().get(index));
						index++;
					}
					if (boardUsed) {
						board.setNumber(board.getNumber()-1);
					}
					for (final Element el : elements) {
						el.setUsed(false);
					}
				}
			}
		}
	}

	/**
	 * Functia care aplica algoritmul de tatiere de tip ghilotina.
	 * 
	 * @param elements
	 *            - Lista ce contine placiile dorite.
	 * @param root
	 *            - Placa pe care se vor face taieturile.
	 * @return Placa initiala transmisa catre parametrul Root cu modificarile
	 *         efectuate.
	 */
	public Element cut(final ElementList elements, final Element root) {
		if (!root.isUsed()) {
			for (int i = 0; i < elements.size(); i++) {
				Element element = elements.get(i);
				if (!element.isUsed()) {
					final double elx = element.getLength();
					final double ely = element.getWidth();
					final double rx = root.getLength();
					final double ry = root.getWidth();
					if (elx <= rx && ely <= ry) {
						if (horizontal) { // first horizontal , then vertical
							if (ely < ry) {
								horizontalCut(elements, root, ely, rx, ry);
								break;
							} else if (elx < rx) {
								verticalCut(elements, root, elx, rx, ry);
								break;
							}
						} else { // first vertical , then horizontal
							if (elx < rx) {
								verticalCut(elements, root, elx, rx, ry);
								break;
							} else if (ely < ry) {
								horizontalCut(elements, root, ely, rx, ry);
								break;
							}
						}
						if (elx == rx && ely == ry && !root.isUsed()) {
							boardUsed = true;
							element.setUsed(true);
							root.setUsed(true);
							root.setId(element.getId());
							root.setName(element.getName());
							root.setComponentCode(element.getComponentCode());
							root.setRotate(element.isRotate());
							root.getParent().setLoss(true);
							horizontal = !horizontal;
							break;
						}
					}
				}
			}
			if (elements.isAllUsed()) {
				FinalElement newFinal = FinalElement.deepCopy(pal);
				newFinal.calculateArea();
				newFinal.calculateLostArea();
				newFinal.calculateIndividualLoss();
				CutPanel draw = FrontInterfaceGUI.panel;
				if (cutElement.getChildrens().isEmpty()) {
					synchronized (draw) {
						cutElement = newFinal;
						draw.setIncadrare(cutElement, horizontal, boardList);
					}
				} else {
					final int initial = cutElement.getChildrens().size();
					final int newFinalChildrens = newFinal.getChildrens()
							.size();
					if (initial > newFinalChildrens) {
						synchronized (draw) {
							cutElement = newFinal;
							draw.setIncadrare(cutElement, horizontal, boardList);
						}
					} else if (initial == newFinalChildrens) {
						int better = 0;
						final ArrayList<Double> oldLoss = cutElement
								.getIndividualLoss();
						final ArrayList<Double> newLoss = newFinal
								.getIndividualLoss();
						for (int i = 0; i < newFinalChildrens; i++) {
							if (oldLoss.get(i) < newLoss.get(i)) {
								break;
							}
							better++;
						}
						if (better == newFinalChildrens
								&& newFinal.getUseableArea() >= cutElement
										.getUseableArea()) {
							if (newFinal.getUseableArea() == cutElement
									.getUseableArea()) {
								if (newFinal.getUseablePices() <= cutElement
										.getUseablePices()) {
									synchronized (draw) {
										cutElement = newFinal;
										draw.setIncadrare(cutElement, horizontal, boardList);
									}
								}
							} else {
								synchronized (draw) {
									cutElement = newFinal;
									draw.setIncadrare(cutElement, horizontal, boardList);
								}
							}
						}
					}
				}
			}
		}
		return root;
	}

	private void horizontalCut(final ElementList elements, final Element root,
			final double ely, final double rx, final double ry) {
		final Element cut1 = new Element(rx, ely, false);
		final Element cut2 = new Element(rx, ry - ely, false);
		addResultElements(elements, root, cut1, cut2, 'H');
	}

	private void verticalCut(final ElementList elements, final Element Root,
			final double elx, final double rx, final double ry) {
		final Element cut1 = new Element(elx, ry, false);
		final Element cut2 = new Element(rx - elx, ry, false);
		addResultElements(elements, Root, cut1, cut2, 'V');
	}

	private void addResultElements(final ElementList elements,
			final Element root, Element cut1, Element cut2, final char p) {
		root.setPosition(p);
		root.addChild(cut1);
		root.addChild(cut2);
		cut1 = cut(elements, cut1);
		cut2 = cut(elements, cut2);
	}

	public void rotate(Element el) {
		double elength = el.getLength();
		double ewidth = el.getWidth();
		el.setLength(ewidth);
		el.setWidth(elength);
	}
	
	private ArrayList<WoodBoardPice> getList(List<WoodBoardPice> list){
		ArrayList<WoodBoardPice> newList = new ArrayList<WoodBoardPice>();
		for(WoodBoardPice w : list){
			WoodBoardPice newW = new WoodBoardPice(w.getCode(), w.getName(), w.getMaterial(), w.getLength(), 
					w.getWidth(), w.getPrice(), w.getNumber());
			newW.setId(w.getId());
			newList.add(newW);
		}
		return newList;
	}
}
