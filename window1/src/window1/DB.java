package window1;

import java.sql.*;
import java.util.Vector;

/**
 * SQLite�� DB�� �����ϴ� ��ǥ Class
 * 
 * @author �谭��
 *
 */
public class DB {
	static Connection conn;
	static PreparedStatement stmt;
	static ResultSet rs;
	static ResultSet rs2;

	/**
	 * DB�� �������ִ� method�� ȣ�����ִ� method
	 */
	public DB() {
		connectDB();
	}

	/**
	 * DB�� �������ִ� method
	 */
	protected void connectDB() {
		try {
			String url = "jdbc:sqlite:resource/memory.db";
			conn = DriverManager.getConnection(url);

		} catch (SQLException e) {
			System.err.println("Error : DB Connect - Driver Manager");
			System.out.println(e.getMessage());
		}
	}

	/**
	 * 
	 * @param s ������ �������� ���ڿ��� �޾��ִ� �κ�
	 */
	public static void inputQuery(String s){
		try{
			stmt = null;
			stmt = conn.prepareStatement(s);
			stmt.executeUpdate(); 
		}
		catch(SQLException e){
			e.printStackTrace();
			System.err.println("Error : inputQuery\n");
			System.out.println(e.getMessage());
		}
	}

	/**
	 * ȸ������ ������ ���Ϳ� �߰������ִ� method
	 * 
	 * @return MemberInfo ���Ϳ� ���� ����(�߰�)�� �����Ͽ��� �� ��ȯ
	 */
	public static boolean getMember() { // ������ �α��� ���� ����
		boolean isSuccess = false;
		try {
			MemberInfo m;
			Data.member_vector.removeAllElements();
			stmt = conn.prepareStatement("select * from member"); // ������ ����
			rs = stmt.executeQuery();

			while (rs.next()) { // result set�� �� ���� ���
				m = new MemberInfo();
				m.id = rs.getString("id");
				m.pw = rs.getString("pw");
				m.email = rs.getString("email");
				m.name = rs.getString("name");
				m.pwQuestion = Integer.parseInt(rs.getString("pwQuestion"));
				m.pwAnswer = rs.getString("pwAnswer");

				Data.member_vector.addElement(m); // ���Ϳ� ���� ���� �߰�
			}
			isSuccess = true;
		} catch (SQLException e) {
			System.err.println("Error : get member\n");
			System.out.println(e.getMessage());
			isSuccess = false;
		}
		return isSuccess;
	}

	/**
	 * �ּҷ� ������ ���Ϳ� �߰������ִ� method
	 * 
	 * @return AddressInfo ���Ϳ� ���� ����(�߰�)�� �����Ͽ��� �� ��ȯ
	 */
	public boolean getAddress() { // �ּҷ� ���� ����
		boolean isSuccess = false;
		try {
			AddressInfo m;
			Data.address_vector.removeAllElements();
			stmt = conn.prepareStatement("select * from address"); // ������ ����
			rs = stmt.executeQuery();

			while (rs.next()) { // result set�� �� ���� ���
				m = new AddressInfo();
				m.name = rs.getString("name");
				m.num = rs.getString("num");
				m.mail = rs.getString("mail");
				m.major = rs.getString("major");
				m.stid = rs.getString("stid");
				m.month = rs.getString("month");
				m.day = rs.getString("day");
				m.groupname = rs.getString("groupname");
				m.hash = rs.getString("hash");
				m.sex = rs.getString("sex");

				Data.address_vector.addElement(m); // ���Ϳ� �ּҷ� ���� �߰�
			}
			isSuccess = true;
		} catch (SQLException e) {
			System.err.println("Error : get address\n");
			System.out.println(e.getMessage());
			isSuccess = false;
		}
		return isSuccess;
	}

	/**
	 * ȸ������ ������ DB�� �־��ִ� method
	 * 
	 * @param id ȸ������ �� ����� ���̵�
	 * @param passwd ȸ������ �� ����� �н�����
	 * @param email ȸ������ �� ����� �̸���
	 * @param name ȸ������ �� ����� �̸�
	 * @param question ȸ������ �� ����� ��й�ȣ ã�� �� ���� ���� 
	 * @param answer ȸ������ �� ����� ��й�ȣ ã�� �� �� ������ ���� ��
	 * @return DB�� ȸ������ ������ ���������� insert�� �� ��ȯ
	 */
	protected boolean insertMember(String id, String passwd, String email, String name, int question, String answer) {
		boolean isSuccess = false;
		String sql = "insert into member(id, pw, email, name, pwQuestion, pwAnswer) values(?, ?, ?, ?, ?, ?)";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, id);
			stmt.setString(2, passwd);
			stmt.setString(3, email);
			stmt.setString(4, name);
			stmt.setInt(5, question );
			stmt.setString(6, answer);
			stmt.executeUpdate();

