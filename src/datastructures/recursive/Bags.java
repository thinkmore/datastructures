package datastructures.recursive;

import java.util.ArrayList;
import java.util.List;

/**
 * ��ʾ�õݹ��������������
 */
public class Bags {
	/**
	 * �õݹ��������������
	 * @param as ��Ʒ������������
	 * @param targetWeight ������Ŀ������
	 * @param nowIndex ��ǰ��ȡ��������
	 * @param listResult ��¼���
	 */
	public void bag(int[] as,int targetWeight,int nowIndex,List<Integer> listResult){
		if(nowIndex == as.length){
			return;
		}
		
		if(as[nowIndex] > targetWeight){
			//�������ж���һ����Ʒ�Ƿ����
			this.bag(as, targetWeight, ++nowIndex, listResult);
		}else if(as[nowIndex] == targetWeight){
			listResult.add(as[nowIndex]);
			System.out.println("one result===="+listResult);
			listResult.clear();
		}else{
			listResult.add(as[nowIndex]);
			this.bag(as, targetWeight-as[nowIndex], ++nowIndex, listResult);
		}
	}
	public static void main(String[] args) {
		Bags t = new Bags();
		
		int[] as = new int[]{11,8,7,5};
		
		for(int i=0;i<as.length;i++){
			t.bag(as, 19, i, new ArrayList<Integer>());
		}
		
	}
}
