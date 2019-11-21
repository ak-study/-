package Sort;

import java.util.Arrays;

public class MergetSort {

	public static void main(String[] args) {
		int[] array = { 4, 2, 1, 3, 6, 45, 78, 9  };
		int[] temp = new int[array.length];
		mergetSort(array, 0, array.length - 1, temp);
		System.out.println(Arrays.toString(array));
	}

	// 分解数组+合并数组
	public static void mergetSort(int[] array, int left, int right, int[] temp) {
		if (left < right) {
			int mid = (left + right) / 2;
			// 向左边分解
			mergetSort(array, left, mid, temp);
			// 向右边分解
			mergetSort(array, mid + 1, right, temp);
			
			merget(array, left, mid, right, temp);
		}
	}

	public static void merget(int[] array, int left, int mid, int right, int[] temp) {
		int i = left;// 指向分解后 左边数组的最左边
		int j = mid + 1;// 指向分解后 右边数组的最左边
		int t = 0;// 指向新数组temp的第一个位置

		// 第一步，比较分解后的两个数组的大小，将小的一个放到temp数组里面，然后当前指针自增
		// 外层循环表示当两个条件满足时，左边数组和右边数组都没遍历完成
		while (i <= mid && j <= right) {
			// i小于j，将i指向的值放到temp里面， 然后t和i自增
			if (array[i] < array[j]) {
				temp[t] = array[i];
				i++;
				t++;
			} else {
				temp[t] = array[j];
				j++;
				t++;
			}
		}
		// 退出第一层while循环代表，左边数组或者右边数组已经遍历完成，开始第二步
		// 第二步，将左右边数组内剩余值依次放入temp数组
		// 如果i<mid，代表左边没有遍历完，还有剩余
		while (i <= mid) {
			temp[t] = array[i];
			i++;
			t++;
		}
		// 如果j<right，代表右边没有遍历完，还有剩余
		while (j <= right) {
			temp[t] = array[j];
			j++;
			t++;
		}
		// 第三步，将temp数组内的值按照原本下标，放入原数组array里面
		// 注意并不是每次都把全部拷贝过去
		t = 0;
		int templeft = left;
		System.out.println(templeft + "" + right);
		while (templeft <= right) {
			array[templeft] = temp[t];
			t++;
			templeft++;
		}
	}
}
