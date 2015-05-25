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

public class CutPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private FinalElement incadrare;
	private int pageNumber;
	private JButton button1 = null;
	private JButton button2 = null;
	private Draw panel;
	public FinalElement getIncadrare() {
		return incadrare;
	}

	public void setIncadrare(final FinalElement incadrare) {
		this.panel.setIncadrare(incadrare);
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

	private void createContents() {
		setMinimumSize(new Dimension(500, 500));
		setBackground(SystemColor.inactiveCaption);
		setBorder(new LineBorder(new Color(0, 0, 0)));
		this.pageNumber = 0;
		JSplitPane splitPane = new JSplitPane();

		panel = new Draw();
		panel.setBackground(SystemColor.activeCaption);
		panel.setPreferredSize(new Dimension(200, 200));
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(21)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addComponent(splitPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(157))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(this.panel, GroupLayout.PREFERRED_SIZE, 417, Short.MAX_VALUE)
							.addContainerGap())))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(48)
					.addComponent(this.panel, GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(splitPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		button2 = new JButton("Next");
		button2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (panel.getNrPlaci() == 2) {
					panel.getNrPlaci();
					if (panel.getIncadrare().getChildrens().size()  >= ((pageNumber+1) * 2)+2) {
						pageNumber += pageNumber == panel.getIncadrare()
								.getChildrens().size() - 1 ? 0 : 1;
						panel.setPageNumber(pageNumber);
						panel.drawing();
					}
				} else if (panel.getNrPlaci() == 1) {
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
