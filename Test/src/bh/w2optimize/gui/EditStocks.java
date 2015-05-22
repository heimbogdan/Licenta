package bh.w2optimize.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Dialog.ModalExclusionType;
import java.awt.Dialog.ModalityType;
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
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JPopupMenu;
import java.awt.Component;
import javax.swing.JMenuItem;

public class EditStocks extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;

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
		
		this.textField = new JTextField();
		this.textField.setBounds(94, 60, 86, 20);
		layeredPane.add(this.textField);
		this.textField.setColumns(10);
		
		this.textField_1 = new JTextField();
		this.textField_1.setBounds(94, 91, 86, 20);
		layeredPane.add(this.textField_1);
		this.textField_1.setColumns(10);
		
		this.textField_2 = new JTextField();
		this.textField_2.setBounds(94, 122, 86, 20);
		layeredPane.add(this.textField_2);
		this.textField_2.setColumns(10);
		
		this.textField_3 = new JTextField();
		this.textField_3.setBounds(94, 153, 86, 20);
		layeredPane.add(this.textField_3);
		this.textField_3.setColumns(10);
		
		this.textField_4 = new JTextField();
		this.textField_4.setBounds(94, 184, 86, 20);
		layeredPane.add(this.textField_4);
		this.textField_4.setColumns(10);
		
		this.textField_5 = new JTextField();
		this.textField_5.setBounds(94, 215, 86, 20);
		layeredPane.add(this.textField_5);
		this.textField_5.setColumns(10);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.setBounds(117, 296, 89, 23);
		layeredPane.add(btnAdd);
		
		JPopupMenu popupMenu = new JPopupMenu();
		addPopup(scrollPane, popupMenu);
		
		JMenuItem mntmEdit = new JMenuItem("Edit");
		popupMenu.add(mntmEdit);
		
		JMenuItem mntmDelete = new JMenuItem("Delete");
		popupMenu.add(mntmDelete);
		
		this.table = new JTable();
		this.table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Code", "Name", "Material", "Length", "Width", "Price"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		this.table.getColumnModel().getColumn(0).setResizable(false);
		this.table.getColumnModel().getColumn(0).setPreferredWidth(60);
		this.table.getColumnModel().getColumn(1).setResizable(false);
		this.table.getColumnModel().getColumn(1).setPreferredWidth(80);
		this.table.getColumnModel().getColumn(2).setResizable(false);
		this.table.getColumnModel().getColumn(2).setPreferredWidth(80);
		this.table.getColumnModel().getColumn(3).setResizable(false);
		this.table.getColumnModel().getColumn(3).setPreferredWidth(60);
		this.table.getColumnModel().getColumn(4).setResizable(false);
		this.table.getColumnModel().getColumn(4).setPreferredWidth(60);
		this.table.getColumnModel().getColumn(5).setResizable(false);
		scrollPane.setViewportView(this.table);
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
}
