package designPattern;

/**
 * Factory Method Pattern
 *
 * Still encapsulates instance creation in one place to avoid exposing instantiation logic to client,
 * But delegates creation to dedicated sub factory
 * So just need to add new dedicated sub factory if need to create new instance type
 * It complies with: open for extension, closed for modification
 *
 *
 * e.g.:
 * in JDK,  Calendar <- GregorianCalendar
 *                   <- JapaneseImperialCalendar
 * Abstract Factory Calendar delegates object creation to GregorianCalendar and JapaneseImperialCalendar
 *
 */


abstract class PhoneFactory {
    abstract Phone createPhone();

//    // Alternatively
//    public IPhone createIPhone() {
//        return new IPhone();
//    }
//    public Android createAndroid() {
//        return new Android();
//    }
}

class ApplePhoneFactory extends PhoneFactory {
    Phone createPhone() {
        return new IPhone();
    }
}

class GooglePhoneFactory extends PhoneFactory {
    Phone createPhone() {
        return new Android();
    }
}
