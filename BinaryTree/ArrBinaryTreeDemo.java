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
	//重载前序遍历
	
	//index为数组下标，从0开始
	public void preOrder(int index) {
		if(arr.length==0&&arr==null) {
			System.out.println("数组为空，无法遍历");
			return;
		}
		//向左边递归遍历
		if((2*index+1)<arr.length) {
			preOrder(2*index+1);
		}
		System.out.println(arr[index]);
		//向右边递归遍历
		if((2*index+2)<arr.length) {
			preOrder(2*index+2);
		}
	}
}
