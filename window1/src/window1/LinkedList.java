public class LinkedList {
	int count = 0;	// 일정이 있는 노드 갯수
	
	String str_names[];
	String str_holis[];
	
	private CalendarData first;

	// 연결 리스트 생성자
	public LinkedList() {
		first = null;
	}
	
	// 현재 리스트가 비었는지 확인
	public boolean isEmpty() {
		return (first == null);
	}

	// 리스트의 앞 부분에 원소 삽입
	public void insert(String name) {
		CalendarData node = new CalendarData(name);
		node.nextNode = first;
		first = node;
		
	}
	
	public void findNodes() {
		count = 0;			// 초기화
		CalendarData current = first;
		while (current != null) {
			if(current.count != 0) count++;
			current = current.nextNode;
		}
	}
	
	public String [] holis(int cnt, int year) {
		int idx= 0;
		str_holis = new String[cnt];
		CalendarData temp = first;
		return str_holis;
	}
	
	public String [] names() {
		findNodes();
		int idx= 0;
		str_names = new String[count];
		CalendarData temp = first;
		while(temp != null){
			if(temp.isData())
				str_names[idx++] = temp.name + "   [" + temp.count + "]";
			temp = temp.nextNode;
		}
		return str_names;
	}

	// 이름(년도월일)으로 노드 찾기
	public CalendarData search(String name) {
		CalendarData current = first;
		while (current != null) {
			if(current.name.equals(name)) return current;
			current = current.nextNode;
		}
		return null;
	}

	public boolean isHoli(String name) {
		CalendarData current = first;
		while (current != null) {
			if(current.name.equals(name)) {
				if(current.holi)
					return true;
			}
			current = current.nextNode;
		}
		return false;
	}
}