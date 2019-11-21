package Sort;

import java.text.SimpleDateFormat;
import java.util.Date;

public class BubbleSort {

	// 冒泡排序，时间复杂度（O（n^2））
	public static void main(String[] args) {
		int[] array = new int[80000];
		for (int i = 0; i < 80000; i++) {
			array[i] = (int) (Math.random() * 80000);
		}
		Date date1 = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date1str=simpleDateFormat.format(date1);
		System.out.println("排序前： " + date1str);
		BobbleSort(array);
		Date date2 = new Date();
		String date2str=simpleDateFormat.format(date2);
		System.out.println("排序后： " + date2str);

	}

	public static void BobbleSort(int[] array) {
		int temp;
		boolean flag = false;
		// 外层循环数组大小-1次
		for (int i = 0; i < array.length - 1; i++) {
			// 内层循环数组大小-1-i次（每次排序都会确定一个最大值，所以每次排序次数都会减1）
			for (int j = 0; j < array.length - 1 - i; j++) {
				// 如果当前的值比后一个值大，则交换
				if (array[j] > array[j + 1]) {
					flag = true;
					temp = array[j];
					array[j] = array[j + 1];
					array[j + 1] = temp;
				}
			}
			// 如果一轮排序下来一次都没有发生交换，则退出外层循环
			if (!flag) { 
				break;
			} else {
				flag = false;// 如果发生了交换，则重置flag，进行下次排序
			}
		}
	}

}
