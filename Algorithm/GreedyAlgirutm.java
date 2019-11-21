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

		hashSet1.add("北京");
		hashSet1.add("上海");
		hashSet1.add("天津");

		hashSet2.add("广州");
		hashSet2.add("北京");
		hashSet2.add("深圳");

		hashSet3.add("成都");
		hashSet3.add("上海");
		hashSet3.add("杭州");

		hashSet4.add("上海");
		hashSet4.add("天津");

		hashSet5.add("杭州");
		hashSet5.add("大连");

		broadcasts.put("k1", hashSet1);
		broadcasts.put("k2", hashSet2);
		broadcasts.put("k3", hashSet3);
		broadcasts.put("k4", hashSet4);
		broadcasts.put("k5", hashSet5);

		allAreas.add("北京");
		allAreas.add("上海");
		allAreas.add("天津");
		allAreas.add("成都");
		allAreas.add("杭州");
		allAreas.add("深圳");
		allAreas.add("大连");
		allAreas.add("广州");

		HashSet<String> selects = new HashSet<String>();// 存放已选中的地区
		// 存放 和所有单个地区和所有城市交集的城市
		// 例如所有地区=“北京，上海，深圳，天津”,单个地区=“北京，上海，大连”,则temp就为"北京，上海"
		HashSet<String> tempSet = new HashSet<String>();
		String maxKey = null;// 指向交集城市最多的那个地区
		int size=0;
		while (allAreas.size() != 0) {
			// 将maxkey指向置空
			maxKey = null;
			size=0;
			for (String key : broadcasts.keySet()) {
				// 清除temp的值，避免比交集时出错
				tempSet.clear();
				HashSet<String> areas = broadcasts.get(key);
				tempSet.addAll(areas);
				// 单个地区和所有城市的交集城市
				tempSet.retainAll(allAreas);
				// 如果key指向的那个地区和所有地区的交集城市比maxkey指向的多，则将key赋值给maxkey
				if(maxKey!=null) {
					//比较前，先对maxkey指向的地区集合进行取交集
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
