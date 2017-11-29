package window1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
/**
 * @author 김찬중
 * 프로그램의 메인화면
 */
public class Home extends JFrame {

	private JPanel contentPane;
	private JTextField tf_search;

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

		String[] element = new String[5];
		element[0] = "이름";
		element[1] = "학번";
		element[2] = "학과";
		element[3] = "번호";
		element[4] = "해쉬태그";
		JComboBox cb_type = new JComboBox(element);
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

		JLabel lb_background = new JLabel(new ImageIcon("rsc\\Home.jpg"));
		lb_background.setBackground(Color.WHITE);
		lb_background.setBounds(0, 0, 1642, 800);
		contentPane.add(lb_background);


	}
}