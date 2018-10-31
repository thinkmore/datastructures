package datastructures.tree234;
/**
 * ������װ2-3-4���ڵ�Ķ���
 */
public class Node234 {
	/**
	 *��Ŷ��ʶ�������� 
	 */
	private KeyItem[] items = new KeyItem[3];
	/**
	 * ��Ŷ���ӽڵ����
	 */
	private Node234[] children = new Node234[4];
	/**
	 * ���ڵ����
	 */
	private Node234 parent = null;
	/**
	 * ������¼���ڵ㵽�״���˼���ʶ��������
	 */
	private int numItems;
	
	
	/**
	 * �����ڵ㣬�¼���һ��ʶ��������
	 * @param newItem
	 * @return
	 */
	public int insertKeyItem(KeyItem newItem){
		//ά����ŵ�ʶ��������� ����
		numItems++;
		
		//�Ӻ���ǰ�������ݲ��ҺͱȽ�
		for(int i=2;i>=0;i--){
			if(this.items[i]==null){
				continue;
			}else{
				if(newItem.getId() > items[i].getId()){
					items[i+1] = newItem;
					return i+1;
				}else{
					items[i+1] = items[i];
				}
			}
		}
		items[0] = newItem;
		
		return 0;
	}
	/**
	 * �ӱ��ڵ㣬ɾ��һ��ʶ��������
	 * @return
	 */
	public KeyItem removeKeyItem(){
		KeyItem ret = items[this.numItems-1];
		items[this.numItems-1] = null;
		this.numItems--;
		return ret;
	}
	/**
	 * ���ұ��ڵ��Ƿ���ֵΪkey��ʶ��������
	 * @param key
	 * @return
	 */
	public int findKeyItem(int key){
		for(int i=0;i<3;i++){
			if(items[i]==null){
				break;
			}else if(items[i].getId() == key){
				return i;
			}
		}
		return -1;
	}
	/**
	 * �����ڵ�����һ���ӽڵ�
	 * @param childIndex
	 * @param childNode
	 */
	public void connectChild(int childIndex,Node234 childNode){
		this.children[childIndex] = childNode;
		if(childNode!=null){
			childNode.setParent(this);
		}
	}
	/**
	 * �ӱ��ڵ�Ͽ�ĳ���ӽڵ������
	 * @param childIndex
	 * @return
	 */
	public Node234 disconnectChild(int childIndex){
		Node234 ret = this.children[childIndex];
		this.children[childIndex] = null;
		return ret;
	}
	/**
	 * ��ȡĳ���ӽڵ�	
	 * @param childIndex
	 * @return
	 */
	public Node234 getChild(int childIndex){
		return this.children[childIndex];
	}
	/**
	 * �õ����ڵ����
	 * @return
	 */
	public Node234 getParent(){
		return this.parent;
	}
	/**
	 * �ж��Ƿ�Ҷ�ӽڵ�
	 * @return
	 */
	public boolean isLeaf(){
		return  this.children[0]==null ;
	}
	/**
	 * �жϱ��ڵ��ʶ���������Ƿ��Ѿ�����
	 * @return
	 */
	public boolean isFull(){
		return this.numItems==3;
	}
	
	public void displayNode(){
		for(int i=0;i<this.numItems;i++){
			System.out.println(this.items[i].getId()+",");
		}
		System.out.println(" --- ");
	}
	
	public KeyItem[] getItems() {
		return items;
	}
	public void setItems(KeyItem[] items) {
		this.items = items;
	}
	public Node234[] getChildren() {
		return children;
	}
	public void setChildren(Node234[] children) {
		this.children = children;
	}
	public int getNumItems() {
		return numItems;
	}
	public void setNumItems(int numItems) {
		this.numItems = numItems;
	}
	public void setParent(Node234 parent) {
		this.parent = parent;
	}
}
