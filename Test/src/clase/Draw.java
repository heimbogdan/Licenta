package clase;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Path2D;
import java.util.ArrayList;

import javax.swing.JPanel;

public class Draw extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Incadrare incadrare;
	private Placa PAL;

	public Incadrare getIncadrare() {
		return incadrare;
	}

	public void setIncadrare(Incadrare incadrare) {
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
			for (ArrayList<ArrayList<Placa>> root : incadrare) {
				double x = 200;
				double y = 100;
				g2.draw(drawPlaca(x, y, this.PAL, false));
				for (ArrayList<Placa> set : root) {
					Placa pl = new Placa(this.PAL.getLungime(), set.get(0)
							.getLatime());
					g2.draw(drawPlaca(x, y, pl, false));
					double x1 = x;
					for (Placa placa : set) {
						g2.draw(drawPlaca(x1, y, placa, true));
						x1 += placa.getLungime();
					}
					y += set.get(0).getLatime();
				}
				break;
			}
		}
	}

	private Path2D drawPlaca(double x, double y, Placa PAL, boolean elem) {
		Path2D path = new Path2D.Double();
		path.moveTo(x, y);
		path.lineTo(x, y + PAL.getLatime());
		path.lineTo(x + PAL.getLungime(), y + PAL.getLatime());
		path.lineTo(x + PAL.getLungime(), y);
		if (elem) {
			path.lineTo(x, y);
			path.lineTo(x + PAL.getLungime(), y + PAL.getLatime());
		}
		path.closePath();
		return path;
	}
}
