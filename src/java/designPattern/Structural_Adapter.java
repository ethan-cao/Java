package designPattern;

/**
 * Adapter Pattern
 *
 * It wraps an object as another type to make it compatible with another type,
 * possible if the type has similar functional role
 *
 * It takes an instance of different abstract/interface type
 * and returns an implementation of own/another abstract/interface type,
 * which decorates/overrides the given instance
 *
 * e.g. Arrays.asList(...a) wrap array to make it compatible with List
 */

class AdapterPattern {
    public static void main(String... args) {
        ChinesePlug chinesePlug = new ChinesePlug();
        EUSocket euSocket = new NLSocket();

        ChineseSocket chineseSocket = ChinaToEUAdapter.adapt(euSocket);

        chinesePlug.connect(chineseSocket);
    }
}

abstract class ChineseSocket {
}

class ChineseSocketA extends ChineseSocket {
}

abstract class EUSocket {
}

class NLSocket extends EUSocket {
}

class ChinaToEUAdapter {
    static ChineseSocket adapt(EUSocket euSocket) {
        return new ChineseSocketA(); // wrapper euSocket to make it comply with ChineseSocket
    }
}

class ChinesePlug {
    public void connect(ChineseSocket socket) {
    }
}

