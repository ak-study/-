package BinaryTree;

public class BinaryTreeDemo {

	public static void main(String[] args) {
		Hero root = new Hero(1, "老大");
		Hero note2 = new Hero(2, "老二");
		Hero note3 = new Hero(3, "老三");
		Hero note4 = new Hero(4, "老四");

		root.setLeft(note2);
		root.setRight(note3);
		note3.setRight(note4);
		BinaryTree binaryTree = new BinaryTree(root);

		// System.out.println("前序遍历");
		// binaryTree.preOrder();
		// System.out.println("中序遍历");
		// binaryTree.midOrder();
		// System.out.println("后序遍历");
		// binaryTree.lastOrder();

//		System.out.println("删除前");
//		binaryTree.preOrder();
//		binaryTree.delNote(4);
//		System.out.println("删除后");
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
