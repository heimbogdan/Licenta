package bh.w2optimize.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.JLayeredPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;

import java.awt.Color;
import java.util.List;
import java.util.Vector;

import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableModel;

import bh.w2optimize.db.dao.ComponentDAO;
import bh.w2optimize.db.dao.GeneralComponentDAO;
import bh.w2optimize.elements.Element;
import bh.w2optimize.elements.ElementList;
import bh.w2optimize.entity.Component;
import bh.w2optimize.entity.GeneralComponent;

import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.JPopupMenu;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JMenuItem;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class ComponentBrowser extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2027311490164484117L;
	
	private final static Logger log = Logger.getLogger(ComponentBrowser.class);
	
	private final JPanel contentPanel = new JPanel();
	private JTable componentTable;
	private JTextField codeCompTB;
	private JTextField nameCompTB;
	private JTable compElementTable;
	private ComponentBrowser _self;
	private JTable genCompTable;
	private FrontInterfaceGUI parent;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ComponentBrowser dialog = new ComponentBrowser(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			if(log.isDebugEnabled()){
				log.error(e.getStackTrace().toString());
			}
		}
	}

	/**
	 * Create the dialog.
	 */
	@SuppressWarnings("serial")
	public ComponentBrowser(FrontInterfaceGUI parent) {
		this.parent = parent;
		setTitle("Component Browser");
		setResizable(false);
		setModalityType(ModalityType.APPLICATION_MODAL);
		_self = this;
		setBounds(100, 100, 589, 482);
		getContentPane().setLayout(new BorderLayout());
		this.contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(this.contentPanel, BorderLayout.CENTER);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		
		tabbedPane.setBackground(Color.LIGHT_GRAY);
		GroupLayout gl_contentPanel = new GroupLayout(this.contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addComponent(tabbedPane, GroupLayout.DEFAULT_SIZE, 563, Short.MAX_VALUE)
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addComponent(tabbedPane, GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
		);
		
		JLayeredPane componentLayeredPane = new JLayeredPane();
		componentLayeredPane.setBackground(Color.LIGHT_GRAY);
		tabbedPane.addTab("Component", null, componentLayeredPane, null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		this.codeCompTB = new JTextField();
		this.codeCompTB.setEnabled(false);
		this.codeCompTB.setColumns(10);
		
		this.nameCompTB = new JTextField();
		this.nameCompTB.setColumns(10);
		
		JLabel lblCode = new JLabel("Code");
		
		JLabel lblName = new JLabel("Name");
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		JLabel lblElements = new JLabel("Elements");
		
		JButton btnSave = new JButton("Save");
		btnSave.addMouseListener(new MouseAdapter() {
			@SuppressWarnings({"rawtypes"})
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String code = codeCompTB.getText();
				if(code != null && !code.isEmpty()){
					Component comp = ComponentDAO.getByCode(code);
					ElementList listElem = new ElementList();
					Vector data = ((DefaultTableModel)compElementTable.getModel()).getDataVector();
					for (Object element : data) {
						Vector row = (Vector) element;
						Element el = new Element((Double) row.get(1),
								(Double) row.get(2),
								(Boolean) row.get(3) == null ? false
										: (Boolean) row.get(3));
						el.setName((String)row.get(0));
						listElem.add(el);
					}
					comp.setName(nameCompTB.getText());
					comp.setElements(listElem);
					ComponentDAO.update(comp);
					loadDataComp();
					resetEditFieldsComp();
				}
			}
		});
		GroupLayout gl_layeredPane = new GroupLayout(componentLayeredPane);
		gl_layeredPane.setHorizontalGroup(
			gl_layeredPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_layeredPane.createSequentialGroup()
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 295, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_layeredPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_layeredPane.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_layeredPane.createParallelGroup(Alignment.TRAILING)
								.addGroup(Alignment.LEADING, gl_layeredPane.createSequentialGroup()
									.addGap(6)
									.addComponent(lblElements)
									.addContainerGap())
								.addComponent(scrollPane_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 257, Short.MAX_VALUE)
								.addGroup(gl_layeredPane.createSequentialGroup()
									.addComponent(btnSave)
									.addContainerGap())))
						.addGroup(gl_layeredPane.createSequentialGroup()
							.addGap(42)
							.addGroup(gl_layeredPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblCode)
								.addComponent(lblName))
							.addGap(39)
							.addGroup(gl_layeredPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(this.codeCompTB, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(this.nameCompTB, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addContainerGap())))
		);
		gl_layeredPane.setVerticalGroup(
			gl_layeredPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_layeredPane.createSequentialGroup()
					.addGroup(gl_layeredPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(scrollPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 367, Short.MAX_VALUE)
						.addGroup(gl_layeredPane.createSequentialGroup()
							.addContainerGap(13, Short.MAX_VALUE)
							.addGroup(gl_layeredPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(this.codeCompTB, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblCode))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_layeredPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(this.nameCompTB, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblName))
							.addGap(18)
							.addComponent(lblElements)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 236, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnSave)))
					.addGap(5))
		);
		
		this.compElementTable = new JTable();
		this.compElementTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Name", "Length", "Width", "Rotate"
			}
		) {
			@SuppressWarnings("rawtypes")
			Class[] columnTypes = new Class[] {
				String.class, Double.class, Double.class, Boolean.class
			};
			@SuppressWarnings({ "unchecked", "rawtypes" })
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		this.compElementTable.getColumnModel().getColumn(0).setResizable(false);
		this.compElementTable.getColumnModel().getColumn(1).setResizable(false);
		this.compElementTable.getColumnModel().getColumn(2).setResizable(false);
		this.compElementTable.getColumnModel().getColumn(3).setResizable(false);
		this.compElementTable.setFillsViewportHeight(true);
		scrollPane_1.setViewportView(this.compElementTable);
		
		JPopupMenu popupMenu_1 = new JPopupMenu();
		addPopup(this.compElementTable, popupMenu_1);
		
		JMenuItem mntmNew = new JMenuItem("New");
		popupMenu_1.add(mntmNew);
		
		JMenuItem mntmDelete_1 = new JMenuItem("Delete");
		mntmDelete_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				int index = compElementTable.getSelectedRow();
				if(index != -1){
					if (JOptionPane.showConfirmDialog(null,	"Are you sure you want to delete this element?",
							"Warning!", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
						DefaultTableModel model = (DefaultTableModel) compElementTable.getModel();
						model.removeRow(index);
					}
				} else {
					JOptionPane.showMessageDialog(_self, "Please select a component!", "Warning!", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		popupMenu_1.add(mntmDelete_1);
		
		this.componentTable = new JTable();
		this.componentTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Code", "Name", "Elements No."
			}
		) {
			@SuppressWarnings("rawtypes")
			Class[] columnTypes = new Class[] {
				String.class, String.class, Integer.class
			};
			@SuppressWarnings({ "unchecked", "rawtypes" })
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			// MODEL tabel cells not editable
			boolean[] columnEditables = new boolean[] {
				false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		this.componentTable.getColumnModel().getColumn(0).setResizable(false);
		this.componentTable.getColumnModel().getColumn(1).setResizable(false);
		this.componentTable.getColumnModel().getColumn(2).setResizable(false);
		this.componentTable.setFillsViewportHeight(true);
		scrollPane.setViewportView(this.componentTable);
		
		JPopupMenu popupMenu = new JPopupMenu();
		addPopup(this.componentTable, popupMenu);
		
		JMenuItem mntmEdit = new JMenuItem("Edit");
		mntmEdit.addMouseListener(new MouseAdapter() {
			@SuppressWarnings("rawtypes")
			@Override
			public void mousePressed(MouseEvent arg0) {
				int index = componentTable.getSelectedRow();
				if(index != -1){
					resetEditFieldsComp();
					DefaultTableModel model = (DefaultTableModel) componentTable.getModel();					
					codeCompTB.setText((String) model.getValueAt(index, 0));
					nameCompTB.setText((String) model.getValueAt(index, 1));
					loadDataCompElements(codeCompTB.getText());
				}
			}
		});
		popupMenu.add(mntmEdit);
		
		JMenuItem mntmDelete = new JMenuItem("Delete");
		mntmDelete.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				int index = componentTable.getSelectedRow();
				if(index != -1){
					if (JOptionPane.showConfirmDialog(null,	"Are you sure you want to delete this compoment?",
							"Warning!", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
						DefaultTableModel model = (DefaultTableModel) componentTable.getModel();
						Component comp = ComponentDAO.getByCode((String) model.getValueAt(index, 0));
						ComponentDAO.delete(comp);
						model.removeRow(index);
					}
				} else {
					JOptionPane.showMessageDialog(_self, "Please select a component!", "Warning!", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		popupMenu.add(mntmDelete);
		componentLayeredPane.setLayout(gl_layeredPane);
		
		JLayeredPane genCompLayeredPane = new JLayeredPane();
		tabbedPane.addTab("General Component", null, genCompLayeredPane, null);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane_2.setBounds(0, 0, 568, 382);
		genCompLayeredPane.add(scrollPane_2);
		
		this.genCompTable = new JTable();
		this.genCompTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Code", "Name", "Length", "Width", "Height", "Elements No."
			}
		) {
			@SuppressWarnings("rawtypes")
			Class[] columnTypes = new Class[] {
				String.class, String.class, Double.class, Double.class, Double.class, Integer.class
			};
			@SuppressWarnings({ "unchecked", "rawtypes" })
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		this.genCompTable.getColumnModel().getColumn(0).setResizable(false);
		this.genCompTable.getColumnModel().getColumn(0).setPreferredWidth(60);
		this.genCompTable.getColumnModel().getColumn(1).setResizable(false);
		this.genCompTable.getColumnModel().getColumn(1).setPreferredWidth(110);
		this.genCompTable.getColumnModel().getColumn(2).setResizable(false);
		this.genCompTable.getColumnModel().getColumn(2).setPreferredWidth(55);
		this.genCompTable.getColumnModel().getColumn(3).setResizable(false);
		this.genCompTable.getColumnModel().getColumn(3).setPreferredWidth(55);
		this.genCompTable.getColumnModel().getColumn(4).setResizable(false);
		this.genCompTable.getColumnModel().getColumn(4).setPreferredWidth(55);
		this.genCompTable.getColumnModel().getColumn(5).setResizable(false);
		this.genCompTable.getColumnModel().getColumn(5).setPreferredWidth(55);
		this.genCompTable.setFillsViewportHeight(true);
		scrollPane_2.setViewportView(this.genCompTable);
		
		JPopupMenu popupMenu_2 = new JPopupMenu();
		addPopup(this.genCompTable, popupMenu_2);
		
		JMenuItem mntmEdit_1 = new JMenuItem("Edit");
		mntmEdit_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				int index = genCompTable.getSelectedRow();
				if(index != -1){
					resetEditFieldsComp();
					DefaultTableModel model = (DefaultTableModel) genCompTable.getModel();
					GeneralComponent genComp = GeneralComponentDAO.getByCode((String) model.getValueAt(index, 0));
					new GeneralComponentEditor(_self, genComp).setVisible(true);
				} else {
					JOptionPane.showMessageDialog(_self, "Please select a general component!", "Warning!", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		popupMenu_2.add(mntmEdit_1);
		
		JMenuItem mntmDelete_2 = new JMenuItem("Delete");
		mntmDelete_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				int index = genCompTable.getSelectedRow();
				if(index != -1){
					if (JOptionPane.showConfirmDialog(null,	"Are you sure you want to delete this general compoment?",
							"Warning!", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
						DefaultTableModel model = (DefaultTableModel) genCompTable.getModel();
						GeneralComponent genComp = GeneralComponentDAO.getByCode((String) model.getValueAt(index, 0));
						GeneralComponentDAO.delete(genComp);
						model.removeRow(index);
					}
				} else {
					JOptionPane.showMessageDialog(_self, "Please select a general component!", "Warning!", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		popupMenu_2.add(mntmDelete_2);
		this.contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Select");
				okButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						int index = tabbedPane.getSelectedIndex();
						if(index != -1){
							if(index == 0){
								DefaultTableModel model = (DefaultTableModel) componentTable.getModel();
								int rowid = componentTable.getSelectedRow();
								if(rowid != -1){
									String code = (String) model.getValueAt(rowid, 0);
									Component comp = ComponentDAO.getByCode(code);
									if (comp != null) {
										parent.addData(comp.getElements());
									} else {
										JOptionPane.showMessageDialog(_self, "Nothing return from database!", "ERROR!", JOptionPane.ERROR_MESSAGE);
									}
								} else {
									JOptionPane.showMessageDialog(_self, "No component selected!", "ERROR!", JOptionPane.ERROR_MESSAGE);
								}
							} else if(index == 1){
								DefaultTableModel model = (DefaultTableModel) genCompTable.getModel();
								int rowid = genCompTable.getSelectedRow();
								if(rowid != -1){
									String code = (String) model.getValueAt(rowid, 0);
									GeneralComponent genComp = GeneralComponentDAO.getByCode(code);
									if (genComp != null) {
										JTextField lenTB = new JTextField();
										JTextField widTB = new JTextField();
										JTextField heiTB = new JTextField();
										lenTB.setText(genComp.getLength() + "");
										widTB.setText(genComp.getWidth() + "");
										heiTB.setText(genComp.getHeight() + "");
										Object[] message = {
												"Please enter your values or leave the default ones!\n",
											    "Length:", lenTB,"Width:", widTB,"Height:", heiTB};
										int option = JOptionPane.showConfirmDialog(null, message, "Login", JOptionPane.OK_CANCEL_OPTION);
										if (option == JOptionPane.OK_OPTION) {
											double len = Double.valueOf(lenTB.getText());
											double wid = Double.valueOf(widTB.getText());
											double hei = Double.valueOf(heiTB.getText());
											genComp.rescaleElements(len, wid, hei);
											parent.addData(genComp.getElements());
										}
									} else {
										JOptionPane.showMessageDialog(_self, "Nothing return from database!", "Warning!", JOptionPane.WARNING_MESSAGE);
									}
								} else {
									JOptionPane.showMessageDialog(_self, "No general component selected!", "Warning!", JOptionPane.WARNING_MESSAGE);
								}
							}
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
		
		tabbedPane.setSelectedIndex(0);
		loadDataComp();
		tabbedPane.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				if (arg0.getSource() instanceof JTabbedPane) {
                    JTabbedPane pane = (JTabbedPane) arg0.getSource();
                    if(pane.getSelectedIndex() == 0){
                    	loadDataComp();
                    }else if(pane.getSelectedIndex() == 1){
                    	loadDataGenComp();
                    }
                }
			}
		});
	}
	
	public void loadDataComp(){
		List<Component> list = ComponentDAO.getAll();
		if(list != null && !list.isEmpty()){
			DefaultTableModel model = (DefaultTableModel) componentTable.getModel();
			while(model.getRowCount() != 0){
				model.removeRow(0);
			}
			for(Component board : list){
				model.addRow(new Object[] {board.getCode(),board.getName(),board.getElements().size()});
			}
		}
	}
	
	public void loadDataCompElements(String code){
		Component comp = ComponentDAO.getByCode(code);
		if (comp != null) {
			ElementList list = comp.getElements();
			if (list != null && !list.isEmpty()) {
				DefaultTableModel model = (DefaultTableModel) compElementTable.getModel();
				while (model.getRowCount() != 0) {
					model.removeRow(0);
				}
				for (Element board : list) {
					model.addRow(new Object[] { board.getName(), board.getLength(), board.getWidth(), board.isRotate()});
				}
			}
		}
	}
	
	public void loadDataGenComp(){
		List<GeneralComponent> list = GeneralComponentDAO.getAll();
		if(list != null && !list.isEmpty()){
			DefaultTableModel model = (DefaultTableModel) genCompTable.getModel();
			while(model.getRowCount() != 0){
				model.removeRow(0);
			}
			for(GeneralComponent board : list){
				model.addRow(new Object[] {board.getCode(),board.getName(),board.getLength(),board.getWidth(),board.getHeight(),board.getElements().size()});
			}
		}
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
	
	private void resetEditFieldsComp(){
		String empty = "";
		codeCompTB.setText(empty);
		nameCompTB.setText(empty);
		DefaultTableModel model = (DefaultTableModel)compElementTable.getModel();
		while(model.getRowCount() != 0){
			model.removeRow(0);
		}
	}
}
