package datastructures.superiorsort;
/**
 * ��ʾ��������
 */
public class RadixSort {
	/**
	 * ʵ�ֻ�������
	 * @param as Ҫ���������
	 * @param maxSite ������ ������� ���λ��
	 */
	public void sort(int[] as,int maxSite){
		
		//1����¼��ǰ���ڴ����λ��
		int nowSite = 1;
		//��¼10�����ֵ
		int[][] temp = new int[10][as.length];
		//��¼ ÿ�μ���λ����ʱ����Ҫ /��ֵ
		int n = 1;
		//������¼ 0-9 ÿ��Ͱ�ڵ�ֵ�ĸ���
		int[] order = new int[10];
		
		while(nowSite <= maxSite){
			//����ĳһλ����
			//2�����ո�λ����ֵ�������ݷ�ɢ��temp����ȥ
			for(int i=0;i<as.length;i++){
				int siteNum = (as[i]/n)%10;
				temp[siteNum][order[siteNum]] = as[i];
				order[siteNum] = order[siteNum] +1;
			}
			//3������0-9��˳�򣬰�temp�����ֵ�����ûص�ԭʼ��������
			int k = 0;
			for(int i=0;i<10;i++){
				if(order[i]!=0){
					for(int j=0;j<order[i];j++){
						as[k] = temp[i][j];
						k++;
					}
				}
				//��Ͱ�ڸ�����Ϊ0
				order[i] = 0;
			}
			//������һλҪ/��nֵ
			n *= 10;
			nowSite++;
		}		
	}
	
	public static void main(String[] args) {
		RadixSort t = new RadixSort();
		int[] as = new int[]{3,5,4,8,7,90,180,88};
		t.sort(as, 3);
		
		for(int a : as){
			System.out.println(a);
		}
	}
}
