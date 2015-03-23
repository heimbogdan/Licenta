package clase2;

public class GuillotineCut {

	private static FinalElement CutElement;
	private static Element PAL;
	private static int level;
	private static boolean horizontal;

	public static FinalElement beginCutting(ElementList elements, Element Root) {
		CutElement = new FinalElement(0, 0);
		horizontal = false;
		permute(elements, Root, 0);
		horizontal = true;
		permute(elements, Root, 0);
		return CutElement;
	}

	public static void permute(ElementList elements, Element Root, int k) {
		for (int i = k; i < elements.size(); i++) {
			java.util.Collections.swap(elements, i, k);
			permute(elements, Root, k + 1);
			java.util.Collections.swap(elements, k, i);
		}
		if (k == elements.size() - 1) {
			PAL = new Element(0, 0);
			level = 0;
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

	public static Element cut(ElementList elements, Element Root) {
		for (int i = 0; i < elements.size(); i++) {
			Element element = elements.get(i);
			if (!element.isUsed()) {
				if (!Root.isUsed()) {
					double elx = element.getLength();
					double ely = element.getWidth();
					double rx = Root.getLength();
					double ry = Root.getWidth();
					if (elx <= rx && ely <= ry) {

						if (horizontal) {
							// orizintal ---
							if (ely < ry) {
								horizontalCut(elements, Root, ely, rx, ry);
								break;
							}
							// vertical ---
							if (elx < rx) {
								verticalCut(elements, Root, elx, rx, ry);
								break;
							}
						} else {
							// vertical ---
							if (elx < rx) {
								verticalCut(elements, Root, elx, rx, ry);
								break;
							}
							// orizintal ---
							if (ely < ry) {
								horizontalCut(elements, Root, ely, rx, ry);
								break;
							}
						}
						if (elx == rx && ely == ry) {
							element.setUsed(true);
							Root.setUsed(true);
							Root.getParent().setLoss(true);
							level++;
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
			if (CutElement.getChildrens().isEmpty()) {
				CutElement = R;
			} else {
				if (CutElement.getChildrens().size() <= R.getChildrens().size()) {
					CutElement = CutElement.getUsebleArea() < R.getUsebleArea() ? R
							: CutElement;
				}
			}
		}
		return Root;
	}

	private static void horizontalCut(ElementList elements, Element Root,
			double ely, double rx, double ry) {
		Element cut1 = new Element(rx, ely);
		Element cut2 = new Element(rx, ry - ely);
		addResultElements(elements, Root, cut1, cut2, 'H');
	}

	private static void verticalCut(ElementList elements, Element Root,
			double elx, double rx, double ry) {
		Element cut1 = new Element(elx, ry);
		Element cut2 = new Element(rx - elx, ry);
		addResultElements(elements, Root, cut1, cut2, 'V');
	}

	private static void addResultElements(ElementList elements, Element Root,
			Element cut1, Element cut2, char p) {
		Root.setPosition(p);
		Root.addChild(cut1);
		Root.addChild(cut2);
		cut1 = cut(elements, cut1);
		cut2 = cut(elements, cut2);
	}

}
