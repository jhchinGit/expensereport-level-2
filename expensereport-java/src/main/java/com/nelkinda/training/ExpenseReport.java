package com.nelkinda.training;

import java.util.List;

public class ExpenseReport {
    private final Scanner scanner;

    ExpenseReport(Scanner scanner){
        this.scanner = scanner;
    }

    public void printReport(List<Expense> expenses) {
        String report = generateReport(expenses);

        System.out.println(report);
    }

    public String generateReport(List<Expense> expenses) {
        int total = 0;
        int mealExpenses = 0;

        scanner.generateHeader();

        for (Expense expense : expenses) {
            if (isMealExpense(expense))
                mealExpenses += expense.amount;

            String expenseName = expense.type.getName();
            String mealOverExpensesMarker = getMealOverExpensesMarker(expense);

            scanner.generateExpense(expense.amount, expenseName, mealOverExpensesMarker);

            total += expense.amount;
        }

        scanner.generateFooter(mealExpenses, total);

        return scanner.scanReport();
    }

    private boolean isMealExpense(Expense expense) {
        return expense.type == ExpenseType.DINNER || expense.type == ExpenseType.BREAKFAST;
    }

    private String getMealOverExpensesMarker(Expense expense) {
        boolean isDinnerOverSpend = expense.type == ExpenseType.DINNER && expense.amount > 5000;
        boolean isBreakfastOverSpend = expense.type == ExpenseType.BREAKFAST && expense.amount > 1000;
        boolean isMealOverSpend = isDinnerOverSpend || isBreakfastOverSpend;

        if (isMealOverSpend){
            return "X";
        }

        return " ";
    }
}
