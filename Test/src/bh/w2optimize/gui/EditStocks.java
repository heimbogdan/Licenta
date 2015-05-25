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
import javax.swing.JFormattedTextField;
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
import java.text.Format;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

import javax.swing.JMenuItem;

import bh.w2optimize.db.dao.WoodBoardDAO;
import bh.w2optimize.db.dao.WoodBoardPiceDAO;
import bh.w2optimize.entity.WoodBoard;
import bh.w2optimize.entity.WoodBoardPice;

import javax.swing.JComboBox;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class EditStocks extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -776644945767968269L;
	private final JPanel contentPanel = new JPanel();
	private JTable stockTable;
	private JTextField nameTB;
	private JTextField materialTB;
	private JTextField lengthTB;
	private JTextField widthTB;
	private JTextField priceTB;
	private DefaultTableModel stockData;
	private int editIndex;
	private List<WoodBoardPice> list;
	private List<WoodBoard> listWood;
	private JComboBox<String> codeCB;
	private JTextField numberTB;
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
		
		this.nameTB = new JTextField();
		this.nameTB.setEditable(false);
		this.nameTB.setBounds(94, 91, 112, 20);
		layeredPane.add(this.nameTB);
		this.nameTB.setColumns(10);
		
		this.materialTB = new JTextField();
		this.materialTB.setEditable(false);
		this.materialTB.setBounds(94, 122, 112, 20);
		layeredPane.add(this.materialTB);
		this.materialTB.setColumns(10);
		
		this.lengthTB = new JFormattedTextField(NumberFormat.getNumberInstance());
		this.lengthTB.setBounds(94, 153, 112, 20);
		layeredPane.add(this.lengthTB);
		this.lengthTB.setColumns(10);
		
		this.widthTB = new JFormattedTextField(NumberFormat.getNumberInstance());
		this.widthTB.setBounds(94, 184, 112, 20);
		layeredPane.add(this.widthTB);
		this.widthTB.setColumns(10);
		
		this.priceTB = new JTextField();
		this.priceTB.setText("0");
		this.priceTB.setEditable(false);
		this.priceTB.setBounds(94, 215, 112, 20);
		layeredPane.add(this.priceTB);
		this.priceTB.setColumns(10);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Object[] newWood = new Object[] { codeCB.getSelectedItem().toString(),
						nameTB.getText(), materialTB.getText(),
						Double.valueOf(lengthTB.getText()),
						Double.valueOf(widthTB.getText()),
						Double.valueOf(priceTB.getText()), Integer.parseInt(numberTB.getText()) };
				resetTB();
				switch (((JButton)arg0.getComponent()).getText()){
				case "Add":
					stockData.addRow(newWood);
					WoodBoardPiceDAO.insert(new WoodBoardPice((String) newWood[0],
							(String) newWood[1], (String) newWood[2],
							(Double) newWood[3], (Double) newWood[4],
							(Double) newWood[5], (Integer) newWood[6]));
					loadData();
					break;
				case "Save":
					stockData.setValueAt(newWood[0], editIndex, 0);
					stockData.setValueAt(newWood[1], editIndex, 1);
					stockData.setValueAt(newWood[2], editIndex, 2);
					stockData.setValueAt(newWood[3], editIndex, 3);
					stockData.setValueAt(newWood[4], editIndex, 4);
					stockData.setValueAt(newWood[5], editIndex, 5);
					btnAdd.setText("Add");
					WoodBoardPice board = list.get(editIndex);
					board.setLength((Double) newWood[3]);
					board.setWidth((Double) newWood[4]);
					board.setPrice((Double) newWood[5]);
					board.setNumber((Integer) newWood[6]);
					WoodBoardPiceDAO.update(board);
					loadData();
					break;
				default: 
					break;
				}
			}
		});
		btnAdd.setBounds(18, 297, 89, 23);
		layeredPane.add(btnAdd);
		
		codeCB = new JComboBox<String>();
		this.codeCB.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if (arg0.getStateChange() == ItemEvent.SELECTED) {
			          Object item = arg0.getItem();
			          int index = codeCB.getSelectedIndex();
			          nameTB.setText(listWood.get(index).getName());
			          materialTB.setText(listWood.get(index).getMaterial());
				}
			}
		});
		codeCB.setBounds(94, 60, 112, 20);
		listWood = WoodBoardDAO.getAll();
		for (WoodBoard board : listWood) {
			codeCB.addItem(board.getCode());
		}
		
		layeredPane.add(codeCB);
		
		this.numberTB = new JFormattedTextField(NumberFormat.getNumberInstance());
		this.numberTB.setBounds(94, 246, 112, 20);
		layeredPane.add(this.numberTB);
		this.numberTB.setColumns(10);
		
		JLabel lblNumber = new JLabel("Number");
		lblNumber.setBounds(38, 249, 46, 14);
		layeredPane.add(lblNumber);
		
		JButton btnGetPrice = new JButton("Get Price");
		btnGetPrice.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				calculatePrice();
			}
		});
		btnGetPrice.setBounds(117, 297, 89, 23);
		layeredPane.add(btnGetPrice);
		
		this.stockTable = new JTable();
		this.stockTable.setFillsViewportHeight(true);
		this.stockTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Code", "Name", "Material", "Length", "Width", "Price", "Number"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class, Double.class, Double.class, Double.class, Integer.class
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
		this.stockTable.getColumnModel().getColumn(6).setResizable(false);
		this.stockData = (DefaultTableModel) this.stockTable.getModel();
		loadData();
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
					codeCB.setSelectedItem((String) row.get(0));
					nameTB.setText((String) row.get(1));
					materialTB.setText((String) row.get(2));
					lengthTB.setText(row.get(3).toString());
					widthTB.setText(row.get(4).toString());
					priceTB.setText(row.get(5).toString());
					numberTB.setText(row.get(6).toString());
					btnAdd.setText("Save");
				}
			}
		});
		popupMenu.add(mntmEdit);
		
		JMenuItem mntmDelete = new JMenuItem("Delete");
		mntmDelete.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				int index = stockTable.getSelectedRow();
				if(index != -1){
					WoodBoardPice board = list.get(index);
					list.remove(board);
					WoodBoardPiceDAO.delete(board);
					loadData();
				}
			}
		});
		popupMenu.add(mntmDelete);
		this.contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent arg0) {
						dispose();
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
		this.codeCB.setSelectedIndex(-1);
		this.nameTB.setText(empty);
		this.materialTB.setText(empty);
		this.lengthTB.setText(empty);
		this.widthTB.setText(empty);
		this.priceTB.setText("0");
		this.numberTB.setText(empty);
	}
	
	private void loadData(){
		list = WoodBoardPiceDAO.getAll();
		if(list != null){
			while(stockData.getRowCount() != 0){
				stockData.removeRow(0);
			}
			for(WoodBoardPice board : list){
				stockData.addRow(new Object[] {board.getCode(),board.getName(),board.getMaterial(),board.getLength(),board.getWidth(),board.getPrice(),board.getNumber()});
			}
		}
	}
	
	private void calculatePrice(){
		int i = codeCB.getSelectedIndex();
		if(i != -1){
			WoodBoard board = listWood.get(i);
			double basePrice = board.getPrice();
			double squareCM = basePrice / (board.getLength()* board.getWidth());
			Double price = Double.valueOf(lengthTB.getText().trim()) * Double.valueOf(widthTB.getText().trim()) * squareCM;
			priceTB.setText(price.toString());
		}
	}
}
