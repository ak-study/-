package ThreadBinaryTree;

public class ThreadBinaryTreeDemo {

	public static void main(String[] args) {
		// Hero root=new Hero(1, "老大");
		// Hero note2=new Hero(2, "老二");
		// Hero note3=new Hero(3, "老三");
		// Hero note4=new Hero(4, "老四");
		// Hero note5=new Hero(5, "老五");
		// Hero note6=new Hero(6,"老六");
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

		// 二叉树，后面我们要递归创建, 现在简单处理使用手动创建
		root.setLeft(node2);
		root.setRight(node3);
		node2.setLeft(node4);
		node2.setRight(node5);
		node3.setLeft(node6);

		BinaryTree threadBinaryTree = new BinaryTree(root);
		threadBinaryTree.midOrder();
		threadBinaryTree.threadNote(root);
		System.out.println();
		System.out.println("note5的前继为：" + node5.left);
		System.out.println("note5的后继为：" + node5.right);

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
		// 第一步，线索化左子树
		threadNote(note.left);
		// 第二步，线索化当前结点
		// 处理前继结点
		if (note.left == null) {
			note.left = pre;
			note.leftType = 1;
		}
		// 处理后继结点
		if (pre != null && pre.right == null) {
			pre.right = note;
			pre.rightType = 1;
		}
		// 注意！重要，将当前结点赋值给pre,pre记录遍历当前结点的前一个结点
		// 注意观察，pre为成员变量！！！
		pre = note;
		// 第三步，线索化右子树
		threadNote(note.right);
	}

	public void ThreadList() {
		Hero note = root;
		//线索化遍历，找到所有已经被线索化的结点，并且输出结点
		while (note != null) {
			//如果note的leftType等于1，说明该结点已经被线索化
			while (note.leftType == 0) {
				note = note.left;
			}
			//输出该结点
			System.out.println(note);
			//如果该结点的rightType等于1，说明他的下一个结点也被线索化
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
			System.out.println("二叉树为空，无法遍历");
		}
	}

	public void preOrder() {
		if (root != null) {
			this.root.preOrder();
		} else {
			System.out.println("二叉树为空，无法遍历");
		}
	}

	public void midOrder() {
		if (root != null) {
			this.root.midOrder();
		} else {
			System.out.println("二叉树为空，无法遍历");
		}
	}

	public void lastOrder() {
		if (root != null) {
			this.root.lastOrder();
		} else {
			System.out.println("二叉树为空，无法遍历");
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

	// 前序遍历
	public void preOrder() {
		// 输出当前结点
		System.out.println(this);
		if (this.left != null) {
			this.left.preOrder();
		}
		if (this.right != null) {
			this.right.preOrder();
		}
	}

	// 中序遍历
	public void midOrder() {
		if (this.left != null) {
			this.left.midOrder();
		}
		System.out.println(this);
		if (this.right != null) {
			this.right.midOrder();
		}
	}

	// 后序遍历
	public void lastOrder() {
		if (this.left != null) {
			this.left.lastOrder();
		}
		if (this.right != null) {
			this.right.lastOrder();
		}
		System.out.println(this);
	}

	// 前序查找
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