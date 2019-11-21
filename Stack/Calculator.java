package Stack;


public class Calculator {

	public static void main(String[] args) {
		stack1 numstack = new stack1(10);// ��ջ
		stack1 operstack = new stack1(10);// ����ջ
		String ex = "70+2*6-2";
		int index = 0;// ���ڿ���chʶ�����
		int num1 = 0;
		int num2 = 0;
		int oper = 0;
		int res = 0;// ���
		int ch = 0;// ��ǰ�ַ�
		String keepNum = "";
		while (true) {
			ch = ex.substring(index, index + 1).charAt(0);
			// ���chΪ�����
			if (operstack.isOper(ch)) {
				// �������ջΪ�գ���ֱ�ӽ�ch�������ջ
				if (operstack.isEmpty()) {
					operstack.push(ch);
				} else {
					// �����ǰch�����ȼ�С�ڵ��ڷ���ջջ�������ȼ����򵯳�������ջ�����ֺ�һ��
					// ����ջ�ķ��Ž������㣬�������������ջ��������ǰ����ch�������ջ
					if (operstack.priority(ch) <= operstack.peek()) {
						num1 = numstack.pop();
						num2 = numstack.pop();
						oper = operstack.pop();
						res = numstack.cal(num1, num2, oper);
						// �����������ջ
						numstack.push(res);
						// ����ǰ����ch�������ջ
						operstack.push(ch);
					} else {
						// �����ǰ����ch���ڷ���ջջ�������ȼ�����ֱ�ӽ���ǰ����ch�������ջ
						operstack.push(ch);
					}
				}
				// ���chΪ���֣�ֱ�Ӵ�����ջ
			} else {
				keepNum += ch - 48;
				// ���ch�Ѿ����ַ��������һ������ֱ�ӽ�keepNumѹ����ջ
				// ����������ж�Խ���쳣
				if (index == ex.length() - 1) {
					numstack.push(Integer.parseInt(keepNum));
				} else {
					// �����ǰ�ַ�����һ���ַ�Ϊ���������ֱ�ӽ�keepNumѹ��ջ
					if (operstack.isOper(ex.subSequence(index + 1, index + 2).charAt(0))) {
						numstack.push(Integer.parseInt(keepNum));
						// ���keepNum����Ҫ����
						keepNum = "";
					}
				}
			}
			index++;
			if (index >= ex.length()) {
				// ɨ�����
				break;
			}
		}
		while (true) {
			// �������ջΪ�գ������㷨��������ϣ��������ջ�ж����ж���ջ���Ƿ�ֻ����һ������
			if (operstack.isEmpty()) {
				break;
			}
			num1 = numstack.pop();
			num2 = numstack.pop();
			oper = operstack.pop();
			res = numstack.cal(num1, num2, oper);
			numstack.push(res);
		}
		// ������
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
			System.out.println("ջ��");
			return;
		}
		top++;
		stack[top] = value;
	}

	public int pop() {
		if (this.isEmpty()) {
			System.out.println("ջ��");
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

	// �жϴ����oper�����ȼ��������ֱ�ʾ
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

	// ����num1��num2��ֵ
	public int cal(int num1, int num2, int oper) {
		int rel = 0;
		switch (oper) {
		case '+':
			rel = num1 + num2;
			break;
		case '-':
			// �������ȼ�������ջ�Ľṹ��num2����num1����ջ��
			rel = num2 + num1;
			break;
		case '*':
			rel = num1 + num2;
			break;
		case '/':
			// ͬ��
			rel = num2 + num1;
			break;
		default:
			break;
		}
		return rel;
	}
}
