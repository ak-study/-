package Algorithm;

public class Hanoitower {

	public static void main(String[] args) {
		hannoitower(30, 'A','B', 'C');

	}

	public static void hannoitower(int num, char a, char b, char c) {
		if (num == 1) {
			System.out.println("把第1个盘从: " + a + " -> " + c);
		} else {
			//分治思路，永远看成只有两个盘，既上面的所有盘和最底下的一个盘
			//第一步，把上面的盘从a移动到b
			hannoitower(num - 1, a, c, b);
			//第二步，把最下面的盘从a移动到c
			System.out.println("把第" + num + "个盘从: " + a + " -> " + c);
			//第三步，把上面的盘从b移动到c
			hannoitower(num - 1, b, a, c);
		}
	}

}
