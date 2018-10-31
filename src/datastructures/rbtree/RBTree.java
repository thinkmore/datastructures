package datastructures.rbtree;
/**
 * ��ʾ������Ĳ���
 */
public class RBTree {
	/**
	 * ���ڵ�
	 */
	private RBNode root;
	/**
	 * ������ʾ�ҵ��Ľڵ�����Ǹ��ڵ�����ӻ�������
	 */
	private boolean isLeftChild = true;
/////////////////////////////////////////////////////////////////////////////
	/**
	 * �ڼ���һ���½ڵ�󣬶Ժ��������У�鲢����
	 * @param nowNode
	 */
	private void afterInsert(RBNode nowNode){
//		1�����������Ǹ��ڵ㣬��ôΥ������2����ֱ�Ӱѽڵ��޸�Ϊ��ɫ
		if(nowNode.getParent()==null){
			nowNode.setRed(false);
			root = nowNode;
		}else if(!nowNode.getParent().isRed()){
//		2���������ڵ�ĸ��ڵ��Ǻ�ɫ�ģ�˵�����Ϲ���ʲô������
		}else if(nowNode.getParent().isRed()){
			RBNode g = nowNode.getParent().getParent();
			RBNode u = null;
			if(g!=null){
				u = (g.getLeftChild()==nowNode.getParent())?g.getRightChild():g.getLeftChild();
			}
			if(u!=null && u.isRed()){
//		3���������ڵ�ĸ��ڵ��Ǻ�ɫ�ģ����游������һ���ӽڵ㣨����ڵ㣩Ҳ�Ǻ�ɫ�ģ�
//			��ô�����游�ڵ��죬��������ڵ��ڣ�Ȼ�������游�ڵ�Ϊ��ǰ�ڵ㣬Ȼ�����¿�ʼ�жϡ�
				g.setRed(true);
				u.setRed(false);
				nowNode.getParent().setRed(false);
				
				nowNode = g;
				this.afterInsert(nowNode);
			}else if((u==null || !u.isRed())
					&& nowNode == nowNode.getParent().getLeftChild()
					&& (g!=null && nowNode.getParent()==g.getLeftChild())
				){
//		4���������ڵ�ĸ��ڵ��Ǻ�ɫ������ڵ��Ǻ�ɫ���Ҳ���ڵ����丸�����ӽڵ㣬�����ڵ����游�ڵ�����ӽڵ㣬
//			��ô���Ѹ��ڵ��Ϊ��ɫ���游�ڵ��Ϊ��ɫ��Ȼ����游�ڵ����������Ȼ�����¿�ʼ�жϡ�
				nowNode.getParent().setRed(false);
				g.setRed(true);
				
				this.rightRotate(g);
				
				this.afterInsert(nowNode);
			}else if((u==null || !u.isRed())
					&& nowNode == nowNode.getParent().getRightChild()
					&& (g!=null && nowNode.getParent()==g.getRightChild())
				){
//		5���������ڵ�ĸ��ڵ��Ǻ�ɫ������ڵ��Ǻ�ɫ���Ҳ���ڵ����丸�����ӽڵ㣬�����ڵ����游�ڵ�����ӽڵ㣬
//			��ô���Ѹ��ڵ��Ϊ��ɫ���游�ڵ��Ϊ��ɫ��Ȼ����游�ڵ����������Ȼ�����¿�ʼ�жϡ�
				nowNode.getParent().setRed(false);
				g.setRed(true);
				
				this.leftRotate(g);
				
				this.afterInsert(nowNode);
			}else if((u==null || !u.isRed())
					&& nowNode == nowNode.getParent().getRightChild()
					&& (g!=null && nowNode.getParent()==g.getLeftChild())
				){
//		6���������ڵ�ĸ��ڵ��Ǻ�ɫ������ڵ��Ǻ�ɫ���Ҳ���ڵ����丸�����ӽڵ㣬�����ڵ����游�ڵ�����ӽڵ㣬
//			��ô���ѵ�ǰ�ڵ�ĸ��ڵ���Ϊ�µĵ�ǰ�ڵ㣬���µĵ�ǰ�ڵ����������Ȼ�����¿�ʼ�жϡ�
				RBNode oldParent = nowNode.getParent();
				
				this.leftRotate(oldParent);
				
				this.afterInsert(oldParent);			
			}else if((u==null || !u.isRed())
					&& nowNode == nowNode.getParent().getLeftChild()
					&& (g!=null && nowNode.getParent()==g.getRightChild())
				){
//		7���������ڵ�ĸ��ڵ��Ǻ�ɫ������ڵ��Ǻ�ɫ���Ҳ���ڵ����丸�����ӽڵ㣬�����ڵ����游�ڵ�����ӽڵ㣬
//			��ô���ѵ�ǰ�ڵ�ĸ��ڵ���Ϊ�µĵ�ǰ�ڵ㣬���µĵ�ǰ�ڵ����������Ȼ�����¿�ʼ�ж�
				RBNode oldParent = nowNode.getParent();
				
				this.rightRotate(oldParent);
				
				this.afterInsert(oldParent);
			}
		}
		
	}
	/**
	 * ��������
	 * @param node
	 */
	private void rightRotate(RBNode node){
		//��¼�����ڵ� ԭʼ�����ӽڵ�
		RBNode oldLeftChild = node.getLeftChild();
		//��¼�����ڵ� ԭʼ�����ӽڵ� �� ���ӽڵ�
		RBNode oldLeftRightChild = null;
		
		if(oldLeftChild!=null){
			oldLeftRightChild = oldLeftChild.getRightChild();
		}
		
		if(node.getParent()!=null){
			//�ж��Ǹ��ڵ�����ӻ�������
			boolean isLeftChild = (node.getParent().getLeftChild()==node);
			if(isLeftChild){
				node.getParent().setLeftChild(oldLeftChild);
			}else{
				node.getParent().setRightChild(oldLeftChild);
			}
			if(oldLeftChild!=null){
				oldLeftChild.setParent(node.getParent());
			}
		}else{
			oldLeftChild.setParent(null);
			oldLeftChild.setRed(false);
			root = oldLeftChild;
		}
		
		if(oldLeftChild!=null){
			oldLeftChild.setRightChild(node);
		}
		node.setParent(oldLeftChild);
		
		node.setLeftChild(oldLeftRightChild);
		if(oldLeftRightChild!=null){
			oldLeftRightChild.setParent(node);
		}
	}
	/**
	 * ��������
	 * @param node
	 */
	private void leftRotate(RBNode node){
		RBNode oldRightChild = node.getRightChild();
		RBNode oldRightLeftChild = null;
		if(oldRightChild!=null){
			oldRightLeftChild = oldRightChild.getLeftChild();
		}
		
		if(node.getParent()!=null){
			//�ж��Ǹ��ڵ�����ӻ�������
			boolean isLeftChild = (node.getParent().getLeftChild()==node);
			if(isLeftChild){
				node.getParent().setLeftChild(oldRightChild);
			}else{
				node.getParent().setRightChild(oldRightChild);
			}
			if(oldRightChild!=null){
				oldRightChild.setParent(node.getParent());
			}
		}else{
			oldRightChild.setParent(null);
			oldRightChild.setRed(false);
			root = oldRightChild;			
		}
		
		if(oldRightChild!=null){
			oldRightChild.setLeftChild(node);
		}
		node.setParent(oldRightChild);
		
		node.setRightChild(oldRightLeftChild);
		if(oldRightLeftChild!=null){
			oldRightLeftChild.setParent(node);
		}
	}
////////////////////////////////////////////////////////////////////////////	
	/**
	 * ɾ���ڵ��ĵ�������
	 * @param node
	 */
	private void afterDelete(RBNode nowNode){
//		1����ǰ�ڵ��Ǻ죬��ô��ֱ�Ӱѵ�ǰ�ڵ��ɺ�ɫ������
		if(nowNode.isRed()){
			nowNode.setRed(false);
		}else if(nowNode.getParent()==null){
//		2����ǰ�ڵ��Ǻ����Ǹ��ڵ㣬��ô��ʲô��������������
			nowNode.setRed(false);
		}else if(!nowNode.isRed()){
			RBNode g = nowNode.getParent().getParent();
			RBNode b = (nowNode==nowNode.getParent().getLeftChild())?nowNode.getParent().getRightChild():
				nowNode.getParent().getLeftChild();
			
			if(b.isRed() && nowNode==nowNode.getParent().getLeftChild()){
//		3����ǰ�ڵ��Ǻ����ֵܽڵ�Ϊ��ɫ����ǰ�ڵ�Ϊ���ڵ�����ӽڵ㣬
//			��ô�����ֵܽ���ɸ��ڵ����ɫ���Ѹ��ڵ��ɺ�ɫ��Ȼ���ڸ��ڵ����������������¿�ʼ�жϡ�
				b.setRed(nowNode.getParent().isRed());
				nowNode.getParent().setRed(true);
				
				this.leftRotate(nowNode.getParent());
				
				this.afterDelete(nowNode);
			}else if(b.isRed() && nowNode==nowNode.getParent().getRightChild()){
//		4����ǰ�ڵ��Ǻ����ֵܽڵ�Ϊ��ɫ����ǰ�ڵ�Ϊ���ڵ�����ӽڵ㣬
//			��ô�����ֵܽ���ɸ��ڵ����ɫ���Ѹ��ڵ��ɺ�ɫ��Ȼ���ڸ��ڵ����������������¿�ʼ�жϡ�
				b.setRed(nowNode.getParent().isRed());
				nowNode.getParent().setRed(true);
				
				this.rightRotate(nowNode.getParent());
				
				this.afterDelete(nowNode);
			}else if(!b.isRed() && !nowNode.getParent().isRed()
					&& (b.getLeftChild()==null || !b.getLeftChild().isRed())
					&& (b.getRightChild()==null || !b.getRightChild().isRed())
				){
//		5����ǰ�ڵ��Ǻ��Ҹ��ڵ���ֵܽڵ㶼Ϊ��ɫ���ֵܽڵ�������ӽڵ�ȫΪ��ɫ��
//			��ô�����ֵܽڵ��죬Ȼ��Ѹ��ڵ㵱���µĵ�ǰ�ڵ㣬�����¿�ʼ�ж�
				b.setRed(true);
				nowNode = nowNode.getParent();
				
				this.afterDelete(nowNode);
			}else if(!b.isRed() && nowNode.getParent().isRed()
					&& (b.getLeftChild()==null || !b.getLeftChild().isRed())
					&& (b.getRightChild()==null || !b.getRightChild().isRed())){
//		6����ǰ�ڵ��Ǻ����ֵܽڵ�Ϊ��ɫ���ֵܽڵ�������ӽڵ㶼�Ǻ�ɫ�����Ǹ��ڵ��Ǻ�ɫ��
//			��ô���Ͱ��ֵܽڵ��죬���ڵ��ڣ�����
				b.setRed(true);
				nowNode.getParent().setRed(false);
				
			}else if(!b.isRed() && nowNode == nowNode.getParent().getLeftChild()
					&& (b.getLeftChild()!=null && b.getLeftChild().isRed())
					&& (b.getRightChild()==null || !b.getRightChild().isRed())
				){
//		7����ǰ�ڵ��Ǻ����ֵܽڵ�Ϊ��ɫ���ֵܽڵ�������Ǻ�ɫ�������Ǻ�ɫ�����ҵ�ǰ�ڵ��Ǹ��ڵ�����ӽڵ㣬
//			��ô�����ֵܽڵ��죬�ֵ����ӽڵ��ڣ�Ȼ����ֵܽڵ���������������¿�ʼ�ж�
				b.setRed(true);
				b.getLeftChild().setRed(false);
				
				this.rightRotate(b);
				
				this.afterDelete(nowNode);
			}else if(!b.isRed() && nowNode == nowNode.getParent().getRightChild()
					&& (b.getRightChild()!=null && b.getRightChild().isRed())
					&& (b.getLeftChild()==null || !b.getLeftChild().isRed())){
//		8����ǰ�ڵ��Ǻ����ֵܽڵ�Ϊ��ɫ���ֵܽڵ�������Ǻ�ɫ�������Ǻ�ɫ�����ҵ�ǰ�ڵ��Ǹ��ڵ�����ӽڵ㣬
//			��ô�����ֵܽڵ��죬�ֵ����ӽڵ��ڣ�Ȼ����ֵܽڵ������������¿�ʼ�ж�
				b.setRed(true);
				b.getRightChild().setRed(false);
				
				this.leftRotate(b);
				
				this.afterDelete(nowNode);
			}else if(!b.isRed() && nowNode == nowNode.getParent().getLeftChild()
					&& (b.getRightChild()!=null && b.getRightChild().isRed())
					){
//		9����ǰ�ڵ��Ǻ����ֵܽڵ�Ϊ��ɫ���ֵܽڵ�������Ǻ�ɫ�����ӵ���ɫ���⣬���ҵ�ǰ�ڵ��Ǹ��ڵ�����ӽڵ㣬
//			��ô�����ֵܽڵ��ɵ�ǰ�ڵ㸸�ڵ����ɫ���ѵ�ǰ�ڵ㸸�ڵ��ڣ��ֵܽڵ����ӱ�ڣ�Ȼ���Ե�ǰ�ڵ�ĸ��ڵ�Ϊ֧�����������������
				b.setRed(nowNode.getParent().isRed());
				nowNode.getParent().setRed(false);
				
				b.getRightChild().setRed(false);
				
				this.leftRotate(nowNode.getParent());
			}else if(!b.isRed() && nowNode == nowNode.getParent().getRightChild()
					&& (b.getLeftChild()!=null && b.getLeftChild().isRed())){
//		10����ǰ�ڵ��Ǻ����ֵܽڵ�Ϊ��ɫ���ֵܽڵ�������Ǻ�ɫ�����ӵ���ɫ���⣬���ҵ�ǰ�ڵ��Ǹ��ڵ�����ӽڵ㣬
//			��ô�����ֵܽڵ��ɵ�ǰ�ڵ㸸�ڵ����ɫ���ѵ�ǰ�ڵ㸸�ڵ��ڣ��ֵܽڵ����ӱ�ڣ�Ȼ���Ե�ǰ�ڵ�ĸ��ڵ�Ϊ֧�����������������
				b.setRed(nowNode.getParent().isRed());
				nowNode.getParent().setRed(false);
				
				b.getLeftChild().setRed(false);
				
				this.rightRotate(nowNode.getParent());
			}
		}
	}
	/**
	 * ɾ����ʱ������������Ҫɾ���Ľڵ�
	 * @param node
	 * @param key
	 * @return
	 */
	private RBNode findOneNode(RBNode node,int key){
		if(node!=null){
			if(node.getId()==key){
				return node;
			}
			RBNode tempNode = findOneNode(node.getLeftChild(), key);
			if(tempNode!=null){
				if(tempNode==tempNode.getParent().getLeftChild()){
					this.isLeftChild = true;
				}else{
					this.isLeftChild = false;
				}
				return tempNode;
			}
			
			tempNode = findOneNode(node.getRightChild(), key);
			if(tempNode!=null){
				if(tempNode==tempNode.getParent().getLeftChild()){
					this.isLeftChild = true;
				}else{
					this.isLeftChild = false;
				}
				return tempNode;
			}
		}
		return null;
	}
	
///////////////////////////////////////////////////////////////////////////
	/**
	 * ����һ���ڵ�
	 * @param key Ҫ���ҵ�idֵ
	 * @return
	 */
	public RBNode find(int key){
		//��ǰ�ڵ�
		RBNode current = root;
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
	 * ����һ���ڵ�
	 * @param id
	 * @param data
	 */
	public void insert(int id,int data){
		//1���ȴ���һ���½ڵ�
		RBNode newNode = new RBNode(id,data,true);

		if(root == null){
			root = newNode;
		}else{
			//2������Ҫ�����λ��		
			RBNode current = root;
			RBNode parent = null;
			
			while(true){
				parent = current;
				if(id < current.getId()){
					current = current.getLeftChild();
					//���û�����ӽڵ�
					if(current == null){
						//3���޸���Ӧ�Ľڵ������
						parent.setLeftChild(newNode);
						
						//add
						newNode.setParent(parent);
						break;
					}
				}else{
					current = current.getRightChild();
					if(current==null){
						//3���޸���Ӧ�Ľڵ������
						parent.setRightChild(newNode);
						
						//add
						newNode.setParent(parent);
						break;
					}
				}
			}
		}
		
		//����������󣬿�ʼ����
		this.afterInsert(newNode);
	}
	/**
	 * ǰ���ȡ�ڵ�����
	 * @param node
	 */
	public void preOrder(RBNode node){
		if(node != null){
			String pId = "";
			if(node.getParent()!=null && node.getParent().getId() > 0){
				pId = ""+node.getParent().getId();
			}
			if(node.getId()>=0){
				System.out.println(node.getId()+","+node.isRed()+","+pId+" --- ");
			}
			preOrder(node.getLeftChild());
			preOrder(node.getRightChild());
		}
	}
	/**
	 * �����ȡ�ڵ�����
	 * @param node
	 */
	public void inOrder(RBNode node){
		if(node != null){
			inOrder(node.getLeftChild());
			System.out.println(node.getId()+" - ");
			inOrder(node.getRightChild());
		}
	}
	/**
	 * �����ȡ�ڵ�����
	 * @param node
	 */
	public void postOrder(RBNode node){
		if(node != null){
			postOrder(node.getLeftChild());
			postOrder(node.getRightChild());
			System.out.println(node.getId()+" - ");
		}
	}
	/**
	 * ��ȡ��С�ڵ�
	 * @return
	 */
	public RBNode getMinNode(){
		RBNode current = root;
		RBNode lastNode = null;
		while(current!=null){
			lastNode = current;
			current = current.getLeftChild();
		}
		return lastNode;
	}
	/**
	 * ��ȡ���ڵ�
	 * @return
	 */
	public RBNode getMaxNode(){
		RBNode current = root;
		RBNode lastNode = null;
		while(current!=null){
			lastNode = current;
			current = current.getRightChild();
		}
		return lastNode;
	}
	/**
	 * ɾ��һ���ڵ�
	 * @param key
	 * @return
	 */
	public boolean delete(int key){
		//1���ҵ�Ҫɾ���Ľڵ�
		RBNode current = root;
		RBNode parent = root;
		
		//������¼���汻ɾ�ڵ���Ǹ��ڵ�
		RBNode nowNode = null;
		
		current = this.findOneNode(root, key);
		if(current == null){
			return true;
		}
		parent = current.getParent();
		
		//2��û���ӽڵ�
		if((current.getLeftChild()==null || current.getLeftChild().getId()<0) 
				&& (current.getRightChild()==null || current.getRightChild().getId()<0)){
			this.noChildren(parent, current, isLeftChild);
			
			if(!current.isRed()){
				nowNode = new RBNode(-1, -1,false);
				nowNode.setParent(current.getParent());
				
				if(parent!=null){
					if(this.isLeftChild){
						parent.setLeftChild(nowNode);
					}else{
						parent.setRightChild(nowNode);
					}
				}
			}
			
			current.setParent(null);
		}		
		//3��ֻ��һ���ӽڵ�
		//ֻ����ڵ�
		else if(current.getRightChild()== null || current.getRightChild().getId()<0){
			this.oneLeftChild(parent, current, isLeftChild);
			
			if(!current.isRed() && current.getLeftChild().isRed()){
				current.getLeftChild().setRed(false);
			}else if(!current.isRed() && current.getLeftChild().isRed()){
				nowNode = current.getLeftChild();
			}			
		}
		//ֻ���ҽڵ�
		else if(current.getLeftChild() == null || current.getLeftChild().getId()<0){
			this.oneRightChild(parent, current, isLeftChild);
			
			if(!current.isRed() && current.getRightChild().isRed()){
				current.getRightChild().setRed(false);
			}else{
				nowNode = current.getRightChild();
			}
		}
		//4���������ӽڵ�
		else{
			//4.1���ҵ������̽ڵ�
			RBNode successor = this.getSuccessor(current);
			
			//4.2���������ڵ�����ݽ���һ�£���Ҫ������ɫ��Ҳ���ı���ԭ�еĸ��ӵȹ�ϵ��
			RBNode tempNode = new RBNode(successor.getId(), successor.getData(), successor.isRed());
			
			successor.setId(current.getId());
			successor.setData(current.getData());
			
			current.setId(tempNode.getId());
			current.setData(tempNode.getData());
			
			//4.3Ȼ�����½���ɾ��
			this.delete(successor.getId());			
		}
		
		//ɾ���ڵ�󣬽�������ƽ��
		if(nowNode!=null){
			afterDelete(nowNode);
		}
		return true;
	}
	/**
	 * �ҵ�Ҫɾ���ڵ�������̽ڵ�
	 * @param delNode
	 * @return
	 */
	private RBNode getSuccessor(RBNode delNode){
		RBNode successor = delNode;
		RBNode successorParent = delNode;
		RBNode current = delNode.getRightChild();
		//���ҽڵ�
		while(current!=null){
			successorParent = successor;
			successor = current;
			//Ҫ���˵�id=-1�Ŀ��ӽڵ�
			if(current.getLeftChild()!=null && current.getLeftChild().getId()>0){
				current = current.getLeftChild();
			}else{
				current = null;
			}
		}
		//������Ӧ��ֵ
//		if(successor!=delNode.getRightChild()){
//			successorParent.setLeftChild(successor.getRightChild());
//			
//			if(successor.getRightChild()!=null){
//				successor.getRightChild().setParent(successorParent);
//			}
//			
//			successor.setRightChild(delNode.getRightChild());
//			delNode.getRightChild().setParent(successor);
//		}
		return successor;
	}
	private void oneRightChild(RBNode parent,RBNode current,boolean isLeftChild){
		if(current == root){
			root = current.getRightChild();
			
			current.getRightChild().setParent(null);
		}else{
			if(isLeftChild){
				parent.setLeftChild(current.getRightChild());
				
				current.getRightChild().setParent(parent);
			}else{
				parent.setRightChild(current.getRightChild());
				
				current.getRightChild().setParent(parent);
			}
		}
	}
	private void oneLeftChild(RBNode parent,RBNode current,boolean isLeftChild){
		if(current == root){
			root = current.getLeftChild();
			
			current.getLeftChild().setParent(null);
		}else{
			if(isLeftChild){
				parent.setLeftChild(current.getLeftChild());
				
				current.getLeftChild().setParent(parent);
			}else{
				parent.setRightChild(current.getLeftChild());
				
				current.getLeftChild().setParent(parent);
			}
		}
	}
	
	private void noChildren(RBNode parent,RBNode current,boolean isLeftChild){
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
		RBTree t = new RBTree();
		
		t.insert(6,6);
		t.insert(5,2);
		t.insert(8,2433);
		t.insert(3, 5);
		t.insert(7,77);
		t.insert(9,233);
		
		
		t.delete(6);
		
		t.preOrder(t.root);
		
		
	}
}
