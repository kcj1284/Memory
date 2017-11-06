package window1;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Join extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;


	public Join() {
		setTitle("join");
		setBounds(400, 300, 700, 400);
		getContentPane().setLayout(null);
		
		JLabel lblJoin = new JLabel("Join");
		lblJoin.setFont(new Font("±¼¸²", Font.PLAIN, 30));
		lblJoin.setBounds(296, 12, 62, 40);
		getContentPane().add(lblJoin);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(122, 147, 62, 18);
		getContentPane().add(lblName);
		
		JLabel lblId = new JLabel("Id");
		lblId.setBounds(122, 174, 62, 18);
		getContentPane().add(lblId);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(122, 202, 82, 18);
		getContentPane().add(lblPassword);
		
		textField = new JTextField();
		textField.setBounds(208, 144, 220, 24);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(208, 171, 116, 24);
		getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(208, 199, 220, 24);
		getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		JButton btnCheck = new JButton("check");
		btnCheck.setBounds(323, 170, 105, 27);
		getContentPane().add(btnCheck);
		
		JButton btnNewButton = new JButton("Join");
		btnNewButton.setFont(new Font("±¼¸²", Font.PLAIN, 20));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnNewButton.setBounds(428, 143, 105, 80);
		getContentPane().add(btnNewButton);

		
	}
}
