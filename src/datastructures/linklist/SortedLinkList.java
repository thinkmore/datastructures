package datastructures.linklist;
/**
 * ��ʾ��������Ļ�������
 */
public class SortedLinkList {
	private LinkNode firstNode;
	
	public void insertFirst(int id){
		LinkNode newLink = new LinkNode(id);
		
		//��Ҫ�����������Ӧ�ò����λ��
		LinkNode previous = null;
		LinkNode current = firstNode;
		//1��Ѱ�ҵ���һ��   ֵ�ȴ���ֵ ��  �Ľ��
		while(current!=null && id > current.getId()){
			previous = current;
			current = current.getNext();
		}
		//2�������½ڵ�
		if(previous == null){
			firstNode = newLink;
		}else{
			previous.setNext(newLink);
		}
		newLink.setNext(current);
	}
	public LinkNode removeFirst(){
		LinkNode temp = firstNode;
		firstNode = firstNode.getNext();
		return temp;
	}
	public LinkNode peekFirst(){
		return firstNode;
	}
	public boolean isEmpty(){
		return firstNode==null;
	}
	public LinkNode find(int id){
		LinkNode node = firstNode;
		//��firstNode�ڵ����²���		
		while(node.getId()!=id){
			if(node.getNext() == null){
				return null;
			}else{
				node = node.getNext();
			}
		}
		return node;
	}
	public LinkNode remove(int id){
		LinkNode needDelete = firstNode;
		LinkNode previous = firstNode;
		//������Ҫɾ���Ľ�㣬�Լ�����ǰ�����
		while(needDelete.getId() != id){
			if(needDelete.getNext() == null){
				return null;
			}else{
				previous = needDelete;
				needDelete = needDelete.getNext();
			}
		}
		//�������ý��Ĺ�ϵ
		if(needDelete.equals(firstNode)){
			firstNode = firstNode.getNext();
		}else{
			previous.setNext(needDelete.getNext());
		}
		
		return needDelete;
	}
	public void displayList(){
		System.out.println("====================================>");
		LinkNode tempNode = firstNode;
		while(tempNode!=null){
			tempNode.printLink();
			tempNode = tempNode.getNext();
		}
	}
	public static void main(String[] args) {
		SortedLinkList t = new SortedLinkList();
		
		t.insertFirst(2);
		t.insertFirst(6);
		t.insertFirst(3);
		t.insertFirst(9);
		
		t.displayList();
		
		t.removeFirst();
		
		t.displayList();
		
		LinkNode ret1 = t.find(6);
		System.out.println("now ret1===");
		ret1.printLink();
		t.displayList();
		
		t.remove(6);
		t.displayList();
	}
}
