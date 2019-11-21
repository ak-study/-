package Algorithm;

import java.util.Arrays;

public class KruskalCase {
	char[] verxs;// �洢�����Ϣ������
	int[][] weight;// ͼ
	int Num;// ������
	final static int INF = Integer.MAX_VALUE;// int�����ֵ����ʾ���֮�䲻��ͨ

	public static void main(String[] args) {
		char[] verxs = { 'A', 'B', 'C', 'D', 'E', 'F', 'G' };
		// ��³˹�����㷨���ڽӾ���
		int weight[][] = {
				/* A *//* B *//* C *//* D *//* E *//* F *//* G */
				/* A */ { 0, 12, INF, INF, INF, 16, 14 }, /* B */ { 12, 0, 10, INF, INF, 7, INF },
				/* C */ { INF, 10, 0, 3, 5, 6, INF }, /* D */ { INF, INF, 3, 0, 4, INF, INF },
				/* E */ { INF, INF, 5, 4, 0, 2, 8 }, /* F */ { 16, 7, 6, INF, 2, 0, 9 },
				/* G */ { 14, INF, INF, INF, 8, 9, 0 } };
		KruskalCase kruskalCase = new KruskalCase(verxs, weight);
		kruskalCase.printf();
		System.out.println(Arrays.toString(kruskalCase.getEData(weight)));

	}

	public KruskalCase(char[] verxs, int[][] weight) {
		this.verxs = verxs;
		this.weight = weight;
		// ͳ����Ч�ߵĸ���
		for (int i = 0; i < verxs.length; i++) {
			for (int j = i + 1; j < verxs.length; j++) {
				if (weight[i][j] != INF) {
					Num++;
				}
			}
		}
	}

	public void printf() {
		for (int i = 0; i < verxs.length; i++) {
			System.out.printf("%12c", verxs[i]);
		}
		System.out.println();
		for (int i = 0; i < verxs.length; i++) {
			System.out.printf("%c", verxs[i]);
			for (int j = 0; j < verxs.length; j++) {
				System.out.printf("%12d", weight[i][j]);
			}
			System.out.println();
		}
	}

	// ��Edata�������weight����
	public void sort(EData[] edata) {
		for (int i = 0; i < verxs.length; i++) {
			for (int j = 0; j < verxs.length - i - 1; j++) {
				if (edata[j].weight > edata[j + 1].weight) {
					int temp = edata[j].weight;
					edata[j].weight = edata[j + 1].weight;
					edata[j + 1].weight = temp;
				}
			}
		}
	}
	/**
	 * ���ܣ�����һ����㣬���ؽ���±�
	 * @param c ����Ľ��
	 * @return ���ظý���±�,����Ҳ����򷵻�-1
	 */
	public int getIndex(char c) {
		for (int i = 0; i < verxs.length; i++) {
			if (c == verxs[i]) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * ���ܣ���ȡ��Ч�ߵ���Ϣ������EData����
	 * @param weight ͨ����ά����weight����ȡ
	 * @return ������Ч��EData������
	 */
	public EData[] getEData(int[][] weight) {
		EData eData[] = new EData[Num];
		int index = 0;
		for (int i = 0; i < verxs.length; i++) {
			for (int j = i + 1; j < verxs.length; j++) {
				if (weight[i][j] != INF) {
					eData[index] = new EData(verxs[i], verxs[j], weight[i][j]);
					index++;
				}
			}
		}
		return eData;
	}
	/**
	 * 
	 * @param ends ��¼���������Ӧ���յ���±�
	 * @param i ����Ķ����Ӧ���±�
	 * @return ���붥��i���յ��Ӧ���±�
	 */
	public int getends(int[] ends,int i) {
		while(ends[i]!=0) {
			i=ends[i];
		}
		return i;
	}

}

class EData {
	char start;//��ʼ��
	char end;//�յ��
	int weight;//Ȩֵ

	public EData(char start, char end, int weight) {
		this.start = start;
		this.end = end;
		this.weight = weight;
	}

	@Override
	public String toString() {
		return "EData [start=" + start + ", end=" + end + ", weight=" + weight + "]";
	}

}
