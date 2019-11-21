package Algorithm;

public class MinGraphAlgorithm {

	public static void main(String[] args) {
		//���Կ���ͼ�Ƿ񴴽�ok
				char[] data = new char[]{'A','B','C','D','E','F','G'};
				int verxs = data.length;
				//�ڽӾ���Ĺ�ϵʹ�ö�ά�����ʾ,10000�����������ʾ�����㲻��ͨ
				int [][]weight=new int[][]{
		            {10000,5,7,10000,10000,10000,2},
		            {5,10000,10000,9,10000,10000,3},
		            {7,10000,10000,10000,8,10000,10000},
		            {10000,9,10000,10000,10000,4,10000},
		            {10000,10000,8,10000,10000,5,4},
		            {10000,10000,10000,4,5,10000,6},
		            {2,3,10000,10000,4,6,10000},};
		            
		        //����MGraph����
		        Graph graph = new Graph(verxs);
		        //����һ��MinTree����
		        MinGraph minTree = new MinGraph();
		        minTree.CreateGraph(graph, data, verxs, weight);
		        //���
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
	 * @param graph ͼ
	 * @param start ��ʾ�ӵڼ�����㿪ʼѰ��
	 */
	public void prim(Graph graph,int start) {
		//��¼�Ѿ����ʹ��Ľ��,1�����Ѿ����ʹ���0����δ����
		int visited[]=new int[graph.Num];
		visited[start]=1;
		//�ٶ�һ�����Ȩֵ����Ѱ����̱ߵ�ʱ������ֵ�Ա�
		int min=10000;
		//��¼������С�ıߵ�����
		int h1=0;
		int h2=0;
		for(int k=1;k<graph.Num;k++) {
			
			//i�����Ѿ����ʹ��Ľ�㣬j����δ���ʹ��Ľ��
			//�������˫��forѭ���󣬽����ҵ���С��һ����min
			for(int i=0;i<graph.Num;i++) {
				for(int j=0;j<graph.Num;j++) {
					//���i���ʹ���jΪ���ʹ���i��j����ı�С���Ѿ���¼����С��min�����滻min
					if(visited[i]==1&&visited[j]==0&&graph.weight[i][j]<min) {
						min=graph.weight[i][j];
						h1=i;
						h2=j;
					}
				}
			}
			//���Ѱ�ҵ��ıߵ���Ϣ
			System.out.println("��"+k+"��Ѱ�ң� ��["+graph.verxs[h1]+","+graph.verxs[h2]+"]  ,ȨֵΪ��"+min);
			//��h2���Ϊ�ѷ��ʹ�����Ϊh1�Ѿ���ǹ��ѷ��ʣ�
			visited[h2]=1;
			//����min
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
	char[] verxs;// �������
	int Num;// �ߵĸ���
	int weight[][];// ͼ��Ȩ��

	public Graph(int verx) {
		verxs=new char[verx];
		weight=new int[verx][verx];
		this.Num = verx;
	}
}
