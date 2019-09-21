package collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class LinkedHashMapTest{
    public static void main(String[] args){
        LinkedHashMap<String, Integer> linkedHashMap = new LinkedHashMap<String, Integer>();

        linkedHashMap.put("Chinese", 90);
        linkedHashMap.put("Math", 99);
        linkedHashMap.put("English", 95);
        linkedHashMap.put("English", 222);

       for (Map.Entry<String, Integer> entry: linkedHashMap.entrySet() ){
           System.out.println("keys :" + entry.getKey() );
       }

        Set entrySet = linkedHashMap.entrySet();

        System.out.println(entrySet);

    }
}
