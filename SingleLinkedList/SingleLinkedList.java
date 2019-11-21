package SingleLinkedList;


public class SingleLinkedList {
	public static void main(String[] args) {
		HeroNote note = new HeroNote(1, "老大");
		HeroNote note2 = new HeroNote(2, "老二");
		HeroNote note4 = new HeroNote(4, "老四");
		HeroNote note3 = new HeroNote(3, "老三");
		singlelist singlelist = new singlelist();
		singlelist.paixu(note);
		singlelist.paixu(note2);
		singlelist.paixu(note4);
		singlelist.paixu(note3);
		// singlelist.findlist(4);
		// singlelist.drop(note3);
		singlelist.list(singlelist.getHead());
		System.out.println("---------------------------");
		// System.out.println(singlelist.getlength(singlelist.getHead()));
		// System.out.println(singlelist.findlastnote(singlelist.getHead(), 5));
		HeroNote n=singlelist.fanzhuan(singlelist.getHead());
		singlelist.list(n);
	}

}

class singlelist {
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
	}

	// 遍历链表
	public void list(HeroNote head) {
		HeroNote temp = head;
		if (head.next == null) {
			System.out.println("链表为空");
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

	public void paixu(HeroNote note) {
		HeroNote temp = head;
		while (true) {
			if (temp.no == note.no) {
				System.out.println("排名重复!");
				return;
			}
			//说明已经到达最后一个结点
			if (temp.next == null) {
				break;
			}
			if (temp.next.no > note.no) {
				break;
			}
			temp = temp.next;
		}
		note.next = temp.next;
		temp.next = note;
	}

	public void findlist(int no) {
		HeroNote temp = head;
		while (true) {
			if (temp.no == no) {
				break;
			}
			if (temp.next == null) {
				System.out.println("查无此人");
				return;
			}
			temp = temp.next;
		}
		System.out.println(temp);
	}

	public void drop(HeroNote note) {
		HeroNote temp = head;
		while (true) {
			if (temp.next.no == note.no) {
				break;
			}
			if (temp.next == null) {
				System.out.println("查无此人");
				return;
			}
			temp = temp.next;
		}
		temp.next = note.next;
	}

	public int getlength(HeroNote headd) {
		HeroNote temp = head;
		int length = 0;
		if (temp.next == null) {
			System.out.println("空链表");
			return 0;
		}
		while (true) {
			if (temp.next == null)
				break;
			if (temp.next != null) {
				length++;
				temp = temp.next;
			}
		}
		return length;
	}

	//习题：查找倒数第index个结点
	public HeroNote findlastnote(HeroNote headd, int index) {
		HeroNote temp = head.next;
		int length = this.getlength(headd);
		int id = length - index;// 正数遍历的次数
		if (id <= 0)
			return null;
		if (temp.next == null) {
			return null;
		}
		for (int i = 0; i < id; i++) {
			temp = temp.next;
		}
		return temp;
	}

	//习题：翻转单链表
	public HeroNote fanzhuan(HeroNote headd) {
		//如果该链表的结点小于等于两个，则无需翻转
		if(headd.next==null||headd.next.next==null) {
			return null;
		}
		HeroNote cur = headd.next;
		HeroNote newhead = new HeroNote(0, "");
		HeroNote next=null;
		while(cur!=null) {
			    next=cur.next;
			    cur.next=newhead.next;
			    newhead.next=cur;
			    cur=next;
		}
		headd.next=newhead.next;
		return newhead;
	}
}

class HeroNote {
	public int no;
	public String name;
	public HeroNote next;

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
