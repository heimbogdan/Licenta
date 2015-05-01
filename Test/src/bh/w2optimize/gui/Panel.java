package bh.w2optimize.gui;

import javax.swing.JFrame;
import bh.w2optimize.entity.FinalElement;

public class Panel {

	private JFrame jFrame;
	private CutPanel draw;

	public CutPanel getDraw() {
		return draw;
	}

	public Panel() {
		interfata(null);
	}

	public Panel(final FinalElement incadrare) {
		interfata(incadrare);
	}

	private void interfata(final FinalElement incadrare) {
		jFrame = new JFrame("Taie ceva :D");
		jFrame.setSize(800, 550);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		draw = new CutPanel();
		draw.setIncadrare(incadrare);
		jFrame.add(draw);
		jFrame.setVisible(true);
	}
}
