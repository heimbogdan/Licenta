package bh.w2optimize.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.util.Vector;

import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JPopupMenu;


import javax.swing.JMenuItem;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import bh.w2optimize.db.dao.ComponentDAO;
import bh.w2optimize.db.dao.GeneralComponentDAO;
import bh.w2optimize.elements.Element;
import bh.w2optimize.elements.ElementList;
import bh.w2optimize.elements.GeneralElement;
import bh.w2optimize.entity.Component;
import bh.w2optimize.entity.GeneralComponent;

public class ComponentAdder extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2682565383816020073L;
	
	private final static Logger log = Logger.getLogger(ComponentAdder.class);
	
	private final JPanel contentPanel = new JPanel();
	private JTextField codeTB;
	private JTextField nameTb;
	private JTextField lengthTB;
	private JTextField widthTB;
	private JTextField heightTB;
	private JTable table;
	private DefaultTableModel modelComp;
	private DefaultTableModel modelGenComp;
	private ComponentAdder _self;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ComponentAdder dialog = new ComponentAdder();
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
	public ComponentAdder() {
		createContents();
	}
	@SuppressWarnings("serial")
	private void createContents() {
		_self = this;
		setResizable(false);
		setModalityType(ModalityType.APPLICATION_MODAL);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setTitle("Component Creator");
		setBounds(100, 100, 753, 420);
		getContentPane().setLayout(new BorderLayout());
		this.contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(this.contentPanel, BorderLayout.CENTER);
		
		JCheckBox chckbxGeneralComponent = new JCheckBox("General Component");
		chckbxGeneralComponent.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if (arg0.getStateChange() == ItemEvent.SELECTED) {
					setEnableGenComp(true);
				} else if (arg0.getStateChange() == ItemEvent.DESELECTED){
					setEnableGenComp(false);
				}
			}
		});
		this.codeTB = new JTextField();
		this.codeTB.setColumns(10);
		
		JLabel lblCode = new JLabel("Code");
		
		JLabel lblName = new JLabel("Name");
		
		this.nameTb = new JTextField();
		this.nameTb.setColumns(10);
		
		this.lengthTB = new JTextField();
		this.lengthTB.setEnabled(false);
		this.lengthTB.setEditable(false);
		this.lengthTB.setColumns(10);
		
		JLabel lblLength = new JLabel("Length");
		
		this.widthTB = new JTextField();
		this.widthTB.setEditable(false);
		this.widthTB.setEnabled(false);
		this.widthTB.setColumns(10);
		
		this.heightTB = new JTextField();
		this.heightTB.setEditable(false);
		this.heightTB.setEnabled(false);
		this.heightTB.setColumns(10);
		
		JLabel lblWidth = new JLabel("Width");
		
		JLabel lblHeight = new JLabel("Height");
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		GroupLayout gl_contentPanel = new GroupLayout(this.contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING, false)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(lblName)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(this.nameTb, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(lblCode)
							.addGap(18)
							.addComponent(this.codeTB, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(18)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(chckbxGeneralComponent)
							.addGap(22)
							.addComponent(lblLength)
							.addGap(22)
							.addComponent(this.lengthTB, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(lblWidth)
							.addGap(22)
							.addComponent(this.widthTB, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblHeight)
					.addGap(19)
					.addComponent(this.heightTB, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(156, Short.MAX_VALUE))
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 737, Short.MAX_VALUE)
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(this.codeTB, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblCode)
								.addComponent(chckbxGeneralComponent))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(this.nameTb, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblName)
								.addComponent(this.widthTB, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblWidth)))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(this.heightTB, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblHeight)
								.addComponent(this.lengthTB, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblLength))
							.addGap(31)))
					.addPreferredGap(ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 270, GroupLayout.PREFERRED_SIZE))
		);
		
		this.modelGenComp = new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Name", "Length", "Width", "Rotate", "L Code", "W Code", "L Percent", "W percent", "No."
				}
			) {
				@SuppressWarnings("rawtypes")
				Class[] columnTypes = new Class[] {
					String.class, Double.class, Double.class, Boolean.class, Integer.class, Integer.class, Integer.class, Integer.class, Integer.class
				};
				@SuppressWarnings({ "unchecked", "rawtypes" })
				public Class getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				}
			};
		this.modelComp = new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Name", "Length", "Width", "Rotate", "L Code", "W Code", "L Percent", "W percent", "No."
				}
			) {
				@SuppressWarnings("rawtypes")
				Class[] columnTypes = new Class[] {
					String.class, Double.class, Double.class, Boolean.class, Integer.class, Integer.class, Integer.class, Integer.class, Integer.class
				};
				@SuppressWarnings({ "unchecked", "rawtypes" })
				public Class getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				}
				boolean[] columnEditables = new boolean[] {
					true, true, true, true, false, false, false, false, true
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			}; 
		
		this.table = new JTable();
		this.table.setFillsViewportHeight(true);
		this.table.setModel(modelComp);
		formatTable();
		scrollPane.setViewportView(this.table);
		
		JPopupMenu popupMenu = new JPopupMenu();
		addPopup(this.table, popupMenu);
		
		JMenuItem mntmNew = new JMenuItem("New");
		mntmNew.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				model.addRow(new Object[]{null,null,null,null,null,null,null,null,null});
			}
		});
		popupMenu.add(mntmNew);
		
		JMenuItem mntmDelete = new JMenuItem("Delete");
		mntmDelete.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				int index = table.getSelectedRow();
				if(index != -1){
					DefaultTableModel model = (DefaultTableModel) table.getModel();
					model.removeRow(index);
				} else {
					JOptionPane.showMessageDialog(_self,
							"Please select a row!", "Warning!",
							JOptionPane.WARNING_MESSAGE);
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
				JButton okButton = new JButton("Save");
				okButton.addMouseListener(new MouseAdapter() {
					@SuppressWarnings("rawtypes")
					@Override
					public void mouseClicked(MouseEvent e) {
						if (validateField(nameTb) && validateField(codeTB)) {
							DefaultTableModel model = (DefaultTableModel) table.getModel();
							Vector data = model.getDataVector();
							ElementList list = new ElementList();
							if (chckbxGeneralComponent.isSelected()) {
								if (validateField(lengthTB)	&& validateField(widthTB) && validateField(heightTB)) {
									for (Object row : data) {
										Vector rowD = (Vector) row;
										if (validateRow(rowD, true)) {
											int number = getNumber((Integer) rowD.get(8));
											for (int i = 0; i < number; i++) {
												GeneralElement genEl = new GeneralElement((Double) rowD.get(1),	(Double) rowD.get(2), (Boolean) rowD.get(3) == null ? false	: (Boolean) rowD.get(3),
														(Integer) rowD.get(4), (Integer) rowD.get(5), (Integer) rowD.get(6), (Integer) rowD.get(7));
												genEl.setName((String) rowD.get(0));
												list.add(genEl);
											}
										}
									}
									if (!list.isEmpty()) {
										GeneralComponent genComp = new GeneralComponent(nameTb.getText(), codeTB.getText(), Double.valueOf(lengthTB.getText()),
												Double.valueOf(widthTB.getText()), Double.valueOf(heightTB.getText()),list);
										GeneralComponentDAO.insert(genComp);
									} else {
										JOptionPane.showMessageDialog(_self, "Please add at least one element!", "Warning!", JOptionPane.WARNING_MESSAGE);
									}
								}
							} else {
								for (Object row : data) {
									Vector rowD = (Vector) row;
									if (validateRow(rowD, false)){
										int number = getNumber((Integer) rowD.get(8));
										for (int i = 0; i < number; i++) {
											Element elem = new Element((Double) rowD.get(1), (Double) rowD.get(2), (Boolean) rowD.get(3) == null ? false : (Boolean) rowD.get(3));
											elem.setName((String) rowD.get(0));
											list.add(elem);
										}
									}
								}
								if (!list.isEmpty()) {
									Component comp = new Component(nameTb.getText(), codeTB.getText(), list);
									ComponentDAO.insert(comp);
								} else {
									JOptionPane.showMessageDialog(_self, "Please add at least one element!", "Warning!", JOptionPane.WARNING_MESSAGE);
								}

							} 
						} else {
							JOptionPane.showMessageDialog(_self, "Please complete all fields before saving!", "Warning!", JOptionPane.WARNING_MESSAGE);
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
					public void mouseClicked(MouseEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		setEnableGenComp(false);
	}
	
	@SuppressWarnings({ "rawtypes" })
	private void setEnableGenComp(boolean state){
		lengthTB.setEnabled(state);
		lengthTB.setEditable(state);
		lengthTB.setText("");
		widthTB.setEnabled(state);
		widthTB.setEditable(state);
		widthTB.setText("");
		heightTB.setEnabled(state);
		heightTB.setEditable(state);
		heightTB.setText("");
		if(state){
			Vector data = modelComp.getDataVector();
			while(modelGenComp.getRowCount() != 0){
				modelGenComp.removeRow(0);
			}
			for(Object row : data){
				modelGenComp.addRow((Vector) row);
			}
			table.setModel(modelGenComp);
			formatTable();
		} else {
			Vector data = modelGenComp.getDataVector();
			while(modelComp.getRowCount() != 0){
				modelComp.removeRow(0);
			}
			for(Object obj : data){
				Vector row = (Vector) obj;
				modelComp.addRow(new Object[] {row.get(0),row.get(1),row.get(2),row.get(3),null,null,null,null,null});
			}
			table.setModel(modelComp);
			formatTable();
		}
	}
	
	private boolean validateField(JTextField field){
		return (field.getText() != null && !field.getText().trim().isEmpty());
	}
	
	private boolean validateRow(Vector row, boolean isGenComp){
		boolean isValid = (String)row.get(0) != null && (Double)row.get(1) != null && (Double)row.get(2) != null;
		if(isValid){
			isValid = !((String)row.get(0)).isEmpty();
		}
		if(isValid && isGenComp){
			isValid = (Integer)row.get(4) != null && (Integer)row.get(5) != null && (Integer)row.get(6) != null && (Integer)row.get(7) != null;
		}
		return isValid;
	}
	
	private void formatTable(){
		this.table.getColumnModel().getColumn(0).setResizable(false);
		this.table.getColumnModel().getColumn(0).setPreferredWidth(110);
		this.table.getColumnModel().getColumn(1).setResizable(false);
		this.table.getColumnModel().getColumn(1).setPreferredWidth(55);
		this.table.getColumnModel().getColumn(2).setResizable(false);
		this.table.getColumnModel().getColumn(2).setPreferredWidth(55);
		this.table.getColumnModel().getColumn(3).setResizable(false);
		this.table.getColumnModel().getColumn(3).setPreferredWidth(50);
		this.table.getColumnModel().getColumn(4).setResizable(false);
		this.table.getColumnModel().getColumn(4).setPreferredWidth(55);
		this.table.getColumnModel().getColumn(5).setResizable(false);
		this.table.getColumnModel().getColumn(5).setPreferredWidth(55);
		this.table.getColumnModel().getColumn(6).setResizable(false);
		this.table.getColumnModel().getColumn(6).setPreferredWidth(60);
		this.table.getColumnModel().getColumn(7).setResizable(false);
		this.table.getColumnModel().getColumn(7).setPreferredWidth(60);
		this.table.getColumnModel().getColumn(8).setResizable(false);
		this.table.getColumnModel().getColumn(8).setPreferredWidth(40);
	}
	
	private int getNumber(Integer number){
		int val = number == null ? 1 : number;
		val = val < 0 ? 1 : val;
		return val;
	}
	
	private static void addPopup(java.awt.Component component, final JPopupMenu popup) {
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
}
