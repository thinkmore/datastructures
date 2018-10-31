package datastructures.graph2.mst;
/**
 * ��װ��Ȩͼ�ıߵĶ���
 */
public class Edge {
	/**
	 * Դ����
	 */
	private int srcVertex;
	/**
	 * Ŀ�ĵض���
	 */
	private int destVertex;
	/**
	 * Ȩֵ����ҵ�����ݣ������ʾ���Ǿ���
	 */
	private int distance;
	
	public Edge(int srcVertex, int destVertex, int distance) {
		super();
		this.srcVertex = srcVertex;
		this.destVertex = destVertex;
		this.distance = distance;
	}
	public int getSrcVertex() {
		return srcVertex;
	}
	public void setSrcVertex(int srcVertex) {
		this.srcVertex = srcVertex;
	}
	public int getDestVertex() {
		return destVertex;
	}
	public void setDestVertex(int destVertex) {
		this.destVertex = destVertex;
	}
	public int getDistance() {
		return distance;
	}
	public void setDistance(int distance) {
		this.distance = distance;
	}
}
