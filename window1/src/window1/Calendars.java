package window1;


import java.util.*;

import javax.swing.*;

import java.awt.*; //밑에와 같은데 이것이좀더 포괄적이다.

import java.awt.event.*; //모든 이벤트를 쓴다라는것이다.

import java.io.*; // 파일 입출력에 대한 것들이다.

import java.text.*;

public class Calendars extends JPanel {

	protected int mm, dd;

	JPanel bp = new JPanel(); // BorderLayout 중앙 판넬 (버튼)
	JPanel tp = new JPanel(); // BorderLayout 상단 판넬 (년,달)

	protected JButton labs[][]; // 날짜 버튼

	protected int leadGap = 0;

	Calendar calendar = new GregorianCalendar(); // 자바에서 달력을 만들어주는 calendar를 선언함.(선언안할시 달력기능 x)

	protected final int thisMonth = calendar.get(Calendar.MONTH);

	private JLabel b0;
	private JLabel b1;
	private JButton b2 = new JButton("Black");
	private int count = 0;

	private JComboBox monthChoice;

	JTextPane textPane;

	JTextArea ta_content;

	SimpleDateFormat sdf;

	JButton b_save;

	GridBagLayout gbl;

	JTextField tf_date, tf_weather, tf_title; // 날짜, 날씨, 제목

	GridBagConstraints gbc;

	String month;

	String day;

	Calendars() { // 월,날짜 (정의)클릭하게 만듬

		super();

		// 여기부터 수정!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

		JPanel p = new JPanel(new BorderLayout());

		// 전체 돗자리

		gbl = new GridBagLayout();

		gbc = new GridBagConstraints();

		JPanel p1 = new JPanel(gbl);

		gbc.fill = gbc.BOTH;

		// 컴포넌트가 격자보가 작을때 처리..

		gbc.insets = new Insets(5, 5, 5, 5);

		// 격자와 격자사이의 여백

		tf_date = new JTextField("", 10);

		tf_weather = new JTextField("", 10);

		tf_title = new JTextField("", 10);

		ta_content = new JTextArea("", 8, 10);

		b_save = new JButton("저 장");

		sdf = new SimpleDateFormat("yy.MM.dd HH:mm E요일");

		tf_date.setEnabled(false);

		JPanel p2 = new JPanel();

		p.add(p2, "South");

		setMMDD(calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));

		buildGUI(); // 이거 지우면 아예 실행 x

