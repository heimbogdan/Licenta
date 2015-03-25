package clase;

import javax.swing.JFrame;
import clase2.FinalElement;

public class Panel {

	private JFrame jFrame;
	private Draw draw;

	public Panel() {
		interfata(null);
	}

	public Panel(FinalElement incadrare) {
		interfata(incadrare);
	}

	private void interfata(FinalElement incadrare) {
		jFrame = new JFrame("Taie ceva :D");
		jFrame.setSize(800, 550);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		draw = new Draw();
		draw.setIncadrare(incadrare);
		jFrame.add(draw);
		jFrame.setVisible(true);
	}
}
