package clase;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Panel {

	private JFrame jFrame;
	private Draw draw;
	private JButton button1;
	private JButton button2;
	private JLabel labPlaca;

	public Panel() {
		interfata(null,null);
	}

	public Panel(Incadrare incadrare, Placa PAL) {
		interfata(incadrare, PAL);
	}

	private void interfata(Incadrare incadrare, Placa PAL) {
		jFrame = new JFrame("Taie ceva :D");
		jFrame.setSize(800, 550);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// ------------------------------------
		draw = new Draw();
		draw.setIncadrare(incadrare);
		draw.setPAL(PAL);
		
		
		button1 = new JButton("Prev");
		button1.setBounds(320, 430, 60, 20);

		button2 = new JButton("Next");
		button2.setBounds(420, 430, 60, 20);

		labPlaca = new JLabel("Placa");
		labPlaca.setBounds(380, 10, 100, 20);

		draw.setLayout(null);
		draw.add(button1);
		draw.add(button2);
		draw.add(labPlaca);
		// -------------------------------------

		jFrame.add(draw);
		jFrame.setVisible(true);
	}
}
