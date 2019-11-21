package BinaryTree;

public class BinaryTreeDemo {

	public static void main(String[] args) {
		Hero root = new Hero(1, "�ϴ�");
		Hero note2 = new Hero(2, "�϶�");
		Hero note3 = new Hero(3, "����");
		Hero note4 = new Hero(4, "����");

		root.setLeft(note2);
		root.setRight(note3);
		note3.setRight(note4);
		BinaryTree binaryTree = new BinaryTree(root);

		// System.out.println("ǰ�����");
		// binaryTree.preOrder();
		// System.out.println("�������");
		// binaryTree.midOrder();
		// System.out.println("�������");
		// binaryTree.lastOrder();

//		System.out.println("ɾ��ǰ");
//		binaryTree.preOrder();
//		binaryTree.delNote(4);
//		System.out.println("ɾ����");
//		binaryTree.preOrder();
		Hero node=binaryTree.preOrderSearch(4);
		System.out.println(node);

	}

}

class BinaryTree {
	Hero root;

	public BinaryTree(Hero root) {
		this.root = root;
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
		Hero temp=null;
		if(this.left!=null) {
			temp=this.left.preOrderSearch(no);
		}
		if(temp!=null) {
			return temp;
		}
		if(this.right!=null) {
			temp=this.right.preOrderSearch(no);
		}
		return temp;
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
