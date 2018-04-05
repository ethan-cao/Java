
import java.util.ArrayList;
import java.util.List;

public class MagicCandy {
    public MagicCandy() {
    }

    public static int whichOne(int n) {
        List<Integer> candyList = new ArrayList();

        for(int i = 0; i < n; ++i) {
            ((List)candyList).add(i + 1);
        }

        while(((List)candyList).size() != 1) {
            candyList = discardSquare((List)candyList);
        }

        return (Integer)((List)candyList).get(0);
    }

    public static List<Integer> discardSquare(List<Integer> candyList) {
        List<Integer> discardIndex = new ArrayList();
        int originalSize = candyList.size();

        int j;
        for(j = 0; j < candyList.size(); ++j) {
            int root = (int)Math.sqrt((double)(j + 1));
            if (root * root == j + 1) {
                discardIndex.add(j);
            }
        }

        for(j = 0; j < discardIndex.size(); ++j) {
            candyList.remove((Integer)discardIndex.get(j) - (originalSize - candyList.size()));
            discardIndex.remove(j);
            --j;
        }

        return candyList;
    }

    public static void main(String[] args) {
        System.out.println(whichOne(473));
    }
}
