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
import bh.w2optimize.entity.Element;
import bh.w2optimize.entity.ElementList;
import bh.w2optimize.entity.WoodBoard;
import bh.w2optimize.guillotine.GuillotineConstraints;
import bh.w2optimize.guillotine.GuillotineMain;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigInteger;
import java.util.Vector;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.border.LineBorder;

import java.awt.Color;

import javax.swing.JRadioButton;

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
	private JTextField restrictionTB;

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

	private void createContents() {
		_self = this;
		SQLiteConnection.getInstance();
		setResizable(false);
		setTitle("W2Optimize");
		setPreferredSize(new Dimension(840, 600));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 851, 602);

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
		mnNewMenu.add(mntmExit);

		JMenu mnEdit = new JMenu("Edit");
		menuBar.add(mnEdit);

		JMenuItem mntmEditComponents = new JMenuItem("Edit Components");
		mnEdit.add(mntmEditComponents);

		JMenuItem mntmEditWoodBoards = new JMenuItem("Edit Wood Boards");
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

		JButton btnAddComponent = new JButton("Choose Component");

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
						.addComponent(constraints, GroupLayout.PREFERRED_SIZE, 289, GroupLayout.PREFERRED_SIZE)
						.addComponent(layeredPane, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 289, Short.MAX_VALUE)
						.addGroup(Alignment.TRAILING, gl_panel_1.createSequentialGroup()
							.addComponent(btnStop)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnStart))
						.addComponent(scrollPane, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 289, Short.MAX_VALUE)
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
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 301, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(constraints, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnStart)
						.addComponent(btnStop)))
		);
		
		JLabel lblRestrictions = new JLabel("Restrictions");
		lblRestrictions.setBounds(10, 11, 72, 14);
		constraints.add(lblRestrictions);
		
		JRadioButton rdbtnTime = new JRadioButton("Time");
		rdbtnTime.setBounds(78, 7, 72, 23);
		constraints.add(rdbtnTime);
		
		JRadioButton rdbtnTries = new JRadioButton("Tries");
		rdbtnTries.setBounds(78, 32, 109, 23);
		constraints.add(rdbtnTries);
		
		JRadioButton rdbtnBestSolution = new JRadioButton("Best Solution");
		rdbtnBestSolution.setBounds(78, 55, 109, 23);
		constraints.add(rdbtnBestSolution);
		
		JRadioButton rdbtnNone = new JRadioButton("None");
		rdbtnNone.setBounds(10, 32, 59, 23);
		constraints.add(rdbtnNone);
		rdbtnNone.setSelected(true);
		
		JLabel lblValue = new JLabel("Value");
		lblValue.setBounds(207, 11, 53, 14);
		constraints.add(lblValue);
		
		this.restrictionTB = new JTextField();
		this.restrictionTB.setBounds(193, 33, 86, 20);
		constraints.add(this.restrictionTB);
		this.restrictionTB.setColumns(10);
		this.restrictionTB.setEnabled(false);
		
		rdbtnTime.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				rdbtnTries.setSelected(false);
				rdbtnBestSolution.setSelected(false);
				rdbtnNone.setSelected(false);
				restrictionTB.setEnabled(true);
				restrictionTB.setToolTipText("Value representing time in seconds.");
			}
		});
		rdbtnTries.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				rdbtnTime.setSelected(false);
				rdbtnBestSolution.setSelected(false);
				rdbtnNone.setSelected(false);
				restrictionTB.setEnabled(true);
				restrictionTB.setToolTipText("Value represent the number of tries.");
				restrictionTB.setText("");
			}
		});
		rdbtnBestSolution.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				rdbtnTime.setSelected(false);
				rdbtnTries.setSelected(false);
				rdbtnNone.setSelected(false);
				restrictionTB.setEnabled(true);
				restrictionTB.setToolTipText("Value represent the number of best solutions find before stop.");
			}
		});
		rdbtnNone.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				rdbtnTime.setSelected(false);
				rdbtnTries.setSelected(false);
				rdbtnBestSolution.setSelected(false);
				restrictionTB.setEnabled(false);
				restrictionTB.setToolTipText(null);
			}
		});
		
				

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
		table.setAutoCreateRowSorter(true);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Length", "Width", "No.", "Rotate"
			}
		) {
			Class[] columnTypes = new Class[] {
				Double.class, Double.class, Integer.class, Boolean.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		tableData = (DefaultTableModel) table.getModel();
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(3).setResizable(false);
		table.setFillsViewportHeight(true);
		scrollPane.setViewportView(table);

		JPopupMenu popupMenu = new JPopupMenu();
		addPopup(table, popupMenu);

		JMenuItem mntmNewRow = new JMenuItem("New Row");
		mntmNewRow.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				tableData.addRow(new Object[] {null,null,null,null});
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
			@Override
			public void mouseClicked(MouseEvent e) {
				if (usedBoard == null) {
					JOptionPane.showMessageDialog(_self,
							"Please select wood board!", "Warning!",
							JOptionPane.WARNING_MESSAGE);
				} else {
					panel.resetIncadrare();
					ElementList elms = new ElementList();
					Vector data = tableData.getDataVector();
					if (data.isEmpty()) {
						JOptionPane.showMessageDialog(_self,
								"Please add some elements to begin cutting!", "Warning!",
								JOptionPane.WARNING_MESSAGE);
					} else {
						for (Object element : data) {
							Vector row = (Vector) element;
							elms.addMore((Double) row.get(0), (Double) row
									.get(1),
									(Boolean) row.get(3) == null ? false
											: (Boolean) row.get(3),
									(Integer) row.get(2));
						}
						GuillotineMain guillotineMain = GuillotineMain
								.getInstance();
						Integer elemNr = elms.size();
						int elemRotate = 0;
						for(Element el : elms){
							if(el.isRotate()){
								elemRotate++;
							}
						}
						BigInteger totalPerm = new BigInteger(elemNr.toString());
						for (int i = elemNr - 1; i > 0; i--) {
							totalPerm = totalPerm.multiply(new BigInteger(i + ""));
						}
						totalPerm = totalPerm.multiply(BigInteger.valueOf(2).pow(elemRotate));
						panel.setTotalPerm(totalPerm);
						panel.setIncadrare(null,0,false);
						GuillotineConstraints constraint = GuillotineConstraints.NONE;
						if(rdbtnTime.isSelected()){
							constraint = GuillotineConstraints.TIME;
						} else if (rdbtnTries.isSelected()){
							constraint = GuillotineConstraints.TRIES;
						}else if ( rdbtnBestSolution.isSelected()){
							constraint = GuillotineConstraints.BESTSOLUTION;
						}
						String value = null; 
						if(restrictionTB.isEnabled()){
							value = restrictionTB.getText();
						}
						guillotineMain.start(elms, usedBoard.toElement(),constraint, value);
					}
				}
			}
		});
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
