package collection;
import java.util.Set;
import java.util.LinkedHashSet;

public class LinkedHashSetTest{
    public static void main(String[] args){
        LinkedHashSet<Integer> linkHashSet = new LinkedHashSet<Integer>();

        linkHashSet.add(new Integer(2));
        linkHashSet.add(new Integer(3));
        linkHashSet.add(new Integer(0));
        linkHashSet.add(new Integer(-4));
        linkHashSet.add(new Integer(92));
        linkHashSet.add(new Integer(88));
        
//       for (Integer each : linkHashSet){
//           System.out.println(each);
//       }

        System.out.println(linkHashSet);

    }
}
