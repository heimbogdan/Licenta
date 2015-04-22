package bh.w2optimize.entity;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * List of Elements
 * 
 * @author bogdan.heim
 * @version 1.0
 * @since 22.04.2015
 */
public class ElementList extends ArrayList<Element> {

	private static final long serialVersionUID = 1L;

	public static Comparator<Element> comparator = new Comparator<Element>() {

		@Override
		public int compare(Element o1, Element o2) {
			int val;
			if (o1.area() == o2.area()) {
				final double length = o1.getLength() - o2.getLength();
				final double width = o1.getWidth() - o2.getWidth();
				final double length1 = length < 0 ? length * (-1) : length;
				final double width1 = width < 0 ? width * (-1) : width;
				if (length1 > width1) {
					val = length < 0 ? 1 : -1;
				} else if (width1 > length1) {
					val = width < 0 ? 1 : -1;
				} else {
					val = 0;
				}
			} else if (o1.area() > o2.area()) {
				val = -1;
			} else {
				val = 1;
			}
			return val;
		}
	};

	/**
	 * Method that adds more elements of the same length and width
	 * 
	 * @param xVal
	 *            - Element's length
	 * @param yVal
	 *            - Element's width
	 * @param number
	 *            - The number of elements to be added
	 */
	public void addMore(final double xVal, final double yVal, final int number) {
		for (int i = 0; i < number; i++) {
			final Element element = new Element(xVal, yVal);
			this.add(element);
		}
	}

	public boolean isAllUsed() {
		boolean use = true;
		for (final Element e : this) {
			if (!e.isUsed()) {
				use = false;
			}
		}
		return use;
	}

	@Override
	public Object clone() {
		final ElementList elementList = new ElementList();
		for (final Element element : this) {
			elementList.add(element.cloneElement());
		}
		return elementList;
	}
}
