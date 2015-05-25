package bh.w2optimize.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLayeredPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import java.awt.Color;

import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.JPopupMenu;

import java.awt.Component;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;

import javax.swing.JMenuItem;

import bh.w2optimize.entity.WoodBoard;

public class EditStocks extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -776644945767968269L;
	private final JPanel contentPanel = new JPanel();
	private JTable stockTable;
	private JTextField codeTB;
	private JTextField nameTB;
	private JTextField materialTB;
	private JTextField lengthTB;
	private JTextField widthTB;
	private JTextField priceTB;
	private DefaultTableModel stockData;
	private ArrayList<WoodBoard> insertList;
	private int editIndex;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			EditStocks dialog = new EditStocks();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public EditStocks() {
		createContents();
	}
	private void createContents() {
		setModalityType(ModalityType.APPLICATION_MODAL);
		setTitle("Stocks");
		setResizable(false);
		setBounds(100, 100, 709, 425);
		getContentPane().setLayout(new BorderLayout());
		this.contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(this.contentPanel, BorderLayout.CENTER);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBorder(new LineBorder(Color.BLACK));
		
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBorder(new LineBorder(new Color(0, 0, 0)));
		GroupLayout gl_contentPanel = new GroupLayout(this.contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPanel.createSequentialGroup()
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 439, Short.MAX_VALUE)
					.addGap(18)
					.addComponent(layeredPane, GroupLayout.PREFERRED_SIZE, 226, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(scrollPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 330, Short.MAX_VALUE)
						.addComponent(layeredPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 330, Short.MAX_VALUE))
					.addContainerGap())
		);
		
		JLabel lblCode = new JLabel("Code");
		lblCode.setBounds(38, 63, 46, 14);
		layeredPane.add(lblCode);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(38, 94, 46, 14);
		layeredPane.add(lblName);
		
		JLabel lblMaterial = new JLabel("Material");
		lblMaterial.setBounds(38, 125, 46, 14);
		layeredPane.add(lblMaterial);
		
		JLabel lblLength = new JLabel("Length");
		lblLength.setBounds(38, 156, 46, 14);
		layeredPane.add(lblLength);
		
		JLabel lblWidth = new JLabel("Width");
		lblWidth.setBounds(38, 187, 46, 14);
		layeredPane.add(lblWidth);
		
		JLabel lblPrice = new JLabel("Price");
		lblPrice.setBounds(38, 218, 46, 14);
		layeredPane.add(lblPrice);
		
		this.codeTB = new JTextField();
		this.codeTB.setBounds(94, 60, 86, 20);
		layeredPane.add(this.codeTB);
		this.codeTB.setColumns(10);
		
		this.nameTB = new JTextField();
		this.nameTB.setBounds(94, 91, 86, 20);
		layeredPane.add(this.nameTB);
		this.nameTB.setColumns(10);
		
		this.materialTB = new JTextField();
		this.materialTB.setBounds(94, 122, 86, 20);
		layeredPane.add(this.materialTB);
		this.materialTB.setColumns(10);
		
		this.lengthTB = new JTextField();
		this.lengthTB.setBounds(94, 153, 86, 20);
		layeredPane.add(this.lengthTB);
		this.lengthTB.setColumns(10);
		
		this.widthTB = new JTextField();
		this.widthTB.setBounds(94, 184, 86, 20);
		layeredPane.add(this.widthTB);
		this.widthTB.setColumns(10);
		
		this.priceTB = new JTextField();
		this.priceTB.setBounds(94, 215, 86, 20);
		layeredPane.add(this.priceTB);
		this.priceTB.setColumns(10);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Object[] newWood = new Object[] { codeTB.getText(),
						nameTB.getText(), materialTB.getText(),
						Double.valueOf(lengthTB.getText()),
						Double.valueOf(widthTB.getText()),
						Double.valueOf(priceTB.getText()) };
				resetTB();
				switch (((JButton)arg0.getComponent()).getText()){
				case "Add":
					stockData.addRow(newWood);
					if (insertList == null) {
						insertList = new ArrayList<WoodBoard>();
					}
					insertList.add(new WoodBoard((String) newWood[0],
							(String) newWood[1], (String) newWood[2],
							(Double) newWood[3], (Double) newWood[4],
							(Double) newWood[5]));
					break;
				case "Save":
					// salvare
					stockData.setValueAt(newWood[0], editIndex, 0);
					stockData.setValueAt(newWood[1], editIndex, 1);
					stockData.setValueAt(newWood[2], editIndex, 2);
					stockData.setValueAt(newWood[3], editIndex, 3);
					stockData.setValueAt(newWood[4], editIndex, 4);
					stockData.setValueAt(newWood[5], editIndex, 5);
					btnAdd.setText("Add");
				default: 
					break;
						
				}
			}
		});
		btnAdd.setBounds(117, 296, 89, 23);
		layeredPane.add(btnAdd);
		
		this.stockTable = new JTable();
		this.stockTable.setFillsViewportHeight(true);
		this.stockTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Code", "Name", "Material", "Length", "Width", "Price"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class, Double.class, Double.class, Double.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		this.stockTable.getColumnModel().getColumn(0).setResizable(false);
		this.stockTable.getColumnModel().getColumn(0).setPreferredWidth(60);
		this.stockTable.getColumnModel().getColumn(1).setResizable(false);
		this.stockTable.getColumnModel().getColumn(1).setPreferredWidth(80);
		this.stockTable.getColumnModel().getColumn(2).setResizable(false);
		this.stockTable.getColumnModel().getColumn(2).setPreferredWidth(80);
		this.stockTable.getColumnModel().getColumn(3).setResizable(false);
		this.stockTable.getColumnModel().getColumn(3).setPreferredWidth(60);
		this.stockTable.getColumnModel().getColumn(4).setResizable(false);
		this.stockTable.getColumnModel().getColumn(4).setPreferredWidth(60);
		this.stockTable.getColumnModel().getColumn(5).setResizable(false);
		this.stockData = (DefaultTableModel) this.stockTable.getModel();
		scrollPane.setViewportView(this.stockTable);
		
		JPopupMenu popupMenu = new JPopupMenu();
		addPopup(this.stockTable, popupMenu);
		
		JMenuItem mntmEdit = new JMenuItem("Edit");
		mntmEdit.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				int index = stockTable.getSelectedRow();
				if(index != -1){
					editIndex = index;
					resetTB();
					Vector row = (Vector) stockData.getDataVector().get(index);
					codeTB.setText((String) row.get(0));
					nameTB.setText((String) row.get(1));
					materialTB.setText((String) row.get(2));
					lengthTB.setText(row.get(3).toString());
					widthTB.setText(row.get(4).toString());
					priceTB.setText(row.get(5).toString());
					btnAdd.setText("Save");
				}
			}
		});
		popupMenu.add(mntmEdit);
		
		JMenuItem mntmDelete = new JMenuItem("Delete");
		popupMenu.add(mntmDelete);
		this.contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Save");
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
	}
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
	
	private void resetTB(){
		String empty = "";
		this.codeTB.setText(empty);
		this.nameTB.setText(empty);
		this.materialTB.setText(empty);
		this.lengthTB.setText(empty);
		this.widthTB.setText(empty);
		this.priceTB.setText(empty);
	}
}
