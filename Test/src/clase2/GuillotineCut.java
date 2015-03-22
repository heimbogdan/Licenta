package clase2;

public class GuillotineCut {

	private static FinalElement CutElement;
	private static Element PAL;
	private static int level;

	public static FinalElement beginCutting(ElementList elements, Element Root) {
		CutElement = new FinalElement(Root.getLength(), Root.getWidth());
		PAL = Root;
		level = 0;
		cut(elements, Root);
		return CutElement;
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

						// vertical ---
						if (elx < rx) {
							verticalCut(elements, Root, elx, rx, ry);
							break;
							// Root.setChildrens(new ArrayList<Element>());
						}
						// orizintal ---
						if (ely < ry) {
							horizontalCut(elements, Root, ely, rx, ry);
							break;
							// Root.setChildrens(new ArrayList<Element>());
						}
						if (elx == rx && ely == ry) {
							element.setUsed(true);
							Root.setUsed(true);
							level++;
							break;
						}
					}
				}
			}
		}
		if (elements.isAllUsed()) {
			FinalElement R = FinalElement.deepCopy(PAL);
			R.setArea(R.calculateArea());
			CutElement = CutElement.getArea() < R.getArea() ? R : CutElement;
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
