package window1;
/**
 * 모든 GUI창의 객체를 static으로 저장해두는 클래스
 */
public class Frame {
	static Login frame_login = new Login();
	static FindIDPW frame_findIDPW = new FindIDPW();
	static FindPW frame_findPW = new FindPW();
	static ChangePW frame_changePW = new ChangePW();
	static AddPerson frame_addperson = new AddPerson();
	static AddGroup frame_addgroup = new AddGroup();
}