public class CalendarData {
	-
	public String name;- -
	public int count = 0; // ���� ���� �ǹ�
	-
	public boolean holi;- -
	public String cal[] = new String[100];- -
	public CalendarData nextNode;- - // ��� ������
	-

	public CalendarData(String name) {
 -		this.name = name;
 -		for(int i = 0 ; i < 100 ; i++){
 -			cal[i] = new String("");
 -		}
 -		count = 0;
 -		holi = false;
 -	}- -

	public void setCal(String data) {
 -		cal[count] = data;
 -		count++;
 -		sort();
 -	}- -

	public void delCal(int idx) {
 -		count--;
 -		for(int i = idx+1 ; i < count+1 ; i++) {
 -			cal[idx++] = cal[i];
 -			if(i == count) {
 -				sort();
 -				break;
 -			}
 -		}
 -	}- -

	public void sort() {	// ����
 -		for(int i = 0 ; i < count-1 ; i++) {
 -			for(int j = i+1 ; j < count ; j++) {
 -				if(cal[i].compareTo(cal[j]) > 0) {
 -					String tmp = new String(cal[i]);
 -					cal[i] = new String(cal[j]);
 -					cal[j] = new String(tmp);
 -				}
 -			}
 -		}
 -	}- -

	public boolean isData() {	// �����Ͱ� ������ false(count�� 0���� ���� ��)
 -		if(count <= 0) return false;
 -		else return true;
 -	}- -

	public int getcnt() {
 -		return count;
 -	}-
}