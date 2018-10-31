package datastructures.btree;

import java.util.ArrayList;
import java.util.List;

/**
 * ��ʾB���Ĳ���
 */
public class BTree {
	/**
	 * B���Ľ���
	 */
	private int m = 0;
	
	private BTreeNode root = null;
	
	public BTree(int m){
		this.m = m;
		root = new BTreeNode(m);
	}
	
	/**
	 * ����key���Ҷ���
	 * @param key
	 * @return
	 */
	public Object[] find(int key){
		Object[] ret = new Object[2];
		BTreeNode current = root;
		int childIndex = -1;
		while(true){
			childIndex = current.findKeyItem(key);
			if(childIndex >=0 ){
				ret[0] = current;
				ret[1] = childIndex;
				return ret;
			}else if(current.isLeaf()){
				return null;
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
	private BTreeNode getNext(BTreeNode node,int key){
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
		BTreeNode current = root;
		KeyItem newItem = new KeyItem(id);
		boolean needInsert = true;
		//����Ҫ�����λ��
		while(true){
			current = this.findCurrent(current, id);
			
			if(current.isFull()){
				needInsert = false;
				//�������
				//1�����ڵ����
				this.splitNode(current,newItem);
				//2������current
				//2.1 �ȵõ�current�ĸ��ڵ㣬��Ϊ���ڵ������
				//2.2�ٴ��²�����һ���ڵ�
				current = current.getParent();
			}else if(current.isLeaf()){
				break;
			}
		}
		if(needInsert){
			//��ļ���
			current.insertKeyItem(newItem);
		}
	}
	/**
	 * ������Ҫ������ݵ�Ҷ�ӽڵ�
	 * @param current
	 * @param id
	 * @return
	 */
	private BTreeNode findCurrent(BTreeNode current,int id){
		while(true){
			if(current.isLeaf()){
				break;
			}else{
				current = this.getNext(current, id);
			}
		}
		return current;
	}
	
	private KeyItem[] insertKeyItem(KeyItem[] items,KeyItem newItem){
		//�Ӻ���ǰ�������ݲ��ҺͱȽ�
		for(int i=(items.length-1);i>=0;i--){
			if(items[i]==null){
				continue;
			}else{
				if(newItem.getId() > items[i].getId()){
					items[i+1] = newItem;
					return items;
				}else{
					items[i+1] = items[i];
				}
			}
		}
		items[0] = newItem;
		
		return items;
	}
	
	
	/**
	 * ���ѽڵ�
	 * @param node
	 */
	private KeyItem splitNode(BTreeNode node,KeyItem newItem){
		
		//1���Ȱ�newItem����node�����ü����м�Ԫ��
		KeyItem[] newKeyItems = new KeyItem[node.getNumItems()+1];
		//�Ȱ�nodeԭ����keyItem����
		for(int i=0;i<node.getNumItems();i++){
			newKeyItems = this.insertKeyItem(newKeyItems, node.getItem(i));
		}
		//�ټ����µ�
		newKeyItems = this.insertKeyItem(newKeyItems, newItem);
		//�����м�����λ��
		int middleIndex = (node.getM()+1)/2 - 1;
		
		//2����ʼ�ƶ�ʶ������
		
		//��ԭʼ�����ݼ�¼���������Ͽ����ǵĹ�ϵ
		KeyItem[] rightItems = new KeyItem[node.getM()-middleIndex-1];
		BTreeNode[] childs = new BTreeNode[node.getM()-middleIndex-1];
		
		BTreeNode parent;
		
		//��һ��ϴ�������ƶ����½ڵ�
		for(int i=0;i<rightItems.length;i++){
			rightItems[i] = newKeyItems[middleIndex+i+1];
		}
		//һ���С�����ݲ���������node����
		node.removeAllItems();
		for(int i=0;i<middleIndex;i++){
			node.insertKeyItem(newKeyItems[i]);
		}
		//�����ƶ����ұߵ�child
		for(int i=(childs.length-1);i>=0;i--){
			childs[i] = node.disconnectChild(middleIndex+i+1);
		}

		//����һ���ֵܽڵ�
		BTreeNode rightNode = new BTreeNode(m);
		
		//����Ǹ��ڵ㣬��Ҫ�����µ�parent����ά����ϵ
		if(node == root){
			root = new BTreeNode(m);
			parent = root;
			root.connectChild(0, node);
		}else{
			parent = node.getParent();
		}
		
		//�м��������ƶ������ڵ�
		boolean needSplitParent = false;
		KeyItem parentOldKeyItem = null;
		BTreeNode parentOldChildNode = null;
		
		if(parent.isFull()){
			needSplitParent = true;
			//��parent�����һ��keyItem�����һ��child����ɾ���ˣ��ڷ���parent�ڵ��ʱ���ټ������
			parentOldKeyItem = parent.removeKeyItem();
			parentOldChildNode = parent.getChild(m-1);
		}
		
		KeyItem upKeyItem = newKeyItems[middleIndex];
		int itemIndex = parent.insertKeyItem(upKeyItem);
		
		//ά��parnet�е�child�ڵ�Ĺ�ϵ
		int num = parent.getNumItems();
		for(int i=num-1;i>itemIndex;i--){
			//�ȶϿ��������¼���
			BTreeNode temp = parent.disconnectChild(i);
			parent.connectChild(i+1,temp);
		}
		//���µ��ֵܽڵ���븸�ڵ�
		parent.connectChild(itemIndex+1, rightNode);
		
		//ά���µ��ֵܽڵ�
		for(int i=0;i<rightItems.length;i++){
			rightNode.insertKeyItem(rightItems[i]);
		}
		for(int i=0;i<childs.length;i++){
			rightNode.connectChild(i, childs[i]);
		}

		//3�������ڵ��������
		if(needSplitParent){
			KeyItem lastUpKeyItem = this.splitNode(parent, parentOldKeyItem);
			if(lastUpKeyItem.getId() == parentOldKeyItem.getId()){
				//˵�����ϴ�ɾ����parent�����һ������ΪupKeyItem������
				//��ô��parentOldChildNode��Ӧ����ΪupKeyItem�ĵ�һ��child
				Object[] objs = this.find(upKeyItem.getId());
				BTreeNode tempNode = (BTreeNode)objs[0];
				
				//��ԭ�����λ�ú�������ݶ�����ƶ�һλ
				for(int i=0;i<tempNode.getNumItems();i++){
					tempNode.connectChild(i+1, tempNode.getChild(i));
				}
				tempNode.connectChild(0, parentOldChildNode);
			}else{
				Object[] objs = this.find(parentOldKeyItem.getId());
				BTreeNode tempNode = (BTreeNode)objs[0];
				int tempIndex = (Integer)objs[1];
				
				//��ԭ�����λ�ú�������ݶ�����ƶ�һλ
				for(int i=(tempIndex+1);i<tempNode.getNumItems();i++){
					tempNode.connectChild(i+1, tempNode.getChild(i));
				}
				tempNode.connectChild(tempIndex+1, parentOldChildNode);
			}			
		}
		
		return upKeyItem;
	}
	
	public void displayTree(BTreeNode node,int level,int childNumber){
		System.out.print("level=="+level+" , child=="+childNumber+"  ");
		node.displayNode();
		
		for(int i=0;i<node.getNumItems()+1;i++){
			BTreeNode c = node.getChild(i);
			if(c==null){
				return;
			}else{
				displayTree(c,level+1,i);
			}
		}
	}
////////////////////////////////////////////////////////////////////////////////////
	/**
	 * ɾ��ĳ��keyItem
	 * @param keyItemValue
	 */
	public void removeKeyItem(int keyItemValue){
//		1����ɾ�����Ϊ��Ҷ��㣬�ұ�ɾ�ؼ���Ϊ�ý���е�i���ؼ���key[i]��
//		��ɴ�C[i]��ָ���������ҳ���С�ؼ���Y������key[i]��λ�ã�Ȼ����Ҷ�����ɾȥY��
//		����һ�����Ͱ��ڷ�Ҷ���ɾ���ؼ���k������ͱ����ɾ��Ҷ�ӽ���еĹؼ��ֵ������ˡ�
		Object[] delObj = this.find(keyItemValue);
		if(delObj!=null){
			BTreeNode delNode = (BTreeNode)delObj[0];
			int delKeyIndex = (Integer)delObj[1];
			
			if(!delNode.isLeaf()){
				BTreeNode nowDelNode = this.findMinKeyItemNode(delNode.getChild(delKeyIndex+1));
				
				KeyItem minKeyItem = nowDelNode.getItem(0);
				//����key[i]��λ��
				delNode.getItems()[delKeyIndex] = minKeyItem;
				//Ȼ����Ҷ�����ɾȥY
				this.removeFromLeaf(nowDelNode, 0);
			}else{
				this.removeFromLeaf(delNode, delKeyIndex);
			}
		}
	}
	
	private BTreeNode findMinKeyItemNode(BTreeNode node){
		if(node!=null && node.isLeaf()){
			return node;
		}
		return this.findMinKeyItemNode(node.getChild(0));
	}
	
	private void removeFromLeaf(BTreeNode delNode,int delKeyIndex){
//	Ҫɾ���Ĺؼ�����Ҷ�ӽڵ��ϵ����
		//�Ȱ�����ؼ���ɾ����
		delNode.removeKeyItem(delKeyIndex);
		
		//Ȼ���ٵ���
		this.afterRemove(delNode);
	}
	/**
	 * ɾ��ʶ�����ĵ���
	 * @param delNode
	 */
	private void afterRemove(BTreeNode delNode){
		int n = delNode.getNumItems();
		int minM = (int)(Math.ceil(m/2));
		if(m%2==0){
			minM = minM-1;
		}
//		1�������ɾ�ؼ������ڽ���ԭ�ؼ��ָ���n��ceil(m/2)��ֱ��ɾ������������
		if(n>=minM){
			//
		}else if(n==(minM-1)){
//		2������������ֵܽ�����С����ࡱ�Ĺؼ��֣�����ý�����ڵ��ң����ֵܽ���еĹؼ�����Ŀ����ceil(m/2)��
//			��ɽ��ң����ֵܽ������С���󣩹ؼ�������������㣬
//			�����������С�����ڸ����ƹؼ��ֵĹؼ�����������ɾ�ؼ������ڽ����
			BTreeNode rightBrotherNode = this.getRightBrotherNode(delNode);
			BTreeNode leftBrotherNode = this.getLeftBrotherNode(delNode);
			
			//��������
			if(rightBrotherNode!=null && rightBrotherNode.getNumItems() > minM){
//				��ɽ����ֵܽ������С�ؼ��������������
				//�����������С�ڸ����ƹؼ��ֵĹؼ�����������ɾ�ؼ������ڽ����
				KeyItem rightMinKeyItem = rightBrotherNode.getItem(0);
				
				rightBrotherNode.removeKeyItem(0);
				//�ڸ��ڵ��в���Ҫ�����λ��
				int inParentIndex = delNode.getParent().getNumItems()-1;
				for(int i=0;i<delNode.getParent().getNumItems();i++){
					if(delNode.getParent().getItem(i).getId() > rightMinKeyItem.getId()){
						inParentIndex = i;
						break;
					}
				}
				
				delNode.insertKeyItem(delNode.getParent().getItem(inParentIndex));
				
				delNode.getParent().removeKeyItem(inParentIndex);
				
				delNode.getParent().insertKeyItem(rightMinKeyItem);
				
				//ͬʱ��ԭ��rightBrotherNode�ĵ�һ��child����Ӧ�ó�ΪdelNode��������child
				delNode.getChildren()[delNode.getNumItems()] = rightBrotherNode.getChild(0);
				
				if(rightBrotherNode.getChild(0)!=null){
					delNode.getChildren()[delNode.getNumItems()].setParent(delNode);
				}
				
				for(int i=1;i<(rightBrotherNode.getNumItems()+2);i++){
					rightBrotherNode.getChildren()[i-1] = rightBrotherNode.getChild(i);
				}
			}else if(leftBrotherNode!=null && leftBrotherNode.getNumItems() > minM){
//				��ɽ����ֵܽ�������ؼ�������������㣬
//				����������� ���ڸ����ƹؼ��ֵĹؼ�����������ɾ�ؼ������ڽ����
				BTreeNode leftMaxChild = leftBrotherNode.getChild(leftBrotherNode.getNumItems());
				KeyItem leftMaxKeyItem = leftBrotherNode.removeKeyItem();
				
				//�ڸ��ڵ��в���Ҫ�����λ��
				int inParentIndex = delNode.getParent().getNumItems()-1;
				for(int i=0;i<delNode.getParent().getNumItems();i++){
					if(delNode.getParent().getItem(i).getId() > leftMaxKeyItem.getId()){
						inParentIndex = i;
						break;
					}
				}
				
				delNode.insertKeyItem(delNode.getParent().getItem(inParentIndex));
				delNode.getParent().removeKeyItem(inParentIndex);
				delNode.getParent().insertKeyItem(leftMaxKeyItem);
				
				//ͬʱ��ԭ��leftBrotherNode�����һ��chid��Ӧ��ΪdelNode�ĵ�һ��child
				for(int i=(delNode.getNumItems()-1);i>=0;i--){
					delNode.getChildren()[i+1] = delNode.getChildren()[i];
				}
				delNode.getChildren()[0] = leftMaxChild;
				if(leftMaxChild!=null){
					leftMaxChild.setParent(delNode);
				}
			}else{
//		3����������ֵܽ����û�С����ࡱ�Ĺؼ��֣����Ҫɾ���ؼ��ֵĽ�������󣨻��ң��ֵܽ���Լ��������
//			�ָ���ߵĹؼ��ֺϲ���һ����㣬����ɾ���ؼ��ֺ󣬸ý����ʣ��Ĺؼ��֣����ϸ�����еĹؼ���Kiһ��
//			�ϲ���Ci��ָ���ֵܽ����ȥ��������ʹ������йؼ��ָ���С��ceil(m/2)����Դ˸������ͬ������
				
				BTreeNode parent = delNode.getParent();
				if(leftBrotherNode!=null){
					//�ڸ��ڵ��в��ҷָ�ؼ��ֵ�λ��
					int inParentIndex = 0;
					for(inParentIndex=0;inParentIndex<delNode.getParent().getNumItems();inParentIndex++){
						if(delNode.getParent().getItem(inParentIndex).getId() > leftBrotherNode.getItem(0).getId()){
							break;
						}
					}
					//�����ǰ�����ȥ��
					inParentIndex = (inParentIndex+1 > parent.getNumItems()) ? parent.getNumItems():inParentIndex+1;
					
					BTreeNode ci = parent.getChild(inParentIndex-1);
					//��delNode��ʣ��Ĺؼ��ּ��뵽ci
					for(int i=0;i<delNode.getNumItems();i++){
						if(delNode.getItem(i)!=null && delNode.getItem(i).getId()>0){
							ci.insertKeyItem(delNode.getItem(i));
						}
					}
					//��parent�ķָ�ؼ��ּ���ci
					ci.insertKeyItem(parent.getItem(inParentIndex-1));
					
					//ά��parent�е�child
					int oldNumItems = parent.getNumItems();
					parent.removeKeyItem(inParentIndex-1);
					
					for(int i=inParentIndex;i<oldNumItems;i++){
						parent.getChildren()[i] = parent.getChild(i+1);
						parent.getChildren()[i+1] = null;
					}
					if(oldNumItems==inParentIndex){
						parent.getChildren()[oldNumItems] = null;
					}
					
					//ci��childrenӦ����ciԭ����child + delNode��child
					List<BTreeNode> tempList = new ArrayList<BTreeNode>();
					for(int i=0;i<ci.getChildren().length;i++){
						if(ci.getChild(i)==null || ci.getChild(i).getNumItems()==0){
							break;
						}
						tempList.add(ci.getChild(i));
					}
					for(int i=0;i<delNode.getChildren().length;i++){
						if(delNode.getChild(i)==null || delNode.getChild(i).getNumItems()==0){
							break;
						}
						tempList.add(delNode.getChild(i));
					}
					
					//�����µ�ci��children
					BTreeNode[] ciChildren = new BTreeNode[ci.getNumItems()+1];
					
					for(int i=0;i<tempList.size();i++){
						ciChildren[i] = tempList.get(i);
						ciChildren[i].setParent(ci);
					}
					
					ci.setChildren(ciChildren);
					
					//������ڵ��Ǹ�Ԫ�صĻ�����Ҫ�����µĸ�
					if(parent.getNumItems()==0 && parent==root){
						root = ci;
						return;
					}
				}else if(rightBrotherNode!=null){
					int inParentIndex = parent.getNumItems()-1;
					for(int i=0;i<delNode.getParent().getNumItems();i++){
						if(delNode.getParent().getItem(i).getId() > rightBrotherNode.getItem(0).getId()){
							inParentIndex = i;
							break;
						}
					}
					//�����ǰ�����ȥ��
					inParentIndex = (inParentIndex-1 <0) ? 0:inParentIndex-1;
					
					BTreeNode ci = parent.getChild(inParentIndex+1);
					//��delNode��ʣ��Ĺؼ��ּ��뵽ci
					for(int i=0;i<delNode.getNumItems();i++){
						if(delNode.getItem(i)!=null && delNode.getItem(i).getId()>0){
							ci.insertKeyItem(delNode.getItem(i));
						}
					}
					//��parent�ķָ�ؼ��ּ���ci
					ci.insertKeyItem(parent.getItem(inParentIndex));
					
					//ά��parent�е�child
					int oldNumItems = parent.getNumItems();
					parent.removeKeyItem(inParentIndex);
					
					for(int i=inParentIndex+1;i<=oldNumItems;i++){
						parent.getChildren()[i-1] = parent.getChild(i);
						parent.getChildren()[i] = null;
					}
					//ci��childӦ����ԭ��delNode��child+ci��child
					List<BTreeNode> tempList = new ArrayList<BTreeNode>();
					
					for(int i=0;i<delNode.getChildren().length;i++){
						if(delNode.getChild(i)==null || delNode.getChild(i).getNumItems()==0){
							break;
						}
						tempList.add(delNode.getChild(i));
					}
					for(int i=0;i<ci.getChildren().length;i++){
						if(ci.getChild(i)==null || ci.getChild(i).getNumItems()==0){
							break;
						}
						tempList.add(ci.getChild(i));
					}
					
					//�����µ�ci��children
					BTreeNode[] ciChildren = new BTreeNode[ci.getNumItems()+1];
					
					for(int i=0;i<tempList.size();i++){
						ciChildren[i] = tempList.get(i);
						ciChildren[i].setParent(ci);
					}
					
					ci.setChildren(ciChildren);
					
					//������ڵ��Ǹ�Ԫ�صĻ�����Ҫ�����µĸ�
					if(parent.getNumItems()==0 && parent==root){
						root = ci;
						return;
					}
				}
				//�ݹ鴦���ڵ�
				if(parent!=null){
					this.afterRemove(parent);
				}
			}
		}		
	}
	/**
	 * ��ȡһ���ڵ�����ֽڵ�
	 * @param node
	 * @return
	 */
	private BTreeNode getRightBrotherNode(BTreeNode node){
		if(node!=root){
			int nodeIndex = -1;
			//�ڸ��ڵ��������Լ���child����
			for(int i=0;i<node.getParent().getChildren().length;i++){
				if(node.getParent().getChild(i) == node){
					nodeIndex = i;
					break;
				}
			}
			if(nodeIndex < (m-1)){
				return node.getParent().getChild(nodeIndex+1);
			}
			
		}
		return null;
	}
	/**
	 * ��ȡһ���ڵ�����ֽڵ�
	 * @param node
	 * @return
	 */
	private BTreeNode getLeftBrotherNode(BTreeNode node){
		if(node!=root){
			int nodeIndex = -1;
			//�ڸ��ڵ��������Լ���child����
			for(int i=0;i<node.getParent().getChildren().length;i++){
				if(node.getParent().getChild(i) == node){
					nodeIndex = i;
					break;
				}
			}
			if(nodeIndex > 0){
				return node.getParent().getChild(nodeIndex-1);
			}
			
		}
		return null;
	}
	
	
	public static void main(String[] args) {
		BTree t = new BTree(6);
		
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
		
		t.removeKeyItem(8);
		t.removeKeyItem(9);
		t.removeKeyItem(20);
		t.removeKeyItem(19);
		
		t.displayTree(t.root,0,0);
	}
}