package Sort;

import java.util.Arrays;

public class HeapSort {

	public static void main(String[] args) {
		int arr[] = { 4, 9, 52, 34, 12 };
		heapSort(arr);
		System.out.println(Arrays.toString(arr));

	}

	public static void heapSort(int[] arr) {
		int temp = 0;
		// 先将数组排序成一个堆
		for (int i = arr.length / 2 - 1; i >= 0; i--) {
			adjust(arr, i, arr.length);
		}
		// 每次都将第0个值替换成数组里面最大的值，然后放到数组的末尾
		// 下次计算最大值时，就将上一次的最大值去除
		for (int j = arr.length - 1; j > 0; j--) {
			temp = arr[j];
			arr[j] = arr[0];
			arr[0] = temp;
			adjust(arr, 0, j);
		}
	}

	/**
	 * 功能：每次都会将i指向的值变成传进来的数组里面最大的值
	 * 
	 * @param arr
	 *            传进来的数组
	 * @param i
	 *            该数组的非叶子结点
	 * @param length
	 *            传进来的长度，每次长度有会发生变化，因为堆排序会降每次的最大值放在最后
	 */
	public static void adjust(int[] arr, int i, int length) {
		int temp = arr[i];
		for (int k = i * 2 + 1; k < length; k = k * 2 + 1) {
			if (k + 1 < length && arr[k] < arr[k + 1]) {
				// k指向的是该非叶子结点的左结点（i*2+1），则k+1指向该非叶子结点的右结点（i*2+1+1）
				k++;// 如果左结点比右结点小，则将k指向右结点
			}
			// 上一个if结构出来后，k一定指向非叶子结点i的最大子结点
			if (arr[k] > temp) {
				arr[i] = arr[k];// 将最大的结点赋值给该非叶子结点（即k的父结点）,此时k的位置和i的位置，值一样
				i = k;// 并且将i指向k，后续循环查找后面的子树
			} else {
				break;// 如果子结点不比父结点大，则直接退出
			}
		}
		arr[i] = temp;
	}

}
