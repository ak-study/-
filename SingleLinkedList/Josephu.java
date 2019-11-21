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
			System.out.println("小孩数量过少，无法完成游戏");
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
	//startNo：从第几个男孩开始数，countNum：每次数几个，nums：出圈几个男孩
	public void countBoy(int startNo,int countNum,int nums) {
		boy helper=frist;
		while(true) {
			if(helper.next==frist) {
				break;
			}
			helper=helper.next;
		}
		//开始数数前先将helper和frist移动到目标位置
		for(int i=0;i<startNo-1;i++) {
			helper=helper.next;
			frist=frist.next;
		}
		int count=1;
		while(true) {
			if (count>nums) {
				break;
			}
			//开始数数，first移动countNum-1的位置（准备出圈的男孩）,然后再往前移动一个位置
			//能使helper的下一个直接指向first，相当于将目标位置移除
			for(int i=0;i<countNum-1;i++) {
				frist=frist.next;
				helper=helper.next;
			}
			System.out.println("出圈男孩的号数："+frist);
			frist=frist.next;
			helper.next=frist;
			count++;
		}
		System.out.println("最后留在圈中的小孩：");
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
