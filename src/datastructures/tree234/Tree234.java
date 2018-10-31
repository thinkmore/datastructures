package datastructures.tree234;
/**
 * ��ʾ2-3-4���Ĳ���
 */
public class Tree234 {
	private Node234 root = new Node234();
	
	/**
	 * ����key���Ҷ���
	 * @param key
	 * @return
	 */
	public int find(int key){
		Node234 current = root;
		int childIndex = -1;
		while(true){
			childIndex = current.findKeyItem(key);
			if(childIndex >=0 ){
				return childIndex;
			}else if(current.isLeaf()){
				return -1;
			}else{
				current = this.getNext(current, key);
			}
		}		
	}
	/**
	 * ������һ���ڵ�
	 * @param node
	 * @param key
	 * @return
	 */
	private Node234 getNext(Node234 node,int key){
		int i = 0;
		for(i=0;i<node.getNumItems();i++){
			if(key < node.getItems()[i].getId()){
				return node.getChild(i);
			}
		}
		return node.getChild(i);
	}
	/**
	 * ����һ��ʶ������
	 * @param id
	 */
	public void insert(int id){
		Node234 current = root;
		KeyItem newItem = new KeyItem(id);
		//����Ҫ�����λ��
		while(true){
			if(current.isFull()){
				//�������
				//1�����ڵ����
				this.splitNode(current);
				//2������current
				//2.1 �ȵõ�current�ĸ��ڵ㣬��Ϊ���ڵ������
				//2.2�ٴ��²�����һ���ڵ�
				current = this.getNext(current.getParent(), id);
			}else{
				if(current.isLeaf()){
					break;
				}else{
					current = this.getNext(current, id);
				}
			}
		}
		//��ļ���
		current.insertKeyItem(newItem);		
	}
	/**
	 * ���ѽڵ�
	 * @param node
	 */
	private void splitNode(Node234 node){
		//��ԭʼ�����ݼ�¼���������Ͽ����ǵĹ�ϵ
		KeyItem key2,key3;
		Node234 parent,child3,child4;
		
		key3 = node.removeKeyItem();
		key2 = node.removeKeyItem();
		
		child3 = node.disconnectChild(2);
		child4 = node.disconnectChild(3);
		
		//����һ���ֵܽڵ�
		Node234 rightNode = new Node234();
		
		//����Ǹ��ڵ㣬��Ҫ�����µ�parent����ά����ϵ
		if(node == root){
			root = new Node234();
			parent = root;
			root.connectChild(0, node);
		}else{
			parent = node.getParent();
		}
		
		//�м��������ƶ������ڵ�
		int itemIndex = parent.insertKeyItem(key2);
		//ά��parnet�е�child�ڵ�Ĺ�ϵ
		int num = parent.getNumItems();
		for(int i=num-1;i>itemIndex;i--){
			//�ȶϿ��������¼���
			Node234 temp = parent.disconnectChild(i);
			parent.connectChild(i+1,temp);
		}
		//���µ��ֵܽڵ���븸�ڵ�
		parent.connectChild(itemIndex+1, rightNode);
		
		//ά���µ��ֵܽڵ�
		rightNode.insertKeyItem(key3);
		rightNode.connectChild(0, child3);
		rightNode.connectChild(1, child4);
	}
	
	public void displayTree(Node234 node){
		node.displayNode();
		for(int i=0;i<node.getNumItems()+1;i++){
			Node234 c = node.getChild(i);
			if(c==null){
				return;
			}else{
				displayTree(c);
			}
		}
	}
	
	public static void main(String[] args) {
		Tree234 t = new Tree234();
		
		t.insert(20);
		t.insert(10);
		t.insert(15);
		t.insert(19);
		t.insert(8);
		t.insert(13);
		t.insert(30);
		t.insert(40);
		t.insert(50);
		t.insert(38);
		t.insert(9);
		
		t.displayTree(t.root);
	}
}
