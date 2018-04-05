import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class SortishDiv2 {
    public static int count = 0;

    public SortishDiv2() {
    }

    public static int ways(int sortedness, int[] seq) {
        int waysCount = 0;
        Set<Integer> diff = new HashSet();

        int i;
        for(i = 1; i <= seq.length; ++i) {
            diff.add(i);
        }

        int[] var7 = seq;
        int var6 = seq.length;

        for(int var5 = 0; var5 < var6; ++var5) {
            i = var7[var5];
            diff.remove(i);
        }

        getWays(diff, seq, sortedness);
        return waysCount;
    }

    private static void getWays(Set<Integer> diff, int[] seq, int sortedness) {
        if (diff.size() > 0) {
            for(int i = 0; i < seq.length; ++i) {
                if (seq[i] == 0) {
                    for(Iterator var5 = diff.iterator(); var5.hasNext(); seq[i] = 0) {
                        int temp = (Integer)var5.next();
                        seq[i] = temp;
                        Set<Integer> newDiff = new HashSet();
                        Iterator var8 = diff.iterator();

                        while(var8.hasNext()) {
                            int t = (Integer)var8.next();
                            newDiff.add(t);
                        }

                        newDiff.remove(temp);
                        getWays(newDiff, seq, sortedness);
                    }

                    return;
                }
            }
        } else if (getSortedness(seq) == sortedness) {
            ++count;
        }

    }

    private static int getSortedness(int[] seq) {
        int sortedness = 0;

        for(int i = 0; i < seq.length; ++i) {
            for(int j = i + 1; j < seq.length; ++j) {
                if (seq[i] < seq[j]) {
                    ++sortedness;
                }
            }
        }

        return sortedness;
    }

    public static void main(String[] args) {
        int sortedness = 5;
        int[] seq = new int[]{4, 0, 0, 2, 0};
        ways(sortedness, seq);
        System.out.println(count);
    }
}
