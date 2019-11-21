package Graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class graph {

	private int numofEdges=0;//图的边数
	private int[][] Edges;//图对应的二维数组
	private List<String> vertexList;//存放结点
	private boolean[] isVisited;//boolean数组，标记结点是否已访问
	private int n;//图的大小
	public static void main(String[] args) {
		String[] strings= {"A","B","C","D","E","F","G","H","I"};
		int length=strings.length;
		graph graph=new graph(length);
		for(int i=0;i<length;i++) {
			graph.addVertex(strings[i]);
		}
		//创建图
		graph.inserEdges(0, 1, 1);
		graph.inserEdges(0, 2, 1);
		graph.inserEdges(1, 2, 1);
		graph.inserEdges(1, 3, 1);
		graph.inserEdges(2, 4, 1);
		graph.inserEdges(2, 5, 1);
		graph.inserEdges(2, 5, 1);
		graph.inserEdges(3, 6, 1);
		graph.inserEdges(3, 7, 1);
		graph.inserEdges(4, 8, 1);
		graph.showGraph();
		
		System.out.println("深度优先遍历：");
		graph.DFS();
		System.out.println();
		System.out.println("广度优先遍历：");
		graph.BFS();

	}
	/**
	 * 初始化
	 * @param n 图的长宽
	 */
	public graph(int n) {
		this.n=n;
		Edges = new int[n][n];
		vertexList = new ArrayList<String>(n);
		isVisited = new boolean[n];
	}
	/**
	 * 获取第一个不为0的邻接结点
	 * 遍历以index那一行的所有结点，如果找到一个大于0的，则返回该下标
	 * @param index 行数
	 * @return index，j中不为0的那一个纵坐标
	 */
	private int getFirstNieghbor(int index) {
		for (int j = 0; j < vertexList.size(); j++) {
			if (Edges[index][j] > 0) {
				return j;
			}
		}
		return -1;
	}
	/**
	 * 获取以传入的点为初始结点的  下一个不为0的邻接点
	 * @param v1
	 * @param v2
	 * @return 返回坐标v1，v2的下一个不为0的坐标（既v1，v2+j中不为0的） 
	 */
	private int getNextNieghbor(int v1, int v2) {
		for (int j = v2 + 1; j < vertexList.size(); j++) {
			if (Edges[v1][j] > 0) {
				return j;
			}
		}
		return -1;
	}
	private void DFS(boolean[] isvertex,int i) {
		//输出当前结点
		System.out.print(vertexList.get(i)+" -> ");
		//并且在boolean数组中将对应的下标改为true，表示已经遍历过
		isvertex[i]=true;
		//查找该结点的第一个邻接结点w
		int w=getFirstNieghbor(i);
		//如果w存在,进入while循环
		while(w!=-1) {
			//如果w未被访问过，则进行递归遍历
			if(!isvertex[w]) {
				DFS(isvertex, w);
			}
			//如果w被访问过，则将w指向该结点的第二个邻接结点
			w=getNextNieghbor(i, w);
		}
	}

	public void DFS() {
		isVisited=new boolean[n];
		DFS(isVisited, 0);
	}

	public void BFS() {
		isVisited=new boolean[n];
		BFS(isVisited, 0);
	}
	private void BFS(boolean[] isVisited,int i) {
		//输出当前结点
		System.out.print(vertexList.get(i)+" -> ");
		isVisited[i]=true;
		int u;//记录被访问的结点
		int w;//表示u的邻接结点
		LinkedList<Integer> list=new LinkedList<Integer>(); //充当队列使用，记录被访问结点的顺序
		list.addLast(i);//将i放入队列
		while(!list.isEmpty()) {
			//移除第一个结点，并且从这个结点u开始像遍历
			u=list.removeFirst();
			//获得u的第一个邻接结点
			w=getFirstNieghbor(u);
			//如果邻接结点w存在
			while(w!=-1) {
				//并且该结点w未被访问过
				if(!isVisited[w]) {
					//输出该结点的信息
					System.out.print(vertexList.get(w)+" -> ");
					//将该结点设置为已访问，并且加入到队列，以备后续遍历
					isVisited[w]=true;
					list.addLast(w);
				}
				//再获取第一个邻接结点的下一个邻接结点
			     w=getNextNieghbor(u, w);
			}
		}
		
	}
	/**
	 *  表示AB之间有连接则添加 v1=0，v2=1，weight=1
	 * @param v1 结点1
	 * @param v2 结点2
	 * @param weigh 两个结点之间的权重
	 */
	public void inserEdges(int v1,int v2,int weight) {
		Edges[v1][v2]=weight;
		Edges[v2][v1]=weight;
		numofEdges++;
	}
	//添加结点到集合内
	//A->0，B->1...
	public void addVertex(String s) {
		vertexList.add(s);
	}
	public void showGraph() {
		System.out.print("  ");
		for(String s:vertexList) {
			System.out.print(s+" ");
		}
		System.out.println();
		for(int i=0;i<Edges.length;i++) {
			System.out.print(vertexList.get(i)+" ");
			for(int j=0;j<Edges.length;j++) {
				System.out.print(Edges[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println("图的边数为:"+ numofEdges);
	}
}
