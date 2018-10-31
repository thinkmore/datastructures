package datastructures.superiorsort;
/**
 * ��ʾ��������
 */
public class QuickSort {
	/**
	 * ��������п�������
	 * @param as
	 * @param left
	 * @param right
	 */
	public void quickSort(int[] as,int left,int right){
		//�˳�����
		//ÿ��Ҫ������������
		int size = right-left + 1;
		if(size <= 3){
			//�Ͳ����ÿ��������ֹ������
			this.manualSort(as, left, right);
			
			return ;
		}
		//1����ȡ����ķ�����Ŧֵ
		int mv = this.medianValue(as, left, right);
		//2�����շ�����Ŧֵ��������� ����
		int centerIndex = this.partition(as, left, right, mv);
		//3������������ظ�ִ��quickSort
		this.quickSort(as, left, centerIndex-1);
		//4�����ұ������ظ�ִ��quickSort
		this.quickSort(as, centerIndex+1, right);
	}
	/**
	 * ���Ƕ�������з�����С�ķ�ǰ�棬��ķź��棬�м��Ƿ�����Ŧֵ
	 * @param as
	 * @param left
	 * @param right
	 * @param mv
	 * @return ������Ŧֵ������λ��
	 */
	private int partition(int[] as,int left,int right,int mv){
		
		//1����������ָʾλ�õ�����
		//��¼��ߵ�����λ��
		int leftIndex = left;
		//��¼�ұߵ�����λ��
		int rightIndex = right - 1;
		
		//2�����з��������ǰ�С��ֵ��ǰͷ����ķź�ͷ
		while(true){
			//��ߣ����������ҵ�һ���� mv ���ֵ��ͣ
			while(as[++leftIndex] < mv){
				//ʲô��������
			}
			//�ұߣ����������ҵ�һ���� mvС ��ֵ��ͣ
			while(as[--rightIndex] > mv){
				//ʲô��������
			}
			if(leftIndex >= rightIndex){
				break;
			}else{
				this.swap(as, leftIndex, rightIndex);
			}
		}
		//3������ֵ �ŵ� ������λ��
		this.swap(as, leftIndex, right-1);
		
		return leftIndex;
	}
	/**
	 * ���������Ŧֵ
	 * @param as
	 * @param left
	 * @param right
	 * @return
	 */
	private int medianValue(int[] as,int left,int right){
		int center = (left+right)/2;
		if(as[left] > as[center]){
			this.swap(as, left, center);
		}
		if(as[left] > as[right]){
			this.swap(as, left, right);
		}
		if(as[center] > as[right]){
			this.swap(as, center, right);
		}
		this.swap(as, center, right-1);
		return as[right-1];
	}
	private void swap(int[] as,int index1,int index2){
		int temp = as[index1];
		as[index1] = as[index2];
		as[index2] = temp;
	}
	/**
	 * �ֹ�����ֻ��3��ֵ����
	 * @param as
	 * @param left
	 * @param right
	 */
	private void manualSort(int[] as,int left,int right){
		int size = right - left + 1;
		
		if(size == 1){
			//��������
		}else if(size == 2){
			if(as[left] > as[right]){
				this.swap(as, left, right);
			}
		}else if(size == 3){
			if(as[left] > as[right-1]){
				this.swap(as, left, right-1);
			}
			if(as[left] > as[right]){
				this.swap(as, left, right);
			}
			if(as[right-1] > as[right]){
				this.swap(as, right-1, right);
			}
		}
	}
	
	public static void main(String[] args) {
		QuickSort t = new QuickSort();
		
		int[] as = new int[]{3,5,4,8,7,90,80,88};
		
		t.quickSort(as, 0, as.length-1);
		
		for(int a : as){
			System.out.println(a);
		}
	}
}
