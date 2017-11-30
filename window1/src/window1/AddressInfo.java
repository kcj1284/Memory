package window1;

public class AddressInfo {

	String name,phone,email,major,birthday,groupname,snsAddress,hash,gender;
	int code;
	int rowid;

	public AddressInfo(){};

	public AddressInfo(String name,String phone,String email,String major,int code,String birthday,String groupname,String snsAddress,String hash,String gender,int rowid){
		this.name = name;
		this.phone = phone;
		this.email = email;
		this.major = major;
		this.code = code;
		this.birthday = birthday;
		this.groupname = groupname;
		this.snsAddress = snsAddress;
		this.hash = hash;
		this.gender = gender;
		this.rowid = rowid;
	}

}
