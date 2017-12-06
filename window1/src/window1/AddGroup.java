package window1;

import java.awt.BorderLayout;
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
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;

/**
 * @author 김찬중 Home에서 그룹을 추가하는 Class
 */
public class AddGroup extends JFrame {

	private JPanel contentPane;
	private JTextField tf_groupadd;

	private String groupadd;

	/**
	 * Create the frame.
	 */
	public AddGroup() {

		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage("rsc\\logo_icon.png"));
		setTitle("AddGroup");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setBounds(100, 100, 500, 368);

		tf_groupadd = new JTextField();
		tf_groupadd.setBackground(Color.LIGHT_GRAY);
		tf_groupadd.setFont(new Font("맑은 고딕", Font.PLAIN, 25));
		tf_groupadd.setForeground(Color.WHITE);
		tf_groupadd.setColumns(10);
		tf_groupadd.setBounds(119, 173, 170, 35);
		contentPane.add(tf_groupadd);

		JButton btn_Add = new JButton("");
		btn_Add.setBorderPainted(false);
		btn_Add.setIcon(new ImageIcon("rsc\\icon\\btn_add.png"));
		btn_Add.setBounds(186, 276, 116, 45);
		btn_Add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddGroup();
				reset();
				Frame.frame_addgroup.setVisible(false);
			}
		});
		contentPane.setLayout(null);
		contentPane.add(btn_Add);

		JLabel lb_background = new JLabel(new ImageIcon("rsc\\Addgroup.png"));
		lb_background.setBackground(Color.WHITE);
		lb_background.setBounds(0, 0, 500, 373);
		contentPane.add(lb_background);
		setContentPane(contentPane);

	}

	public void AddGroup() {

		DB db = new DB();

		groupadd = tf_groupadd.getText();
		db.insertgroup(groupadd);
		JOptionPane.showMessageDialog(Frame.frame_addgroup, "그룹이 정상적으로 추가 되었습니다.", "그룹추가 완료",
				JOptionPane.INFORMATION_MESSAGE);
	}

	void reset() {
		tf_groupadd.setText("");
	}

}
