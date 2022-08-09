package com.nelkinda.training;

import java.util.List;

public class ExpenseReport {
    private final Scanner scanner;
    private final List<Expense> expenses;

    ExpenseReport(Scanner scanner, List<Expense> expenses){
        this.scanner = scanner;
        this.expenses = expenses;
    }

    public void print() {
        System.out.println(generate());
    }

    public String generate() {
        this.scanner.generateHeader();
        generateBody();
        this.scanner.generateFooter(getMealExpenses(), getTotalExpenses());

        return this.scanner.scanReport();
    }

    private void generateBody() {
        this.expenses
                .forEach(e ->
                        this.scanner.generateExpense(
                                e.getAmount(),
                                e.getType().getName(),
                                getMealOverExpensesMarker(e)
                        )
                );
    }

    private int getMealExpenses() {
        return this.expenses
                .stream()
                .mapToInt(Expense::getMealExpense)
                .sum();
    }

    private int getTotalExpenses() {
        return this.expenses
                .stream()
                .mapToInt(Expense::getAmount)
                .sum();
    }

    private String getMealOverExpensesMarker(Expense expense) {
        if (expense.isMealOverSpend()){
            return "X";
        }

        return " ";
    }
}
