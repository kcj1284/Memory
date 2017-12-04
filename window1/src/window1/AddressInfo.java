package window1;

public class AddressInfo {

	String name, num, mail, major, hash, stid, sex, groupname, month, day;
	int rowid;

	public AddressInfo(){};

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
