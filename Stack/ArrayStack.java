package Stack;

public class ArrayStack {

	public static void main(String[] args) {
		stack stack=new stack(5);
		stack.push(1);
		stack.push(2);
		stack.push(3);
		stack.push(4);
		stack.push(5);
		stack.list();
	}

}
class stack{
	private int top=-1;
	private int[] stack;
	private int maxsize;
	public stack(int maxsize) {
		this.maxsize=maxsize;
		stack=new int[maxsize];
	}
	public boolean isFull(){
		if(top==maxsize-1) {
			return true;
		}
		return false;
	}
	public boolean isEmpty() {
		if(top==-1) {
			return true;
		}
		return false;
	}
	public void push(int value) {
		if(this.isFull()) {
			System.out.println("Õ»Âú");
			return;
		}
		top++;
		stack[top]=value;
	}
	public int pop(){
		if(this.isEmpty()) {
			System.out.println("Õ»¿Õ");
			return -1;
		}
	    int value=stack[top];
	    top--;
	    return value;
	}
	public void list() {
		for(int i=top;i>=0;i--) {
			System.out.printf("stack[%d]=%d\n",i,stack[i]);
		}
	}
}
