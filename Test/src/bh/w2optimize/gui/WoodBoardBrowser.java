package bh.w2optimize.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import java.awt.Dimension;

import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ScrollPaneConstants;

import java.awt.Dialog.ModalityType;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class WoodBoardBrowser extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private JScrollPane n;
	private static FrontInterfaceGUI front;
	
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
		createContents();
		front = (FrontInterfaceGUI) container;
	}  
	
	private void createContents() {
		setModalityType(ModalityType.APPLICATION_MODAL);
		setTitle("Wood Board Browser");
		setResizable(false);
		setBounds(100, 100, 450, 300);
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
		}
		this.contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
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
	
}
