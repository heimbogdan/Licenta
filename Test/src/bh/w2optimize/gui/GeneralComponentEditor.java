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
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JPopupMenu;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.JMenuItem;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;

import bh.w2optimize.db.dao.GeneralComponentDAO;
import bh.w2optimize.entity.Element;
import bh.w2optimize.entity.ElementList;
import bh.w2optimize.entity.GeneralComponent;
import bh.w2optimize.entity.GeneralElement;

public class GeneralComponentEditor extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2164482194872097498L;
	
	
	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private JTextField codeTB;
	private JTextField nameTB;
	private JTextField lengthTB;
	private JTextField widthTB;
	private JTextField heightTB;
	private GeneralComponentEditor _self;
	private ComponentBrowser parent;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			GeneralComponentEditor dialog = new GeneralComponentEditor(null,null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	@SuppressWarnings("serial")
	public GeneralComponentEditor(ComponentBrowser parent, GeneralComponent genComp) {
		setResizable(false);
		setTitle("General Component Editor");
		setModalityType(ModalityType.APPLICATION_MODAL);
		this.parent = parent;
		_self = this;
		setBounds(100, 100, 702, 392);
		getContentPane().setLayout(new BorderLayout());
		this.contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(this.contentPanel, BorderLayout.CENTER);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		this.codeTB = new JTextField();
		this.codeTB.setEnabled(false);
		this.codeTB.setColumns(10);
		this.nameTB = new JTextField();
		this.nameTB.setColumns(10);
		this.lengthTB = new JTextField();
		this.lengthTB.setColumns(10);
		this.widthTB = new JTextField();
		this.widthTB.setColumns(10);
		this.heightTB = new JTextField();
		this.heightTB.setColumns(10);
		JLabel lblCode = new JLabel("Code");
		JLabel lblName = new JLabel("Name");
		JLabel lblLength = new JLabel("Length");
		JLabel lblWidth = new JLabel("Width");
		JLabel lblHeight = new JLabel("Height");
		GroupLayout gl_contentPanel = new GroupLayout(this.contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 487, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblCode)
						.addComponent(lblName)
						.addComponent(lblLength)
						.addComponent(lblWidth)
						.addComponent(lblHeight))
					.addPreferredGap(ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(this.codeTB, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(this.nameTB, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(this.lengthTB, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(this.widthTB, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(this.heightTB, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 337, Short.MAX_VALUE)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(76)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(this.codeTB, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblCode))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(this.nameTB, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblName))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(this.lengthTB, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblLength))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(this.widthTB, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblWidth))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(this.heightTB, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblHeight))
					.addContainerGap(90, Short.MAX_VALUE))
		);
		{
			this.table = new JTable();
			this.table.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Name", "Length", "Width", "Rotate", "L Code", "W Code", "L Percent", "W Pencent"
				}
			) {
				@SuppressWarnings("rawtypes")
				Class[] columnTypes = new Class[] {
					String.class, Double.class, Double.class, Boolean.class, Integer.class, Integer.class, Integer.class, Integer.class
				};
				@SuppressWarnings({ "unchecked", "rawtypes" })
				public Class getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				}
			});
			this.table.getColumnModel().getColumn(0).setResizable(false);
			this.table.getColumnModel().getColumn(0).setPreferredWidth(90);
			this.table.getColumnModel().getColumn(1).setResizable(false);
			this.table.getColumnModel().getColumn(1).setPreferredWidth(45);
			this.table.getColumnModel().getColumn(2).setResizable(false);
			this.table.getColumnModel().getColumn(2).setPreferredWidth(45);
			this.table.getColumnModel().getColumn(3).setResizable(false);
			this.table.getColumnModel().getColumn(3).setPreferredWidth(45);
			this.table.getColumnModel().getColumn(4).setResizable(false);
			this.table.getColumnModel().getColumn(4).setPreferredWidth(50);
			this.table.getColumnModel().getColumn(5).setResizable(false);
			this.table.getColumnModel().getColumn(5).setPreferredWidth(50);
			this.table.getColumnModel().getColumn(6).setResizable(false);
			this.table.getColumnModel().getColumn(6).setPreferredWidth(60);
			this.table.getColumnModel().getColumn(7).setResizable(false);
			this.table.getColumnModel().getColumn(7).setPreferredWidth(60);
			this.table.setFillsViewportHeight(true);
			scrollPane.setViewportView(this.table);
			{
				JPopupMenu popupMenu = new JPopupMenu();
				addPopup(this.table, popupMenu);
				{
					JMenuItem mntmNew = new JMenuItem("New");
					mntmNew.addMouseListener(new MouseAdapter() {
						@Override
						public void mousePressed(MouseEvent e) {
							DefaultTableModel model = (DefaultTableModel) table.getModel();
							model.addRow(new Object[] {null,null,null,null,null,null,null,null});
						}
					});
					popupMenu.add(mntmNew);
				}
				{
					JMenuItem mntmDelete = new JMenuItem("Delete");
					mntmDelete.addMouseListener(new MouseAdapter() {
						@Override
						public void mousePressed(MouseEvent e) {
							int index = table.getSelectedRow();
							if(index != -1){
								if (JOptionPane.showConfirmDialog(null,	"Are you sure you want to delete this general compoment?",
										"Warning!", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
									DefaultTableModel model = (DefaultTableModel) table.getModel();
									model.removeRow(index);
								}
							} else {
								JOptionPane.showMessageDialog(_self, "Please select a general component!", "Warning!", JOptionPane.WARNING_MESSAGE);
							}
						}
					});
					popupMenu.add(mntmDelete);
				}
			}
		}
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
						boolean complete = true;
						ElementList listElem = new ElementList();
						String code = codeTB.getText();
						DefaultTableModel model = (DefaultTableModel) table.getModel();
						Vector data = model.getDataVector();
						for (Object element : data) {
							Vector row = (Vector) element;
							if (row.get(0) != null && row.get(1) != null && row.get(2) != null && row.get(4) != null && row.get(5) != null &&
									row.get(6) != null && row.get(7) != null){
								GeneralElement genElem = new GeneralElement((Double)row.get(1), (Double)row.get(2), (Boolean)row.get(3)==null ? false : (Boolean)row.get(3),
										(Integer)row.get(4), (Integer)row.get(5), (Integer)row.get(6), (Integer)row.get(7));
								genElem.setName((String) row.get(0));
								listElem.add(genElem);
							} else {
								complete = false;
							}
						}
						if(complete){
							GeneralComponent genComponent = GeneralComponentDAO.getByCode(code);
							genComponent.setName(nameTB.getText());
							genComponent.setLength(Double.valueOf(lengthTB.getText()));
							genComponent.setWidth(Double.valueOf(widthTB.getText()));
							genComponent.setHeight(Double.valueOf(heightTB.getText()));
							genComponent.setElements(listElem);
							GeneralComponentDAO.update(genComponent);
							_self.parent.loadDataGenComp();
							dispose();
						} else {
							JOptionPane.showMessageDialog(_self, "Some of the general elements have empty values!", "Warning!", JOptionPane.WARNING_MESSAGE);
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
		
		// populate fields
		codeTB.setText(genComp.getCode());
		nameTB.setText(genComp.getName());
		lengthTB.setText(genComp.getLength() + "");
		widthTB.setText(genComp.getWidth() + "");
		heightTB.setText(genComp.getHeight() + "");
		// populate tabel
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		ElementList list = genComp.getElements();
		if(list != null && !list.isEmpty()){
			for ( Element elem : list){
				GeneralElement el = (GeneralElement) elem;
				model.addRow(new Object[] {el.getName(),el.getLength(),el.getWidth(),el.isRotate(),el.getLengthCode(),el.getWidthCode(),
						el.getPercent1(),el.getPercent2()});
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
}
