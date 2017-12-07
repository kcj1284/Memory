package window1;

import java.util.Vector;
/**
 * @author 김강민
 * 
 * DB에 있는 정보들을 벡터로 저장하는 Class
 */ 
public class Data {
	public static int userIndex = -1;
	public static Vector<MemberInfo> member_vector = new Vector<MemberInfo>();
	public static Vector<AddressInfo> address_vector = new Vector<AddressInfo>();
	public static Vector<String> groupname_vector = new Vector<String>(); 
}