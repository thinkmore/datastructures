package datastructures.binarytree;
/**
 * Huffman�㷨ʹ�õ����ȼ�����
 */
public class HuffmanPriorityQueue {
	/**
	 * ��Ŷ������ݵ�����
	 */
	private HuffmanNode[] queue;
	/**
	 * ��¼��ǰ���������ŵ�Ԫ�ظ�����Ҳ�൱��������
	 */
	private int nItems ;
	
	private int length;
	
	public int size(){
		return nItems;
	}
	
	public HuffmanPriorityQueue(int length){
		this.length = length;
		queue = new HuffmanNode[length];
		nItems = 0;
	}
	
	public void insert(HuffmanNode data){
		//1����������û��������Ļ���ֱ�Ӹ�ֵ
		if(nItems == 0){
			queue[nItems] = data;
			nItems++;
		}else{
			//2������������������Ļ�������Ҫ���бȽϣ�������������
			int i = 0;
			for(i=nItems-1;i>=0;i--){
				if(data.getCount() > queue[i].getCount()){
					queue[i+1] = queue[i];
				}else{
					break;
				}
			}
			queue[i+1] = data;
			nItems++;
		}
	}
	public HuffmanNode remove(){
		nItems--;
		HuffmanNode temp = queue[nItems];
		queue[nItems] = null;
		return temp;
	}
	public HuffmanNode peekFront(){
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
		for(HuffmanNode d : queue){
			System.out.println(d);
		}
	}
	
	public static void main(String[] args) {
		HuffmanPriorityQueue t = new HuffmanPriorityQueue(5);
		
		t.insert(new HuffmanNode('a',5));
		t.insert(new HuffmanNode('b',6));
		t.insert(new HuffmanNode('c',3));
		t.insert(new HuffmanNode('d',4));
		t.insert(new HuffmanNode('e',1));
		
		t.printQueue();
		
		HuffmanNode ret = t.peekFront();
		System.out.println("now ret=="+ret);
		
		t.remove();
		HuffmanNode ret2 = t.remove();
		System.out.println("now ret2=="+ret2);
		
		t.printQueue();
		
	}
}
