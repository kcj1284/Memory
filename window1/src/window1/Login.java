package window1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
/**
 * @author 김찬중
 * Login을 하는 Class
 */
public class Login extends JFrame {
	DB db = new DB();
	int index = -1;

	private JPanel contentPane;
	private String id;
	private String pw;
	private JTextField tf_id;
	private JPasswordField tf_pw;

	/**
	 * @author 김찬중
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Frame.frame_login
					.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	/**
	 * Create the frame.
	 */ 
	public Login() {
		setTitle("Login");
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage("rsc\\logo_icon.png"));
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(50, 30, 1303, 829);
  
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		tf_id = new JTextField();
		tf_id.setFont(new Font("맑은 고딕", Font.PLAIN, 25));
		tf_id.setBounds(170, 442, 250, 45);
		contentPane.add(tf_id);
		tf_id.setColumns(10);

		JButton btn_login = new JButton("");
		btn_login.setBorderPainted(false);
		btn_login.setIcon(new ImageIcon("rsc\\icon\\btn_login.png"));
		btn_login.setBounds(425, 442, 97, 97);
		contentPane.add(btn_login);

		JButton btn_join = new JButton("");
		btn_join.setBorderPainted(false);
		btn_join.setIcon(new ImageIcon("rsc\\icon\\btn_join.png"));
		btn_join.setBounds(530, 442, 97, 97);
		btn_join.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Frame.frame_join.reset();
				Frame.frame_join.setVisible(true);
			}
		});
		contentPane.add(btn_join);
		
		JButton btn_find = new JButton("");
		btn_find.setBorderPainted(false);
		btn_find.setIcon(new ImageIcon("rsc\\icon\\btn_join.png"));
		btn_find.setBounds(170, 551, 150, 51);
		btn_find.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Frame.frame_findIDPW.setVisible(true);
			}
		});
		contentPane.add(btn_find);
		
		tf_pw = new JPasswordField();
		tf_pw.setFont(new Font("맑은 고딕", Font.PLAIN, 25));
		tf_pw.setBounds(170, 494, 250, 45);
		contentPane.add(tf_pw);
		
		class Listener implements ActionListener, KeyListener{
			public void actionPerformed(ActionEvent arg0) {
				login();
			}

			public void keyPressed(KeyEvent e){
				if(e.getKeyCode() == KeyEvent.VK_ENTER)
					login();
			}

			public void keyReleased(KeyEvent e){}

			public void keyTyped(KeyEvent e){}
		}

		Listener listener = new Listener();

		btn_login.addActionListener(listener);
		tf_id.addActionListener(listener);
		tf_pw.addActionListener(listener);
		

		JLabel lb_background = new JLabel(new ImageIcon("rsc\\Login.jpg"));
		lb_background.setBackground(Color.WHITE);
		lb_background.setBounds(0, 0, 1300, 800);
		contentPane.add(lb_background);
	}

	/**
	 * @author 김찬중
	 * 유저 정보를 불러와서 이에 해당하는 계정이 있으면 로그인을 하는 메소드
	 */
	
	private void login(){
		db.getMember();
		id = tf_id.getText().toString();
		pw = new String(tf_pw.getPassword());
		if(id.length() == 0){
			JOptionPane.showMessageDialog(Frame.frame_login, "아이디를 입력해주세요.", "오류", JOptionPane.ERROR_MESSAGE);
			return;
		}
		if(pw.length() == 0){
			JOptionPane.showMessageDialog(Frame.frame_login, "비밀번호를 입력해주세요.", "오류", JOptionPane.ERROR_MESSAGE);
			return;
		}

		if((index = db.isPWCorrect(id, pw)) != -1){
			Data.userIndex = index;
			Frame.frame_home.setVisible(true); 
			Frame.frame_popup.setVisible(true); 
			tf_id.setText("");
			tf_pw.setText("");
			Frame.frame_login.setVisible(false); 
			return;
		} else{
			JOptionPane.showMessageDialog(Frame.frame_login, "아이디 또는 비밀번호가 일치하지 않습니다.", "오류", JOptionPane.ERROR_MESSAGE);
		}
	}
} 