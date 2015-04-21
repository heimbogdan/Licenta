package bh.w2optimize.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import bh.w2optimize.entity.Element;
import bh.w2optimize.entity.FinalElement;

public class Draw extends JPanel implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private FinalElement incadrare;
	private int pageNumber;
	private final JButton button1;
	private final JButton button2;
	private final JLabel labPlaca;
	private int nrIncadrare;

	public FinalElement getIncadrare() {
		return incadrare;
	}

	public void setIncadrare(final FinalElement incadrare) {
		boolean makeDraw = false;
		if (this.incadrare == null) {
			this.incadrare = incadrare;
			makeDraw = true;
		} else if (incadrare.getChildrens().size() < this.incadrare
				.getChildrens().size()) {
			this.incadrare = incadrare;
			makeDraw = true;
		} else if (incadrare.getChildrens().size() == this.incadrare
				.getChildrens().size()) {
			final ArrayList<Double> newLoss = incadrare.getIndividualLoss();
			final ArrayList<Double> oldLoss = this.incadrare
					.getIndividualLoss();
			for (int i = 0; i < newLoss.size(); i++) {
				if (newLoss.get(i) < oldLoss.get(i)) {
					this.incadrare = incadrare;
					makeDraw = true;
					break;
				}
			}
		}
		if (makeDraw) {
			this.pageNumber = 0;
			this.nrIncadrare++;
			this.drawing();
		}
	}

	public void drawing() {
		repaint();
	}

	public Draw() {
		super();
		this.pageNumber = 0;
		this.nrIncadrare = 0;
		button1 = new JButton("Prev");
		button1.setBounds(320, 430, 60, 20);
		button1.addActionListener(this);
		button2 = new JButton("Next");
		button2.setBounds(420, 430, 60, 20);
		button2.addActionListener(this);
		labPlaca = new JLabel("Placa " + (pageNumber + 1) + "/ incadrare "
				+ nrIncadrare);
		labPlaca.setBounds(300, 10, 200, 20);
		this.setLayout(null);
		this.add(button1);
		this.add(button2);
		this.add(labPlaca);
	}

	@Override
	protected void paintComponent(final Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		if (incadrare != null) {
			g.setColor(Color.BLACK);
			g2 = drawPlaca(this.incadrare.getChildrens().get(this.pageNumber),
					g2);
		}
		this.labPlaca.setText("Placa " + (pageNumber + 1) + "/ incadrare "
				+ nrIncadrare);
	}

	private Graphics2D drawPlaca(final Element root, Graphics2D g2) {
		Path2D path = new Path2D.Double();
		final double x = root.getPoint().getX() + 200;
		final double y = root.getPoint().getY() + 100;
		g2.setColor(Color.BLACK);
		if (root.isUsed()) {
			final Rectangle2D rect = new Rectangle2D.Double();
			rect.setRect(x, y, root.getLength(), root.getWidth());
			g2.setColor(Color.RED);
			g2.fill(rect);
		} else {
			path.moveTo(x, y);
			if (!root.getChildrens().isEmpty()) {
				for (final Element el : root.getChildrens()) {
					g2 = drawPlaca(el, g2);
				}
			}
			path.lineTo(x, y + root.getWidth());
			path.lineTo(x + root.getLength(), y + root.getWidth());
			path.lineTo(x + root.getLength(), y);
			path.closePath();
			g2.draw(path);
		}
		return g2;
	}

	@Override
	public void actionPerformed(final ActionEvent e) {
		if (e.getSource() == this.button2) {
			this.pageNumber += this.pageNumber == this.incadrare.getChildrens()
					.size() - 1 ? 0 : 1;
			this.drawing();
		} else {
			this.pageNumber -= this.pageNumber == 0 ? 0 : 1;
			this.drawing();
		}
	}
}
