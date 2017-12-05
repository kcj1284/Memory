package window1;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
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
	private String combo, value;

	/**
	 * Create the frame.
	 */
	public Home() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(50, 30, 1642, 820);
		setIconImage(Toolkit.getDefaultToolkit().getImage("rsc\\logo_icon.png"));
		setTitle("Home");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		String[] element = new String[6];
		element[0] = "이름";
		element[1] = "학번";
		element[2] = "학과";
		element[3] = "번호";
		element[4] = "해쉬태그";
		element[5] = "생일";

		cb_type = new JComboBox(element);
		cb_type.setBounds(307, 12, 99, 44);
		cb_type.setBackground(new Color(114, 172, 69));
		contentPane.add(cb_type);
		cb_type.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		cb_type.setForeground(Color.WHITE);

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
		tf_search = new JTextField();
		tf_search.setBounds(406, 12, 426, 44);
		contentPane.add(tf_search);
		tf_search.setColumns(10);

		JButton btn_search = new JButton("");
		btn_search.setBorderPainted(false);
		btn_search.setIcon(new ImageIcon("rsc\\icon\\btn_search.jpg"));
		btn_search.setBounds(833, 13, 123, 42);
		contentPane.add(btn_search);

		JButton btn_logout = new JButton("");
		btn_logout.setBorderPainted(false);
		btn_logout.setIcon(new ImageIcon("rsc\\icon\\btn_logout.png"));
		btn_logout.setBounds(1500, 13, 123, 42);
		contentPane.add(btn_logout);
		btn_logout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Frame.frame_login.setVisible(true);
				Frame.frame_home.setVisible(false);
				Frame.frame_popup.setVisible(false);
			}
		});

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(307, 68, 1188, 640);
		contentPane.add(scrollPane);

		model = new Table_model();
		TableColumnModel columnModel = new DefaultTableColumnModel();
		TableCellRenderer renderer = new DefaultTableCellRenderer();

		TableColumn column = new TableColumn(0);
		column.setCellRenderer(renderer);
		column.setHeaderValue("이름");
		column.setPreferredWidth(10);
		columnModel.addColumn(column);

		column = new TableColumn(1);
		column.setHeaderValue("번호");
		columnModel.addColumn(column);

		column = new TableColumn(2);
		column.setHeaderValue("학번");
		columnModel.addColumn(column);

		column = new TableColumn(3);
		column.setHeaderValue("전공");
		columnModel.addColumn(column);

		column = new TableColumn(4);
		column.setHeaderValue("성별");
		columnModel.addColumn(column);

		column = new TableColumn(5);
		column.setHeaderValue("생일");
		columnModel.addColumn(column);
		columnModel.setColumnSelectionAllowed(false);

		table_view = new JTable(model, columnModel);
		table_view.setRowHeight(20);
		scrollPane.setViewportView(table_view);

		JTableHeader header = table_view.getTableHeader();
		header.setPreferredSize(new Dimension(10, 50));

		JLabel lb_background = new JLabel(new ImageIcon("rsc\\Home.jpg"));
		lb_background.setForeground(Color.WHITE);
		lb_background.setBackground(Color.WHITE);
		lb_background.setBounds(0, 0, 1642, 800);
		contentPane.add(lb_background);

	}

	class Table_model extends AbstractTableModel {

		private ArrayList<AddressInfo> pages;

		public Table_model() {
			pages = new ArrayList<AddressInfo>();
		}

		public int getColumnCount() {
			return 6;
		}

		public int getRowCount() {
			return pages.size();
		}

		public void addPageInfo(AddressInfo page) {
			int idx = pages.size();
			pages.add(page);
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
				return info.month.toString() + "	" + info.day.toString();
			default:
				return "invalid";
			}
		}
	}

	public void Home() {

		combo = cb_type.getSelectedItem().toString();
		value = tf_search.getText();

	}
}
