package datastructures.graph.topo;

import datastructures.graph.bfs.MyQueue;
import datastructures.graph.bfs.Vertex;

/**
 * ��ʾ����ͼ����������
 */
public class Graph {
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
	 * ��¼��������Ľ��
	 */
	private String[] sortedArray;
	
	public Graph(int n){
		this.vertexList = new Vertex[n];
		matrix = new int[n][n];
		this.sortedArray = new String[n];
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
	public void addEdge(int start,int end){
		this.matrix[start][end] = 1;
	}
	/**
	 * ʵ��topo����
	 */
	public void topo(){
		while(numVertex>0){
			//1���ҵ�һ��û�к�̵Ķ���
			int currentVertex = noSuccessors();
			if(currentVertex == -1){
				System.out.println("����ͼ�����л�����������������");
				return;
			}
			//2���������뵽�����б���
			this.sortedArray[numVertex-1] = this.vertexList[currentVertex].getLabel();
			//3��ɾ���������
			this.deleteVertex(currentVertex);
		}
		//������
		for(String s : sortedArray){
			System.out.print(s+" ");
		}
		System.out.println("");
	}
	/**
	 * ɾ��һ�����㣬����ֻ��ɾ��ĩβ���Ǹ�
	 * @param index
	 */
	private void deleteVertex(int index){
		//1���Ӷ���������ɾ������
		for(int i=index;i<numVertex-1;i++){
			this.vertexList[i] = vertexList[i+1];
		}
		//2��ά���ڽӾ���� ��
		for(int row = index;row<numVertex-1;row++){
			this.moveRowUp(row, numVertex);
		}
		//3��ά���ڽӾ���� ��
		for(int col=index;col<numVertex-1;col++){
			this.moveColLeft(col, numVertex - 1);
		}
		//4:�����Ķ������-1
		numVertex--;
	}
	/**
	 * �����������ƶ�һ��
	 * @param col
	 * @param length
	 */
	private void moveColLeft(int col,int length){
		for(int i=0;i<length;i++){
			this.matrix[i][col] = this.matrix[i][col+1];
		}
	}
	/**
	 * ��������������һ��
	 * @param row
	 * @param length
	 */
	private void moveRowUp(int row,int length){
		for(int i=0;i<length;i++){
			this.matrix[row][i] = this.matrix[row+1][i];
		}
	}
	
	/**
	 * Ѱ��û�к�̵Ķ���
	 * @return
	 */
	private int noSuccessors(){
		boolean hasEdge = false;
		
		for(int i=0;i<numVertex;i++){
			hasEdge = false;
			for(int j=0;j<numVertex;j++){
				if(this.matrix[i][j]>0){
					hasEdge = true;
					break;
				}
			}
			if(!hasEdge){
				return i;
			}
		}
		return -1;
	}
	
	public static void main(String[] args) {
		Graph t = new Graph(100);
		
		t.addVertex("A");
		t.addVertex("B");
		t.addVertex("C");
		t.addVertex("D");
		t.addVertex("E");
		t.addVertex("F");
		t.addVertex("G");
		
		t.addEdge(0, 3);
		t.addEdge(0, 4);
		t.addEdge(1, 4);
		t.addEdge(2, 5);
		t.addEdge(3, 6);
		t.addEdge(5, 6);
		t.addEdge(4, 6);
		
		t.topo();
		
	}
}
