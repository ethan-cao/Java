package collection;

import java.util.*;

public class HashSetTest {

    public static void main(String[] args) {
        Obj obj1 = new Obj("1");
        Obj obj2 = new Obj("2");
        Obj obj3 = new Obj("3");

        Set<Obj> s = new HashSet<>();
        s.add(obj1);
        s.add(obj2);
        s.add(obj3);

        System.out.println(s.size());
    }

    static void sort() {
        Set<Integer> set= new HashSet<>();

        // Solution 1
        List sortedList = new ArrayList(set);
        Collections.sort(sortedList);

        // Solution 2
        TreeSet<Integer> sortedSort = new TreeSet<>(set);
    }

    static class Obj {
        String name;

        Obj(String name){
            this.name = name;
        }

        @Override
        public int hashCode(){
//            return Objects.hash(this.name);
            return 1;
        }

        @Override
        public boolean equals(Object that){
            if (that instanceof Obj){
                if ( ((Obj) that).name.equals(this.name) ){
                    return true;
                }
            }

           return false;
        }
    }

}

