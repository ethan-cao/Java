import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

public class RoughStrings {
    public RoughStrings() {
    }

    public static int minRoughness(String s, int n) {
        int roughness = -1;
        Entry<Character, Integer> mostEntry = (Entry)searchLetter(s).toArray()[0];
        char mostLetter = (Character)mostEntry.getKey();
        int mostFrequency = (Integer)mostEntry.getValue();
        System.out.println("most letter: " + mostLetter);
        Entry<Character, Integer> leastEntry = (Entry)searchLetter(s).toArray()[searchLetter(s).size() - 1];
        char leastLetter = (Character)leastEntry.getKey();
        int leastFrequency = (Integer)leastEntry.getValue();
        if (mostFrequency - leastFrequency < n) {
            s = removeLetter(s, mostLetter, mostFrequency - leastFrequency);
            mostEntry = (Entry)searchLetter(s).toArray()[0];
            if ((Character)mostEntry.getKey() == mostLetter) {
                roughness = 0;
            } else {
                minRoughness(s, n - (mostFrequency - leastFrequency));
            }
        } else {
            s = removeLetter(s, mostLetter, n);
            mostEntry = (Entry)searchLetter(s).toArray()[0];
            leastEntry = (Entry)searchLetter(s).toArray()[searchLetter(s).size() - 1];
            if ((Character)mostEntry.getKey() == mostLetter) {
                roughness = mostFrequency - n - leastFrequency;
            } else {
                roughness = (Integer)mostEntry.getValue() - (Integer)leastEntry.getValue();
            }
        }

        return roughness;
    }

    private static List<Entry<Character, Integer>> searchLetter(String s) {
        HashMap<Character, Integer> hm = new HashMap();

        for(int i = 0; i < s.length(); ++i) {
            if (hm.containsKey(s.charAt(i))) {
                hm.put(s.charAt(i), (Integer)hm.get(s.charAt(i)) + 1);
            } else {
                hm.put(s.charAt(i), 1);
            }
        }

        List<Entry<Character, Integer>> entryList = new ArrayList(hm.entrySet());
//        RoughStrings.HashMapValueCompare hmvc = new RoughStrings.HashMapValueCompare((RoughStrings.HashMapValueCompare)null);
//        Collections.sort(entryList, hmvc);
        return entryList;
    }

    private static String removeLetter(String s, char c, int n) {
        StringBuilder string = new StringBuilder(s);
        int count = 0;

        for(int i = 0; i < string.length() && string.charAt(i) == c && count != n; ++i) {
            string.deleteCharAt(i);
            ++count;
            --i;
        }

        String newString = new String(string);
        System.out.println("new :" + string);
        return newString;
    }

    public static void main(String[] args) {
        String s = "veryeviltestcase";
        Entry<Character, Integer> mostEntry = (Entry)searchLetter(s).toArray()[0];
        char mostC = (Character)mostEntry.getKey();
        int most = (Integer)mostEntry.getValue();
        Entry<Character, Integer> leastEntry = (Entry)searchLetter(s).toArray()[searchLetter(s).size() - 1];
        char leastC = (Character)leastEntry.getKey();
        int least = (Integer)leastEntry.getValue();
        System.out.println(mostC + " " + most);
        System.out.println(leastC + " " + least);
        int rough = minRoughness(s, 1);
        System.out.println(rough);
    }

    private static class HashMapValueCompare implements Comparator<Entry<Character, Integer>> {
        private HashMapValueCompare() {
        }

        public int compare(Entry<Character, Integer> e1, Entry<Character, Integer> e2) {
            return (Integer)e2.getValue() - (Integer)e1.getValue();
        }
    }
}
