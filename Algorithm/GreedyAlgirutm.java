package Algorithm;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class GreedyAlgirutm {

	public static void main(String[] args) {
		Map<String, HashSet<String>> broadcasts = new HashMap<String, HashSet<String>>();
		HashSet<String> hashSet1 = new HashSet<String>();
		HashSet<String> hashSet2 = new HashSet<String>();
		HashSet<String> hashSet3 = new HashSet<String>();
		HashSet<String> hashSet4 = new HashSet<String>();
		HashSet<String> hashSet5 = new HashSet<String>();
		HashSet<String> allAreas = new HashSet<String>();

		hashSet1.add("����");
		hashSet1.add("�Ϻ�");
		hashSet1.add("���");

		hashSet2.add("����");
		hashSet2.add("����");
		hashSet2.add("����");

		hashSet3.add("�ɶ�");
		hashSet3.add("�Ϻ�");
		hashSet3.add("����");

		hashSet4.add("�Ϻ�");
		hashSet4.add("���");

		hashSet5.add("����");
		hashSet5.add("����");

		broadcasts.put("k1", hashSet1);
		broadcasts.put("k2", hashSet2);
		broadcasts.put("k3", hashSet3);
		broadcasts.put("k4", hashSet4);
		broadcasts.put("k5", hashSet5);

		allAreas.add("����");
		allAreas.add("�Ϻ�");
		allAreas.add("���");
		allAreas.add("�ɶ�");
		allAreas.add("����");
		allAreas.add("����");
		allAreas.add("����");
		allAreas.add("����");

		HashSet<String> selects = new HashSet<String>();// �����ѡ�еĵ���
		// ��� �����е������������г��н����ĳ���
		// �������е���=���������Ϻ������ڣ����,��������=���������Ϻ���������,��temp��Ϊ"�������Ϻ�"
		HashSet<String> tempSet = new HashSet<String>();
		String maxKey = null;// ָ�򽻼����������Ǹ�����
		int size=0;
		while (allAreas.size() != 0) {
			// ��maxkeyָ���ÿ�
			maxKey = null;
			size=0;
			for (String key : broadcasts.keySet()) {
				// ���temp��ֵ������Ƚ���ʱ����
				tempSet.clear();
				HashSet<String> areas = broadcasts.get(key);
				tempSet.addAll(areas);
				// �������������г��еĽ�������
				tempSet.retainAll(allAreas);
				// ���keyָ����Ǹ����������е����Ľ������б�maxkeyָ��Ķ࣬��key��ֵ��maxkey
				if(maxKey!=null) {
					//�Ƚ�ǰ���ȶ�maxkeyָ��ĵ������Ͻ���ȡ����
				broadcasts.get(maxKey).retainAll(allAreas);
				size=broadcasts.get(maxKey).size();
				}
				if (tempSet.size()>0&&(maxKey == null || tempSet.size() > size)) {
					maxKey = key;
				}
			}
			if (maxKey != null) {
				selects.add(maxKey);
				allAreas.removeAll(broadcasts.get(maxKey));	
			}
		}
		System.out.println(selects);

	}

}
