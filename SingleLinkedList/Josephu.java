package SingleLinkedList;

public class Josephu {
	public static void main(String[] args) {
		CircleLinkedList circleLinkedList = new CircleLinkedList();
		circleLinkedList.addBoy(125);
		circleLinkedList.countBoy(10, 20, 123);
	}

}

class CircleLinkedList {
	boy frist = null;

	public void addBoy(int nums) {
		if (nums < 2) {
			System.out.println("С���������٣��޷������Ϸ");
			return;
		}
		boy cur = null;
		for (int i = 1; i <= nums; i++) {
			boy boy = new boy(i);
			if (i == 1) {
				frist = boy;
				frist.next = frist;
				cur = frist;
			} else {
				cur.next = boy;
				boy.next = frist;
				cur = cur.next;
			}
		}
	}

	public void list() {
		if (frist == null) {
			return;
		}
		boy cur = frist;
		while (true) {
			System.out.println(cur);
			if (cur.next == frist) {
				break;
			}
			cur = cur.next;
		}
	}
	//startNo���ӵڼ����к���ʼ����countNum��ÿ����������nums����Ȧ�����к�
	public void countBoy(int startNo,int countNum,int nums) {
		boy helper=frist;
		while(true) {
			if(helper.next==frist) {
				break;
			}
			helper=helper.next;
		}
		//��ʼ����ǰ�Ƚ�helper��frist�ƶ���Ŀ��λ��
		for(int i=0;i<startNo-1;i++) {
			helper=helper.next;
			frist=frist.next;
		}
		int count=1;
		while(true) {
			if (count>nums) {
				break;
			}
			//��ʼ������first�ƶ�countNum-1��λ�ã�׼����Ȧ���к���,Ȼ������ǰ�ƶ�һ��λ��
			//��ʹhelper����һ��ֱ��ָ��first���൱�ڽ�Ŀ��λ���Ƴ�
			for(int i=0;i<countNum-1;i++) {
				frist=frist.next;
				helper=helper.next;
			}
			System.out.println("��Ȧ�к��ĺ�����"+frist);
			frist=frist.next;
			helper.next=frist;
			count++;
		}
		System.out.println("�������Ȧ�е�С����");
		this.list();
	}
}

class boy {
	public int no;
	public boy next;

	public boy(int no) {
		this.no = no;
	}

	@Override
	public String toString() {
		return "boy [no=" + no + "]";
	}
}
