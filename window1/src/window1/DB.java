package window1;

import java.sql.*;

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
	Connection conn;
	PreparedStatement stmt;
	ResultSet rs;
 
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

	public boolean getMember() { // 유저 정보 갱신
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

				System.out.println(rs.getInt("id") + "\t" + rs.getString("pw") + "\t" + rs.getString("email") + "\t"
						+ rs.getString("name") + "\t" +

						rs.getString("pwAnswer"));
				Data.member_vector.addElement(m); // 벡터에 유저 정보 추가
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
			isSuccess = true;
			/*isSuccess = stmt.execute(sql);*/
		} catch (SQLException e) {
			System.err.println("Error : Insert Member");
		}
		return isSuccess;
	}
	
	public boolean searchaddress(String inputText){
		boolean isSuccess = false;
		try {
			AddressInfo m;
			Data.address_vector.removeAllElements();
			stmt = conn.prepareStatement("select * from address"); // 쿼리문 전송
			rs = stmt.executeQuery();

			while (rs.next()) { // result set이 더 있을 경우
				m = new AddressInfo();
				m.name = rs.getString("name");
				m.phone = rs.getString("phone");
				m.email = rs.getString("email");
				m.major = rs.getString("major");
				m.code = Integer.parseInt(rs.getString("code"));
				m.birthday = rs.getString("birthday");
				m.groupname = rs.getString("groupname");
				m.snsAddress = rs.getString("snsAddress");
				m.hash = rs.getString("hash");
				m.gender = rs.getString("gender");
				

				System.out.println(rs.getString("name") + "\t" + rs.getString("phone") + "\t" + rs.getString("email") + "\t"
						+ rs.getString("major") + "\t" + rs.getInt("code") + rs.getString("birthday") + "\t" + rs.getString("groupname") + "\t"
						+rs.getString("snsAddress") + "\t" + rs.getString("hash") + "\t" +rs.getString("gender") );
				Data.address_vector.addElement(m); // 벡터에 유저 정보 추가
			}
			isSuccess = true;
		} catch (SQLException e) {

		}
		return isSuccess;
	}

	public boolean insertaddress(String name,String phone,String email,String major,int code,String birthday,String groupname,String snsAddress,String hash,String gender) {
		boolean isSuccess = false;
		String sql = "insert into address(name,phone,email,major,code,birthday,groupname,snsAddress,hash,gender) values(?,?,?,?,?,?,?,?,?,?)";
		try {

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
	
	/*public int findid(String email, String name){
		int i;
		for(i=0; i<Data.member_vector.size(); i++){
			if(Data.member_vector.elementAt(i).email.equals(email) && Data.member_vector.elementAt(i).name.equals(name)){
				return i;
			}
		}
	}*/


}