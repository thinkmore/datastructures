package datastructures.queue;
/**
 * ��ʾ���ȼ�����
 */
public class PriorityQueue {
	/**
	 * ��Ŷ������ݵ�����
	 */
	private int[] queue;
	/**
	 * ��¼��ǰ���������ŵ�Ԫ�ظ�����Ҳ�൱��������
	 */
	private int nItems ;
	
	public PriorityQueue(int length){
		queue = new int[length];
		nItems = 0;
	}
	
	public void insert(int data){
		//1����������û��������Ļ���ֱ�Ӹ�ֵ
		if(nItems == 0){
			queue[nItems] = data;
			nItems++;
		}else{
			//2������������������Ļ�������Ҫ���бȽϣ�������������
			int i = 0;
			for(i=nItems-1;i>=0;i--){
				if(data < queue[i]){
					queue[i+1] = queue[i];
				}else{
					break;
				}
			}
			queue[i+1] = data;
			nItems++;
		}
	}
	public int remove(){
		nItems--;
		int temp = queue[nItems];
		queue[nItems] = 0;
		return temp;
	}
	public int peekFront(){
		return queue[nItems-1];
	}
	public boolean isEmpty(){
		return nItems==0;
	}
	public boolean isFull(){
		return nItems==queue.length;
	}
	public void printQueue(){
		System.out.println("=======================>");
		for(int d : queue){
			System.out.println(d);
		}
	}
	
	public static void main(String[] args) {
		PriorityQueue t = new PriorityQueue(5);
		
		t.insert(5);
		t.insert(6);
		t.insert(3);
		t.insert(4);
		t.insert(1);
		
		t.printQueue();
		
		int ret = t.peekFront();
		System.out.println("now ret=="+ret);
		
		t.remove();
		int ret2 = t.remove();
		System.out.println("now ret2=="+ret2);
		
		t.printQueue();
		
	}
}
