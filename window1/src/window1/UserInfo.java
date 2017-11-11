package window1;
/**
 * @author 손휘진
 * 회원 관리 시스템에 필요한 필드들을 가지는 클래스
 */
public class UserInfo {
	String id, pw, email, name, pwAnswer, introduce, followee, follower;
	int animal, followerNum, postNum, pwQuestion;
	
	public UserInfo(){};
	
	public UserInfo(String id, String pw, String email, String name, int pwQuestion,
			String pwAnswer, String introduce, int animal, int followerNum, int postNum,
			String followee, String follower){
		this.id = id;
		this.pw = pw;
		this.email = email;
		this.name = name;
		this.pwQuestion = pwQuestion;
		this.pwAnswer = pwAnswer;
		this.introduce = introduce;
		this.animal = animal;
		this.followerNum = followerNum;
		this.postNum = postNum;
		this.followee = followee;
		this.follower = follower;
	}
}