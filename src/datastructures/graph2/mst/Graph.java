package datastructures.graph2.mst;

/**
 * ��ʾ��Ȩͼ����С������
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
	 * ��ǰ�Ķ���
	 */
	private int currentVertex;
	/**
	 * ���ȼ�����
	 */
	private PriorityQueue pq;
	/**
	 * ���������еĶ������
	 */
	private int numTree;
	
	public Graph(int n){
		this.vertexList = new Vertex[n];
		matrix = new int[n][n];
		this.pq = new PriorityQueue(n);
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
	 * ������С������
	 */
	public void mst(){
		currentVertex = 0;
		while(numTree<numVertex-1){
			//1:
			this.vertexList[currentVertex].setInTree(true);
			numTree++;
			//2:
			for(int i=0;i<numVertex;i++){
				if(i==currentVertex
						|| this.vertexList[i].isInTree()
						|| this.matrix[currentVertex][i]==0
				){
					continue;
				}
				this.putInPQ(i, this.matrix[currentVertex][i]);
			}
			//3:
			Edge minEdge = pq.remove();
			int srcVertex = minEdge.getSrcVertex();
			currentVertex = minEdge.getDestVertex();
			
			System.out.println(this.vertexList[srcVertex].getLabel()
					+"-->"+this.vertexList[currentVertex].getLabel());

		}
		//4���ָ����е� inTree��״̬
		for(int i=0;i<numVertex;i++){
			this.vertexList[i].setInTree(false);
		}
	}
	
	private void putInPQ(int newVertex,int distance){
		int queueIndex = pq.findDestVertex(newVertex);
		if(queueIndex!=-1){
			Edge tempEdge = pq.peekIndex(queueIndex);
			if(tempEdge.getDistance() > distance){
				pq.removeIndex(queueIndex);
				Edge newEdge = new Edge(currentVertex,newVertex,distance);
				pq.insert(newEdge);
			}
		}else{
			Edge newEdge = new Edge(currentVertex,newVertex,distance);
			pq.insert(newEdge);
		}
	}
	public static void main(String[] args) {
		Graph t = new Graph(10);
		
		t.addVertex("A");
		t.addVertex("B");
		t.addVertex("C");
		t.addVertex("D");
		
		t.addEdge(0, 1,30);
		t.addEdge(0, 2,35);
		t.addEdge(1, 2,50);
		t.addEdge(2, 3,40);
		
		t.mst();		
	}
}
