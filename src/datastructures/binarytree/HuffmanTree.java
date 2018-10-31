package datastructures.binarytree;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * ��ʾӦ��Haffman�㷨��ʵ��ѹ���ͽ�ѹ
 */
public class HuffmanTree {
	public String decompress(String fileName){
		DataInputStream in = null;
		String srcContent = "";
		try{
			in = new DataInputStream(new FileInputStream(new File(fileName)));
//		1����ȡ������Ϣ�������������
			Map<String,String> map = readCodes(in);
//		2�����ؾ������������
			byte[] datas = this.readDatas(in); 
//		3���Ѷ��ص��ֽڻ�ԭ�ɶ�Ӧ����������,����char
			int[] dataInts = this.bytes2IntArray(datas);
//		4�����������������ɵĹ��������룬����ת����ԭʼ���ַ����Ӷ��õ�ԭʼ������
			srcContent = this.huffman2Char(map, dataInts); 
		}catch(Exception err){
			err.printStackTrace();
		}finally{
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return srcContent;
	}
	/**
	 * ��ѹ������ԭʼ������
	 * @param map
	 * @param dataInts
	 * @return
	 */
	private String huffman2Char(Map<String,String> map,int[] dataInts){
		StringBuffer buffer = new StringBuffer();
		//1�����������黹ԭ��Ϊ��Ӧ���ַ�����Ҳ����haffman���봮
		String str = this.int2BinaryString(dataInts);
		//2��Ȼ���huffman���봮�����滻���ַ�
		while(str.length()>0){
			for(String code : map.keySet()){
				if(str.startsWith(code)){
					buffer.append(map.get(code));
					str = str.substring(code.length());
					break;
				}
			}
		}
		return buffer.toString();
	}
	/**
	 * ��intֵת���ض�Ӧ�� �����Ʊ����ʾ�� �ַ���
	 * @param as
	 * @return
	 */
	private String int2BinaryString(int[] as){
		int len = as.length;
		//���ÿ��byte��Ӧ�Ķ������ַ���
		String[] ss = new String[len];
		
		String binaryStr = "";
		
		for(int i=0;i<len-1;i++){
			//�õ�ÿ��������Ӧ�Ķ������ַ���
			ss[i] = Integer.toBinaryString(as[i]);
			ss[i] = this.addZero(ss[i]);
			binaryStr += ss[i];
		}
		//����β��ԭ��������ĸ���������ȥ����Щ����0
		int zeros = as[len-1];
		if(zeros > 0){
			binaryStr = binaryStr.substring(0,binaryStr.length() - zeros);
		}
		
		return binaryStr;
	}
	/**
	 * ��ÿ���������ַ��������8λ
	 * @param str
	 * @return
	 */
	private String addZero(String str){
		if(str.length()<8){
			int zeroNum = 8-str.length();
			for(int i=0;i<zeroNum;i++){
				str = "0"+str;
			}
		}
		return str;
	}
	
	/**
	 * ����Щbyteת����д��ȥ��charֵ
	 * @param datas
	 * @return
	 */
	private int[] bytes2IntArray(byte[] datas){
		int[] as = new int[datas.length];
		for(int i=0;i<datas.length;i++){
			if(datas[i] >= 0){
				as[i] = datas[i];
			}else{
				as[i] = datas[i] + 256;
			}
		}
		
		return as;
	}
	/**
	 * ��ȡ���ݲ���
	 * @param in
	 * @return
	 */
	private byte[] readDatas(DataInputStream in)throws Exception{
		//1�������ж��ٸ�byte
		int dataByteNum =  in.readInt();
		//2��������byte����
		byte[] bs = new byte[dataByteNum];
		//3��ѭ����ÿ��byte��ȡ����
		for(int i=0;i<dataByteNum;i++){
			bs[i] = in.readByte();
		}
		return bs;
	}
	/**
	 * ��ȡ���
	 * @param in
	 * @return Map,key--���ַ���Ӧ��Haffuman���룬value- �ַ�
	 */
	private Map<String,String> readCodes(DataInputStream in)throws Exception{
		Map<String,String> map = new HashMap<String,String>(); 
		//1�����ر���ĸ���
		int codeNum = in.readInt();
		//2������ÿ���ַ������볤�ȡ�Haffuman����
		for(int i=0;i<codeNum;i++){
			char codeChar = in.readChar();
			int codeLen = in.readInt();
			String code = "";
			char[] cs = new char[codeLen];
			for(int j=0;j<cs.length;j++){
				code += in.readChar();
			}
			
			map.put(code, ""+codeChar);
		}
		return map;
	}
	
/////////////////////////////////////////////////////////////////////////////	
	public void compress(String str,String outFile){
//		1��ͳ�ƣ�����Ҫѹ����Դ�ļ���ͳ���ַ����ֵĴ���
		HuffmanPriorityQueue queue = this.statistics(str);
//		2��������������������
		HuffmanNode tree = this.buidHuffmanTree(queue); 
//		3�����룺�Թ�����������߼�0���ұ߼�1���Ϳ��Եõ��ַ��Ĺ��������롣
		Map<String,String> map = new HashMap<String,String>();
		this.buildHuffmanCode(map, tree, "");
//		System.out.println("map=="+map);
//		4��������ѱ�����������������ѹ���������
		this.outData(str, map,outFile);
	}
	/**
	 * ������ݵ��ļ�
	 * @param str ԭʼ����
	 * @param map Huffman����
	 * @param outFile ����ļ���·�����ļ���
	 */
	private void outData(String str,Map<String,String> map,String outFileName){
		File outFile = new File(outFileName);
		DataOutputStream os = null;
		try{
			os = new DataOutputStream(new FileOutputStream(outFile));
			//1��������
			this.outCodes(os, map);
			//2�����Դ���ݵ�ÿ���ַ���Ӧ��huffman����
			String dataHuffmanCode = this.source2HumanStr(str, map);
			this.outDataHuffmanCode(os, dataHuffmanCode);
		}catch(Exception err){
			err.printStackTrace();
		}finally{
			try {
				os.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * ���ԭʼ������ת����Ϊhuffman���봮
	 * @param os
	 * @param dataHuffmanCode
	 */
	private void outDataHuffmanCode(DataOutputStream os,String dataHuffmanCode)throws Exception{
		//1��Ҫ�����huffman���봮 ת����Ϊ ��Ӧ�� byte[]
		byte[] bs = this.string2ByteArrays(dataHuffmanCode);
		//2�����byte����ĸ���
		os.writeInt(bs.length);
		//3�����byte����
		os.write(bs);
	}
	/**
	 * ��һ�������Ƕ����Ʊ���Ĵ���ת����Ϊ�����Ķ���������
	 * @param dataHuffmanCode �����Ƕ����Ʊ���Ĵ�
	 * @return
	 */
	private byte[] string2ByteArrays(String dataHuffmanCode){
		byte[] retBytes = null;
		
		char[] cs = dataHuffmanCode.toCharArray();
		int len = cs.length;
		int lenByte = 0;
		
		//1:�ж��������ĳ����Ƿ��ܱ�8����
		if(len % 8 == 0){
			//2���������Ļ�����8λ��Ϊһ��byte
			lenByte = len/8 + 1;
			retBytes = new byte[lenByte];
			for(int i=0;i<lenByte -1;i++){
				String s = "";
				for(int j=i*8;j<(i+1)*8;j++){
					s += cs[j];
				}
				retBytes[i] = this.chars2byte(s); 
			}
			//���ò�0�ĸ���
			retBytes[lenByte - 1] = 0;
		}else{
			//3�����������Ļ������һ���ַ��������油0��ʹ���Ϊ8λ����Ϊһ��byte��ͬʱҪ��¼����ĸ���
			lenByte = len/8 + 2;
			retBytes = new byte[lenByte];
			int zeroNum = 8 - len % 8;
			//��0
			for(int i=0;i<zeroNum;i++){
				dataHuffmanCode +="0";
			}
			//���¼���char����
			cs = dataHuffmanCode.toCharArray();
			for(int i=0;i<lenByte -1;i++){
				String s = "";
				for(int j=i*8;j<(i+1)*8;j++){
					s += cs[j];
				}
				retBytes[i] = this.chars2byte(s); 
			}
			//���ò�0�ĸ���
			retBytes[lenByte - 1] = (byte)zeroNum;
		}
		
		return retBytes;
	}
	/**
	 * ��һ��char�ַ���ת����byte
	 * @param s
	 * @return
	 */
	private byte chars2byte(String s){
		byte ret = 0;
		char[] cs = s.toCharArray();
		
		for(int i=0;i<cs.length;i++){
			//����ÿһλchar �����������byteֵ
			byte tempB = (byte)(Byte.parseByte(""+cs[i])*Math.pow(2,cs.length-i-1));
			ret = (byte)(ret+tempB);
		}		
		return ret;
	}
	
	/**
	 * ��ԭʼ������ת����Ϊhuffman���봮
	 * @param str
	 * @param map
	 * @return
	 */
	private String source2HumanStr(String str,Map<String,String> map){
		StringBuffer buffer = new StringBuffer();
		char[] cs = str.toCharArray();
		for(char c : cs){
			buffer.append(map.get(""+c));
		}
		return buffer.toString();
	}
	/**
	 * ������
	 * @param os
	 * @param map
	 */
	private void outCodes(DataOutputStream os,Map<String,String> map)throws Exception{
		//1�������ĸ���
		os.writeInt(map.size());
		for(String key : map.keySet()){
			//2�����ÿ���ַ����Լ�����ĳ���
			os.writeChar(key.charAt(0));
			os.writeInt(map.get(key).length());
			//3�����ÿ���ַ���Ӧ��Huffman����
			os.writeChars(map.get(key));
		}
	}
	
	/**
	 * ����Huffman��������ÿ���ַ���Ӧ��Huffman����
	 * @param map
	 * @param tree
	 * @param zeroOrOneStr
	 */
	private void buildHuffmanCode(Map<String,String> map,HuffmanNode tree,String zeroOrOneStr){
		//1����û���ӽڵ�
		if(tree.getLeftChild() == null && tree.getRightChild()==null){
			map.put(""+tree.getC(), zeroOrOneStr);
		}
		//2�������ӽڵ�
		if(tree.getLeftChild()!=null){
			this.buildHuffmanCode(map, tree.getLeftChild(), zeroOrOneStr+"0");
		}
		//3�������ӽڵ�
		if(tree.getRightChild()!=null){
			this.buildHuffmanCode(map, tree.getRightChild(), zeroOrOneStr+"1");
		}
	}
	/**
	 * ����Huffman��
	 * @param queue 
	 * @return
	 */
	private HuffmanNode buidHuffmanTree(HuffmanPriorityQueue queue){
		while(queue.size() > 1){
			//1����ȡ������СȨ�صĶ���
			HuffmanNode n1 = queue.remove();
			HuffmanNode n2 = queue.remove();
			//2����������������ĸ�����
			HuffmanNode n3 = new HuffmanNode((char)0,n1.getCount()+n2.getCount());
			n3.setLeftChild(n1);
			n3.setRightChild(n2);
			//3���Ѹ��������ص���������
			queue.insert(n3);
		}
		return queue.peekFront();
	}
	/**
	 * ͳ���������ַ����ֵĴ�����������Ȩ�ص����ȼ�����
	 * @param str ԭʼ��Ҫѹ��������
	 * @return
	 */
	private HuffmanPriorityQueue statistics(String str){
		//1��ͳ�ƴ���
		Map<Character,Integer> map = new HashMap<Character,Integer>();
		char[] cs = str.toCharArray();
		for(char c : cs){
			Object obj = map.get(c);
			if(obj==null){
				map.put(c, 1);
			}else{
				map.put(c, ((Integer)obj)+1);
			}
		}
		//2���������ȼ�����
		HuffmanPriorityQueue queue = new HuffmanPriorityQueue(map.size());
		for(char c : map.keySet()){
			HuffmanNode node = new HuffmanNode(c, map.get(c));
			queue.insert(node);
		}
		return queue;
	}
	/**
	 * ���ļ���ȡҪѹ��������
	 * @param fileName
	 * @return
	 */
	public String readFile(String fileName){
		StringBuffer buffer = new StringBuffer();
		DataInputStream in = null;
		
		try {
			in = new DataInputStream(new FileInputStream(new File(fileName)));
			String tempStr = "";
			while((tempStr=in.readLine())!=null){
				buffer.append(tempStr+"\n");
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return buffer.toString();
	}
	public static void main(String[] args) {
		HuffmanTree t = new HuffmanTree();
		
		//t.compress(t.readFile("HuffmanTree.java"), "temp.txt");
		
		String s = t.decompress("temp.txt");
		System.out.println("s=="+s);
	}
}
