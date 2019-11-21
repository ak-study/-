package Algorithm;

public class KMPAlgorithm {

	public static void main(String[] args) {
		String s1= "BBCADSAD";
		String s2="CADS";
		int[] next=KMPnext(s2);
		int index=kmpSearch(s1, s2, next);
		System.out.println(index);

	}
	/**
	 * 
	 * @param s1 源字符串
	 * @param s2 子字符串
	 * @param next 子字符串对应的kmp数组
	 * @return s2在s1中最早出现的位置
	 */
	public static int kmpSearch(String s1, String s2, int[] next) {
		// i指向s1，j指向s2
		for (int i = 0, j = 0; i < s1.length(); i++) {
			// 如果i指向的字符和j指向的字符不一样
			while (j > 0 && s1.charAt(i) != s2.charAt(j)) {
				j = next[j - 1];
			}
			// 如果i指向的字符和j指向的字符一样，则将j后移，然后i后移
			if (s1.charAt(i) == s2.charAt(j)) {
				j++;
			}
			// 如果j的长度等于s2的长度，则说明j移动到最后，直接返回
			if (j == s2.length()) {
				return i - j + 1;
			}
		}
		return -1;
	}
	/**
	 * 功能：用传入的字符串数组创建一个对应的kmp数组
	 * @param string 传入的字符串子串
	 * @return 该字符串子串对应的kmp数组
	 */
	public static int[] KMPnext(String string) {
		int[] next = new int[string.length()];
		// 手动将next数组的第一个位置置位0，因为如果只有一个字符，则前缀后缀都为0
		next[0] = 0;
		for (int i = 1, j = 0; i < string.length(); i++) {
			// 如果i指向的字符和j指向的字符不相等
			while (j > 0 && string.charAt(i) != string.charAt(j)) {
				// kmp核心算法，记住就行
				j = next[j - 1];
			}
			// 如果i指向的字符和j指向的字符相等，则j后移，然后将j的下标存入next数组，然后i后移
			if (string.charAt(i) == string.charAt(j)) {
				j++;
			}
			next[i] = j;
		}
		return next;
	}
}
