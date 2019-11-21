package Algorithm;

public class KnapsackProblem {
	public static void main(String[] args) {
		int val[] = { 1500,3000,2000};
		int weight[] = { 1,4,3 };
		int m = 4;// ��������
		int n = val.length;// ��Ʒ����

		// ������ά���飬������,������+1�����õ�һ�е�һ��Ϊ0������������
		int[][] v = new int[n + 1][m + 1];
		//����һ��path���飬������¼��Ʒ�ķ���
		int[][]path= new int[n + 1][m + 1];
		// ��ʼ����һ��Ϊ0
		for (int i = 0; i < v.length; i++) {
			v[i][0] = 0;
		}
		// ��ʼ����һ��Ϊ0
		for (int i = 0; i < v[0].length; i++) {
			v[0][i] = 0;
		}
		for (int i = 1; i < v.length; i++) {
			for (int j = 1; j < v[0].length; j++) {
				// ����������Ʒ���������ڵ�ǰ��������ʹ����һ���������䵱ǰ���
				if (weight[i - 1] > j) {
					v[i][j] = v[i - 1][j];
				} else {
					// ����Ƚ�����һ����Ʒ��ֵ��󣬻��ǵ�ǰ��Ʒ�ļ�ֵ+ʣ���������ܹ�װ�����Ʒ�ļ�ֵ���
					// v[i - 1][j - weight[i-1]����������-��ǰ��Ʒ����֮�󣬶�Ӧ����Ʒ�ļ�ֵ
					// val[i-1]����ǰ��Ʒ�ļ�ֵ
					if (v[i - 1][j] < val[i - 1] + v[i - 1][j - weight[i - 1]]) {
						v[i][j] = val[i - 1] + v[i - 1][j - weight[i - 1]];
						path[i][j]=1;
					} else {
						v[i][j] = v[i - 1][j];
					}
				}
			}
		}

		for (int i = 0; i < v.length; i++) {
			for (int j = 0; j < v[0].length; j++) {
				System.out.print(v[i][j] + " \t");
			}
			System.out.println();
		}
		System.out.println("=============================");
		for (int i = 0; i < v.length; i++) {
			for (int j = 0; j < v[0].length; j++) {
				if(path[i][j]==1) {
				System.out.print(i);
				}
			}
			System.out.println();
		}

	}
}
