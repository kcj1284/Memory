package window1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Popup extends JFrame {

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public Popup() {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage("rsc\\logo_icon.png"));
		setTitle("Popup");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setBounds(50, 30, 900, 482);
		JLabel lb_background = new JLabel(new ImageIcon("rsc\\Popup.png"));
		lb_background.setBackground(Color.WHITE);
		lb_background.setBounds(0, 0, 900, 482);
		contentPane.add(lb_background);
		setContentPane(contentPane);
	}

}
