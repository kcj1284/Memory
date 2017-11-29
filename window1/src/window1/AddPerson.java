package window1;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

/**
 * @author ������ Home���� �ι��� �߰��ϴ� Class
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
	private JCheckBox cb_man;
	private JCheckBox cb_man;
	JComboBox<String> cb_day,cb_month;

	private String name, num, mail, major, stid, group, sns, hash;
	private int day,month;
	
	boolean ch_man = true;
	
	public AddPerson() {

		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage("rsc\\logo_icon.png"));
		setTitle("AddPerson");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setBounds(310, 115, 398, 775);
		
		tf_name = new JTextField();
		tf_name.setBackground(Color.LIGHT_GRAY);
		tf_name.setFont(new Font("���� ���", Font.PLAIN, 25));
		tf_name.setForeground(Color.WHITE);
		tf_name.setColumns(10);
		tf_name.setBounds(185, 70, 170, 40);
		contentPane.add(tf_name);

		tf_num = new JTextField();
		tf_num.setBackground(Color.LIGHT_GRAY);
		tf_num.setFont(new Font("���� ���", Font.PLAIN, 25));
		tf_num.setForeground(Color.WHITE);
		tf_num.setColumns(15);
		tf_num.setBounds(170, 180, 200, 40);
		contentPane.add(tf_num);

		tf_mail = new JTextField();
		tf_mail.setBackground(Color.LIGHT_GRAY);
		tf_mail.setFont(new Font("���� ���", Font.PLAIN, 25));
		tf_mail.setForeground(Color.WHITE);
		tf_mail.setColumns(10);
		tf_mail.setBounds(170, 240, 200, 40);
		contentPane.add(tf_mail);

		tf_major = new JTextField();
		tf_major.setBackground(Color.LIGHT_GRAY);
		tf_major.setFont(new Font("���� ���", Font.PLAIN, 25));
		tf_major.setForeground(Color.WHITE);
		tf_major.setColumns(10);
		tf_major.setBounds(170, 300, 200, 40);
		contentPane.add(tf_major);

		tf_stid = new JTextField();
		tf_stid.setBackground(Color.LIGHT_GRAY);
		tf_stid.setFont(new Font("���� ���", Font.PLAIN, 25));
		tf_stid.setForeground(Color.WHITE);
		tf_stid.setColumns(10);
		tf_stid.setBounds(170, 360, 200, 40);
		contentPane.add(tf_stid);

		tf_group = new JTextField();
		tf_group.setBackground(Color.LIGHT_GRAY);
		tf_group.setFont(new Font("���� ���", Font.PLAIN, 25));
		tf_group.setForeground(Color.WHITE);
		tf_group.setColumns(10);
		tf_group.setBounds(170, 480, 200, 40);
		contentPane.add(tf_group);

		tf_hash = new JTextField();
		tf_hash.setBackground(Color.LIGHT_GRAY);
		tf_hash.setFont(new Font("���� ���", Font.PLAIN, 25));
		tf_hash.setForeground(Color.WHITE);
		tf_hash.setColumns(10);
		tf_hash.setBounds(170, 600, 200, 40);
		contentPane.add(tf_hash);
		
		cb_month = new JComboBox<String>();
		cb_month.setFont(new Font("���� ���", Font.PLAIN, 25));
		cb_month.setForeground(Color.WHITE);
		cb_month.addItem("1");
		cb_month.setBounds(170, 420, 95, 40);
		contentPane.add(cb_month);
		
		cb_day = new JComboBox<String>();
		cb_day.setFont(new Font("���� ���", Font.PLAIN, 25));
		cb_day.setForeground(Color.WHITE);
		cb_day.addItem("1");
		cb_day.setBounds(275, 420, 95, 40);
		contentPane.add(cb_day);

		JButton btn_save = new JButton("");
		btn_save.setBorderPainted(false);
		btn_save.setIcon(new ImageIcon("rsc\\icon\\btn_save.png"));
		btn_save.setBounds(162, 694, 110, 46);
		btn_save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Frame.frame_addperson.setVisible(false);
			}
		});
		contentPane.setLayout(null);
		contentPane.add(btn_save);
		
		cb_man = new JCheckBox("����",ch_man);
		cb_man.setFont(new Font("����", Font.PLAIN, 20));
		cb_man.setBounds(170, 538, 96, 45);
		contentPane.add(cb_man);
		
		cb_man = new JCheckBox("����",ch_man);
		cb_man.setFont(new Font("����", Font.PLAIN, 20));
		cb_man.setBounds(272, 538, 96, 45);
		contentPane.add(cb_man);
		
		JButton btn_close = new JButton("");
		btn_close.setBorderPainted(false);
		btn_close.setIcon(new ImageIcon("rsc\\icon\\btn_close.png"));
		btn_close.setBounds(275, 694, 110, 46);
		btn_close.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Frame.frame_addperson.setVisible(false);
			}
		});
		contentPane.setLayout(null);
		contentPane.add(btn_close);

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
		group = tf_group.getText();
		hash = tf_hash.getText();
		month = cb_month.getSelectedIndex();
		day = cb_day.getSelectedIndex();
		
		
		///////////// name ///////////////
		if (name.length() < 4 || name.length() > 12) { // ���̵��� ���̰� ª�ų� ���
			JOptionPane.showMessageDialog(Frame.frame_addperson, "�̸��� 2~6���� �ѱ۸� ��� �����մϴ�.", "����",
					JOptionPane.ERROR_MESSAGE);
			return;
		}
		///////////// num ///////////////
		if (num.length() != 11) { // ��ȣ�� ���̰� 11�� �ƴϸ�
			JOptionPane.showMessageDialog(Frame.frame_addperson, "��ȣ�� ���ڸ� ��� �����մϴ�.", "����", JOptionPane.ERROR_MESSAGE);
			return;
		}
		for (char c : num.toCharArray()) {
			if (!Character.isDigit(c)) { // ���ڰ� �ƴϸ�
				JOptionPane.showMessageDialog(Frame.frame_addperson, "��ȣ�� ���ڸ� ��� �����մϴ�.", "����",
						JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
		///////////// email ///////////////
		int AtNum = 0, DotNum = 0; // @�̶� .�� ����

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
		///////////// stid ///////////////
		if (stid.length() != 6) { // �й��� ���̰� 6�� �ƴϸ�
			JOptionPane.showMessageDialog(Frame.frame_addperson, "�й��� 6�ڸ� ���ڸ� ��� �����մϴ�.", "����", JOptionPane.ERROR_MESSAGE);
			return;
		}
		for (char c : stid.toCharArray()) {
			if (!Character.isDigit(c)) { // ���ڰ� �ƴϸ�
				JOptionPane.showMessageDialog(Frame.frame_addperson, "�й��� ���ڸ� ��� �����մϴ�.", "����",
						JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
		
		/* DB������ �ּ���.
		db.insert(name, num, mail, major, stid, month, day, group, sns, hash);
		db.getMember();
		JOptionPane.showMessageDialog(Frame.frame_addperson, "�ο��� ���������� �߰� �Ǿ����ϴ�.", "�ι��߰� �Ϸ�", JOptionPane.INFORMATION_MESSAGE);
		Frame.frame_join.setVisible(false);
		*/
	}
	void reset(){
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
