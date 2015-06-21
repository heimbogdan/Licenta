package bh.w2optimize.gui;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JPanel;

import bh.w2optimize.entity.FinalElement;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.LineBorder;

import java.awt.Dimension;

import javax.swing.JSplitPane;
import javax.swing.LayoutStyle.ComponentPlacement;

import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigInteger;

import javax.swing.JProgressBar;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class CutPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private FinalElement incadrare;
	private int pageNumber;
	private int permNumber1;
	private int permNumber2;
	private BigInteger totalPerm;
	private JButton button1 = null;
	private JButton button2 = null;
	private Draw panel;
	private JLabel percentDone;
	
	public FinalElement getIncadrare() {
		return incadrare;
	}

	public void setIncadrare(FinalElement incadrare, int perm, boolean horizontal ) {
		if (incadrare != null) {
			if (horizontal) {
				this.permNumber1 = perm;
			} else {
				this.permNumber2 = perm;
			}
		} else {
			this.permNumber1 = 0;
			this.permNumber2 = 0;
		}
		int value = new BigInteger((permNumber1 + permNumber2) + "").divide(totalPerm).multiply(new BigInteger(10 + "")).intValue();
		percentDone.setText((new Float(value)/10) + "%");
		panel.setIncadrare(incadrare);
		this.repaint();
	}

	public void resetIncadrare(){
		this.panel.resetIncadrare();
	}
	
	public void drawing() {
		repaint();
	}

	public CutPanel() {
		super();
		createContents();
	}

	public int getNrIncadrare(){
		return this.panel.getNrIncadrare();
	}
	
	public void setTotalPerm(BigInteger totalPerm){
		this.totalPerm = totalPerm;
	}
	
	private void createContents() {
		setMinimumSize(new Dimension(500, 500));
		setBackground(SystemColor.inactiveCaption);
		setBorder(new LineBorder(new Color(0, 0, 0)));
		this.pageNumber = 0;
		this.permNumber1 = 0;
		this.permNumber2 = 0;
		JSplitPane splitPane = new JSplitPane();

		panel = new Draw();
		panel.setBackground(SystemColor.activeCaption);
		panel.setPreferredSize(new Dimension(200, 200));
		
		percentDone = new JLabel("0%");
		percentDone.setHorizontalAlignment(SwingConstants.RIGHT);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(21)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(splitPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(157))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(this.panel, GroupLayout.PREFERRED_SIZE, 417, Short.MAX_VALUE)
							.addContainerGap())))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(397, Short.MAX_VALUE)
					.addComponent(percentDone, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(percentDone)
					.addGap(26)
					.addComponent(this.panel, GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(splitPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		button2 = new JButton("Next");
		button2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int aux = panel.getNrPlaci();
				if (aux == 2) {
					if (panel.getIncadrare().getChildrens().size()  >= ((pageNumber+1) * 2)+1) {
						pageNumber += pageNumber == panel.getIncadrare()
								.getChildrens().size() - 1 ? 0 : 1;
						panel.setPageNumber(pageNumber);
						panel.drawing();
					}
				} else {
					pageNumber += pageNumber == panel.getIncadrare()
							.getChildrens().size() - 1 ? 0 : 1;
					panel.setPageNumber(pageNumber);
					panel.drawing();
				}
			}
		});
		splitPane.setRightComponent(button2);
		button1 = new JButton("Prev");
		button1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				pageNumber -= pageNumber == 0 ? 0 : 1;
				panel.setPageNumber(pageNumber);
				panel.drawing();
			}
		});
		splitPane.setLeftComponent(button1);
		setLayout(groupLayout);
	}
}
