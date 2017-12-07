package window1;

/**
 * �ι����� �ּҷ� ������ ����ִ� Class
 * 
 * @author �谭��
 *
 */
public class AddressInfo {

	String name, num, mail, major, hash, stid, sex, groupname, month, day;
	int rowid;

	/**
	 * ��ü ����
	 */
	public AddressInfo(){};

	/**
	 * �ּҷϿ� ����� ������� �޾��ִ� method
	 * 
	 * @param name �ּҷϿ� �Է��� �̸�
	 * @param num �ּҷϿ� �Է��� ��ȭ��ȣ
	 * @param mail �ּҷϿ� �Է��� ����
	 * @param major �ּҷϿ� �Է��� ������
	 * @param stid �ּҷϿ� �Է��� �й�
	 * @param month �ּҷϿ� �Է��� ����(��)
	 * @param day �ּҷϿ� �Է��� ����(��)
	 * @param groupname �ּҷϿ� �Է��� �׷��
	 * @param hash �ּҷϿ� �Է��� �ؽ��±׸�
	 * @param sex �ּҷϿ� �Է��� ����
	 * @param rowid DB�� ����� rowid ��ȣ
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
