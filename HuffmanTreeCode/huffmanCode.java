package HuffmanTreeCode;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
//import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class huffmanCode {

	public static void main(String[] args) {
		//ѹ���ļ�
		String src = "G:\\src.jpg";
		String dst = "G:\\dst.zip";
		FileZip(src, dst);
		System.out.println("ѹ�����");
		//��ѹ�ļ�
		String src2 = "G:\\dst.zip";
		String dst2 = "G:\\src2.jpg";
		unFileZip(src2, dst2);
		System.out.println("��ѹ���");
//		//ԭ���ַ������ȣ�40
//		String string = "i like like like java do you like a java";
//		//��ȡѹ��ǰ���ֽ�����
//		byte[] bytes = string.getBytes();
//		System.out.println("ѹ��ǰ���ֽ����飺"+Arrays.toString(bytes));
//		
//		//��ȡѹ������ֽ�����
//		byte[] by=getHuffmanZip(bytes);
//		//ѹ������ĳ��ȣ�17��ѹ����57.5%
//		System.out.println("ѹ����ĵ��ֽ����飺"+Arrays.toString(by));
//		
//		//��ȡ��ѹ����ֽ�����
//		byte[] b=deCode(HuffmanCodes, by);
//		System.out.println("��ѹ����ֽ����飺"+Arrays.toString(b));
//		System.out.println("ת��Ϊ�ַ���"+new String(b));
	}
	/**
	 * ���ܣ���ѹ������ֽ�����bytes���н��룬����ԭ�ȵ��ַ�����Ӧ���ֽ�����
	 * @param map ѹ��ʱ�Ĺ����������
	 * @param bytes ѹ������ֽ�����
	 * @return ����ԭ�ȵ��ַ�����Ӧ���ֽ�����
	 */
	private static byte[] deCode(Map<Byte, String> map,byte[] bytes) {
		StringBuilder stringBuilder=new StringBuilder();
		//ͨ��forѭ����bytes�ֽ�����תΪ�������ַ���
		for(int i=0;i<bytes.length;i++) {
			//�ж�i�Ƿ�Ϊ���һ������������һ������Ҫ����
			boolean flag=(i==bytes.length-1);
			stringBuilder.append(byteToBitString(!flag, bytes[i]));
		}
		//��תmap
		Map<String, Byte> map2=new HashMap<String, Byte>();
		for(Entry<Byte, String> entry:map.entrySet()) {
			map2.put(entry.getValue(), entry.getKey());
		}
		//���ݹ������������ѯ�ַ���stringBuilder��Ӧ�ı��룬����list������
		List<Byte> list=new ArrayList<Byte>();
		for(int i=0;i<stringBuilder.length();) {
			boolean flag =true;
			Byte b = null;
			int count=1;
			//ͨ��count��ȡһ���ַ�����Ȼ��ȥmap�в�ѯ�������ѯ�Һ���˳���û��ѯ����count++
			//�Ƚ�ȡ����+1
			while(flag) {
				b=map2.get(stringBuilder.substring(i,i+count));
				//���bΪ�գ����ȡ���ַ�����key����map�������Ҳ��������ȡ����+1
				if(b==null) {
					count++;
				}else {
					//�����Ϊ�գ����ҵ����ַ�����Ӧ��Դ���룬�˳�whileѭ��
					flag=false;
				}
			}
			list.add(b);
			//��iλ�Ƶ�count��λ�ã���ȡ��һ������map�����ҵ���key
			i+=count;
		}
		//��list���Ϸ���byte�����У�����
		byte[] bs=new byte[list.size()];
		for(int i=0;i<list.size();i++) {
			bs[i]=list.get(i);
		}
		return bs;
	}
	
	/**
	 * ���ܣ����ļ�ѹ�������Ҵ�ŵ�ָ��λ��
	 * @param srcFile �ļ���ַ
	 * @param dstFile �ļ�ѹ�����ŵĵ�ַ
	 */
	public static void FileZip(String srcFile, String dstFile) {
		//�����ļ����������
		FileInputStream iStream = null;
		FileOutputStream oStream = null;
		//������������������㽫����д�뵽Ŀ���ַ
		ObjectOutputStream objectOutputStream = null;
		try {
			iStream = new FileInputStream(srcFile);
			oStream = new FileOutputStream(dstFile);
			objectOutputStream = new ObjectOutputStream(oStream);

			// ��ȡԴ�ļ��ĳ��ȣ�����һ���ֽ����飬����Ϊԭ�ļ��ĳ���
			byte[] b = new byte[iStream.available()];
			// ��ȡԴ�ļ����ȣ������ֽ�������
			iStream.read(b);
			// ͨ��getHuffmanZip��ȡѹ������ֽ�����huffmanzip
			byte[] huffmanzip = getHuffmanZip(b);

			// ��ѹ������ֽ�����huffmanzip�Ͷ�Ӧ��huffman��������Ŀ���ַdstFile
			objectOutputStream.writeObject(huffmanzip);
			objectOutputStream.writeObject(HuffmanCodes);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				iStream.close();
				oStream.close();
				objectOutputStream.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
	/**
	 * ���ܣ����ļ���ѹ��Ŀ��λ��
	 * @param zipSrc ѹ������λ��
	 * @param dstFile ��ѹ��Ŀ��λ��
	 */
	public static void unFileZip(String zipSrc, String dstFile) {
		InputStream is = null;
		ObjectInputStream ois = null;
		OutputStream os = null;
		try {
			is = new FileInputStream(zipSrc);
			ois = new ObjectInputStream(is);
			os = new FileOutputStream(dstFile);
			
			// ��ȡѹ������ֽ�����huffmanbyte
			byte[] HuffmanByte = (byte[]) ois.readObject();
			// ��ȡhuffmanbyte��Ӧ��huffman�����
			@SuppressWarnings("unchecked")
			Map<Byte, String> HuffmanCodes = (Map<Byte, String>) ois.readObject();
			// ���ֽ������ѹ
			byte[] src = deCode(HuffmanCodes, HuffmanByte);
			os.write(src);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				is.close();
				os.close();
				ois.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}
	/**
	 * ���ܣ����ֽ�ͨ���������ʽ���루����flag�ж��費��Ҫͨ�����룩
	 * @param flag ���Ϊ�棬���Բ������ʽ���룬����Դ�뷵��
	 * @param b ��Ҫ������ֽ�
	 * @return ͨ��flag�жϣ����Ϊ�淵�ز�����ʽ�Ķ������ַ���������Դ�뷵��
	 */
	private static String byteToBitString(boolean flag, byte b) {
		// ��bת��Ϊint���ͣ�����ͨ��ingter���н���
		int temp = b;
		if (flag) {
			temp |= 256;// ��Ϊ��256 ����λ�����tempΪ������
		}
		String str = Integer.toBinaryString(temp);// ����
		if (flag) {
			return str.substring(str.length() - 8);// ��ȡstr���λ���أ����룩
		} else {
			return str;// ֱ�ӷ��أ�Դ�룩
		}

	}
	
	/**
	 * ���ܣ���ȡ ͨ��������ѹ���õ����ֽ�����
	 * @param bytes ԭʼ�ַ�����Ӧ���ֽ�����
	 * @return ����ѹ������ֽ�����
	 */
	public static byte[] getHuffmanZip(byte[] bytes) {
		//��һ����ͨ��ԭʼ�ֽ����鴴���������������һ�ù���������ͷ���
		huffmanCode huffmanTreeCode = new huffmanCode();
		List<Node> list = huffmanTreeCode.getByteList(bytes);
		Node huffmanRoot = huffmanTreeCode.createHuffmanTreeCode(list);
		//�ڶ�����ͨ��������������ù���������
		Map<Byte, String> HuffmanCodes=getCodes(huffmanRoot);
		//��������ͨ�������������ԭʼ�ֽ����飬���ѹ������ֽ�����
		byte[] by=huffmanTreeCode.zip(bytes, HuffmanCodes);
		//��󣬷���ѹ������ֽ�����
		return by;
	}

	static private Map<Byte, String> HuffmanCodes = new HashMap<Byte, String>();
	static private StringBuilder stringBuilder = new StringBuilder();

	// ����getCodes�����÷���(ֻҪ�������������ͷ��㣬�Ϳ�����ɹ���������)
	private static Map<Byte, String> getCodes(Node root) {
		if (root == null) {
			return null;
		}
		getCodes(root.left, "0", stringBuilder);
		getCodes(root.right, "1", stringBuilder);
		return HuffmanCodes;
	}

	/**
	 * 
	 * @param bytes ������δ����������ֽ�����
	 * @param codes2 ���ݴ�����δ����������ֽ�������Huffman�����
	 * @return �����Խ�   ��������bytes����huffman��������Ķ������ֽ�����ת����ʮ���Ƶ��ֽ�����
	 * ���磺ԭ�ȵ�bytes =��97��100��102��... =����������������=��10011001001....=���ٽ��������벹��
	 * ת��Ϊ=��newbytes=��-88��-66��-97...��
	 */
	private  byte[] zip(byte[] bytes, Map<Byte, String> codes2) {
		//����ƴ�Ӵ�huffman����������ȡ���ֽ�
		StringBuilder stringBuilder = new StringBuilder();
		for (byte b : bytes) {
			stringBuilder = stringBuilder.append(codes2.get(b));
		}
		int len;
		//����ƴ�ӵ��ַ�����8λΪһ���ĳ���Ϊ����
		if(stringBuilder.length()%8==0) {
			len=stringBuilder.length()/8;
		}else {
			len=stringBuilder.length()/8+1;
		}
		//�����µ��ַ����飬���ڴ�Ž�ƴ�ӵ��ַ�����8λΪһ�εĽ��
		byte[] huffmanCodesBytes = new byte[len];
		int index = 0;
		for (int i = 0; i < stringBuilder.length(); i += 8) {
			if (i + 8 > stringBuilder.length()) {
				huffmanCodesBytes[index] = (byte) Integer.parseInt(stringBuilder.substring(i), 2);
			} else {
				huffmanCodesBytes[index] = (byte) Integer.parseInt(stringBuilder.substring(i, i + 8), 2);
				index++;
			}
		}
		return huffmanCodesBytes;
	}

	/**
	 * ���ܣ���ȡhuffman�����
	 * @param note ����Ľ��
	 * @param string ��¼·�������������ߵݹ���Ϊ0���ұ�����1
	 * @param stringBuilder ƴ�� ֱ��Ѱ�ҵ�Ҷ�ӽ��ǰ��·��
	 */
	private static void getCodes(Node note, String string, StringBuilder stringBuilder) {
		StringBuilder stringBuilder2 = new StringBuilder(stringBuilder);
		stringBuilder2.append(string);
		if (note != null) {
			// ���Ϊ�գ��ý��Ϊ��Ҷ�ӽ��
			if (note.b == null) {
				// ����ߵݹ�
				getCodes(note.left, "0", stringBuilder2);
				// ���ұߵݹ�
				getCodes(note.right, "1", stringBuilder2);
			} else {
				// �����Ϊ�գ�˵���ý��ΪҶ�ӽ�㣬�������map����
				//ע����ʱstringBuilder2ΪѰ�ҵ�Ҷ�ӽ��ǰƴ�ӵ��ַ���
				HuffmanCodes.put(note.b, stringBuilder2.toString());
			}
		}
	}

	// �����ֽ����飬����ͳ��ÿ���ֽڳ��ֵĴ����������ٴ�����note������list������
	/**
	 * ���ܣ���ȡ һ��ͳ����ÿ���ֽ����ֽ������г��ֵĴ��� ��Ӧ��list����
	 * @param bytes ѹ��ǰ���ֽ�����
	 * @return һ��ͳ����ÿ���ֽ����ֽ������г��ֵĴ��� ��Ӧ��list����
	 */
	private List<Node> getByteList(byte[] bytes) {
		List<Node> list = new ArrayList<Node>();
		// byte�ֽڣ�integerͳ���ֽڳ��ֵĴ���
		Map<Byte, Integer> map = new HashMap<Byte, Integer>();
		// �����ֽ�����bytes��ͳ��ÿ���ֽڳ��ֵĴ��������ҷ���map������
		for (byte b : bytes) {
			Integer con = map.get(b);
			if (con == null) {
				map.put(b, 1);
			} else {
				map.put(b, con + 1);
			}
		}
		for (Map.Entry<Byte, Integer> entry : map.entrySet()) {
			list.add(new Node(entry.getKey(), entry.getValue()));
		}
		return list;
	}

	/**
	 * ���ܣ�ͨ��һ��ͳ����ÿ���ֽ����ֽ������г��ֵĴ��� ��Ӧ��list���� ������������
	 * @param list ������ܵļ���
	 * @return ����������ͷ���
	 */
	private Node createHuffmanTreeCode(List<Node> list) {
		while (list.size() > 1) {
			Collections.sort(list);
			Node left = list.get(0);
			Node right = list.get(1);
			Node parent = new Node(null, left.weight + right.weight);
			parent.left = left;
			parent.right = right;
			list.remove(left);
			list.remove(right);
			list.add(parent);
		}
		return list.get(0);
	}

	//ǰ�����
	public void preOrder(Node root) {
		if (root != null) {
			root.preOrder();
		} else {
			System.out.println("Huffman��Ϊ�գ��޷�����");
		}

	}

}

// �������
class Node implements Comparable<Node> {
	Byte b;// �����ֽڶ���
	int weight;// ���ַ����ֵĴ�����Ȩֵ��
	Node left;
	Node right;

	public Node(Byte b, int weight) {
		this.b = b;
		this.weight = weight;
	}

	@Override
	public int compareTo(Node o) {
		return this.weight - o.weight;
	}

	public void preOrder() {
		System.out.println(this);
		if (this.left != null) {
			this.left.preOrder();
		}
		if (this.right != null) {
			this.right.preOrder();
		}
	}

	@Override
	public String toString() {
		return "Node [b=" + b + ", weight=" + weight + "]";
	}

}