		recompute(); // 이거 지우면 1~31일까지 표시 안됨.(오늘의 날짜도 빨강색으로 표시안됨)

	}

	public boolean Save(String year, String month, String day) {

		String filename = year + month + day;

		String savefile = textPane.getText();

		try {

			FileWriter fw = new FileWriter("C:\\다이어리\\" + filename + ".txt");

			fw.write(savefile, 0, savefile.length());

			fw.close();

			JOptionPane.showMessageDialog(null, "스케줄 입력 완료!!", "일정 관리", JOptionPane.INFORMATION_MESSAGE);

			return true;

		} catch (Exception e) {

			System.out.println(e);

			return false;

		}

	}

	private void setMMDD(int month, int today) { // 현재날짜 숫자에 맞게 설정해준다 .

		mm = month;

		dd = today;

	}

	String[] months = { "1월", "2월", "3월", "4월", "5월", "6월", "7월", "8월", "9월", "10월", "11월", "12월" };

	// 경계(날짜로 달선택하기)

	private void buildGUI() {

		getAccessibleContext().setAccessibleDescription("Calendar not accessible yet. Sorry!");

		setBorder(BorderFactory.createEtchedBorder());

		setLayout(new BorderLayout());

		JLabel la = new JLabel("2017년");
		tp.add(la);
		tp.add(monthChoice = new JComboBox()); // 달 콤보박스 생성,판넬에 넣기

		for (int i = 0; i < months.length; i++)

			// 달에 날짜를 선택함.

			monthChoice.addItem(months[i]); // 달의 콤보박스에 1~12월 넣기

		monthChoice.setSelectedItem(months[mm]); // 지금 현재 달찾는 기능 x

		monthChoice.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent ae) { // 달 선택의 이벤트 처리를 하는 기능.

				int i = monthChoice.getSelectedIndex();

				if (i >= 0) {

					mm = i;

					recompute();

				}

			}

		});

		monthChoice.getAccessibleContext().setAccessibleName("Months"); // 달선택

		add(BorderLayout.CENTER, tp); // (왼쪽,가운데정렬,오른쪽정렬)

		JPanel bp = new JPanel();

		bp.setLayout(new GridLayout(7, 7)); // 7x7 폼

		labs = new JButton[6][7]; // 6x7 폼

		bp.add(b0 = new JLabel("일", SwingConstants.CENTER)); // 월~일까지 버튼에 기입
		b0.setForeground(Color.RED);

		bp.add(new JLabel("월", SwingConstants.CENTER)); // 요일 라벨 생성,텍스트 중앙으로 배치

		bp.add(new JLabel("화", SwingConstants.CENTER));

		bp.add(new JLabel("수", SwingConstants.CENTER));

		bp.add(new JLabel("목", SwingConstants.CENTER));

		bp.add(new JLabel("금", SwingConstants.CENTER));

		bp.add(b1 = new JLabel("토", SwingConstants.CENTER));
		b1.setForeground(Color.BLUE);

		tp.add(b2); // 주말 색깔을 해제하고 색칠하는 것

		b2.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent ae) {

				JButton b = (JButton) ae.getSource();
				if (b.getText().equals("Black")) {
					b.setText("Color");
					for (int x = 0; x < 6; x++) {
						labs[x][0].setForeground(Color.BLACK);
						labs[x][6].setForeground(Color.BLACK);
					}

				} else {
					b.setText("Black");
					for (int x = 0; x < 6; x++) {
						labs[x][0].setForeground(Color.RED);
						labs[x][6].setForeground(Color.BLUE);
					}
				}
			}

		});

		ActionListener dateSetter = new ActionListener() { // 버튼이벤트

			public void actionPerformed(ActionEvent e) {

				// 버튼 이벤트 관한것들.

				JDialog dialogOval = new JDialog(); // 창 크기

				JDialog.setDefaultLookAndFeelDecorated(true);

				dialogOval.setBounds(new Rectangle(200, 200, 200, 200));

				JLabel mOval = new JLabel("and width(not 0!):");

				dialogOval.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);

				textPane = new JTextPane(); // 날짜 클릭했을때 글을 쓰는필드

				textPane.setSize(100, 20);

				gbl = new GridBagLayout();

				JPanel p = new JPanel(new BorderLayout());

				JPanel p1 = new JPanel(gbl);

				p.add(p1, "Center");

				b_save = new JButton("저 장");

				JPanel p2 = new JPanel();

				p2.add(b_save);

				p.add(p2, "South");

				b_save.addActionListener(this);

				JTextField rwidth = new JTextField();

				b_save = new JButton("저 장");

				month = monthChoice.getSelectedItem().toString().replace("월", "");

				day = e.getActionCommand();

				b_save.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {

						Object obj = e.getSource();

						if (obj == b_save) { // 저장버튼 이벤트

							Save("2017", month, day);

						} else

							System.exit(0);

					}

				});

				JButton fOval = new JButton("Fill"); // 창생성

				fOval.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {

					}

				});

				Container cOval = getRootPane();

				cOval.add(mOval);

				dialogOval.add(textPane);

				cOval.add(rwidth);

				dialogOval.add(b_save, BorderLayout.SOUTH);

				cOval.add(fOval);

				dialogOval.setVisible(true); // 이것들이 진실이면 소스를 실행하게 해주는것들.

				String num = e.getActionCommand();

				if (!num.equals("")) {

					setDayActive(Integer.parseInt(num));

				}

			}

		};

		for (int i = 0; i < 6; i++)

			// 전체적인 폼을 만듬( 행과열)

			for (int j = 0; j < 7; j++) {

				bp.add(labs[i][j] = new JButton(""));

				labs[i][j].addActionListener(dateSetter);// 버튼을 누르면 메모 입력할수있는 기능 넣기

			}

		add(bp, BorderLayout.SOUTH);

	}

	public final static int dom[] = { 31, 28, 31, 30, /* jan feb mar apr */// 달들에 있는날짜들을 빼줌.

			31, 30, 31, 31, /* may jun jul aug */

			30, 31, 30, 31 /* sep oct nov dec */

	};

	protected void recompute() {

		clearDayActive();

		leadGap = new GregorianCalendar(2017, mm, 1).get(Calendar.DAY_OF_WEEK) - 1;

		int daysInMonth = dom[mm];

		// 달의 앞에 빈칸

		for (int i = 0; i < leadGap; i++) {
			labs[0][i].setText("");
		}

		// 버튼에 날짜 넣기

		for (int i = 1; i <= daysInMonth; i++) {

			JButton b = labs[(leadGap + i - 1) / 7][(leadGap + i - 1) % 7];

			b.setText(Integer.toString(i));
			if((leadGap + i - 1) % 7 == 0) 
				b.setForeground(Color.RED);
			
			else if((leadGap + i - 1) % 7 == 6) 
				b.setForeground(Color.BLUE);

		}

		// 뒷부분 공백

		for (int i = leadGap + daysInMonth; i < 6 * 7; i++) {

			labs[(i) / 7][(i) % 7].setText("");

		}

		// Shade current day, only if current month

		if (mm == thisMonth)

			setDayActive(dd); // shade the box for today

		// Say we need to be drawn on the screen

		repaint();

	}

	// 숫자없으면 데이터 표현 x(다음으로 넘어감)

	private void clearDayActive() {

		JButton b;

		if (activeDay > 0) {

			b = labs[(leadGap + activeDay - 1) / 7][(leadGap + activeDay - 1) % 7];

			b.setBackground(b0.getBackground());

			b.repaint();

			activeDay = -1;

		}

	}

	private int activeDay = -1;

	// 달에 있는 날자 표기함

	public void setDayActive(int newDay) {

		clearDayActive();

		if (newDay <= 0)

			dd = new GregorianCalendar().get(Calendar.DAY_OF_MONTH);

		else

			dd = newDay;

		activeDay = newDay;

	}

	// 프로그램 메인

	public void Calendars(){

		JFrame f = new JFrame("Calendars"); // cal 폼

		Container c = f.getContentPane(); // 모양을 얻어서 c에 넣겠다.

		// c.setLayout(null);

		c.setLayout(new FlowLayout());

		c.add(new Calendars()); // 대상추가

		f.pack();

		f.setVisible(true);

		// 폴더 생성

		File fa = new File("c:\\다이어리");

		if (fa.exists())

			fa.delete();

		try {

			Thread.sleep(3000);

		} catch (InterruptedException ie) {

		}

		if (!fa.exists())

			fa.mkdir();

	}

}