			Data.member_vector.addElement(new MemberInfo(id,passwd,email,name,question,answer)); // ���Ϳ� ���� ���� �߰�
			isSuccess = true;
		} catch (SQLException e) {
			System.err.println("Error : Insert Member");
			System.out.println(e.getMessage());
			isSuccess = false;
		}
		return isSuccess;
	}

	/**
	 * �ڽ��� ����� �ּҷ� ��ü�� �˻����ִ� method
	 * 
	 * @return ��ü �˻��� ������ �� ��ȯ
	 */
	public boolean searchaddress(){
		boolean isSuccess = false;
		try {
			AddressInfo m;
			Data.address_vector.removeAllElements();
			stmt = conn.prepareStatement("select rowid,* from address"); // ������ ����
			rs = stmt.executeQuery();

			while (rs.next()) { // result set�� �� ���� ���
				m = new AddressInfo();
				m.name = rs.getString("name");
				m.num = rs.getString("num");
				m.mail = rs.getString("mail");
				m.major = rs.getString("major");
				m.stid = rs.getString("stid");
				m.month = rs.getString("month");
				m.groupname = rs.getString("groupname");
				m.day = rs.getString("day");
				m.hash = rs.getString("hash");
				m.sex = rs.getString("sex");
				m.rowid = rs.getInt("rowid");

				Data.address_vector.addElement(m); 
			}
			isSuccess = true;
		} catch (SQLException e) {
			System.err.println("Error : overall search\n");
			System.out.println(e.getMessage());
			isSuccess = false;
		}
		return isSuccess;
	}

	/**
	 * �ּҷϿ��� �̸��� �������� �˻����ִ� method
	 * 
	 * @param name �˻� �������� �ݿ��� �̸�
	 * @return �˻��� ���� �� ��ȯ
	 */
	public boolean searchname(String name){
		boolean isSuccess = false;
		try {
			AddressInfo m;
			Data.address_vector.removeAllElements();
			stmt = conn.prepareStatement("select rowid,* from address WHERE name like ?"); // ������ ����
			stmt.setString(1, '%'+name+'%');

			rs = stmt.executeQuery();

			while (rs.next()) { // result set�� �� ���� ���
				m = new AddressInfo();
				m.name = rs.getString("name");
				m.num = rs.getString("num");
				m.mail = rs.getString("mail");
				m.major = rs.getString("major");
				m.stid = rs.getString("stid");
				m.month = rs.getString("month");
				m.groupname = rs.getString("groupname");
				m.day = rs.getString("day");
				m.hash = rs.getString("hash");
				m.sex = rs.getString("sex");
				m.rowid = rs.getInt("rowid");

				Data.address_vector.addElement(m);
			}
			isSuccess = true;
		} catch (SQLException e) {
			System.err.println("Error : search name\n");
			System.out.println(e.getMessage());
			isSuccess = false;
		}
		return isSuccess;
	}

	/**
	 * �ּҷϿ��� �й��� �������� �˻����ִ� method
	 * 
	 * @param stid �˻� �������� �ݿ��� �й�
	 * @return �˻��� ���� �� ��ȯ
	 */
	public boolean searchcode(String stid){
		boolean isSuccess = false;
		try {
			AddressInfo m;
			Data.address_vector.removeAllElements();
			stmt = conn.prepareStatement("select rowid,* from address WHERE stid like ?"); // ������ ����
			stmt.setString(1, '%'+stid+'%');
			rs = stmt.executeQuery();

			while (rs.next()) { // result set�� �� ���� ���
				m = new AddressInfo();
				m.name = rs.getString("name");
				m.num = rs.getString("num");
				m.mail = rs.getString("mail");
				m.major = rs.getString("major");
				m.stid = rs.getString("stid");
				m.month = rs.getString("month");
				m.groupname = rs.getString("groupname");
				m.day = rs.getString("day");
				m.hash = rs.getString("hash");
				m.sex = rs.getString("sex");
				m.rowid = rs.getInt("rowid");

				Data.address_vector.addElement(m); 
			}
			isSuccess = true;
		} catch (SQLException e) {
			System.err.println("Error : search code\n");
			System.out.println(e.getMessage());
			isSuccess = false;
		}
		return isSuccess;
	}

	/**
	 * �ּҷϿ��� ������ �������� �˻����ִ� method
	 * 
	 * @param major �˻� �������� �ݿ��� ������
	 * @return �˻��� ���� �� ��ȯ
	 */
	public boolean searchmajor(String major){
		boolean isSuccess = false;
		try {
			AddressInfo m;
			Data.address_vector.removeAllElements();
			stmt = conn.prepareStatement("select rowid,* from address WHERE major like ?"); // ������ ����
			stmt.setString(1, '%'+major+'%');
			rs = stmt.executeQuery();

			while (rs.next()) { // result set�� �� ���� ���
				m = new AddressInfo();
				m.name = rs.getString("name");
				m.num = rs.getString("num");
				m.mail = rs.getString("mail");
				m.major = rs.getString("major");
				m.stid = rs.getString("stid");
				m.month = rs.getString("month");
				m.groupname = rs.getString("groupname");
				m.day = rs.getString("day");
				m.hash = rs.getString("hash");
				m.sex = rs.getString("sex");
				m.rowid = rs.getInt("rowid");

				Data.address_vector.addElement(m); 
			}
			isSuccess = true;
		} catch (SQLException e) {
			System.err.println("Error : search major\n");
			System.out.println(e.getMessage());
			isSuccess = false;

		}
		return isSuccess;
	}

	/**
	 * �ּҷϿ��� ��ȣ�� �������� �˻����ִ� method
	 * 
	 * @param num �˻� �������� �ݿ��� ��ȣ
	 * @return �˻��� ���� �� ��ȯ
	 */
	public boolean searchphone(String num){
		boolean isSuccess = false;
		try {
			AddressInfo m;
			Data.address_vector.removeAllElements();
			stmt = conn.prepareStatement("select rowid,* from address WHERE num like ?"); // ������ ����
			stmt.setString(1, '%'+num+'%');

			rs = stmt.executeQuery();

			while (rs.next()) { // result set�� �� ���� ���
				m = new AddressInfo();
				m.name = rs.getString("name");
				m.num = rs.getString("num");
				m.mail = rs.getString("mail");
				m.major = rs.getString("major");
				m.stid = rs.getString("stid");
				m.month = rs.getString("month");
				m.groupname = rs.getString("groupname");
				m.day = rs.getString("day");
				m.hash = rs.getString("hash");
				m.sex = rs.getString("sex");
				m.rowid = rs.getInt("rowid");

				Data.address_vector.addElement(m); 
			}
			isSuccess = true;
		} catch (SQLException e) {
			System.err.println("Error : serch phoneNumber\n");
			System.out.println(e.getMessage());
			isSuccess = false;
		}
		return isSuccess;
	}

	/**
	 * �ּҷϿ��� �ؽ��±�(Ű����)�� �������� �˻����ִ� method
	 * 
	 * @param hash �˻� �������� �ݿ��� �ؽø�
	 * @return �˻��� ���� �� ��ȯ
	 */
	public boolean searchHash(String hash){
		boolean isSuccess = false;
		try {
			AddressInfo m;
			Data.address_vector.removeAllElements();
			stmt = conn.prepareStatement("select rowid,* from address WHERE hash like ?"); // ������ ����
			stmt.setString(1, '%'+hash+'%');

			rs = stmt.executeQuery();

			while (rs.next()) { // result set�� �� ���� ���
				m = new AddressInfo();
				m.name = rs.getString("name");
				m.num = rs.getString("num");
				m.mail = rs.getString("mail");
				m.major = rs.getString("major");
				m.stid = rs.getString("stid");
				m.month = rs.getString("month");
				m.groupname = rs.getString("groupname");
				m.day = rs.getString("day");
				m.hash = rs.getString("hash");
				m.sex = rs.getString("sex");
				m.rowid = rs.getInt("rowid");

				Data.address_vector.addElement(m); 
			}
			isSuccess = true;
		} catch (SQLException e) {
			System.err.println("Error : search hash\n");
			System.out.println(e.getMessage());
			isSuccess = false;
		}
		return isSuccess;
	}

	/**
	 * �ڽ��� ����� �θ��� �ּҷ� ������ DB�� �־��ִ� method
	 * 
	 * @param name �ּҷϿ� ����� �̸�
	 * @param num �ּҷϿ� ����� ��ȭ��ȣ
	 * @param mail �ּҷϿ� ����� �̸���
	 * @param major �ּҷϿ� ����� ������
	 * @param stid �ּҷϿ� ����� �й�
	 * @param month �ּҷϿ� ����� ����(��)
	 * @param day �ּҷϿ� ����� ����(��)
	 * @param groupname �ּҷϿ� ����� �׷��
	 * @param hash �ּҷϿ� ����� �ؽ��±׸�
	 * @param sex �ּҷϿ� ����� ����
	 * @return �ּҷϿ� ������ ����ϴ� ���� ���� �� ��ȯ
	 */
	public boolean insertAddress(String name,String num,String mail,String major,String stid,String month,String day,String groupname,String hash,String sex) {
		boolean isSuccess = false;
		String sql = "insert into address(name,num,mail,major,stid,month,day,groupname,hash,sex) values(?,?,?,?,?,?,?,?,?,?)";
		try {

			stmt = conn.prepareStatement(sql);
			stmt.setString(1, name);
			stmt.setString(2, num);
			stmt.setString(3, mail);
			stmt.setString(4, major);
			stmt.setString(5, stid);
			stmt.setString(6, month);
			stmt.setString(7, day);
			stmt.setString(8, groupname);
			stmt.setString(9, hash);
			stmt.setString(10, sex);
			stmt.executeUpdate();
			isSuccess = true;

		} catch (SQLException e) {
			System.err.println("Error : Insert Address");
			System.out.println(e.getMessage());
			isSuccess = false;
		}
		return isSuccess;
	}

	/**
	 * �ּҷϿ��� ������ �ּҷ� ������ DB���� �����ִ� method
	 * 
	 * @param num ������ �ּҷ��� rowid
	 * @return ������ ������ �� ��ȯ
	 */
	public boolean deleteAddress(int num){

		boolean isSuccess = true;
		String sql = "DELETE FROM address WHERE rowid = ?";
		try{
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, num);
			stmt.executeUpdate();
		} catch (SQLException e){
			System.out.println("Error : delete address\n");
			System.out.println(e.getMessage());
			isSuccess = false;
		}
		return isSuccess;
	}

	/*public boolean updateaddress(String name,String num,String mail,String major,String stid,String month,String day,String groupname,String hash,String sex,int rowid){
      boolean isSuccess = false;
      String sql = "UPDATE address SET name=?,num=?,mail=?,major=?,stid=?,month=?,day=?,groupname=?,hash=?,sex=?,rowid=? WHERE rowid=?";
      try{
         stmt = conn.prepareStatement(sql);
         stmt.setString(1, name);
         stmt.setString(2, num);
         stmt.setString(3, mail);
         stmt.setString(4, major);
         stmt.setString(5, stid);
         stmt.setString(6, month);
         stmt.setString(7, day);
         stmt.setString(8, groupname);
         stmt.setString(9, hash);
         stmt.setString(10, sex);
         stmt.setInt(11, rowid);
         stmt.executeUpdate();
         isSuccess = true;
      }catch (SQLException e) {
         System.err.println("Error : Update address");
         System.out.println(e.getMessage());
         isSuccess = false;
      }
      return isSuccess;
   }*/

	/**
	 * �ּҷϿ� ��ϵ� �θƵ� �߿��� ������ ���� ���ִ� method
	 * 
	 * @return ������ ���� ��ȯ
	 */
	public int accountMan(){
		int i = 0;
		try {
			stmt = conn.prepareStatement("SELECT rowid,*" + "FROM address WHERE sex = '����'"); // ����������                                                
			rs = stmt.executeQuery();
			while (rs.next()) { // result set�� �� ���� ���
				i++;
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return i;
	}

	/**
	 * �ּҷϿ� ��ϵ� �θƵ� �߿��� ������ ���� ���ִ� method
	 * 
	 * @return ������ ���� ��ȯ
	 */
	public int accountWoman(){
		int i = 0;
		try {
			stmt = conn.prepareStatement("SELECT rowid,*" + "FROM address WHERE sex = '����'"); // ����������                                                
			rs = stmt.executeQuery();
			while (rs.next()) { // result set�� �� ���� ���
				i++;
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return i;
	}

	/**
	 * �׷쿡 ���Ե� ��ü �ּҷ��� �˻����ִ� method 
	 * 
	 */
	public void group(){
		try {
			stmt = conn.prepareStatement("select * from group_name"); // ������ ����
			rs = stmt.executeQuery();

			while (rs.next()) { // result set�� �� ���� ���
				String m=rs.getString("name");
				Data.groupname_vector.addElement(m); 
			}   
		} catch (SQLException e) {
			System.err.println("Error : serch overall group address\n");
			System.out.println(e.getMessage());
		}

	}

	/**
	 * �Է��� �׷��� �ּҷϿ� ����� �׷���� ��ġ�ϸ� �� �ּҷ��� �׷쿡 �־��ִ� method
	 * 
	 * @param groupname ������ �ּҷ��� ���� �׷���� �Է� 
	 * @return �׷�� �ּҷ��� �ִ� ���� ���� �� ��ȯ
	 */
	public boolean insertgroup(String groupname) {
		boolean isSuccess = false;
		String sql = "insert into group_name values(?)";
		try {

			stmt = conn.prepareStatement(sql);
			stmt.setString(1, groupname);
			stmt.executeUpdate();
			isSuccess = true;

		} catch (SQLException e) {
			System.err.println("Error : Insert group\n");
			System.out.println(e.getMessage());
		}
		return isSuccess;
	}

	/**
	 * �׷���� ������� �ּҷ��� �˻����ִ� method
	 * 
	 * @param groupname �˻��� �׷���� �Է�
	 * @return �׷�� �˻��� ������ �� ��ȯ
	 */
	public boolean searchgroup(String groupname){
		boolean isSuccess = false;
		try {
			AddressInfo m;
			Data.address_vector.removeAllElements();
			stmt = conn.prepareStatement("select rowid,* from address WHERE groupname=?"); // ������ ����
			stmt.setString(1, groupname);
			rs = stmt.executeQuery();
			isSuccess = true;

			while (rs.next()) { // result set�� �� ���� ���
				m = new AddressInfo();
				m.name = rs.getString("name");
				m.num = rs.getString("num");
				m.mail = rs.getString("mail");
				m.major = rs.getString("major");
				m.stid = rs.getString("stid");
				m.month = rs.getString("month");
				m.groupname = rs.getString("groupname");
				m.day = rs.getString("day");
				m.hash = rs.getString("hash");
				m.sex = rs.getString("sex");
				m.rowid = rs.getInt("rowid");

				Data.address_vector.addElement(m); 
			}
			isSuccess = true;
		} catch (SQLException e) {
			System.err.println("Error : search groupname\n");
			System.out.println(e.getMessage());
			isSuccess = false;
		}
		return isSuccess;
	}

	/**
	 * DB�� �����ϴ� method
	 * 
	 */
	protected void closeDB() {
		try {
			if (conn != null)
				conn.close();
		} catch (SQLException e) {
			System.err.println("Error : DB Close\n");
		}
	}

	/**
	 * �α��� �� �� DB�� �ִ� id�� password�� �����ִ� method (��ġ�� �� �α��� ����)
	 * 
	 * @param id ������ id�� �Է�
	 * @param pw ������ password�� �Է�
	 * @return �������� �ʴ� id�� password�� �� ��ȯ
	 */
	public int isPWCorrect(String id, String pw){
		int i;
		for(i=0; i<Data.member_vector.size(); i++){
			if(Data.member_vector.elementAt(i).id.equals(id) && Data.member_vector.elementAt(i).pw.equals(pw)){
				return i; // ���̵� ����� ��ġ�ϸ� �α����� ���� �ε��� ��ȯ
			}
		}
		System.out.println(Data.member_vector.size());
		return -1; // �������� ���� ��� -1 ��ȯ
	}

	/**
	 * id�� ã�� �� ���Ǵ� method
	 * 
	 * @param email ȸ������ �� ����� �̸��� �Է� 
	 * @param name ȸ������ �� ����� �̸� �Է�
	 * @return ã�� �ִ� id�� ��ȯ
	 */
	public String findid(String email, String name){
		int i;
		String id="kkm";
		for(i=0; i<Data.address_vector.size(); i++){
			if(Data.address_vector.elementAt(i).mail.equals(email) && Data.address_vector.elementAt(i).name.equals(name)){

			}
		}
		return id;
	}

	/**
	 * ��й�ȣ�� ã�ų� ������ �� ���Ǵ� method
	 * 
	 * @param id ȸ������ �� ����� ID �Է�
	 * @param pwAnswer ȸ������ �� ����� ������ ���� �亯 �Է�
	 * @return id�� ������ ���� �亯�� ��ġ�� �� True�� ��ȯ
	 */
	public static boolean PWCorrect(String id, String pwAnswer) {
		try{
			stmt = conn.prepareStatement("select id from member where id=\'" + id + "\' AND pwAnswer = \'" + pwAnswer + "\'");
			rs = stmt.executeQuery();

			if(rs.next()){ // ������ �����ϴ� ���
				return true;
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return false;
	}
}