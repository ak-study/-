package Stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PolandNotation {

	public static void main(String[] args) {
		String ex = "1+((2+3)*4)-5";
		List<String> list = tohouzui(ex);
		List<String>ls=zhuanhouzui(list);
		int res=cal(ls);
		System.out.println("������Ϊ��"+res);
		/*
		 * String string = "3 4 + 5 * 6 -"; List<String> list = new ArrayList<String>();
		 * // ���ݿո�ָ��ַ����������ṹ����String���� String[] string2 = string.split(" "); //
		 * �������ڵ�ֵȡ�����뼯�� for (String str : string2) { list.add(str); } int res =
		 * cal(list); System.out.println(res);
		 */
	}

	public static List<String> tohouzui(String string) {
		int i = 0;
		char c;
		String str;
		List<String> list = new ArrayList<String>();
		while (i < string.length()) {
			// ����ASCII��48-57����48��57��֮��ķ��ţ���Ϊ���֣�����Ķ����ַ�
			// cΪ�ַ���ֱ�ӷ��뼯��
			if ((c = string.charAt(i)) < 48 || (c = string.charAt(i)) > 57) {
				list.add(c + "");
				i++;
				// cΪ���֣������ƴ��
			} else {
				str = "";
				// ��������ƴ�ӣ�ѭ���ж�c�Ƿ�Ϊ����
				while (i < string.length() && (c = string.charAt(i)) >= 48 && (c = string.charAt(i)) <= 57) {
					str += c;
					i++;
				}
				list.add(str);
			}
		}
		return list;
	}

	public static List<String> zhuanhouzui(List<String>ls){
		Stack<String> s1=new Stack<String>();
		List<String>s2=new ArrayList<String>();
		for(String s:ls) {
			//���������
			if(s.matches("\\d+")) {
				s2.add(s);
			}else if(s.equals("(")) {
				s1.push(s);
			}else if(s.equals(")")) {
				//ֱ������������ǰ��ѭ����s1ջ�����ŵ��������뵽s2
				while(!s1.peek().equals("(")) {
					s2.add(s1.pop());
				}
				//����ѭ���󣬴���ǰջ������Ϊ�����ţ����䵯������
				s1.pop();
			}else {
				//�жϵ�ǰȡ�õķ���s�����ȼ��Ƿ��s1��ջ���������ȼ��ͣ���������ͣ���ջ������
				//ѭ���������뵽s2��ֱ��s1��ջ���������ȼ����ڵ�ǰ����s
				while(s1.size()!=0&&oper(s)<=oper(s1.peek())) {
					s2.add(s1.pop());
				}
				//����ѭ����˵����ǰ����s�����ȼ�����s1ջ���������ȼ���ֱ����ջ
				s1.add(s);
			}
			//���ϲ�����ɺ�ѭ����s1ʣ�����ѭ��˳�򵯳����뵽s2
		}
		while(s1.size()!=0) {
			s2.add(s1.pop());
		}
		return s2;
	}
	public static int oper(String oper) {
		if(oper.equals("+")||oper.equals("-")) {
			return 1;
		}else if(oper.equals("*")|| oper.equals("/")){
			return 2;
		}else if(oper.equals("(")) {
			return 0;
		}else {
			System.out.println("����������ڣ�");
			return -1;
		}
	}

	public static int cal(List<String> list) {
		Stack<String> stack = new Stack<String>();
		for (String item : list) {
			// ���itemΪ���֣���ֱ��ѹ��
			if (item.matches("\\d+")) {
				stack.push(item);
				// ���itemΪ������򵯳��������ֺ͵�ǰ�����item���㣬�������res����ջ��
			} else {
				int num1 = Integer.parseInt(stack.pop());
				int num2 = Integer.parseInt(stack.pop());
				int res = 0;
				switch (item) {
				case "+":
					res = num1 + num2;
					break;
				case "-":
					res = num2 - num1;
					break;
				case "*":
					res = num1 * num2;
					break;
				case "/":
					res = num1 / num2;
					break;
				default:
					throw new RuntimeException("���Ŵ���");
				}
				stack.push(res + "");
			}
		}
		return Integer.parseInt(stack.pop());
	}

}
