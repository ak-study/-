package Sort;

import java.util.Arrays;

public class HeapSort {

	public static void main(String[] args) {
		int arr[] = { 4, 9, 52, 34, 12 };
		heapSort(arr);
		System.out.println(Arrays.toString(arr));

	}

	public static void heapSort(int[] arr) {
		int temp = 0;
		// �Ƚ����������һ����
		for (int i = arr.length / 2 - 1; i >= 0; i--) {
			adjust(arr, i, arr.length);
		}
		// ÿ�ζ�����0��ֵ�滻��������������ֵ��Ȼ��ŵ������ĩβ
		// �´μ������ֵʱ���ͽ���һ�ε����ֵȥ��
		for (int j = arr.length - 1; j > 0; j--) {
			temp = arr[j];
			arr[j] = arr[0];
			arr[0] = temp;
			adjust(arr, 0, j);
		}
	}

	/**
	 * ���ܣ�ÿ�ζ��Ὣiָ���ֵ��ɴ�������������������ֵ
	 * 
	 * @param arr
	 *            ������������
	 * @param i
	 *            ������ķ�Ҷ�ӽ��
	 * @param length
	 *            �������ĳ��ȣ�ÿ�γ����лᷢ���仯����Ϊ������ήÿ�ε����ֵ�������
	 */
	public static void adjust(int[] arr, int i, int length) {
		int temp = arr[i];
		for (int k = i * 2 + 1; k < length; k = k * 2 + 1) {
			if (k + 1 < length && arr[k] < arr[k + 1]) {
				// kָ����Ǹ÷�Ҷ�ӽ������㣨i*2+1������k+1ָ��÷�Ҷ�ӽ����ҽ�㣨i*2+1+1��
				k++;// ���������ҽ��С����kָ���ҽ��
			}
			// ��һ��if�ṹ������kһ��ָ���Ҷ�ӽ��i������ӽ��
			if (arr[k] > temp) {
				arr[i] = arr[k];// �����Ľ�㸳ֵ���÷�Ҷ�ӽ�㣨��k�ĸ���㣩,��ʱk��λ�ú�i��λ�ã�ֵһ��
				i = k;// ���ҽ�iָ��k������ѭ�����Һ��������
			} else {
				break;// ����ӽ�㲻�ȸ�������ֱ���˳�
			}
		}
		arr[i] = temp;
	}

}
