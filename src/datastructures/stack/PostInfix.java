package datastructures.stack;
/**
 * ��ʾ��ջ��ʵ�� ����׺���ʽת����Ϊ��׺���ʽ
 */
public class PostInfix {
	/**
	 * ����׺���ʽת����Ϊ��׺���ʽ
	 * @param str Ҫת������׺���ʽ
	 * @return ת���ɵĺ�׺���ʽ
	 */
	public String doTransfer(String str){
		StringBuffer buffer = new StringBuffer();
		MyStack ms = new MyStack(20);
		
		//1�����ַ���ת����Ϊ�ַ�����
		char[] cs = str.toCharArray();
		//2����ÿ���ַ������ж���ִ����Ӧ�Ĳ���
		for(int i=0;i<cs.length;i++){
			char c = (char)cs[i];
			//2.1������ǲ���������Ҫ�ּ������
			if(c=='+' || c=='-'){
				doOperation(ms,buffer,c, 1);
			}else if(c=='*' || c=='/'){
				doOperation(ms,buffer,c, 2);
			}
			//2.2������������ţ�ѹջ
			else if(c=='('){
				ms.push(c);
			}
			//2.3������������ţ���ջ������У�ֱ��������δ֪
			else if(c==')'){
				doRightBracket(ms,buffer);
			}
			//2.4������ǲ�������ֱ�Ӽ��뵽���
			else{
				buffer.append(c);
			}
		}
		//3����ջ�еĲ��������ε����������
		while(!ms.isEmpty()){
			buffer.append((char)ms.pop());
		}
		
		return buffer.toString();
	}
	/**
	 * �������������
	 * @param ms
	 * @param buffer
	 * @param c
	 * @param level
	 */
	private void doOperation(MyStack ms,StringBuffer buffer,char c,int level){
		//1�����δ�ջ����ȡһ��ֵ
		while(!ms.isEmpty()){
			char topC = (char)ms.pop();
			//2�������ֵ����������ݽ��бȽ�
			//2.1�����ջ���������ǣ������������ǰ���ѹ����
			if(topC=='('){
				ms.push(topC);
				break;
			}else{
				//���Ȼ�ȡ��ջ��Ԫ������Ӧ�����ȼ���
				int topLevel = 0;
				if(topC=='+' || topC=='-'){
					topLevel = 1;
				}else{
					topLevel = 2;
				}

				if(topLevel >= level){
					//2.2�����ջ�������ݵ����ȼ�����ڵ��ڴ�������ݼ�����ô��Ҫ���
					buffer.append(topC);
				}else{
					//2.3�����ջ�����������ȼ�С�ڴ�������ݼ��𣬲���
					ms.push(topC);
					break;
				}
			}
		}
		//3���ҵ�λ�ú󣬰Ѵ���Ĳ�����ѹ��
		ms.push(c);
	}
	/**
	 * ���������ŵ����
	 * @param c
	 */
	private void doRightBracket(MyStack ms,StringBuffer buffer){
		//1����ջ�е������ݣ��������׺���ʽ��
		while(!ms.isEmpty()){
			char topC = (char)ms.pop();
			//2��ֱ������"("Ϊֹ
			if(topC=='('){
				break;
			}else{
				buffer.append(topC);
			}
		}
	}

	public static void main(String[] args) {
		PostInfix t = new PostInfix();
		String ret = t.doTransfer("(3+2)/5-((7+8)*4-5)");
		System.out.println("ret==="+ret);
	}
}
