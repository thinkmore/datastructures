package datastructures.recursive;

import java.math.BigInteger;

/**
 * ��ʾ�ݹ���׳�
 */
public class Factories {
	//N! = N * (N-1)!
	//5! = 5 * 4!
	//4! = 4*  3!
	//3! = 3*2*1

	
	
	public int factories(int n){
		//Ҫע���˳�����
		if(n==1){
			return 1;
		}
		return n * factories(n-1);
	}
	public BigInteger factories2(BigInteger n){
		//Ҫע���˳�����
		if(n.intValue()==1){
			return BigInteger.ONE;
		}
		return n.multiply(factories2(n.subtract(BigInteger.ONE)));
	}
	
	public static void main(String[] args) {
		Factories t = new Factories();
////		int ret = t.factories(56);
//		BigInteger ret = t.factories2(new BigInteger("100"));
//		System.out.println("ret=="+ret);

		int sum = 0;
		for(int i=0;i<=100;i++){
			
			sum +=i;
			
		}
		System.out.println("sum-=="+sum);
	
		int ret2 = t.mySum(100);
		System.out.println("ret2=="+ret2);
	}
	
	public int mySum(int n){
		if(n==1){
			return 1;
		}

		//ֻ��Ҫ����һ���Ĳ�����ʲô����
		
		return n+mySum(n-1);
	}
}

