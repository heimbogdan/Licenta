package bh.w2optimize.gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import bh.w2optimize.entity.FinalElement;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.LineBorder;

import java.awt.Dimension;

import javax.swing.JSplitPane;
import javax.swing.LayoutStyle.ComponentPlacement;

import com.sun.corba.se.impl.orbutil.ObjectWriter;

import java.awt.SystemColor;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

public class CutPanel extends JPanel implements ActionListener {

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
		splitPane.setRightComponent(button2);
		button1 = new JButton("Prev");
		splitPane.setLeftComponent(button1);
		button1.addActionListener(this);
		button2.addActionListener(this);
		setLayout(groupLayout);
	}

	@Override
	public void actionPerformed(final ActionEvent e) {
		if (e.getSource() == this.button2) {
			if (this.panel.getNrPlaci() == 2) {
				this.panel.getNrPlaci();
				if (this.panel.getIncadrare().getChildrens().size()  >= ((this.pageNumber+1) * 2)+2) {
					this.pageNumber += this.pageNumber == this.panel.getIncadrare()
							.getChildrens().size() - 1 ? 0 : 1;
					this.panel.setPageNumber(pageNumber);
					this.panel.drawing();
				}
			} else if (this.panel.getNrPlaci() == 1) {
				this.pageNumber += this.pageNumber == this.panel.getIncadrare()
						.getChildrens().size() - 1 ? 0 : 1;
				this.panel.setPageNumber(pageNumber);
				this.panel.drawing();
			}
		} else {
			this.pageNumber -= this.pageNumber == 0 ? 0 : 1;
			this.panel.setPageNumber(pageNumber);
			this.panel.drawing();
		}
	}
}
