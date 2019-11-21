package Sort;

import java.util.Arrays;

public class RadixSort {
	public static void main(String[] args) {
		int[] array = { 10, 782, 35, -5, 7, 325 };
		radixSort(array);
		System.out.println(Arrays.toString(array));

	}

	public static void radixSort(int[] array) {
		// 获取最大数组中的最大位数
		int max = array[0];
		for (int i = 0; i < array.length; i++) {
			if (array[i] > max) {
				max = array[i];
			}
		}
		int maxlenth = (max + "").length();
		// 用二维数组创建十个桶，每个桶都是一个一维数组
		// bucketElementCount记录每个桶里面有多少个数据
		int[][] bucket = new int[10][array.length];
		int[] bucketElementCount = new int[10];
		for (int i = 0, n = 1; i < maxlenth; i++, n *= 10) {
			// 取出数组里面对应的位数，按照桶的序号依次放入桶内，第一次是个位，第二次是十位...
			for (int j = 0; j < array.length; j++) {
				int numb = array[j] / n % 10;
				// 将取出来的数放入对应的桶中
				bucket[numb][bucketElementCount[numb]] = array[j];
				// 将记录每个桶里面有多少个数据的值+1
				bucketElementCount[numb]++;
			}
			// 第二步，将每个桶里面的数据，按顺序取出放入到原数组array里面
			int index = 0;
			for (int k = 0; k < bucketElementCount.length; k++) {
				if (bucketElementCount[k] != 0) {
					for (int l = 0; l < bucketElementCount[k]; l++) {
						array[index++] = bucket[k][l];
					}
				}
				// 每进行一次数组放回，把记录桶内数据个数的数组bucketElementCount清空，否则会影响下一次的放回
				// 因为放回的内层逻辑通过bucketElementCount[k]，未清空的话可能会多放入
				bucketElementCount[k] = 0;
			}
		}
	}

}
