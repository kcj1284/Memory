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
 * @author 김찬중
 * Home에서 인물을 추가하는 Class
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
		 * @author 김찬중
		 * 남자 여자 선택하는 라디오 버튼
		 * 선택한 성별을 sex에 저장
		 */
		
		JRadioButton[] gender = new JRadioButton[2];
		String[] text = { "남자", "여자" };
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
					sex = "남자";
				else
					sex = "여자";
			}
		}

		/**
		 * @author 김찬중
		 * 이름을 추가하는 텍스트필드
		 */
		
		tf_name = new JTextField();
		tf_name.setBackground(new Color(245, 244, 243));
		tf_name.setFont(new Font("맑은 고딕", Font.PLAIN, 25));
		tf_name.setForeground(Color.BLACK);
		tf_name.setColumns(10);
		tf_name.setBounds(185, 70, 170, 40);
		contentPane.add(tf_name);

		/**
		 * @author 김찬중
		 * 전화번호를 추가하는 텍스트필드
		 */
		
		tf_num = new JTextField();
		tf_num.setBackground(new Color(245, 244, 243));
		tf_num.setFont(new Font("맑은 고딕", Font.PLAIN, 25));
		tf_num.setForeground(Color.BLACK);
		tf_num.setColumns(15);
		tf_num.setBounds(170, 180, 200, 40);
		contentPane.add(tf_num);

		/**
		 * @author 김찬중
		 * 이메일을 추가하는 텍스트필드
		 */
		
		tf_mail = new JTextField();
		tf_mail.setBackground(new Color(245, 244, 243));
		tf_mail.setFont(new Font("맑은 고딕", Font.PLAIN, 25));
		tf_mail.setForeground(Color.BLACK);
		tf_mail.setColumns(10);
		tf_mail.setBounds(170, 240, 200, 40);
		contentPane.add(tf_mail);
		
		/**
		 * @author 김찬중
		 * 전공을 추가하는 텍스트필드
		 */
		
		tf_major = new JTextField();
		tf_major.setBackground(new Color(245, 244, 243));
		tf_major.setFont(new Font("맑은 고딕", Font.PLAIN, 25));
		tf_major.setForeground(Color.BLACK);
		tf_major.setColumns(10);
		tf_major.setBounds(170, 300, 200, 40);
		contentPane.add(tf_major);

		/**
		 * @author 김찬중
		 * 학번을 추가하는 텍스트필드
		 */
		
		tf_stid = new JTextField();
		tf_stid.setBackground(new Color(245, 244, 243));
		tf_stid.setFont(new Font("맑은 고딕", Font.PLAIN, 25));
		tf_stid.setForeground(Color.BLACK);
		tf_stid.setColumns(10);
		tf_stid.setBounds(170, 360, 200, 40);
		contentPane.add(tf_stid);

		/**
		 * @author 김찬중
		 * 그룹을 추가하는 텍스트필드
		 */
		
		tf_group = new JTextField();
		tf_group.setBackground(new Color(245, 244, 243));
		tf_group.setFont(new Font("맑은 고딕", Font.PLAIN, 25));
		tf_group.setForeground(Color.BLACK);
		tf_group.setColumns(10);
		tf_group.setBounds(170, 480, 200, 40);
		contentPane.add(tf_group);

		/**
		 * @author 김찬중
		 * 해쉬태그를 추가하는 텍스트필드
		 */
		
		tf_hash = new JTextField();
		tf_hash.setBackground(new Color(245, 244, 243));
		tf_hash.setFont(new Font("맑은 고딕", Font.PLAIN, 25));
		tf_hash.setForeground(Color.BLACK);
		tf_hash.setColumns(10);
		tf_hash.setBounds(170, 600, 200, 40);
		contentPane.add(tf_hash);

		/**
		 * @author 김찬중
		 * 생일의 월을 선택하는 콤보박스
		 */
		
		String[] monthArr = { "1월", "2월", "3월", "4월", "5월", "6월", "7월", "8월", "9월", "10월", "11월", "12월" };
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
		cb_month.setFont(new Font("맑은 고딕", Font.PLAIN, 25));
		cb_month.setForeground(Color.BLACK);
		cb_month.setBackground(new Color(245, 244, 243));
		cb_month.setBounds(170, 420, 95, 40);
		contentPane.add(cb_month);

		/**
		 * @author 김찬중
		 * 생일의 일을 선택하는 콤보박스
		 */
		
		String[] dayArr = { "1일", "2일", "3일", "4일", "5일", "6일", "7일", "8일", "9일", "10일", "11일", "12일", "13일", "14일",
				"15일", "16일", "17일", "18일", "19일", "20일", "21일", "22일", "23일", "24일", "25일", "26일", "27일", "28일", "29일",
				"30일", "31일" };
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
		cb_day.setFont(new Font("맑은 고딕", Font.PLAIN, 25));
		cb_day.setForeground(Color.BLACK);
		cb_day.setBackground(new Color(245, 244, 243));
		cb_day.setBounds(275, 420, 95, 40);
		contentPane.add(cb_day);

		/**
		 * @author 김찬중
		 * AddPerson 창을 닫는 버튼
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
		 * @author 김찬중
		 * AddPerson에 입력한 자료들을 DB에 입력하는 버튼
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
		 * @author 김찬중
		 * 입력된 이름을 검사하여 길이가 너무 짧다면 오류메세지를 띄운다
		 */
		
		if (name.length() > 4) { 
			JOptionPane.showMessageDialog(Frame.frame_addperson, "이름은 2~6자의 한글만 사용 가능합니다.", "오류",
					JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		/**
		 * @author 김찬중
		 * 입력된 번호를 검사하여 숫자가 아니거나 길이가 맞지 않으면 오류메세지를 띄운다
		 */
		
		if (num.length() != 11) { 
			JOptionPane.showMessageDialog(Frame.frame_addperson, "번호는 숫자만 사용 가능합니다.", "오류", JOptionPane.ERROR_MESSAGE);
			return;
		}
		for (char c : num.toCharArray()) {
			if (!Character.isDigit(c)) { 
				JOptionPane.showMessageDialog(Frame.frame_addperson, "번호는 숫자만 사용 가능합니다.", "오류",
						JOptionPane.ERROR_MESSAGE);
				return;
			}
		}

		/**
		 * @author 김찬중
		 * 입력된 이메일을 검사하여 /@버튼이 없거나 .이 없다면 오류메세지를 띄운다  
		 */
		
		int AtNum = 0, DotNum = 0; 

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
		
		/**
		 * @author 김찬중
		 * 입력된 학번을 검사하여 숫자가 아니거라 길이가 6이 아니면 오류메세지를 띄운다 
		 */
		
		if (stid.length() != 6) { 
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

		db.insertAddress(name, num, mail, major, stid, month, day, groupname, hash, sex);
		db.getAddress();
		JOptionPane.showMessageDialog(Frame.frame_addperson, "인물이 정상적으로 추가 되었습니다.", "인물추가 완료",
				JOptionPane.INFORMATION_MESSAGE);
		Frame.frame_addperson.setVisible(false);

	}
	
	/**
	 * @author 김찬중
	 * 창이 닫힌 후 자료의 입력장들을 비워주는 메소드
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
