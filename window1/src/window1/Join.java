package window1;

import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;

/**
 * @author김찬중 
 * 회원가입을 하는 Class
 */
public class Join extends JFrame {

	private JPanel contentPane;
	private JTextField tf_id;
	private JTextField tf_email;
	private JTextField tf_name;
	private JTextField tf_pwAnswer;
	private JPasswordField tf_pw;
	private JPasswordField tf_pwCheck;
	JComboBox<String> cb_pwQuestion;

	private String id, pw, pwCheck, email, name, pwAnswer;
	private int pwQuestion;

	public Join() {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(".\\rsc\\logo_icon.png"));
		setTitle("Join");
		setBounds(100, 100, 403, 575);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		/**
		 * @author 김찬중
		 * id를 입력하는 텍스트필드
		 */
		
		tf_id = new JTextField();
		tf_id.setOpaque(false);
		tf_id.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		tf_id.setForeground(Color.WHITE);
		tf_id.setColumns(10);
		tf_id.setBounds(144, 128, 145, 24);
		contentPane.add(tf_id);

		/**
		 * @author 김찬중
		 * 이메일을 입력하는 텍스트필드
		 */
		
		tf_email = new JTextField();
		tf_email.setOpaque(false);
		tf_email.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		tf_email.setForeground(Color.WHITE);
		tf_email.setColumns(10);
		tf_email.setBounds(144, 255, 217, 24);
		contentPane.add(tf_email);

		/**
		 * @author 김찬중
		 * 이름을 입력하는 텍스트필드
		 */
		
		tf_name = new JTextField();
		tf_name.setOpaque(false);
		tf_name.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		tf_name.setForeground(Color.WHITE);
		tf_name.setColumns(10);
		tf_name.setBounds(144, 295, 145, 24);
		contentPane.add(tf_name);

		/**
		 * @author 김찬중
		 * 비밀번호 질문의 답변을 입력하는 텍스트필드
		 */
		
		tf_pwAnswer = new JTextField();
		tf_pwAnswer.setOpaque(false);
		tf_pwAnswer.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		tf_pwAnswer.setForeground(Color.WHITE);
		tf_pwAnswer.setColumns(10);
		tf_pwAnswer.setBounds(144, 378, 145, 24);
		contentPane.add(tf_pwAnswer);

		/**
		 * @author 김찬중
		 * 비밀번호를 입력하는 패스워드필드
		 */
		
		tf_pw = new JPasswordField();
		tf_pw.setOpaque(false);
		tf_pw.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		tf_pw.setForeground(Color.WHITE);
		tf_pw.setBounds(144, 172, 145, 24);
		contentPane.add(tf_pw);

		/**
		 * @author 김찬중
		 * 비밀번호를 확인해주는 패스워드필드
		 */
		
		tf_pwCheck = new JPasswordField();
		tf_pwCheck.setOpaque(false);
		tf_pwCheck.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		tf_pwCheck.setForeground(Color.WHITE);
		tf_pwCheck.setBounds(144, 214, 145, 24);
		contentPane.add(tf_pwCheck);

		/**
		 * @author 김찬중
		 * 입력된 자료들을 DB로 보내는 버튼
		 */
		
