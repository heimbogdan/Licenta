package bh.w2optimize.guillotine;

import bh.w2optimize.entity.Element;
import bh.w2optimize.entity.ElementList;
import bh.w2optimize.entity.FinalElement;
import bh.w2optimize.guillotine.algorithm.GuillotineCut;

public class GuillotineThread extends Thread {
	
	private ElementList elements;
	private Element root;
	private boolean horizaontal;


	@Override
	public void run() {
		final GuillotineCut gCut = new GuillotineCut(this.horizaontal);
		gCut.beginCutting(this.elements, this.root);
		gCut.getCutElement();
	}

	public GuillotineThread(final ElementList elementList,final Element root,final boolean h) {
		
		this.elements = elementList;
		this.root = root;
		this.horizaontal = h;
	}
}
