package window1;


public class UserInfo {
	String id, pw, email, name, pwAnswer;
	int pwQuestion;
	
	public UserInfo(){};
	
	public UserInfo(String id, String pw, String email, String name, int pwQuestion, String pwAnswer){
		this.id = id;
		this.pw = pw;
		this.email = email;
		this.name = name;
		this.pwQuestion = pwQuestion;
		this.pwAnswer = pwAnswer;
	}
}