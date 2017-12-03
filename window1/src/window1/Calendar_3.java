import javax.swing.*;
 import java.awt.event.*;
 import java.net.*;
 import java.awt.*;
 import java.util.*;
 
 public class Calendar_3 extends JPanel {
 	Color mainWhite = new Color(0xF5F4F3);
 	Color mainGreen = new Color(0x3B895E);
 	Color subGreen = new Color(0x367257);
 
 	JButton all_sche = new JButton("�������");
 	JButton btn_menu = new JButton("��������");
 	Set_schedule sF;
 	///////////////
 	All_sch fr_2;
 
 	// ����ð� //
 	Calendar cal = Calendar.getInstance();
 	int year = cal.get(Calendar.YEAR);
 	int month = cal.get(Calendar.MONTH) + 1;
 	int day = cal.get(Calendar.DATE);
 
 	//// ���÷� ����Ǵ� �ð����� ���� ///
 	Calendar tmp = Calendar.getInstance();
 	int tmp_year = cal.get(Calendar.YEAR);
 	int tmp_month = cal.get(Calendar.MONTH);
 	int tmp_date = cal.get(Calendar.DATE);
 	int mon_fir = tmp.get(Calendar.DAY_OF_WEEK) - 1; // ���泯¥�� ���� ����
 	int mon_last = cal.getActualMaximum(Calendar.DATE); // ����� ���� ���ϼ�
 
 	/// ��� �г� ///
 	JPanel pl_0_status = new JPanel();
 	JButton btn_year = new JButton(year + "��");
 	JButton btn_month = new JButton(month + "��");
 
 	JButton next_y = new JButton(">>");
 	JButton next_m = new JButton(">");
 	JButton previous_y = new JButton("<<");
 	JButton previous_m = new JButton("<");
 	JButton btn_td = new JButton("today : " + year + "  " + month + "  " + day);
 	//////// �߾� �г� /////////
 	JPanel pl_day = new JPanel();
 	JLabel lb_day[] = new JLabel[7];
 	String days[] = { "��", "��", "ȭ", "��", "��", "��", "��" };
 	JButton btn_day[] = new JButton[42];
 
 	JList jlist; // ���� ������ �ϴܺ� ����Ʈ
 	DefaultListModel model; // ����Ʈ�� ��
 
 	JLabel lb_td = new JLabel(year + "�� " + month + "�� " + day + "�� " + doW(tmp.get(Calendar.DAY_OF_WEEK)));
 
 	//////////////////// ���Ḯ��Ʈ ���� //////////////////////
 
 	//ApiExplorer api;
 	boolean isapi[] = new boolean[9]; // api ����� �ð��� �� �ɷ��� boolean���� Ȯ��
 	int holi_years[] = new int[9]; // �ش� �⵵�� ������ �� tmp_year2014
 
 	LinkedList list;
 	String node_name = year + "/" + month + "/" + day; // ��� �����α� ���� ������ �̸�
 														// (�⵵����)
 
 	private CalendarData first; // ����� ���
 
 	Calendar_3() {
 		tmp.set(Calendar.DATE, 1);
 		mon_fir = tmp.get(Calendar.DAY_OF_WEEK) - 1;
 
 		for (int i = 0; i < 9; i++) {
 			isapi[i] = false;
 			holi_years[i] = 0;
 		}
 
 		list = new LinkedList(); // ���� ���� ����Ʈ ����
 		jlist = new JList(new DefaultListModel()); // ����Ʈ �ʱ�ȭ
 		model = (DefaultListModel) jlist.getModel(); // ����Ʈ�� �� �ʱ�ȭ
 
 		setapi(year);
 
 		list.insert(node_name); // ���Ḯ��Ʈ�� ���糯¥�� ��� ����
 
 		makeGUI();
 
 		setSize(500, 500);
 		setLocation(200, 100);
 		setVisible(true);
 	}
 
 	void setapi(int tmp_year) {
 		if (tmp_year >= 2014 && tmp_year <= 2022)
 			if (!isapi[tmp_year - 2014]) {
 			}
 		if (tmp_year >= 2014 && tmp_year <= 2022)
 			isapi[tmp_year - 2014] = true;
 	}
 
 	void init() { // �ʿ��� ������ �ʱ�ȭ
 		year = cal.get(Calendar.YEAR);
 		month = cal.get(Calendar.MONTH) + 1;
 		day = cal.get(Calendar.DATE);
 		//// ����� �ӽ� �ð� ///
 		tmp = Calendar.getInstance();
 		tmp_year = cal.get(Calendar.YEAR);
 		tmp_month = cal.get(Calendar.MONTH) - 1;
 		tmp_date = cal.get(Calendar.DATE);
 
 		mon_fir = tmp.get(Calendar.DAY_OF_WEEK) - 1; // ���泯¥�� ���� ����
 		mon_last = cal.getActualMaximum(Calendar.DATE); // ����� ���� ���ϼ�
 	}
 
 	void setCal(int year, int month, int day) { // ��ü cal ����
 		tmp.set(Calendar.YEAR, year);
 		tmp.set(Calendar.MONTH, month);
 		tmp.set(Calendar.DATE, day);
 	}
 
 	void makeGUI() {
 		this.setLayout(new BorderLayout(0, 0));
 		days(); // ��¥ ����â (���� ������ �߾Ӻ�) �ʱ�ȭ
 		status(); // �⵵ �� ����â(���� ������ ��ܺ�) �ʱ�ȭ
 		this.setBackground(mainWhite);
 	}
 
 	String doW(int num) { // �ǳ׹��� ������ ���ڰ��� �ѱ۷� ��ȯ
 		String dow = "�Ͽ���";
 		switch (num) {
 		case 2:
 			dow = "������";
 			break;
 		case 3:
 			dow = "ȭ����";
 			break;
 		case 4:
 			dow = "������";
 			break;
 		case 5:
 			dow = "�����";
 			break;
 		case 6:
 			dow = "�ݿ���";
 			break;
 		case 7:
 			dow = "�����";
 			break;
 		}
 		return dow;
 	}
 
 	void days() { // ���� ������ �߾Ӻ�
 		pl_day.setLayout(new GridLayout(7, 7)); // ���Ͽ� �ش��ϴ� ���̺��� �����Ͽ� 7x7 �׸���
 		pl_day.setBackground(mainWhite);
 		for (int i = 0; i < 7; i++) { // ��~����ϱ��� �����ؼ� ���ٿ� ����
 			lb_day[i] = new JLabel(days[i]);
 			lb_day[i].setHorizontalAlignment(SwingConstants.CENTER);
 			lb_day[i].setForeground(Color.BLACK);
 			lb_day[i].setFont(new Font("����", Font.BOLD, 22));
 			pl_day.add(lb_day[i]);
 		}
 		lb_day[0].setForeground(Color.RED); // �Ͽ����� ������
 		for (int i = 0; i < 42; i++) { // ���� ��¥�� �°� ��ư ��ġ ����
 			if (i >= mon_fir && i < mon_last + mon_fir) { // mon_fir > ���� �� ����
 															// �ش��ϴ� 1���� ����,
 															// last�� �� �ϼ�
 				btn_day[i] = new JButton(new Integer(i - mon_fir + 1).toString()); // 1�Ϻ���
 																					// ����
 				pl_day.add(btn_day[i]);
 			} else {
 				btn_day[i] = new JButton(""); // �ش����� �ʴ� ��ư���� text�� ����� ��Ȱ��ȭ
 				btn_day[i].setEnabled(false);
 				pl_day.add(btn_day[i]);
 			}
 			btn_day[i].setBorderPainted(false); // �ܰ��� �����
 			if ((i - mon_fir + 1) == cal.get(Calendar.DATE)) { // ���� ��¥�� �ش��ϴ�
 																// ��ư��
 				btn_day[i].setBackground(Color.LIGHT_GRAY); // £�� ȸ��
 				btn_day[i].setFont(new Font("����", Font.ITALIC, 18));
 			} else {
 				btn_day[i].setBackground(mainWhite); // �������� ���� ȸ��
 				btn_day[i].setFont(new Font("����", Font.ITALIC, 18));
 			}
 
 			if (i % 7 == 0) {
 				btn_day[i].setForeground(Color.RED); // �Ͽ��Ͽ� �ش��ϴ� ��ư���� ������
 			} else
 				btn_day[i].setForeground(Color.BLACK); // ������ �Ͼ��
 
 			btn_day[i].addActionListener(new btn_day_Listener());
 		}
 		add(pl_day, BorderLayout.CENTER);
 	}
 
 	void status() { // ���� ������ ��ܺ�.(����â)
 		add(pl_0_status, BorderLayout.NORTH);
 		pl_0_status.setLayout(null);
 		pl_0_status.setPreferredSize(new Dimension(400, 85)); // ��� �г� ũ�� ���� ����
 
 		pl_0_status.add(all_sche);
 		all_sche.setFont(new Font("����", Font.BOLD, 15));
 		all_sche.setBounds(0, 5, 130, 30);
 		all_sche.setBorderPainted(false);
 		all_sche.setBackground(mainGreen);
 		all_sche.setForeground(mainWhite);
 		all_sche.addActionListener(new ActionListener() {
 			public void actionPerformed(ActionEvent e) {
 				fr_2 = new All_sch(list, holi_years[year - 2014]); //// ���⼭ ������
 																	//// ���� �ϴ�
 																	//// ����////
 				
 				
 				fr_2.jlist.addMouseListener(new MouseAdapter() {
 					public void mouseClicked(MouseEvent e) {
 						JList tmp = (JList) e.getSource();
 						
 						if (e.getClickCount() == 2 && !fr_2.model.isEmpty()) {
 								fr_2.devideStr(fr_2.names[tmp.getSelectedIndex()]);
 							
 							node_name = fr_2.year + "/" + fr_2.month + "/" + fr_2.date;
 							shift_day(fr_2.year, fr_2.month, fr_2.date);
 							for (int i = 0; i < 42; i++) {
 								if (btn_day[i].getText().equals(new Integer(fr_2.date).toString()))
 									btn_day[i].setBackground(Color.LIGHT_GRAY);
 								else
 									btn_day[i].setBackground(mainWhite); // ������
 							}
 						}
 					}
 				});
 			}
 		});
 
 		pl_0_status.add(btn_menu); // �������� ��ư ����
 		btn_menu.setFont(new Font("����", Font.BOLD, 15));
 		btn_menu.setBounds(365, 5, 130, 30);
 		btn_menu.setBorderPainted(false);
 		btn_menu.setBackground(mainGreen);
 		btn_menu.setForeground(mainWhite);
 		btn_menu.addActionListener(new btn_day_Listener()); // �������� ��ư�� ������ ����
 
 		pl_0_status.add(btn_year); // �⵵
 		btn_year.setFont(new Font("���", Font.PLAIN, 35));
 		btn_year.setBounds(127, 35, 150, 50);
 		btn_year.setBorderPainted(false);
 		btn_year.setBackground(mainGreen);
 		btn_year.setForeground(mainWhite);
 
 		pl_0_status.add(btn_month); // ��
 		btn_month.setFont(new Font("���", Font.PLAIN, 30));
 		btn_month.setBounds(260, 35, 100, 50);
 		btn_month.setBorderPainted(false);
 		btn_month.setBackground(mainGreen);
 		btn_month.setForeground(mainWhite);
 
 		pl_0_status.add(next_m); // ���� �� ��ư
 		next_m.setBounds(370, 45, 50, 30);
 		next_m.addActionListener(new myListener());
 		next_m.setBorderPainted(false);
 		next_m.setBackground(mainGreen);
 		next_m.setForeground(mainWhite);
 
 		pl_0_status.add(next_y); // ���� �� ��ư
 		next_y.setBounds(420, 45, 50, 30);
 		next_y.addActionListener(new myListener());
 		next_y.setBorderPainted(false);
 		next_y.setBackground(mainGreen);
 		next_y.setForeground(mainWhite);
 
 		pl_0_status.add(previous_m); // ���� �� ��ư
 		previous_m.setBounds(75, 45, 50, 30);
 		previous_m.addActionListener(new myListener());
 		previous_m.setBorderPainted(false);
 		previous_m.setBackground(mainGreen);
 		previous_m.setForeground(mainWhite);
 
 		pl_0_status.add(previous_y); // ���� �⵵ ��ư
 		previous_y.setBounds(25, 45, 50, 30);
 		previous_y.addActionListener(new myListener());
 		previous_y.setBorderPainted(false);
 		previous_y.setBackground(mainGreen);
 		previous_y.setForeground(mainWhite);
 
 		pl_0_status.add(btn_td); // today ǥ�� ��ư
 		btn_td.setBounds(140, 5, 210, 30);
 		btn_td.setFont(new Font("���", Font.BOLD, 18));
 		btn_td.setBackground(mainGreen);
 		btn_td.setForeground(mainWhite);
 		btn_td.setBorderPainted(false);
 		btn_td.addActionListener(new myListener());
 
 		pl_0_status.setBackground(mainGreen);
 		pl_0_status.setPreferredSize(new Dimension(500, 85)); // ũ�����
 	}
 
 	class myListener implements ActionListener { // ��� �гο� ��ġ�� ��ư���� ������
 		public void actionPerformed(ActionEvent e) {
 			JButton tmp_bt = (JButton) e.getSource();
 			if (tmp_bt.equals(next_m)) { // ���� �� ������ ��
 				if (tmp_month == 11) {
 					tmp_month = 0;
 					tmp_year++;
 					setapi(tmp_year);
 				} else
 					tmp_month += 1;
 			} else if (tmp_bt.equals(next_y)) { // ���� �⵵ ������ ��
 				tmp_year += 1;
 				setapi(tmp_year);
 			}
 			if (tmp_bt.equals(previous_m)) { // ���� �� ������ ��
 				if (tmp_month == 0) {
 					tmp_month = 11;
 					tmp_year--;
 					setapi(tmp_year);
 				} else
 					tmp_month = 1;
 			} else if (tmp_bt.equals(previous_y)) {
 				tmp_year = 1;
 				setapi(tmp_year);
 			} else if (tmp_bt.equals(btn_td)) { // today ��ư ������ ��
 				tmp_year = year; // ������ ���� �⵵�� �޷� ����
 				tmp_month = month - 1;
 				for (int i = 0; i < 42; i++)
 					if (btn_day[i].getText().equals(day + 1 + ""))
 						btn_day[i].setBackground(Color.LIGHT_GRAY); 
 				
 				
 				// ���� ��¥��
 				// �ش��ϴ� ��ư
 				// £�� ȸ������
 				lb_td.setText(year + "�� " + month + "�� " + day + "�� " + doW(cal.get(Calendar.DAY_OF_WEEK)));
 				node_name = tmp_year + "/" + (tmp_month + 1) + "/" + day;
 				showList(0);
 				// �ϴܺ� �󺧵� ���� ��¥�� �°� ����
 			}
 			tmp.set(Calendar.MONTH, tmp_month); // �⵵, ��¥ �ʱ�ȭ
 			tmp.set(Calendar.YEAR, tmp_year);
 			btn_year.setText(tmp_year + "��"); // �г� ��ܺ��� �� �� ����
 			btn_month.setText((tmp_month + 1) + "��");
 			setting();
 		}
 	}
 
 	class btn_day_Listener implements ActionListener { // ��¥ ��ư�� ���� ��ư ������
 														// //////�ٲٴ� ��//////
 		public void actionPerformed(ActionEvent e) {
 			JButton tmp_bt = (JButton) e.getSource();
 			if (!tmp_bt.equals(btn_menu)) { // �߾� ��¥�κ� ��ư�� Ŭ������ �� true
 				node_name = tmp_year + "/" + (tmp_month + 1) + "/" + tmp_bt.getText(); // ���õ�
 																						// ��ư��
 																						// �ش��ϴ�
 																						// �����
 																						// "�̸���"!!
 																						// �����
 				int tmp_day = new Integer(tmp_bt.getText()).intValue(); // Ŭ����
 																		// ��¥
 				for (int i = 0; i < 42; i++)
 					btn_day[i].setBackground(mainWhite); // ��� ��ư ���� ȸ��
 				setCal(tmp_year, tmp_month, tmp_day); // �޷� ��ü cal �ʱ�ȭ
 				lb_td.setText(
 						tmp_year + "�� " + (tmp_month + 1) + "�� " + tmp_day + "�� " + doW(tmp.get(Calendar.DAY_OF_WEEK)));
 				// ���� �г� �ϴܺ� ��¥ �ʱ�ȭ
 				tmp_bt.setBackground(Color.LIGHT_GRAY); // ���õ� ��ư £�� ȸ������
 				tmp_date = tmp_day;
 
 				model.clear(); // �������� â�� ����Ʈ �ʱ�ȭ
 				if (list.search(node_name) == null) { // Ŭ���� ��ư�� ��尡 ���ٸ� ����
 					list.insert(node_name);
 				} else
 					showList(0); // ������ �ִٸ�
 			} else { // ���� ���� â�� ���� ������!!
 				sF = new Set_schedule(tmp_year, tmp_month + 1, tmp_date); // ��������
 																			// â
 																			// ����
 				sF.setLocation(900, 200);
 				sF.tf.requestFocus(); // ���� ������ ��Ÿ������ �ؽ�Ʈ �ʵ尡 ��Ŀ���� ����
 				showList(1); // �ش� ����� ��� ���� ���
 
 				sF.tf.addActionListener(new ActionListener() { // textfield ������
 					public void actionPerformed(ActionEvent e) {
 						if (!sF.tf.getText().isEmpty()) { // �ؽ�Ʈ �ʵ忡 ���� �ִٸ�
 							String time = "";
 							if (sF.time) {
 								String hh = sF.h + "";
 								String mm = sF.m + "";
 								if (sF.h < 10)
 									hh = "0" + sF.h;
 								if (sF.m < 10)
 									mm = "0" + sF.m;
 								time = "( " + hh + " : " + mm + " ) ";
 							}
 							list.search(node_name).setCal(time + sF.tf.getText()); // ����
 																					// ��¥
 																					// ��忡
 																					// �ؽ�Ʈ
 																					// �ʵ忡
 																					// �ִ�
 																					// ������
 																					// �߰�
 							sF.tf.setText("");
 							sF.tf.requestFocus();
 							sF.model.clear(); // ���� ������ ����Ʈ �ʱ�ȭ
 							showList(1); // �ش� ����� ��� ���� ���
 							setting();
 							for (int i = 0; i < 42; i++) {
 								if (new Integer(tmp_date).toString().equals(btn_day[i].getText()))
 									btn_day[i].setBackground(Color.LIGHT_GRAY);
 								else
 									btn_day[i].setBackground(mainWhite);
 							}
 						}
 					}
 				});
 
 				for (int i = 0; i < 4; i++) {
 					sF.btn[i].addActionListener(new ActionListener() {
 						public void actionPerformed(ActionEvent e) { // ��������
 																		// ��ư�鿡
 																		// ����
 																		// �̺�Ʈ
 							JButton bt_tmp = (JButton) e.getSource();
 							if (bt_tmp.equals(sF.btn[0])) { // �߰� ��ư �̺�Ʈ
 								if (!sF.tf.getText().isEmpty()) { // �ؽ�Ʈ �ʵ忡 ����
 																	// �ִٸ�
 									String time = "";
 									if (sF.time) // ���� �߰��� �� �ð� ��� �Ѵٸ� true
 										time = "(" + sF.h + " : " + sF.m + ") ";
 									list.search(node_name).setCal(time + sF.tf.getText()); // ����
 																							// ��¥
 																							// ��忡
 																							// �ؽ�Ʈ
 																							// �ʵ忡
 																							// �ִ�
 																							// ������
 																							// �߰�
 									sF.tf.setText(""); // �߰� �Ŀ� textfield�� Ŭ����
 									sF.tf.requestFocus(); // Ŭ���� �� �ٷ� ���� ���� �ְ�
 															// textfield�� ��Ŀ���� ��
 
 									sF.model.clear(); // ���� ������ ����Ʈ �ʱ�ȭ
 									showList(1); // �ش� ����� ��� ���� ���
 								}
 							} else if (!(bt_tmp.equals(sF.btn[3]))) { // ����, ����
 																		// ��ư��
 																		// ����
 																		// �̺�Ʈ
 								int idx = sF.list.getSelectedIndex();
 								if (idx != 1) {
 									if (bt_tmp.equals(sF.btn[1])) // ������ư�̶��
 										sF.tf.setText(list.search(node_name).cal[idx]); // ���õ�
 																						// ����Ʈ
 																						// ��������
 																						// textfield��
 																						// �÷��ΰ�
 									list.search(node_name).delCal(idx); // ����
 									sF.model.clear(); // ���� ������ ����Ʈ �ʱ�ȭ
 									showList(1); // �ش� ����� ��� ���� ���
 									if (idx != 0)
 										sF.list.setSelectedIndex(idx - 1); // ���õ�
 																			// ����Ʈ
 																			// ��������
 																			// 0��
 																			// �ƴ϶��
 																			// ����
 																			// ��
 																			// ����
 																			// ������
 																			// ����
 									else
 										sF.tf.requestFocus();
 								}
 							} else { // ��� ��ư�� ���� �̺�Ʈ ����
 								showList(0);
 								sF.dispose(); // �ι�° ������ ����
 							}
 							setting();
 							for (int i = 0; i < 42; i++) {
 								if (new Integer(tmp_date).toString().equals(btn_day[i].getText()))
 									btn_day[i].setBackground(Color.LIGHT_GRAY);
 								else
 									btn_day[i].setBackground(mainWhite);
 							}
 						}
 					});
 				}
 			}
 		}
 	}
 
 	public void shift_day(int year, int month, int date) {
 		tmp_year = year; // �ӽ� �ð��� �Է¹��� �ð����� ����
 		tmp_month = month - 1;
 		tmp_date = date;
 		for (int i = 0; i < 42; i++)
 			if (btn_day[i].getText().equals(day + 1 + "")) {
 				btn_day[i].setBackground(Color.LIGHT_GRAY);
 				btn_day[i].setFont(new Font("����", Font.ITALIC, 19));
 			} // ���� ��¥�� �ش��ϴ� ��ư £�� ȸ������
 		tmp.set(Calendar.MONTH, tmp_month); // �⵵, ��¥ �ʱ�ȭ
 		tmp.set(Calendar.YEAR, tmp_year);
 		tmp.set(Calendar.DATE, tmp_date);
 		lb_td.setText(year + "�� " + month + "�� " + tmp_date + "�� " + doW(tmp.get(Calendar.DAY_OF_WEEK)));
 		showList(0);
 		// �ϴܺ� �󺧵� ���� ��¥�� �°� ����
 		btn_year.setText(tmp_year + "��"); // �г� ��ܺ��� �� �� ����
 		btn_month.setText((tmp_month + 1) + "��");
 		setting();
 	}
 
 	public void setting() { // �ٲ� �� ���� �°� ��¥ ��ư�� ��ġ �ʱ�ȭ(ù �ʱ�ȭ���� ��� ���) ////������
 		tmp.set(Calendar.DAY_OF_MONTH, 1);
 		mon_fir = tmp.get(Calendar.DAY_OF_WEEK) - 1;
 		mon_last = tmp.getActualMaximum(Calendar.DATE);
 		for (int i = 0; i < 42; i++) {
 			pl_day.remove(btn_day[i]);
 
 			if ((i - mon_fir + 2) == day + 1 && (year == tmp_year) && (month - 1 == tmp_month))
 				btn_day[i].setBackground(Color.LIGHT_GRAY);
 			else
 				btn_day[i].setBackground(mainWhite); // ������
 
 			if (i >= mon_fir && i < mon_last + mon_fir) { // �ش� 1�Ϻ��� ���ϱ��� ��ġ�� �°�
 															// ����
 				btn_day[i].setText(new Integer(i - mon_fir + 1).toString());
 				btn_day[i].setEnabled(true);
 				pl_day.add(btn_day[i]);
 			} else {
 				btn_day[i].setText("");
 				btn_day[i].setEnabled(false);
 				pl_day.add(btn_day[i]);
 			}
 			if ((i % 7 == 0 ))
 				btn_day[i].setForeground(Color.RED); // ���� ����
 			else if (isCal(tmp.get(Calendar.YEAR) + "/" + (tmp.get(Calendar.MONTH) + 1) + "/" + btn_day[i].getText()))
 				btn_day[i].setForeground(Color.BLUE);
 			else
 				btn_day[i].setForeground(Color.BLACK);
 
 			btn_day[i].setBorderPainted(false);
 			btn_day[i].setFont(new Font("����", Font.ITALIC, 18));
 			btn_day[i].addActionListener(new btn_day_Listener());
 
 		}
 	}
 
 	boolean isCal(String name) { // ������ �ֳ� ���� üũ
 		if (list.search(name) != null)
 			if (list.search(name).getcnt() > 0)
 				return true;
 		return false;
 	}
 
 	public void showList(int n) {
 		if (n != 1)
 			model.clear();
 		else
 			sF.model.clear();
 		CalendarData tmp = list.search(node_name);
 
 		if (tmp != null) {
 			for (int i = 0; i < tmp.count; i++) { // �ش� ����� ��� ���� ���
 				if (n == 0)
 					model.addElement((i + 1) + ". " + list.search(node_name).cal[i]);
 				if (n == 1)
 					sF.model.addElement((i + 1) + ". " + list.search(node_name).cal[i]);
 			}
 		}
 	}
 } 