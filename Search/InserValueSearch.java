package Search;

import java.util.ArrayList;

public class InserValueSearch {

	public static void main(String[] args) {
		int[] array = { 1, 2, 2, 4, 5, 6, 7, 8 };
		ArrayList<Integer> list = inserValueSearch(array, 0, array.length - 1, 1);
		System.out.println("下标为： " + list);

	}

	public static ArrayList<Integer> inserValueSearch(int[] array, int left, int right, int findVal) {
		if (left > right || findVal < array[0] || findVal > array[array.length-1]) {
			return new ArrayList<Integer>();
		}
		// 差值查找算法
		int mid = left + (right - left) * (findVal - array[left]) / (array[right] - array[left]);
		int midval = array[mid];
		if (findVal > midval) {
			// 向右边递归
			return inserValueSearch(array, mid + 1, right, findVal);
		} else if (findVal < midval) {
			// 向左边递归
			return inserValueSearch(array, left, mid - 1, findVal);
		} else {
			// 当mid正好等于查找的数findVal，声明temp指针，指向mid 左/右 边查找
			// 如果temp 小于0/大于length-1 或者不等于查找值findVal，则退出，否则不断往下移
			int temp = mid - 1;
			ArrayList<Integer> list = new ArrayList<Integer>();
			while (true) {
				if (temp < 0 || array[temp] != findVal) {
					break;
				}
				// 没找到一个值就放入集合
				list.add(temp);
				temp--;
			}
			// 将中间的值放入集合
			list.add(mid);
			temp = mid + 1;
			while (true) {
				if (temp > array.length - 1 || array[temp] != findVal) {
					break;
				}
				list.add(temp);
				temp++;
			}
			// 返回集合
			return list;
		}
	}
}
