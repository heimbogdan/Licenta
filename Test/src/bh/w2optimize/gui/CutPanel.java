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
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.BoxLayout;
import javax.swing.border.LineBorder;

import org.eclipse.wb.swing.FocusTraversalOnArray;

import java.awt.Component;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.FlowLayout;

import javax.swing.JSplitPane;
import java.awt.ComponentOrientation;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.SystemColor;

public class CutPanel extends JPanel implements ActionListener {

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
	private JPanel panel;

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

	public CutPanel() {
		super();
		setBackground(SystemColor.inactiveCaption);
		setBorder(new LineBorder(new Color(0, 0, 0)));
		this.pageNumber = 0;
		this.nrIncadrare = 0;
		labPlaca = new JLabel("Placa " + (pageNumber + 1) + "/ incadrare "
				+ nrIncadrare);
		labPlaca.setAlignmentY(Component.TOP_ALIGNMENT);
		labPlaca.setAlignmentX(Component.CENTER_ALIGNMENT);
		labPlaca.setHorizontalAlignment(SwingConstants.CENTER);
		
		JSplitPane splitPane = new JSplitPane();
		
		panel = new JPanel();
		panel.setBackground(SystemColor.activeCaption);
		panel.setPreferredSize(new Dimension(200, 200));
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap(174, Short.MAX_VALUE)
					.addComponent(splitPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(157))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(66)
					.addComponent(labPlaca, GroupLayout.DEFAULT_SIZE, 316, Short.MAX_VALUE)
					.addGap(66))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(21)
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 403, Short.MAX_VALUE)
					.addGap(24))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(labPlaca, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(splitPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		button2 = new JButton("Next");
		splitPane.setRightComponent(button2);
		button1 = new JButton("Prev");
		splitPane.setLeftComponent(button1);
		button1.addActionListener(this);
		button2.addActionListener(this);
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
