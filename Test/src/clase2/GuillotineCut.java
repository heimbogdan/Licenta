package clase2;

import java.util.ArrayList;

public class GuillotineCut {

	public static ArrayList<Element> execute(ElementList elements,
			Element Root, ArrayList<Element> results) {
		elements.sort(elements.c);
		if (results == null) {
			results = new ArrayList<Element>();
			results.add(Root);
		}
		
		for (Element element : elements) {
			for (Element R : results) {
				if (element.getLength() <= R.getLength()
						&& element.getWidth() <= R.getWidth()
						&& !element.isUsed()) {
					
					R.addChild(element);
					
				}
			}
		}
		return null;
	}
}
