package window1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

class Main extends JFrame {
	private JPanel contentPane;
	
	Main() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		setContentPane(contentPane);
		setBounds(300, 200, 1000, 600);
		contentPane.setLayout(new BorderLayout());
		
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		//Image icon = Toolkit.getDefaultToolkit().getImage("imges/iconorigin.png");
		//setIconImage(icon);
		

		/**
		 * 상단 인물추가 그룹추가 검색 로그아웃
		 */

		// 상단 패널
		JPanel mNORTH = new JPanel(); 
		add(mNORTH, BorderLayout.NORTH);
		mNORTH.setLayout(new BorderLayout());

		//상단 왼쪽 메뉴 패널
		JPanel mnMenu = new JPanel();
		mNORTH.add(mnMenu, BorderLayout.CENTER);
		mnMenu.setLayout(new FlowLayout(FlowLayout.LEFT));
		mnMenu.setBackground(Color.WHITE);
		
		JButton addPerson = new JButton("인물추가");
 		mnMenu.add(addPerson);
 		JButton addGroup = new JButton("그룹추가");
		mnMenu.add(addGroup);

		String[] mnmSearch = { "이름", "전화번호", "학번", "학년" };
		JComboBox searchCombo = new JComboBox();
		for (int i = 0; i < mnmSearch.length; i++)
			searchCombo.addItem(mnmSearch[i]);
		mnMenu.add(searchCombo);
		mnMenu.add(new JTextField(20));
		JButton search = new JButton("검색");
		mnMenu.add(search);

		//상단 오른쪽 로그아웃 패널
		JPanel mnLogout = new JPanel(); 
		mNORTH.add(mnLogout, BorderLayout.EAST);
		mnLogout.setLayout(new FlowLayout(FlowLayout.RIGHT));
		mnLogout.setBackground(Color.WHITE);
		JButton logout = new JButton("로그아웃");
		mnLogout.add(logout);
		logout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login login = new Login();
				login.setVisible(true);
				setVisible(false);
			}
		});

		
		/**
		 * 왼쪽 그룹목록 통계자료
		 */
		
		//왼쪽 패널
		JPanel mWEST = new JPanel(); 
		add(mWEST, BorderLayout.WEST);
		mWEST.setLayout(new GridLayout(2, 1));
		Color hexCf2f2f2 = new Color(242, 242, 242);
		mWEST.setBackground(Color.WHITE);

		//왼쪽 그룹목록 패널
		Panel mwGroup = new Panel();
		mWEST.add(mwGroup, BorderLayout.EAST);
		mwGroup.setLayout(new FlowLayout());
		JLabel listGroup = new JLabel("그룹목록");
		mwGroup.add(listGroup);

		//왼쪽 통계 패널
		Panel mwChart = new Panel(); 
		mwChart.setLayout(new GridLayout());
		add(mwChart);

		
		/**
		 * 가운데 이름 전화번호 학과 학번
		 */
		
		//중앙 연락처 패널
		JPanel mCENTER = new JPanel(); 
		add(mCENTER, BorderLayout.CENTER);
		mCENTER.setLayout(new BorderLayout());

		//중앙 연락처 구분 패널
		JPanel mcTop = new JPanel(); 
		mCENTER.add(mcTop, BorderLayout.NORTH);
		mcTop.setBackground(Color.WHITE);
		mcTop.setLayout(new GridLayout(1, 4));
		JLabel personName = new JLabel("이름");
		mcTop.add(personName);
		JLabel personNumber = new JLabel("전화번호");
		mcTop.add(personNumber);
		JLabel personMajor = new JLabel("학과");
		mcTop.add(personMajor);
		JLabel personStudentNumber = new JLabel("학번");
		mcTop.add(personStudentNumber);

		//중앙 연락처 목록 패널
		JPanel mcContact = new JPanel(); 
		mCENTER.add(mcContact, BorderLayout.CENTER);
		String[][] contact = new String[20][4];
		mcContact.setLayout(new GridLayout(20,4));
		for(int i=0; i<4; i++) {
			for(int j=0; j<20; j++) {
			JButton contactbutton = new JButton("");
			mcContact.add(contactbutton);
			}
		}

		setSize(1280, 720);
		setVisible(true);
	}

}
