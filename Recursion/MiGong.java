package Recursion;

public class MiGong {

	public static void main(String[] args) {
		int[][] map = new int[8][7];
		for (int i = 0; i < 7; i++) {
			map[0][i] = 1;
			map[7][i] = 1;
		}
		for (int i = 0; i < 8; i++) {
			map[i][0] = 1;
			map[i][6] = 1;
		}
		// �����ϰ�
		map[3][1] = 1;
		map[3][2] = 1;
		//������ͼ
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 7; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println("�����");
		mg(map, 3, 3);
		//�����Ѿ��ҵ���·��ĵ�ͼ
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 7; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}

	//1��ǽ��2���Ѿ��߹���3����·��ͨ��0����δ�߹�
	// map:��ͼ��i,j,��ʾ�ӵ�ͼ���ĸ�λ����Ϊ��ʼ��
	public static boolean mg(int[][] map, int i, int j) {
		// �����յ�Ϊ2����ʾ�Ѿ��߹������С���ߵ���㣬����
		if (map[6][5] == 2) {
			return true;
			//
		} else {
			//����õ㻹δ�߹������ȱ��Ϊ2�����ҿ�����ջ�鿴��һ���Ƿ����
			if (map[i][j] == 0) {
				map[i][j] = 2;
				//ÿ���ж��������Լ��鿴��һ���Ƿ���ߣ�����������������ջ�ռ䣬���������
				//������һ�������жϸĵ��Ƿ����
				if (mg(map, i + 1, j)) {//������
					return true;
				} else if (mg(map, i, j + 1)) {//������
					return true;
				} else if (mg(map, i - 1, j)) {//������
					return true;
				} else if (mg(map, i, j - 1)) {//������
					return true;
				} else {
					//����������Ҿ��߲�ͨ������Ϊ3�����ҷ���false
					map[i][j] = 3;
					return false;
				}
				//����Ѿ��߹�������Ϊ1��2��3��ֱ�ӽ�����ջ�ռ䣬���һ��ݵ���һ��ջ�ռ�
			} else {
				return false;
			}
		}
	}
}
