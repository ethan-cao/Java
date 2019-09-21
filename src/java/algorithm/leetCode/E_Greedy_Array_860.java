package algorithm.leetCode;

import java.util.HashMap;
import java.util.Map;

/*
At a lemonade stand, each lemonade costs $5.
Customers are standing in a queue to buy from you, and order one at a time (in the order specified by bills).

Each customer will only buy 1 lemonade and pay with either a $5, $10, or $20 bill.
You must provide the correct change to each customer, so that the net transaction is that the customer pays $5.

Note that you don't have any change in hand at first. Return true iff you can provide every customer with correct change.

0 <= bills.length <= 10000, bills[i] will be either 5, 10, or 20.

### Example
[5,5,5,10,20] -> T
Explanation: From the first 3 customers, we collect three $5 bills in order.
From the fourth customer, we collect a $10 bill and give back a $5.
From the fifth customer, we give a $10 bill and a $5 bill.
Since all customers got correct change, we output true.

[5,5,10] -> T
[10, 10] -> F

[5,5,10,10,20]-> F
From the first two customers in order, we collect two $5 bills.
For the next two customers in order, we collect a $10 bill and give back a $5 bill.
For the last customer, we can't give change of $15 back because we only have two $10 bills.
Since not every customer received correct change, the answer is false.

### Condition

### Essential problem

### Corner case

*/

public class E_Greedy_Array_860 {

    public static void main(String[] args) {
        int[] bill1 = {5, 5, 5, 10, 20};
        int[] bill2 = {5, 5, 10};
        int[] bill3 = {10, 10};
        int[] bill4 = {5, 5, 10, 10, 20};
        int[] bill5 = {5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 20, 5, 5, 5, 5, 5, 5, 5, 10, 5, 20, 20, 5, 5, 5, 5, 5, 10, 5, 5, 5, 20, 5, 5, 5, 10, 5, 5, 10, 5, 20, 5, 5, 20, 5, 10, 5, 5, 20, 5, 5, 5, 5, 5, 5, 10, 20, 5, 20, 20, 10, 5, 20, 20, 5, 10, 5, 5, 5, 5, 5, 5, 20, 20, 20, 20, 5, 5, 10, 5, 20, 5, 5, 5, 5, 10, 10, 5, 5, 5, 20, 5, 5, 5, 5, 5, 5, 20, 5, 20, 10, 10, 20, 5, 5, 5, 5, 20, 20, 5, 5, 5, 5, 20, 5, 20, 20, 5, 5, 10, 5, 5, 5, 20, 5, 5, 20, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 20, 5, 5, 10, 20, 20, 5, 5, 10, 20, 5, 5, 5, 5, 10, 20, 5, 5, 10, 20, 5, 10, 10};

        System.out.println(lemonadeChange1(bill1));  // T
        System.out.println(lemonadeChange1(bill2));  // T
        System.out.println(lemonadeChange1(bill3));  // F
        System.out.println(lemonadeChange1(bill4));  // F
        System.out.println(lemonadeChange1(bill5));  // T
    }

    public static boolean lemonadeChange(int[] bills) {
        Map<Integer, Integer> tally = new HashMap<>();

        for (int bill : bills) {
            if (bill == 5) {
                tally.put(5, tally.getOrDefault(5, 0) + 1);
            }

            if (bill == 10) {
                int count5 = tally.getOrDefault(5, 0);

                if (count5 >= 1) {
                    tally.put(5, count5 - 1);
                    tally.put(10, tally.getOrDefault(10, 0) + 1);
                } else {
                    return false;
                }
            }

            if (bill == 20) {
                int count5 = tally.getOrDefault(5, 0);
                int count10 = tally.getOrDefault(10, 0);

                // spend 10 first to make sure we have enough 5
                if (count5 >= 1 && count10 >= 1) {  // it is important to use 10 first
                    tally.put(5, count5 - 1);
                    tally.put(10, count10 - 1);
                    tally.put(20, tally.getOrDefault(20, 0) + 1);
                } else if (count5 >= 3) {
                    tally.put(5, count5 - 3);
                    tally.put(20, tally.getOrDefault(20, 0) + 1);
                } else {
                    return false;
                }
            }

        }

        return true;
    }

    // no map implementation
    public static boolean lemonadeChange1(int[] bills) {
        int count5 = 0;
        int count10 = 0;

        for (int bill : bills) {
            if (bill == 5) {
                count5++;
            }

            if (bill == 10) {
                count5--;
                count10++;
            }

            if (bill == 20) {
                if (count10 > 0) {
                    count5--;
                    count10--;
                } else {
                    count5 -= 3;
                }

            }

            if (count5 < 0 || count10 < 0) {
                return false;
            }
        }

        return true;
    }


}
