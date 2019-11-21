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
		
		//������ת��ǰ����Ҫ������
		//��һ�������������������߶�-�������߶�>1  ����  �������߶�-�������߶�>��������ת
		//ÿ�����������֮�󣬶��Ե�ǰ������һ����ת�жϣ�����������ĸ߶ȴ�������������֮��Ȼ��������ת
		//����תǰ������������������
		//1.�����������ת����ô���н�����������������������ߣ�����ƽ����Ч
		//2.�����������ת����ô���н�����������������������ߣ�����ƽ����Ч
		//3.�����������ת�У��ҵĽ������������������ͣ���ô�Ըý���������ת
		//4.����תҲ����ͬ����
		
		// ��������ת
		if (this.leftHight() > this.rightHight()) {
			// �������ת�У���ǰ�������ӽ����������߶�>��ǰ��ǰ�������ӽ����������߶�
			// ��4��
			// ��Ըý���Ƚ�������ת��Ȼ������������ת
			if (this.left != null && this.left.rightHight() > this.left.leftHight()) {
				this.left.leftRotate();
			}
			this.rightRotate();
			// ÿ����һ����ת��ֱ�ӷ��أ��Է�����BUG
			return;
		}
		// ��������ת
		if (this.leftHight() < this.rightHight()) {
			// �������ת�У���ǰ�������ӽ����������߶�>��ǰ��ǰ�������ӽ����������߶�
			// ��3��
			// ��Ըý���Ƚ�������ת��Ȼ������������ת
			if (this.right != null && this.right.leftHight() < this.right.rightHight()) {
				this.left.rightRotate();
			}
			this.leftRotate();
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
	//��ȡ�Ե�ǰ���Ϊ���ڵ�������ĸ߶�
	public int Hight() {
		return Math.max(this.left==null ? 0 : this.left.Hight(), this.right==null ? 0 : this.right.Hight())+1;
	}
	@Override
	public String toString() {
		return "Node [value=" + value + "]";
	}
	//����ת
	public void leftRotate() {
		//����һ����㣬ֵ�͵�ǰ���һ��
		Node newNode=new Node(value);
		//���½���������ָ��ǰ����������
		newNode.left=this.left;
		//���½���������ָ���½���  ������  ��  ������
		newNode.right=this.right.left;
		//����ǰ���ָ��ǰ�����ҽ�㣨��ֵ��
		this.value=this.right.value;
		//����ǰ�������ӽ��ָ�� ���ӽ��� ���ӽ�㣨���ˣ�ԭ�ȵ����ӽ���Ѿ�������ָ�������������գ�
		this.right=this.right.right;
		//����ǰ�������ӽ��ָ���½��
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
