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
 * @author������ 
 * ȸ�������� �ϴ� Class
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
		 * @author ������
		 * id�� �Է��ϴ� �ؽ�Ʈ�ʵ�
		 */
		
		tf_id = new JTextField();
		tf_id.setOpaque(false);
		tf_id.setFont(new Font("���� ���", Font.PLAIN, 15));
		tf_id.setForeground(Color.WHITE);
		tf_id.setColumns(10);
		tf_id.setBounds(144, 128, 145, 24);
		contentPane.add(tf_id);

		/**
		 * @author ������
		 * �̸����� �Է��ϴ� �ؽ�Ʈ�ʵ�
		 */
		
		tf_email = new JTextField();
		tf_email.setOpaque(false);
		tf_email.setFont(new Font("���� ���", Font.PLAIN, 15));
		tf_email.setForeground(Color.WHITE);
		tf_email.setColumns(10);
		tf_email.setBounds(144, 255, 217, 24);
		contentPane.add(tf_email);

		/**
		 * @author ������
		 * �̸��� �Է��ϴ� �ؽ�Ʈ�ʵ�
		 */
		
		tf_name = new JTextField();
		tf_name.setOpaque(false);
		tf_name.setFont(new Font("���� ���", Font.PLAIN, 15));
		tf_name.setForeground(Color.WHITE);
		tf_name.setColumns(10);
		tf_name.setBounds(144, 295, 145, 24);
		contentPane.add(tf_name);

		/**
		 * @author ������
		 * ��й�ȣ ������ �亯�� �Է��ϴ� �ؽ�Ʈ�ʵ�
		 */
		
		tf_pwAnswer = new JTextField();
		tf_pwAnswer.setOpaque(false);
		tf_pwAnswer.setFont(new Font("���� ���", Font.PLAIN, 15));
		tf_pwAnswer.setForeground(Color.WHITE);
		tf_pwAnswer.setColumns(10);
		tf_pwAnswer.setBounds(144, 378, 145, 24);
		contentPane.add(tf_pwAnswer);

		/**
		 * @author ������
		 * ��й�ȣ�� �Է��ϴ� �н������ʵ�
		 */
		
		tf_pw = new JPasswordField();
		tf_pw.setOpaque(false);
		tf_pw.setFont(new Font("���� ���", Font.PLAIN, 15));
		tf_pw.setForeground(Color.WHITE);
		tf_pw.setBounds(144, 172, 145, 24);
		contentPane.add(tf_pw);

		/**
		 * @author ������
		 * ��й�ȣ�� Ȯ�����ִ� �н������ʵ�
		 */
		
		tf_pwCheck = new JPasswordField();
		tf_pwCheck.setOpaque(false);
		tf_pwCheck.setFont(new Font("���� ���", Font.PLAIN, 15));
		tf_pwCheck.setForeground(Color.WHITE);
		tf_pwCheck.setBounds(144, 214, 145, 24);
		contentPane.add(tf_pwCheck);

		/**
		 * @author ������
		 * �Էµ� �ڷ���� DB�� ������ ��ư
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
		 * @author ������
		 * ��й�ȣ�� ã������ �޺��ڽ�
		 */
		
		cb_pwQuestion = new JComboBox<String>();
		cb_pwQuestion.setFont(new Font("���� ���", Font.PLAIN, 15));
		cb_pwQuestion.setForeground(Color.WHITE);
		cb_pwQuestion.setBackground(new Color(54, 114, 87));
		cb_pwQuestion.addItem("���� �巡�����?");
		cb_pwQuestion.addItem("���� ���� 1ȣ��?");
		cb_pwQuestion.addItem("���� ��￡ ���� ������ ������?");
		cb_pwQuestion.addItem("���� ��￡ ���� ��Ҵ�?");
		cb_pwQuestion.addItem("���� ��Ӵ��� ������?");
		cb_pwQuestion.addItem("���� �ƹ����� ������?");
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
			 * @author ������
			 * �Էµ� id�� �������� Ȯ���ϰ� ���̸� üũ�ϰ� �ߺ��� üũ�� �� ���ǿ� ���� ������ �����޼����� ����
			 */
			
			if (id.length() < 4 || id.length() > 12) {
				JOptionPane.showMessageDialog(Frame.frame_join, "���̵�� 4~12���� ����, ���ڸ� ��� �����մϴ�.", "����",
						JOptionPane.ERROR_MESSAGE);
				return;
			}
			for (char c : id.toCharArray()) {
				if (!Character.isAlphabetic(c) && !Character.isDigit(c)) { 
					JOptionPane.showMessageDialog(Frame.frame_join, "���̵�� 4~12���� ����, ���ڸ� ��� �����մϴ�.", "����",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
			}
			for (int i = 0; i < Data.member_vector.size(); i++) {
				if (Data.member_vector.get(i).id.equals(id)) {
					JOptionPane.showMessageDialog(Frame.frame_join, "�̹� �����ϴ� ���̵��Դϴ�.", "����", JOptionPane.ERROR_MESSAGE);
					return;
				}
			}

			/**
			 * @author ������
			 * �Էµ� ��й�ȣ�� ���̿� ����� �������� Ȯ���ϰ� ���� ������ �����޼����� ����
			 */
			if (pw.length() < 4 || pw.length() > 12) { 
				JOptionPane.showMessageDialog(Frame.frame_join, "��й�ȣ�� 4~12���� ����, ���ڸ� ��� �����մϴ�.", "����",
						JOptionPane.ERROR_MESSAGE);
				return;
			}
			for (char c : pw.toCharArray()) {
				if (!Character.isAlphabetic(c) && !Character.isDigit(c)) { 
					JOptionPane.showMessageDialog(Frame.frame_join, "��й�ȣ�� 4~12���� ����, ���ڸ� ��� �����մϴ�.", "����",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
			}
			if (!pw.equals(pwCheck)) { 
				JOptionPane.showMessageDialog(Frame.frame_join, "��й�ȣ�� ��ġ���� �ʽ��ϴ�.", "����", JOptionPane.ERROR_MESSAGE);
				return;
			}

			/**
			 * @author ������
			 * �Էµ� �̸����� �˻��Ͽ� /@��ư�� ���ų� .�� ���ٸ� �����޼����� ����  
			 */
			
			int AtNum = 0, DotNum = 0;  

			for (char c : email.toCharArray()) {
				if (c == '@')
					AtNum++;
				if (c == '.')
					DotNum++;
			}
			if (AtNum != 1 || DotNum != 1) {
				JOptionPane.showMessageDialog(Frame.frame_join, "�ùٸ� ������ �̸����� �ƴմϴ�.", "����", JOptionPane.ERROR_MESSAGE);
				return;
			}

			/**
			 * @author ������
			 * �̸��� �Էµ��� ������ �����޼����� ����
			 */
			
			if (name.length() == 0) {
				JOptionPane.showMessageDialog(Frame.frame_join, "�̸��� �Է����ּ���.", "����", JOptionPane.ERROR_MESSAGE);
				return;
			}

			/**
			 * @author ������
			 * ��й�ȣ ���� �Էµ��� ������ �����޼����� ����  
			 */
			if (pwAnswer.length() == 0) {
				JOptionPane.showMessageDialog(Frame.frame_join, "��й�ȣ ã�� ���� �Է����ּ���.", "����", JOptionPane.ERROR_MESSAGE);
				return;
			}

			db.insertMember(id, pw, email, name, pwQuestion, pwAnswer);
			//db.addressBook(name);
			db.getMember();
			JOptionPane.showMessageDialog(Frame.frame_join, "ȸ�������� ���������� �Ϸ�Ǿ����ϴ�.", "ȸ������ �Ϸ�",
					JOptionPane.INFORMATION_MESSAGE);
			Frame.frame_join.setVisible(false);
		
	}

	/**
	 * @author ������
	 * â�� ���� �� �ڷ��� �Է������ ����ִ� �޼ҵ�
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