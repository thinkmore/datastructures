package datastructures.btree;
/**
 * ������װB���ڵ�Ķ���
 */
public class BTreeNode {
	/**
	 * B���Ľ���
	 */
	private int m = 0;
	
	/**
	 *��Ŷ��ʶ�������� 
	 */
	private KeyItem[] items =  null; 
	/**
	 * ��Ŷ���ӽڵ����
	 */
	private BTreeNode[] children = null;
	/**
	 * ���ڵ����
	 */
	private BTreeNode parent = null;
	/**
	 * ������¼���ڵ㵽�״���˼���ʶ��������
	 */
	private int numItems;
	
	
	public BTreeNode(int m){
		this.m = m;
		items = new KeyItem[m-1];
		children = new BTreeNode[m];
	}
	
	
	/**
	 * ����������ȡ��Ӧ��ʶ��������
	 * @param index
	 * @return
	 */
	public KeyItem getItem(int index){
		return items[index];
	}
	/**
	 * �����ڵ㣬�¼���һ��ʶ��������
	 * @param newItem
	 * @return
	 */
	public int insertKeyItem(KeyItem newItem){
		//ά����ŵ�ʶ��������� ����
		numItems++;
		
		//�Ӻ���ǰ�������ݲ��ҺͱȽ�
		for(int i=(m-2);i>=0;i--){
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
	 * ����������ɾ��ĳ��keyitem
	 * @param index
	 */
	public void removeKeyItem(int index){
		//������ǰ�ƶ�һ��
		for(int i=index+1;i<this.numItems;i++){
			this.items[i-1] = this.items[i];
		}
		//�����һ����Ϊnull
		this.items[this.numItems-1] = null;
		
		this.numItems--;
	}
	/**
	 * ɾ���ڵ����е�keyItem
	 */
	public void removeAllItems(){
		for(int i=0;i<this.numItems;i++){
			this.items[i] = null;
		}
		this.numItems = 0;
	}
	
	/**
	 * ���ұ��ڵ��Ƿ���ֵΪkey��ʶ��������
	 * @param key
	 * @return
	 */
	public int findKeyItem(int key){
		for(int i=0;i<(m-1);i++){
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
	public void connectChild(int childIndex,BTreeNode childNode){
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
	public BTreeNode disconnectChild(int childIndex){
		BTreeNode ret = this.children[childIndex];
		this.children[childIndex] = null;
		return ret;
	}
	/**
	 * ��ȡĳ���ӽڵ�	
	 * @param childIndex
	 * @return
	 */
	public BTreeNode getChild(int childIndex){
		return this.children[childIndex];
	}
	/**
	 * �õ����ڵ����
	 * @return
	 */
	public BTreeNode getParent(){
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
		return this.numItems==(m-1);
	}
	
	public void displayNode(){
		for(int i=0;i<this.numItems;i++){
			System.out.print(this.items[i].getId()+",");
		}
		System.out.println(" --- ");
	}
	
	public KeyItem[] getItems() {
		return items;
	}
	public void setItems(KeyItem[] items) {
		this.items = items;
	}
	public BTreeNode[] getChildren() {
		return children;
	}
	public void setChildren(BTreeNode[] children) {
		this.children = children;
	}
	public int getNumItems() {
		return numItems;
	}
	public void setNumItems(int numItems) {
		this.numItems = numItems;
	}
	public void setParent(BTreeNode parent) {
		this.parent = parent;
	}
	public int getM() {
		return m;
	}
	public void setM(int m) {
		this.m = m;
	}
}
