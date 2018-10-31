package datastructures.graph.dfs;
/**
 * ��ʾͼ(����)��������ȱ���
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
	 * ջ
	 */
	private MyStack theStack;
	
	public Graph(int n){
		this.vertexList = new Vertex[n];
		matrix = new int[n][n];
		this.theStack = new MyStack(100);
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
		this.matrix[end][start] = 1;
	}
	/**
	 * ����������ȵı���
	 */
	public void dfs(){
		//1��
		this.vertexList[0].setWasVisited(true);
		this.theStack.push(0);
		System.out.print(this.vertexList[0].getLabel()+" ");
		
		//2:3:
		while(!theStack.isEmpty()){
			//�ҵ���һ��δ���ʵ��ڽӶ���
			int v = this.getNextUnvisitedVertex(theStack.peek());
			if(v==-1){
				//�Ҳ�����һ���ˣ��͵���
				this.theStack.pop();
			}else{
				this.vertexList[v].setWasVisited(true);
				this.theStack.push(v);
				System.out.print(this.vertexList[v].getLabel()+" ");
			}
		}
			
		//3:�����еķ���״̬��ԭ�������Ͳ���Ӱ�쵽�����Ĳ���
		for(int i=0;i<numVertex;i++){
			this.vertexList[i].setWasVisited(false);
		}
	}
	/**
	 * �ҵ���һ��δ���ʵ��ڽӶ���
	 * @param index
	 * @return
	 */
	private int getNextUnvisitedVertex(int index){
		for(int i=0;i<numVertex;i++){
			if(this.matrix[index][i] == 1
					&& !this.vertexList[i].isWasVisited()
			){
				return i;
			}
		}
		return -1;
	}
	
	public static void main(String[] args) {
		Graph t = new Graph(10);
		
		t.addVertex("A");
		t.addVertex("B");
		t.addVertex("C");
		t.addVertex("D");
		t.addVertex("E");
		t.addVertex("F");
		
		t.addEdge(0, 1);
		t.addEdge(0, 2);
		t.addEdge(0, 3);
		t.addEdge(1, 2);
		t.addEdge(1, 3);
		t.addEdge(2, 4);
		t.addEdge(4, 5);
		
		t.dfs();
		
	}
}
