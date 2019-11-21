package HashTable;

import java.util.Scanner;

public class HashTableDemo {

	public static void main(String[] args) {
		EmpLinkedListArrays empLinkedListArrays = new EmpLinkedListArrays(7);
		Scanner scanner = new Scanner(System.in);
		int key;
		while (true) {
			System.out.println("请输入序号：");
			System.out.println("1.添加雇员");
			System.out.println("2.显示雇员信息");
			System.out.println("3.通过ID查找雇员信息");
			System.out.println("4.通过ID删除雇员");
			System.out.println("5.退出");
			key = scanner.nextInt();
			switch (key) {
			case 1:
				int id;
				String name;
				System.out.println("请输入雇员id");
				id = scanner.nextInt();
				System.out.println("请输入雇员姓名");
				name = scanner.next();
				Emp emp = new Emp(id, name);
				empLinkedListArrays.add(emp);
				break;
			case 2:
				empLinkedListArrays.list();
				break;
			case 3:
				System.out.println("请输入雇员的id");
				id = scanner.nextInt();
				empLinkedListArrays.findEmpByID(id);
				break;
			case 4:
				System.out.println("请输入要删除的雇员的id");
				id = scanner.nextInt();
				empLinkedListArrays.dropByID(id);
				System.out.println("删除成功");
				break;
			case 5:
				scanner.close();
				System.exit(0);
			default:
				break;
			}
		}

	}

}

// 哈希表
class EmpLinkedListArrays {
	// 创建一个链表数组，当作哈希表
	public EmpLinkedList[] empLinkedListArrays;
	public int size;

	public EmpLinkedListArrays(int size) {
		this.size = size;
		// 初始化链表数组
		empLinkedListArrays = new EmpLinkedList[size];
		// 重点，数组里面的每条链表也需要初始化，不然会报空指针异常
		// 如果不初始化就相当于仅仅初始化了一个空数组
		for (int i = 0; i < size; i++) {
			empLinkedListArrays[i] = new EmpLinkedList();
		}
	}

	// 添加雇员
	public void add(Emp emp) {
		int id = getNumb(emp.id);
		empLinkedListArrays[id].add(emp);
	}

	// 通过ID删除雇员
	public void dropByID(int id) {
		int LinkedListNo = getNumb(id);
		Emp emp = empLinkedListArrays[LinkedListNo].findIEmpByID(id);
		empLinkedListArrays[LinkedListNo].dropEmp(emp);
	}

	// 通过ID查找雇员
	public void findEmpByID(int id) {
		int LinkedListNo = getNumb(id);
		Emp emp = empLinkedListArrays[LinkedListNo].findIEmpByID(id);
		if (emp == null) {
			System.out.println("查无此人");
		} else {
			System.out.printf("雇员ID：%d\n 雇员姓名：%s\n", emp.id, emp.name);
		}
	}

	// 遍历哈希表
	public void list() {
		for (int i = 0; i < size; i++) {
			empLinkedListArrays[i].list(i + 1);
		}
	}

	// 通过模上哈希表数组大小，获得传入的ID属于第几条链表
	public int getNumb(int id) {
		return id % size;
	}
}

// 雇员类
class Emp {
	public int id;
	public String name;
	public Emp next;

	public Emp(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
}

// 雇员链表
class EmpLinkedList {
	// 头结点默认为空
	Emp head;

	// 为链表添加结点
	public void add(Emp emp) {
		if (head == null) {
			head = emp;
			return;
		}
		Emp temp = head;
		while (true) {
			if (temp.next == null) {
				break;
			}
			temp = temp.next;
		}
		temp.next = emp;
	}

	// 通过ID查找链表里的结点
	public Emp findIEmpByID(int id) {
		if (head == null) {
			return null;
		}
		Emp temp = head;
		while (true) {
			if (temp.id == id) {
				break;
			}
			if (temp.next == null) {
				temp = null;
				break;
			}
			temp = temp.next;
		}
		return temp;
	}

	// 删除雇员
	public void dropEmp(Emp emp) {
		if (head == null) {
			return;
		}
		Emp temp = head;
		while (true) {
			// 如果只有一个结点，则直接判断该结点和传入的雇员ID是否相符合
			// 如果符合，直接将头结点置为空然后返回
			if (temp.next == null) {
				if (temp.id == emp.id) {
					head = null;
					return;
				}
				return;
			}
			if (temp.next.id == emp.id) {
				break;
			}
			temp = temp.next;
		}
		temp.next = emp.next;
	}

	// 遍历链表
	public void list(int no) {
		if (head == null) {
			System.out.println("第" + no + "条链表为空");
			return;
		}
		System.out.println("第" + no + "条链表信息为： ");
		Emp temp = head;
		while (true) {
			System.out.printf("=> id: %d  name: %s \t", temp.id, temp.name);
			if (temp.next == null) {
				break;
			}
			temp = temp.next;
		}
		System.out.println();
	}
}
