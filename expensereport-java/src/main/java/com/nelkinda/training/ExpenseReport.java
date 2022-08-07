package com.nelkinda.training;

import lombok.AllArgsConstructor;

import java.util.Date;
import java.util.List;

enum ExpenseType {
    DINNER, BREAKFAST, CAR_RENTAL
}

@AllArgsConstructor
class Expense {
    ExpenseType type;
    int amount;
}

public class ExpenseReport {
    private final DateHelper dateHelper;

    ExpenseReport(DateHelper dateHelper){
        this.dateHelper = dateHelper;
    }

    public void printReport(List<Expense> expenses, boolean htmlMode) {
        String report = generateReport(expenses, htmlMode);

        System.out.println(report);
    }

    public String generateReport(List<Expense> expenses, boolean htmlMode) {
        String report;
        int total = 0;
        int mealExpenses = 0;

        if (htmlMode) {
            report = generateHtmlHeader() +
                    generateHtmlTable();
        } else {
            report = generateHeader();
        }

        for (Expense expense : expenses) {
            if (expense.type == ExpenseType.DINNER || expense.type == ExpenseType.BREAKFAST) {
                mealExpenses += expense.amount;
            }

            String expenseName = "";
            switch (expense.type) {
            case DINNER:
                expenseName = "Dinner";
                break;
            case BREAKFAST:
                expenseName = "Breakfast";
                break;
            case CAR_RENTAL:
                expenseName = "Car Rental";
                break;
            }

            String mealOverExpensesMarker = expense.type == ExpenseType.DINNER && expense.amount > 5000 || expense.type == ExpenseType.BREAKFAST && expense.amount > 1000 ? "X" : " ";

            if (htmlMode) {
                report += generateHtmlExpense(expense, expenseName, mealOverExpensesMarker);
            } else {
                report += generateExpense(expense, expenseName, mealOverExpensesMarker);
            }

            total += expense.amount;
        }
        if (htmlMode) {
            report += generateHtmlTableClosing() +
                    generateHtmlTotalExpenses(mealExpenses) +
                    generateHtmlClosing();
        }
        else {
            report += generateTotalExpenses(total, mealExpenses);
        }

        return report;
    }

    private String generateHtmlHeader() {
        return "<!DOCTYPE html><html lang=\"en\"><head>" +
                "<title>Expenses " + this.dateHelper.getDateNow() + "</title>" +
                "</head><body><h1>Expenses " + this.dateHelper.getDateNow() + "</h1>";
    }

    private String generateHtmlTable() {
        return "<table><thead>" +
                "<tr><th scope=\"col\">Type</th><th scope=\"col\">Amount</th><th scope=\"col\">Over Limit</th></tr>" +
                "</thead><tbody>";
    }

    private String generateHeader() {
        return "Expenses " + this.dateHelper.getDateNow() + "\n";
    }

    private String generateHtmlExpense(Expense expense, String expenseName, String mealOverExpensesMarker) {
        return "<tr><td>" + expenseName + "</td><td>" + expense.amount + "</td><td>" + mealOverExpensesMarker + "</td></tr>";
    }

    private String generateExpense(Expense expense, String expenseName, String mealOverExpensesMarker) {
        return expenseName + "\t" + expense.amount + "\t" + mealOverExpensesMarker + "\n";
    }

    private String generateHtmlTableClosing() {
        return "</tbody></table>";
    }

    private String generateHtmlTotalExpenses(int mealExpenses) {
        return "<p>Meal expenses: " + mealExpenses + "</p>" +
                "<p>Total expenses: " + mealExpenses + "</p>";
    }

    private String generateHtmlClosing() {
        return "</body></html>";
    }

    private String generateTotalExpenses(int total, int mealExpenses) {
        return "Meal expenses: " + mealExpenses  + "\n" +
                "Total expenses: " + total + "\n";
    }
}
