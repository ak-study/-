package Algorithm;

public class Hanoitower {

	public static void main(String[] args) {
		hannoitower(30, 'A','B', 'C');

	}

	public static void hannoitower(int num, char a, char b, char c) {
		if (num == 1) {
			System.out.println("�ѵ�1���̴�: " + a + " -> " + c);
		} else {
			//����˼·����Զ����ֻ�������̣�������������̺�����µ�һ����
			//��һ������������̴�a�ƶ���b
			hannoitower(num - 1, a, c, b);
			//�ڶ���������������̴�a�ƶ���c
			System.out.println("�ѵ�" + num + "���̴�: " + a + " -> " + c);
			//����������������̴�b�ƶ���c
			hannoitower(num - 1, b, a, c);
		}
	}

}
