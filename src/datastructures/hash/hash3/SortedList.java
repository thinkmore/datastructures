package datastructures.hash.hash3;
/**
 * ��װ�������
 */
public class SortedList {
	private Link first;
	
	/**
	 * ����Link����
	 * @param link
	 */
	public void insert(Link link){
		Link pre = null;
		Link current = first;
		while(current!=null && link.getKey() > current.getKey()){
			pre = current;
			current = current.getNext();
		}
		
		if(pre==null){
			first = link;
		}else{
			pre.setNext(link);
		}
		
		link.setNext(current);
	}
	/**
	 * ɾ��Link����
	 * @param key
	 */
	public void remove(int key){
		Link pre = null;
		Link current = first;
		while(current!=null && key != current.getKey()){
			pre = current;
			current = current.getNext();
		}
		
		if(pre==null){
			first = first.getNext();
		}else{
			pre.setNext(current.getNext());
		}
	}
	/**
	 * ����Link����
	 * @param key
	 * @return
	 */
	public Link findLink(int key){
		Link current = first;
		while(current!=null && key >= current.getKey()){
			if(current.getKey()==key){
				return current;
			}
			current = current.getNext();
		}
		return null;
	}
	
	public void displayList(){
		Link current = first;
		while(current!=null){
			System.out.print(current.toString());
			current = current.getNext();
		}
		System.out.println("");
	}
}