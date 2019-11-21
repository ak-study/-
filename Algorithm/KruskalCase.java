package Algorithm;

import java.util.Arrays;

public class KruskalCase {
	char[] verxs;// 存储结点信息的数组
	int[][] weight;// 图
	int Num;// 结点个数
	final static int INF = Integer.MAX_VALUE;// int的最大值，表示结点之间不连通

	public static void main(String[] args) {
		char[] verxs = { 'A', 'B', 'C', 'D', 'E', 'F', 'G' };
		// 克鲁斯卡尔算法的邻接矩阵
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
		// 统计有效边的个数
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

	// 对Edata数组根据weight排序
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
	 * 功能：传入一个结点，返回结点下标
	 * @param c 传入的结点
	 * @return 返回该结点下标,如果找不到则返回-1
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
	 * 功能：获取有效边的信息，放入EData数组
	 * @param weight 通过二维数组weight来获取
	 * @return 返回有效边EData的数组
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
	 * @param ends 记录各个顶点对应的终点的下标
	 * @param i 传入的顶点对应的下标
	 * @return 传入顶点i的终点对应的下标
	 */
	public int getends(int[] ends,int i) {
		while(ends[i]!=0) {
			i=ends[i];
		}
		return i;
	}

}

class EData {
	char start;//起始边
	char end;//终点边
	int weight;//权值

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
