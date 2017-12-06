package window1;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

/**
 * @author 김찬중 프로그램의 메인화면
 */
public class Home extends JFrame {

	JPanel contentPane;
	JTextField tf_search;
	JTable table_view;
	ArrayList<AddressInfo> rs_set = null;
	JComboBox cb_type;
	DefaultTableModel defaultTableModel;
	Table_model model;
	Group_model model2;
	private JTable table_group;
	
	public Home() {

		DB db = new DB();
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(250, 130, 1509, 752);
		setIconImage(Toolkit.getDefaultToolkit().getImage("rsc\\logo_icon.png"));
		setTitle("Home");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		/**
		 * @author 김찬중
		 * 검색을 할 유형을 고르는 콤보박스
		 */

		String[] element = { "이름", "학번", "학과", "번호", "해쉬태그" };
		JComboBox<String> cb_type = new JComboBox<String>();
		for (int i = 0; i < element.length; i++) {
			cb_type.addItem(element[i]);
		}
		cb_type.setBounds(307, 12, 99, 44);
		cb_type.setBackground(new Color(114, 172, 69));
		contentPane.add(cb_type);
		cb_type.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		cb_type.setForeground(Color.WHITE);

		/**
		 * @author 김찬중
		 * AddPerson 창을 여는 버튼
		 */

		JButton btn_AddPerson = new JButton("");
		btn_AddPerson.setBorderPainted(false);
		btn_AddPerson.setIcon(new ImageIcon("rsc\\icon\\btn_AddPerson.png"));
		btn_AddPerson.setBounds(3, 10, 144, 97);
		btn_AddPerson.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Frame.frame_addperson.setVisible(true);
			}
		});
		contentPane.add(btn_AddPerson);

		/**
		 * @author 김찬중
		 * AddGroup 창을 여는 버튼
		 */

		JButton btn_AddGroup = new JButton("");
		btn_AddGroup.setBorderPainted(false);
		btn_AddGroup.setIcon(new ImageIcon("rsc\\icon\\btn_AddGroup.png"));
		btn_AddGroup.setBounds(157, 10, 144, 97);
		btn_AddGroup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Frame.frame_addgroup.setVisible(true);
			}
		});
		contentPane.add(btn_AddGroup);
		
		/**
		 * @author 김찬중
		 * 검색할 내용을 입력하는 텍스트필드
		 */

		tf_search = new JTextField();
		tf_search.setBounds(406, 12, 426, 44);
		contentPane.add(tf_search);
		tf_search.setColumns(10);

		JButton btn_search = new JButton("");
		btn_search.setBorderPainted(false);
		btn_search.setIcon(new ImageIcon("rsc\\icon\\btn_search.jpg"));
		btn_search.setBounds(833, 13, 123, 42);
		btn_search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				switch(cb_type.getSelectedIndex()) {
				case 0:
					db.searchname(tf_search.getText());
					model.addPageInfo(Data.address_vector);
					model.fireTableDataChanged();
					break;
				case 1:
					db.searchcode(tf_search.getText());
					model.addPageInfo(Data.address_vector);
					model.fireTableDataChanged();
					break;
				case 2:
					db.searchmajor(tf_search.getText());
					model.addPageInfo(Data.address_vector);
					model.fireTableDataChanged();
					break;
				case 3:
					db.searchphone(tf_search.getText());
					model.addPageInfo(Data.address_vector);
					model.fireTableDataChanged();
					break;
				case 4:
					db.searchHash(tf_search.getText());
					model.addPageInfo(Data.address_vector);
					model.fireTableDataChanged();
					break;
				}
					
			}

		});
		contentPane.add(btn_search);

		/**
		 * @author 김찬중 
		 * 모든 창을 닫고 로그인 화면을 띄워주는 버튼
		 */

		JButton btn_logout = new JButton("");
		btn_logout.setBorderPainted(false);
		btn_logout.setIcon(new ImageIcon("rsc\\icon\\btn_logout.png"));
		btn_logout.setBounds(1372, 12, 123, 42);
		contentPane.add(btn_logout);
		btn_logout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tf_search.setText("");
				Data.address_vector.removeAllElements();
				Frame.frame_login.setVisible(true);
				Frame.frame_home.setVisible(false);
				Frame.frame_popup.setVisible(false);
			}
		});

		/**
		 * @author 김찬중
		 * DB에서 가져온 자료를 table에 넣는 함수들
		 */

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(307, 68, 1188, 640);
		contentPane.add(scrollPane);

		model = new Table_model();
		TableColumnModel columnModel = new DefaultTableColumnModel();
		TableCellRenderer renderer = new DefaultTableCellRenderer();
		
		TableColumn column = new TableColumn(0);
		column.setPreferredWidth(10);
		DefaultTableCellRenderer center = new DefaultTableCellRenderer();
		center.setHorizontalAlignment(SwingConstants.CENTER);		
		
		column.setCellRenderer(renderer);
		column.setHeaderValue("이름");
		column.setCellRenderer(center);
		columnModel.addColumn(column);
		
		column = new TableColumn(1);
		column.setHeaderValue("번호");
		column.setCellRenderer(center);
		columnModel.addColumn(column);
		
		column = new TableColumn(2);
		column.setHeaderValue("학번");
		column.setCellRenderer(center);
		columnModel.addColumn(column);
		
		column = new TableColumn(3);
		column.setHeaderValue("전공");
		column.setCellRenderer(center);
		columnModel.addColumn(column);
		
		column = new TableColumn(4);
		column.setHeaderValue("성별");
		column.setCellRenderer(center);
		columnModel.addColumn(column);
		
		column = new TableColumn(5);
		column.setHeaderValue("생일");
		column.setCellRenderer(center);
		columnModel.addColumn(column);
		columnModel.setColumnSelectionAllowed(false);
		
		table_view = new JTable(model, columnModel);
		table_view.setFont(new Font("굴림", Font.PLAIN, 25));
		table_view.setRowHeight(50);
		table_view.addMouseListener(new MouseListener() {
	         public void mouseClicked(MouseEvent e) {
	             JTable j = (JTable) e.getComponent();
	             if(e.getClickCount() == 2) { 
	            	 Frame.frame_addperson.data = model.pages.get(j.getSelectedRow());
	            	 Frame.frame_addperson.Value();
	            	 Frame.frame_addperson.setVisible(true);
	                   
	                }
	          }
	          public void mouseEntered(MouseEvent e) {}
	          public void mouseExited(MouseEvent e) {}
	          public void mousePressed(MouseEvent e) {}
	          public void mouseReleased(MouseEvent e) {}
	       });
		scrollPane.setViewportView(table_view);
		scrollPane.getViewport().setBackground(Color.WHITE);

		JTableHeader header = table_view.getTableHeader();
		header.setPreferredSize(new Dimension(10, 50));
		header.setFont(new Font("굴림", Font.BOLD, 30));
		header.setBackground(Color.WHITE);

		
		/*class rate_panel extends JPanel {
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.setColor(Color.RED);
				g.drawArc(0, 0, 80, 80, 90, 270);
			}
		}
	
		JPanel rate_panel = new JPanel();
		rate_panel.setBounds(3, 416, 298, 298);
		contentPane.add(rate_panel);*/
		
		model2 = new Group_model();
		TableColumnModel columnModel2 = new DefaultTableColumnModel();
		
		TableColumn column1 = new TableColumn(0);
		column1.setPreferredWidth(10);
		column1.setHeaderValue("이름");
		center.setHorizontalAlignment(SwingConstants.CENTER);		
		
		column1.setCellRenderer(renderer);
		column1.setCellRenderer(center);
		columnModel2.addColumn(column1);
	
		
		
		table_group = new JTable(model2,columnModel2);
		/*table_group.setOpaque(false);*/
		table_group.setBounds(3, 133, 296, 328);
		table_group.addMouseListener(new MouseListener() {
	         public void mouseClicked(MouseEvent e) {
	             JTable j = (JTable) e.getComponent();
	             if(e.getClickCount() == 2) { 
	            	
	            	 db.searchgroup(model2.list.get(j.getSelectedRow()));
	            	model.addPageInfo(Data.address_vector);
					model.fireTableDataChanged();
	                   
	                }
	          }
	          public void mouseEntered(MouseEvent e) {}
	          public void mouseExited(MouseEvent e) {}
	          public void mousePressed(MouseEvent e) {}
	          public void mouseReleased(MouseEvent e) {}
	       });
		contentPane.add(table_group);

		JLabel lb_background = new JLabel(new ImageIcon("rsc\\Home.jpg"));
		lb_background.setBackground(Color.WHITE);
		lb_background.setBounds(0, 0, 1642, 800);
		contentPane.add(lb_background);

	}

	class Table_model extends AbstractTableModel {

		private Vector<AddressInfo> pages;

		public Table_model() {
			pages = new Vector<AddressInfo>();
		}

		public int getColumnCount() {
			return 6;
		}

		public int getRowCount() {
			return pages.size();
		}

		public void addPageInfo(Vector<AddressInfo> page) {
			this.pages = page;
		}

		public Object getValueAt(int rowIndex, int columnIndex) {
			AddressInfo info = pages.get(rowIndex);
			switch (columnIndex) {
			case 0:
				return info.name;
			case 1:
				return info.num;
			case 2:
				return info.stid;
			case 3:
				return info.major;
			case 4:
				return info.sex;
			case 5:
				return info.month + "월 " + info.day + "일";
			default:
				return "invalid";
			}
		}
	}
	
	
	class Group_model extends AbstractTableModel {
		DB db = new DB();
		
		private Vector<String> list;

		public Group_model(){
			db.group();
			System.out.println(Data.groupname_vector.get(1));
			list= Data.groupname_vector;
		}

		public int getColumnCount() {
			return 1;
		}

		public int getRowCount() {
			return list.size();
		}

		/*public void addPageInfo(Vector<String> page) {
			this.list = page;
		}*/
		
		public Object getValueAt(int rowIndex, int columnIndex) {
			
			switch (0) {
			case 0:
				return Data.groupname_vector.get(rowIndex);
			
			default:
				return "invalid";
			}
		}
	}
}
