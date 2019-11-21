package Graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class graph {

	private int numofEdges=0;//ͼ�ı���
	private int[][] Edges;//ͼ��Ӧ�Ķ�ά����
	private List<String> vertexList;//��Ž��
	private boolean[] isVisited;//boolean���飬��ǽ���Ƿ��ѷ���
	private int n;//ͼ�Ĵ�С
	public static void main(String[] args) {
		String[] strings= {"A","B","C","D","E","F","G","H","I"};
		int length=strings.length;
		graph graph=new graph(length);
		for(int i=0;i<length;i++) {
			graph.addVertex(strings[i]);
		}
		//����ͼ
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
		
		System.out.println("������ȱ�����");
		graph.DFS();
		System.out.println();
		System.out.println("������ȱ�����");
		graph.BFS();

	}
	/**
	 * ��ʼ��
	 * @param n ͼ�ĳ���
	 */
	public graph(int n) {
		this.n=n;
		Edges = new int[n][n];
		vertexList = new ArrayList<String>(n);
		isVisited = new boolean[n];
	}
	/**
	 * ��ȡ��һ����Ϊ0���ڽӽ��
	 * ������index��һ�е����н�㣬����ҵ�һ������0�ģ��򷵻ظ��±�
	 * @param index ����
	 * @return index��j�в�Ϊ0����һ��������
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
	 * ��ȡ�Դ���ĵ�Ϊ��ʼ����  ��һ����Ϊ0���ڽӵ�
	 * @param v1
	 * @param v2
	 * @return ��������v1��v2����һ����Ϊ0�����꣨��v1��v2+j�в�Ϊ0�ģ� 
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
		//�����ǰ���
		System.out.print(vertexList.get(i)+" -> ");
		//������boolean�����н���Ӧ���±��Ϊtrue����ʾ�Ѿ�������
		isvertex[i]=true;
		//���Ҹý��ĵ�һ���ڽӽ��w
		int w=getFirstNieghbor(i);
		//���w����,����whileѭ��
		while(w!=-1) {
			//���wδ�����ʹ�������еݹ����
			if(!isvertex[w]) {
				DFS(isvertex, w);
			}
			//���w�����ʹ�����wָ��ý��ĵڶ����ڽӽ��
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
		//�����ǰ���
		System.out.print(vertexList.get(i)+" -> ");
		isVisited[i]=true;
		int u;//��¼�����ʵĽ��
		int w;//��ʾu���ڽӽ��
		LinkedList<Integer> list=new LinkedList<Integer>(); //�䵱����ʹ�ã���¼�����ʽ���˳��
		list.addLast(i);//��i�������
		while(!list.isEmpty()) {
			//�Ƴ���һ����㣬���Ҵ�������u��ʼ�����
			u=list.removeFirst();
			//���u�ĵ�һ���ڽӽ��
			w=getFirstNieghbor(u);
			//����ڽӽ��w����
			while(w!=-1) {
				//���Ҹý��wδ�����ʹ�
				if(!isVisited[w]) {
					//����ý�����Ϣ
					System.out.print(vertexList.get(w)+" -> ");
					//���ý������Ϊ�ѷ��ʣ����Ҽ��뵽���У��Ա���������
					isVisited[w]=true;
					list.addLast(w);
				}
				//�ٻ�ȡ��һ���ڽӽ�����һ���ڽӽ��
			     w=getNextNieghbor(u, w);
			}
		}
		
	}
	/**
	 *  ��ʾAB֮������������� v1=0��v2=1��weight=1
	 * @param v1 ���1
	 * @param v2 ���2
	 * @param weigh �������֮���Ȩ��
	 */
	public void inserEdges(int v1,int v2,int weight) {
		Edges[v1][v2]=weight;
		Edges[v2][v1]=weight;
		numofEdges++;
	}
	//��ӽ�㵽������
	//A->0��B->1...
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
		System.out.println("ͼ�ı���Ϊ:"+ numofEdges);
	}
}
