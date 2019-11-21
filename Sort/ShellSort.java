package Sort;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ShellSort {

	public static void main(String[] args) {
		int[] array = new int[80000];
		for (int i = 0; i < 80000; i++) {
			array[i] = (int) (Math.random() * 8000000);
		}
		Date date1 = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date1str = simpleDateFormat.format(date1);
		System.out.println("排序前： " + date1str);
		shellSort2(array);
		Date date2 = new Date();
		String date2str = simpleDateFormat.format(date2);
		System.out.println("排序后： " + date2str);

	}

	public static void shellSort(int[] array) {
		int temp = 0;
		// 第一步，分组，每组长度为数组长度除以2
		for (int gap = array.length / 2; gap > 0; gap /= 2) {
			// 内层将每次的分组都进行一次插入排序
			for (int i = gap; i < array.length; i++) {
				for (int j = i - gap; j >= 0; j -= gap) {
					if (array[j] > array[j + gap]) {
						temp = array[j];
						array[j] = array[j + gap];
						array[j + gap] = temp;
					}
				}
			}
		}
	}

	public static void shellSort2(int[] array) {
		int val = 0;
		int preindex = 0;
		//外层将增量分组，每次长度除以二
		for (int gap = array.length / 2; gap > 0; gap /= 2) {
			//内层通过插入算法对每次的分组进行排序
			for (int i = gap; i < array.length; i++) {
				preindex= i-gap;
				val = array[i];
				while (preindex >= 0 && val<array[preindex]) {
						array[preindex+gap] = array[preindex];
						preindex-=gap;
				}
				array[preindex+gap]=val;
			}
			
		}
	}

}
