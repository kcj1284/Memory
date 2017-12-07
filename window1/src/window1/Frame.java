package window1;

/**
 * @author 김찬중 
 * 모든 GUI창의 객체를 static으로 저장해두는 Class
 */
public class Frame {
	static Home frame_home = new Home();
	static Login frame_login = new Login();
	static Join frame_join = new Join();
	static FindIDPW frame_findIDPW = new FindIDPW();
	static FindPW frame_findPW = new FindPW();
	static ChangePW frame_changePW = new ChangePW();
	static AddPerson frame_addperson = new AddPerson();
	static AddGroup frame_addgroup = new AddGroup();
	static CalendarMain frame_calendars = new CalendarMain();
}
