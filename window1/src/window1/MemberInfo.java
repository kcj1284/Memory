package window1;

/**
 * @author �谭��
 * 
 * ȸ�����Կ� ���� ������ ����ִ� Class
 */

public class MemberInfo {
	String id, pw, email, name, pwAnswer;
	int pwQuestion;

	/**
	 * ��ü ����
	 */
	public MemberInfo(){};

	/**
	 * ȸ������ ������ ����ִ� method
	 * 
	 * @param id ȸ������ �� �Է��� ID
	 * @param pw ȸ������ �� �Է��� password
	 * @param email ȸ������ �� �Է��� �̸���
	 * @param name ȸ������ �� �Է��� �̸�
	 * @param pwQuestion ȸ������ �� ������ ����
	 * @param pwAnswer ȸ������ �� �Է��� ������ ���� ��
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