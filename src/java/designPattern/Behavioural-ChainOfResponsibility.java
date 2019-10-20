package designPattern;

import java.util.LinkedList;
import java.util.List;

/**
 * Chain Of Responsibility Pattern
 *
 * It defines a chain of objects. Request enters from one end and keeps going till it finds the suitable handler.
 */

class ChainOfResponsibilityPattern {
    public static void main(String[] args) {
        Employee director = new Director("Director", 1000);
        Employee manager = new Manager("Manager");
        Employee engineer = new Engineer("Engineer");

        ExpenseChain expenseChain = new ExpenseChain();
        expenseChain.addExpenseProcessors(engineer);
        expenseChain.addExpenseProcessors(manager);
        expenseChain.addExpenseProcessors(director);

        expenseChain.handleExpense(500);

    }
}

class Director extends Employee {
    public Director(String name, int budget) {
        this.name = name;
        this.budget = budget;
    }

    @Override
    public void work() {
        System.out.println(this.name + " is directing...");
    }
}

class ExpenseChain {
    List<Employee> expenseProcessors = new LinkedList<>();

    public void addExpenseProcessors(Employee expenseProcessor) {
        this.expenseProcessors.add(expenseProcessor);
    }

    public void handleExpense(int amount) {

        for (Employee expenseProcessor : this.expenseProcessors) {
            System.out.printf("checking if %s can handle expense %d\n", expenseProcessor.name, amount);

            if (expenseProcessor.budget > amount) {
                System.out.printf("%s is handling expense %d\n", expenseProcessor.name, amount);
                break;
            }
        }
    }
}

