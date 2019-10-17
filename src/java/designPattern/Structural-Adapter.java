package designPattern;

/**
 * Adapter Pattern
 *
 * It wrap an object to make it compatible with another class, not always possible
 *
 * e.g. Arrays.asList(...a) wrap array to make it compatible with List
 */

class AdapterPattern {

    public static void main(String... args) {
        ChinesePlug chinesePlug = new ChinesePlug();

        EUSocket euSocket = new EUSocket();
        ChineseSocket chineseSocket = ChinaToEUAdapter.adapt(euSocket);

        chinesePlug.connect(chineseSocket);
    }

}

class ChineseSocket {
}

class EUSocket {
}

class ChinaToEUAdapter {
    static ChineseSocket adapt(EUSocket euSocket) {
        return new ChineseSocket(); // wrapper euSocket to make it comply with ChineseSocket
    }
}

class ChinesePlug {
    public void connect(ChineseSocket socket) {
    }
}

class EUPlug {
    public void connect(EUSocket socket) {
    }
}
