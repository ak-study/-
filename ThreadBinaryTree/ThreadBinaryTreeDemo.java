package ThreadBinaryTree;

public class ThreadBinaryTreeDemo {

	public static void main(String[] args) {
		// Hero root=new Hero(1, "�ϴ�");
		// Hero note2=new Hero(2, "�϶�");
		// Hero note3=new Hero(3, "����");
		// Hero note4=new Hero(4, "����");
		// Hero note5=new Hero(5, "����");
		// Hero note6=new Hero(6,"����");
		//
		// root.left=note2;
		// root.right=note3;
		// note2.left=note4;
		// note2.right=note5;
		// note3.left=note6;
		Hero root = new Hero(1, "tom");
		Hero node2 = new Hero(3, "jack");
		Hero node3 = new Hero(6, "smith");
		Hero node4 = new Hero(8, "mary");
		Hero node5 = new Hero(10, "king");
		Hero node6 = new Hero(14, "dim");

		// ����������������Ҫ�ݹ鴴��, ���ڼ򵥴���ʹ���ֶ�����
		root.setLeft(node2);
		root.setRight(node3);
		node2.setLeft(node4);
		node2.setRight(node5);
		node3.setLeft(node6);

		BinaryTree threadBinaryTree = new BinaryTree(root);
		threadBinaryTree.midOrder();
		threadBinaryTree.threadNote(root);
		System.out.println();
		System.out.println("note5��ǰ��Ϊ��" + node5.left);
		System.out.println("note5�ĺ��Ϊ��" + node5.right);

		threadBinaryTree.ThreadList();

	}

}

class BinaryTree {
	Hero root;
	Hero pre = null;

	public BinaryTree(Hero root) {
		this.root = root;
	}

	public void threadNote(Hero note) {
		if (note == null) {
			return;
		}
		// ��һ����������������
		threadNote(note.left);
		// �ڶ�������������ǰ���
		// ����ǰ�̽��
		if (note.left == null) {
			note.left = pre;
			note.leftType = 1;
		}
		// �����̽��
		if (pre != null && pre.right == null) {
			pre.right = note;
			pre.rightType = 1;
		}
		// ע�⣡��Ҫ������ǰ��㸳ֵ��pre,pre��¼������ǰ����ǰһ�����
		// ע��۲죬preΪ��Ա����������
		pre = note;
		// ��������������������
		threadNote(note.right);
	}

	public void ThreadList() {
		Hero note = root;
		//�������������ҵ������Ѿ����������Ľ�㣬����������
		while (note != null) {
			//���note��leftType����1��˵���ý���Ѿ���������
			while (note.leftType == 0) {
				note = note.left;
			}
			//����ý��
			System.out.println(note);
			//����ý���rightType����1��˵��������һ�����Ҳ��������
			while (note.rightType == 1) {
				note = note.right;
				System.out.println(note);
			}
			note = note.right;
		}
	}

	public Hero preOrderSearch(int no) {
		if (root != null) {
			return root.preOrderSearch(no);
		} else {
			return null;
		}
	}

	public Hero midOrderSearch(int no) {
		if (root != null) {
			return root.midOrderSearch(no);
		} else {
			return null;
		}
	}

	public Hero lastOrderSearch(int no) {
		if (root != null) {
			return root.lastOrderSearch(no);
		} else {
			return null;
		}
	}

	public void delNote(int no) {
		if (root != null) {
			if (root.getNo() == no) {
				root = null;
				return;
			} else {
				root.delNote(no);
			}
		} else {
			System.out.println("������Ϊ�գ��޷�����");
		}
	}

	public void preOrder() {
		if (root != null) {
			this.root.preOrder();
		} else {
			System.out.println("������Ϊ�գ��޷�����");
		}
	}

	public void midOrder() {
		if (root != null) {
			this.root.midOrder();
		} else {
			System.out.println("������Ϊ�գ��޷�����");
		}
	}

	public void lastOrder() {
		if (root != null) {
			this.root.lastOrder();
		} else {
			System.out.println("������Ϊ�գ��޷�����");
		}
	}
}

class Hero {
	private int no;
	private String name;
	public Hero left;
	public Hero right;
	public int leftType = 0;
	public int rightType = 0;

	public Hero(int no, String na) {
		super();
		this.no = no;
		this.name = na;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Hero getLeft() {
		return left;
	}

	public void setLeft(Hero left) {
		this.left = left;
	}

	public Hero getRight() {
		return right;
	}

	public void setRight(Hero right) {
		this.right = right;
	}

	@Override
	public String toString() {
		return "Hero [no=" + no + ", name=" + name + "]";
	}

	// ǰ�����
	public void preOrder() {
		// �����ǰ���
		System.out.println(this);
		if (this.left != null) {
			this.left.preOrder();
		}
		if (this.right != null) {
			this.right.preOrder();
		}
	}

	// �������
	public void midOrder() {
		if (this.left != null) {
			this.left.midOrder();
		}
		System.out.println(this);
		if (this.right != null) {
			this.right.midOrder();
		}
	}

	// �������
	public void lastOrder() {
		if (this.left != null) {
			this.left.lastOrder();
		}
		if (this.right != null) {
			this.right.lastOrder();
		}
		System.out.println(this);
	}

	// ǰ�����
	public Hero preOrderSearch(int no) {
		if (this.no == no) {
			return this;
		}
		Hero res = null;
		if (this.left != null) {
			res = this.left.preOrderSearch(no);
		}
		if (res != null) {
			return res;
		}
		if (this.right != null) {
			res = this.right.preOrderSearch(no);
		}
		return res;
	}

	public Hero midOrderSearch(int no) {
		Hero res = null;
		if (this.left != null) {
			res = this.left.preOrderSearch(no);
		}
		if (res != null) {
			return res;
		}
		if (this.no == no) {
			return this;
		}
		if (this.right != null) {
			res = this.right.preOrderSearch(no);
		}
		return res;
	}

	public Hero lastOrderSearch(int no) {
		Hero res = null;
		if (this.left != null) {
			res = this.left.preOrderSearch(no);
		}
		if (res != null) {
			return res;
		}
		if (this.right != null) {
			res = this.right.preOrderSearch(no);
		}
		if (res != null) {
			return res;
		}
		if (this.no == no) {
			return this;
		}
		return res;
	}

	public void delNote(int no) {
		if (this.left != null && this.left.no == no) {
			this.left = null;
			return;
		}
		if (this.right != null && this.right.no == no) {
			this.right = null;
			return;
		}
		if (this.left != null) {
			this.left.delNote(no);
		}
		if (this.right != null) {
			this.right.delNote(no);
		}
	}

}