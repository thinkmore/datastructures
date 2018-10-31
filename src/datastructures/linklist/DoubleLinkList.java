package datastructures.linklist;
/**
 * ��ʾ˫������
 */
public class DoubleLinkList {
	private LinkNode2 first;
	private LinkNode2 last;
	
	public boolean isEmpty(){
		return first==null;
	}
	public LinkNode2 peekFirst(){
		return first;
	}
	public void insertFirst(int id){
		LinkNode2 newLink = new LinkNode2(id);
		
		if(isEmpty()){
			last = newLink;
		}else{
			first.setPrevious(newLink);
		}
		newLink.setNext(first);
		
		first = newLink;
	}
	public void insertLast(int id){
		LinkNode2 newLink = new LinkNode2(id);
		if(isEmpty()){
			first = newLink;
		}else{
			last.setNext(newLink);
			newLink.setPrevious(last);
		}
		last = newLink;
	}	
	public LinkNode2 removeFirst(){
		LinkNode2 temp = first;
		if(first.getNext()==null){
			last = null;
		}else{
			first.getNext().setPrevious(null);
		}
		first = first.getNext();
		return temp;
	}
	public LinkNode2 find(int id){
		LinkNode2 node = first;
		//��first�ڵ����²���		
		while(node.getId()!=id){
			if(node.getNext() == null){
				return null;
			}else{
				node = node.getNext();
			}
		}
		return node;
	}
	public void displayListForward(){
		System.out.println("====================================>");
		LinkNode2 tempNode = first;
		while(tempNode!=null){
			tempNode.printLink();
			tempNode = tempNode.getNext();
		}
	}
	
	////////
	public LinkNode2 removeLast(){
		LinkNode2 temp = last;
		
		if(last.getPrevious() == null){
			first = null;
		}else{
			last.getPrevious().setNext(null);
		}
		last = last.getPrevious();
		
		return temp;
	}
	public LinkNode2 remove(int key){
		//1�����ҵ����Ҫɾ���Ľ��
		LinkNode2 node = this.find(key);
		//2�������������first����ô�Ͱ�����next��Ϊ�µ�first
		if(node == first){
			first = node.getNext();
		}else{
			//2.1 ���� �Ͱ�����ڵ�� previous�ڵ� �� next�����ó�Ϊ����ڵ��next
			node.getPrevious().setNext(node.getNext());
		}
		//3�������������last����ô�Ͱ�����previous�����Ϊ�µ�last
		if(node == last){
			last = node.getPrevious();
		}else{
			//3.1������ �Ͱ�����ڵ�� next�ڵ� ��previouse�����ó�Ϊ����ڵ��previous
			node.getNext().setPrevious(node.getPrevious());
		}
		
		return node;
	}
	public boolean insertAfter(int key,int id){
		//1�����ҵ�Ҫ�����������½�� �� ���
		LinkNode2 node = this.find(key);
		//2���ֱ�������������㣬Ҫ���������½ڵ�Ľ�㡢�½�㡢Ҫ���������½ڵ�� ��̽��
		LinkNode2 newLink = new LinkNode2(id);
		
		if(node == last){
			newLink.setNext(null);
			last = newLink;
		}else{
			newLink.setNext(node.getNext());
			node.getNext().setPrevious(newLink);
		}
		
		newLink.setPrevious(node);
		node.setNext(newLink);
		
		return true;
	}
	
	public void displayListBackward(){
		System.out.println("====================================>");
		LinkNode2 tempNode = last;
		while(tempNode!=null){
			tempNode.printLink();
			tempNode = tempNode.getPrevious();
		}
	}
	public static void main(String[] args) {
		DoubleLinkList t = new DoubleLinkList();
		
		t.insertFirst(1);
		t.insertFirst(2);
		t.insertFirst(3);
		
		t.displayListForward();
		t.displayListBackward();
		
		t.insertLast(4);
		t.insertLast(5);
		t.insertLast(6);
		
		t.displayListForward();
		t.displayListBackward();
		
		t.removeFirst();
		t.displayListForward();
		t.displayListBackward();
		
		t.removeLast();
		t.displayListForward();
		t.displayListBackward();
		
		t.remove(1);
		t.displayListForward();
		t.displayListBackward();
		
		t.insertAfter(2, 77);
		t.displayListForward();
		t.displayListBackward();	
	}
}