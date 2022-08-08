package com.nelkinda.training;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ExpenseReportTest {

    @BeforeAll
    public void setUp() {
        DateHelper dateHelper = Mockito.mock(DateHelper.class);
        Mockito.when(dateHelper.getDateNow()).thenReturn(new Date(2022, 7, 8));
        COMMON_EXPENSE_REPORT = new ExpenseReport(new CommonScanner(dateHelper));
        HTML_EXPENSE_REPORT = new ExpenseReport(new HtmlScanner(dateHelper));
    }

    public static ExpenseReport COMMON_EXPENSE_REPORT;
    public static ExpenseReport HTML_EXPENSE_REPORT;

    @Test
    void printBreakfastExpenseReport(){
        Expense expense = new Expense(ExpenseType.BREAKFAST, 0);

        COMMON_EXPENSE_REPORT.printReport(List.of(expense));
    }

    @Test
    void printBreakfastExpenseReportHtmlMode(){
        Expense expense = new Expense(ExpenseType.BREAKFAST, 0);

        HTML_EXPENSE_REPORT.printReport(List.of(expense));
    }

    @Test
    void printDinnerExpenseReport(){
        Expense expense = new Expense(ExpenseType.DINNER, 0);

        COMMON_EXPENSE_REPORT.printReport(List.of(expense));
    }

    @Test
    void printCarRentalExpenseReport(){
        Expense expense = new Expense(ExpenseType.CAR_RENTAL, 0);

        COMMON_EXPENSE_REPORT.printReport(List.of(expense));
    }

    @Test
    void generateBreakfastExpenseReport(){
        Expense expense = new Expense(ExpenseType.BREAKFAST, 10);

        String report = COMMON_EXPENSE_REPORT.generateReport(List.of(expense));
        String expected = "Expenses Tue Aug 08 00:00:00 MYT 3922\n" +
                "Breakfast\t10\t \n" +
                "Meal expenses: 10\n" +
                "Total expenses: 10\n";

        assertEquals(expected, report);
    }

    @Test
    void generateDinnerExpenseReport(){
        Expense expense = new Expense(ExpenseType.DINNER, 10);

        String report = COMMON_EXPENSE_REPORT.generateReport(List.of(expense));
        String expected = "Expenses Tue Aug 08 00:00:00 MYT 3922\n" +
                "Dinner\t10\t \n" +
                "Meal expenses: 10\n" +
                "Total expenses: 10\n";

        assertEquals(expected, report);
    }

    @Test
    void generateCarRentalExpenseReport(){
        Expense expense = new Expense(ExpenseType.CAR_RENTAL, 10);

        String report = COMMON_EXPENSE_REPORT.generateReport(List.of(expense));
        String expected = "Expenses Tue Aug 08 00:00:00 MYT 3922\n" +
                "Car Rental\t10\t \n" +
                "Meal expenses: 0\n" +
                "Total expenses: 10\n";

        assertEquals(expected, report);
    }

    @Test
    void generateBreakfastHtmlExpenseReport(){
        Expense expense = new Expense(ExpenseType.BREAKFAST, 10);

        String report = HTML_EXPENSE_REPORT.generateReport(List.of(expense));
        String expected = "<!DOCTYPE html><html lang=\"en\"><head><title>Expenses Tue Aug 08 00:00:00 MYT 3922</title></head><body><h1>Expenses Tue Aug 08 00:00:00 MYT 3922</h1><table><thead><tr><th scope=\"col\">Type</th><th scope=\"col\">Amount</th><th scope=\"col\">Over Limit</th></tr></thead><tbody><tr><td>Breakfast</td><td>10</td><td> </td></tr></tbody></table><p>Meal expenses: 10</p><p>Total expenses: 10</p></body></html>";

        assertEquals(expected, report);
    }

    @Test
    void generateOverSpendBreakfastExpenseReport(){
        Expense expense = new Expense(ExpenseType.BREAKFAST, 1001);

        String report = HTML_EXPENSE_REPORT.generateReport(List.of(expense));
        String expected = "<!DOCTYPE html><html lang=\"en\"><head><title>Expenses Tue Aug 08 00:00:00 MYT 3922</title></head><body><h1>Expenses Tue Aug 08 00:00:00 MYT 3922</h1><table><thead><tr><th scope=\"col\">Type</th><th scope=\"col\">Amount</th><th scope=\"col\">Over Limit</th></tr></thead><tbody><tr><td>Breakfast</td><td>1001</td><td>X</td></tr></tbody></table><p>Meal expenses: 1001</p><p>Total expenses: 1001</p></body></html>";

        assertEquals(expected, report);
    }

    @Test
    void generateDinnerHtmlExpenseReport(){
        Expense expense = new Expense(ExpenseType.DINNER, 10);

        String report = HTML_EXPENSE_REPORT.generateReport(List.of(expense));
        String expected = "<!DOCTYPE html><html lang=\"en\"><head><title>Expenses Tue Aug 08 00:00:00 MYT 3922</title></head><body><h1>Expenses Tue Aug 08 00:00:00 MYT 3922</h1><table><thead><tr><th scope=\"col\">Type</th><th scope=\"col\">Amount</th><th scope=\"col\">Over Limit</th></tr></thead><tbody><tr><td>Dinner</td><td>10</td><td> </td></tr></tbody></table><p>Meal expenses: 10</p><p>Total expenses: 10</p></body></html>";

        assertEquals(expected, report);
    }

    @Test
    void generateOverSpendDinnerExpenseReport(){
        Expense expense = new Expense(ExpenseType.DINNER, 5001);

        String report = HTML_EXPENSE_REPORT.generateReport(List.of(expense));
        String expected = "<!DOCTYPE html><html lang=\"en\"><head><title>Expenses Tue Aug 08 00:00:00 MYT 3922</title></head><body><h1>Expenses Tue Aug 08 00:00:00 MYT 3922</h1><table><thead><tr><th scope=\"col\">Type</th><th scope=\"col\">Amount</th><th scope=\"col\">Over Limit</th></tr></thead><tbody><tr><td>Dinner</td><td>5001</td><td>X</td></tr></tbody></table><p>Meal expenses: 5001</p><p>Total expenses: 5001</p></body></html>";

        assertEquals(expected, report);
    }

    @Test
    void generateCarRentalHtmlExpenseReport(){
        Expense expense = new Expense(ExpenseType.CAR_RENTAL, 10);

        String report = HTML_EXPENSE_REPORT.generateReport(List.of(expense));
        String expected = "<!DOCTYPE html><html lang=\"en\"><head><title>Expenses Tue Aug 08 00:00:00 MYT 3922</title></head><body><h1>Expenses Tue Aug 08 00:00:00 MYT 3922</h1><table><thead><tr><th scope=\"col\">Type</th><th scope=\"col\">Amount</th><th scope=\"col\">Over Limit</th></tr></thead><tbody><tr><td>Car Rental</td><td>10</td><td> </td></tr></tbody></table><p>Meal expenses: 0</p><p>Total expenses: 10</p></body></html>";

        assertEquals(expected, report);
    }
}
