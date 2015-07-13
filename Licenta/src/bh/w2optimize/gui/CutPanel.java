package bh.w2optimize.gui;

import java.awt.Color;
import java.awt.Graphics2D;

import javax.swing.JButton;
import javax.swing.JPanel;

import bh.w2optimize.db.dao.WoodBoardPiceDAO;
import bh.w2optimize.elements.FinalElement;
import bh.w2optimize.entity.WoodBoardPice;

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
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import org.apache.log4j.Logger;

public class CutPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final static Logger log = Logger.getLogger(CutPanel.class);
	
	private FinalElement incadrare;
	private int pageNumber;
	private JButton button1 = null;
	private JButton button2 = null;
	private Draw panel;
	
	public ArrayList<WoodBoardPice> getBoardList(){
		return panel.getBoardList();
	}
	
	public FinalElement getIncadrare() {
		return panel.getIncadrare();
	}

	public void setIncadrare(FinalElement incadrare, boolean horizontal, ArrayList<WoodBoardPice> boardList ) {
		panel.setIncadrare(incadrare,boardList);
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
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(51)
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
