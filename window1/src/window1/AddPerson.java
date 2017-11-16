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
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
/**
 * @author ±èÂùÁß
 * Home¿¡¼­ ÀÎ¹°À» Ãß°¡ÇÏ´Â Class
 */
public class AddPerson extends JFrame {

	private JPanel contentPane;
	private JTextField tf_name;
	private JTextField tf_num;
	private JTextField tf_mail;
	private JTextField tf_major;
	private JTextField tf_stid;
	private JTextField tf_date;
	private JTextField tf_group;
	private JTextField tf_sns;
	private JTextField tf_hash;
	
	/**
	 * Create the frame.
	 */
	public AddPerson() {

		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage("rsc\\logo_icon.png"));
		setTitle("AddPerson");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setBounds(310, 115, 398, 775);

		tf_name = new JTextField();
		tf_name.setBackground(Color.LIGHT_GRAY);
		tf_name.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 25));
		tf_name.setForeground(Color.WHITE);
		tf_name.setColumns(10);
		tf_name.setBounds(185, 70, 170, 40);
		contentPane.add(tf_name);
		
		tf_num = new JTextField();
		tf_num.setBackground(Color.LIGHT_GRAY);
		tf_num.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 25));
		tf_num.setForeground(Color.WHITE);
		tf_num.setColumns(15);
		tf_num.setBounds(170, 180, 200, 40);
		contentPane.add(tf_num);
		
		tf_mail = new JTextField();
		tf_mail.setBackground(Color.LIGHT_GRAY);
		tf_mail.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 25));
		tf_mail.setForeground(Color.WHITE);
		tf_mail.setColumns(10);
		tf_mail.setBounds(170, 240, 200, 40);
		contentPane.add(tf_mail);
		
		tf_major = new JTextField();
		tf_major.setBackground(Color.LIGHT_GRAY);
		tf_major.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 25));
		tf_major.setForeground(Color.WHITE);
		tf_major.setColumns(10);
		tf_major.setBounds(170, 300, 200, 40);
		contentPane.add(tf_major);
		
		tf_stid = new JTextField();
		tf_stid.setBackground(Color.LIGHT_GRAY);
		tf_stid.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 25));
		tf_stid.setForeground(Color.WHITE);
		tf_stid.setColumns(10);
		tf_stid.setBounds(170, 360, 200, 40);
		contentPane.add(tf_stid);

		tf_date = new JTextField();
		tf_date.setBackground(Color.LIGHT_GRAY);
		tf_date.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 25));
		tf_date.setForeground(Color.WHITE);
		tf_date.setColumns(10);
		tf_date.setBounds(170, 420, 200, 40);
		contentPane.add(tf_date);
		
		tf_group = new JTextField();
		tf_group.setBackground(Color.LIGHT_GRAY);
		tf_group.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 25));
		tf_group.setForeground(Color.WHITE);
		tf_group.setColumns(10);
		tf_group.setBounds(170, 480, 200, 40);
		contentPane.add(tf_group);

		tf_sns = new JTextField();
		tf_sns.setBackground(Color.LIGHT_GRAY);
		tf_sns.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 25));
		tf_sns.setForeground(Color.WHITE);
		tf_sns.setColumns(10);
		tf_sns.setBounds(170, 540, 200, 40);
		contentPane.add(tf_sns);

		tf_hash = new JTextField();
		tf_hash.setBackground(Color.LIGHT_GRAY);
		tf_hash.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 25));
		tf_hash.setForeground(Color.WHITE);
		tf_hash.setColumns(10);
		tf_hash.setBounds(170, 600, 200, 40);
		contentPane.add(tf_hash);
		
		JButton btn_save = new JButton("");
		btn_save.setBorderPainted(false);
		btn_save.setIcon(new ImageIcon("rsc\\icon\\btn_save.png"));
		btn_save.setBounds(162, 694, 110, 46);
		btn_save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Frame.frame_addperson.setVisible(false);
			}
		});
		contentPane.setLayout(null);
		contentPane.add(btn_save);
		
		JButton btn_close = new JButton("");
		btn_close.setBorderPainted(false);
		btn_close.setIcon(new ImageIcon("rsc\\icon\\btn_close.png"));
		btn_close.setBounds(275, 694, 110, 46);
		btn_close.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Frame.frame_addperson.setVisible(false);
			}
		});
		contentPane.setLayout(null);
		contentPane.add(btn_close);

		JLabel lb_background = new JLabel(new ImageIcon("rsc\\Addperson.png"));
		lb_background.setBackground(Color.WHITE);
		lb_background.setBounds(5, 5, 382, 730);
		contentPane.add(lb_background);
		setContentPane(contentPane);

	}

}
