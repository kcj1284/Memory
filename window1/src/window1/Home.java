package window1;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
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
 * @author ������ ���α׷��� ����ȭ��
 */
public class Home extends JFrame {

	JPanel contentPane;
	JTextField tf_search;
	JTable table_view;
	ArrayList<AddressInfo> rs_set = null;
	JComboBox cb_type;
	DefaultTableModel defaultTableModel;
	Table_model model;
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
		 * @author ������ �˻��� �� ������ ���� �޺��ڽ�
		 */

		String[] element = { "�̸�", "�й�", "�а�", "��ȣ", "�ؽ��±�" };
		JComboBox<String> cb_type = new JComboBox<String>();
		for (int i = 0; i < element.length; i++) {
			cb_type.addItem(element[i]);
		}
		cb_type.setBounds(307, 12, 99, 44);
		cb_type.setBackground(new Color(114, 172, 69));
		contentPane.add(cb_type);
		cb_type.setFont(new Font("���� ���", Font.BOLD, 20));
		cb_type.setForeground(Color.WHITE);

		/**
		 * @author ������ AddPerson â�� ���� ��ư
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
		 * @author ������ AddGroup â�� ���� ��ư
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
		 * @author ������ �˻��� ������ �Է��ϴ� �ؽ�Ʈ�ʵ�
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
		 * @author ������ ��� â�� �ݰ� �α��� ȭ���� ����ִ� ��ư
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
		 * @author ������ DB���� ������ �ڷḦ table�� �ִ� �Լ���
		 */

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(307, 68, 1188, 640);
		contentPane.add(scrollPane);

		model = new Table_model();
		TableColumnModel columnModel = new DefaultTableColumnModel();
		TableCellRenderer renderer = new DefaultTableCellRenderer();

		TableColumn column = new TableColumn(0);
		column.setPreferredWidth(10);

		column.setCellRenderer(renderer);
		column.setHeaderValue("�̸�");
		columnModel.addColumn(column);

		column = new TableColumn(1);
		column.setHeaderValue("��ȣ");
		columnModel.addColumn(column);

		column = new TableColumn(2);
		column.setHeaderValue("�й�");
		columnModel.addColumn(column);

		column = new TableColumn(3);
		column.setHeaderValue("����");
		columnModel.addColumn(column);

		column = new TableColumn(4);
		column.setHeaderValue("����");
		columnModel.addColumn(column);

		column = new TableColumn(5);
		column.setHeaderValue("����");
		columnModel.addColumn(column);
		columnModel.setColumnSelectionAllowed(false);

		table_view = new JTable(model, columnModel);
		table_view.setFont(new Font("����", Font.PLAIN, 30));
		table_view.setRowHeight(50);
		scrollPane.setViewportView(table_view);
		scrollPane.getViewport().setBackground(Color.WHITE);

		JTableHeader header = table_view.getTableHeader();
		header.setPreferredSize(new Dimension(10, 50));
		header.setBackground(Color.WHITE);

		JLabel label = new JLabel("�׷�");
		label.setBounds(14, 153, 62, 18);
		contentPane.add(label);

		JList group_list = new JList();
		group_list.setBackground(Color.WHITE);
		group_list.setBounds(3, 139, 295, 265);
		contentPane.add(group_list);

		JLabel lb_background = new JLabel(new ImageIcon("rsc\\Home.jpg"));
		lb_background.setForeground(Color.WHITE);
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
				return info.month + "	" + info.day;
			default:
				return "invalid";
			}
		}
	}
}
