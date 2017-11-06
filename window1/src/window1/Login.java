package window1;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		Login frame = new Login();
		frame.setVisible(true);
	}

	/**
	 * Create the frame.
	 */
	public Login() {
		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(300, 200, 1000, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JButton btnLogin = new JButton("login");
		btnLogin.setBounds(303, 307, 105, 27);
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main main = new Main();
				main.setVisible(true);
				setVisible(false);
			}
		});
		contentPane.setLayout(null);
		contentPane.add(btnLogin);
		
		textField = new JTextField();
		textField.setBounds(186, 308, 116, 24);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(186, 333, 116, 24);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnJoin = new JButton("join");
		btnJoin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Join join = new Join();
				join.setVisible(true);
			}
		});
		btnJoin.setBounds(303, 332, 105, 27);
		contentPane.add(btnJoin);
		
	}
}
