package window1;

/**
 * ȸ�����Կ� ���� ����
 */

public class MemberInfo {
	String id, pw, email, name, pwAnswer;
	int pwQuestion;

	public MemberInfo(){};

	public MemberInfo(String id, String pw, String email, String name, int pwQuestion, String pwAnswer){
		this.id = id;
		this.pw = pw;
		this.email = email;
		this.name = name;
		this.pwQuestion = pwQuestion;
		this.pwAnswer = pwAnswer;
	}
}