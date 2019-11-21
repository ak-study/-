package Algorithm;

public class BinarySearchNoRecursion {

	public static void main(String[] args) {
		int arr[] = { 2, 3, 4, 9, 10, 30, 50 };
		int index = BinarySearch(arr, 11);
		System.out.println(index);

	}
	//二分查找算法，非递归
	public static int BinarySearch(int[] arr, int target) {
		int left = 0;
		int right = arr.length - 1;
		int mid = 0;
		while (left <= right) {
			mid = (left + right) / 2;
			if (target == arr[mid]) {
				return mid;
			} else if (target > arr[mid]) {
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}
		return -1;
	}

}
