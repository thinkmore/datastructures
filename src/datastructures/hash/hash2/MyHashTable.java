package datastructures.hash.hash2;
/**
 *	��ʾ�����ٹ�ϣ�������ͻ��Hash��
 */
public class MyHashTable {
	private DataItem[] hashArray;
	private int arraySize;
	
	public MyHashTable(int size){
		this.arraySize = size;
		this.hashArray = new DataItem[this.arraySize];
	}
	/**
	 * ��һ�ε�hash����
	 * @param key
	 * @return
	 */
	public int hashFun(int key){
		return key%this.arraySize;
	}
	/**
	 * �ٹ�ϣ����
	 * @param key
	 * @return
	 */
	public int hashFun2(int key){
		return 7-key%7;
	}
	
	/**
	 * �������ݶ���
	 * @param item
	 */
	public void insert(DataItem item){
		int hashVal = this.hashFun(item.getKey());
		int stepSize = this.hashFun2(item.getKey());
		
		//����Ƿ��ͻ�������ͻ�˾����²��ҵ�һ��δʹ�õ�λ��
		while(this.hashArray[hashVal]!=null){
			hashVal += stepSize;
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
		int stepSize = this.hashFun2(key);
		
		while(this.hashArray[hashVal]!=null){
			if(this.hashArray[hashVal].getKey() == key){
				this.hashArray[hashVal] = null;
				break;
			}
			
			hashVal += stepSize;
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
		int stepSize = this.hashFun2(key);
		
		while(this.hashArray[hashVal]!=null){
			if(this.hashArray[hashVal].getKey() == key){
				return this.hashArray[hashVal];
			}
			
			hashVal += stepSize;
			//����Ƿ񵽵ף������˴�ͷ��
			hashVal %= this.arraySize;
		}
		return null;
	}
	
	public void displayHashTable(){
		for(DataItem d : this.hashArray){
			System.out.print(d+" , ");
		}
		System.out.println("=======================2222222");
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
