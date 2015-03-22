package clase2;

import java.util.ArrayList;
import java.util.Comparator;

public class ElementList extends ArrayList<Element> {

	private static final long serialVersionUID = 1L;
	Comparator<Element> c = new Comparator<Element>() {

		@Override
		public int compare(Element o1, Element o2) {
			if (o1.area() == o2.area()) {
				double l = o1.getLength() - o2.getLength();
				double w = o1.getWidth() - o2.getWidth();
				double l1 = l < 0 ? l * (-1) : l;
				double w1 = w < 0 ? w * (-1) : w;
				if (l1 > w1) {
					return l < 0 ? -1 : 1;
				} else if (w1 > l1) {
					return w < 0 ? -1 : 1;
				} else {
					return 0;
				}
			} else if (o1.area() > o2.area()) {
				return 1;
			} else {
				return -1;
			}
		}
	};

	public void addMore(double x, double y, int number) {
		for (int i = 0; i < number; i++) {
			Element element = new Element(x, y);
			this.add(element);
		}
	}

	public boolean isAllUsed() {
		for (Element e : this) {
			if (!e.isUsed()) {
				return false;
			}
		}
		return true;
	}
}
