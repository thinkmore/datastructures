package datastructures.hash.hash3;


/**
 * ��ʾ��������ַ�������ͻ��Hash��
 */
public class MyHashTable {
	private SortedList[] hasArray;
	private int arraySize;
	
	public MyHashTable(int size){
		this.arraySize = size;
		this.hasArray = new SortedList[this.arraySize];
		//��ʼ��ÿ������
		for(int i=0;i<this.arraySize;i++){
			this.hasArray[i] = new SortedList();
		}
	}
	/**
	 * hash����
	 * @param key
	 * @return
	 */
	public int hashFun(int key){
		return key%this.arraySize;
	}
	/**
	 * �������ݶ���
	 * @param item
	 */
	public void insert(Link link){
		int hashVal = this.hashFun(link.getKey());
		this.hasArray[hashVal].insert(link);
	}
	
	/**
	 * ɾ�����ݶ���
	 * @param key
	 */
	public void remove(int key){
		int hashVal = this.hashFun(key);
		this.hasArray[hashVal].remove(key);
	}
	/**
	 * ����key��ȡ���ݶ���
	 * @param key
	 * @return
	 */
	public Link findData(int key){
		int hashVal = this.hashFun(key);
		return this.hasArray[hashVal].findLink(key);
	}
	
	public void displayHashTable(){
		for(SortedList d : this.hasArray){
			d.displayList();
		}
		System.out.println("=======================33333");
	}
	
	public static void main(String[] args) {
		MyHashTable t = new MyHashTable(10);
		
		for(int i=1;i<=10;i++){
			Link d = new Link(i,i+100);
			t.insert(d);
		}
		
		String s = "";
		
		t.displayHashTable();
		
		t.remove(3);
		
		t.displayHashTable();
	}
}
