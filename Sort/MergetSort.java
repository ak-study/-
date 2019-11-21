package Sort;

import java.util.Arrays;

public class MergetSort {

	public static void main(String[] args) {
		int[] array = { 4, 2, 1, 3, 6, 45, 78, 9  };
		int[] temp = new int[array.length];
		mergetSort(array, 0, array.length - 1, temp);
		System.out.println(Arrays.toString(array));
	}

	// �ֽ�����+�ϲ�����
	public static void mergetSort(int[] array, int left, int right, int[] temp) {
		if (left < right) {
			int mid = (left + right) / 2;
			// ����߷ֽ�
			mergetSort(array, left, mid, temp);
			// ���ұ߷ֽ�
			mergetSort(array, mid + 1, right, temp);
			
			merget(array, left, mid, right, temp);
		}
	}

	public static void merget(int[] array, int left, int mid, int right, int[] temp) {
		int i = left;// ָ��ֽ�� �������������
		int j = mid + 1;// ָ��ֽ�� �ұ�����������
		int t = 0;// ָ��������temp�ĵ�һ��λ��

		// ��һ�����ȽϷֽ�����������Ĵ�С����С��һ���ŵ�temp�������棬Ȼ��ǰָ������
		// ���ѭ����ʾ��������������ʱ�����������ұ����鶼û�������
		while (i <= mid && j <= right) {
			// iС��j����iָ���ֵ�ŵ�temp���棬 Ȼ��t��i����
			if (array[i] < array[j]) {
				temp[t] = array[i];
				i++;
				t++;
			} else {
				temp[t] = array[j];
				j++;
				t++;
			}
		}
		// �˳���һ��whileѭ�����������������ұ������Ѿ�������ɣ���ʼ�ڶ���
		// �ڶ����������ұ�������ʣ��ֵ���η���temp����
		// ���i<mid���������û�б����꣬����ʣ��
		while (i <= mid) {
			temp[t] = array[i];
			i++;
			t++;
		}
		// ���j<right�������ұ�û�б����꣬����ʣ��
		while (j <= right) {
			temp[t] = array[j];
			j++;
			t++;
		}
		// ����������temp�����ڵ�ֵ����ԭ���±꣬����ԭ����array����
		// ע�Ⲣ����ÿ�ζ���ȫ��������ȥ
		t = 0;
		int templeft = left;
		System.out.println(templeft + "" + right);
		while (templeft <= right) {
			array[templeft] = temp[t];
			t++;
			templeft++;
		}
	}
}
