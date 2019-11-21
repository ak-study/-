package BinarySortTree;

public class binarysorttreedemo {

	public static void main(String[] args) {
		int[] arr= {7,3,10,12,5,1,9,2,0};
		binarySortTree binarySortTree=new binarySortTree();
		for(int i=0;i<arr.length;i++) {
			binarySortTree.add(new Node(arr[i]));
		}
		//binarySortTree.infixOrder();
		binarySortTree.delNode(7);
		binarySortTree.delNode(3);
		binarySortTree.delNode(10);
		binarySortTree.delNode(12);
		binarySortTree.delNode(5);
		binarySortTree.delNode(1);
		binarySortTree.delNode(9);
		binarySortTree.delNode(2);
		binarySortTree.delNode(0);
		binarySortTree.infixOrder();
	}

}
class binarySortTree{
	Node root;
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
	@Override
	public String toString() {
		return "Node [value=" + value + "]";
	}
	
}
