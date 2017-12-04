package window1;

import java.sql.*;
import java.util.Vector;

/**
 * mysql로 회원 정보를 전송하는 Class
 */
public class DB {
	private static String DB_ID = "memory";
	/*
	 * private static String DB_PASSWORD = "0238"; private static String DB_NAME
	 * = "memory"; private static String DB_IP = "localhost"; private static int
	 * DB_PORT = 3306;
	 * 
	 * private static String DB_Connect = "jdbc:mysql://" + DB_IP + ":" +
	 * DB_PORT + "/" + DB_NAME;
	 */
	static Connection conn;
	static PreparedStatement stmt;
	static ResultSet rs;
	static ResultSet rs2;
	

	public DB() {
		connectDB();
	}

	protected void connectDB() {
		try {
			String url = "jdbc:sqlite:resource/memory.db";
			conn = DriverManager.getConnection(url);

		} catch (SQLException e) {
			System.err.println("Error : DB Connect - Driver Manager");
		}
	}

	
	public static void inputQuery(String s){
	      try{
	         stmt = null;
	         stmt = conn.prepareStatement(s);
	         stmt.executeUpdate(); 
	      }
	      catch(SQLException e){
	         e.printStackTrace();
	      }
	   }
	
	public static boolean getMember() { // 유저 정보 갱신
		boolean isSuccess = false;
		try {
			MemberInfo m;
			Data.member_vector.removeAllElements();
			stmt = conn.prepareStatement("select * from member"); // 쿼리문 전송
			rs = stmt.executeQuery();

			while (rs.next()) { // result set이 더 있을 경우
				m = new MemberInfo();
				m.id = rs.getString("id");
				m.pw = rs.getString("pw");
				m.email = rs.getString("email");
				m.name = rs.getString("name");
				m.pwQuestion = Integer.parseInt(rs.getString("pwQuestion"));
				m.pwAnswer = rs.getString("pwAnswer");

				System.out.println(rs.getString("id") + "\t" + rs.getString("pw") + "\t" + rs.getString("email") + "\t"
						+ rs.getString("name") + "\t" + rs.getString("pwAnswer"));
				Data.member_vector.addElement(m); // 벡터에 유저 정보 추가
			}
			isSuccess = true;
		} catch (SQLException e) {

		}
		return isSuccess;
	}

	public boolean getAddress() { // 유저 정보 갱신
		boolean isSuccess = false;
		try {
			AddressInfo m;
			Data.address_vector.removeAllElements();
			stmt = conn.prepareStatement("select * from address"); // 쿼리문 전송
			rs = stmt.executeQuery();

			while (rs.next()) { // result set이 더 있을 경우
				m = new AddressInfo();
				m.name = rs.getString("name");
				m.num = rs.getString("num");
				m.mail = rs.getString("mail");
				m.major = rs.getString("major");
				m.hash = rs.getString("hash");
				m.stid = rs.getString("stid");
				m.sex = rs.getString("sex");
				m.month = rs.getString("month");
				m.day = rs.getString("day");
				m.groupname = rs.getString("groupname");

				System.out.println(rs.getString("id") + "\t" + rs.getString("pw") + "\t" + rs.getString("email") + "\t"
						+ rs.getString("name") + "\t" + rs.getString("pwAnswer"));
				Data.address_vector.addElement(m); // 벡터에 유저 정보 추가
			}
			isSuccess = true;
		} catch (SQLException e) {

		}
		return isSuccess;
	}
	
	
	
