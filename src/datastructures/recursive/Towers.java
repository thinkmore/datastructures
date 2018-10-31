package datastructures.recursive;
/**
 *	��ʾ�õݹ��������ŵ������
 */
public class Towers {
	/**
	 * �õݹ��������ŵ������
	 * @param topN ���Ǻ�ŵ���Ŀ���
	 * @param src ����Ҫ�ƶ��ĺ�ŵ���Ŀ����ڵ���
	 * @param temp ���������ƶ�
	 * @param dest ���Ǻ�ŵ���Ŀ�Ҫ�������
	 */
	public void transfer(int topN,String src,String temp,String dest){
		if(topN==1){
			System.out.println(topN +" �� "+src +" �ƶ��� "+dest);
		}else{
			//1����topN-1����һ�����壬�ƶ�topN-1��temp
			transfer(topN-1,src,dest,temp);
			//2����topN�ƶ���dest
			System.out.println(topN +" �� "+src +" �ƶ��� "+dest);
			//3����topN-1�ƶ���dest
			transfer(topN-1,temp,src,dest);
		}
	}
	
	public static void main(String[] args) {
		Towers t = new Towers();
		t.transfer(4,"A", "B", "C");
	}
}
