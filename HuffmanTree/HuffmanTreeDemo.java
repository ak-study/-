package HuffmanTree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HuffmanTreeDemo {

	public static void main(String[] args) {
		int arr[] = { 13, 7, 8, 3, 29, 6, 1};
		Note note = createHuffmanTree(arr);
		preOrder(note);

	}

	public static Note createHuffmanTree(int arr[]) {
		List<Note> notes = new ArrayList<Note>();
		for (int val : arr) {
			notes.add(new Note(val));
		}
		// ��note�����������
		Collections.sort(notes);
		// ��ȥnotes��������ĵ�һ���͵�0�����ϳ�һ���µĶ�����ٷ���notes��������
		// ѭ����������󼯺������ʣ��һ��note����
		while (notes.size() > 1) {
			Note left = notes.get(0);
			Note right = notes.get(1);
			Note parent = new Note(left.val + right.val);
			parent.left = left;
			parent.right = right;
			notes.remove(left);
			notes.remove(right);
			notes.add(parent);
		}
		return notes.get(0);
	}

	public static void preOrder(Note root) {
		if (root != null) {
			root.preOrder();
		} else {
			System.out.println("�������޷�����");
		}
	}

}

class huffmanTree {
	public Note createHuffmanTree(int arr[]) {
		List<Note> notes = new ArrayList<Note>();
		for (int val : arr) {
			notes.add(new Note(val));
		}
		// ��note�����������
		// ��ȥnotes��������ĵ�һ���͵�0�����ϳ�һ���µĶ�����ٷ���notes��������
		// ѭ����������󼯺������ʣ��һ��note����
		while (notes.size() > 1) {
			//ÿ�ν�������Ƴ�ǰ����Ҫ�ٴν������򣬣���Ϊ����parent����ӵ����ϵ����
			Collections.sort(notes);
			//ÿ�ζ��ó����ϵĵ�һ���͵ڶ�����Ȼ�󴴽�һ���½��ָ�����������
			//֮���Ƴ�֮ǰ��������㣬���´����Ľ����뼯�ϣ��ظ����������裬��󼯺���Ӧ��ֻ��һ�����
			//�ý����ǹ��������ĸ��ڵ�
			Note left = notes.get(0);
			Note right = notes.get(1);
			Note parent = new Note(left.val + right.val);
			parent.left = left;
			parent.right = right;
			notes.remove(0);
			notes.remove(1);
			notes.add(parent);//��ӵ����ϵ����
		}
		return notes.get(0);
	}

	public void preOrder(Note root) {
		if (root != null) {
			root.preOrder();
		} else {
			System.out.println("�������޷�����");
		}
	}
}

// ʵ��Comparable����󣬿���ͨ��Collections�� ���� ����
class Note implements Comparable<Note> {
	int val = 0;
	Note left;
	Note right;

	public Note(int val) {
		this.val = val;
	}

	@Override
	public int compareTo(Note o) {
		return val - o.val;
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
		return "Note [val=" + val + "]";
	}

}
