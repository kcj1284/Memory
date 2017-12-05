package window1;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

/**
 * @author ������
 * Home���� �ι��� �߰��ϴ� Class
 */
public class AddPerson extends JFrame {

	private JPanel contentPane;
	private JTextField tf_name;
	private JTextField tf_num;
	private JTextField tf_mail;
	private JTextField tf_major;
	private JTextField tf_stid;
	private JTextField tf_group;
	private JTextField tf_hash;
	JComboBox<String> cb_day, cb_month;

	private String name, num, mail, major, hash, stid, sex, groupname, month, day;
	ButtonGroup group;

	public AddPerson() {

		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage("rsc\\logo_icon.png"));
		setTitle("AddPerson");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setBounds(310, 115, 398, 775);

		/**
		 * @author ������
		 * ���� ���� �����ϴ� ���� ��ư
		 * ������ ������ sex�� ����
		 */
		
		JRadioButton[] gender = new JRadioButton[2];
		String[] text = { "����", "����" };
		JPanel genderpanel = new JPanel();
		genderpanel.setBackground(new Color(245, 244, 243));
		genderpanel.setBounds(170, 542, 200, 40);
		contentPane.add(genderpanel);
		ButtonGroup group = new ButtonGroup();
		for (int i = 0; i < gender.length; i++) {
			gender[i] = new JRadioButton(text[i]);
			group.add(gender[i]);
			genderpanel.add(gender[i]);
		}
		class MyItemListener implements ItemListener {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.DESELECTED)
					return;
				if (gender[0].isSelected())
					sex = "����";
				else
					sex = "����";
			}
		}

		/**
		 * @author ������
		 * �̸��� �߰��ϴ� �ؽ�Ʈ�ʵ�
		 */
		
		tf_name = new JTextField();
		tf_name.setBackground(new Color(245, 244, 243));
		tf_name.setFont(new Font("���� ���", Font.PLAIN, 25));
		tf_name.setForeground(Color.BLACK);
		tf_name.setColumns(10);
		tf_name.setBounds(185, 70, 170, 40);
		contentPane.add(tf_name);

		/**
		 * @author ������
		 * ��ȭ��ȣ�� �߰��ϴ� �ؽ�Ʈ�ʵ�
		 */
		
		tf_num = new JTextField();
		tf_num.setBackground(new Color(245, 244, 243));
		tf_num.setFont(new Font("���� ���", Font.PLAIN, 25));
		tf_num.setForeground(Color.BLACK);
		tf_num.setColumns(15);
		tf_num.setBounds(170, 180, 200, 40);
		contentPane.add(tf_num);

		/**
		 * @author ������
		 * �̸����� �߰��ϴ� �ؽ�Ʈ�ʵ�
		 */
		
		tf_mail = new JTextField();
		tf_mail.setBackground(new Color(245, 244, 243));
		tf_mail.setFont(new Font("���� ���", Font.PLAIN, 25));
		tf_mail.setForeground(Color.BLACK);
		tf_mail.setColumns(10);
		tf_mail.setBounds(170, 240, 200, 40);
		contentPane.add(tf_mail);
		
		/**
		 * @author ������
		 * ������ �߰��ϴ� �ؽ�Ʈ�ʵ�
		 */
		
		tf_major = new JTextField();
		tf_major.setBackground(new Color(245, 244, 243));
		tf_major.setFont(new Font("���� ���", Font.PLAIN, 25));
		tf_major.setForeground(Color.BLACK);
		tf_major.setColumns(10);
		tf_major.setBounds(170, 300, 200, 40);
		contentPane.add(tf_major);

		/**
		 * @author ������
		 * �й��� �߰��ϴ� �ؽ�Ʈ�ʵ�
		 */
		
		tf_stid = new JTextField();
		tf_stid.setBackground(new Color(245, 244, 243));
		tf_stid.setFont(new Font("���� ���", Font.PLAIN, 25));
		tf_stid.setForeground(Color.BLACK);
		tf_stid.setColumns(10);
		tf_stid.setBounds(170, 360, 200, 40);
		contentPane.add(tf_stid);

		/**
		 * @author ������
		 * �׷��� �߰��ϴ� �ؽ�Ʈ�ʵ�
		 */
		
		tf_group = new JTextField();
		tf_group.setBackground(new Color(245, 244, 243));
		tf_group.setFont(new Font("���� ���", Font.PLAIN, 25));
		tf_group.setForeground(Color.BLACK);
		tf_group.setColumns(10);
		tf_group.setBounds(170, 480, 200, 40);
		contentPane.add(tf_group);

		/**
		 * @author ������
		 * �ؽ��±׸� �߰��ϴ� �ؽ�Ʈ�ʵ�
		 */
		
		tf_hash = new JTextField();
		tf_hash.setBackground(new Color(245, 244, 243));
		tf_hash.setFont(new Font("���� ���", Font.PLAIN, 25));
		tf_hash.setForeground(Color.BLACK);
		tf_hash.setColumns(10);
		tf_hash.setBounds(170, 600, 200, 40);
		contentPane.add(tf_hash);

		/**
		 * @author ������
		 * ������ ���� �����ϴ� �޺��ڽ�
		 */
		
		String[] monthArr = { "1��", "2��", "3��", "4��", "5��", "6��", "7��", "8��", "9��", "10��", "11��", "12��" };
		JComboBox<String> cb_month = new JComboBox<String>();
		for (int i = 0; i < monthArr.length; i++) {
			cb_month.addItem(monthArr[i]);
		}
		cb_month.setSelectedIndex(0);
		cb_month.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				month = cb_month.getSelectedItem().toString();
			}

		});
		cb_month.setFont(new Font("���� ���", Font.PLAIN, 25));
		cb_month.setForeground(Color.BLACK);
		cb_month.setBackground(new Color(245, 244, 243));
		cb_month.setBounds(170, 420, 95, 40);
		contentPane.add(cb_month);

		/**
		 * @author ������
		 * ������ ���� �����ϴ� �޺��ڽ�
		 */
		
		String[] dayArr = { "1��", "2��", "3��", "4��", "5��", "6��", "7��", "8��", "9��", "10��", "11��", "12��", "13��", "14��",
				"15��", "16��", "17��", "18��", "19��", "20��", "21��", "22��", "23��", "24��", "25��", "26��", "27��", "28��", "29��",
				"30��", "31��" };
		JComboBox<String> cb_day = new JComboBox<String>();
		for (int i = 0; i < dayArr.length; i++) {
			cb_day.addItem(dayArr[i]);
		}
		cb_day.setSelectedIndex(0);
		cb_day.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				month = cb_day.getSelectedItem().toString();
			}

		});
		cb_day.setFont(new Font("���� ���", Font.PLAIN, 25));
		cb_day.setForeground(Color.BLACK);
		cb_day.setBackground(new Color(245, 244, 243));
		cb_day.setBounds(275, 420, 95, 40);
		contentPane.add(cb_day);

		/**
		 * @author ������
		 * AddPerson â�� �ݴ� ��ư
		 */
		
		JButton btn_close = new JButton("");
		btn_close.setBorderPainted(false);
		btn_close.setIcon(new ImageIcon("rsc\\icon\\btn_close.png"));
		btn_close.setBounds(275, 694, 110, 46);
		btn_close.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reset();
				Frame.frame_addperson.setVisible(false);
			}
		});
		contentPane.setLayout(null);
		contentPane.add(btn_close);

		/**
		 * @author ������
		 * AddPerson�� �Է��� �ڷ���� DB�� �Է��ϴ� ��ư
		 */
		
		JButton btn_save = new JButton("");
		btn_save.setBorderPainted(false);
		btn_save.setIcon(new ImageIcon("rsc\\icon\\btn_save.png"));
		btn_save.setBounds(162, 694, 110, 46);
		btn_save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AddPerson();
			}
		});

		contentPane.setLayout(null);
		contentPane.add(btn_save);

		JLabel lb_background = new JLabel(new ImageIcon("rsc\\Addperson.png"));
		lb_background.setBackground(Color.WHITE);
		lb_background.setBounds(5, 5, 382, 730);
		contentPane.add(lb_background);
		setContentPane(contentPane);

	}

	public void AddPerson() {

		DB db = new DB();

		name = tf_name.getText();
		num = tf_num.getText();
		mail = tf_mail.getText();
		major = tf_major.getText();
		stid = tf_stid.getText();
		hash = tf_hash.getText();
		groupname = tf_group.getText();

		/**
		 * @author ������
		 * �Էµ� �̸��� �˻��Ͽ� ���̰� �ʹ� ª�ٸ� �����޼����� ����
		 */
		
		if (name.length() > 4) { 
			JOptionPane.showMessageDialog(Frame.frame_addperson, "�̸��� 2~6���� �ѱ۸� ��� �����մϴ�.", "����",
					JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		/**
		 * @author ������
		 * �Էµ� ��ȣ�� �˻��Ͽ� ���ڰ� �ƴϰų� ���̰� ���� ������ �����޼����� ����
		 */
		
		if (num.length() != 11) { 
			JOptionPane.showMessageDialog(Frame.frame_addperson, "��ȣ�� ���ڸ� ��� �����մϴ�.", "����", JOptionPane.ERROR_MESSAGE);
			return;
		}
		for (char c : num.toCharArray()) {
			if (!Character.isDigit(c)) { 
				JOptionPane.showMessageDialog(Frame.frame_addperson, "��ȣ�� ���ڸ� ��� �����մϴ�.", "����",
						JOptionPane.ERROR_MESSAGE);
				return;
			}
		}

		/**
		 * @author ������
		 * �Էµ� �̸����� �˻��Ͽ� /@��ư�� ���ų� .�� ���ٸ� �����޼����� ����  
		 */
		
		int AtNum = 0, DotNum = 0; 

		for (char c : mail.toCharArray()) {
			if (c == '@')
				AtNum++;
			if (c == '.')
				DotNum++;
		}
		if (AtNum != 1 || DotNum != 1) {
			JOptionPane.showMessageDialog(Frame.frame_addperson, "�ùٸ� ������ �̸����� �ƴմϴ�.", "����", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		/**
		 * @author ������
		 * �Էµ� �й��� �˻��Ͽ� ���ڰ� �ƴϰŶ� ���̰� 6�� �ƴϸ� �����޼����� ���� 
		 */
		
		if (stid.length() != 6) { 
			JOptionPane.showMessageDialog(Frame.frame_addperson, "�й��� 6�ڸ� ���ڸ� ��� �����մϴ�.", "����",
					JOptionPane.ERROR_MESSAGE);
			return;
		}
		for (char c : stid.toCharArray()) {
			if (!Character.isDigit(c)) { // ���ڰ� �ƴϸ�
				JOptionPane.showMessageDialog(Frame.frame_addperson, "�й��� ���ڸ� ��� �����մϴ�.", "����",
						JOptionPane.ERROR_MESSAGE);
				return;
			}
		}

		db.insertAddress(name, num, mail, major, stid, month, day, groupname, hash, sex);
		db.getAddress();
		JOptionPane.showMessageDialog(Frame.frame_addperson, "�ι��� ���������� �߰� �Ǿ����ϴ�.", "�ι��߰� �Ϸ�",
				JOptionPane.INFORMATION_MESSAGE);
		Frame.frame_addperson.setVisible(false);

	}
	
	/**
	 * @author ������
	 * â�� ���� �� �ڷ��� �Է������ ����ִ� �޼ҵ�
	 */
	
	void reset() {
		tf_name.setText("");
		tf_num.setText("");
		tf_mail.setText("");
		tf_major.setText("");
		tf_stid.setText("");
		tf_group.setText("");
		tf_hash.setText("");
		cb_month.setSelectedIndex(0);
		cb_day.setSelectedIndex(0);

	}
}
