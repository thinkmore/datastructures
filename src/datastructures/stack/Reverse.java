package datastructures.stack;
/**
 * ��ʾӦ��ջ��ʵ���ַ�����ת
 */
public class Reverse {
	public String doRev(String str){
		StringBuffer buffer=new StringBuffer();
		
		MyStack ms = new MyStack(20);
		//1�����ַ�������charһ��һ����ȡ����
		char[] cs = str.toCharArray();
		for(char c : cs){
			//2������Щ�ַ�����ѹ��ջ��
			ms.push(c);
		}
		//3�����δ�ջ�е���char�������µ��ַ���
		while(!ms.isEmpty()){
			char c = (char)ms.pop();
			buffer.append(c);
		}
		return buffer.toString();
	}
	
	public static void main(String[] args) {
		Reverse t = new Reverse();
		String ret = t.doRev("this is ��  test");
		System.out.println("ret==="+ret);
	}	
}
