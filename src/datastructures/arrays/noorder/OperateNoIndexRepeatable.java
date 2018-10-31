package datastructures.arrays.noorder;

import java.util.ArrayList;
import java.util.List;

/**
 * ������ʾֱ��ʹ�����ݲ�����������,�ɴ���ظ�ֵ
 */
public class OperateNoIndexRepeatable {
	private int[] datas = null;
	private int currentIndex = 0;
	
	public OperateNoIndexRepeatable(int length){
		datas = new int[length];
	}
	
	public int insert(int data){
		datas[currentIndex] = data;
		currentIndex++;
		return currentIndex-1;
	}
	/**
	 * ��ȡ����data�������е�����λ��
	 * @param begin ��ʼ���ҵ�λ��
	 * @param data Ҫ��������������
	 * @return �������ʼλ�������һ������
	 */
	private int getIndex(int begin,int data){
		int index = -1;
		for(int i=begin;i<currentIndex;i++){
			if(datas[i] == data){
				index = i;
				break;
			}
		}
		return index;
	}
	
	public void remove(int data){
		//1������������ݶ�Ӧ������
		int index = this.getIndex(0,data);
		//ѭ��������������ݣ�ֱ���Ҳ���λ��
		while(index>=0){
			//2��ͬǰһ����ʾ��ɾ��һ������
			for(int i=index;i<currentIndex;i++){
				datas[i] = datas[i+1];
			}			
			currentIndex--;
			//�ٴβ��Һ��������ݶ�Ӧ������
			index = this.getIndex(index,data);
		}
	}
	public List<Integer> searchOne(int data){
		List<Integer> retList = new ArrayList<Integer>();
		//1������������ݶ�Ӧ������
		int index = this.getIndex(0,data);
		while(index >= 0){
			//2������У��ͼ��뵽Ҫ���صļ�����
			retList.add(datas[index]);
			//�ٴβ��Һ��������ݶ�Ӧ������
			index = this.getIndex(index+1,data);
		}
		return retList;
	}	
	public void printDatas(){
		System.out.println("======================================>");
		for(int d : datas){
			System.out.println(d);
		}
	}
	public static void main(String[] args) {
		OperateNoIndexRepeatable t = new OperateNoIndexRepeatable(10);
		
		t.insert(3);
		t.insert(6);
		t.insert(1);
		t.insert(2);
		t.insert(2);
		t.insert(6);
		
		t.printDatas();
		
		t.remove(6);
		t.printDatas();
		
		List<Integer> ret = t.searchOne(2);
		System.out.println("ret=="+ret);
	}
}
