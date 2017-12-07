package window1;

import java.sql.*;
import java.util.Vector;

/**
 * SQLite로 DB를 관리하는 대표 Class
 * 
 * @author 김강민
 *
 */
public class DB {
	static Connection conn;
	static PreparedStatement stmt;
	static ResultSet rs;
	static ResultSet rs2;

	/**
	 * DB를 연결해주는 method를 호출해주는 method
	 */
	public DB() {
		connectDB();
	}

	/**
	 * DB를 연결해주는 method
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
	 * @param s 실행할 쿼리문을 문자열로 받아주는 부분
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
	 * 회원가입 정보를 벡터에 추가시켜주는 method
	 * 
	 * @return MemberInfo 벡터에 정보 갱신(추가)을 성공하였을 때 반환
	 */
	public static boolean getMember() { // 유저의 로그인 정보 갱신
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

				Data.member_vector.addElement(m); // 벡터에 유저 정보 추가
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
	 * 주소록 정보를 벡터에 추가시켜주는 method
	 * 
	 * @return AddressInfo 벡터에 정보 갱신(추가)을 성공하였을 때 반환
	 */
	public boolean getAddress() { // 주소록 정보 갱신
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
				m.stid = rs.getString("stid");
				m.month = rs.getString("month");
				m.day = rs.getString("day");
				m.groupname = rs.getString("groupname");
				m.hash = rs.getString("hash");
				m.sex = rs.getString("sex");

				Data.address_vector.addElement(m); // 벡터에 주소록 정보 추가
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
	 * 회원가입 정보를 DB에 넣어주는 method
	 * 
	 * @param id 회원가입 때 등록한 아이디
	 * @param passwd 회원가입 때 등록한 패스워드
	 * @param email 회원가입 때 등록한 이메일
	 * @param name 회원가입 때 등록한 이름
	 * @param question 회원가입 때 등록한 비밀번호 찾기 할 때의 질문 
	 * @param answer 회원가입 때 등록한 비밀번호 찾기 할 때 질문에 대한 답
	 * @return DB에 회원가입 정보를 성공적으로 insert할 시 반환
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

			Data.member_vector.addElement(new MemberInfo(id,passwd,email,name,question,answer)); // 벡터에 유저 정보 추가
			isSuccess = true;
		} catch (SQLException e) {
			System.err.println("Error : Insert Member");
			System.out.println(e.getMessage());
			isSuccess = false;
		}
		return isSuccess;
	}

