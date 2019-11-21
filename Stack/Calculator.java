package Stack;


public class Calculator {

	public static void main(String[] args) {
		stack1 numstack = new stack1(10);// 数栈
		stack1 operstack = new stack1(10);// 符号栈
		String ex = "70+2*6-2";
		int index = 0;// 用于控制ch识别次数
		int num1 = 0;
		int num2 = 0;
		int oper = 0;
		int res = 0;// 结果
		int ch = 0;// 当前字符
		String keepNum = "";
		while (true) {
			ch = ex.substring(index, index + 1).charAt(0);
			// 如果ch为运算符
			if (operstack.isOper(ch)) {
				// 如果符号栈为空，则直接将ch放入符号栈
				if (operstack.isEmpty()) {
					operstack.push(ch);
				} else {
					// 如果当前ch的优先级小于等于符号栈栈顶的优先级，则弹出两个数栈的数字和一个
					// 符号栈的符号进行运算，并将结果存入数栈，并将当前符号ch存入符号栈
					if (operstack.priority(ch) <= operstack.peek()) {
						num1 = numstack.pop();
						num2 = numstack.pop();
						oper = operstack.pop();
						res = numstack.cal(num1, num2, oper);
						// 将结果存入数栈
						numstack.push(res);
						// 将当前符号ch存入符号栈
						operstack.push(ch);
					} else {
						// 如果当前符号ch大于符号栈栈顶的优先级，则直接将当前符号ch存入符号栈
						operstack.push(ch);
					}
				}
				// 如果ch为数字，直接存入数栈
			} else {
				keepNum += ch - 48;
				// 如果ch已经是字符串的最后一个，则直接将keepNum压如数栈
				// 否则下面的判断越界异常
				if (index == ex.length() - 1) {
					numstack.push(Integer.parseInt(keepNum));
				} else {
					// 如果当前字符的下一个字符为运算符，这直接将keepNum压数栈
					if (operstack.isOper(ex.subSequence(index + 1, index + 2).charAt(0))) {
						numstack.push(Integer.parseInt(keepNum));
						// 情况keepNum（重要！）
						keepNum = "";
					}
				}
			}
			index++;
			if (index >= ex.length()) {
				// 扫描完毕
				break;
			}
		}
		while (true) {
			// 如果符号栈为空，根据算法，计算完毕（如果用数栈判断则判断数栈内是否只含有一个数）
			if (operstack.isEmpty()) {
				break;
			}
			num1 = numstack.pop();
			num2 = numstack.pop();
			oper = operstack.pop();
			res = numstack.cal(num1, num2, oper);
			numstack.push(res);
		}
		// 输出结果
		System.out.printf("%s=%d", ex, numstack.pop());

	}

}

class stack1 {
	private int top = -1;
	private int[] stack;
	private int maxsize;

	public stack1(int maxsize) {
		this.maxsize = maxsize;
		stack = new int[maxsize];
	}

	public boolean isFull() {
		if (top == maxsize - 1) {
			return true;
		}
		return false;
	}

	public boolean isEmpty() {
		if (top == -1) {
			return true;
		}
		return false;
	}

	public int peek() {
		return stack[top];
	}

	public void push(int value) {
		if (this.isFull()) {
			System.out.println("栈满");
			return;
		}
		top++;
		stack[top] = value;
	}

	public int pop() {
		if (this.isEmpty()) {
			System.out.println("栈空");
			return -1;
		}
		int value = stack[top];
		top--;
		return value;
	}

	public void list() {
		for (int i = top; i >= 0; i--) {
			System.out.printf("stack[%d]=%d\n", i, stack[i]);
		}
	}

	// 判断传入的oper的优先级，用数字表示
	public int priority(int oper) {
		if (oper == '*' || oper == '/') {
			return 1;
		} else if (oper == '+' || oper == '-') {
			return 0;
		} else {
			return -1;
		}
	}

	public boolean isOper(int oper) {
		return oper == '+' || oper == '-' || oper == '*' || oper == '/';
	}

	// 计算num1和num2的值
	public int cal(int num1, int num2, int oper) {
		int rel = 0;
		switch (oper) {
		case '+':
			rel = num1 + num2;
			break;
		case '-':
			// 运算优先级，根据栈的结构，num2先与num1存入栈内
			rel = num2 + num1;
			break;
		case '*':
			rel = num1 + num2;
			break;
		case '/':
			// 同上
			rel = num2 + num1;
			break;
		default:
			break;
		}
		return rel;
	}
}
