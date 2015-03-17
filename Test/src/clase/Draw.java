package clase;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Path2D;

import javax.swing.JPanel;

public class Draw extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Path2D path = null;

	@Override
	public void paintComponents(Graphics g) {
		super.paintComponents(g);
		
		
		g.setColor(Color.BLACK);
		g.drawRect(100, 100, 50, 60);
	}

}
