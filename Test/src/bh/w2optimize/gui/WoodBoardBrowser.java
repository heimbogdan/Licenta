package bh.w2optimize.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import java.awt.Dimension;

import javax.swing.ScrollPaneConstants;

import bh.w2optimize.db.dao.WoodBoardDAO;
import bh.w2optimize.entity.WoodBoard;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Vector;

public class WoodBoardBrowser extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6050547569794768822L;
	
	private final JPanel contentPanel = new JPanel();
	private static JTable table;
	private JScrollPane n;
	private static FrontInterfaceGUI front;
	private WoodBoardBrowser _self = this;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			WoodBoardBrowser dialog = new WoodBoardBrowser(front);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 * @param container 
	 */
	public WoodBoardBrowser(Container container) {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		createContents();
		front = (FrontInterfaceGUI) container;
	}  
	
	@SuppressWarnings({ "static-access", "serial" })
	private void createContents() {
		setModalityType(ModalityType.APPLICATION_MODAL);
		setTitle("Wood Board Browser");
		setResizable(false);
		setBounds(100, 100, 451, 300);
		getContentPane().setLayout(new BorderLayout());
		this.contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(this.contentPanel, BorderLayout.CENTER);
		{
			n = new JScrollPane();
			this.n.setName("");
			this.n.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			this.n.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			this.n.setPreferredSize(new Dimension(50, 50));
		}
		GroupLayout gl_contentPanel = new GroupLayout(this.contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(this.n, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(this.n, GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE)
					.addContainerGap())
		);
		{
			this.table = new JTable();
			this.n.setViewportView(this.table);
			this.table.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Code", "Name", "Material", "Standard Size"
				}
			) {
				boolean[] columnEditables = new boolean[] {
					true, false, false, false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			});
			this.table.getColumnModel().getColumn(0).setPreferredWidth(70);
			this.table.getColumnModel().getColumn(1).setResizable(false);
			this.table.getColumnModel().getColumn(1).setPreferredWidth(80);
			this.table.getColumnModel().getColumn(2).setResizable(false);
			this.table.getColumnModel().getColumn(2).setPreferredWidth(80);
			this.table.getColumnModel().getColumn(3).setResizable(false);
			this.table.getColumnModel().getColumn(3).setPreferredWidth(80);
			loadData();
		}
		this.contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Select");
				okButton.addMouseListener(new MouseAdapter() {
					@SuppressWarnings("rawtypes")
					@Override
					public void mouseClicked(MouseEvent e) {
						if (table.getSelectedRow() != -1) {
							Vector obj = (Vector) ((DefaultTableModel) table
									.getModel()).getDataVector().get(
									table.getSelectedRow());
							front.setUsedBoard(WoodBoardDAO.getByCode(obj.get(0).toString()));
							dispose();
						} else {
							JOptionPane.showMessageDialog(_self, "Please select a row!", "Warning!", JOptionPane.WARNING_MESSAGE);
						}
					}
				});
				{
					JButton btnAdd = new JButton("Add");
					btnAdd.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							new WoodBoardAdder(_self).setVisible(true);
						}
					});
					buttonPane.add(btnAdd);
				}
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
	
	public void loadData(){
		List<WoodBoard> list = WoodBoardDAO.getAll();
		if(list != null && !list.isEmpty()){
			DefaultTableModel model = (DefaultTableModel) table.getModel();
			while(model.getRowCount() != 0){
				model.removeRow(0);
			}
			for(WoodBoard board : list){
				model.addRow(new Object[] {board.getCode(),board.getName(),board.getMaterial(),board.getLength() + " x "+ board.getWidth()});
			}
		}
	}
	
}
