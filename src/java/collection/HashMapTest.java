package collection;

import java.util.*;

public class HashMapTest {

    public static void main(String[] args) {
        // DoubleBraceInitialization : http://wiki.c2.com/?DoubleBraceInitialization
        HashMap<String, String> hm1 = new HashMap<String, String>() {{
            put("a1", "a1");
            put("a2", "a1");
        }};

        // get list set containing all keys
        Set<String> keys = hm1.keySet();
        // get list set containing all values
        Set<String> values = new HashSet<>(hm1.values());

        HashMap<String, Integer> hm2 = new HashMap<>();
        hm2.put("list", 2);
        hm2.put("b", 1);
        hm2.put("c", 3);

        // iteration
        for (Map.Entry<String, Integer> entry : hm2.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }

        System.out.println(sortOnValue1(hm2));

        // lambda
        hm2.forEach((k, v) -> System.out.println("key: " + k + " value:" + v));




        Map map = new HashMap();
        // This cause compiler error, since generic type is not used, map.entrySet will return Object, rather than Map.Entry<K, V>
//        for(Map.Entry entry : map.entrySet()){ }
        for (Iterator it = map.entrySet().iterator(); it.hasNext(); ) {
            Object entry = it.next();
        }

        // It we use generics
        Map<Object, Object> mapNew = new HashMap<>();
        for (Map.Entry entry : mapNew.entrySet()) {

        }

    }

    static <K, V extends Comparable> List<Map.Entry<K, V>> sortOnValue1(Map<K, V> map) {
        List<Map.Entry<K, V>> entries = new LinkedList<>(map.entrySet());

        Collections.sort(entries, new Comparator<Map.Entry<K, V>>() {
            @Override
            public int compare(Map.Entry<K, V> o1, Map.Entry<K, V> o2) {
                return o1.getValue().compareTo(o2.getValue());
            }
        });

        return entries;
    }


/*
    // Using Guava
	static List sortOnValue(Map map){
		List<Map.Entry<String, Integer>> entries = Lists.newArrayList(map.entrySet());

		Ordering<Map.Entry<String, Integer>> descendingOrder = new Ordering<Map.Entry<String, Integer>>() {
			@Override
			public int compare(Map.Entry<String, Integer> left, Map.Entry<String, Integer> right) {
				return left.getValue().compareTo(right.getValue());
			}
		};

		Collections.sort(entries, descendingOrder);

		return entries;
	}
*/

}
