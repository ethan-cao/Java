package designPattern;

/**
 * Factory Pattern
 *
 * It encapsulates instance creation in one place to avoid exposing instantiation logic to client
 * It requires to modify factory if need to create new instance type
 * It does not complies with: open for extension, closed for modification,
 * that is where Factory Method comes to play
 *
 */

class FactoryPattern {
    public static void main(String[] args) {
    }
}


abstract class Phone {
}

class IPhone extends Phone {
}

class Android extends Phone {
}


class PhoneFactory {

    public Phone createPhone(String phoneType) {
        if ("iPhone".equalsIgnoreCase(phoneType)) {
            return new IPhone();
        } else if ("android".equalsIgnoreCase(phoneType)) {
            return new Android();
        }

        // add more logic if need to create new instance type

        return null;
    }

}