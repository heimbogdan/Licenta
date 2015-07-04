package bh.w2optimize.guillotine;

import bh.w2optimize.elements.Element;
import bh.w2optimize.elements.ElementList;
import bh.w2optimize.elements.FinalElement;
import bh.w2optimize.guillotine.algorithm.GuillotineCut;

public class GuillotineThread extends Thread {
	
	private ElementList elements;
	private Element root;
	private boolean horizontal;
	private FinalElement fin;
	
	public FinalElement getFin() {
		return fin;
	}

	
	public void setElements(ElementList elements) {
		this.elements = (ElementList) elements.clone();
	}

//TODO de modificat din element in string / se va prelua codul placii
	public void setRoot(Element root) {
		this.root = root.cloneElement();
	}

	

	
	@Override
	public void run() {
		final GuillotineCut gCut = new GuillotineCut(this.horizontal);
		//TODO nu se va mai trimite un element (root) ci lista cu placiile existente in stoc probabil si codul
		gCut.beginCutting(this.elements, this.root);
		fin = gCut.getCutElement();
	}

	public GuillotineThread(final boolean h) {
		this.horizontal = h;
	}
}
