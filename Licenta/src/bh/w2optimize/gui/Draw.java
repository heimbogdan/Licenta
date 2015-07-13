package bh.w2optimize.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

import bh.w2optimize.elements.Element;
import bh.w2optimize.elements.FinalElement;
import bh.w2optimize.entity.WoodBoardPice;

import javax.swing.SwingConstants;

import java.awt.Component;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import org.apache.log4j.Logger;

public class Draw extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final static Logger log = Logger.getLogger(Draw.class);
	
	private FinalElement result;
	private int pageNumber;
	private JLabel labPlaca;
	private int resultNumber;
	private int boardNumber;
	private ArrayList<WoodBoardPice> boardList = null;
	
	public ArrayList<WoodBoardPice> getBoardList(){
		return boardList;
	}
	
	public void setNrPlaci(int nr) {
		this.boardNumber = nr;
	}

	public int getNrPlaci() {
		return this.boardNumber;
	}

	public FinalElement getIncadrare() {
		return result;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public int getNrIncadrare(){
		return this.resultNumber;
	}
	public void setIncadrare(final FinalElement incadrare, ArrayList<WoodBoardPice> boardList) {

		boolean makeDraw = false;

		if (this.result == null) {
			this.result = incadrare;
			this.boardList = boardList;
			makeDraw = true;

		} else if (incadrare.getChildrens().size() < this.result
				.getChildrens().size()) {

			this.result = incadrare;
			makeDraw = true;
		} else if (incadrare.getChildrens().size() == this.result
				.getChildrens().size()) {
			if (this.result.getUseableArea() <= incadrare.getUseableArea()) {
				if (this.result.getUseableArea() == incadrare
						.getUseableArea()) {
					if (this.result.getUseablePices() > incadrare
							.getUseablePices()) {
						this.result = incadrare;
						this.boardList = boardList;
						makeDraw = true;
					}
				} else {
					final ArrayList<Double> newLoss = incadrare
							.getIndividualLoss();
					final ArrayList<Double> oldLoss = this.result
							.getIndividualLoss();
					for (int i = 0; i < newLoss.size(); i++) {
						if (newLoss.get(i) < oldLoss.get(i)) {
							this.result = incadrare;
							this.boardList = boardList;
							makeDraw = true;
							break;
						}
					}
				}
			}
		}
		if (makeDraw) {
			this.pageNumber = 0;
			this.resultNumber++;
			this.drawing();
		}
	}

	public void resetIncadrare() {
		this.result = null;
		this.boardList =  null;
		this.pageNumber = 0;
		this.resultNumber = 0;
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
		this.resultNumber = 0;
		labPlaca = new JLabel(new StringBuilder().append("Placa ")
				.append(pageNumber + 1).append("/ incadrare ")
				.append(resultNumber).toString());
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
		if (result != null) {
			g.setColor(Color.BLACK);
			this.boardNumber = this.result.getChildrens().size() > 1 ? 2 : 1;
			if (this.boardNumber == 1) {
				g2 = drawPlaca(
						this.result.getChildrens().get(this.pageNumber), g2,
						200, 100);
				this.labPlaca.setText("Board " + (pageNumber + 1)
						+ "/ Result " + resultNumber);
			} else if (this.boardNumber == 2) {
				int num = this.pageNumber * 2;
				if (this.result.getChildrens().size() - 1 > num) {
					if (this.result.getChildrens().size() >= num + 2) {
						Element root1 = this.result.getChildrens().get(num);
						Element root2 = this.result.getChildrens().get(
								num + 1);
						g2 = drawPlaci(new Element[] { root1, root2 }, g2, 20,
								100);
						this.labPlaca.setText("Board " + (num + 1) + "-"
								+ (num + 2) + "/ Result " + resultNumber);
					}
				} else {
					g2 = drawPlaca(this.result.getChildrens().get(num), g2,
							200, 100);
					this.labPlaca.setText("Board " + (num + 1) + "/ Result "
							+ resultNumber);
				}
			}
		}
	}

	private Graphics2D drawPlaca(final Element root, Graphics2D g2,
			double xVal, double yVal) {
		Path2D path = new Path2D.Double();
		final double x = (root.getPoint().getX() / 10) + xVal;
		final double y = (root.getPoint().getY() / 10) + yVal;
		g2.setColor(Color.BLACK);
		if (root.isUsed()) {
			final Rectangle2D rect = new Rectangle2D.Double();
			rect.setRect(x, y, root.getLength() / 10, root.getWidth() / 10);
			g2.setColor(getElemColor(root.getId()%10));
			g2.fill(rect);
			g2.setColor(Color.BLACK);
			Point2D p = root.getPoint();
			g2.drawString(root.getId() + "", Float.parseFloat((x + root.getLength()/20)+ ""), 
					Float.parseFloat((y + root.getWidth()/20)+ ""));
			g2.draw(rect);
		} else {
			path.moveTo(x, y);
			if (!root.getChildrens().isEmpty()) {
				for (final Element el : root.getChildrens()) {
					g2 = drawPlaca(el, g2, xVal, yVal);
				}
			}
			path.lineTo(x, y + root.getWidth() / 10);
			path.lineTo(x + root.getLength() / 10, y + root.getWidth() / 10);
			path.lineTo(x + root.getLength() / 10, y);
			path.closePath();
			g2.draw(path);
		}
		return g2;
	}

	private Graphics2D drawPlaci(final Element[] roots, Graphics2D g2,
			double xVal, double yVal) {

		for (Element root : roots) {
			Path2D path = new Path2D.Double();
			final double x = root.getPoint().getX() / 10 + xVal;
			final double y = root.getPoint().getY() / 10 + 100;
			g2.setColor(Color.BLACK);
			if (root.isUsed()) {
				final Rectangle2D rect = new Rectangle2D.Double();
				rect.setRect(x, y, root.getLength() / 10, root.getWidth() / 10);
				g2.setColor(getElemColor(root.getId()%10));
				g2.fill(rect);
			} else {
				path.moveTo(x, y);
				if (!root.getChildrens().isEmpty()) {
					for (final Element el : root.getChildrens()) {
						g2 = drawPlaca(el, g2, xVal, yVal);
					}
				}
				path.lineTo(x, y + root.getWidth() / 10);
				path.lineTo(x + root.getLength() / 10, y + root.getWidth() / 10);
				path.lineTo(x + root.getLength() / 10, y);
				path.closePath();
				g2.draw(path);
			}
			xVal += root.getLength() / 10 + 20;
		}
		return g2;
	}
	
	private Color getElemColor(int color){
		switch (color) {
		case 1:
			return new Color(255, 51, 0);
		case 2:
			return new Color(255, 153, 51);
		case 3:
			return new Color(153, 204, 0);
		case 4:
			return new Color(0, 204, 153);
		case 5:
			return new Color(163, 71, 71);
		case 6:
			return new Color(98, 98, 184);
		case 7:
			return new Color(80, 137, 80);
		case 8:
			return new Color(230, 180, 77);
		case 9:
			return new Color(103, 203, 111);
		default:
			return new Color(102, 51, 0);
		}
	}
}
