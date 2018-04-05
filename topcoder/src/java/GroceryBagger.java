
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GroceryBagger {
    public GroceryBagger() {
    }

    public static int minimumBags(int strength, String[] itemType) {
        int miniBags = 0;
        Set<String> uniqueType = new HashSet(Arrays.asList(itemType));
        List<String> uniqueTypeList = new ArrayList(uniqueType);
        List<String> itemTypeList = Arrays.asList(itemType);

        for(int i = 0; i < uniqueTypeList.size(); ++i) {
            int stock = 0;
            ++miniBags;

            for(int j = 0; j < itemTypeList.size(); ++j) {
                if (((String)uniqueTypeList.get(i)).equals(itemType[j])) {
                    ++stock;
                    if (stock == strength) {
                        stock = 0;
                        if (itemTypeList.subList(j + 1, itemTypeList.size()).contains(uniqueTypeList.get(i))) {
                            ++miniBags;
                        }
                    }
                }
            }
        }

        return miniBags;
    }

    public static void main(String[] args) {
        String[] s = new String[]{"GIY", "GIY", "GDKEUGBSDLVIQBYFIHYTPBVNHJXOTMJYIQOOBYGZSNCWXWSK", "C", "GIY", "GIY", "C", "C", "GIY", "C", "C", "C", "GIY", "GIY", "GIY"};
        System.out.println(minimumBags(14, s));
    }
}
