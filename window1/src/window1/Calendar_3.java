package window1;
import javax.swing.*;
 import java.awt.event.*;
 import java.net.*;
 import java.awt.*;
 import java.util.*;
 
 public class Calendar_3 extends JPanel {
 	Color mainWhite = new Color(0xF5F4F3);
 	Color mainGreen = new Color(0x3B895E);
 	Color subGreen = new Color(0x367257);

 	Set_schedule sF;
 	///////////////
 	All_sch fr_2;
 
 	// 현재시간 //
 	Calendar cal = Calendar.getInstance();
 	int year = cal.get(Calendar.YEAR);
 	int month = cal.get(Calendar.MONTH) + 1;
 	int day = cal.get(Calendar.DATE);
 
 	//// 수시로 변경되는 시간들의 정보 ///
 	Calendar tmp = Calendar.getInstance();
 	int tmp_year = cal.get(Calendar.YEAR);
 	int tmp_month = cal.get(Calendar.MONTH);
 	int tmp_date = cal.get(Calendar.DATE);
 	int mon_fir = tmp.get(Calendar.DAY_OF_WEEK) - 1; // 변경날짜의 시작 요일
 	int mon_last = cal.getActualMaximum(Calendar.DATE); // 변경된 달의 총일수
 
 	/// 상단 패널 ///
 	JPanel pl_0_status = new JPanel();
 	JButton btn_year = new JButton(year + "년");
 	JButton btn_month = new JButton(month + "월");
 
 	JButton next_y = new JButton(">>");
 	JButton next_m = new JButton(">");
 	JButton previous_y = new JButton("<<");
 	JButton previous_m = new JButton("<");
 	JButton btn_td = new JButton("today : " + year + "  " + month + "  " + day);
 	//////// 중앙 패널 /////////
 	JPanel pl_day = new JPanel();
 	JLabel lb_day[] = new JLabel[7];
 	String days[] = { "일", "월", "화", "수", "목", "금", "토" };
 	JButton btn_day[] = new JButton[42];
 
 	JList jlist; // 메인 프레임 하단부 리스트
 	DefaultListModel model; // 리스트의 모델
 
 	JLabel lb_td = new JLabel(year + "년 " + month + "월 " + day + "일 " + doW(tmp.get(Calendar.DAY_OF_WEEK)));
 
 	//////////////////// 연결리스트 관련 //////////////////////
 
 	//ApiExplorer api;
 	boolean isapi[] = new boolean[9]; // api 참고는 시간이 좀 걸려서 boolean으로 확인
 	int holi_years[] = new int[9]; // 해당 년도의 공휴일 수 tmp_year2014
 
 	LinkedList list;
 	String node_name = year + "/" + month + "/" + day; // 노드 구별두기 위한 노드들의 이름
 														// (년도월일)
 
 	private CalendarData first; // 노드의 헤드
 
 	Calendar_3() {
 		tmp.set(Calendar.DATE, 1);
 		mon_fir = tmp.get(Calendar.DAY_OF_WEEK) - 1;
 
 		for (int i = 0; i < 9; i++) {
 			isapi[i] = false;
 			holi_years[i] = 0;
 		}
 
 		list = new LinkedList(); // 일정 연결 리스트 생성
 		jlist = new JList(new DefaultListModel()); // 리스트 초기화
 		model = (DefaultListModel) jlist.getModel(); // 리스트의 모델 초기화
 
 		setapi(year);
 
 		list.insert(node_name); // 연결리스트에 현재날짜로 노드 만듬
 
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
 
 	void init() { // 필요한 변수들 초기화
 		year = cal.get(Calendar.YEAR);
 		month = cal.get(Calendar.MONTH) + 1;
 		day = cal.get(Calendar.DATE);
 		//// 변경된 임시 시간 ///
 		tmp = Calendar.getInstance();
 		tmp_year = cal.get(Calendar.YEAR);
 		tmp_month = cal.get(Calendar.MONTH) - 1;
 		tmp_date = cal.get(Calendar.DATE);
 
 		mon_fir = tmp.get(Calendar.DAY_OF_WEEK) - 1; // 변경날짜의 시작 요일
 		mon_last = cal.getActualMaximum(Calendar.DATE); // 변경된 달의 총일수
 	}
 
 	void setCal(int year, int month, int day) { // 객체 cal 셋팅
 		tmp.set(Calendar.YEAR, year);
 		tmp.set(Calendar.MONTH, month);
 		tmp.set(Calendar.DATE, day);
 	}
 
 	void makeGUI() {
 		this.setLayout(new BorderLayout(0, 0));
 		days(); // 날짜 상태창 (메인 프레임 중앙부) 초기화
 		status(); // 년도 월 상태창(메인 프레임 상단부) 초기화
 		this.setBackground(mainWhite);
 	}
 
 	String doW(int num) { // 건네받은 요일의 숫자값을 한글로 변환
 		String dow = "일요일";
 		switch (num) {
 		case 2:
 			dow = "월요일";
 			break;
 		case 3:
 			dow = "화요일";
 			break;
 		case 4:
 			dow = "수요일";
 			break;
 		case 5:
 			dow = "목요일";
 			break;
 		case 6:
 			dow = "금요일";
 			break;
 		case 7:
 			dow = "토요일";
 			break;
 		}
 		return dow;
 	}
 
 	void days() { // 메인 프레임 중앙부
 		pl_day.setLayout(new GridLayout(7, 7)); // 요일에 해당하는 레이블을 포함하여 7x7 그리드
 		pl_day.setBackground(mainWhite);
 		for (int i = 0; i < 7; i++) { // 일~토요일까지 생성해서 윗줄에 부착
 			lb_day[i] = new JLabel(days[i]);
 			lb_day[i].setHorizontalAlignment(SwingConstants.CENTER);
 			lb_day[i].setForeground(Color.BLACK);
 			lb_day[i].setFont(new Font("돋움", Font.BOLD, 22));
 			pl_day.add(lb_day[i]);
 		}
 		lb_day[0].setForeground(Color.RED); // 일요일은 빨갛게
 		for (int i = 0; i < 42; i++) { // 현재 날짜에 맞게 버튼 위치 설정
 			if (i >= mon_fir && i < mon_last + mon_fir) { // mon_fir > 현재 년 월에
 															// 해당하는 1일의 요일,
 															// last는 총 일수
 				btn_day[i] = new JButton(new Integer(i - mon_fir + 1).toString()); // 1일부터
 																					// 생성
 				pl_day.add(btn_day[i]);
 			} else {
 				btn_day[i] = new JButton(""); // 해당하지 않는 버튼들은 text를 지우고 비활성화
 				btn_day[i].setEnabled(false);
 				pl_day.add(btn_day[i]);
 			}
 			btn_day[i].setBorderPainted(false); // 외곽선 지우기
 			if ((i - mon_fir + 1) == cal.get(Calendar.DATE)) { // 오늘 날짜에 해당하는
 																// 버튼은
 				btn_day[i].setBackground(Color.LIGHT_GRAY); // 짙은 회색
 				btn_day[i].setFont(new Font("돋움", Font.ITALIC, 18));
 			} else {
 				btn_day[i].setBackground(mainWhite); // 나머지는 밝은 회색
 				btn_day[i].setFont(new Font("돋움", Font.ITALIC, 18));
 			}
 
 			if (i % 7 == 0) {
 				btn_day[i].setForeground(Color.RED); // 일요일에 해당하는 버튼들은 빨갛게
 			} else
 				btn_day[i].setForeground(Color.BLACK); // 나머진 하얗게
 		}
 		add(pl_day, BorderLayout.CENTER);
 	}
 
 	void status() { // 메인 프레임 상단부.(상태창)
 		add(pl_0_status, BorderLayout.NORTH);
 		pl_0_status.setLayout(null);
 		pl_0_status.setPreferredSize(new Dimension(400, 85)); // 상단 패널 크기 강제 설정
 
 		pl_0_status.add(btn_year); // 년도
 		btn_year.setFont(new Font("고딕", Font.PLAIN, 35));
 		btn_year.setBounds(127, 35, 150, 50);
 		btn_year.setBorderPainted(false);
 		btn_year.setBackground(mainGreen);
 		btn_year.setForeground(mainWhite);
 
 		pl_0_status.add(btn_month); // 월
 		btn_month.setFont(new Font("고딕", Font.PLAIN, 30));
 		btn_month.setBounds(260, 35, 100, 50);
 		btn_month.setBorderPainted(false);
 		btn_month.setBackground(mainGreen);
 		btn_month.setForeground(mainWhite);
 
 		pl_0_status.add(next_m); // 다음 월 버튼
 		next_m.setBounds(370, 45, 50, 30);
 		next_m.addActionListener(new myListener());
 		next_m.setBorderPainted(false);
 		next_m.setBackground(mainGreen);
 		next_m.setForeground(mainWhite);
 
 		pl_0_status.add(next_y); // 다음 년 버튼
 		next_y.setBounds(420, 45, 50, 30);
 		next_y.addActionListener(new myListener());
 		next_y.setBorderPainted(false);
 		next_y.setBackground(mainGreen);
 		next_y.setForeground(mainWhite);
 
 		pl_0_status.add(previous_m); // 이전 월 버튼
 		previous_m.setBounds(75, 45, 50, 30);
 		previous_m.addActionListener(new myListener());
 		previous_m.setBorderPainted(false);
 		previous_m.setBackground(mainGreen);
 		previous_m.setForeground(mainWhite);
 
 		pl_0_status.add(previous_y); // 이전 년도 버튼
 		previous_y.setBounds(25, 45, 50, 30);
 		previous_y.addActionListener(new myListener());
 		previous_y.setBorderPainted(false);
 		previous_y.setBackground(mainGreen);
 		previous_y.setForeground(mainWhite);
 
 		pl_0_status.add(btn_td); // today 표시 버튼
 		btn_td.setBounds(140, 5, 210, 30);
 		btn_td.setFont(new Font("고딕", Font.BOLD, 18));
 		btn_td.setBackground(mainGreen);
 		btn_td.setForeground(mainWhite);
 		btn_td.setBorderPainted(false);
 		btn_td.addActionListener(new myListener());
 
 		pl_0_status.setBackground(mainGreen);
 		pl_0_status.setPreferredSize(new Dimension(500, 85)); // 크기고정
 	}
 
 	class myListener implements ActionListener { // 상단 패널에 위치한 버튼들의 리스너
 		public void actionPerformed(ActionEvent e) {
 			JButton tmp_bt = (JButton) e.getSource();
 			if (tmp_bt.equals(next_m)) { // 다음 월 눌렀을 시
 				if (tmp_month == 11) {
 					tmp_month = 0;
 					tmp_year++;
 					setapi(tmp_year);
 				} else
 					tmp_month += 1;
 			} else if (tmp_bt.equals(next_y)) { // 다음 년도 눌렀을 시
 				tmp_year += 1;
 				setapi(tmp_year);
 			}
 			if (tmp_bt.equals(previous_m)) { // 이전 월 눌렀을 시
 				if (tmp_month == 0) {
 					tmp_month = 11;
 					tmp_year--;
 					setapi(tmp_year);
 				} else
 					tmp_month = 1;
 			} else if (tmp_bt.equals(previous_y)) {
 				tmp_year = 1;
 				setapi(tmp_year);
 			} else if (tmp_bt.equals(btn_td)) { // today 버튼 눌렀을 때
 				tmp_year = year; // 변수를 현재 년도와 달로 변경
 				tmp_month = month - 1;
 				for (int i = 0; i < 42; i++)
 					if (btn_day[i].getText().equals(day + 1 + ""))
 						btn_day[i].setBackground(Color.LIGHT_GRAY); 
 				
 				
 				// 현재 날짜에
 				// 해당하는 버튼
 				// 짙은 회색으로
 				lb_td.setText(year + "년 " + month + "월 " + day + "일 " + doW(cal.get(Calendar.DAY_OF_WEEK)));
 				node_name = tmp_year + "/" + (tmp_month + 1) + "/" + day;
 				showList(0);
 				// 하단부 라벨도 변경 날짜에 맞게 변경
 			}
 			tmp.set(Calendar.MONTH, tmp_month); // 년도, 날짜 초기화
 			tmp.set(Calendar.YEAR, tmp_year);
 			btn_year.setText(tmp_year + "년"); // 패널 상단부의 년 월 변경
 			btn_month.setText((tmp_month + 1) + "월");
 			setting();
 		}
 	}
 
 	public void shift_day(int year, int month, int date) {
 		tmp_year = year; // 임시 시간을 입력받은 시간으로 변경
 		tmp_month = month - 1;
 		tmp_date = date;
 		for (int i = 0; i < 42; i++)
 			if (btn_day[i].getText().equals(day + 1 + "")) {
 				btn_day[i].setBackground(Color.LIGHT_GRAY);
 				btn_day[i].setFont(new Font("돋움", Font.ITALIC, 19));
 			} // 현재 날짜에 해당하는 버튼 짙은 회색으로
 		tmp.set(Calendar.MONTH, tmp_month); // 년도, 날짜 초기화
 		tmp.set(Calendar.YEAR, tmp_year);
 		tmp.set(Calendar.DATE, tmp_date);
 		lb_td.setText(year + "년 " + month + "월 " + tmp_date + "일 " + doW(tmp.get(Calendar.DAY_OF_WEEK)));
 		showList(0);
 		// 하단부 라벨도 변경 날짜에 맞게 변경
 		btn_year.setText(tmp_year + "년"); // 패널 상단부의 년 월 변경
 		btn_month.setText((tmp_month + 1) + "월");
 		setting();
 	}
 
 	public void setting() { // 바뀐 년 월에 맞게 날짜 버튼들 위치 초기화(첫 초기화때랑 방식 비슷) ////수정ㅊ
 		tmp.set(Calendar.DAY_OF_MONTH, 1);
 		mon_fir = tmp.get(Calendar.DAY_OF_WEEK) - 1;
 		mon_last = tmp.getActualMaximum(Calendar.DATE);
 		for (int i = 0; i < 42; i++) {
 			pl_day.remove(btn_day[i]);
 
 			if ((i - mon_fir + 2) == day + 1 && (year == tmp_year) && (month - 1 == tmp_month))
 				btn_day[i].setBackground(Color.LIGHT_GRAY);
 			else
 				btn_day[i].setBackground(mainWhite); // 배경색깔
 
 			if (i >= mon_fir && i < mon_last + mon_fir) { // 해당 1일부터 말일까지 위치에 맞게
 															// 세팅
 				btn_day[i].setText(new Integer(i - mon_fir + 1).toString());
 				btn_day[i].setEnabled(true);
 				pl_day.add(btn_day[i]);
 			} else {
 				btn_day[i].setText("");
 				btn_day[i].setEnabled(false);
 				pl_day.add(btn_day[i]);
 			}
 			if ((i % 7 == 0 ))
 				btn_day[i].setForeground(Color.RED); // 글자 색깔
 			else if (isCal(tmp.get(Calendar.YEAR) + "/" + (tmp.get(Calendar.MONTH) + 1) + "/" + btn_day[i].getText()))
 				btn_day[i].setForeground(Color.BLUE);
 			else
 				btn_day[i].setForeground(Color.BLACK);
 
 			btn_day[i].setBorderPainted(false);
 			btn_day[i].setFont(new Font("돋움", Font.ITALIC, 18));
 
 		}
 	}
 
 	boolean isCal(String name) { // 일정이 있나 없나 체크
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
 			for (int i = 0; i < tmp.count; i++) { // 해당 노드의 모든 일정 출력
 				if (n == 0)
 					model.addElement((i + 1) + ". " + list.search(node_name).cal[i]);
 				if (n == 1)
 					sF.model.addElement((i + 1) + ". " + list.search(node_name).cal[i]);
 			}
 		}
 	}
 } 