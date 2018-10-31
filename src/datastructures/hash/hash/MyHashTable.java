package datastructures.hash.hash;
/**
 *	��ʾ��������̽�ⷨ�����ͻ��Hash��
 */
public class MyHashTable {
	private DataItem[] hashArray;
	private int arraySize;
	
	public MyHashTable(int size){
		this.arraySize = size;
		this.hashArray = new DataItem[this.arraySize];
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
	public void insert(DataItem item){
		int hashVal = this.hashFun(item.getKey());
		
		//����Ƿ��ͻ�������ͻ�˾����²��ҵ�һ��δʹ�õ�λ��
		while(this.hashArray[hashVal]!=null){
			hashVal += 1;
			//����Ƿ񵽵ף������˴�ͷ��
			hashVal %= this.arraySize;
		}
		
		//��������
		this.hashArray[hashVal] = item;		
	}
	/**
	 * ɾ�����ݶ���
	 * @param key
	 */
	public void remove(int key){
		int hashVal = this.hashFun(key);
		
		while(this.hashArray[hashVal]!=null){
			if(this.hashArray[hashVal].getKey() == key){
				this.hashArray[hashVal] = null;
				break;
			}
			
			hashVal += 1;
			//����Ƿ񵽵ף������˴�ͷ��
			hashVal %= this.arraySize;
		}
	}
	/**
	 * ����key��ȡ���ݶ���
	 * @param key
	 * @return
	 */
	public DataItem findData(int key){
		int hashVal = this.hashFun(key);
		
		while(this.hashArray[hashVal]!=null){
			if(this.hashArray[hashVal].getKey() == key){
				return this.hashArray[hashVal];
			}
			
			hashVal += 1;
			//����Ƿ񵽵ף������˴�ͷ��
			hashVal %= this.arraySize;
		}
		return null;
	}
	
	public void displayHashTable(){
		for(DataItem d : this.hashArray){
			System.out.print(d+" , ");
		}
		System.out.println("=======================");
	}
	
	public static void main(String[] args) {
		MyHashTable t = new MyHashTable(10);
		
		for(int i=1;i<=10;i++){
			DataItem d = new DataItem(i,i+100);
			t.insert(d);
		}
		
		t.displayHashTable();
		
		t.remove(3);
		
		t.displayHashTable();
	}
}
