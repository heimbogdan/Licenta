package clase;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Path2D;
import javax.swing.JPanel;

import clase2.Element;
import clase2.FinalElement;

public class Draw extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private FinalElement incadrare;
	private Placa PAL;

	public FinalElement getIncadrare() {
		return incadrare;
	}

	public void setIncadrare(FinalElement incadrare) {
		this.incadrare = incadrare;
	}

	public Placa getPAL() {
		return PAL;
	}

	public void setPAL(Placa pAL) {
		PAL = pAL;
	}

	public void drawing() {
		repaint();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		if (incadrare != null) {
			g.setColor(Color.BLACK);
			for (Element root : incadrare.getChildrens()) {
				g2 = drawPlaca(incadrare.getChildrens().get(0), g2);
			}
			// for (ArrayList<ArrayList<Placa>> root : incadrare) {
			// double x = 200;
			// double y = 100;
			// g2.draw(drawPlaca(x, y, this.PAL, false));
			// for (ArrayList<Placa> set : root) {
			// Placa pl = new Placa(this.PAL.getLungime(), set.get(0)
			// .getLatime());
			// g2.draw(drawPlaca(x, y, pl, false));
			// double x1 = x;
			// for (Placa placa : set) {
			// g2.draw(drawPlaca(x1, y, placa, true));
			// x1 += placa.getLungime();
			// }
			// y += set.get(0).getLatime();
			// }
			// break;
			// }
		}
	}

	private Graphics2D drawPlaca(Element root, Graphics2D g2) {
		Path2D path = new Path2D.Double();
		double x = root.getPoint().getX() + 200;
		double y = root.getPoint().getY() + 100;
		path.moveTo(x, y);
		if (!root.getChildrens().isEmpty()) {
			for (Element el : root.getChildrens()) {
				g2 = drawPlaca(el, g2);
			}
		}
		path.lineTo(x, y + root.getWidth());
		path.lineTo(x + root.getLength(), y + root.getWidth());
		path.lineTo(x + root.getLength(), y);
		if (root.isUsed()) {
			path.lineTo(x, y);
			path.lineTo(x + root.getLength(), y + root.getWidth());
		}
		// path.lineTo(x, y + PAL.getLatime());
		// path.lineTo(x + PAL.getLungime(), y + PAL.getLatime());
		// path.lineTo(x + PAL.getLungime(), y);
		// if (elem) {
		// path.lineTo(x, y);
		// path.lineTo(x + PAL.getLungime(), y + PAL.getLatime());
		// }
		path.closePath();
		g2.draw(path);
		return g2;
	}
}
