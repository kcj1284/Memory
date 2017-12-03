package window1;

import java.util.*;

import javax.swing.*;

import Album.SlideShow;

import java.awt.*; //�ؿ��� ������ �̰������� �������̴�.
import java.awt.event.*; //��� �̺�Ʈ�� ���ٶ�°��̴�.
import java.io.*; // ���� ����¿� ���� �͵��̴�.
import java.text.*;

public class Calendars extends JPanel {
	protected int yy;
	protected int mm, dd;
	protected JButton labs[][];
	protected int leadGap = 0;

	Calendar calendar = new GregorianCalendar(); // �ڹٿ��� �޷��� ������ִ� calendar�� ������.(������ҽ� �޷±�� x)

	protected final int thisYear = calendar.get(Calendar.YEAR);
	protected final int thisMonth = calendar.get(Calendar.MONTH);
	private JButton b0;
	private JComboBox monthChoice;
	private JComboBox yearChoice;

	JTextPane textPane;
	JTextArea ta_content;
	SimpleDateFormat sdf;
	GregorianCalendar gc;
	JButton b_save;
	GridBagLayout gbl;
	JTextField tf_date, tf_weather, tf_title; // ��¥, ����, ����
	GridBagConstraints gbc;

	String year;
	String month;
	String day;

	Calendars() { // ��.��,��¥ (����)Ŭ���ϰ� ����

		super();

		JPanel p = new JPanel(new BorderLayout());

		// ��ü ���ڸ�

		gbl = new GridBagLayout();
		gbc = new GridBagConstraints();
		JPanel p1 = new JPanel(gbl);
		gbc.fill = gbc.BOTH;
		// ������Ʈ�� ���ں��� ������ ó��..
		gbc.insets = new Insets(5, 5, 5, 5);
		// ���ڿ� ���ڻ����� ����
		tf_date = new JTextField("", 10);
		tf_weather = new JTextField("", 10);
		tf_title = new JTextField("", 10);
		ta_content = new JTextArea("", 8, 10);
		b_save = new JButton("�� ��");
		gc = new GregorianCalendar();
		sdf = new SimpleDateFormat("yy.MM.dd HH:mm E����");
		gc.set(gc.get(Calendar.YEAR), gc.get(Calendar.MONTH), gc.get(Calendar.DAY_OF_MONTH),
				gc.get(Calendar.HOUR_OF_DAY), gc.get(Calendar.MINUTE));
		tf_date.setText(sdf.format(gc.getTime()));
		tf_date.setEnabled(false);
		JPanel p2 = new JPanel();
		p.add(p2, "South");
		setYYMMDD(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)); // ��
																													// ���
																													// �߰�(2010��,2011���...)
		buildGUI(); // �̰� ����� �ƿ� ���� x
		recompute(); // �̰� ����� 1~31�ϱ��� ǥ�� �ȵ�.(������ ��¥�� ���������� ǥ�þȵ�)
	}

	public boolean Save(String year, String month, String day) {

		// ���� ��

		// gc = new GregorianCalendar();

		// sdf = new SimpleDateFormat("yyyyMMdd");

		// gc.set(gc.get(Calendar.YEAR), gc.get(Calendar.MONTH),
		// gc.get(Calendar.DAY_OF_MONTH));

		String filename = year + month + day;
		String savefile = "����:" + textPane.getText();

		try {
			FileWriter fw = new FileWriter("C:\\���̾\\" + filename + ".txt");
			fw.write(savefile, 0, savefile.length());
			fw.close();
			JOptionPane.showMessageDialog(null, "������ �Ϸ� �Ǿ����ϴ�.", "Success!!", JOptionPane.INFORMATION_MESSAGE);
			return true;

		} catch (Exception e) {

			System.out.println(e);

			return false;

		}

	}

	private void setYYMMDD(int year, int month, int today) { // ���糯¥ ���ڿ� �°� �������ش� .

		yy = year;

		mm = month;

		dd = today;

	}

	String[] months = { "1��", "2��", "3��", "4��", "5��", "6��", "7��", "8��", "9��", "10��", "11��", "12��" };

	// ���(��¥�� �޼����ϱ�)

	private void buildGUI() {
		getAccessibleContext().setAccessibleDescription("Calendar not accessible yet. Sorry!");
		setBorder(BorderFactory.createEtchedBorder());
		setLayout(new BorderLayout());
		setLayout(new BorderLayout());// �׳� 1
		JPanel tp = new JPanel(); // �޼��� �ϴ°�(��ü���� Ʋ(�޷�))
		tp.add(monthChoice = new JComboBox()); // (�� �����Ҽ��ִ°�)

		for (int i = 0; i < months.length; i++)
			// �޿� ��¥�� ������.

			monthChoice.addItem(months[i]); // ���� �޺��ڽ� x
		monthChoice.setSelectedItem(months[mm]); // ���� ���� ��ã�� ��� x
		monthChoice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) { // �� ������ �̺�Ʈ ó���� �ϴ� ���.

				int i = monthChoice.getSelectedIndex();

				if (i >= 0) {

					mm = i;

					recompute();

				}

			}

		});

		monthChoice.getAccessibleContext().setAccessibleName("Months"); // �޼���
		monthChoice.getAccessibleContext().setAccessibleDescription("Choose a month of the year"); // �⵵ ����
		tp.add(yearChoice = new JComboBox()); // �⵵�� ���Ѱ͵�
		yearChoice.setEditable(true);
		for (int i = yy - 5; i < yy + 5; i++)
			yearChoice.addItem(Integer.toString(i));
		yearChoice.setSelectedItem(Integer.toString(yy));
		yearChoice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {

				int i = yearChoice.getSelectedIndex();

				if (i >= 0) {

					yy = Integer.parseInt(yearChoice.getSelectedItem().toString());

					recompute();

				}

			}

		});

		add(BorderLayout.CENTER, tp); // (����,�������,����������)
		JPanel bp = new JPanel();
		bp.setLayout(new GridLayout(7, 7)); // 7x7 ��
		labs = new JButton[6][7]; // 6x7 ��
		bp.add(b0 = new JButton("��")); // ��~�ϱ��� ��ư�� ����
		bp.add(new JButton("��"));
		bp.add(new JButton("ȭ"));
		bp.add(new JButton("��"));
		bp.add(new JButton("��"));
		bp.add(new JButton("��"));
		bp.add(new JButton("��"));

		ActionListener dateSetter = new ActionListener() { // 27�� �������� â�� �߰� �ϴ±��.

			public void actionPerformed(ActionEvent e) {
				// ��ư �̺�Ʈ ���Ѱ͵�.
				JDialog dialogOval = new JDialog(); // â ũ��
				JDialog.setDefaultLookAndFeelDecorated(true);
				dialogOval.setBounds(new Rectangle(200, 200, 200, 200));
				JLabel mOval = new JLabel("and width(not 0!):");
				dialogOval.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
				textPane = new JTextPane(); // ��¥ Ŭ�������� ���� �����ʵ�
				textPane.setSize(100, 20);
				gbl = new GridBagLayout();
				JPanel p = new JPanel(new BorderLayout());
				JPanel p1 = new JPanel(gbl);
				p.add(p1, "Center");
				b_save = new JButton("�� ��");
				JPanel p2 = new JPanel();
				p2.add(b_save);
				p.add(p2, "South");
				b_save.addActionListener(this);
				JTextField rwidth = new JTextField();
				b_save = new JButton("�� ��");
				// JButton dOval = new JButton("����");

				year = yearChoice.getSelectedItem().toString();

				month = monthChoice.getSelectedItem().toString().replace("��", "");

				day = e.getActionCommand();

				b_save.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {

						Object obj = e.getSource();

						if (obj == b_save) { // �����ư �̺�Ʈ

							Save(year, month, day);

						} else

							System.exit(0);

					}

				});

				JButton fOval = new JButton("Fill"); // â����

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

				dialogOval.setVisible(true); // �̰͵��� �����̸� �ҽ��� �����ϰ� ���ִ°͵�.

				String num = e.getActionCommand();

				if (!num.equals("")) {

					setDayActive(Integer.parseInt(num));

				}

			}

		};

		for (int i = 0; i < 6; i++)

			// ��ü���� ���� ����( �����)

			for (int j = 0; j < 7; j++) {

				bp.add(labs[i][j] = new JButton(""));

				labs[i][j].addActionListener(dateSetter);// 151

			}

		add(BorderLayout.SOUTH, bp);

	}

	public final static int dom[] = {
			31, 28, 31, 30, /* jan feb mar apr */// �޵鿡 �ִ³�¥���� ����.
			31, 30, 31, 31, /* may jun jul aug */
			30, 31, 30, 31 /* sep oct nov dec */

	};

	// ���� �����

	protected void recompute() {

		if (mm < 0 || mm > 11)

			throw new IllegalArgumentException("Month " + mm + " bad, must be 0-11");

		clearDayActive();
		calendar = new GregorianCalendar(yy, mm, dd); // ���� ��¥ ���(���糯¥ ���)
		leadGap = new GregorianCalendar(yy, mm, 1).get(Calendar.DAY_OF_WEEK) - 1;
		int daysInMonth = dom[mm];
		if (isLeap(calendar.get(Calendar.YEAR)) && mm > 1)

			++daysInMonth;

		// Blank out the labels before 1st day of month

		for (int i = 0; i < leadGap; i++) {

			labs[0][i].setText("");

		}

		// Fill in numbers for the day of month.

		for (int i = 1; i <= daysInMonth; i++) {

			JButton b = labs[(leadGap + i - 1) / 7][(leadGap + i - 1) % 7];

			b.setText(Integer.toString(i));

		}

		// 7 days/week * up to 6 rows

		for (int i = leadGap + 1 + daysInMonth; i < 6 * 7; i++) {

			labs[(i) / 7][(i) % 7].setText("");

		}

		// Shade current day, only if current month

		if (thisYear == yy && mm == thisMonth)

			setDayActive(dd); // shade the box for today

		// Say we need to be drawn on the screen

		repaint();

	}

	public boolean isLeap(int year) { // ���� �����

		if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0)

			return true;

		return false;

	}

	public void setDate(int yy, int mm, int dd) { // ���� �����

		this.yy = yy;
		this.mm = mm;
		this.dd = dd;
		recompute();

	}

	// ���ھ����� ������ ǥ�� x(�������� �Ѿ)

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

	// �޿� �ִ� ���� ǥ����

	public void setDayActive(int newDay) {

		clearDayActive();
		if (newDay <= 0)

			dd = new GregorianCalendar().get(Calendar.DAY_OF_MONTH);

		else

			dd = newDay;

		Component square = labs[(leadGap + newDay - 1) / 7][(leadGap + newDay - 1) % 7];
		square.setBackground(Color.red);
		square.repaint();
		activeDay = newDay;
	}

	// ���α׷� ����

	public static void main(String[] av) {

		JFrame f = new JFrame("5�� �޷¸����"); // cal ��
		Container c = f.getContentPane(); // ����� �� c�� �ְڴ�.
		c.setLayout(null);
		c.setLayout(new FlowLayout());
		c.add(new Calendars()); // ����߰�
		f.pack();
		f.setVisible(true);

		// ���� ����

		File fa = new File("c:\\���̾");

		if (fa.exists())
			fa.delete();

		try {
			Thread.sleep(3000);
		}
		catch (InterruptedException ie) {
		}

		if (!fa.exists())

			fa.mkdir();
	}
}
