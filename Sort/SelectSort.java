package Sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class SelectSort {
	public static void main(String[] args) {
		int[] array = { 8, 3, 2, 7, 6, 9 };
		Select(array);
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
		Select2(array);
		Date date2 = new Date();
		String date2str = simpleDateFormat.format(date2);
		System.out.println("排序后： " + date2str);
	}

	public static void Select(int[] array) {
		int temp;
		boolean flag = false;
		// 外层循环数组大小-1次
		for (int i = 0; i < array.length - 1; i++) {
			// 内层循环数组大小-1-i次（每次排序都会确定一个最小值，所以每次排序次数都会减1）
			for (int j = 0; j < array.length - 1 - i; j++) {
				// 如果当前的值比后面某一个值大，则交换
				if (array[i] > array[i + j + 1]) {
					flag = true;
					temp = array[i];
					array[i] = array[i + j + 1];
					array[i + j + 1] = temp;
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

	public static void Select2(int[] array) {
		for (int i = 0; i < array.length - 1; i++) {
			// 先假定第i个为最小值，存下该指的数组下标和数值
			int minIndex = i;
			int min = array[i];
			for (int j = i + 1; j < array.length; j++) {
				// 如果找到比假定值小的值，则替换假定值min和其下标minIndex
				if (min > array[j]) {
					min = array[j];
					minIndex = j;
				}
			}
			// 每经历一次内层for循环，minindex和min都会指向最小值
			// 如果minIdex发生了变化，说明找到最小值
			// 则和array[i]替换数值
			if (minIndex != i) {
				array[minIndex] = array[i];
				array[i] = min;
			}
		}
	}

}
