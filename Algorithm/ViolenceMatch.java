package Algorithm;

public class ViolenceMatch {

	public static void main(String[] args) {
		String s1="�����κ����۵����ݿ���ܴ�Ƽ�";
		String s2="���۵�����-";
		int index=violenceMatch(s1, s2);
		System.out.println(index);

	}
	public static int violenceMatch(String s1,String s2) {
		char[] c1=s1.toCharArray();
		char[] c2=s2.toCharArray();
		int i=0;//ָ��c1����
		int j=0;//ָ��c2����
		while(i<c1.length&&j<c2.length) {
			//���ƥ��ɹ�������ָ��i��ָ��j�����ƶ�
			if(c1[i]==c2[j]) {
				i++;
				j++;
				//���ƥ�䲻�ɹ�������i����һλ������j��λ0
				//ע�⣬�����i-j+1��Ч��������i����һλ
			}else {
				i=i-j+1;
				j=0;
			}
		}
		//�����s1���ҵ�ƥ����ַ�������j�ĳ���Ӧ�õ���c2�ĳ��ȣ���Ϊj�����������δ��0��˵���ƶ��������
		if(j==c2.length) {
			return i-j;
		}else {
			//���ɹ�����-1
			return -1;
		}
	}

}
