package window1;

/**
 * @author 김강민
 * 
 * 회원가입에 사용된 정보를 담아주는 Class
 */

public class MemberInfo {
	String id, pw, email, name, pwAnswer;
	int pwQuestion;

	/**
	 * 객체 생성
	 */
	public MemberInfo(){};

	/**
	 * 회원가입 정보를 담아주는 method
	 * 
	 * @param id 회원가입 시 입력한 ID
	 * @param pw 회원가입 시 입력한 password
	 * @param email 회원가입 시 입력한 이메일
	 * @param name 회원가입 시 입력한 이름
	 * @param pwQuestion 회원가입 시 선택한 질문
	 * @param pwAnswer 회원가입 시 입력한 질문에 대한 답
	 */
	public MemberInfo(String id, String pw, String email, String name, int pwQuestion, String pwAnswer){
		this.id = id;
		this.pw = pw;
		this.email = email;
		this.name = name;
		this.pwQuestion = pwQuestion;
		this.pwAnswer = pwAnswer;
	}
}