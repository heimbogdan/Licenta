package clase;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Panel {

	private JFrame jFrame;
	private JPanel jPanel;
	private JButton button1;
	private JButton button2;
	private JLabel labPlaca;
	private Draw dr;

	public Panel() {
		interfata(null);
	}

	public Panel(Incadrare incadrare) {
		interfata(incadrare);
	}

	private void interfata(Incadrare incadrare) {
		jFrame = new JFrame("Taie ceva :D");
		jFrame.setSize(800, 550);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// ------------------------------------
		jPanel = new JPanel();

		button1 = new JButton("Prev");
		button1.setBounds(320, 430, 60, 20);

		button2 = new JButton("Next");
		button2.setBounds(420, 430, 60, 20);

		labPlaca = new JLabel("Placa");
		labPlaca.setBounds(380, 10, 100, 20);

		dr = new Draw();
		dr.setBounds(100, 40, 600, 380);
		dr.setBackground(Color.WHITE);

		jPanel.setLayout(null);
		jPanel.add(button1);
		jPanel.add(button2);
		jPanel.add(labPlaca);
		jPanel.add(dr);
		// -------------------------------------
		
		jFrame.add(jPanel);
		jFrame.setVisible(true);
	}
}
