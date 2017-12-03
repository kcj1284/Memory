public class LinkedList {
	int count = 0;	// ������ �ִ� ��� ����
	
	String str_names[];
	String str_holis[];
	
	private CalendarData first;

	// ���� ����Ʈ ������
	public LinkedList() {
		first = null;
	}
	
	// ���� ����Ʈ�� ������� Ȯ��
	public boolean isEmpty() {
		return (first == null);
	}

	// ����Ʈ�� �� �κп� ���� ����
	public void insert(String name) {
		CalendarData node = new CalendarData(name);
		node.nextNode = first;
		first = node;
		
	}
	
	public void findNodes() {
		count = 0;			// �ʱ�ȭ
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

	// �̸�(�⵵����)���� ��� ã��
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