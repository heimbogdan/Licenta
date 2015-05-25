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

	@Override
	public void run() {
		final GuillotineCut gCut = new GuillotineCut(this.horizontal);
		gCut.beginCutting(this.elements, this.root);
		fin = gCut.getCutElement();
	}

	public GuillotineThread(final ElementList elementList,final Element root,final boolean h) {
		
		this.elements = elementList;
		this.root = root;
		this.horizontal = h;
	}
}