		JButton btn_join = new JButton("");
		btn_join.setIcon(new ImageIcon(".\\rsc\\icon\\btn_post.PNG"));
		btn_join.setBorderPainted(false);
		btn_join.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				join();
			}
		});
		btn_join.setBounds(125, 455, 167, 35);
		contentPane.add(btn_join);

		/**
		 * @author 김찬중
		 * 비밀번호를 찾기위한 콤보박스
		 */
		
		cb_pwQuestion = new JComboBox<String>();
		cb_pwQuestion.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		cb_pwQuestion.setForeground(Color.WHITE);
		cb_pwQuestion.setBackground(new Color(54, 114, 87));
		cb_pwQuestion.addItem("나의 장래희망은?");
		cb_pwQuestion.addItem("나의 보물 1호는?");
		cb_pwQuestion.addItem("가장 기억에 남는 선생님 성함은?");
		cb_pwQuestion.addItem("가장 기억에 남는 장소는?");
		cb_pwQuestion.addItem("나의 어머니의 성함은?");
		cb_pwQuestion.addItem("나의 아버지의 성함은?");
		cb_pwQuestion.setBounds(144, 336, 217, 24);
		contentPane.add(cb_pwQuestion);

		try {
			JLabel lb_background = new JLabel(new ImageIcon(ImageIO.read(new File(".\\rsc\\join.png"))));
			lb_background.setBackground(Color.WHITE);
			lb_background.setBounds(0, 0, 400, 550);
			contentPane.add(lb_background);
		} catch (IOException s) {
			s.printStackTrace();
		}
	}

	public void join() {
		DB db = new DB();
		
			id = tf_id.getText();
			pw = String.valueOf(tf_pw.getPassword());
			pwCheck = String.valueOf(tf_pwCheck.getPassword());
			email = tf_email.getText();
			name = tf_name.getText();
			pwQuestion = cb_pwQuestion.getSelectedIndex();
			pwAnswer = tf_pwAnswer.getText();

			/**
			 * @author 김찬중
			 * 입력된 id가 영어인지 확인하고 길이를 체크하고 중복을 체크한 후 조건에 맞지 않으면 오류메세지를 띄운다
			 */
			
			if (id.length() < 4 || id.length() > 12) {
				JOptionPane.showMessageDialog(Frame.frame_join, "아이디는 4~12자의 영문, 숫자만 사용 가능합니다.", "오류",
						JOptionPane.ERROR_MESSAGE);
				return;
			}
			for (char c : id.toCharArray()) {
				if (!Character.isAlphabetic(c) && !Character.isDigit(c)) { 
					JOptionPane.showMessageDialog(Frame.frame_join, "아이디는 4~12자의 영문, 숫자만 사용 가능합니다.", "오류",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
			}
			for (int i = 0; i < Data.member_vector.size(); i++) {
				if (Data.member_vector.get(i).id.equals(id)) {
					JOptionPane.showMessageDialog(Frame.frame_join, "이미 존재하는 아이디입니다.", "오류", JOptionPane.ERROR_MESSAGE);
					return;
				}
			}

			/**
			 * @author 김찬중
			 * 입력된 비밀번호의 길이와 영어와 숫자인지 확인하고 맞지 않으면 오류메세지를 띄운다
			 */
			if (pw.length() < 4 || pw.length() > 12) { 
				JOptionPane.showMessageDialog(Frame.frame_join, "비밀번호는 4~12자의 영문, 숫자만 사용 가능합니다.", "오류",
						JOptionPane.ERROR_MESSAGE);
				return;
			}
			for (char c : pw.toCharArray()) {
				if (!Character.isAlphabetic(c) && !Character.isDigit(c)) { 
					JOptionPane.showMessageDialog(Frame.frame_join, "비밀번호는 4~12자의 영문, 숫자만 사용 가능합니다.", "오류",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
			}
			if (!pw.equals(pwCheck)) { 
				JOptionPane.showMessageDialog(Frame.frame_join, "비밀번호가 일치하지 않습니다.", "오류", JOptionPane.ERROR_MESSAGE);
				return;
			}

			/**
			 * @author 김찬중
			 * 입력된 이메일을 검사하여 /@버튼이 없거나 .이 없다면 오류메세지를 띄운다  
			 */
			
			int AtNum = 0, DotNum = 0;  

			for (char c : email.toCharArray()) {
				if (c == '@')
					AtNum++;
				if (c == '.')
					DotNum++;
			}
			if (AtNum != 1 || DotNum != 1) {
				JOptionPane.showMessageDialog(Frame.frame_join, "올바른 형식의 이메일이 아닙니다.", "오류", JOptionPane.ERROR_MESSAGE);
				return;
			}

			/**
			 * @author 김찬중
			 * 이름이 입력되지 않으면 에러메세지를 띄운다
			 */
			
			if (name.length() == 0) {
				JOptionPane.showMessageDialog(Frame.frame_join, "이름을 입력해주세요.", "오류", JOptionPane.ERROR_MESSAGE);
				return;
			}

			/**
			 * @author 김찬중
			 * 비밀번호 답이 입력되지 않으면 오류메세지를 띄운다  
			 */
			if (pwAnswer.length() == 0) {
				JOptionPane.showMessageDialog(Frame.frame_join, "비밀번호 찾기 답을 입력해주세요.", "오류", JOptionPane.ERROR_MESSAGE);
				return;
			}

			db.insertMember(id, pw, email, name, pwQuestion, pwAnswer);
			//db.addressBook(name);
			db.getMember();
			JOptionPane.showMessageDialog(Frame.frame_join, "회원가입이 정상적으로 완료되었습니다.", "회원가입 완료",
					JOptionPane.INFORMATION_MESSAGE);
			Frame.frame_join.setVisible(false);
		
	}

	/**
	 * @author 김찬중
	 * 창이 닫힌 후 자료의 입력장들을 비워주는 메소드
	 */
	
	void reset() {
		tf_id.setText("");
		tf_pw.setText("");
		tf_pwCheck.setText("");
		tf_email.setText("");
		tf_name.setText("");
		cb_pwQuestion.setSelectedIndex(0);
		tf_pwAnswer.setText("");
	}
}