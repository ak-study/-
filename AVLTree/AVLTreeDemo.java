package AVLTree;

public class AVLTreeDemo {

	public static void main(String[] args) {
		int[] arr= {10,12,8,9,7,6};
		AVLTree avlTree=new AVLTree();
		for(int i=0;i<arr.length;i++) {
			avlTree.add(new Node(arr[i]));
		}
		System.out.println(avlTree.getRoot().leftHight());
		System.out.println(avlTree.getRoot().rightHight());

	}

}
class AVLTree{
	Node root;
	public Node getRoot() {
		return root;
	}
	public void infixOrder() {
		if(root!=null) {
			root.infixOrder();
		}else {
			System.out.println("二叉排序树为空， 无法遍历");
		}
	}
	public void add(Node node) {
		//如果头结点为空，则直接将传入结点赋值给头结点
		if(root==null) {
			root=node;
		}else {
			root.add(node);
		}
	}
	private Node serach(int value) {
		if(root==null) {
			return null;
		}
		return root.search(value);
	}
	private Node serachParent(int value) {
		if(root==null) {
			return null;
		}
		return root.serachParent(value);
	}
	/**
	 * 删除以 目标结点的 右子结点为根节点的二叉排序树的最小结点，并返回该值
	 * @param node 目标结点的右子结点
	 * @return 返回右子树的最小值
	 */
	private int delRightTreeMin(Node node) {
		Node targetNode = node;
		// 循环向左边查找，直到达到最左边的那个结点（既该结点为该子树的最小值）
		while (targetNode.left != null) {
			targetNode = targetNode.left;
		}
		// 删除该结点
		delNode(targetNode.value);
		// 返回该值
		return targetNode.value;
	}

