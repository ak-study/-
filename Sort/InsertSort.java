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
		System.out.println("����ǰ�� " + date1str);
		insertSort(array);
		Date date2 = new Date();
		String date2str = simpleDateFormat.format(date2);
		System.out.println("����� " + date2str);
	}

	public static void insertSort(int[] array) {
		// ���ѭ�������С-1��
		for (int i = 1; i < array.length; i++) {
			// ��¼��i����ֵ�͵�i-1�����±꣬ÿ����������������һ�����бȽ�
			int val = array[i];
			int preindex = i - 1;
			// ����i��ֵ��iǰ�������ֵ�Ƚϣ������һ����ʼ��ǰ�Ƚϣ���
			// �����ǰһ��С����ǰһ��ֵ����һλ
			// ֱ����i��ֵ��ǰһ��ֵ���ұȺ�һ��ֵС
			// ����preindexС��0��������preindex�Ѿ�ָ���һ����ֵ�������˳�ѭ�����ڽ�i��ֵ��ֵ
			// �����i��ֵ�����ǰ���ǰ������ֵ�����ģ���ֱ�������жϣ����Լ���ֵ���Լ�
			while (preindex >= 0 && val < array[preindex]) {
				array[preindex + 1] = array[preindex];
				preindex--;// ��������ѭ��
			}
			// ÿ����һ��whileѭ����iǰ�����е���ֵ������һ����������
			// preindex+1��i 
			array[preindex + 1] = val;
		}
	}

}
