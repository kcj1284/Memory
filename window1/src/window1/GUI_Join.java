package window1;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

/**
 * @author ������
 * ȸ�����Կ� ���Ǵ� GUIâ
 */
@SuppressWarnings("serial")
public class GUI_Join extends JFrame {

	private JPanel contentPane;
	private JTextField tf_id;
	private JTextField tf_email;
	private JTextField tf_name;
	private JTextField tf_pwAnswer;
	private JPasswordField pf_pw;
	private JPasswordField pf_pwCheck;
	JComboBox<String> cb_pwQuestion;

	private String id, pw, pwCheck, email, name, pwAnswer;
	private int pwQuestion;

	public GUI_Join() {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage("rsc\\logo_icon.png"));
		setTitle("ȸ������");
		setBounds(100, 100, 403, 575);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);		

		tf_id = new JTextField();
		tf_id.setBackground(new Color(102, 211, 222));
		tf_id.setFont(new Font("���� ����", Font.PLAIN, 15));
		tf_id.setForeground(Color.WHITE);
		tf_id.setColumns(10);
		tf_id.setBounds(144, 128, 145, 24);
		contentPane.add(tf_id);

		tf_email = new JTextField();
		tf_email.setBackground(new Color(102, 211, 222));
		tf_email.setFont(new Font("���� ����", Font.PLAIN, 15));
		tf_email.setForeground(Color.WHITE);
		tf_email.setColumns(10);
		tf_email.setBounds(144, 255, 217, 24);
		contentPane.add(tf_email);

		tf_name = new JTextField();
		tf_name.setBackground(new Color(102, 211, 222));
		tf_name.setFont(new Font("���� ����", Font.PLAIN, 15));
		tf_name.setForeground(Color.WHITE);
		tf_name.setColumns(10);
		tf_name.setBounds(144, 295, 145, 24);
		contentPane.add(tf_name);

		tf_pwAnswer = new JTextField();
		tf_pwAnswer.setBackground(new Color(102, 211, 222));
		tf_pwAnswer.setFont(new Font("���� ����", Font.PLAIN, 15));
		tf_pwAnswer.setForeground(Color.WHITE);
		tf_pwAnswer.setColumns(10);
		tf_pwAnswer.setBounds(144, 378, 145, 24);
		contentPane.add(tf_pwAnswer);

		pf_pw = new JPasswordField();
		pf_pw.setBackground(new Color(102, 211, 222));
		pf_pw.setFont(new Font("���� ����", Font.PLAIN, 15));
		pf_pw.setForeground(Color.WHITE);
		pf_pw.setBounds(144, 172, 145, 24);
		contentPane.add(pf_pw);

		pf_pwCheck = new JPasswordField();
		pf_pwCheck.setBackground(new Color(102, 211, 222));
		pf_pwCheck.setFont(new Font("���� ����", Font.PLAIN, 15));
		pf_pwCheck.setForeground(Color.WHITE);
		pf_pwCheck.setBounds(144, 214, 145, 24);
		contentPane.add(pf_pwCheck);

		JButton btn_join = new JButton("");
		btn_join.setIcon(new ImageIcon("rsc\\icon\\btn_post.PNG"));
		btn_join.setBorderPainted(false);
		btn_join.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				join();
			}
		});
		btn_join.setFont(new Font("���� ����", Font.PLAIN, 15));
		btn_join.setBounds(125, 455, 167, 35);
		contentPane.add(btn_join);

		cb_pwQuestion = new JComboBox<String>();
		cb_pwQuestion.setFont(new Font("���� ����", Font.PLAIN, 15));
		cb_pwQuestion.setForeground(Color.WHITE);
		cb_pwQuestion.setBackground(new Color(102, 211, 222));
		cb_pwQuestion.addItem("���� �巡�����?");
		cb_pwQuestion.addItem("���� ���� 1ȣ��?");
		cb_pwQuestion.addItem("���� ��￡ ���� ������ ������?");
		cb_pwQuestion.addItem("���� ��￡ ���� ��Ҵ�?");
		cb_pwQuestion.addItem("���� ��Ӵ��� ������?");
		cb_pwQuestion.addItem("���� �ƹ����� ������?");
		cb_pwQuestion.setBounds(144, 336, 217, 24);
		contentPane.add(cb_pwQuestion);
		
		try{
			JLabel lb_background = new JLabel(new ImageIcon(ImageIO.read(new File("rsc\\join.png"))));
			lb_background.setBackground(Color.WHITE);
			lb_background.setBounds(0, 0, 400, 550);
			contentPane.add(lb_background);
		}
		catch(IOException s){
			s.printStackTrace();
		}
	}

	/**
	 * ���� ������ �Է¹ް� ȸ�������� �ϰ� ���ִ� �޼ҵ�
	 */
	public void join(){
		DB.getDBInfo();
		
		id = tf_id.getText();
		pw = String.valueOf(pf_pw.getPassword());
		pwCheck = String.valueOf(pf_pwCheck.getPassword());
		email = tf_email.getText();
		name = tf_name.getText();
		pwQuestion = cb_pwQuestion.getSelectedIndex();
		pwAnswer = tf_pwAnswer.getText();

		///////////// id ///////////////
		if(id.length() < 4 || id.length() > 12){ // ���̵��� ���̰� ª�ų� ���
			JOptionPane.showMessageDialog(Frame.frame_join, "���̵�� 4~12���� ����, ���ڸ� ��� �����մϴ�.", "����", JOptionPane.ERROR_MESSAGE);
			return;
		}
		for(char c : id.toCharArray()){ 
			if(!Character.isAlphabetic(c) && !Character.isDigit(c)){ // ���̵� ���� �Ǵ� ���ڰ� �ƴϸ�
				JOptionPane.showMessageDialog(Frame.frame_join, "���̵�� 4~12���� ����, ���ڸ� ��� �����մϴ�.", "����", JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
		for(int i = 0; i < DB.userInfo_vector.size(); i++){
			if(DB.userInfo_vector.get(i).id.equals(id)){ // ���̵� �ߺ��Ǹ�
				JOptionPane.showMessageDialog(Frame.frame_join, "�̹� �����ϴ� ���̵��Դϴ�.", "����", JOptionPane.ERROR_MESSAGE);
				return;
			}
		}

		///////////// pw ///////////////
		if(pw.length() < 4 || pw.length() > 12){ // ��й�ȣ�� ���̰� ª�ų� ���
			JOptionPane.showMessageDialog(Frame.frame_join, "��й�ȣ�� 4~12���� ����, ���ڸ� ��� �����մϴ�.", "����", JOptionPane.ERROR_MESSAGE);
			return;
		}
		for(char c : pw.toCharArray()){ 
			if(!Character.isAlphabetic(c) && !Character.isDigit(c)){ // ��й�ȣ�� ���� �Ǵ� ���ڰ� �ƴϸ�
				JOptionPane.showMessageDialog(Frame.frame_join, "��й�ȣ�� 4~12���� ����, ���ڸ� ��� �����մϴ�.", "����", JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
		if(!pw.equals(pwCheck)){ // ��й�ȣ�� ��й�ȣȮ���̶� �ٸ���
			JOptionPane.showMessageDialog(Frame.frame_join, "��й�ȣ�� ��ġ���� �ʽ��ϴ�.", "����", JOptionPane.ERROR_MESSAGE);
			return;
		}

		///////////// email ///////////////
		int AtNum = 0, DotNum = 0; // @�̶� .�� ����

		for(char c : email.toCharArray()){
			if(c == '@') AtNum++;
			if(c == '.') DotNum++;
		}
		if(AtNum != 1 || DotNum != 1 || email.length() <= 5){
			JOptionPane.showMessageDialog(Frame.frame_join, "�ùٸ� ������ �̸����� �ƴմϴ�.", "����", JOptionPane.ERROR_MESSAGE);
			return;
		}


		///////////// name ///////////////
		if(name.length() == 0){
			JOptionPane.showMessageDialog(Frame.frame_join, "�̸��� �Է����ּ���.", "����", JOptionPane.ERROR_MESSAGE);
			return;
		}


		///////////// pwAnswer ///////////////
		if(pwAnswer.length() == 0){
			JOptionPane.showMessageDialog(Frame.frame_join, "��й�ȣ ã�� ���� �Է����ּ���.", "����", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		///////////// DB�� ������ �Է� ///////////////
		DB.insertUserInfo(getTrimmedUserInfo());
		DB.getDBInfo();
		JOptionPane.showMessageDialog(Frame.frame_join, "ȸ�������� ���������� �Ϸ�Ǿ����ϴ�.", "ȸ������ �Ϸ�", JOptionPane.INFORMATION_MESSAGE);
		Frame.frame_join.setVisible(false);
	}
	
	/**
	 * ����ڰ� �Է��� ������ id|pw|email|name|pwQuestion|pwAnswer||||||| �������� ���� ��ȯ�Ѵ�.
	 * (ȸ�������� ���� �޼ҵ��̹Ƿ� introduce, followerNum ���� �������� �ʴ´�.)
	 * @return
	 */
	private String getTrimmedUserInfo(){
		StringBuilder sb = new StringBuilder("");
		sb.append(id + "|");
		sb.append(pw + "|");
		sb.append(email + "|");
		sb.append(name + "|");
		sb.append(pwQuestion + "|");
		sb.append(pwAnswer + "|||||||");
		return sb.toString();
	}
	
	/**
	 * ��� �Է¶��� ������ �����ִ� �޼ҵ�
	 */
	void reset(){
		tf_id.setText("");
		pf_pw.setText("");
		pf_pwCheck.setText("");
		tf_email.setText("");
		tf_name.setText("");
		cb_pwQuestion.setSelectedIndex(0);
		tf_pwAnswer.setText("");
	}
}