package datastructures.superiorsort;
/**
 * ��ʾϣ������
 */
public class ShellSort {

	public void shellSort(int[] as){
		
		int increment = 1;
		//1���������������
		while(increment <= as.length/3){
			increment = increment*3 + 1;
		}
		//3��Ȼ������С����ļ�����ظ���2��
		int j=0;
		int temp=0;
		for(;increment > 0;increment=(increment-1)/3){
			//2����ÿһ����в��뷨����
			for(int i=increment;i<as.length;i++){
				temp = as[i];
				for(j=i;j>=increment;j-=increment){
					if(temp < as[j-increment]){
						as[j] = as[j-increment];
					}else{
						break;
					}
				}
				as[j] = temp;
			}
		}		
	}
	
	public static void main(String[] args) {
		ShellSort t = new ShellSort();
		int[] as = new int[]{3,5,4,8,7,90,80,88};
		t.shellSort(as);
		
		for(int a : as){
			System.out.println(a);
		}
	}
}
