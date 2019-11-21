package Search;

import java.util.Arrays;

public class FibonacciSearch {
	static int maxsize = 20;

	public static void main(String[] args) {
		int[] arr = { 1, 2, 2, 4, 5, 6, 7, 8 };
		int index = fibonacciSearch(arr, 4);
		System.out.println(index);

	}

	public static int[] getFib() {
		int[] fib = new int[maxsize];
		fib[0] = 1;
		fib[1] = 1;
		for (int i = 2; i < maxsize; i++) {
			fib[i] = fib[i - 1] + fib[i - 2];
		}
		return fib;
	}

	public static int fibonacciSearch(int[] arr, int findVal) {
		int left = 0;
		int right = arr.length - 1;
		int k = 0;
		int mid = 0;
		int fib[] = getFib();
		// 寻找最接近原数组长度的斐波那契数值k
		while (right > fib[k]) {
			k++;
		}
		// 当前数组temp里面由原数组数值+长度为最接近原数组长度的斐波那契数值，剩余部分为0补充
		int[] temp = Arrays.copyOf(arr, fib[k]);
		// 将用0补充的那些数值，全部替换成原数组的最后一个数值
		for (int i = right + 1; i < fib[k]; i++) {
			temp[i] = arr[right];
		}
		// 最后一步，寻找findVal
		//不懂，等以后再了解...
		while (left <= right) {
			mid = left + fib[k - 1] - 1;
			if (findVal < temp[mid]) {
				right = mid - 1;
				k--;
			} else if (findVal > temp[mid]) {
				left = mid + 1;
				k -= 2;
			} else {
				if (mid <= right) {
					return mid;
				} else {
					return right;
				}
			}
		}
		return -1;
	}
}
