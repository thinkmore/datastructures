package datastructures.graph.connect;
/**
 * ��ʾ����ͼ����ͨ��
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
//		this.matrix[end][start] = 1;
	}
	/**
	 * ����������ȵı���
	 */
	public void dfs(int begin){
		//1��
		this.vertexList[begin].setWasVisited(true);
		this.theStack.push(begin);
		System.out.print(this.vertexList[begin].getLabel()+" ");
		
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
	/**
	 * warshall�㷨
	 * @return
	 */
	public int[][] warshall(){
		int[][] newMartrix = this.matrix;
		
		for(int i=0;i<newMartrix.length;i++){
			for(int j=0;j<newMartrix[i].length;j++){
				if(i==j){
					continue;
				}
				if(newMartrix[i][j]==1){
					for(int k=0;k<newMartrix.length;k++){
						if(k==i||k==j){
							continue;
						}
						if(newMartrix[k][i]==1){
							newMartrix[k][j] = 1;
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
		t.addVertex("E");
		t.addVertex("F");
		
		t.addEdge(0, 1);
		t.addEdge(0, 2);
		t.addEdge(0, 3);
		t.addEdge(1, 2);
		t.addEdge(1, 3);
		t.addEdge(2, 4);
		t.addEdge(4, 5);
		
		for(int i=0;i<6;i++){
			t.dfs(i);
			System.out.println("");
		}
		System.out.println("---------------------------------->");
		
		int[][] ret = t.warshall();
		for(int i=0;i<6;i++){
			for(int j=0;j<6;j++){
				System.out.println("i="+i+",j="+j+" , la="+ret[i][j]);
			}
		}
		
	}
}
