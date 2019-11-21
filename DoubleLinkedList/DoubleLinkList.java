package DoubleLinkedList;

public class DoubleLinkList {
	public static void main(String[] args) {
		HeroNote note = new HeroNote(1, "�ϴ�");
		HeroNote note2 = new HeroNote(2, "�϶�");
		HeroNote note3 = new HeroNote(3, "����");
		HeroNote note4 = new HeroNote(4, "����");
		HeroNote note5 = new HeroNote(5, "����");
		DoublekList doublekList = new DoublekList();
		doublekList.paixu(note5);
		doublekList.paixu(note);
		doublekList.paixu(note2);
		doublekList.paixu(note4);
		doublekList.paixu(note3);
		doublekList.list(doublekList.getHead());
		System.out.println("-----------------------------");

	}

}

class DoublekList {
	HeroNote head = new HeroNote(0, "");

	public HeroNote getHead() {
		return head;
	}

	public void add(HeroNote note) {
		HeroNote temp = head;
		while (true) {
			if (temp.next == null) {
				break;
			}
			temp = temp.next;
		}
		temp.next = note;
		note.pre = temp;
	}

	public void paixu(HeroNote note) {
		HeroNote temp = head;
		while (true) {
			//���ж�temp����һ���Ƿ�Ϊ�գ���ӵ�һ�����������һ������ʱ������ָ�룩
			if (temp.next == null) {
				temp.next = note;
				note.pre = temp;
				return;
			}
			if (temp.next.no == note.no) {
				System.out.println("�����ظ�");
				return;
			}
			if (temp.next.no > note.no) {
				break;
			}
			temp = temp.next;
		}
		note.next = temp.next;
		temp.next = note;
		note.next.pre = note;
		note.pre = temp;
	}

	// ��������
	public void list(HeroNote head) {
		HeroNote temp = head;
		if (head.next == null) {
			System.out.println("����Ϊ��");
			return;
		}
		while (true) {
			if (temp.next == null) {
				break;
			}
			System.out.println(temp.next);
			temp = temp.next;
		}
	}

	public void findlist(int no) {
		HeroNote temp = head;
		while (true) {
			if (temp.no == no) {
				break;
			}
			if (temp.next == null) {
				System.out.println("���޴���");
				return;
			}
			temp = temp.next;
		}
		System.out.println(temp);
	}

	public void drop(HeroNote note) {
		HeroNote temp = head;
		while (true) {
			if (temp.no == note.no) {
				break;
			}
			if (temp.next == null) {
				System.out.println("���޴���");
				return;
			}
			temp = temp.next;
		}
		temp.pre.next = temp.next;
		// ���ǰ�ж���ӵĽ���Ƿ�Ϊ���һ����㣬�������ֿ�ָ���쳣
		if (temp.next != null)
			;
		temp.next.pre = temp.pre;
	}
}

class HeroNote {
	public int no;
	public String name;
	public HeroNote next;
	public HeroNote pre;

	public HeroNote(int no, String name) {
		super();
		this.no = no;
		this.name = name;
	}

	@Override
	public String toString() {
		return "HeroNote [no=" + no + ", name=" + name + "]";
	}

}
