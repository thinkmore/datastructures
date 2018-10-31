package datastructures.graph2.mst;
/**
 * ��ʾ���ȼ�����
 */
public class PriorityQueue {
	/**
	 * ��Ŷ������ݵ�����
	 */
	private Edge[] queue;
	/**
	 * ��¼��ǰ���������ŵ�Ԫ�ظ�����Ҳ�൱��������
	 */
	private int nItems ;
	
	public PriorityQueue(int length){
		queue = new Edge[length];
		nItems = 0;
	}
	
	public void insert(Edge data){
		//1����������û��������Ļ���ֱ�Ӹ�ֵ
		if(nItems == 0){
			queue[nItems] = data;
			nItems++;
		}else{
			//2������������������Ļ�������Ҫ���бȽϣ�������������
			int i = 0;
			for(i=nItems-1;i>=0;i--){
				if(data.getDistance() < queue[i].getDistance()){
					queue[i+1] = queue[i];
				}else{
					break;
				}
			}
			queue[i+1] = data;
			nItems++;
		}
	}
	public Edge remove(){
		nItems--;
		Edge temp = queue[nItems];
		queue[nItems] = null;
		return temp;
	}
	public void removeIndex(int index){
		for(int i=index;i<nItems-1;i++){
			queue[i] = this.queue[i+1];
		}
		nItems--;
	}
	
	public Edge peekFront(){
		return queue[nItems-1];
	}
	public Edge peekIndex(int index){
		return this.queue[index];
	}
	
	public boolean isEmpty(){
		return nItems==0;
	}
	public boolean isFull(){
		return nItems==queue.length;
	}
	public void printQueue(){
		System.out.println("=======================>");
		for(Edge d : queue){
			System.out.println(d);
		}
	}
	
	public int findDestVertex(int destVertexIndex){
		for(int i=0;i<nItems;i++){
			if(queue[i].getDestVertex() == destVertexIndex){
				return i;
			}
		}
		return -1;
	}
	
	public static void main(String[] args) {
		PriorityQueue t = new PriorityQueue(5);
		
//		t.insert(5);
//		t.insert(6);
//		t.insert(3);
//		t.insert(4);
//		t.insert(1);
//		
//		t.printQueue();
//		
//		int ret = t.peekFront();
//		System.out.println("now ret=="+ret);
//		
//		t.remove();
//		int ret2 = t.remove();
//		System.out.println("now ret2=="+ret2);
//		
//		t.printQueue();
		
	}
}
