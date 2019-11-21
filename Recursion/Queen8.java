package Recursion;

public class Queen8 {
	//用一维数组表示八皇后的坐标，数组下标代表皇后位于哪一行，数组下标的值代表皇后位于哪一列
	//例如：array=｛0，4，7，5，2，6，1，3｝中
	//array[7]代表该皇后位于第三行第八个位置，坐标为（3，8）
	static int max = 8;
	static int[] array = new int[max];
	static int count=0;

	public static void main(String[] args) {
		check(0);
		System.out.printf("一共有%d种解法",count);
	}
	// n代表要摆放的皇后的下标
	public static void check(int n) {
		//如果n=max，代表八个皇后已经摆完，直接输出返回
		if(n==max) {
			print();
			return;
		}else {
			//循环将传进来的皇后在第一列到第八列摆放一次
			for(int i=0;i<max;i++) {
				array[n]=i;
				//如果判断下标为n的皇后和前面皇后的位置不冲突，则n+1判断下一行皇后的位置
				//如果判断冲突，则跳过if结构，i+1，将皇后移向下一列
				if(judge(n)) {
					check(n+1);
				}
			}
		}
	}

	// n代表要摆放的皇后的下标
	// 该方法用于检查下标为n的皇后前面的皇后位置是否和该皇后冲突
	// 冲突条件1.位于同一行2.位于同一对角线
	public static boolean judge(int n) {
		for (int i = 0; i < n; i++) {
			//array[i] == array[n]表示下标为n的皇后与前面的皇后位于同一列
			//Math.abs(n - i) == Math.abs(array[n] - array[i]判断坐标的差值
			//如果坐标的差值相同，则代表下标为n的皇后与前面的皇后位于同一对角线
			if (array[i] == array[n] || Math.abs(n - i) == Math.abs(array[n] - array[i])) {
				return false;
			}
		}
		return true;
	}

	// 输出八皇后的摆放位置
	public static void print() {
		count++;
		for (int i = 0; i < max; i++) {
			System.out.print(array[i] + " ");
		}
		System.out.println();
	}

}
