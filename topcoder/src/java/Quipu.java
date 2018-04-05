import java.util.ArrayList;
import java.util.Iterator;

public class Quipu {
    public Quipu() {
    }

    public static int readKnots(String knots) {
        String modernNumber = "";
        ArrayList<String> digitString = getEachDigit(knots);
        Iterator var4 = digitString.iterator();

        while(true) {
            while(var4.hasNext()) {
                String digit = (String)var4.next();
                if (digit.contains("X")) {
                    String single = (new Integer(digit.length())).toString();
                    modernNumber = modernNumber + single;
                } else if (digit.length() != 1) {
                    for(int i = 1; i < digit.length(); ++i) {
                        modernNumber = modernNumber + "0";
                    }
                }
            }

            return Integer.parseInt(modernNumber);
        }
    }

    private static ArrayList<String> getEachDigit(String knots) {
        ArrayList<String> knot = new ArrayList();
        knots = knots.substring(1, knots.length() - 1);
        if (knots.length() >= 2) {
            for(int i = 0; i < knots.length() - 2; ++i) {
                if (knots.charAt(i) != knots.charAt(i + 1)) {
                    knot.add(knots.substring(0, i + 1));
                    knots = knots.substring(i + 1);
                    i = -1;
                } else if (knots.matches("-+") || knots.matches("X+")) {
                    knot.add(knots);
                    if (knots.contains("-")) {
                        knot.add("--");
                    }
                    break;
                }
            }
        } else {
            knot.add("X");
        }

        Iterator var3 = knot.iterator();

        while(var3.hasNext()) {
            String e = (String)var3.next();
            System.out.println(e);
        }

        return knot;
    }

    public static void main(String[] args) {
        String knots = "-X-------";
        System.out.println(readKnots(knots));
    }
}
