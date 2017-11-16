package window1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 * mysql�� ȸ�� ������ �����ϴ� Class
 */ 
public class DB {
   private static String DB_ID = "memory";
   private static String DB_PASSWORD = "0238";
   private static String DB_NAME = "memory";
   private static String DB_IP = "localhost";
   private static int DB_PORT = 3306;

   private static String DB_Connect = "jdbc:mysql://" + DB_IP + ":" + DB_PORT + "/" + DB_NAME;

   Connection conn;
   PreparedStatement stmt;
   ResultSet rs;

   public DB() {
      connectDB();
   }

   protected void connectDB() {
      try {
         Class.forName("com.mysql.jdbc.Driver");
         conn = DriverManager.getConnection(DB_Connect, DB_ID, DB_PASSWORD);
      } catch (ClassNotFoundException e) {
         System.err.println("Error : DB Connect - Class Not Found");
      } catch (SQLException e) {
         System.err.println("Error : DB Connect - Driver Manager");
      }
   }

   public boolean getMember(){  // ���� ���� ����
      boolean isSuccess = false;
      try{
         MemberInfo m;
         Data.member_vector.removeAllElements();

         stmt = conn.prepareStatement("select * from member"); // ������ ����
         rs = stmt.executeQuery();

         while(rs.next()){ // result set�� �� ���� ���
            m = new MemberInfo();
            m.id = rs.getString("id");
            m.pw = rs.getString("pw");
            m.email = rs.getString("email");
            m.name = rs.getString("name");
            m.pwQuestion= Integer.parseInt(rs.getString("pwQuestion"));
            m.pwAnswer = rs.getString("pwAnswer");

            Data.member_vector.addElement(m); // ���Ϳ� ���� ���� �߰�
         }
         isSuccess = true;
      } catch(SQLException e){

      }
      return isSuccess;
   }

   protected boolean insertMember(String id, String passwd, String email, String name, int question, String answer) {
      boolean isSuccess = false;
      try {
         String sql = "insert into member values(\'" + id + "\', \'" + passwd + "\', \'" + email + "\', \'" + name + "\', "
               + question + ", \'" + answer + "\');";

         isSuccess = stmt.execute(sql);
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
			   return i; // ���̵� ����� ��ġ�ϸ� �α����� ���� �ε��� ��ȯ
		   }
	   }
	   System.out.println(Data.member_vector.size());
	   return -1; // �������� ���� ��� -1 ��ȯ
   }
}