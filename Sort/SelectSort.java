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
		System.out.println("����ǰ�� " + date1str);
		Select2(array);
		Date date2 = new Date();
		String date2str = simpleDateFormat.format(date2);
		System.out.println("����� " + date2str);
	}

	public static void Select(int[] array) {
		int temp;
		boolean flag = false;
		// ���ѭ�������С-1��
		for (int i = 0; i < array.length - 1; i++) {
			// �ڲ�ѭ�������С-1-i�Σ�ÿ�����򶼻�ȷ��һ����Сֵ������ÿ��������������1��
			for (int j = 0; j < array.length - 1 - i; j++) {
				// �����ǰ��ֵ�Ⱥ���ĳһ��ֵ���򽻻�
				if (array[i] > array[i + j + 1]) {
					flag = true;
					temp = array[i];
					array[i] = array[i + j + 1];
					array[i + j + 1] = temp;
				}
			}
			// ���һ����������һ�ζ�û�з������������˳����ѭ��
			if (!flag) {
				break;
			} else {
				flag = false;// ��������˽�����������flag�������´�����
			}
		}
	}

	public static void Select2(int[] array) {
		for (int i = 0; i < array.length - 1; i++) {
			// �ȼٶ���i��Ϊ��Сֵ�����¸�ָ�������±����ֵ
			int minIndex = i;
			int min = array[i];
			for (int j = i + 1; j < array.length; j++) {
				// ����ҵ��ȼٶ�ֵС��ֵ�����滻�ٶ�ֵmin�����±�minIndex
				if (min > array[j]) {
					min = array[j];
					minIndex = j;
				}
			}
			// ÿ����һ���ڲ�forѭ����minindex��min����ָ����Сֵ
			// ���minIdex�����˱仯��˵���ҵ���Сֵ
			// ���array[i]�滻��ֵ
			if (minIndex != i) {
				array[minIndex] = array[i];
				array[i] = min;
			}
		}
	}

}
