package Search;

import java.util.ArrayList;

public class InserValueSearch {

	public static void main(String[] args) {
		int[] array = { 1, 2, 2, 4, 5, 6, 7, 8 };
		ArrayList<Integer> list = inserValueSearch(array, 0, array.length - 1, 1);
		System.out.println("�±�Ϊ�� " + list);

	}

	public static ArrayList<Integer> inserValueSearch(int[] array, int left, int right, int findVal) {
		if (left > right || findVal < array[0] || findVal > array[array.length-1]) {
			return new ArrayList<Integer>();
		}
		// ��ֵ�����㷨
		int mid = left + (right - left) * (findVal - array[left]) / (array[right] - array[left]);
		int midval = array[mid];
		if (findVal > midval) {
			// ���ұߵݹ�
			return inserValueSearch(array, mid + 1, right, findVal);
		} else if (findVal < midval) {
			// ����ߵݹ�
			return inserValueSearch(array, left, mid - 1, findVal);
		} else {
			// ��mid���õ��ڲ��ҵ���findVal������tempָ�룬ָ��mid ��/�� �߲���
			// ���temp С��0/����length-1 ���߲����ڲ���ֵfindVal�����˳������򲻶�������
			int temp = mid - 1;
			ArrayList<Integer> list = new ArrayList<Integer>();
			while (true) {
				if (temp < 0 || array[temp] != findVal) {
					break;
				}
				// û�ҵ�һ��ֵ�ͷ��뼯��
				list.add(temp);
				temp--;
			}
			// ���м��ֵ���뼯��
			list.add(mid);
			temp = mid + 1;
			while (true) {
				if (temp > array.length - 1 || array[temp] != findVal) {
					break;
				}
				list.add(temp);
				temp++;
			}
			// ���ؼ���
			return list;
		}
	}
}
