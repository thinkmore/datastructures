package datastructures.arrays.order;
/**
 * ������ʾֱ��ʹ�����ݲ�����������,��Ų��ظ���ֵ
 */
public class OperateOrderNoIndexBinarySearch {
	private int[] datas = null;
	private int currentIndex = 0;
	
	public OperateOrderNoIndexBinarySearch(int length){
		datas = new int[length];
	}
	
	public int insert(int data){
		//����˳��������
		int index = 0;
		//1����������dataӦ�ô�ŵ�λ��
		for(index=0;index<currentIndex;index++){
			if(datas[index] > data){
				break;
			}
		}
		//2�������λ�ü����������ݣ�����ƶ�һλ
		for(int i=currentIndex;i>index;i--){
			datas[i] = datas[i-1];
		}
		//3����data���õ�Ӧ�ô�ŵ�λ��
		datas[index] = data;
		
		currentIndex++;
		return currentIndex-1;
	}
	private int binarySearch(int data){
		int index = -1;
		//������ʾС����ߵ�����
		int lowIndex = 0;
		//������ʾ�����ߵ�����
		int highIndex = currentIndex - 1;
		
		while(true){
			//1���ҵ��м������λ��
			index = (lowIndex + highIndex)/2;
			//2����Ҫ���ҵ����ݺ��м�����λ�õ����ݽ��бȽ�
			if(lowIndex > highIndex){
				//û���ҵ�����
				return -1;
			}else if(datas[index]==data){
				return index;
			}else{
				if(data < datas[index]){
					highIndex = index - 1;
				}else{
					lowIndex = index + 1;
				}
			}
		}
	}
	
	public void remove(int data){
		//1������������ݶ�Ӧ������
		int index = this.binarySearch(data);
		//2��ͬǰһ����ʾ
		for(int i=index;i<currentIndex;i++){
			datas[i] = datas[i+1];
		}
		currentIndex--;
	}
	public int searchOne(int data){
		//1������������ݶ�Ӧ������
		int index = this.binarySearch(data);
		//2������У��ͷ���datas�е�����
		if(index >=0 ){
			return datas[index];
		}		
		//3�����û�У��ͷ���0��
		return 0;
	}	
	public void printDatas(){
		System.out.println("======================================>");
		for(int d : datas){
			System.out.println(d);
		}
	}
	public static void main(String[] args) {
		OperateOrderNoIndexBinarySearch t = new OperateOrderNoIndexBinarySearch(10);
		
		t.insert(3);
		t.insert(6);
		t.insert(1);
		t.insert(2);
		
		t.printDatas();
		
		t.remove(1);
		t.printDatas();
		
		int ret = t.searchOne(3);
		System.out.println("ret=="+ret);
	}
}
