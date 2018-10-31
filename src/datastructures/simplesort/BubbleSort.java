package datastructures.simplesort;
/**
 * ��ʾð�������㷨
 */
public class BubbleSort {
	public void bubbleSort(int[] as){
		//i�������ƱȽϵĴ���
		for(int i=as.length-1;i>0;i--){
			//�����ѭ����ɣ���ʾһ��ð�����
			for(int j=0;j<i;j++){
				if(as[j] > as[j+1]){
					//���ǰ������ݱȺ�������ݴ�Ӧ�ý���
					swap(as,j,j+1);
				}
			}
		}
	}
	
	public void bubbleSort2(int[] as){
		//���ȡһ�������Ƚϵ�����λ��
		for(int i=0;i<as.length-1;i++){
			//�ڲ�ѭ������ȡ����Ҫ�����Ƚϵ�����
			for(int j=i+1;j<as.length;j++){
				if(as[i] < as[j]){
					swap(as,i,j);
				}
			}
		}
	}
	
	private void swap(int[] as,int aIndex,int bIndex){
		int temp = as[aIndex];
		as[aIndex] = as[bIndex];
		as[bIndex] = temp;
	}
	private void printDatas(int[] as){
		System.out.println("======================>");
		for(int d : as){
			System.out.println(d);
		}
	}
	public static void main(String[] args) {
		BubbleSort t = new BubbleSort();
		int[] as = new int[]{3,2,8,6};
		
		t.bubbleSort2(as);
		t.printDatas(as);
		
	}
}
