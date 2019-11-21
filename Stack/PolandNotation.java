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
		System.out.println("运算结果为："+res);
		/*
		 * String string = "3 4 + 5 * 6 -"; List<String> list = new ArrayList<String>();
		 * // 根据空格分隔字符串，并将结构存入String数组 String[] string2 = string.split(" "); //
		 * 将数组内的值取出放入集合 for (String str : string2) { list.add(str); } int res =
		 * cal(list); System.out.println(res);
		 */
	}

	public static List<String> tohouzui(String string) {
		int i = 0;
		char c;
		String str;
		List<String> list = new ArrayList<String>();
		while (i < string.length()) {
			// 根据ASCII表，48-57（含48，57）之间的符号，均为数字，以外的都是字符
			// c为字符，直接放入集合
			if ((c = string.charAt(i)) < 48 || (c = string.charAt(i)) > 57) {
				list.add(c + "");
				i++;
				// c为数字，则进行拼接
			} else {
				str = "";
				// 进行数字拼接，循环判断c是否为数字
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
			//如果是数字
			if(s.matches("\\d+")) {
				s2.add(s);
			}else if(s.equals("(")) {
				s1.push(s);
			}else if(s.equals(")")) {
				//直到遇到左括号前，循环将s1栈顶符号弹出并加入到s2
				while(!s1.peek().equals("(")) {
					s2.add(s1.pop());
				}
				//跳出循环后，代表当前栈顶符号为左括号，将其弹出消除
				s1.pop();
			}else {
				//判断当前取得的符号s的优先级是否比s1的栈顶符号优先级低，如果比它低，则将栈顶符号
				//循环弹出加入到s2，直到s1的栈顶符号优先级低于当前符号s
				while(s1.size()!=0&&oper(s)<=oper(s1.peek())) {
					s2.add(s1.pop());
				}
				//跳出循环，说明当前符号s的优先级高于s1栈顶符号优先级，直接入栈
				s1.add(s);
			}
			//以上操作完成后，循环将s1剩余符号循环顺序弹出加入到s2
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
			System.out.println("运算符不存在！");
			return -1;
		}
	}

	public static int cal(List<String> list) {
		Stack<String> stack = new Stack<String>();
		for (String item : list) {
			// 如果item为数字，则直接压入
			if (item.matches("\\d+")) {
				stack.push(item);
				// 如果item为运算符则弹出两个数字和当前运算符item计算，并将结果res存入栈内
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
					throw new RuntimeException("符号错误！");
				}
				stack.push(res + "");
			}
		}
		return Integer.parseInt(stack.pop());
	}

}
