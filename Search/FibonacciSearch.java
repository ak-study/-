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
		// Ѱ����ӽ�ԭ���鳤�ȵ�쳲�������ֵk
		while (right > fib[k]) {
			k++;
		}
		// ��ǰ����temp������ԭ������ֵ+����Ϊ��ӽ�ԭ���鳤�ȵ�쳲�������ֵ��ʣ�ಿ��Ϊ0����
		int[] temp = Arrays.copyOf(arr, fib[k]);
		// ����0�������Щ��ֵ��ȫ���滻��ԭ��������һ����ֵ
		for (int i = right + 1; i < fib[k]; i++) {
			temp[i] = arr[right];
		}
		// ���һ����Ѱ��findVal
		//���������Ժ����˽�...
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
