import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.event.*;
import java.awt.*;
import java.util.*;
 
public class Set_schedule extends JDialog{
	Color mainWhite = new Color(0xF5F4F3);
	Color mainGreen = new Color(0x3B895E);
	Color subGreen = new Color(0x367257);
	Color subBrown = new Color(0xF1B975);
	
	Container contentpane = getContentPane();
	Calendar now = Calendar.getInstance();
	Calendar cal = Calendar.getInstance();
	
	String dday = new String();
	
	int year = 0;
	int month = 0;
	int day = 0;
	public int h = 0;			// �ð�
	public int m = 0;			// ��
	boolean time = false;		// Ʈ��� �ð� ���
	boolean selected = false;
	
	//////////////////////////
	
	JPanel pl_total = new JPanel();
	JPanel pl_date = new JPanel();
	JLabel lb_date = new JLabel();
	
	///////////////////////////
	
	JPanel pl_middle = new JPanel();
	JPanel pl_t = new JPanel();	// �ð� ���� ��� ���Ƴ��� �г�
	
	JPanel pl_cb = new JPanel();
	String hour[] = {"00","01","02","03","04","05","06","07","08","09","10","11"};
	String am_hour[] = new String[12];
	String pm_hour[] = new String[12];
	String min[] = new String[60];
	
	JComboBox cb_h;
	JComboBox cb_m;
	DefaultComboBoxModel model_cb;
	
	JLabel hours= new JLabel("��");
	JLabel minu= new JLabel("��");
	
	JPanel pl_rb = new JPanel();
	JRadioButton meridiem[] = new JRadioButton[3];	// 0: ����, 1 : ����, 2 : �ð� X
	
	JPanel pl_btn = new JPanel();	// ��� ��ư�� ���Ƴ��� �г�
	String str[] = {"�߰�","����","����","�ݱ�"};
	JButton btn[] = new JButton[4];	// 0 : �߰�, 1 : ����, 2 : ����, 3 : �ݱ�
	
	
	///////////////////////////
	
	JPanel pl_list = new JPanel();
	JTextField tf = new JTextField(); // ���� �Է�
	JList list;
	DefaultListModel model;
	
	public Set_schedule(int year, int month, int day){
		super(Calendar_3, dday, true);
		init(year, month, day);
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);	// Xǥ ������ ���� �������� �Ȳ���
		
		makeGUI();
		
		setSize(300, 500);
		setVisible(true);
		setResizable(false); // ũ�� ����
		this.setLocation(700, 500);
	}
	
	void init(int year, int month, int day) {
		this.year = year;
		this.month = month;
		this.day = day;
		
		cal.set(year, month-1, day);
		long difference = cal.getTimeInMillis() - Calendar.getInstance().getTimeInMillis(); 
	    long date = difference/(1000 * 60 * 60 * 24);
	    if(date == 0) dday = year + "/" + month + "/" + day;
	    else if(date > 0) dday = year + "/" + month + "/" + day + " (D-day : +" + date + ")";
	    else dday = year + "/" + month + "/" + day + " (D-day : " + date + ")";
		
		list = new JList(new DefaultListModel());
		model = (DefaultListModel) list.getModel();
		cb_h = new JComboBox(new DefaultComboBoxModel());
		model_cb = (DefaultComboBoxModel) cb_h.getModel();
		
		list.addKeyListener(new keyAdapter());
		tf.addKeyListener(new keyAdapter());
	}
	
	void makeGUI() {
		pl_total.setLayout(new BorderLayout());
		
		setN(); // ��¥ �г�
		setM(); // �߰� �г� (textfield, list)
		
		pl_total.add(pl_date, BorderLayout.NORTH);
		pl_total.add(pl_middle, BorderLayout.SOUTH);

		
		add(pl_total, BorderLayout.NORTH);
		
		setL();	// ����Ʈ
	}

	void setN() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month-1);
		cal.set(Calendar.DATE, day);
		pl_date.setLayout(new GridBagLayout());
		//pl_date.setBorderPainted(false);
		pl_date.setBackground(mainGreen);
		
		lb_date.setText(year + "�� " + month + "�� " + day + "�� " + doW(cal.get(Calendar.DAY_OF_WEEK)));
		lb_date.setFont(new Font("���ü", Font.BOLD, 20));
		lb_date.setPreferredSize(new Dimension(240, 42)); // ��� �г� ũ�� ���� ����
		lb_date.setForeground(Color.WHITE);
		
		pl_date.add(lb_date);
	}
	
	String doW(int num) {	// ���� �ѱ۷� ��ȯ
		String dow = "�Ͽ���";
		switch(num) {
		case 2: dow = "������";break;
		case 3: dow = "ȭ����";break;
		case 4: dow = "������";break;
		case 5: dow = "�����";break;
		case 6: dow = "�ݿ���";break;
		case 7: dow = "�����";break;
		}
		return dow;
	}
	
	void setM() {	
		///////  ��ư �г� (�Ʒ�)  ///////
		
		pl_btn.setLayout(new GridLayout(1,4));
		
		for(int i = 0 ; i < 4 ; i++) {
			btn[i] = new JButton(str[i]);
			pl_btn.add(btn[i]);
			btn[i].setFont(new Font("����", Font.BOLD, 15));
			btn[i].setBorderPainted(false);
			btn[i].setBackground(mainGreen);
			btn[i].setForeground(Color.WHITE);
		}
		btn[2].setBackground(subBrown);
		pl_middle.add(pl_btn, BorderLayout.SOUTH);
		pl_middle.setBackground(mainGreen);
	}
	
	void setL() {
		pl_list.setLayout(new BorderLayout());
		pl_list.add(tf, BorderLayout.NORTH);
		tf.setBackground(Color.WHITE);
		pl_list.add(new JScrollPane(list), BorderLayout.CENTER);
		list.setBackground(Color.WHITE);
		add(pl_list, BorderLayout.CENTER);
		pl_list.setBackground(mainGreen);
	}
	
	class keyAdapter extends KeyAdapter{
		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode() == KeyEvent.VK_ESCAPE) dispose();
		}
	}
}