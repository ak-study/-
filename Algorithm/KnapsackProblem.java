package Algorithm;

public class KnapsackProblem {
	public static void main(String[] args) {
		int val[] = { 1500,3000,2000};
		int weight[] = { 1,4,3 };
		int m = 4;// 背包容量
		int n = val.length;// 物品个数

		// 创建二维数组，代表表格,容量各+1，设置第一行第一列为0，方便后面计算
		int[][] v = new int[n + 1][m + 1];
		//创建一个path数组，用来记录物品的放入
		int[][]path= new int[n + 1][m + 1];
		// 初始化第一列为0
		for (int i = 0; i < v.length; i++) {
			v[i][0] = 0;
		}
		// 初始化第一行为0
		for (int i = 0; i < v[0].length; i++) {
			v[0][i] = 0;
		}
		for (int i = 1; i < v.length; i++) {
			for (int j = 1; j < v[0].length; j++) {
				// 如果放入的物品的重量大于当前重量，则使用上一格的数据填充当前表格
				if (weight[i - 1] > j) {
					v[i][j] = v[i - 1][j];
				} else {
					// 否则比较是上一个物品价值最大，还是当前物品的价值+剩余容量所能够装入的物品的价值最大
					// v[i - 1][j - weight[i-1]代表总容量-当前物品容量之后，对应的物品的价值
					// val[i-1]代表当前物品的价值
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
