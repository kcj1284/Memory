package window1;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;	

/**
 * @author ������
 * ���̵� �Ǵ� ��й�ȣ�� ã�� �� ���Ǵ� GUIâ
 */
@SuppressWarnings("serial")
public class FindIDPW extends JFrame {

	private JPanel contentPane;
	private JTextField tf_findid_email;
	private JTextField tf_findpw_email;
	private JTextField tf_findid_name;
	private JTextField tf_findpw_id;

	public FindIDPW() {
		setResizable(false);
		setTitle("���̵�/��й�ȣ ã��");
		setIconImage(Toolkit.getDefaultToolkit().getImage("rsc\\logo_icon.png"));
		setBounds(100, 100, 403, 530);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lb_background = new JLabel("");
		lb_background.setIcon(new ImageIcon("rsc\\IDPWFind.png"));
		lb_background.setBounds(0, 0, 400, 514);
		contentPane.add(lb_background);
		
		tf_findid_email = new JTextField();
		tf_findid_email.setBackground(new Color(102, 211, 222));
		tf_findid_email.setForeground(Color.WHITE);
		tf_findid_email.setFont(new Font("����", Font.PLAIN, 18));
		tf_findid_email.setBounds(135, 95, 215, 24);
		contentPane.add(tf_findid_email);
		tf_findid_email.setColumns(10);
		
		tf_findid_name = new JTextField();
		tf_findid_name.setBackground(new Color(102, 211, 222));
		tf_findid_name.setForeground(Color.WHITE);
		tf_findid_name.setFont(new Font("����", Font.PLAIN, 18));
		tf_findid_name.setBounds(135, 136, 146, 24);
		contentPane.add(tf_findid_name);
		tf_findid_name.setColumns(10);
		
		tf_findpw_id = new JTextField();
		tf_findpw_id.setBackground(new Color(102, 211, 222));
		tf_findpw_id.setForeground(Color.WHITE);
		tf_findpw_id.setFont(new Font("����", Font.PLAIN, 18));
		tf_findpw_id.setBounds(135, 330, 144, 24);
		contentPane.add(tf_findpw_id);
		tf_findpw_id.setColumns(10);
		
		tf_findpw_email = new JTextField();
		tf_findpw_email.setBackground(new Color(102, 211, 222));
		tf_findpw_email.setForeground(Color.WHITE);
		tf_findpw_email.setFont(new Font("����", Font.PLAIN, 18));
		tf_findpw_email.setColumns(15);
		tf_findpw_email.setBounds(135, 372, 215, 24);
		contentPane.add(tf_findpw_email);
		contentPane.add(lb_background);
		
		JButton btn_findID = new JButton("");
		btn_findID.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String email = tf_findid_email.getText();
				String name = tf_findid_name.getText();
				findID(email, name);
			}
		});
		btn_findID.setIcon(new ImageIcon("rsc\\icon\\btn_find.PNG"));
		btn_findID.setBorderPainted(false);
		btn_findID.setBounds(127, 196, 165, 35);
		contentPane.add(btn_findID);
		
		JButton btn_findPW = new JButton("");
		btn_findPW.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String id = tf_findpw_id.getText();
				String email = tf_findpw_email.getText();
				findPW(id, email);
			}
		});
		btn_findPW.setIcon(new ImageIcon("rsc\\icon\\btn_find.PNG"));
		btn_findPW.setBorderPainted(false);
		btn_findPW.setBounds(127, 468, 165, 35);
		contentPane.add(btn_findPW);
	}
	
	/**
	 * �̸��ϰ� �̸��� �Է¹޾Ƽ� �̿� ��ġ�ϴ� ���̵� �޽����� ����ִ� �޼ҵ�
	 */
	private void findID(String email, String name){
		int i;
		for(i = 0; i < DB.userInfo_vector.size(); i++){
			if(DB.userInfo_vector.get(i).email.equals(email) && DB.userInfo_vector.get(i).name.equals(name)){
				JOptionPane.showMessageDialog(Frame.frame_join, "���̵�� " + DB.userInfo_vector.get(i).id + "�Դϴ�.", "���̵� ã��", JOptionPane.INFORMATION_MESSAGE);
				break;
			}
		}
		if(i == DB.userInfo_vector.size()){
			JOptionPane.showMessageDialog(Frame.frame_join, "��ġ�ϴ� ������ �������� �ʽ��ϴ�.", "����", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	/**
	 * ���̵�, �̸���, �̸��� �޾Ƽ� �̿� ��ġ�ϴ� ����Ȯ�� ������ ����ִ� �޼ҵ�
	 */
	private void findPW(String id, String email){
		int i;
		for(i = 0; i < DB.userInfo_vector.size(); i++){
			if(DB.userInfo_vector.get(i).id.equals(id) && DB.userInfo_vector.get(i).email.equals(email)){
				String pwAnswer = "";
				switch(DB.userInfo_vector.get(i).pwQuestion){
				case 0:
					pwAnswer = "���� �巡�����?";
					break;
				case 1:
					pwAnswer = "���� ���� �� 1ȣ��?";
					break;
				case 2:
					pwAnswer = "���� ��￡ ���� ������ ������?";
					break;
				case 3:
					pwAnswer = "���� ��￡ ���� ��Ҵ�?";
					break;
				case 4:
					pwAnswer = "���� ��Ӵ��� ������?";
					break;
				case 5:
					pwAnswer = "���� �ƹ����� ������?";
					break;
				}
				DB.userIndex = i;
				Frame.frame_findPW.setVisible(true);
				Frame.frame_findPW.lb_pwQuestion.setText(pwAnswer);
				Frame.frame_findIDPW.setVisible(false);
				break;
			}
		}
		if(i == DB.userInfo_vector.size()){
			JOptionPane.showMessageDialog(Frame.frame_join, "��ġ�ϴ� ������ �������� �ʽ��ϴ�.", "����", JOptionPane.ERROR_MESSAGE);
		}
	}
}
