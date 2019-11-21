package Sort;

import java.util.Arrays;

public class RadixSort {
	public static void main(String[] args) {
		int[] array = { 10, 782, 35, -5, 7, 325 };
		radixSort(array);
		System.out.println(Arrays.toString(array));

	}

	public static void radixSort(int[] array) {
		// ��ȡ��������е����λ��
		int max = array[0];
		for (int i = 0; i < array.length; i++) {
			if (array[i] > max) {
				max = array[i];
			}
		}
		int maxlenth = (max + "").length();
		// �ö�ά���鴴��ʮ��Ͱ��ÿ��Ͱ����һ��һά����
		// bucketElementCount��¼ÿ��Ͱ�����ж��ٸ�����
		int[][] bucket = new int[10][array.length];
		int[] bucketElementCount = new int[10];
		for (int i = 0, n = 1; i < maxlenth; i++, n *= 10) {
			// ȡ�����������Ӧ��λ��������Ͱ��������η���Ͱ�ڣ���һ���Ǹ�λ���ڶ�����ʮλ...
			for (int j = 0; j < array.length; j++) {
				int numb = array[j] / n % 10;
				// ��ȡ�������������Ӧ��Ͱ��
				bucket[numb][bucketElementCount[numb]] = array[j];
				// ����¼ÿ��Ͱ�����ж��ٸ����ݵ�ֵ+1
				bucketElementCount[numb]++;
			}
			// �ڶ�������ÿ��Ͱ��������ݣ���˳��ȡ�����뵽ԭ����array����
			int index = 0;
			for (int k = 0; k < bucketElementCount.length; k++) {
				if (bucketElementCount[k] != 0) {
					for (int l = 0; l < bucketElementCount[k]; l++) {
						array[index++] = bucket[k][l];
					}
				}
				// ÿ����һ������Żأ��Ѽ�¼Ͱ�����ݸ���������bucketElementCount��գ������Ӱ����һ�εķŻ�
				// ��Ϊ�Żص��ڲ��߼�ͨ��bucketElementCount[k]��δ��յĻ����ܻ�����
				bucketElementCount[k] = 0;
			}
		}
	}

}
