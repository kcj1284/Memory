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
 * @author 김찬중 Home에서 인물을 추가하는 Class
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

	private String name, num, mail, major, stid, hash, sex;
	ButtonGroup group;
	private int day, month;

	public AddPerson() {

		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage("rsc\\logo_icon.png"));
		setTitle("AddPerson");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setBounds(310, 115, 398, 775);
		
		JRadioButton [] gender = new JRadioButton[2];
		String [] text = {"남자","여자"};
		JPanel genderpanel = new JPanel();
		genderpanel.setBounds(170, 542, 200, 40);
		contentPane.add(genderpanel);
		ButtonGroup group = new ButtonGroup();
		for(int i = 0;i<gender.length;i++) {
			gender[i] = new JRadioButton(text[i]);
			group.add(gender[i]);
			genderpanel.add(gender[i]);
			gender[i].addItemListener(new ItemListener() {
					public void itemStateChanged(ItemEvent e) {
						if(e.getStateChange()==ItemEvent.DESELECTED)
							return;
						if(gender[0].isSelected())
							sex = "남자";
						else
							sex = "여자";
					}
			});
		}
		
		tf_name = new JTextField();
		tf_name.setBackground(Color.LIGHT_GRAY);
		tf_name.setFont(new Font("맑은 고딕", Font.PLAIN, 25));
		tf_name.setForeground(Color.WHITE);
		tf_name.setColumns(10);
		tf_name.setBounds(185, 70, 170, 40);
		contentPane.add(tf_name);

		tf_num = new JTextField();
		tf_num.setBackground(Color.LIGHT_GRAY);
		tf_num.setFont(new Font("맑은 고딕", Font.PLAIN, 25));
		tf_num.setForeground(Color.WHITE);
		tf_num.setColumns(15);
		tf_num.setBounds(170, 180, 200, 40);
		contentPane.add(tf_num);

		tf_mail = new JTextField();
		tf_mail.setBackground(Color.LIGHT_GRAY);
		tf_mail.setFont(new Font("맑은 고딕", Font.PLAIN, 25));
		tf_mail.setForeground(Color.WHITE);
		tf_mail.setColumns(10);
		tf_mail.setBounds(170, 240, 200, 40);
		contentPane.add(tf_mail);

		tf_major = new JTextField();
		tf_major.setBackground(Color.LIGHT_GRAY);
		tf_major.setFont(new Font("맑은 고딕", Font.PLAIN, 25));
		tf_major.setForeground(Color.WHITE);
		tf_major.setColumns(10);
		tf_major.setBounds(170, 300, 200, 40);
		contentPane.add(tf_major);

		tf_stid = new JTextField();
		tf_stid.setBackground(Color.LIGHT_GRAY);
		tf_stid.setFont(new Font("맑은 고딕", Font.PLAIN, 25));
		tf_stid.setForeground(Color.WHITE);
		tf_stid.setColumns(10);
		tf_stid.setBounds(170, 360, 200, 40);
		contentPane.add(tf_stid);

		tf_group = new JTextField();
		tf_group.setBackground(Color.LIGHT_GRAY);
		tf_group.setFont(new Font("맑은 고딕", Font.PLAIN, 25));
		tf_group.setForeground(Color.WHITE);
		tf_group.setColumns(10);
		tf_group.setBounds(170, 480, 200, 40);
		contentPane.add(tf_group);

		tf_hash = new JTextField();
		tf_hash.setBackground(Color.LIGHT_GRAY);
		tf_hash.setFont(new Font("맑은 고딕", Font.PLAIN, 25));
		tf_hash.setForeground(Color.WHITE);
		tf_hash.setColumns(10);
		tf_hash.setBounds(170, 600, 200, 40);
		contentPane.add(tf_hash);
		
		String [] month = {"1","2","3","4","5","6","7","8","9","10","11","12"};
		JComboBox cb_month = new JComboBox(month);
		cb_month.setFont(new Font("맑은 고딕", Font.PLAIN, 25));
		cb_month.setForeground(Color.BLACK);
		cb_month.setBounds(170, 420, 95, 40);
		contentPane.add(cb_month);
		
		String [] day = {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"};
		JComboBox cb_day = new JComboBox(day);
		cb_day.setFont(new Font("맑은 고딕", Font.PLAIN, 25));
		cb_day.setForeground(Color.BLACK);
		cb_day.setBounds(275, 420, 95, 40);
		contentPane.add(cb_day);
		
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
		month = cb_month.getSelectedIndex();
		day = cb_day.getSelectedIndex();

		///////////// name ///////////////
		if (name.length() < 4 || name.length() > 12) { // 아이디의 길이가 짧거나 길면
			JOptionPane.showMessageDialog(Frame.frame_addperson, "이름은 2~6자의 한글만 사용 가능합니다.", "오류",
					JOptionPane.ERROR_MESSAGE);
			return;
		}
		///////////// num ///////////////
		if (num.length() != 11) { // 번호의 길이가 11이 아니면
			JOptionPane.showMessageDialog(Frame.frame_addperson, "번호는 숫자만 사용 가능합니다.", "오류", JOptionPane.ERROR_MESSAGE);
			return;
		}
		for (char c : num.toCharArray()) {
			if (!Character.isDigit(c)) { // 숫자가 아니면
				JOptionPane.showMessageDialog(Frame.frame_addperson, "번호는 숫자만 사용 가능합니다.", "오류",
						JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
		///////////// email ///////////////
		int AtNum = 0, DotNum = 0; // @이랑 .의 개수

		for (char c : mail.toCharArray()) {
			if (c == '@')
				AtNum++;
			if (c == '.')
				DotNum++;
		}
		if (AtNum != 1 || DotNum != 1) {
			JOptionPane.showMessageDialog(Frame.frame_addperson, "올바른 형식의 이메일이 아닙니다.", "오류", JOptionPane.ERROR_MESSAGE);
			return;
		}
		///////////// stid ///////////////
		if (stid.length() != 6) { // 학번의 길이가 6이 아니면
			JOptionPane.showMessageDialog(Frame.frame_addperson, "학번은 6자리 숫자만 사용 가능합니다.", "오류",
					JOptionPane.ERROR_MESSAGE);
			return;
		}
		for (char c : stid.toCharArray()) {
			if (!Character.isDigit(c)) { // 숫자가 아니면
				JOptionPane.showMessageDialog(Frame.frame_addperson, "학번은 숫자만 사용 가능합니다.", "오류",
						JOptionPane.ERROR_MESSAGE);
				return;
			}
		}

		/*
		 * db.insert(name, num, mail, major, stid, month, day, group, hash,sex);
		 * db.getMember(); JOptionPane.showMessageDialog(Frame.frame_addperson,
		 * "인물이 정상적으로 추가 되었습니다.", "인물추가 완료", JOptionPane.INFORMATION_MESSAGE);
		 * Frame.frame_join.setVisible(false);
		 */
	}

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
