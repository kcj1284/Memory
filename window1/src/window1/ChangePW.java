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
 * @author ������
 * ���ο� ��й�ȣ�� ������ �� �ֵ��� ���ִ� Class
 */
@SuppressWarnings("serial")
public class ChangePW extends JFrame {

	private JPanel contentPane;
	private JPasswordField tf_pwCheck;
	private JPasswordField tf_pw;

	public ChangePW() {
		setResizable(false);
		setTitle("��й�ȣ ����");
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

				if (pw.length() < 4 || pw.length() > 12) { // ��й�ȣ�� ���̰� ª�ų� ���
					JOptionPane.showMessageDialog(Frame.frame_join, "��й�ȣ�� 4~12���� ����, ���ڸ� ��� �����մϴ�.", "����",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				for (char c : pw.toCharArray()) {
					if (!Character.isAlphabetic(c) && !Character.isDigit(c)) { // ��й�ȣ�� ���� �Ǵ� ���ڰ� �ƴϸ�
						JOptionPane.showMessageDialog(Frame.frame_join, "��й�ȣ�� 4~12���� ����, ���ڸ� ��� �����մϴ�.", "����",
								JOptionPane.ERROR_MESSAGE);
						return;
					}
				}
				if (!pw.equals(pwCheck)) { // ��й�ȣ�� ��й�ȣȮ���̶� �ٸ���
					JOptionPane.showMessageDialog(Frame.frame_join, "��й�ȣ�� ��ġ���� �ʽ��ϴ�.", "����",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				changePW(pw);
				JOptionPane.showMessageDialog(Frame.frame_join, "��й�ȣ�� ���������� ����Ǿ����ϴ�.", "��й�ȣ ���� �Ϸ�",
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
				tf_pwCheck.setFont(new Font("����", Font.PLAIN, 18));
				tf_pwCheck.setColumns(10);
				tf_pwCheck.setOpaque(false);
				tf_pwCheck.setBounds(123, 149, 165, 24);
				contentPane.add(tf_pwCheck);
		
				tf_pw = new JPasswordField();
				tf_pw.setForeground(Color.BLACK);
				tf_pw.setFont(new Font("����", Font.PLAIN, 18));
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