package datastructures.binarytree;
/**
 * Haffman���Ľڵ����
 */
public class HuffmanNode {
	/**
	 * �ַ�
	 */
	private char c;
	/**
	 * ���ֵĴ�����Ҳ����Ȩ��
	 */
	private int count;
	
	private HuffmanNode leftChild;
	private HuffmanNode rightChild;
	
	public HuffmanNode(char c, int count) {
		super();
		this.c = c;
		this.count = count;
	}
	public char getC() {
		return c;
	}
	public void setC(char c) {
		this.c = c;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public HuffmanNode getLeftChild() {
		return leftChild;
	}
	public void setLeftChild(HuffmanNode leftChild) {
		this.leftChild = leftChild;
	}
	public HuffmanNode getRightChild() {
		return rightChild;
	}
	public void setRightChild(HuffmanNode rightChild) {
		this.rightChild = rightChild;
	}
	@Override
	public String toString() {
		return "HuffmanNode [c=" + c + ", count=" + count + ", leftChild="
				+ leftChild + ", rightChild=" + rightChild + "]";
	}
	
	
}