	protected boolean insertMember(String id, String passwd, String email, String name, int question, String answer) {
		boolean isSuccess = false;
		String sql = "insert into member(id, pw, email, name, pwQuestion, pwAnswer) values(?, ?, ?, ?, ?, ?)";
		try {
			/*String sql = "insert into member values(\'" + id + "\', \'" + passwd + "\', \'" + email + "\', \'" + name
					+ "\', " + question + ", \'" + answer + "\');";*/
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, id);
			stmt.setString(2, passwd);
			stmt.setString(3, email);
			stmt.setString(4, name);
			stmt.setInt(5, question );
			stmt.setString(6, answer);
			stmt.executeUpdate();
			Data.member_vector.addElement(new MemberInfo(id,passwd,email,name,question,answer));
			isSuccess = true;
			/*isSuccess = stmt.execute(sql);*/
		} catch (SQLException e) {
			System.err.println("Error : Insert Member");
		}
		return isSuccess;
	}

	
	public boolean searchaddress(){
		boolean isSuccess = false;
		try {
			AddressInfo m;
			Data.address_vector.removeAllElements();
			stmt = conn.prepareStatement("select rowid,* from address"); // 쿼리문 전송
			rs = stmt.executeQuery();

			while (rs.next()) { // result set이 더 있을 경우
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



				System.out.println(rs.getString("name") + "\t" + rs.getString("phone") + "\t" + rs.getString("email") + "\t"
						+ rs.getString("major") + "\t" + rs.getInt("code") + rs.getString("birthday") + "\t" + rs.getString("groupname") + "\t"
						+rs.getString("snsAddress") + "\t" + rs.getString("hash") + "\t" +rs.getString("gender") );
				Data.address_vector.addElement(m); // 벡터에 유저 정보 추가
			}
			isSuccess = true;
		} catch (SQLException e) {
			System.err.println("Error : Can't serch\n");
			System.out.println(e.getMessage());
			isSuccess = false;
		}
		return isSuccess;
	}

	public boolean searchname(String name){
		boolean isSuccess = false;
		try {
			AddressInfo m;
			Data.address_vector.removeAllElements();
			stmt = conn.prepareStatement("select rowid,* from address WHERE name like ?"); // 쿼리문 전송
			stmt.setString(1, '%'+name+'%');

			rs = stmt.executeQuery();

			while (rs.next()) { // result set이 더 있을 경우
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


				System.out.println(rs.getString("name") + "\t" + rs.getString("phone") + "\t" + rs.getString("email") + "\t"
						+ rs.getString("major") + "\t" + rs.getInt("code") + rs.getString("birthday") + "\t" + rs.getString("groupname") + "\t"
						+rs.getString("snsAddress") + "\t" + rs.getString("hash") + "\t" +rs.getString("gender") );
				Data.address_vector.addElement(m); // 벡터에 유저 정보 추가
			}
			isSuccess = true;
		} catch (SQLException e) {
			System.err.println("Error : Can't name\n");
			System.out.println(e.getMessage());
			isSuccess = false;
		}
		return isSuccess;
	}

	public boolean searchcode(int code){
		boolean isSuccess = false;
		try {
			AddressInfo m;
			Data.address_vector.removeAllElements();
			stmt = conn.prepareStatement("select rowid,* from address WHERE code like ?"); // 쿼리문 전송
			stmt.setInt(1, '%'+code+'%');

			rs = stmt.executeQuery();

			while (rs.next()) { // result set이 더 있을 경우
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



				System.out.println(rs.getString("name") + "\t" + rs.getString("phone") + "\t" + rs.getString("email") + "\t"
						+ rs.getString("major") + "\t" + rs.getInt("code") + rs.getString("birthday") + "\t" + rs.getString("groupname") + "\t"
						+rs.getString("snsAddress") + "\t" + rs.getString("hash") + "\t" +rs.getString("gender") );
				Data.address_vector.addElement(m); // 벡터에 유저 정보 추가
			}
			isSuccess = true;
		} catch (SQLException e) {
			System.err.println("Error : Can't code\n");
			System.out.println(e.getMessage());
			isSuccess = false;
		}
		return isSuccess;
	}

	public boolean searchmajor(String major){
		boolean isSuccess = false;
		try {
			AddressInfo m;
			Data.address_vector.removeAllElements();
			stmt = conn.prepareStatement("select rowid,* from address WHERE major like ?"); // 쿼리문 전송
			stmt.setString(1, '%'+major+'%');

			rs = stmt.executeQuery();

			while (rs.next()) { // result set이 더 있을 경우
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



				System.out.println(rs.getString("name") + "\t" + rs.getString("phone") + "\t" + rs.getString("email") + "\t"
						+ rs.getString("major") + "\t" + rs.getInt("code") + rs.getString("birthday") + "\t" + rs.getString("groupname") + "\t"
						+rs.getString("snsAddress") + "\t" + rs.getString("hash") + "\t" +rs.getString("gender") );
				Data.address_vector.addElement(m); // 벡터에 유저 정보 추가
			}
			isSuccess = true;
		} catch (SQLException e) {
			System.err.println("Error : Can't major\n");
			System.out.println(e.getMessage());
			isSuccess = false;

		}
		return isSuccess;
	}

	public boolean searchphone(String phone){
		boolean isSuccess = false;
		try {
			AddressInfo m;
			Data.address_vector.removeAllElements();
			stmt = conn.prepareStatement("select rowid,* from address WHERE phone like ?"); // 쿼리문 전송
			stmt.setString(1, '%'+phone+'%');

			rs = stmt.executeQuery();

			while (rs.next()) { // result set이 더 있을 경우
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



				System.out.println(rs.getString("name") + "\t" + rs.getString("phone") + "\t" + rs.getString("email") + "\t"
						+ rs.getString("major") + "\t" + rs.getInt("code") + rs.getString("birthday") + "\t" + rs.getString("groupname") + "\t"
						+rs.getString("snsAddress") + "\t" + rs.getString("hash") + "\t" +rs.getString("gender") );
				Data.address_vector.addElement(m); // 벡터에 유저 정보 추가
			}
			isSuccess = true;
		} catch (SQLException e) {
			System.err.println("Error : Can't phone\n");
			System.out.println(e.getMessage());
			isSuccess = false;
		}
		return isSuccess;
	}

	public boolean searchhash(String hash){
		boolean isSuccess = false;
		try {
			AddressInfo m;
			Data.address_vector.removeAllElements();
			stmt = conn.prepareStatement("select rowid,* from address WHERE hash like ?"); // 쿼리문 전송
			stmt.setString(1, '%'+hash+'%');

			rs = stmt.executeQuery();

			while (rs.next()) { // result set이 더 있을 경우
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



				System.out.println(rs.getString("name") + "\t" + rs.getString("phone") + "\t" + rs.getString("email") + "\t"
						+ rs.getString("major") + "\t" + rs.getInt("code") + rs.getString("birthday") + "\t" + rs.getString("groupname") + "\t"
						+rs.getString("snsAddress") + "\t" + rs.getString("hash") + "\t" +rs.getString("gender") );
				Data.address_vector.addElement(m); // 벡터에 유저 정보 추가
			}
			isSuccess = true;
		} catch (SQLException e) {
			System.err.println("Error : Can't hash\n");
			System.out.println(e.getMessage());
			isSuccess = false;
		}
		return isSuccess;
	}

	public boolean insertAddress(String name,String num,String mail,String major,String stid,String month,String day,String groupname,String hash,String sex) {
		boolean isSuccess = false;
		String sql = "insert into address(name,num,mail,major,stid,month,day,group,hash,sex) values(?,?,?,?,?,?,?,?,?,?)";
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
			stmt.executeUpdate(sql);
			isSuccess = true;
			/*	  MemberInfo m;
					Data.member_vector.removeAllElements();*/

			/*stmt = conn.prepareStatement("insert into address(name) values('kangmin')"); 
		         String sql = "insert into address values(\'" + "kangmin" + "\', \'" + phone + "\', \'" + email + "\', \'" + major + "\', "
		               + code + ", \'" + birthday + "\', \'" + groupname + "\', \'" + snsAddress + "\', \'" + hash + "\', \'" + gender + "\');";*/


		} catch (SQLException e) {
			System.err.println("Error : Insert Member");
		}
		return isSuccess;
	}

	public boolean deleteAddress(String phone){

		boolean isSuccess = true;
		String sql = "DELETE FROM address WHERE phone = ?";
		try{
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, phone);
			System.out.println(sql);

			stmt.executeUpdate();
		} catch (SQLException e){
			System.out.println("Error : Can't delete\n");
			System.out.println(e.getMessage());
			isSuccess = false;
		}
		return isSuccess;
	}

	public boolean updateaddress(String name,String phone,String email,String major,int code,String birthday,String groupname,String snsAddress,String hash,String gender,int rowid){
		boolean isSuccess = false;
		String sql = "UPDATE address SET name=?,phone=?,email=?,major=?,code=?,birthday=?,groupname=?,snsAddress=?,hash=?,gender=?,rowid=? WHERE rowid=?";
		try{
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, name);
			stmt.setString(2, phone);
			stmt.setString(3, email);
			stmt.setString(4, major);
			stmt.setInt(5, code);
			stmt.setString(6, birthday);
			stmt.setString(7, groupname);
			stmt.setString(8, snsAddress);
			stmt.setString(9, hash);
			stmt.setString(10, gender);
			stmt.setInt(11, rowid);
			stmt.executeUpdate(sql);
			isSuccess = true;
		}catch (SQLException e) {
			System.err.println("Error : Update address");
			System.out.println(e.getMessage());
			isSuccess = false;
		}
		return isSuccess;
	}

	public int accountgender(){
		int gender=0;
		try {
			Statement stmt = conn.createStatement();// 쿼리문 전송
			gender=(int)(stmt.executeUpdate("select count(*) from address WHERE gender=남")/stmt.executeUpdate("select count(*) from address")*100);

			System.out.println(gender);

		} catch (SQLException e) {
			System.err.println("Error : Don't account gender");
			System.out.println(e.getMessage());
		}
		return gender;
	}



	public Vector<String> group(){
		try {
			stmt = conn.prepareStatement("select * from groupname"); // 쿼리문 전송
			rs = stmt.executeQuery();

			while (rs.next()) { // result set이 더 있을 경우
				String m=rs.getString("groupname");
				System.out.println(rs.getString("groupname"));
				Data.groupname_vector.addElement(m); // 벡터에 유저 정보 추가
			}	
		} catch (SQLException e) {
			System.err.println("Error : Can't serch\n");
			System.out.println(e.getMessage());
		}
		return null;

	}

	public boolean insertgroup(String groupname) {
		boolean isSuccess = false;
		String sql = "insert into groupname values(?)";
		try {

			stmt = conn.prepareStatement(sql);
			stmt.setString(1, groupname);
			stmt.executeUpdate(sql);
			isSuccess = true;

		} catch (SQLException e) {
			System.err.println("Error : Insert Member");
		}
		return isSuccess;
	}

	public boolean searchgroup(){
		boolean isSuccess = false;
		try {
			AddressInfo m;
			Data.address_vector.removeAllElements();
			stmt = conn.prepareStatement("select rowid,* from address WHERE groupname=?"); // 쿼리문 전송
			rs = stmt.executeQuery();

			while (rs.next()) { // result set이 더 있을 경우
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


				System.out.println(rs.getString("name") + "\t" + rs.getString("phone") + "\t" + rs.getString("email") + "\t"
						+ rs.getString("major") + "\t" + rs.getInt("code") + rs.getString("birthday") + "\t" + rs.getString("groupname") + "\t"
						+rs.getString("snsAddress") + "\t" + rs.getString("hash") + "\t" +rs.getString("gender") );
				Data.address_vector.addElement(m); // 벡터에 유저 정보 추가
			}
			isSuccess = true;
		} catch (SQLException e) {
			System.err.println("Error : Can't serch groupname\n");
			System.out.println(e.getMessage());
			isSuccess = false;
		}
		return isSuccess;
	}

	protected void closeDB() {
		try {
			if (conn != null)
				conn.close();
		} catch (SQLException e) {
			System.err.println("Error : DB Close");
		}
	}


	public int isPWCorrect(String id, String pw){
		int i;
		for(i=0; i<Data.member_vector.size(); i++){
			if(Data.member_vector.elementAt(i).id.equals(id) && Data.member_vector.elementAt(i).pw.equals(pw)){
				return i; // 아이디 비번이 일치하면 로그인한 유저 인덱스 반환
			}
		}
		System.out.println(Data.member_vector.size());
		return -1; // 존재하지 않을 경우 -1 반환
	}

	public String findid(String email, String name){
		int i;
		String id="kkm";
		for(i=0; i<Data.address_vector.size(); i++){
			if(Data.address_vector.elementAt(i).mail.equals(email) && Data.address_vector.elementAt(i).name.equals(name)){

			}
		}
		return id;
	}

	public static boolean PWCorrect(String id, String pwAnswer) {
		try{
			stmt = conn.prepareStatement("select id from member where id=\'" + id + "\' AND pwAnswer = \'" + pwAnswer + "\'");
			rs = stmt.executeQuery();

			if(rs.next()){ // 계정이 존재하는 경우
				return true;
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return false;
	}
}