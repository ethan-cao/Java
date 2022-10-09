package basic;

import java.util.EnumMap;
import java.util.EnumSet;

public class EnumTest {

    private enum FIXED_VALUE{
        VALUE1, VALUE2, VALUE3;
    }

    static class A {
    }

    class B {
    }

    interface I {
        public void m();
    }

    /**
     * enum implicitly extend java.lang.Enum, thus they can't extend another class/Enum
     * public abstract class Enum<E extends Enum<E>> implements Comparable<E>, Serializable
     *
     * it can only implement additional interfaces
     */
    public enum Light implements I {
        /**
         * constructor invocation must be present in the very beginning
         * separate each value with, use ; in the end
         */
        RED(2),  // ordinal starts from 0
        GREEN(3),
        YELLOW(2),
        Blue(4) {
            @Override
            public String toString() {
                return "The blue one";
            }
        };

        private int nCode;

        static String type = "Light";

        protected synchronized void m1() {
        }

        // Constructor is private implicitly, and can only be private
        Light(int _nCode) {
            this.nCode = _nCode;
        }

        // Overload
        Light() {
        }

        @Override
        public void m() {
            System.out.println("m() implementation ");
        }

        static void staticMethod() {
        }

        @Override
        public String toString() {
            return String.valueOf(this.nCode);
        }
    }

    //  演示枚举类型的遍历
    private static void testTraversalEnum() {
        Light[] allLight = Light.values();
        for (Light aLight : allLight) {  // order is the order ENUM is defined
            System.out.println(" 当前灯 name ： " + aLight.name());
            System.out.println(" 当前灯 ordinal ： " + aLight.ordinal());
            System.out.println(" 当前灯： " + aLight);
        }
    }

    //  演示 EnumMap 的使用， EnumMap 跟 HashMap 的使用差不多，只不过 key 要是枚举类型
    private static void testEnumMap() {
        // 1. 演示定义 EnumMap 对象， EnumMap 对象的构造函数需要参数传入 , 默认是 key 的类的类型
        EnumMap<Light, String> currEnumMap = new EnumMap<>(Light.class);
        currEnumMap.put(Light.RED, " 红灯 ");
        currEnumMap.put(Light.GREEN, " 绿灯 ");
        currEnumMap.put(Light.YELLOW, " 黄灯 ");

        // 2. 遍历对象
        for (Light aLight : Light.values()) {
            System.out.println("[key=" + aLight.name() + ",value="
                    + currEnumMap.get(aLight) + "]");
        }
    }

    /**
     * 演示 EnumSet 如何使用， EnumSet 是一个抽象类，获取一个类型的枚举类型内容
     * 可以使用 allOf 方法
     */
    private static void testEnumSet() {
        EnumSet<Light> currEnumSet = EnumSet.allOf(Light.class);
        for (Light aLightSetElement : currEnumSet) {
            System.out.println(" 当前 EnumSet 中数据为： " + aLightSetElement);
        }

    }

    public static void main(String... args) {
        System.out.println(" 演示枚举类型的遍历 ......");
        testTraversalEnum();

        System.out.println(" 演示 EnmuMap 对象的使用和遍历 .....");
        testEnumMap();

        System.out.println(" 演示 EnmuSet 对象的使用和遍历 .....");
        testEnumSet();
    }

    void testSwitch() {
        Light l = Light.GREEN;

        switch (l) {
            case RED:
                break;
//           case Light.RED: break;  // compiler error
        }
    }
}

enum NUMBERENUM {
    ONE, TWO, Three   // valid, as there is a default constructor
    // ; can be ignored in this case
}

class E2 {
    enum B {B}

    void C() {
//        enum D { D }  // cannot have method local inner enum
    }
}

