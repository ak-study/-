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
		// 设置障碍
		map[3][1] = 1;
		map[3][2] = 1;
		//遍历地图
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 7; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println("结果：");
		mg(map, 3, 3);
		//遍历已经找到出路后的地图
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 7; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}

	//1：墙，2：已经走过，3：此路不通，0：还未走过
	// map:地图，i,j,表示从地图的哪个位置作为起始点
	public static boolean mg(int[][] map, int i, int j) {
		// 设置终点为2，表示已经走过，如果小球走到这点，返回
		if (map[6][5] == 2) {
			return true;
			//
		} else {
			//如果该点还未走过，则先标记为2，并且开辟新栈查看下一点是否可走
			if (map[i][j] == 0) {
				map[i][j] = 2;
				//每个判读均调用自己查看下一点是否可走，如果可走则继续开辟栈空间，如果不可走
				//则往另一个方向判断改点是否可走
				if (mg(map, i + 1, j)) {//向下走
					return true;
				} else if (mg(map, i, j + 1)) {//向右走
					return true;
				} else if (mg(map, i - 1, j)) {//向上走
					return true;
				} else if (mg(map, i, j - 1)) {//向左走
					return true;
				} else {
					//如果上下左右均走不通，则标记为3，并且返回false
					map[i][j] = 3;
					return false;
				}
				//如果已经走过（可能为1，2，3）直接结束该栈空间，并且回溯到上一个栈空间
			} else {
				return false;
			}
		}
	}
}
