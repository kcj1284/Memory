package window1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
/**
 * @author 김찬중
 * Home에서 그룹을 추가하는 Class
 */
public class AddGroup extends JFrame {

	private JPanel contentPane;

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

		JButton btn_Add = new JButton("");
		btn_Add.setBorderPainted(false);
		btn_Add.setIcon(new ImageIcon("rsc\\icon\\btn_add.png"));
		btn_Add.setBounds(177, 282, 126, 51);
		btn_Add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Frame.frame_addgroup.setVisible(false);
			}
		});
		contentPane.setLayout(null);
		contentPane.add(btn_Add);

		JLabel lb_background = new JLabel(new ImageIcon("rsc\\Addgroup.png"));
		lb_background.setBackground(Color.WHITE);
		lb_background.setBounds(100, 100, 500, 368);
		contentPane.add(lb_background);
		setContentPane(contentPane);

	}

}
