package datastructures.recursive;
/**
 * ��ʾʹ�õݹ���ʵ�ֹ鲢����
 */
public class MergeSort {
	/**
	 * ʹ�õݹ���ʵ�ֹ鲢����
	 * @param theArray Ҫ���������
	 */
	public void mergeSort(int[] theArray){
		int[] temp = new int[theArray.length];
		this.doMergeSort(theArray, temp, 0, theArray.length-1);
	}
	/**
	 * ����ִ�й鲢����ķ���
	 * @param theArray Ҫ���������
	 * @param temp ��ʱ�Ĺ鲢�õ�����
	 * @param lowerBound ��ߵ������߽磬���Ǵ��Ǹ�������ʼȡ����
	 * @param highBound �ұߵ������߽磬����ȡ���ݵ��������
	 */
	private void doMergeSort(int[] theArray,int[] temp,int lowerBound,int highBound){
		if(lowerBound >= highBound){
			return;
		}
		//1������ֽ������
		int mid = (lowerBound + highBound)/2;
		//2������߽�������
		doMergeSort(theArray, temp, lowerBound, mid);
		//3�����ұ߽�������
		doMergeSort(theArray, temp, mid+1, highBound);
		//4���ѽ���ϲ�����
		this.merge(theArray, temp, lowerBound, mid+1, highBound);
	}
	/**
	 * ���ϲ�����
	 * @param theArray  Ҫ���������
	 * @param temp ��ʱ�Ĺ鲢�õ�����
	 * @param lowIndex  ��ߵ������߽磬���Ǵ��Ǹ�������ʼȡ����
	 * @param highIndex �ұ߿�ʼ������λ��
	 * @param highBound �ұߵ������߽磬����ȡ���ݵ��������
	 */
	private void merge(int[] theArray,int[] temp,int lowIndex,int highIndex,int highBound){
		//���ϲ���temp��������¼
		int count = 0;
		//��¼��ߵ���С�����߽�
		int lowerBound = lowIndex;
		//��¼��ߵ���������߽�
		int mid = highIndex - 1;
		
		//1�����������ȡֵ���ұߵ��������αȽ�
		while(lowIndex<=mid && highIndex<=highBound){
			//1.1�����ߵ�ֵ���ұ�С���ǾͰ�������ֵ����temp
			if(theArray[lowIndex] < theArray[highIndex]){
				temp[count++] = theArray[lowIndex++];
			}else{
				//1.2�����ߵ�ֵ���ұߴ󣬰��ұ����ֵ����temp��Ȼ��������±Ƚ�
				temp[count++] = theArray[highIndex++];
			}
		}
		//2���ֱ������߻�ʣ�µ�����
		//2.1�� ������߻�ʣ�µ�����
		while(lowIndex<=mid){
			temp[count++] = theArray[lowIndex++];
		}
		//2.2�������ұ߻�ʣ�µ�����
		while(highIndex <= highBound){
			temp[count++] = theArray[highIndex++];
		}
		
		
		//3�����ź�˳������ݣ����¿�����theArray
		for(int i=0;i<(highBound-lowerBound + 1);i++){
			theArray[lowerBound + i] = temp[i];
		}
	}
	
	public static void main(String[] args) {
		MergeSort t = new MergeSort();
		
		int[] as = new int[]{4,8,5,9,7};
		
		t.mergeSort(as);
		
		for(int a : as){
			System.out.println(a);
		}		
	}
}
