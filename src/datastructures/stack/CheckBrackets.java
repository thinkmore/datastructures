package datastructures.stack;
/**
 * ��ʾ��ջ�������ʽ������(С���С���)�Ƿ�ɶԳ���
 */
public class CheckBrackets {
	public void check(String str){
		MyStack ms = new MyStack(20);
		
		//1�����ַ���ת�����ַ�����
		char[] cs = str.toCharArray();
		for(int i=0;i<cs.length;i++){
			char c = cs[i];
			//2�����ζ�ȡ�ַ��������  ���ŵ� ǰ�벿�֣�ѹ��ջ��
			if(c=='{' || c=='[' || c=='('){
				ms.push(c);
			}else if(c=='}' || c==']' || c==')'){
				//3������� ���ŵĽ������֣��ʹ�ջ���е���һ��ֵ������ƥ��
				char msc = (char)ms.pop();
				if((msc=='{' && c!='}')
					|| 	(msc=='[' && c!=']')
					|| 	(msc=='(' && c!=')')
						){
					System.out.println("sorry��ƥ�䲻�ɹ��������λ��=="+(i+1)+" ���ַ���");
				}else{
					System.out.println("ƥ��ɹ�");
				}
			}
		}
	}
	
	public static void main(String[] args) {
		CheckBrackets t = new CheckBrackets();
		t.check("(3+2)/5-[(7+8)*4-5]");
	}
}

//�����32+5/78+4*5--

//ջ��-54







