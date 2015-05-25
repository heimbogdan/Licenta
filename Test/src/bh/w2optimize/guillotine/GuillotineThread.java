package bh.w2optimize.guillotine;

import bh.w2optimize.entity.Element;
import bh.w2optimize.entity.ElementList;
import bh.w2optimize.entity.FinalElement;
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


	public void setRoot(Element root) {
		this.root = root.cloneElement();
	}

	

	
	@Override
	public void run() {
		final GuillotineCut gCut = new GuillotineCut(this.horizontal);
		gCut.beginCutting(this.elements, this.root);
		fin = gCut.getCutElement();
	}

	public GuillotineThread(final boolean h) {
		this.horizontal = h;
	}
}
