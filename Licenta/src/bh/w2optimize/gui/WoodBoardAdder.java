package bh.w2optimize.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.eclipse.wb.swing.FocusTraversalOnArray;

import bh.w2optimize.db.dao.WoodBoardDAO;
import bh.w2optimize.entity.WoodBoard;

import java.awt.Component;

public class WoodBoardAdder extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1573065020432656573L;
	
	private final static Logger log = Logger.getLogger(WoodBoardAdder.class);
	
	private final JPanel contentPanel = new JPanel();
	private JTextField materialTB;
	private JTextField lengthTB;
	private JTextField codeTB;
	private JTextField nameTB;
	private JTextField widthTB;
	private JTextField priceTB;
	private WoodBoardAdder _self;
	private WoodBoardBrowser parent;
	private boolean editable;
	private String editCode;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			WoodBoardAdder dialog = new WoodBoardAdder(null,false,null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			if(log.isDebugEnabled()){
				log.error(e.getMessage());
			}
		}
	}

	/**
	 * Create the dialog.
	 */
	public WoodBoardAdder(WoodBoardBrowser parent,boolean edit, String code) {
		this.parent = parent;
		this.editable = edit;
		this.editCode = code;
		createContents();
	}

	private void createContents() {
		setModalityType(ModalityType.APPLICATION_MODAL);
		_self = this;
		setTitle("Wood Board Add");
		setResizable(false);
		setBounds(100, 100, 224, 275);
		getContentPane().setLayout(new BorderLayout());
		this.contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(this.contentPanel, BorderLayout.CENTER);

		JLabel lblNewLabel = new JLabel("Code");

		JLabel lblName = new JLabel("Name");

		JLabel lblMaterial = new JLabel("Material");

		JLabel lblLength = new JLabel("Length");

		JLabel lblWidth = new JLabel("Width");

		JLabel lblPrice = new JLabel("Price");

		this.materialTB = new JTextField();
		this.materialTB.setColumns(10);

		this.lengthTB = new JTextField();
		this.lengthTB.setColumns(10);

		this.codeTB = new JTextField();
		this.codeTB.setColumns(10);

		this.nameTB = new JTextField();
		this.nameTB.setColumns(10);

		this.widthTB = new JTextField();
		this.widthTB.setColumns(10);

		this.priceTB = new JTextField();
		this.priceTB.setColumns(10);
		if(editable){
			WoodBoard board = WoodBoardDAO.getByCode(editCode);
			this.codeTB.setText(board.getCode());
			this.nameTB.setText(board.getName());
			this.materialTB.setText(board.getMaterial());
			this.lengthTB.setText(board.getLength() + "");
			this.widthTB.setText(board.getWidth() + "");
			this.priceTB.setText(board.getPrice() + "");
		}
		GroupLayout gl_contentPanel = new GroupLayout(this.contentPanel);
		gl_contentPanel
				.setHorizontalGroup(gl_contentPanel
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								gl_contentPanel
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												gl_contentPanel
														.createParallelGroup(
																Alignment.LEADING)
														.addGroup(
																gl_contentPanel
																		.createParallelGroup(
																				Alignment.TRAILING,
																				false)
																		.addGroup(
																				gl_contentPanel
																						.createSequentialGroup()
																						.addComponent(
																								lblName)
																						.addPreferredGap(
																								ComponentPlacement.RELATED,
																								GroupLayout.DEFAULT_SIZE,
																								Short.MAX_VALUE)
																						.addComponent(
																								this.nameTB,
																								GroupLayout.PREFERRED_SIZE,
																								GroupLayout.DEFAULT_SIZE,
																								GroupLayout.PREFERRED_SIZE))
																		.addGroup(
																				gl_contentPanel
																						.createSequentialGroup()
																						.addComponent(
																								lblNewLabel)
																						.addGap(27)
																						.addComponent(
																								this.codeTB,
																								GroupLayout.PREFERRED_SIZE,
																								GroupLayout.DEFAULT_SIZE,
																								GroupLayout.PREFERRED_SIZE))
																		.addGroup(
																				gl_contentPanel
																						.createSequentialGroup()
																						.addComponent(
																								lblMaterial)
																						.addPreferredGap(
																								ComponentPlacement.RELATED,
																								GroupLayout.DEFAULT_SIZE,
																								Short.MAX_VALUE)
																						.addComponent(
																								this.materialTB,
																								GroupLayout.PREFERRED_SIZE,
																								GroupLayout.DEFAULT_SIZE,
																								GroupLayout.PREFERRED_SIZE))
																		.addComponent(
																				lblLength,
																				Alignment.LEADING)
																		.addGroup(
																				gl_contentPanel
																						.createSequentialGroup()
																						.addComponent(
																								lblWidth)
																						.addPreferredGap(
																								ComponentPlacement.RELATED,
																								GroupLayout.DEFAULT_SIZE,
																								Short.MAX_VALUE)
																						.addComponent(
																								this.widthTB,
																								GroupLayout.PREFERRED_SIZE,
																								GroupLayout.DEFAULT_SIZE,
																								GroupLayout.PREFERRED_SIZE))
																		.addGroup(
																				gl_contentPanel
																						.createSequentialGroup()
																						.addComponent(
																								lblPrice)
																						.addPreferredGap(
																								ComponentPlacement.RELATED,
																								GroupLayout.DEFAULT_SIZE,
																								Short.MAX_VALUE)
																						.addComponent(
																								this.priceTB,
																								GroupLayout.PREFERRED_SIZE,
																								GroupLayout.DEFAULT_SIZE,
																								GroupLayout.PREFERRED_SIZE)))
														.addComponent(
																this.lengthTB,
																Alignment.TRAILING,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE))
										.addContainerGap(286, Short.MAX_VALUE)));
		gl_contentPanel
				.setVerticalGroup(gl_contentPanel
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								gl_contentPanel
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												gl_contentPanel
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																this.codeTB,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(
																lblNewLabel))
										.addPreferredGap(
												ComponentPlacement.UNRELATED)
										.addGroup(
												gl_contentPanel
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																this.nameTB,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(lblName))
										.addPreferredGap(
												ComponentPlacement.UNRELATED)
										.addGroup(
												gl_contentPanel
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																this.materialTB,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(
																lblMaterial))
										.addPreferredGap(
												ComponentPlacement.UNRELATED)
										.addGroup(
												gl_contentPanel
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																this.lengthTB,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(lblLength))
										.addPreferredGap(
												ComponentPlacement.UNRELATED)
										.addGroup(
												gl_contentPanel
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																this.widthTB,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(lblWidth))
										.addPreferredGap(
												ComponentPlacement.UNRELATED)
										.addGroup(
												gl_contentPanel
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																this.priceTB,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(lblPrice))
										.addContainerGap(42, Short.MAX_VALUE)));
		this.contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						if (codeTB.getText().trim().isEmpty()
								|| nameTB.getText().trim().isEmpty()
								|| materialTB.getText().trim().isEmpty()
								|| lengthTB.getText().trim().isEmpty()
								|| widthTB.getText().trim().isEmpty()
								|| priceTB.getText().trim().isEmpty()) {

							JOptionPane.showMessageDialog(_self,
									"Compleate all fields!", "Warning!",
									JOptionPane.WARNING_MESSAGE);
						} else if (WoodBoardDAO.getByCode(codeTB.getText()
								.trim()) != null) {
							if(editable){
								WoodBoard board = WoodBoardDAO.getByCode(editCode);
								board.setCode(codeTB.getText().trim());
								board.setName(nameTB.getText().trim());
								board.setMaterial(materialTB.getText().trim());
								board.setLength(Double.valueOf(lengthTB.getText().trim()));
								board.setWidth(Double.valueOf(widthTB.getText().trim()));
								board.setPrice(Double.valueOf(priceTB.getText().trim()));
								WoodBoardDAO.update(board);
								parent.loadData();
								dispose();
							}else{
								JOptionPane.showMessageDialog(_self,
										"The code you entered is allready in use!",
										"Warning!", JOptionPane.WARNING_MESSAGE);
							}
						} else {
							WoodBoard board = new WoodBoard(codeTB.getText()
									.trim(), nameTB.getText().trim(),
									materialTB.getText().trim(),
									Double.valueOf(lengthTB.getText().trim()),
									Double.valueOf(widthTB.getText().trim()),
									Double.valueOf(priceTB.getText().trim()));
							
							WoodBoardDAO.insert(board);
							parent.loadData();
							dispose();
						}
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent arg0) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[] {
				this.codeTB, this.nameTB, this.materialTB, this.lengthTB,
				this.widthTB, this.priceTB }));
	}
}
