package window1;

/**
 * 인물들의 주소록 정보를 담아주는 Class
 * 
 * @author 김강민
 *
 */
public class AddressInfo {

	String name, num, mail, major, hash, stid, sex, groupname, month, day;
	int rowid;

	/**
	 * 객체 생성
	 */
	public AddressInfo(){};

	/**
	 * 주소록에 저장될 내용들을 받아주는 method
	 * 
	 * @param name 주소록에 입력한 이름
	 * @param num 주소록에 입력한 전화번호
	 * @param mail 주소록에 입력한 메일
	 * @param major 주소록에 입력한 전공명
	 * @param stid 주소록에 입력한 학번
	 * @param month 주소록에 입력한 생일(월)
	 * @param day 주소록에 입력한 생일(일)
	 * @param groupname 주소록에 입력한 그룹명
	 * @param hash 주소록에 입력한 해시태그명
	 * @param sex 주소록에 입력한 성별
	 * @param rowid DB에 저장된 rowid 번호
	 */
	public AddressInfo(String name,String num,String mail,String major,String stid,String month,String day,String groupname,String hash,String sex,int rowid){
		this.name = name;
		this.num = num;
		this.mail = mail;
		this.major = major;
		this.stid = stid;
		this.month = month;
		this.day = day;
		this.groupname = groupname;
		this.hash = hash;
		this.sex = sex;
		this.rowid = rowid;
	}

}
