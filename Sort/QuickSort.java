package Sort;

import java.util.Arrays;

public class QuickSort {
	public static void main(String[] args) {
//		int[] array = new int[8000000];
//		for (int i = 0; i < 8000000; i++) {
//			array[i] = (int) (Math.random() * 80000000);
//		}
//		Date date1 = new Date();
//		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		String date1str = simpleDateFormat.format(date1);
//		System.out.println("排序前： " + date1str);
//		quickSort(array, 0, array.length - 1);
//		Date date2 = new Date();
//		String date2str = simpleDateFormat.format(date2);
//		System.out.println("排序后： " + date2str);
		
		 int[] array = { 5, 7, 0, 9, 10 }; quickSort(array, 0, array.length - 1);
		 System.out.println(Arrays.toString(array));
		 
	}

	public static void quickSort(int[] array, int left, int right) {
		int t;
		// 如果left大于right说明该部分的排序已经完成
		if (left > right) {
			return;
		}
		int l = left;
		int r = right;
		// 设置最左边的数为基准位
		int temp = array[left];
		// 当l==r说明一轮探测已经结束，跳出外层循环
		while (l < r) {
			// 一定要先扫描右边的数，如果满足大于基准数，往左移检查下一位
			while (array[r] >= temp && l < r) {
				r--;
			}
			// 注意不要少掉等号，因为左边的指针从left位置出发，那么第一个值肯定和temp相等
			// 扫描左边的数，如果满足小于基准数，往右移检查下一位
			while (array[l] <= temp && l < r) {
				l++;
			}
			if (l < r) {
				t = array[r];
				array[r] = array[l];
				array[l] = t;
			}
		}
		// 将最左边的数与l和r相遇的位置相换
		array[left] = array[r];
		array[r] = temp;
		// 向左边递归
		quickSort(array, left, l - 1);
		// 向右边递归
		quickSort(array, l + 1, right);
	}
}
