package designPattern;

abstract class CellPhoneFactory {
    abstract Phone createPhone();

//    // Alternatively
//    public IPhone createIPhone() {
//        return new IPhone();
//    }
//    public Android createAndroid() {
//        return new Android();
//    }
}

class ApplePhoneFactory extends CellPhoneFactory {
    Phone createPhone() {
        return new IPhone();
    }
}

class GooglePhoneFactory extends CellPhoneFactory {
    Phone createPhone() {
        return new Android();
    }
}
