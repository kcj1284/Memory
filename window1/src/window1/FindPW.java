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
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * @author ������
 * ��й�ȣ�� ã�� �� ���Ǵ� Class
 */
@SuppressWarnings("serial")
public class FindPW extends JFrame {

	private JPanel contentPane;
	private JTextField tf_pwAnswer;
	private JButton btn_submit;
	JLabel lb_pwQuestion;

	public FindPW() {
		setResizable(false);
		setTitle("��й�ȣ ã��");
		setIconImage(Toolkit.getDefaultToolkit().getImage("rsc\\logo_icon.png"));
		setBounds(100, 100, 410, 315);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lb_pwQuestion = new JLabel("");
		lb_pwQuestion.setForeground(Color.WHITE);
		lb_pwQuestion.setFont(new Font("����", Font.BOLD, 20));
		lb_pwQuestion.setHorizontalAlignment(SwingConstants.CENTER);
		lb_pwQuestion.setBounds(14, 79, 364, 28);
		contentPane.add(lb_pwQuestion);

		JLabel lb_background = new JLabel("");
		lb_background.setIcon(new ImageIcon("rsc\\PWFind.png"));
		lb_background.setBounds(0, 0, 400, 276);
		contentPane.add(lb_background);
		contentPane.add(lb_background);

		tf_pwAnswer = new JTextField();
		tf_pwAnswer.setBackground(new Color(102, 211, 222));
		tf_pwAnswer.setForeground(Color.WHITE);
		tf_pwAnswer.setFont(new Font("����", Font.PLAIN, 18));
		tf_pwAnswer.setColumns(10);
		tf_pwAnswer.setBounds(146, 144, 146, 24);
		contentPane.add(tf_pwAnswer);

		btn_submit= new JButton("");
		btn_submit.setBorderPainted(false);
		btn_submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				findPW2(DB.userInfo_vector.get(DB.userIndex).id);
			}
		});
		btn_submit.setIcon(new ImageIcon("rsc\\icon\\btn_submit.PNG"));
		btn_submit.setBounds(124, 197, 166, 33);
		btn_submit.setBorderPainted(false);
		contentPane.add(btn_submit);
	}

	/**
	 * ����Ȯ�� ������ ���� ���� �´� ��� ��й�ȣ ����â�� ����ִ� �޼ҵ�
	 * @param id ��й�ȣ�� ������ id
	 */
	void findPW2(String id){
		String pwAnswer = tf_pwAnswer.getText();
		int i;
		for(i = 0; i < DB.userInfo_vector.size(); i++){
			if(DB.userInfo_vector.get(i).id.equals(id) && DB.isPWCorrect(id, pwAnswer, true)){
				Frame.frame_changePW.setVisible(true);
				Frame.frame_findPW.setVisible(false);
				break;
			}
		}
		if(i == DB.userInfo_vector.size()){
			JOptionPane.showMessageDialog(Frame.frame_join, "��й�ȣ Ȯ�� ���� ��ġ���� �ʽ��ϴ�.", "����", JOptionPane.ERROR_MESSAGE);
		}
	}
}
