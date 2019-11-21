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
			System.out.println("����������Ϊ�գ� �޷�����");
		}
	}
	public void add(Node node) {
		//���ͷ���Ϊ�գ���ֱ�ӽ������㸳ֵ��ͷ���
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
	 * ɾ���� Ŀ����� ���ӽ��Ϊ���ڵ�Ķ�������������С��㣬�����ظ�ֵ
	 * @param node Ŀ��������ӽ��
	 * @return ��������������Сֵ
	 */
	private int delRightTreeMin(Node node) {
		Node targetNode = node;
		// ѭ������߲��ң�ֱ���ﵽ����ߵ��Ǹ���㣨�ȸý��Ϊ����������Сֵ��
		while (targetNode.left != null) {
			targetNode = targetNode.left;
		}
		// ɾ���ý��
		delNode(targetNode.value);
		// ���ظ�ֵ
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
			// ���Ŀ����������ӽ�㶼Ϊ�գ���˵��Ŀ����ΪҶ�ӽ��
			if (targetNode.left == null && targetNode.right == null) {
				// ������������ӽ�����Ŀ���㣬˵��Ŀ����Ϊ���ڵ�����ӽ��
				if (parentNode.left != null && parentNode.left.value == targetNode.value) {
					parentNode.left = null;
				}
				// ������������ӽ�����Ŀ���㣬˵��Ŀ����Ϊ���ڵ�����ӽ��
				if (parentNode.right != null && parentNode.right.value == targetNode.value) {
					parentNode.right = null;
				}
				//˵��Ŀ��������������
			}else if(targetNode.left!=null&targetNode.right!=null) {
				int min=delRightTreeMin(targetNode.right);
				targetNode.value=min;
			}else {

				// ˵��Ŀ����ֻ���������������������Ѿ��ų����Ҷ�û�н������Ҷ��н��������
				// ˵��Ŀ������ӽ��Ϊ���ӽ��
				if (targetNode.left != null) {
					if (parentNode != null) {
						// ˵��Ŀ����Ϊ���������ӽ��
						if (parentNode.left != null && parentNode.left.value == targetNode.value) {
							parentNode.left = targetNode.left;
							// ˵��Ŀ����Ϊ���ڵ�����ӽ��
						} else {
							parentNode.right = targetNode.left;
						}
					} else {
						//������ڵ�Ϊ�գ�ֱ�ӽ����ڵ�ָ��Ŀ���������
						root = targetNode.left;
					}
				} else {
					if (parentNode != null) {
						// ˵��Ŀ����Ϊ���������ӽ��
						if (parentNode.left != null && parentNode.left.value == targetNode.value) {
							parentNode.left = targetNode.right;
							// ˵��Ŀ����Ϊ���ڵ�����ӽ��
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
		//������Ϊ��ֱ�ӷ���
		if(node==null) {
			return;
		}
		//����������ֵС�ڵ�ǰ����ֵ�����������
		if(node.value<this.value) {
			//��, ��ǰ��������Ϊ��ʱ���
			if(this.left==null) {
				this.left=node;
			}else {
				this.left.add(node);
			}
		//����������ֵ���ڵ��ڵ�ǰ����ֵ�������ұ���
		}else {
			//��, ��ǰ�����ҽ��Ϊ��ʱ���
			if(this.right==null) {
				this.right=node;
			}else {
				this.right.add(node);
			}
		}
	}
	/**
	 * ���ܣ�ͨ��value���Ҷ�Ӧ�Ľ��
	 * @param value Ŀ�����ֵ
	 * @return ����ҵ�value��Ӧ�Ľ�㣬�򷵻ظý�㣬���򷵻�null
	 */
	public Node search(int value) {
		// ���value���ڵ�ǰ����value��˵���ҵ������ص�ǰ���
		if (value == this.value) {
			return this;
		}
		// ���valueС�ڵ�ǰ����value����˵��Ŀ�����ڵ�ǰ������ߣ�Ӧ����ߵݹ�
		if (this.left != null && value < this.value) {
			return this.left.search(value);
			// ���valueС�ڵ�ǰ����value����˵��Ŀ�����ڵ�ǰ������ߣ�Ӧ����ߵݹ�
		} else if (this.right != null && value >= this.value) {
			return this.right.search(value);
			// ������������������˵����ѯ����
		} else {
			return null;
		}
	}
	/**
	 * ���ܣ�����value��Ӧ�Ľ��ĸ���㣬������
	 * @param value Ŀ�����Ӧ��value
	 * @return ����Ŀ����ĸ����
	 */
	public Node serachParent(int value) {
		// �����ǰ�������ӽ��������ӽ�����Ŀ��ֵ���򷵻ص�ǰ��㣨˵���Ѿ��ҵ�����㣩
		if ((this.left != null && this.left.value == value) || (this.right != null && this.right.value == value)) {
			return this;
		}
		// ��������ݹ��ѯ
		// ���valueС�ڵ�ǰ����value����˵��Ŀ�����ڵ�ǰ������ߣ�Ӧ����ߵݹ�
		if (this.left != null && value < this.value) {
			return this.left.serachParent(value);
			// ���valueС�ڵ�ǰ����value����˵��Ŀ�����ڵ�ǰ������ߣ�Ӧ����ߵݹ�
		} else if (this.right != null && value >= this.value) {
			return this.right.serachParent(value);
			// ������������������˵����ѯ����
		} else {
			return null;
		}
	}
	@Override
	public String toString() {
		return "Node [value=" + value + "]";
	}
	
}