	/**
	 * 자신이 등록한 주소록 전체를 검색해주는 method
	 * 
	 * @return 전체 검색을 성공할 시 반환
	 */
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
	 * 주소록에서 이름을 기준으로 검색해주는 method
	 * 
	 * @param name 검색 기준으로 반영할 이름
	 * @return 검색을 성공 시 반환
	 */
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
	 * 주소록에서 학번을 기준으로 검색해주는 method
	 * 
	 * @param stid 검색 기준으로 반영할 학번
	 * @return 검색을 성공 시 반환
	 */
	public boolean searchcode(String stid){
		boolean isSuccess = false;
		try {
			AddressInfo m;
			Data.address_vector.removeAllElements();
			stmt = conn.prepareStatement("select rowid,* from address WHERE stid like ?"); // 쿼리문 전송
			stmt.setString(1, '%'+stid+'%');
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
	 * 주소록에서 전공을 기준으로 검색해주는 method
	 * 
	 * @param major 검색 기준으로 반영할 전공명
	 * @return 검색을 성공 시 반환
	 */
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
	 * 주소록에서 번호를 기준으로 검색해주는 method
	 * 
	 * @param num 검색 기준으로 반영할 번호
	 * @return 검색을 성공 시 반환
	 */
	public boolean searchphone(String num){
		boolean isSuccess = false;
		try {
			AddressInfo m;
			Data.address_vector.removeAllElements();
			stmt = conn.prepareStatement("select rowid,* from address WHERE num like ?"); // 쿼리문 전송
			stmt.setString(1, '%'+num+'%');

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
	 * 주소록에서 해시태그(키워드)를 기준으로 검색해주는 method
	 * 
	 * @param hash 검색 기준으로 반영할 해시명
	 * @return 검색을 성공 시 반환
	 */
	public boolean searchHash(String hash){
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
	 * 자신이 등록한 인맥의 주소록 정보를 DB에 넣어주는 method
	 * 
	 * @param name 주소록에 등록할 이름
	 * @param num 주소록에 등록할 전화번호
	 * @param mail 주소록에 등록할 이메일
	 * @param major 주소록에 등록할 전공명
	 * @param stid 주소록에 등록할 학번
	 * @param month 주소록에 등록할 생일(월)
	 * @param day 주소록에 등록할 생일(일)
	 * @param groupname 주소록에 등록할 그룹명
	 * @param hash 주소록에 등록할 해시태그명
	 * @param sex 주소록에 등록할 성별
	 * @return 주소록에 정보를 등록하는 것을 성공 시 반환
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
	 * 주소록에서 선택한 주소록 정보를 DB에서 지워주는 method
	 * 
	 * @param num 삭제할 주소록의 rowid
	 * @return 삭제를 성공할 시 반환
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
	 * 주소록에 등록된 인맥들 중에서 남성의 수를 세주는 method
	 * 
	 * @return 남성의 수를 반환
	 */
	public int accountMan(){
		int i = 0;
		try {
			stmt = conn.prepareStatement("SELECT rowid,*" + "FROM address WHERE sex = '남자'"); // 쿼리문전송                                                
			rs = stmt.executeQuery();
			while (rs.next()) { // result set이 더 있을 경우
				i++;
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return i;
	}

	/**
	 * 주소록에 등록된 인맥들 중에서 여성의 수를 세주는 method
	 * 
	 * @return 여성의 수를 반환
	 */
	public int accountWoman(){
		int i = 0;
		try {
			stmt = conn.prepareStatement("SELECT rowid,*" + "FROM address WHERE sex = '여자'"); // 쿼리문전송                                                
			rs = stmt.executeQuery();
			while (rs.next()) { // result set이 더 있을 경우
				i++;
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return i;
	}

	/**
	 * 그룹에 포함된 전체 주소록을 검색해주는 method 
	 * 
	 */
	public void group(){
		try {
			stmt = conn.prepareStatement("select * from group_name"); // 쿼리문 전송
			rs = stmt.executeQuery();

			while (rs.next()) { // result set이 더 있을 경우
				String m=rs.getString("name");
				Data.groupname_vector.addElement(m); 
			}   
		} catch (SQLException e) {
			System.err.println("Error : serch overall group address\n");
			System.out.println(e.getMessage());
		}

	}

	/**
	 * 입력한 그룹명과 주소록에 등록한 그룹명이 일치하면 그 주소록을 그룹에 넣어주는 method
	 * 
	 * @param groupname 선택한 주소록을 넣을 그룹명을 입력 
	 * @return 그룹명에 주소록을 넣는 것을 성공 시 반환
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
	 * 그룹명을 기반으로 주소록을 검색해주는 method
	 * 
	 * @param groupname 검색할 그룹명을 입력
	 * @return 그룹명 검색을 성공할 시 반환
	 */
	public boolean searchgroup(String groupname){
		boolean isSuccess = false;
		try {
			AddressInfo m;
			Data.address_vector.removeAllElements();
			stmt = conn.prepareStatement("select rowid,* from address WHERE groupname=?"); // 쿼리문 전송
			stmt.setString(1, groupname);
			rs = stmt.executeQuery();
			isSuccess = true;

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
	 * DB를 종료하는 method
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
	 * 로그인 할 때 DB에 있는 id와 password를 비교해주는 method (일치할 시 로그인 성공)
	 * 
	 * @param id 유저의 id를 입력
	 * @param pw 유저의 password를 입력
	 * @return 존재하지 않는 id와 password일 시 반환
	 */
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

	/**
	 * id를 찾을 때 사용되는 method
	 * 
	 * @param email 회원가입 시 등록한 이메일 입력 
	 * @param name 회원가입 시 등록한 이름 입력
	 * @return 찾고 있는 id를 반환
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
	 * 비밀번호를 찾거나 변경할 때 사용되는 method
	 * 
	 * @param id 회원가입 시 등록한 ID 입력
	 * @param pwAnswer 회원가입 시 등록한 질문에 대한 답변 입력
	 * @return id와 질문에 대한 답변이 일치할 시 True를 반환
	 */
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