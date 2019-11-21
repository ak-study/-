package BinaryTree;

public class ThreadBinaryTre {

	public static void main(String[] args) {
		Note root=new Note(1, "老大");
		Note note2=new Note(3, "老二");
		Note note3=new Note(6, "老三");
		Note note4=new Note(8, "老四");
		Note note5=new Note(10, "老五");
		Note note6=new Note(14,"老六");
		
		root.left=note2;
		root.right=note3;
		note2.left=note4;
		note2.right=note5;
		note3.left=note6;
		
		threadBinaryTree threadBinaryTree=new threadBinaryTree(root);
		threadBinaryTree.threadNote(root);
	}
}

class threadBinaryTree {
	Note root;
	Note pre;

	public threadBinaryTree(Note root) {
		this.root = root;
	}
	
	public void threadNote(Note note) {
		if(note==null) {
			System.out.println("结点为空，无法线索化");
			return;
		}
		//第一步，线索化左子树
		threadNote(note.left);
		//第二步，线索化当前结点
		//处理前继结点
		if(note.left!=null) {
			note.left=pre;
			note.leftType=1;
		}
		//处理后继结点
		if(note.right!=null) {
			pre.right=note;
			pre.rightType=1;
		}
		//注意！重要，将当前结点赋值给pre
		pre=note;
		//第三步，线索化右子树
		threadNote(note.right);
	}

	public Note preOrderSearch(int no) {
		if (root != null) {
			return root.preOrderSearch(no);
		} else {
			return null;
		}
	}

	public Note midOrderSearch(int no) {
		if (root != null) {
			return root.midOrderSearch(no);
		} else {
			return null;
		}
	}

	public Note lastOrderSearch(int no) {
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

class Note {
	private int no;
	private String name;
	public Note left;
	public Note right;
	public int leftType;
	public int rightType;

	public Note(int no, String na) {
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

	public Note getLeft() {
		return left;
	}

	public void setLeft(Note left) {
		this.left = left;
	}

	public Note getRight() {
		return right;
	}

	public void setRight(Note right) {
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
	public Note preOrderSearch(int no) {
		if (this.no == no) {
			return this;
		}
		Note res = null;
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

	public Note midOrderSearch(int no) {
		Note res = null;
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

	public Note lastOrderSearch(int no) {
		Note res = null;
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
