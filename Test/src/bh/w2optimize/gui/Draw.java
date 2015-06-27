package bh.w2optimize.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

import bh.w2optimize.elements.Element;
import bh.w2optimize.elements.FinalElement;

import javax.swing.SwingConstants;

import java.awt.Component;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

public class Draw extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private FinalElement incadrare;
	private int pageNumber;
	private JLabel labPlaca;
	private int nrIncadrare;
	private int nrPlaci;

	public void setNrPlaci(int nr) {
		this.nrPlaci = nr;
	}

	public int getNrPlaci() {
		return this.nrPlaci;
	}

	public FinalElement getIncadrare() {
		return incadrare;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public int getNrIncadrare(){
		return this.nrIncadrare;
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
			if (this.incadrare.getUseableArea() <= incadrare.getUseableArea()) {
				if (this.incadrare.getUseableArea() == incadrare
						.getUseableArea()) {
					if (this.incadrare.getUseablePices() > incadrare
							.getUseablePices()) {
						this.incadrare = incadrare;
						makeDraw = true;
					}
				} else {
					final ArrayList<Double> newLoss = incadrare
							.getIndividualLoss();
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
			}
		}
		if (makeDraw) {
			this.pageNumber = 0;
			this.nrIncadrare++;
			this.drawing();
		}
	}

	public void resetIncadrare() {
		this.incadrare = null;
		this.pageNumber = 0;
		this.nrIncadrare = 0;
	}

	public void drawing() {
		repaint();
	}

	public Draw() {
		super();
		createContents();
	}

	private void createContents() {
		this.pageNumber = 0;
		this.nrIncadrare = 0;

		labPlaca = new JLabel(new StringBuilder().append("Placa ")
				.append(pageNumber + 1).append("/ incadrare ")
				.append(nrIncadrare).toString());
		this.labPlaca.setAlignmentX(Component.CENTER_ALIGNMENT);
		this.labPlaca.setHorizontalAlignment(SwingConstants.CENTER);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(
				Alignment.LEADING).addGroup(
				groupLayout
						.createSequentialGroup()
						.addGap(170)
						.addComponent(this.labPlaca, GroupLayout.DEFAULT_SIZE,
								GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGap(185)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(
				Alignment.LEADING).addGroup(
				groupLayout.createSequentialGroup().addContainerGap()
						.addComponent(this.labPlaca)
						.addContainerGap(275, Short.MAX_VALUE)));
		setLayout(groupLayout);
	}

	@Override
	protected void paintComponent(final Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		if (incadrare != null) {
			g.setColor(Color.BLACK);
			this.nrPlaci = this.incadrare.getChildrens().size() > 1 ? 2 : 1;
			if (this.nrPlaci == 1) {
				g2 = drawPlaca(
						this.incadrare.getChildrens().get(this.pageNumber), g2,
						200, 100);
				this.labPlaca.setText("Placa " + (pageNumber + 1)
						+ "/ incadrare " + nrIncadrare);
			} else if (this.nrPlaci == 2) {
				int num = this.pageNumber * 2;
				if (this.incadrare.getChildrens().size() - 1 > num) {
					if (this.incadrare.getChildrens().size() >= num + 2) {
						Element root1 = this.incadrare.getChildrens().get(num);
						Element root2 = this.incadrare.getChildrens().get(
								num + 1);
						g2 = drawPlaci(new Element[] { root1, root2 }, g2, 20,
								100);
						this.labPlaca.setText("Placa " + (num + 1) + "-"
								+ (num + 2) + "/ incadrare " + nrIncadrare);
					}
				} else {
					g2 = drawPlaca(this.incadrare.getChildrens().get(num), g2,
							200, 100);
					this.labPlaca.setText("Placa " + (num + 1) + "/ incadrare "
							+ nrIncadrare);
				}
			}
		}
	}

	private Graphics2D drawPlaca(final Element root, Graphics2D g2,
			double xVal, double yVal) {
		Path2D path = new Path2D.Double();
		final double x = root.getPoint().getX() + xVal;
		final double y = root.getPoint().getY() + yVal;
		g2.setColor(Color.BLACK);
		if (root.isUsed()) {
			final Rectangle2D rect = new Rectangle2D.Double();
			rect.setRect(x, y, root.getLength(), root.getWidth());
			g2.setColor(Color.RED);
			g2.fill(rect);
			g2.setColor(Color.BLACK);
			g2.draw(rect);
		} else {
			path.moveTo(x, y);
			if (!root.getChildrens().isEmpty()) {
				for (final Element el : root.getChildrens()) {
					g2 = drawPlaca(el, g2, xVal, yVal);
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

	private Graphics2D drawPlaci(final Element[] roots, Graphics2D g2,
			double xVal, double yVal) {

		for (Element root : roots) {
			Path2D path = new Path2D.Double();
			final double x = root.getPoint().getX() + xVal;
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
						g2 = drawPlaca(el, g2, xVal, yVal);
					}
				}
				path.lineTo(x, y + root.getWidth());
				path.lineTo(x + root.getLength(), y + root.getWidth());
				path.lineTo(x + root.getLength(), y);
				path.closePath();
				g2.draw(path);
			}
			xVal += root.getWidth() - 50;
		}
		return g2;
	}
}
