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
import javax.swing.SwingConstants;
import java.awt.Component;
import javax.swing.BoxLayout;
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

	public FinalElement getIncadrare() {
		return incadrare;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}
	
	public void setIncadrare(final FinalElement incadrare) {
		
		boolean makeDraw = false;
		
		if (this.incadrare == null || incadrare.getChildrens().size() < this.incadrare.getChildrens().size()) {
			
			this.incadrare = incadrare;
			makeDraw = true;
			
		}  else if (incadrare.getChildrens().size() == this.incadrare.getChildrens().size()) {
			
			final ArrayList<Double> newLoss = incadrare.getIndividualLoss();
			final ArrayList<Double> oldLoss = this.incadrare.getIndividualLoss();
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
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(170)
					.addComponent(this.labPlaca, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(185))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(this.labPlaca)
					.addContainerGap(275, Short.MAX_VALUE))
		);
		setLayout(groupLayout);
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

}
