package clase;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Path2D;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import clase2.Element;
import clase2.FinalElement;

public class Draw extends JPanel implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private FinalElement incadrare;
	private int pageNumber;
	private JButton button1;
	private JButton button2;
	private JLabel labPlaca;

	public FinalElement getIncadrare() {
		return incadrare;
	}

	public void setIncadrare(FinalElement incadrare) {
		this.incadrare = incadrare;
	}

	public void drawing() {
		repaint();
	}

	public Draw() {
		super();
		this.pageNumber = 0;
		button1 = new JButton("Prev");
		button1.setBounds(320, 430, 60, 20);
		button1.addActionListener(this);
		button2 = new JButton("Next");
		button2.setBounds(420, 430, 60, 20);
		button2.addActionListener(this);
		labPlaca = new JLabel("Placa");
		labPlaca.setBounds(380, 10, 100, 20);
		this.setLayout(null);
		this.add(button1);
		this.add(button2);
		this.add(labPlaca);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		if (incadrare != null) {
			g.setColor(Color.BLACK);
			g2 = drawPlaca(this.incadrare.getChildrens().get(this.pageNumber),
					g2);
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
		path.closePath();
		g2.draw(path);
		return g2;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
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
