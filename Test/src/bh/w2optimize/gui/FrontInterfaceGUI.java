package bh.w2optimize.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JMenuBar;
import javax.swing.UIManager;
import javax.swing.JMenuItem;

import java.awt.Cursor;

import javax.swing.JMenu;

import java.awt.Dimension;
import java.awt.Rectangle;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JButton;
import javax.swing.ListSelectionModel;
import javax.swing.JPopupMenu;

import bh.w2optimize.db.connection.SQLiteConnection;
import bh.w2optimize.elements.Element;
import bh.w2optimize.elements.ElementList;
import bh.w2optimize.entity.WoodBoard;
import bh.w2optimize.guillotine.GuillotineConstraints;
import bh.w2optimize.guillotine.GuillotineMain;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.border.LineBorder;

import java.awt.Color;

import javax.swing.JRadioButton;
import javax.swing.JCheckBox;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class FrontInterfaceGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1562082775449087719L;

	private JPanel contentPane;
	public static CutPanel panel;
	private static JTable table;
	private static DefaultTableModel tableData;
	private JTextField woodBoardNameTB;
	private WoodBoard usedBoard;
	private FrontInterfaceGUI _self;
	private JTextField timeTB;

	public void setUsedBoard(WoodBoard board) {
		this.usedBoard = board;
		this.woodBoardNameTB.setText(board.getName());
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrontInterfaceGUI frame = new FrontInterfaceGUI();
					frame.setVisible(true);
					while (frame.isActive()) {
						Thread.sleep(1);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrontInterfaceGUI() {
		createContents();
	}

	@SuppressWarnings({ "serial", "static-access" })
	private void createContents() {
		_self = this;
		SQLiteConnection.getInstance();
		setResizable(false);
		setTitle("W2Optimize");
		setPreferredSize(new Dimension(840, 600));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1077, 602);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		menuBar.setBorder(UIManager.getBorder("MenuBar.border"));
		setJMenuBar(menuBar);

		JMenu mnNewMenu = new JMenu("File");
		mnNewMenu.setBounds(new Rectangle(0, 0, 50, 20));
		mnNewMenu.setSize(new Dimension(50, 20));
		menuBar.add(mnNewMenu);

		JMenuItem mntmNew = new JMenuItem("New");
		mnNewMenu.add(mntmNew);

		JMenuItem mntmOpen = new JMenuItem("Open ...");
		mnNewMenu.add(mntmOpen);

		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				dispose();
			}
		});
		mnNewMenu.add(mntmExit);

		JMenu mnEdit = new JMenu("Edit");
		menuBar.add(mnEdit);

		JMenuItem mntmEditComponents = new JMenuItem("Edit Components");
		mntmEditComponents.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				new ComponentBrowser(_self).setVisible(true);
			}
		});
		mnEdit.add(mntmEditComponents);

		JMenuItem mntmEditWoodBoards = new JMenuItem("Edit Wood Boards");
		mntmEditWoodBoards.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				WoodBoardBrowser wb = new WoodBoardBrowser(arg0.getComponent()
						.getParent().getParent().getParent().getParent()
						.getParent().getParent());
				wb.setVisible(true);
			}
		});
		mnEdit.add(mntmEditWoodBoards);
		
		JMenuItem mntmEditStocks = new JMenuItem("Edit Stocks");
		mntmEditStocks.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				EditStocks stocks = new EditStocks();
				stocks.setVisible(true);
			}
		});
		mnEdit.add(mntmEditStocks);

		JMenu mnSettings = new JMenu("Settings");
		menuBar.add(mnSettings);

		JMenuItem mntmPreferences = new JMenuItem("Preferences");
		mnSettings.add(mntmPreferences);

		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);

		JMenuItem mntmAbout = new JMenuItem("About");
		mnHelp.add(mntmAbout);

		JMenuItem mntmUserManual = new JMenuItem("User Manual");
		mnHelp.add(mntmUserManual);
		this.contentPane = new JPanel();
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(this.contentPane);

		panel = new CutPanel();

		JPanel panel_1 = new JPanel();

		JScrollPane scrollPane = new JScrollPane();
		scrollPane
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane
				.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		JButton btnStart = new JButton("Start");
		

		JButton btnNewComponent = new JButton("New Component");
		btnNewComponent.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ComponentAdder compAdd = new ComponentAdder();
				compAdd.setVisible(true);
			}
		});

		JButton btnAddComponent = new JButton("Choose Component");
		btnAddComponent.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new ComponentBrowser(_self).setVisible(true);
			}
		});

		JButton btnStop = new JButton("Stop");
		btnStop.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				GuillotineMain.getInstance().stopCurrentThreads();
			}
		});

		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBorder(new LineBorder(new Color(0, 0, 0)));
		
		JLayeredPane constraints = new JLayeredPane();
		constraints.setBorder(new LineBorder(new Color(0, 0, 0)));
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 527, Short.MAX_VALUE)
						.addComponent(layeredPane, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 527, Short.MAX_VALUE)
						.addGroup(Alignment.TRAILING, gl_panel_1.createSequentialGroup()
							.addComponent(constraints, GroupLayout.PREFERRED_SIZE, 402, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnStop)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnStart))
						.addGroup(Alignment.TRAILING, gl_panel_1.createSequentialGroup()
							.addComponent(btnNewComponent)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnAddComponent)))
					.addContainerGap())
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addComponent(layeredPane, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewComponent)
						.addComponent(btnAddComponent))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 375, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
							.addComponent(btnStart)
							.addComponent(btnStop))
						.addComponent(constraints, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)))
		);
		
		JCheckBox chckbxTimeRestriction = new JCheckBox("Time restriction");
		chckbxTimeRestriction.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				if(chckbxTimeRestriction.isSelected()){
					timeTB.setEnabled(true);
					timeTB.setEditable(true);
				} else {
					timeTB.setEnabled(false);
					timeTB.setEditable(false);
					timeTB.setText("");
				}
			}
		});
		chckbxTimeRestriction.setBounds(6, 7, 116, 23);
		constraints.add(chckbxTimeRestriction);
		
		this.timeTB = new JTextField();
		this.timeTB.setEditable(false);
		this.timeTB.setToolTipText("number of seconds");
		this.timeTB.setEnabled(false);
		this.timeTB.setBounds(128, 8, 101, 20);
		constraints.add(this.timeTB);
		this.timeTB.setColumns(10);
		
				

		JLabel lblWoodBoard = new JLabel("Wood Board Name");
		lblWoodBoard.setBounds(10, 11, 125, 14);
		layeredPane.add(lblWoodBoard);

		this.woodBoardNameTB = new JTextField();
		this.woodBoardNameTB.setEditable(false);
		this.woodBoardNameTB.setToolTipText("Name of the used board");
		this.woodBoardNameTB.setBounds(10, 36, 161, 20);
		layeredPane.add(this.woodBoardNameTB);
		this.woodBoardNameTB.setColumns(10);

		JButton btnWoodBoardBrowse = new JButton("Browse");
		btnWoodBoardBrowse.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				WoodBoardBrowser wb = new WoodBoardBrowser(arg0.getComponent()
						.getParent().getParent().getParent().getParent()
						.getParent().getParent());
				wb.setVisible(true);
			}
		});
		btnWoodBoardBrowse.setBounds(181, 35, 89, 23);
		layeredPane.add(btnWoodBoardBrowse);

		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setRowHeight(20);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Component", "Name", "Length", "Width", "Rotate", "No."
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, Object.class, String.class, Double.class, Double.class, Boolean.class, Integer.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, true, true, true, true, true, true
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(0).setPreferredWidth(40);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(1).setPreferredWidth(65);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(2).setPreferredWidth(90);
		table.getColumnModel().getColumn(3).setResizable(false);
		table.getColumnModel().getColumn(3).setPreferredWidth(60);
		table.getColumnModel().getColumn(4).setResizable(false);
		table.getColumnModel().getColumn(4).setPreferredWidth(60);
		table.getColumnModel().getColumn(5).setResizable(false);
		table.getColumnModel().getColumn(5).setPreferredWidth(60);
		table.getColumnModel().getColumn(6).setResizable(false);
		table.getColumnModel().getColumn(6).setPreferredWidth(40);
		tableData = (DefaultTableModel) table.getModel();
		table.setFillsViewportHeight(true);
		scrollPane.setViewportView(table);

		JPopupMenu popupMenu = new JPopupMenu();
		addPopup(table, popupMenu);

		JMenuItem mntmNewRow = new JMenuItem("New Row");
		mntmNewRow.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				tableData.addRow(new Object[] {tableData.getRowCount()+1,null,null,null,null,null});
			}
		});
		popupMenu.add(mntmNewRow);

		JMenuItem mntmDeleteRow = new JMenuItem("Delete Row");
		mntmDeleteRow.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if(table.getSelectedRow() != -1){
					tableData.removeRow(table.getSelectedRow());
				}
			}
		});
		popupMenu.add(mntmDeleteRow);
		panel_1.setLayout(gl_panel_1);
		GroupLayout gl_contentPane = new GroupLayout(this.contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(
				Alignment.LEADING).addGroup(
				gl_contentPane.createSequentialGroup().addComponent(panel_1, GroupLayout.DEFAULT_SIZE,GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addPreferredGap(ComponentPlacement.RELATED).addComponent(this.panel, GroupLayout.PREFERRED_SIZE,508, GroupLayout.PREFERRED_SIZE)));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING,gl_contentPane.createSequentialGroup().addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
														.addComponent(this.panel,Alignment.LEADING,GroupLayout.DEFAULT_SIZE,530,Short.MAX_VALUE)
														.addComponent(panel_1,GroupLayout.DEFAULT_SIZE,GroupLayout.DEFAULT_SIZE,Short.MAX_VALUE))
										.addGap(0)));
		this.contentPane.setLayout(gl_contentPane);
		
		btnStart.addMouseListener(new MouseAdapter() {
			@SuppressWarnings("rawtypes")
			@Override
			public void mouseClicked(MouseEvent e) {
				if (usedBoard == null) {
					JOptionPane.showMessageDialog(_self,"Please select wood board!", "Warning!",JOptionPane.WARNING_MESSAGE);
				} else {
					panel.resetIncadrare();
					ElementList elms = new ElementList();
					Vector data = tableData.getDataVector();
					if (data.isEmpty()) {
						JOptionPane.showMessageDialog(_self, "Please add some elements to begin cutting!", "Warning!", JOptionPane.WARNING_MESSAGE);
					} else {
						for (Object element : data) {
							Vector row = (Vector) element;
							int number = (Integer) row.get(6);
							for(int i = 0; i < number; i++){
								Element el = new Element((Double) row.get(3), (Double) row.get(4), (Boolean) row.get(5) == null ? false	: (Boolean) row.get(5));
								el.setComponentCode((String)row.get(1));
								el.setName((String)row.get(2));
								el.setId((Integer) row.get(0));
								elms.add(el);
							}
						}
						GuillotineMain guillotineMain = GuillotineMain.getInstance();
						
						panel.setIncadrare(null,false);
						GuillotineConstraints constraint = GuillotineConstraints.NONE;
						int value = 0;
						if (chckbxTimeRestriction.isSelected()){
							if(timeTB.getText() != null && !timeTB.getText().isEmpty()){
								try{
									int val = Integer.parseInt(timeTB.getText());
									value = val;
								} catch (Exception ex){
									// TODO log4j
								}
							}
						}
						//TODO de trimis catre main codul placii
						guillotineMain.start(elms, usedBoard.toElement(),constraint, value);
					}
				}
			}
		});
	}

	public void addData(ArrayList<Element> list) {
		ArrayList<Element> newList = new ArrayList<Element>();
		ArrayList<Integer> numList = new ArrayList<Integer>();
		for (Element el : list) {
			boolean isNew = true;
			for (int i = 0; i < newList.size(); i++) {
				if (el.equals(newList.get(i))) {
					numList.set(i, numList.get(i) + 1);
					isNew = false;
				}
			}
			if (isNew) {
				newList.add(el);
				numList.add(1);
			}
		}
		for (int i = 0; i < newList.size(); i++){
			Element el = newList.get(i);
			tableData.addRow(new Object[] {tableData.getRowCount()+1,el.getComponentCode(),el.getName(),el.getLength(),
					el.getWidth(),el.isRotate(),numList.get(i)});
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
