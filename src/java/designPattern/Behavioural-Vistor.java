package designPattern;

/**
 * Visitor Pattern
 *
 * It adds operation to object without modifying the object structure
 * by separating the operation from the object structure on which it operates
 *
 * it follows  open/closed principle
 */

class VisitorPattern {
    public static void main(String[] args) {
        Home home = new Home();

        Visitor friendVisitor = new FriendVisitor();
        home.accept(friendVisitor);

        Visitor familyVisitor = new FamilyVisitor();
        home.accept(familyVisitor);

        // when we wanna add new operation to home object,
        // just set up a new Type PartnerVisitor to host new operation

        Visitor partnerVisitor = new PartnerVisitor();
        home.accept(partnerVisitor);
    }
}


// Visitor host new operation
interface Visitor {
    void visit(Home home);
}

class FriendVisitor implements Visitor {
    @Override
    public void visit(Home home) {
        System.out.printf("Friend is visiting home\n");
    }
}

class FamilyVisitor implements Visitor {
    @Override
    public void visit(Home home) {
        System.out.printf("Family is visiting home\n");
    }
}

class PartnerVisitor implements Visitor {
    @Override
    public void visit(Home home) {
        System.out.printf("Partner is visiting home\n");
    }
}


// Host remains the same
interface Host {
    void accept(Visitor visitor);
}

class Home implements Host {
    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}