package datastructures.arrays.object;
/**
 * ������ʾֱ��ʹ�����ݲ�����������,��Ų��ظ��� ����
 */
public class OperateOrderNoIndexObject {
	private UserModel[] datas = null;
	private int currentIndex = 0;
	
	public OperateOrderNoIndexObject(int length){
		datas = new UserModel[length];
	}
	
	public int insert(UserModel data){
		//����˳��������
		int index = 0;
		//1����������dataӦ�ô�ŵ�λ��
		for(index=0;index<currentIndex;index++){
			if(datas[index].getUuid() > data.getUuid()){
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
	private int getIndex(int uuid){
		int index = -1;
		for(int i=0;i<currentIndex;i++){
			if(datas[i].getUuid() == uuid){
				index = i;
				break;
			}
		}
		return index;
	}
	public void remove(int uuid){
		//1������������ݶ�Ӧ������
		int index = this.getIndex(uuid);
		//2��ͬǰһ����ʾ
		for(int i=index;i<currentIndex;i++){
			datas[i] = datas[i+1];
		}
		currentIndex--;
	}
	public UserModel searchOne(int uuid){
		//1������������ݶ�Ӧ������
		int index = this.getIndex(uuid);
		//2������У��ͷ���datas�е�����
		if(index >=0 ){
			return datas[index];
		}		
		//3�����û�У��ͷ���null
		return null;
	}	
	public void printDatas(){
		System.out.println("======================================>");
		for(UserModel d : datas){
			System.out.println(d);
		}
	}
	public static void main(String[] args) {
		OperateOrderNoIndexObject t = new OperateOrderNoIndexObject(10);
		
		t.insert(new UserModel(1,"����1",1));
		t.insert(new UserModel(6,"����6",6));
		t.insert(new UserModel(3,"����3",3));
		t.insert(new UserModel(2,"����2",2));
		
		t.printDatas();
		
		t.remove(1);
		t.printDatas();
		
		UserModel ret = t.searchOne(2);
		System.out.println("ret=="+ret);
	}
}
