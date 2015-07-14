package bh.w2optimize.gui;

import java.awt.EventQueue;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JMenuBar;
import javax.swing.UIManager;
import javax.swing.JMenuItem;

import java.awt.Cursor;

import javax.swing.JMenu;

import java.awt.BasicStroke;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JFileChooser;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JButton;
import javax.swing.ListSelectionModel;
import javax.swing.JPopupMenu;

import bh.w2optimize.db.connection.SQLiteConnection;
import bh.w2optimize.db.dao.WoodBoardDAO;
import bh.w2optimize.db.dao.WoodBoardPiceDAO;
import bh.w2optimize.elements.Element;
import bh.w2optimize.elements.ElementList;
import bh.w2optimize.elements.FinalElement;
import bh.w2optimize.entity.WoodBoard;
import bh.w2optimize.entity.WoodBoardPice;
import bh.w2optimize.guillotine.GuillotineMain;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.border.LineBorder;

import java.awt.Color;

import javax.swing.JCheckBox;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.hibernate.cfg.Environment;

import com.pdfjet.A4;
import com.pdfjet.CoreFont;
import com.pdfjet.Image;
import com.pdfjet.ImageType;
import com.pdfjet.PDF;
import com.pdfjet.Page;
import com.pdfjet.TextLine;

public class FrontInterfaceGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1562082775449087719L;

	private final static Logger log = Logger.getLogger(FrontInterfaceGUI.class);
	
	private JPanel contentPane;
	public static CutPanel panel;
	private static JTable table;
	private static DefaultTableModel tableData;
	private JTextField woodBoardNameTB;
	private WoodBoard usedBoard;
	private FrontInterfaceGUI _self;
	private JTextField timeTB;
	private JTextField sawTB;

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
					PropertyConfigurator.configure("./log.properties");
					FrontInterfaceGUI frame = new FrontInterfaceGUI();
					frame.setVisible(true);
					while (frame.isActive()) {
						Thread.sleep(1);
					}
				} catch (Exception e) {
					if(log.isDebugEnabled()){
						log.error(e.getMessage());
					}
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrontInterfaceGUI() {
		
		if(log.isDebugEnabled()){
			log.debug("Application started!");
		}
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

		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				dispose();
			}
		});
		
		JMenuItem mntmSave = new JMenuItem("Save");
		mntmSave.addMouseListener(new MouseAdapter() {
			@SuppressWarnings("rawtypes")
			@Override
			public void mousePressed(MouseEvent arg0) {
				GuillotineMain.getInstance().stopThreads();
				FinalElement incadrare = panel.getIncadrare();
				if (incadrare != null) {
					ArrayList<WoodBoardPice> boards = panel.getBoardList();
					int doUpdate = JOptionPane.showConfirmDialog(null,"Do you want to update the stock now?\nIf there will be needed more boards,\nthose will be shown in the report.",
							"Warning!", JOptionPane.YES_NO_OPTION);
					//MODEL File chooser
					JFileChooser fc = new JFileChooser(new File("."));
					fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
					fc.setFileFilter(new FileNameExtensionFilter("Portable Document Format (*.pdf)", "pdf"));
					int opt = fc.showSaveDialog(_self);
					if(opt == JFileChooser.APPROVE_OPTION){
						File file = fc.getSelectedFile();
						//MODEL graphics 2 png
						ArrayList<Element> list = incadrare.getChildrens();
						for (int i = 0; i < list.size(); i++) {
							Element el = list.get(i);
							BufferedImage bi = new BufferedImage(595, 842, BufferedImage.TYPE_INT_ARGB);
							Graphics2D g2 = bi.createGraphics();
							g2.setStroke(new BasicStroke(3));
							g2 = drawPlaca(el, g2, 10, 10);
							g2.setFont(new Font("TimesRoman", Font.BOLD, 16)); 
							g2.drawString(el.getLength() + " X " + el.getWidth(), Float.parseFloat((el.getLength()/8 - 20)+ ""), 
									Float.parseFloat((35 + el.getWidth()/4)+ ""));
							g2.dispose();
							try {
								ImageIO.write(bi, "png", new File(
										"./temp/test" + i + ".png"));
							} catch (Exception e) {
								if(log.isDebugEnabled()){
									log.error(e.getMessage());
								}
							}
						}
						try {
							//MODEL create pdf
							FileOutputStream fos = new FileOutputStream(file);
							PDF pdf = new PDF(fos);
							for (int i = 0; i < incadrare.getChildrens().size(); i++) {
								Page page = new Page(pdf, A4.PORTRAIT);
								String fileName = "./temp/test" + i + ".png";
								BufferedInputStream bis1 = new BufferedInputStream(new FileInputStream(fileName));
								Image image1 = new Image(pdf, bis1, ImageType.PNG);
								image1.setPosition(0, 0);
								image1.drawOn(page);
							}
							Page listPage = new Page(pdf, A4.PORTRAIT);
							TextLine text = new TextLine(new com.pdfjet.Font(pdf, CoreFont.TIMES_ROMAN),"\t\tList of elements:");
							double y = 50;
							text.setPosition(50, y);
							text.drawOn(listPage);
							y += 40;
							StringBuilder sb = new StringBuilder();
							appentElemHeaders(sb);
							drawText(text, y, sb, listPage);
							int lines = 90 + 25 * 20;
							Vector data = tableData.getDataVector();
							for (Object element : data) {
								if(y + 20 > lines){
									listPage = new Page(pdf, A4.PORTRAIT);
									y = 50;
									sb = new StringBuilder();
									appentElemHeaders(sb);
									drawText(text, y, sb, listPage);
								}
								y+=20;
								Vector row = (Vector) element;
								text.setPosition(50, y);
								text.setText(elementLine(row));
								text.drawOn(listPage);
							}
							Page boardPage = new Page(pdf, A4.PORTRAIT);
							y = 50;
							sb = new StringBuilder("\t\tList of used boards:");
							drawText(text, y, sb, boardPage);
							y += 40;
							sb = new StringBuilder();
							appendUsedBoardsHeaders(sb);
							drawText(text, y, sb, boardPage);
							int boardId = 0;
							for( WoodBoardPice board : boards){
								WoodBoardPice stockBoard = WoodBoardPiceDAO.getById(board.getId());
								int number = stockBoard.getNumber() - board.getNumber();
								for(int i = 0 ; i< number ; i++){
									boardId++;
									if (y + 20 > lines) {
										boardPage = new Page(pdf, A4.PORTRAIT);
										y = 50;
										sb = new StringBuilder();
										appendUsedBoardsHeaders(sb);
										drawText(text, y, sb, boardPage);
									}
									y += 20;
									sb = new StringBuilder();
									sb.append(padLeft(boardId + "", 5))
											.append(padLeft(board.getCode(), 25))
											.append(padLeft(board.getName(), 25))
											.append(padLeft(board.getLength() + "",	20))
											.append(padLeft(board.getWidth() + "", 20))
											.append(padLeft(board.getPrice() + "", 20));
									drawText(text, y, sb, boardPage);
								}
							}
							if(doUpdate == JOptionPane.OK_OPTION){
								SQLiteConnection.closeConnection();
								for (int i = 0; i<boards.size(); i++){
									WoodBoardPice board = boards.get(i);
									if (board.getNumber() == 0	&& i != boards.size() - 1) {
										WoodBoardPice stockBoard = WoodBoardPiceDAO.getById(board.getId());
										WoodBoardPiceDAO.delete(stockBoard);
									} else {
										WoodBoardPice stockBoard = WoodBoardPiceDAO.getById(board.getId());
										stockBoard.setNumber(board.getNumber());
										WoodBoardPiceDAO.update(stockBoard);
									}
									if (i == boards.size() - 1 && board.getNumber() < 0) {
										if (y + 80 > lines) {
											boardPage = new Page(pdf, A4.PORTRAIT);
											y = 50;
										} else {
											y += 40;
										}
										text.setPosition(50, y);
										text.setText("\t\tList of needed boards:");
										text.drawOn(boardPage);
										sb = new StringBuilder();
										appendNeedUseBoardsHeaders(sb);
										y+=20;
										drawText(text, y, sb, boardPage);
										sb = new StringBuilder();
										appendNeedUseBoardsLine(sb, board, -1);
										y+=20;
										drawText(text, y, sb, boardPage);
									}
								}
							}
							ArrayList<Element> newEl = incadrare.getListOfUseableBoards();
							ArrayList<WoodBoardPice> newBoards = new ArrayList<WoodBoardPice>();
							WoodBoard sBoard = WoodBoardDAO.getByCode(boards.get(0).getCode());
							if (y + 80 > lines) {
								boardPage = new Page(pdf, A4.PORTRAIT);
								y = 50;
							} else {
								y += 40;
							}
							text.setPosition(50, y);
							text.setText("\t\tList of new useable boards:");
							text.drawOn(boardPage);
							sb = new StringBuilder();
							appendNeedUseBoardsHeaders(sb);
							y+=20;
							drawText(text, y, sb, boardPage);
							for (Element el : newEl){
								Double price = (sBoard.getPrice() / (sBoard.getLength()*sBoard.getWidth())) * (el.getLength() * el.getWidth()) * 100;
								price = (double) (price.intValue() /100);
								double saw = sawTB.getText() != null && !sawTB.getText().isEmpty() ? Double.valueOf(sawTB.getText()) / 2 : 0;
								double length = el.getLength() - saw;
								double width = el.getWidth() - saw;
								WoodBoardPice wbp = new WoodBoardPice(sBoard.getCode(), sBoard.getName(), sBoard.getMaterial(), length, width, price, 1);
								if (y + 20 > lines) {
									boardPage = new Page(pdf, A4.PORTRAIT);
									y = 50;
								}
								sb = new StringBuilder();
								appendNeedUseBoardsLine(sb, wbp, 1);
								y+=20;
								drawText(text, y, sb, boardPage);
								if(doUpdate == JOptionPane.OK_OPTION){
									WoodBoardPiceDAO.insert(wbp);
								}
							}
					        pdf.flush();
					        fos.close();
					        JOptionPane.showMessageDialog(_self,"Result saved!", "Info",JOptionPane.INFORMATION_MESSAGE);
						} catch (Exception e) {
							if(log.isDebugEnabled()){
								log.error(e.getMessage());
							}
						}
					}
				} else {
					JOptionPane.showMessageDialog(_self,"No result found!", "Warning!",JOptionPane.WARNING_MESSAGE);
				}
			}

			private void appendNeedUseBoardsLine(StringBuilder sb,
					WoodBoardPice board, int val) {
				sb.append(padLeft(board.getCode(), 25))
					.append(padLeft(board.getName(), 25))
					.append(padLeft(board.getLength() + "",	20))
					.append(padLeft(board.getWidth() + "", 20))
					.append(padLeft(board.getPrice() + "", 20))
					.append(padLeft((board.getNumber() * (val)) + "", 15));
			}

			private void appendNeedUseBoardsHeaders(StringBuilder sb) {
				sb.append(padLeft("Code", 25))
					.append(padLeft("Name", 25))
					.append(padLeft("Length (mm)", 20))
					.append(padLeft("Width (mm)", 15))
					.append(padLeft("Price", 15))
					.append(padLeft("Number", 10));
			}

			private void appendUsedBoardsHeaders(StringBuilder sb) {
				sb.append(padLeft("ID", 5))
					.append(padLeft("Code", 25))
					.append(padLeft("Name", 25))
					.append(padLeft("Length (mm)", 20))
					.append(padLeft("Width (mm)", 15))
					.append(padLeft("Price", 15));
			}

			private void appentElemHeaders(StringBuilder sb) {
				sb.append(padLeft("ID", 5))
					.append(padLeft("Component Code", 25))
					.append(padLeft("Name", 25))
					.append(padLeft("Length (mm)", 15))
					.append(padLeft("Width (mm)", 15))
					.append(padLeft("Rotate", 15))
					.append(padLeft("Number", 10));
			}

			private void drawText(TextLine text, double y, StringBuilder sb,
					Page boardPage) {
				try {
					text.setPosition(50, y);
					text.setText(sb.toString());
					text.drawOn(boardPage);
				} catch (Exception e) {
					if(log.isDebugEnabled()){
						log.error(e.getMessage());
					}
				}
			}
		});
		mnNewMenu.add(mntmSave);
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
		mntmAbout.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				JOptionPane.showMessageDialog(_self,"Software pentru optimizarea utilizarii \nmaterialelor in tamplarie \nAutor: Bogdan Heim \nLicenta 2015", "INFO",JOptionPane.INFORMATION_MESSAGE);
			}
		});
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
		
		this.timeTB = new JFormattedTextField(NumberFormat.getNumberInstance());
		this.timeTB.setEditable(false);
		this.timeTB.setToolTipText("number of seconds");
		this.timeTB.setEnabled(false);
		this.timeTB.setBounds(128, 8, 101, 20);
		constraints.add(this.timeTB);
		this.timeTB.setColumns(10);
		
		JLabel lblSeconds = new JLabel("seconds");
		lblSeconds.setBounds(239, 11, 75, 14);
		constraints.add(lblSeconds);
		
				

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
		
		JLabel lblSawWidth = new JLabel("Saw thickness (mm)");
		lblSawWidth.setBounds(304, 39, 117, 14);
		layeredPane.add(lblSawWidth);
		
		this.sawTB = new JFormattedTextField(NumberFormat.getNumberInstance());
		this.sawTB.setBounds(431, 36, 86, 20);
		layeredPane.add(this.sawTB);
		this.sawTB.setColumns(10);

		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setRowHeight(20);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Component", "Name", "Length (mm)", "Width (mm)", "Rotate", "No."
			}
		) {
			@SuppressWarnings("rawtypes")
			Class[] columnTypes = new Class[] {
				Integer.class, Object.class, String.class, Double.class, Double.class, Boolean.class, Integer.class
			};
			@SuppressWarnings({ "unchecked", "rawtypes" })
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(0).setPreferredWidth(40);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(1).setPreferredWidth(65);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(2).setPreferredWidth(90);
		table.getColumnModel().getColumn(3).setResizable(false);
		table.getColumnModel().getColumn(3).setPreferredWidth(65);
		table.getColumnModel().getColumn(4).setResizable(false);
		table.getColumnModel().getColumn(4).setPreferredWidth(65);
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
								double saw = sawTB.getText() != null && !sawTB.getText().isEmpty() ? Double.valueOf(sawTB.getText()) / 2 : 0;
								double length = (Double) row.get(3) + saw;
								double width = (Double) row.get(4) + saw;
								Element el = new Element(length, width, (Boolean) row.get(5) == null ? false	: (Boolean) row.get(5));
								el.setComponentCode((String)row.get(1));
								el.setName((String)row.get(2));
								el.setId((Integer) row.get(0));
								elms.add(el);
							}
						}
						GuillotineMain guillotineMain = GuillotineMain.getInstance();
						
						panel.setIncadrare(null,false,null);
						int value = 120;
						if (chckbxTimeRestriction.isSelected()){
							if(timeTB.getText() != null && !timeTB.getText().isEmpty()){
								try{
									int val = Integer.parseInt(timeTB.getText());
									value = val;
								} catch (Exception ex){
									if(log.isDebugEnabled()){
										log.error(ex.getMessage());
									}
								}
							}
						}
						List<WoodBoardPice> list = WoodBoardPiceDAO.getByCode(usedBoard.getCode());
						guillotineMain.start(elms, list, value);
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
	
	private Graphics2D drawPlaca(final Element root, Graphics2D g2,
			double xVal, double yVal) {
		Path2D path = new Path2D.Double();
		final double x = (root.getPoint().getX()/4) + xVal;
		final double y = (root.getPoint().getY()/4) + yVal;
		g2.setBackground(Color.WHITE);
		g2.setColor(Color.BLACK);
		if (root.isUsed()) {
			final Rectangle2D rect = new Rectangle2D.Double();
			rect.setRect(x, y, root.getLength()/4, root.getWidth()/4);
			g2.setColor(getElemColor(root.getId()%10));
			g2.fill(rect);
			g2.setColor(Color.BLACK);
			Point2D p = root.getPoint();
			g2.setFont(new Font("TimesRoman", Font.BOLD, 16)); 
			g2.drawString(root.getId() + "", Float.parseFloat((x + root.getLength()/8)+ ""), 
					Float.parseFloat((y + root.getWidth()/8)+ ""));
			g2.draw(rect);
		} else {
			path.moveTo(x, y);
			if (!root.getChildrens().isEmpty()) {
				for (final Element el : root.getChildrens()) {
					g2 = drawPlaca(el, g2, xVal, yVal);
				}
			}
			path.lineTo(x, y + root.getWidth()/4);
			path.lineTo(x + root.getLength()/4, y + root.getWidth()/4);
			path.lineTo(x + root.getLength()/4, y);
			path.closePath();
			g2.draw(path);
		}
		return g2;
	}
	
	private Color getElemColor(int color){
		switch (color) {
		case 1:
			return new Color(255, 51, 0);
		case 2:
			return new Color(255, 153, 51);
		case 3:
			return new Color(153, 204, 0);
		case 4:
			return new Color(0, 204, 153);
		case 5:
			return new Color(163, 71, 71);
		case 6:
			return new Color(98, 98, 184);
		case 7:
			return new Color(80, 137, 80);
		case 8:
			return new Color(230, 180, 77);
		case 9:
			return new Color(103, 203, 111);
		default:
			return new Color(102, 51, 0);
		}
	}
	
	@SuppressWarnings("rawtypes")
	private String elementLine(Vector row){
		StringBuilder sb = new StringBuilder(padLeft((Integer) row.get(0) + "", 5));
		sb.append(padLeft((String)row.get(1), 30));
		sb.append(padLeft((String)row.get(2), 30));
		sb.append(padLeft((Double) row.get(3) + "", 20));
		sb.append(padLeft((Double) row.get(4) + "", 20));
		sb.append(padLeft(((Boolean) row.get(5) == null ? false	: (Boolean) row.get(5)) + "", 20));
		sb.append(padLeft((Integer) row.get(6) + "", 15));
		return sb.toString();
	}
	
	private  String padLeft(String s, int n) {
		while(s.length() < n){
			s = " " + s;
		}
	    return s;  
	}
}
