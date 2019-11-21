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
	 * @param s1 Դ�ַ���
	 * @param s2 ���ַ���
	 * @param next ���ַ�����Ӧ��kmp����
	 * @return s2��s1��������ֵ�λ��
	 */
	public static int kmpSearch(String s1, String s2, int[] next) {
		// iָ��s1��jָ��s2
		for (int i = 0, j = 0; i < s1.length(); i++) {
			// ���iָ����ַ���jָ����ַ���һ��
			while (j > 0 && s1.charAt(i) != s2.charAt(j)) {
				j = next[j - 1];
			}
			// ���iָ����ַ���jָ����ַ�һ������j���ƣ�Ȼ��i����
			if (s1.charAt(i) == s2.charAt(j)) {
				j++;
			}
			// ���j�ĳ��ȵ���s2�ĳ��ȣ���˵��j�ƶ������ֱ�ӷ���
			if (j == s2.length()) {
				return i - j + 1;
			}
		}
		return -1;
	}
	/**
	 * ���ܣ��ô�����ַ������鴴��һ����Ӧ��kmp����
	 * @param string ������ַ����Ӵ�
	 * @return ���ַ����Ӵ���Ӧ��kmp����
	 */
	public static int[] KMPnext(String string) {
		int[] next = new int[string.length()];
		// �ֶ���next����ĵ�һ��λ����λ0����Ϊ���ֻ��һ���ַ�����ǰ׺��׺��Ϊ0
		next[0] = 0;
		for (int i = 1, j = 0; i < string.length(); i++) {
			// ���iָ����ַ���jָ����ַ������
			while (j > 0 && string.charAt(i) != string.charAt(j)) {
				// kmp�����㷨����ס����
				j = next[j - 1];
			}
			// ���iָ����ַ���jָ����ַ���ȣ���j���ƣ�Ȼ��j���±����next���飬Ȼ��i����
			if (string.charAt(i) == string.charAt(j)) {
				j++;
			}
			next[i] = j;
		}
		return next;
	}
}
