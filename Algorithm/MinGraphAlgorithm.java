package Algorithm;

public class MinGraphAlgorithm {

	public static void main(String[] args) {
		//测试看看图是否创建ok
				char[] data = new char[]{'A','B','C','D','E','F','G'};
				int verxs = data.length;
				//邻接矩阵的关系使用二维数组表示,10000这个大数，表示两个点不联通
				int [][]weight=new int[][]{
		            {10000,5,7,10000,10000,10000,2},
		            {5,10000,10000,9,10000,10000,3},
		            {7,10000,10000,10000,8,10000,10000},
		            {10000,9,10000,10000,10000,4,10000},
		            {10000,10000,8,10000,10000,5,4},
		            {10000,10000,10000,4,5,10000,6},
		            {2,3,10000,10000,4,6,10000},};
		            
		        //创建MGraph对象
		        Graph graph = new Graph(verxs);
		        //创建一个MinTree对象
		        MinGraph minTree = new MinGraph();
		        minTree.CreateGraph(graph, data, verxs, weight);
		        //输出
		        minTree.showGraph(data, verxs, weight);
		        minTree.prim(graph, 0);
	}

}

class MinGraph {
	public void CreateGraph(Graph graph, char[] verxs, int Num, int[][] weight) {
		for (int i = 0; i < Num; i++) {
			graph.verxs[i] = verxs[i];
			for (int j = 0; j < Num; j++) {
				graph.weight[i][j] = weight[i][j];
			}
		}
	}
	/**
	 * 
	 * @param graph 图
	 * @param start 表示从第几个结点开始寻找
	 */
	public void prim(Graph graph,int start) {
		//记录已经访问过的结点,1代表已经访问过，0代表未访问
		int visited[]=new int[graph.Num];
		visited[start]=1;
		//假定一个最大权值，在寻找最短边的时候和这个值对比
		int min=10000;
		//记录两个最小的边的坐标
		int h1=0;
		int h2=0;
		for(int k=1;k<graph.Num;k++) {
			
			//i代表已经访问过的结点，j代表未访问过的结点
			//经历这次双重for循环后，将会找到最小的一个边min
			for(int i=0;i<graph.Num;i++) {
				for(int j=0;j<graph.Num;j++) {
					//如果i访问过且j为访问过且i和j代表的边小于已经记录的最小边min，则替换min
					if(visited[i]==1&&visited[j]==0&&graph.weight[i][j]<min) {
						min=graph.weight[i][j];
						h1=i;
						h2=j;
					}
				}
			}
			//输出寻找到的边的信息
			System.out.println("第"+k+"次寻找： 边["+graph.verxs[h1]+","+graph.verxs[h2]+"]  ,权值为："+min);
			//将h2标记为已访问过（因为h1已经标记过已访问）
			visited[h2]=1;
			//重置min
			min=10000;
		}
	}
	public void showGraph(char[] data,int verxs,int[][] weight) {
		System.out.print("  ");
		for(char c:data) {
			System.out.print(c+" \t");
		}
		System.out.println();
		for(int i=0;i<weight.length;i++) {
			System.out.print(data[i]+" ");
			for(int j=0;j<weight.length;j++) {
				System.out.print(weight[i][j]+"\t");
			}
			System.out.println();
		}
	}
}

class Graph {
	char[] verxs;// 结点数据
	int Num;// 边的个数
	int weight[][];// 图的权重

	public Graph(int verx) {
		verxs=new char[verx];
		weight=new int[verx][verx];
		this.Num = verx;
	}
}
