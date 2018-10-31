package datastructures.binarytree;
/**
 * ??????????????
 */
public class BinaryTree {
	/**
	 * ?????
	 */
	private Node root;
	/**
	 * ??????????
	 * @param key ??????id?
	 * @return
	 */
	public Node find(int key){
		//??????
		Node current = root;
		while(current.getId()!=key){
			if(current.getId() > key){
				current = current.getLeftChild();
			}else if(current.getId() < key){
				current = current.getRightChild();
			}
			if(current == null){
				return null;
			}
		}		
		return current;
	}
	/**
	 * ??????????
	 * @param id
	 * @param data
	 */
	public void insert(int id,int data){
		//1??????????????
		Node newNode = new Node(id,data);

		if(root == null){
			root = newNode;
		}else{
			//2????????????¦Ë??		
			Node current = root;
			Node parent = null;
			
			while(true){
				parent = current;
				if(id < current.getId()){
					current = current.getLeftChild();
					//????????????
					if(current == null){
						//3?????????????????
						parent.setLeftChild(newNode);
						return;
					}
				}else{
					current = current.getRightChild();
					if(current==null){
						//3?????????????????
						parent.setRightChild(newNode);
						return;
					}
				}
			}
		}
	}
	/**
	 * ????????????
	 * @param node
	 */
	public void preOrder(Node node){
		if(node != null){
			System.out.println(node.getId()+" - ");
			preOrder(node.getLeftChild());
			preOrder(node.getRightChild());
		}
	}
	/**
	 * ?????????????
	 * @param node
	 */
	public void inOrder(Node node){
		if(node != null){
			inOrder(node.getLeftChild());
			System.out.println(node.getId()+" - ");
			inOrder(node.getRightChild());
		}
	}
	/**
	 * ?????????????
	 * @param node
	 */
	public void postOrder(Node node){
		if(node != null){
			postOrder(node.getLeftChild());
			postOrder(node.getRightChild());
			System.out.println(node.getId()+" - ");
		}
	}
	/**
	 * ?????§³???
	 * @return
	 */
	public Node getMinNode(){
		Node current = root;
		Node lastNode = null;
		while(current!=null){
			lastNode = current;
			current = current.getLeftChild();
		}
		return lastNode;
	}
	/**
	 * ????????
	 * @return
	 */
	public Node getMaxNode(){
		Node current = root;
		Node lastNode = null;
		while(current!=null){
			lastNode = current;
			current = current.getRightChild();
		}
		return lastNode;
	}
	/**
	 * ?????????
	 * @param key
	 * @return
	 */
	public boolean delete(int key){
		//1?????????????
		Node current = root;
		Node parent = root;
		boolean isLeftChild = true;
		
		while(current.getId()!=key){
			parent = current;
			if(current.getId() > key){
				isLeftChild = true;
				current = current.getLeftChild();
			}else if(current.getId() < key){
				isLeftChild = false;
				current = current.getRightChild();
			}
			if(current == null){
				return false;
			}
		}
		
		//2?????????
		if(current.getLeftChild()==null && current.getRightChild()==null){
			this.noChildren(parent, current, isLeftChild);
		}		
		//3????????????
		//???????
		else if(current.getRightChild()== null){
			this.oneLeftChild(parent, current, isLeftChild);
		}
		//???????
		else if(current.getLeftChild() == null){
			this.oneRightChild(parent, current, isLeftChild);
		}
		//4????????????
		else{
			//4.1?????????????
			Node successor = this.getSuccessor(current);
			if(current == root){
				root = successor;
			}else{
				if(isLeftChild){
					parent.setLeftChild(successor);
				}else{
					parent.setRightChild(successor);
				}
			}
			//????????successor??leftChild
			successor.setLeftChild(current.getLeftChild());
		}
		return true;
	}
	/**
	 * ???????????????????
	 * @param delNode
	 * @return
	 */
	private Node getSuccessor(Node delNode){
		Node successor = delNode;
		Node successorParent = delNode;
		Node current = delNode.getRightChild();
		//??????
		while(current!=null){
			successorParent = successor;
			successor = current;
			current = current.getLeftChild();
		}
		//??????????
		if(successor!=delNode.getRightChild()){
			successorParent.setLeftChild(successor.getRightChild());
			successor.setRightChild(delNode.getRightChild());
		}
		return successor;
	}
	private void oneRightChild(Node parent,Node current,boolean isLeftChild){
		if(current == root){
			root = current.getRightChild();
		}else{
			if(isLeftChild){
				parent.setLeftChild(current.getRightChild());
			}else{
				parent.setRightChild(current.getRightChild());
			}
		}
	}
	private void oneLeftChild(Node parent,Node current,boolean isLeftChild){
		if(current == root){
			root = current.getLeftChild();
		}else{
			if(isLeftChild){
				parent.setLeftChild(current.getLeftChild());
			}else{
				parent.setRightChild(current.getLeftChild());
			}
		}
	}
	
	private void noChildren(Node parent,Node current,boolean isLeftChild){
		if(current == root){
			root = null;
		}else{
			if(isLeftChild){
				parent.setLeftChild(null);
			}else{
				parent.setRightChild(null);
			}
		}
	}
	
	public static void main(String[] args) {
		BinaryTree t = new BinaryTree();
		
		t.insert(6,6);
		t.insert(5,2);
		t.insert(8,2433);
		t.insert(3, 5);
		t.insert(7,77);
		t.insert(9,233);
		
//		System.out.println(t.root.toString());
//		
//		t.inOrder(t.find(6));
//		
//		Node min = t.getMinNode();
//		Node max = t.getMaxNode();
//		System.out.println("min=="+min);
//		System.out.println("max=="+max);
		
		t.delete(8);
		
		System.out.println(t.root.toString());
	}
}
