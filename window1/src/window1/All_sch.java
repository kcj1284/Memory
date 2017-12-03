import javax.swing.*;
 import java.awt.event.*;
 import java.awt.*;
 import java.util.*;
 
 public class All_sch extends JFrame {
 	LinkedList list;
 	
 	JList jlist; // ���� ������ �ϴܺ� ����Ʈ
 	DefaultListModel model; // ����Ʈ�� ��
 	
 	int year;
 	int month;
 	int date;
 	
 	String node_name = year + "/" + month +"/" + date;
 	
 	//String holis [];
 	String names [];
 	int node_num = 0;
 	
 	All_sch(LinkedList list, int count){
 		setTitle("Schedule");
 		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
 		
 		this.list = list;
 		jlist = new JList(new DefaultListModel()); // ����Ʈ �ʱ�ȭ
 		model = (DefaultListModel) jlist.getModel(); // ����Ʈ�� �� �ʱ�ȭ
 		jlist.addKeyListener(new keyAdapter());
 		
 		makeGUI();
 		
 		add(new JScrollPane(jlist), BorderLayout.CENTER);
 		
 		setSize(280, 350);
 		setLocation(120, 200);
 		this.setResizable(false);
 		setVisible(true);
 	}
 	
 	void makeGUI() {
 		set_jlist();
 	}
 	
 	void set_jlist() {
 		model.clear();
 		list.findNodes();
 		node_num = list.count;
 		if(node_num != 0) {
 			names = list.names();
 			sortNames();
 			for(int i = 0 ; i < node_num ; i++)
 				model.addElement(names[i] + "");
 		}
 	}
 	
 	void devideStr(String name) {
 		int idx_1 = name.indexOf("/");
 		int idx_2 = name.lastIndexOf("/");
 		int idx_3 = name.indexOf("[");
 		year = new Integer(name.substring(0, idx_1)).intValue();
 		month = new Integer(name.substring(idx_1+1, idx_2)).intValue();
 		date = new Integer(name.substring(idx_2+1, idx_3).trim()).intValue();
 	}
 	
 	int changeStr(String name) {
 		devideStr(name);
 		return (year*10000) + (month*100) + date;
 	}
 	
 	void sortNames() {		// ����
 		for(int i = 0 ; i < node_num  1 ; i++) {
 			for(int j = i+1 ; j < node_num ; j++) {
 				if(changeStr(names[i]) > changeStr(names[j])) {
 					String tmp = new String(names[i]);
 					names[i] = new String(names[j]);
 					names[j] = new String(tmp);
 				}
 			}
 		}
 	}
 	class keyAdapter extends KeyAdapter{
 		public void keyPressed(KeyEvent e) {
 			if(e.getKeyCode() == KeyEvent.VK_ESCAPE) dispose();
 		}
 	}
 } 