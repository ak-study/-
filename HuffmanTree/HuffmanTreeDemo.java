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
		// 对note对象进行排序
		Collections.sort(notes);
		// 出去notes集合里面的第一个和第0个，合成一个新的对象后再放入notes集合里面
		// 循环操作后，最后集合里面仅剩下一个note对象
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
			System.out.println("空树，无法遍历");
		}
	}

}

class huffmanTree {
	public Note createHuffmanTree(int arr[]) {
		List<Note> notes = new ArrayList<Note>();
		for (int val : arr) {
			notes.add(new Note(val));
		}
		// 对note对象进行排序
		// 出去notes集合里面的第一个和第0个，合成一个新的对象后再放入notes集合里面
		// 循环操作后，最后集合里面仅剩下一个note对象
		while (notes.size() > 1) {
			//每次进行添加移除前都需要再次进行排序，（因为后续parent是添加到集合的最后）
			Collections.sort(notes);
			//每次都拿出集合的第一个和第二个，然后创建一个新结点指向这两个结点
			//之后移除之前的两个结点，将新创建的结点放入集合，重复这两个步骤，最后集合内应将只有一个结点
			//该结点就是哈夫曼树的根节点
			Note left = notes.get(0);
			Note right = notes.get(1);
			Note parent = new Note(left.val + right.val);
			parent.left = left;
			parent.right = right;
			notes.remove(0);
			notes.remove(1);
			notes.add(parent);//添加到集合的最后
		}
		return notes.get(0);
	}

	public void preOrder(Note root) {
		if (root != null) {
			root.preOrder();
		} else {
			System.out.println("空树，无法遍历");
		}
	}
}

// 实现Comparable对象后，可以通过Collections对 对象 排序
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
