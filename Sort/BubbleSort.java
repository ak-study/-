package Sort;

import java.text.SimpleDateFormat;
import java.util.Date;

public class BubbleSort {

	// ð������ʱ�临�Ӷȣ�O��n^2����
	public static void main(String[] args) {
		int[] array = new int[80000];
		for (int i = 0; i < 80000; i++) {
			array[i] = (int) (Math.random() * 80000);
		}
		Date date1 = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date1str=simpleDateFormat.format(date1);
		System.out.println("����ǰ�� " + date1str);
		BobbleSort(array);
		Date date2 = new Date();
		String date2str=simpleDateFormat.format(date2);
		System.out.println("����� " + date2str);

	}

	public static void BobbleSort(int[] array) {
		int temp;
		boolean flag = false;
		// ���ѭ�������С-1��
		for (int i = 0; i < array.length - 1; i++) {
			// �ڲ�ѭ�������С-1-i�Σ�ÿ�����򶼻�ȷ��һ�����ֵ������ÿ��������������1��
			for (int j = 0; j < array.length - 1 - i; j++) {
				// �����ǰ��ֵ�Ⱥ�һ��ֵ���򽻻�
				if (array[j] > array[j + 1]) {
					flag = true;
					temp = array[j];
					array[j] = array[j + 1];
					array[j + 1] = temp;
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

}
