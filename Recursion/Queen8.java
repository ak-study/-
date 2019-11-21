package Recursion;

public class Queen8 {
	//��һά�����ʾ�˻ʺ�����꣬�����±����ʺ�λ����һ�У������±��ֵ����ʺ�λ����һ��
	//���磺array=��0��4��7��5��2��6��1��3����
	//array[7]����ûʺ�λ�ڵ����еڰ˸�λ�ã�����Ϊ��3��8��
	static int max = 8;
	static int[] array = new int[max];
	static int count=0;

	public static void main(String[] args) {
		check(0);
		System.out.printf("һ����%d�ֽⷨ",count);
	}
	// n����Ҫ�ڷŵĻʺ���±�
	public static void check(int n) {
		//���n=max������˸��ʺ��Ѿ����ֱ꣬���������
		if(n==max) {
			print();
			return;
		}else {
			//ѭ�����������Ļʺ��ڵ�һ�е��ڰ��аڷ�һ��
			for(int i=0;i<max;i++) {
				array[n]=i;
				//����ж��±�Ϊn�Ļʺ��ǰ��ʺ��λ�ò���ͻ����n+1�ж���һ�лʺ��λ��
				//����жϳ�ͻ��������if�ṹ��i+1�����ʺ�������һ��
				if(judge(n)) {
					check(n+1);
				}
			}
		}
	}

	// n����Ҫ�ڷŵĻʺ���±�
	// �÷������ڼ���±�Ϊn�Ļʺ�ǰ��Ļʺ�λ���Ƿ�͸ûʺ��ͻ
	// ��ͻ����1.λ��ͬһ��2.λ��ͬһ�Խ���
	public static boolean judge(int n) {
		for (int i = 0; i < n; i++) {
			//array[i] == array[n]��ʾ�±�Ϊn�Ļʺ���ǰ��Ļʺ�λ��ͬһ��
			//Math.abs(n - i) == Math.abs(array[n] - array[i]�ж�����Ĳ�ֵ
			//�������Ĳ�ֵ��ͬ��������±�Ϊn�Ļʺ���ǰ��Ļʺ�λ��ͬһ�Խ���
			if (array[i] == array[n] || Math.abs(n - i) == Math.abs(array[n] - array[i])) {
				return false;
			}
		}
		return true;
	}

	// ����˻ʺ�İڷ�λ��
	public static void print() {
		count++;
		for (int i = 0; i < max; i++) {
			System.out.print(array[i] + " ");
		}
		System.out.println();
	}

}
