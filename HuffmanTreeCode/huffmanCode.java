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
		//压缩文件
		String src = "G:\\src.jpg";
		String dst = "G:\\dst.zip";
		FileZip(src, dst);
		System.out.println("压缩完成");
		//解压文件
		String src2 = "G:\\dst.zip";
		String dst2 = "G:\\src2.jpg";
		unFileZip(src2, dst2);
		System.out.println("解压完成");
//		//原先字符串长度，40
//		String string = "i like like like java do you like a java";
//		//获取压缩前的字节数组
//		byte[] bytes = string.getBytes();
//		System.out.println("压缩前的字节数组："+Arrays.toString(bytes));
//		
//		//获取压缩后的字节数组
//		byte[] by=getHuffmanZip(bytes);
//		//压缩过后的长度，17，压缩率57.5%
//		System.out.println("压缩后的的字节数组："+Arrays.toString(by));
//		
//		//获取解压后的字节数组
//		byte[] b=deCode(HuffmanCodes, by);
//		System.out.println("解压后的字节数组："+Arrays.toString(b));
//		System.out.println("转换为字符串"+new String(b));
	}
	/**
	 * 功能：对压缩后的字节数组bytes进行解码，返回原先的字符串对应的字节数组
	 * @param map 压缩时的哈夫曼编码表
	 * @param bytes 压缩后的字节数组
	 * @return 返回原先的字符串对应的字节数组
	 */
	private static byte[] deCode(Map<Byte, String> map,byte[] bytes) {
		StringBuilder stringBuilder=new StringBuilder();
		//通过for循环将bytes字节数组转为二进制字符串
		for(int i=0;i<bytes.length;i++) {
			//判断i是否为最后一个，如果是最后一个则不需要反码
			boolean flag=(i==bytes.length-1);
			stringBuilder.append(byteToBitString(!flag, bytes[i]));
		}
		//翻转map
		Map<String, Byte> map2=new HashMap<String, Byte>();
		for(Entry<Byte, String> entry:map.entrySet()) {
			map2.put(entry.getValue(), entry.getKey());
		}
		//根据哈夫曼编码表，查询字符串stringBuilder对应的编码，放入list集合中
		List<Byte> list=new ArrayList<Byte>();
		for(int i=0;i<stringBuilder.length();) {
			boolean flag =true;
			Byte b = null;
			int count=1;
			//通过count截取一段字符串，然后去map中查询，如果查询找后就退出，没查询到则count++
			//既截取长度+1
			while(flag) {
				b=map2.get(stringBuilder.substring(i,i+count));
				//如果b为空，则截取的字符串（key）在map集合中找不到，则截取长度+1
				if(b==null) {
					count++;
				}else {
					//如果不为空，则找到该字符串对应的源编码，退出while循环
					flag=false;
				}
			}
			list.add(b);
			//将i位移到count的位置，截取下一段能在map集合找到的key
			i+=count;
		}
		//将list集合放入byte数组中，返回
		byte[] bs=new byte[list.size()];
		for(int i=0;i<list.size();i++) {
			bs[i]=list.get(i);
		}
		return bs;
	}
	
	/**
	 * 功能，将文件压缩，并且存放到指定位置
	 * @param srcFile 文件地址
	 * @param dstFile 文件压缩后存放的地址
	 */
	public static void FileZip(String srcFile, String dstFile) {
		//创建文件输入输出流
		FileInputStream iStream = null;
		FileOutputStream oStream = null;
		//创建对象输出流，方便将对象写入到目标地址
		ObjectOutputStream objectOutputStream = null;
		try {
			iStream = new FileInputStream(srcFile);
			oStream = new FileOutputStream(dstFile);
			objectOutputStream = new ObjectOutputStream(oStream);

			// 获取源文件的长度，创建一个字节数组，长度为原文件的长度
			byte[] b = new byte[iStream.available()];
			// 读取源文件长度，放入字节数组中
			iStream.read(b);
			// 通过getHuffmanZip获取压缩后的字节数组huffmanzip
			byte[] huffmanzip = getHuffmanZip(b);

			// 将压缩后的字节数组huffmanzip和对应的huffman编码表放入目标地址dstFile
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
	 * 功能：将文件解压到目标位置
	 * @param zipSrc 压缩包的位置
	 * @param dstFile 解压的目标位置
	 */
	public static void unFileZip(String zipSrc, String dstFile) {
		InputStream is = null;
		ObjectInputStream ois = null;
		OutputStream os = null;
		try {
			is = new FileInputStream(zipSrc);
			ois = new ObjectInputStream(is);
			os = new FileOutputStream(dstFile);
			
			// 读取压缩后的字节数组huffmanbyte
			byte[] HuffmanByte = (byte[]) ois.readObject();
			// 读取huffmanbyte对应的huffman编码表
			@SuppressWarnings("unchecked")
			Map<Byte, String> HuffmanCodes = (Map<Byte, String>) ois.readObject();
			// 对字节数组解压
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
	 * 功能：对字节通过补码的形式解码（根据flag判断需不需要通过补码）
	 * @param flag 如果为真，则以补码的形式解码，否则按源码返回
	 * @param b 需要解码的字节
	 * @return 通关flag判断，如果为真返回补码形式的二进制字符串，否则按源码返回
	 */
	private static String byteToBitString(boolean flag, byte b) {
		// 将b转换为int类型，方便通过ingter进行解码
		int temp = b;
		if (flag) {
			temp |= 256;// 按为或256 补高位（如果temp为正数）
		}
		String str = Integer.toBinaryString(temp);// 解码
		if (flag) {
			return str.substring(str.length() - 8);// 截取str后八位返回（补码）
		} else {
			return str;// 直接返回（源码）
		}

	}
	
	/**
	 * 功能：获取 通过哈夫曼压缩得到的字节数组
	 * @param bytes 原始字符串对应的字节数组
	 * @return 经过压缩后的字节数组
	 */
	public static byte[] getHuffmanZip(byte[] bytes) {
		//第一步，通过原始字节数组创建哈夫曼树，并且获得哈夫曼树的头结点
		huffmanCode huffmanTreeCode = new huffmanCode();
		List<Node> list = huffmanTreeCode.getByteList(bytes);
		Node huffmanRoot = huffmanTreeCode.createHuffmanTreeCode(list);
		//第二步，通过哈夫曼树，获得哈夫曼编码
		Map<Byte, String> HuffmanCodes=getCodes(huffmanRoot);
		//第三步，通过哈夫曼编码和原始字节数组，获得压缩后的字节数组
		byte[] by=huffmanTreeCode.zip(bytes, HuffmanCodes);
		//最后，返回压缩后的字节数组
		return by;
	}

	static private Map<Byte, String> HuffmanCodes = new HashMap<Byte, String>();
	static private StringBuilder stringBuilder = new StringBuilder();

	// 重载getCodes，调用方便(只要传入哈夫曼树的头结点，就可以完成哈夫曼编码)
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
	 * @param bytes 穿进来未经过处理的字节数组
	 * @param codes2 根据传进来未经过处理的字节数组编的Huffman编码表
	 * @return 返回以将   传进来的bytes经过huffman编码表处理后的二进制字节数组转换成十进制的字节数组
	 * 例如：原先的bytes =｛97，100，102｝... =》经过哈夫曼处理=》10011001001....=》再将经过反码补码
	 * 转换为=》newbytes=｛-88，-66，-97...｝
	 */
	private  byte[] zip(byte[] bytes, Map<Byte, String> codes2) {
		//用于拼接从huffman编码表里面读取的字节
		StringBuilder stringBuilder = new StringBuilder();
		for (byte b : bytes) {
			stringBuilder = stringBuilder.append(codes2.get(b));
		}
		int len;
		//计算拼接的字符串以8位为一步的长度为多少
		if(stringBuilder.length()%8==0) {
			len=stringBuilder.length()/8;
		}else {
			len=stringBuilder.length()/8+1;
		}
		//创建新的字符数组，用于存放将拼接的字符串以8位为一段的结果
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
	 * 功能：获取huffman编码表
	 * @param note 传入的结点
	 * @param string 记录路径，如果是像左边递归则为0，右边则有1
	 * @param stringBuilder 拼接 直到寻找到叶子结点前的路径
	 */
	private static void getCodes(Node note, String string, StringBuilder stringBuilder) {
		StringBuilder stringBuilder2 = new StringBuilder(stringBuilder);
		stringBuilder2.append(string);
		if (note != null) {
			// 如果为空，该结点为非叶子结点
			if (note.b == null) {
				// 向左边递归
				getCodes(note.left, "0", stringBuilder2);
				// 向右边递归
				getCodes(note.right, "1", stringBuilder2);
			} else {
				// 如果不为空，说明该结点为叶子结点，将其放入map里面
				//注：此时stringBuilder2为寻找到叶子结点前拼接的字符串
				HuffmanCodes.put(note.b, stringBuilder2.toString());
			}
		}
	}

	// 遍历字节数组，并且统计每个字节出现的次数，放在再创建成note结点放入list集合内
	/**
	 * 功能：获取 一个统计了每个字节在字节数组中出现的次数 对应的list集合
	 * @param bytes 压缩前的字节数组
	 * @return 一个统计了每个字节在字节数组中出现的次数 对应的list集合
	 */
	private List<Node> getByteList(byte[] bytes) {
		List<Node> list = new ArrayList<Node>();
		// byte字节，integer统计字节出现的次数
		Map<Byte, Integer> map = new HashMap<Byte, Integer>();
		// 遍历字节数组bytes，统计每个字节出现的次数，并且放入map集合中
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
	 * 功能：通过一个统计了每个字节在字节数组中出现的次数 对应的list集合 创建哈夫曼树
	 * @param list 上面介绍的集合
	 * @return 哈夫曼树的头结点
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

	//前序遍历
	public void preOrder(Node root) {
		if (root != null) {
			root.preOrder();
		} else {
			System.out.println("Huffman树为空，无法遍历");
		}

	}

}

// 创建结点
class Node implements Comparable<Node> {
	Byte b;// 结点的字节对象
	int weight;// 该字符出现的次数（权值）
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
