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
		System.out.println("����ǰ�� " + date1str);
		shellSort2(array);
		Date date2 = new Date();
		String date2str = simpleDateFormat.format(date2);
		System.out.println("����� " + date2str);

	}

	public static void shellSort(int[] array) {
		int temp = 0;
		// ��һ�������飬ÿ�鳤��Ϊ���鳤�ȳ���2
		for (int gap = array.length / 2; gap > 0; gap /= 2) {
			// �ڲ㽫ÿ�εķ��鶼����һ�β�������
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
		//��㽫�������飬ÿ�γ��ȳ��Զ�
		for (int gap = array.length / 2; gap > 0; gap /= 2) {
			//�ڲ�ͨ�������㷨��ÿ�εķ����������
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
