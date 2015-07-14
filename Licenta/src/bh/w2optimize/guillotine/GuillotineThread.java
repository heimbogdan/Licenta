package bh.w2optimize.guillotine;

import java.util.List;

import bh.w2optimize.elements.ElementList;
import bh.w2optimize.elements.FinalElement;
import bh.w2optimize.entity.WoodBoardPice;
import bh.w2optimize.guillotine.algorithm.GuillotineCut;

public class GuillotineThread extends Thread {
	
	private ElementList elements;
	private List<WoodBoardPice> roots;
	private boolean horizontal;
	private FinalElement fin;
	
	public FinalElement getFin() {
		return fin;
	}

	
	public void setElements(ElementList elements) {
		this.elements = (ElementList) elements.clone();
	}

	public void setRoots(List<WoodBoardPice> roots) {
		this.roots = roots;
	}

	

	
	@Override
	public void run() {
		final GuillotineCut gCut = new GuillotineCut(this.horizontal);
		gCut.beginCutting(this.elements, roots);
		fin = gCut.getCutElement();
	}

	public GuillotineThread(final boolean h) {
		this.horizontal = h;
	}
}
