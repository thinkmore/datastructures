package datastructures.simplesort;
/**
 * ��ʾ���뷨����
 */
public class InsertSort {
	public void insertSort(int[] as){
		//�ٶ���һ��λ�����ź���
		int j = 0;
		for(int i=1;i < as.length;i++){
			int temp = as[i];
			for(j=i;j>0;j--){
				if(as[j-1] >= temp){
					as[j] = as[j-1];
				}else{
					break;
				}
			}
			as[j] = temp;
		}
	}
	private void printDatas(int[] as){
		System.out.println("======================>");
		for(int d : as){
			System.out.println(d);
		}
	}
	public static void main(String[] args) {
		InsertSort t = new InsertSort();
		int[] as = new int[]{3,2,8,6};
		
		t.insertSort(as);
		t.printDatas(as);
	}
}
