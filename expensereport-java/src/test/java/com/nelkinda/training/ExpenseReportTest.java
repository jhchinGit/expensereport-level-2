package com.nelkinda.training;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ExpenseReportTest {

    @BeforeAll
    public void setUp() {
        DateHelper dateHelper = Mockito.mock(DateHelper.class);
        Mockito.when(dateHelper.getDateNow()).thenReturn(new Date(2022, 7, 8));
        EXPENSE_REPORT = new ExpenseReport(dateHelper);
    }

    public static ExpenseReport EXPENSE_REPORT;

    @Test
    void printReportNotHtmlMode() {
        EXPENSE_REPORT.printReport(Collections.emptyList(), false);
    }

    @Test
    void printReportHtmlMode() {
        EXPENSE_REPORT.printReport(Collections.emptyList(), true);
    }

    @Test
    void printBreakfastExpenseReport(){
        Expense expense = new Expense(ExpenseType.BREAKFAST, 0);

        EXPENSE_REPORT.printReport(List.of(expense), false);
    }

    @Test
    void printBreakfastExpenseReportHtmlMode(){
        Expense expense = new Expense(ExpenseType.BREAKFAST, 0);

        EXPENSE_REPORT.printReport(List.of(expense), true);
    }

    @Test
    void printDinnerExpenseReport(){
        Expense expense = new Expense(ExpenseType.DINNER, 0);

        EXPENSE_REPORT.printReport(List.of(expense), false);
    }

    @Test
    void printCarRentalExpenseReport(){
        Expense expense = new Expense(ExpenseType.CAR_RENTAL, 0);

        EXPENSE_REPORT.printReport(List.of(expense), false);
    }

    @Test
    void generateBreakfastExpenseReport(){
        Expense expense = new Expense(ExpenseType.BREAKFAST, 10);

        String report = EXPENSE_REPORT.generateReport(List.of(expense), false);
        String expected = "Expenses Tue Aug 08 00:00:00 MYT 3922\n" +
                "Breakfast\t10\t \n" +
                "Meal expenses: 10\n" +
                "Total expenses: 10\n";

        assertEquals(expected, report);
    }

    @Test
    void generateDinnerExpenseReport(){
        Expense expense = new Expense(ExpenseType.DINNER, 10);

        String report = EXPENSE_REPORT.generateReport(List.of(expense), false);
        String expected = "Expenses Tue Aug 08 00:00:00 MYT 3922\n" +
                "Dinner\t10\t \n" +
                "Meal expenses: 10\n" +
                "Total expenses: 10\n";

        assertEquals(expected, report);
    }

    @Test
    void generateCarRentalExpenseReport(){
        Expense expense = new Expense(ExpenseType.CAR_RENTAL, 10);

        String report = EXPENSE_REPORT.generateReport(List.of(expense), false);
        String expected = "Expenses Tue Aug 08 00:00:00 MYT 3922\n" +
                "Car Rental\t10\t \n" +
                "Meal expenses: 0\n" +
                "Total expenses: 10\n";

        assertEquals(expected, report);
    }

    @Test
    void generateBreakfastHtmlExpenseReport(){
        Expense expense = new Expense(ExpenseType.BREAKFAST, 10);

        String report = EXPENSE_REPORT.generateReport(List.of(expense), true);
        String expected = "<!DOCTYPE html><html lang=\"en\"><head><title>Expenses Tue Aug 08 00:00:00 MYT 3922</title></head><body><h1>Expenses Tue Aug 08 00:00:00 MYT 3922</h1><table><thead><tr><th scope=\"col\">Type</th><th scope=\"col\">Amount</th><th scope=\"col\">Over Limit</th></tr></thead><tbody><tr><td>Breakfast</td><td>10</td><td> </td></tr></tbody></table><p>Meal expenses: 10</p><p>Total expenses: 10</p></body></html>";

        assertEquals(expected, report);
    }

    @Test
    void generateDinnerHtmlExpenseReport(){
        Expense expense = new Expense(ExpenseType.DINNER, 10);

        String report = EXPENSE_REPORT.generateReport(List.of(expense), true);
        String expected = "<!DOCTYPE html><html lang=\"en\"><head><title>Expenses Tue Aug 08 00:00:00 MYT 3922</title></head><body><h1>Expenses Tue Aug 08 00:00:00 MYT 3922</h1><table><thead><tr><th scope=\"col\">Type</th><th scope=\"col\">Amount</th><th scope=\"col\">Over Limit</th></tr></thead><tbody><tr><td>Dinner</td><td>10</td><td> </td></tr></tbody></table><p>Meal expenses: 10</p><p>Total expenses: 10</p></body></html>";

        assertEquals(expected, report);
    }

    @Test
    void generateCarRentalHtmlExpenseReport(){
        Expense expense = new Expense(ExpenseType.CAR_RENTAL, 10);

        String report = EXPENSE_REPORT.generateReport(List.of(expense), true);
        String expected = "<!DOCTYPE html><html lang=\"en\"><head><title>Expenses Tue Aug 08 00:00:00 MYT 3922</title></head><body><h1>Expenses Tue Aug 08 00:00:00 MYT 3922</h1><table><thead><tr><th scope=\"col\">Type</th><th scope=\"col\">Amount</th><th scope=\"col\">Over Limit</th></tr></thead><tbody><tr><td>Car Rental</td><td>10</td><td> </td></tr></tbody></table><p>Meal expenses: 0</p><p>Total expenses: 0</p></body></html>";

        assertEquals(expected, report);
    }
}
