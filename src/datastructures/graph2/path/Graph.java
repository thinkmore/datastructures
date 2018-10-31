package datastructures.graph2.path;

import datastructures.graph2.mst.PriorityQueue;
import datastructures.graph2.mst.Vertex;

/**
 * ��ʾ��Ȩͼ�ĵϽ�˹�����㷨
 */
public class Graph {
	private final int MAXNUM = 10000000;
	/**
	 * ��������
	 */
	private Vertex vertexList[];
	/**
	 * �ڽӾ���
	 */
	private int[][] matrix;
	/**
	 * ��¼ʵ�ʵĶ���ĸ���
	 */
	private int numVertex;
	/**
	 * ���������еĶ������
	 */
	private int numTree;
	/**
	 * һ�����㵽�����������̾���
	 */
	private DistanceSrcVertex sPath[];
	
	public Graph(int n){
		this.vertexList = new Vertex[n];
		matrix = new int[n][n];
		sPath = new DistanceSrcVertex[n];
		for(int i=0;i<n;i++){
			for(int j=0;j<n;j++){
				this.matrix[i][j] = this.MAXNUM;
			}
		}
	}
	/**
	 * ��������
	 * @param label
	 */
	public void addVertex(String label){
		this.vertexList[numVertex] = new Vertex(label);
		numVertex++;
	}
	/**
	 * ��ӱ�
	 * @param start
	 * @param end
	 */
	public void addEdge(int start,int end,int distance){
		this.matrix[start][end] = distance;
		this.matrix[end][start] = distance;
	}
	/**
	 * �Ͻ�˹�����㷨
	 */
	public void path(){
		//1���ѵ�һ�������������
		int startTree = 0;
		this.vertexList[startTree].setInTree(true);
		numTree++;
		//1.2����sPath����ʼֵ
		for(int i=0;i<numVertex;i++){
			int tempDistance = this.matrix[startTree][i];
			sPath[i] = new DistanceSrcVertex(startTree, tempDistance);
		}
		//2:
		while(numTree < numVertex){
			//4:
			int indexMin = getMin();
			int minDistance = sPath[indexMin].getDistance();
			
			if(minDistance == this.MAXNUM){
				break;
			}else{
				//5:
				int currentVertex = indexMin;
				this.vertexList[currentVertex].setInTree(true);
				this.numTree++;
				//6
				this.adjustSPath(currentVertex, sPath[indexMin].getDistance());
			}
		}
		
		//7���ָ����е� inTree��״̬
		for(int i=0;i<numVertex;i++){
			this.vertexList[i].setInTree(false);
		}
	}
	/**
	 * ����spath
	 * @param currentVertex
	 * @param startToCurrent
	 */
	private void adjustSPath(int currentVertex,int startToCurrent){
		for(int i=1;i<numVertex;i++){
			if(this.vertexList[i].isInTree()){
				continue;
			}
			int currenToEnd = this.matrix[currentVertex][i];
			int startToEnd = startToCurrent + currenToEnd;
			
			if(startToEnd < sPath[i].getDistance()){
				sPath[i].setSrcVertex(currentVertex);
				sPath[i].setDistance(startToEnd);
			}
		}
	}
	
	/**
	 * ��spath�����ҵ���С��distance������
	 * @return
	 */
	private int getMin(){
		int minDistance = this.MAXNUM;
		int indexMin = -1;
		
		for(int i=1;i<numVertex;i++){
			if(!vertexList[i].isInTree()
					&& sPath[i].getDistance() < minDistance
			){
				minDistance = sPath[i].getDistance();
				indexMin = i;
			}
		}
		
		return indexMin;
	}
	
	public void displayPaths(){
		for(int i=0;i<numVertex;i++){
			System.out.print(this.vertexList[i].getLabel()+" = "
					+ sPath[i].getDistance()+" , "
					);
		}
		System.out.println("");
	}
	
	/**
	 * floyd�㷨
	 * @return
	 */
	public int[][] floyd(){
		int[][] newMartrix = this.matrix;
		
		for(int i=0;i<newMartrix.length;i++){
			for(int j=0;j<newMartrix[i].length;j++){
				if(i==j){
					continue;
				}
				if(newMartrix[i][j]!=this.MAXNUM){
					for(int k=0;k<newMartrix.length;k++){
						if(k==i||k==j){
							continue;
						}
						if(newMartrix[k][i]!=this.MAXNUM){
							int temp = newMartrix[i][j]+newMartrix[k][i];
							if(newMartrix[k][j] > temp){
								newMartrix[k][j] = temp;
							}
						}
					}
				}
			}
		}
		
		return newMartrix;
	}
	
	
	public static void main(String[] args) {
		Graph t = new Graph(10);
		
		t.addVertex("A");
		t.addVertex("B");
		t.addVertex("C");
		t.addVertex("D");
		
		t.addEdge(0, 1,30);
		t.addEdge(0, 2,35);
		t.addEdge(1, 2,1);
		t.addEdge(2, 3,40);
		
		t.path();
		
		t.displayPaths();
		System.out.println("---------------------------->");
		
		t.matrix = t.floyd();
		for(int i=0;i<5;i++){
			for(int j=0;j<5;j++){
				System.out.println("i="+i+",j="+j+" , "+t.matrix[i][j]);
			}
		}
	}
}
