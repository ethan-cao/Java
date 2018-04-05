
import java.util.Arrays;

public class PaternityTest {
    public PaternityTest() {
    }

    public static int[] possibleFathers(String child, String mother, String[] men) {
        int[] NO_POSSIBILITY = new int[0];
        int[] possibleFathers = new int[men.length];
        int[] matchCount = new int[men.length];
        int amount = 0;
        boolean[][] fatherTest = new boolean[men.length][child.length()];
        boolean[][] motherTest = new boolean[men.length][child.length()];

        for(int i = 0; i < men.length; ++i) {
            fatherTest[i] = test(men[i], child);
            motherTest[i] = test(mother, child);

            for(int j = 0; j < child.length(); ++j) {
                if (men[i].charAt(j) == child.charAt(j)) {
                    ++matchCount[i];
                }
            }

            if (testPossibility(fatherTest[i], motherTest[i]) && matchCount[i] >= child.length() / 2) {
                possibleFathers[amount] = i;
                ++amount;
            }
        }

        if (amount != 0) {
            return Arrays.copyOfRange(possibleFathers, 0, amount);
        } else {
            return NO_POSSIBILITY;
        }
    }

    private static boolean[] test(String adult, String child) {
        boolean[] testResult = new boolean[child.length()];

        for(int i = 0; i < child.length(); ++i) {
            if (adult.charAt(i) == child.charAt(i)) {
                testResult[i] = true;
            } else {
                testResult[i] = false;
            }
        }

        return testResult;
    }

    private static boolean testPossibility(boolean[] man, boolean[] mother) {
        boolean validity = true;

        for(int i = 0; i < man.length; ++i) {
            validity = validity && (man[i] || mother[i]);
        }

        return validity;
    }

    public static void main(String[] args) {
        String s1 = "PREDNTRKUKRLFLENKX";
        String s2 = "NRFXNTRUJSUFWLENKX";
        String[] s3 = new String[]{"PTJDZBCKUKRLFHAXLM", "QIEDYMSKUKRLFPRHGG", "PYEDQZVKUKRLFZVCVB", "POEDTUKKUKRLFOKJTR", "PAEDRYLKUKRLFLHHKV"};
        System.out.println(possibleFathers(s1, s2, s3)[0]);
        System.out.println(possibleFathers(s1, s2, s3)[1]);
        System.out.println(possibleFathers(s1, s2, s3)[2]);
    }
}