	public void delNode(int value) {
		if (root == null) {
			return;
		} else {
			Node targetNode = serach(value);
			if (targetNode == null) {
				return;
			}
			if (root.value == value&&root.left==null&&root.right==null) {
				root = null;
				return;
			}
			Node parentNode = serachParent(value);
			// 如果目标结点的左右子结点都为空，则说明目标结点为叶子结点
			if (targetNode.left == null && targetNode.right == null) {
				// 如果父结点的左子结点等于目标结点，说明目标结点为父节点的左子结点
				if (parentNode.left != null && parentNode.left.value == targetNode.value) {
					parentNode.left = null;
				}
				// 如果父结点的右子结点等于目标结点，说明目标结点为父节点的右子结点
				if (parentNode.right != null && parentNode.right.value == targetNode.value) {
					parentNode.right = null;
				}
				//说明目标结点右两个子树
			}else if(targetNode.left!=null&targetNode.right!=null) {
				int min=delRightTreeMin(targetNode.right);
				targetNode.value=min;
			}else {

				// 说明目标结点只有左子树或者右子树（已经排除左右都没有结点和左右都有结点的情况）
				// 说明目标结点的子结点为左子结点
				if (targetNode.left != null) {
					if (parentNode != null) {
						// 说明目标结点为父结点的左子结点
						if (parentNode.left != null && parentNode.left.value == targetNode.value) {
							parentNode.left = targetNode.left;
							// 说明目标结点为父节点的右子结点
						} else {
							parentNode.right = targetNode.left;
						}
					} else {
						//如果父节点为空，直接将根节点指向目标结点的左结点
						root = targetNode.left;
					}
				} else {
					if (parentNode != null) {
						// 说明目标结点为父结点的左子结点
						if (parentNode.left != null && parentNode.left.value == targetNode.value) {
							parentNode.left = targetNode.right;
							// 说明目标结点为父节点的右子结点
						} else {
							parentNode.right = targetNode.right;
						}
					} else {
						root = targetNode.right;
					}
				}

			}
		}
	}
	
}
class Node{
	int value;
	Node left;
	Node right;
	public Node(int value) {
		this.value=value;
	}
	public  void infixOrder() {
		if(this.left!=null) {
			this.left.infixOrder();
		}
		System.out.println(this);
		if(this.right!=null) {
			this.right.infixOrder();
		}
	}
	public void add(Node node) {
		//如果结点为空直接返回
		if(node==null) {
			return;
		}
		//如果传入结点的值小于当前结点的值，则向左遍历
		if(node.value<this.value) {
			//当, 当前结点的左结点为空时添加
			if(this.left==null) {
				this.left=node;
			}else {
				this.left.add(node);
			}
		//如果传入结点的值大于等于当前结点的值，则向右遍历
		}else {
			//当, 当前结点的右结点为空时添加
			if(this.right==null) {
				this.right=node;
			}else {
				this.right.add(node);
			}
		}
		
		//进行旋转，前提提要！！！
		//第一个条件，当（左子树高度-右子树高度>1  或者  右子树高度-高子树高度>）进行旋转
		//每个结点添加完成之后，都对当前树进行一个旋转判断，如果左子树的高度大于又子树（反之亦然），则旋转
		//在旋转前必须满足以下条件：
		//1.如果进行左旋转，那么所有结点的右子树都必须比左子树高，否则平衡无效
		//2.如果进行右旋转，那么所有结点的左子树都必须比右子树高，否则平衡无效
		//3.如果出现左旋转中，右的结点的右子树比左子树低，那么对该结点进行右旋转
		//4.右旋转也是相同道理
		
		// 进行右旋转
		if (this.leftHight() > this.rightHight()) {
			// 如果右旋转中，当前结点的左子结点的右子树高度>当前当前结点的左子结点的左子树高度
			// 第4点
			// 则对该结点先进行左旋转，然后再整体右旋转
			if (this.left != null && this.left.rightHight() > this.left.leftHight()) {
				this.left.leftRotate();
			}
			this.rightRotate();
			// 每进行一次旋转，直接返回，以防出现BUG
			return;
		}
		// 进行左旋转
		if (this.leftHight() < this.rightHight()) {
			// 如果左旋转中，当前结点的右子结点的左子树高度>当前当前结点的右子结点的右子树高度
			// 第3点
			// 则对该结点先进行右旋转，然后再整体左旋转
			if (this.right != null && this.right.leftHight() < this.right.rightHight()) {
				this.left.rightRotate();
			}
			this.leftRotate();
		}
	}
	/**
	 * 功能：通过value查找对应的结点
	 * @param value 目标结点的值
	 * @return 如果找到value对应的结点，则返回该结点，否则返回null
	 */
	public Node search(int value) {
		// 如果value等于当前结点的value，说明找到，返回当前结点
		if (value == this.value) {
			return this;
		}
		// 如果value小于当前结点的value，则说明目标结点在当前结点的左边，应向左边递归
		if (this.left != null && value < this.value) {
			return this.left.search(value);
			// 如果value小于当前结点的value，则说明目标结点在当前结点的左边，应向左边递归
		} else if (this.right != null && value >= this.value) {
			return this.right.search(value);
			// 如果不满足上面两项，则说明查询不到
		} else {
			return null;
		}
	}
	/**
	 * 功能：查找value对应的结点的父结点，并返回
	 * @param value 目标结点对应的value
	 * @return 返回目标结点的父结点
	 */
	public Node serachParent(int value) {
		// 如果当前结点的左子结点或者右子结点等于目标值，则返回当前结点（说明已经找到父结点）
		if ((this.left != null && this.left.value == value) || (this.right != null && this.right.value == value)) {
			return this;
		}
		// 否则继续递归查询
		// 如果value小于当前结点的value，则说明目标结点在当前结点的左边，应向左边递归
		if (this.left != null && value < this.value) {
			return this.left.serachParent(value);
			// 如果value小于当前结点的value，则说明目标结点在当前结点的左边，应向左边递归
		} else if (this.right != null && value >= this.value) {
			return this.right.serachParent(value);
			// 如果不满足上面两项，则说明查询不到
		} else {
			return null;
		}
	}
	public int rightHight() {
		if (right==null) {
			return 0;
		}
		return right.Hight();
	}
	public int leftHight() {
		if (left==null) {
			return 0;
		}
		return left.Hight();
	}
	//获取以当前结点为根节点的子树的高度
	public int Hight() {
		return Math.max(this.left==null ? 0 : this.left.Hight(), this.right==null ? 0 : this.right.Hight())+1;
	}
	@Override
	public String toString() {
		return "Node [value=" + value + "]";
	}
	//左旋转
	public void leftRotate() {
		//创建一个结点，值和当前结点一样
		Node newNode=new Node(value);
		//将新结点的左子树指向当前结点的左子树
		newNode.left=this.left;
		//将新结点的右子树指向新结点的  右子树  的  左子树
		newNode.right=this.right.left;
		//将当前结点指向当前结点的右结点（赋值）
		this.value=this.right.value;
		//将当前结点的右子结点指向 右子结点的 有子结点（至此，原先的右子结点已经无引用指向它，将被回收）
		this.right=this.right.right;
		//将当前结点的左子结点指向新结点
		this.left=newNode;
	}
	public void rightRotate() {
		Node newNode=new Node(value);
		newNode.right=this.right;
		newNode.left=this.left.right;
		this.value=this.left.value;
		this.left=this.left.left;
		this.right=newNode;
	}
}
