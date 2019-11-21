package Sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class InsertSort {

	public static void main(String[] args) {
		int[] array = { 8, 3, 2, 7, 6, 9 };
		insertSort(array);
		System.out.println(Arrays.toString(array));

	}

	public void text() {
		int[] array = new int[80000];
		for (int i = 0; i < 80000; i++) {
			array[i] = (int) (Math.random() * 8000000);
		}
		Date date1 = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date1str = simpleDateFormat.format(date1);
		System.out.println("排序前： " + date1str);
		insertSort(array);
		Date date2 = new Date();
		String date2str = simpleDateFormat.format(date2);
		System.out.println("排序后： " + date2str);
	}

	public static void insertSort(int[] array) {
		// 外层循环数组大小-1次
		for (int i = 1; i < array.length; i++) {
			// 记录第i个的值和第i-1个的下标，每次与有序数组的最后一个进行比较
			int val = array[i];
			int preindex = i - 1;
			// 将第i个值和i前面的所有值比较（从最后一个开始往前比较），
			// 如果比前一个小，则前一个值后移一位
			// 直到第i个值比前一个值大且比后一个值小
			// （或preindex小于0，即代表preindex已经指向第一个数值），则退出循环，在将i的值赋值
			// 如果第i个值正好是包括前面所有值中最大的，则直接跳过判断，将自己赋值给自己
			while (preindex >= 0 && val < array[preindex]) {
				array[preindex + 1] = array[preindex];
				preindex--;// 控制跳出循环
			}
			// 每经历一次while循环，i前面所有的数值都会是一个有序数组
			// preindex+1即i 
			array[preindex + 1] = val;
		}
	}

}
