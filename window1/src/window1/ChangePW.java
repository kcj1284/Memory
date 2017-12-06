package window1;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

/**
 * @author 김찬중
 * 새로운 비밀번호를 설정할 수 있도록 해주는 Class
 */
@SuppressWarnings("serial")
public class ChangePW extends JFrame {

	private JPanel contentPane;
	private JPasswordField tf_pwCheck;
	private JPasswordField tf_pw;

	public ChangePW() {
		setResizable(false);
		setTitle("비밀번호 변경");
		setIconImage(Toolkit.getDefaultToolkit().getImage("rsc\\logo_icon.png"));
		setBounds(100, 100, 410, 316);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btn_change = new JButton();
		btn_change.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String pw = new String(tf_pw.getPassword());
				String pwCheck = new String(tf_pwCheck.getPassword());

				if (pw.length() < 4 || pw.length() > 12) { // 비밀번호의 길이가 짧거나 길면
					JOptionPane.showMessageDialog(Frame.frame_join, "비밀번호는 4~12자의 영문, 숫자만 사용 가능합니다.", "오류",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				for (char c : pw.toCharArray()) {
					if (!Character.isAlphabetic(c) && !Character.isDigit(c)) { // 비밀번호가 영어 또는 숫자가 아니면
						JOptionPane.showMessageDialog(Frame.frame_join, "비밀번호는 4~12자의 영문, 숫자만 사용 가능합니다.", "오류",
								JOptionPane.ERROR_MESSAGE);
						return;
					}
				}
				if (!pw.equals(pwCheck)) { // 비밀번호랑 비밀번호확인이랑 다르면
					JOptionPane.showMessageDialog(Frame.frame_join, "비밀번호가 일치하지 않습니다.", "오류",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				changePW(pw);
				JOptionPane.showMessageDialog(Frame.frame_join, "비밀번호가 성공적으로 변경되었습니다.", "비밀번호 변경 완료",
						JOptionPane.INFORMATION_MESSAGE);
				Frame.frame_changePW.setVisible(false);
			}
		});

		btn_change.setIcon(new ImageIcon("rsc\\icon\\btn_change.PNG"));
		btn_change.setBounds(123, 203, 165, 33);
		btn_change.setBorderPainted(false);
		contentPane.add(btn_change);
		
				tf_pwCheck = new JPasswordField();
				tf_pwCheck.setForeground(Color.BLACK);
				tf_pwCheck.setFont(new Font("굴림", Font.PLAIN, 18));
				tf_pwCheck.setColumns(10);
				tf_pwCheck.setOpaque(false);
				tf_pwCheck.setBounds(123, 149, 165, 24);
				contentPane.add(tf_pwCheck);
		
				tf_pw = new JPasswordField();
				tf_pw.setForeground(Color.BLACK);
				tf_pw.setFont(new Font("굴림", Font.PLAIN, 18));
				tf_pw.setColumns(10);
				tf_pw.setOpaque(false);
				tf_pw.setBounds(123, 106, 165, 24);
				contentPane.add(tf_pw);

		JLabel lb_background = new JLabel();
		lb_background.setIcon(new ImageIcon("rsc\\PWChange.png"));
		lb_background.setBounds(0, 0, 400, 277);
		contentPane.add(lb_background);
	}

	private void changePW(String pw) {
		String id = Data.member_vector.get(Data.userIndex).id;
		DB.inputQuery("update member set pw = '" + pw + "' where id = '" + id + "'");
		DB.getMember();
	}

}