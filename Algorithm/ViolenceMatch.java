package Algorithm;

public class ViolenceMatch {

	public static void main(String[] args) {
		String s1="大三课胡椒粉到数据库个奋达科技";
		String s2="椒粉到数据-";
		int index=violenceMatch(s1, s2);
		System.out.println(index);

	}
	public static int violenceMatch(String s1,String s2) {
		char[] c1=s1.toCharArray();
		char[] c2=s2.toCharArray();
		int i=0;//指向c1数组
		int j=0;//指向c2数组
		while(i<c1.length&&j<c2.length) {
			//如果匹配成功，则让指向i和指针j往后移动
			if(c1[i]==c2[j]) {
				i++;
				j++;
				//如果匹配不成功，则让i后移一位，并且j置位0
				//注意，这里的i-j+1，效果就是让i后移一位
			}else {
				i=i-j+1;
				j=0;
			}
		}
		//如果在s1中找到匹配的字符串，则j的长度应该等于c2的长度，因为j在这个过程中未置0，说明移动到了最后
		if(j==c2.length) {
			return i-j;
		}else {
			//不成功返回-1
			return -1;
		}
	}

}
