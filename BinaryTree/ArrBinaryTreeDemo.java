package BinaryTree;

public class ArrBinaryTreeDemo {

	public static void main(String[] args) {
		int[] arr= {1,3,6,8,10,14};
		ArrBinaryTree arrBinaryTree=new ArrBinaryTree(arr);
		arrBinaryTree.preOrder(0);
	}

}
class ArrBinaryTree{
	int[] arr;
	public ArrBinaryTree(int[] arr) {
		this.arr = arr;
	}
	//����ǰ�����
	
	//indexΪ�����±꣬��0��ʼ
	public void preOrder(int index) {
		if(arr.length==0&&arr==null) {
			System.out.println("����Ϊ�գ��޷�����");
			return;
		}
		//����ߵݹ����
		if((2*index+1)<arr.length) {
			preOrder(2*index+1);
		}
		System.out.println(arr[index]);
		//���ұߵݹ����
		if((2*index+2)<arr.length) {
			preOrder(2*index+2);
		}
	}
}
