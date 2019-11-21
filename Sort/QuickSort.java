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
//		System.out.println("����ǰ�� " + date1str);
//		quickSort(array, 0, array.length - 1);
//		Date date2 = new Date();
//		String date2str = simpleDateFormat.format(date2);
//		System.out.println("����� " + date2str);
		
		 int[] array = { 5, 7, 0, 9, 10 }; quickSort(array, 0, array.length - 1);
		 System.out.println(Arrays.toString(array));
		 
	}

	public static void quickSort(int[] array, int left, int right) {
		int t;
		// ���left����right˵���ò��ֵ������Ѿ����
		if (left > right) {
			return;
		}
		int l = left;
		int r = right;
		// ��������ߵ���Ϊ��׼λ
		int temp = array[left];
		// ��l==r˵��һ��̽���Ѿ��������������ѭ��
		while (l < r) {
			// һ��Ҫ��ɨ���ұߵ��������������ڻ�׼���������Ƽ����һλ
			while (array[r] >= temp && l < r) {
				r--;
			}
			// ע�ⲻҪ�ٵ��Ⱥţ���Ϊ��ߵ�ָ���leftλ�ó�������ô��һ��ֵ�϶���temp���
			// ɨ����ߵ������������С�ڻ�׼���������Ƽ����һλ
			while (array[l] <= temp && l < r) {
				l++;
			}
			if (l < r) {
				t = array[r];
				array[r] = array[l];
				array[l] = t;
			}
		}
		// ������ߵ�����l��r������λ���໻
		array[left] = array[r];
		array[r] = temp;
		// ����ߵݹ�
		quickSort(array, left, l - 1);
		// ���ұߵݹ�
		quickSort(array, l + 1, right);
	}
}
