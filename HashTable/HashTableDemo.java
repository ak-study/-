package HashTable;

import java.util.Scanner;

public class HashTableDemo {

	public static void main(String[] args) {
		EmpLinkedListArrays empLinkedListArrays = new EmpLinkedListArrays(7);
		Scanner scanner = new Scanner(System.in);
		int key;
		while (true) {
			System.out.println("��������ţ�");
			System.out.println("1.��ӹ�Ա");
			System.out.println("2.��ʾ��Ա��Ϣ");
			System.out.println("3.ͨ��ID���ҹ�Ա��Ϣ");
			System.out.println("4.ͨ��IDɾ����Ա");
			System.out.println("5.�˳�");
			key = scanner.nextInt();
			switch (key) {
			case 1:
				int id;
				String name;
				System.out.println("�������Աid");
				id = scanner.nextInt();
				System.out.println("�������Ա����");
				name = scanner.next();
				Emp emp = new Emp(id, name);
				empLinkedListArrays.add(emp);
				break;
			case 2:
				empLinkedListArrays.list();
				break;
			case 3:
				System.out.println("�������Ա��id");
				id = scanner.nextInt();
				empLinkedListArrays.findEmpByID(id);
				break;
			case 4:
				System.out.println("������Ҫɾ���Ĺ�Ա��id");
				id = scanner.nextInt();
				empLinkedListArrays.dropByID(id);
				System.out.println("ɾ���ɹ�");
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

// ��ϣ��
class EmpLinkedListArrays {
	// ����һ���������飬������ϣ��
	public EmpLinkedList[] empLinkedListArrays;
	public int size;

	public EmpLinkedListArrays(int size) {
		this.size = size;
		// ��ʼ����������
		empLinkedListArrays = new EmpLinkedList[size];
		// �ص㣬���������ÿ������Ҳ��Ҫ��ʼ������Ȼ�ᱨ��ָ���쳣
		// �������ʼ�����൱�ڽ�����ʼ����һ��������
		for (int i = 0; i < size; i++) {
			empLinkedListArrays[i] = new EmpLinkedList();
		}
	}

	// ��ӹ�Ա
	public void add(Emp emp) {
		int id = getNumb(emp.id);
		empLinkedListArrays[id].add(emp);
	}

	// ͨ��IDɾ����Ա
	public void dropByID(int id) {
		int LinkedListNo = getNumb(id);
		Emp emp = empLinkedListArrays[LinkedListNo].findIEmpByID(id);
		empLinkedListArrays[LinkedListNo].dropEmp(emp);
	}

	// ͨ��ID���ҹ�Ա
	public void findEmpByID(int id) {
		int LinkedListNo = getNumb(id);
		Emp emp = empLinkedListArrays[LinkedListNo].findIEmpByID(id);
		if (emp == null) {
			System.out.println("���޴���");
		} else {
			System.out.printf("��ԱID��%d\n ��Ա������%s\n", emp.id, emp.name);
		}
	}

	// ������ϣ��
	public void list() {
		for (int i = 0; i < size; i++) {
			empLinkedListArrays[i].list(i + 1);
		}
	}

	// ͨ��ģ�Ϲ�ϣ�������С����ô����ID���ڵڼ�������
	public int getNumb(int id) {
		return id % size;
	}
}

// ��Ա��
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

// ��Ա����
class EmpLinkedList {
	// ͷ���Ĭ��Ϊ��
	Emp head;

	// Ϊ������ӽ��
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

	// ͨ��ID����������Ľ��
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

	// ɾ����Ա
	public void dropEmp(Emp emp) {
		if (head == null) {
			return;
		}
		Emp temp = head;
		while (true) {
			// ���ֻ��һ����㣬��ֱ���жϸý��ʹ���Ĺ�ԱID�Ƿ������
			// ������ϣ�ֱ�ӽ�ͷ�����Ϊ��Ȼ�󷵻�
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

	// ��������
	public void list(int no) {
		if (head == null) {
			System.out.println("��" + no + "������Ϊ��");
			return;
		}
		System.out.println("��" + no + "��������ϢΪ�� ");
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
