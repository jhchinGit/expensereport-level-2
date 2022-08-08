package com.nelkinda.training;

abstract class Scanner {
    protected final DateHelper dateHelper;
    protected String content;

    Scanner(DateHelper dateHelper){
        this.dateHelper = dateHelper;
        this.content = "";
    }
    abstract String scanReport();
    abstract void generateHeader();
    abstract void generateExpense(int amount, String expenseName, String mealOverExpensesMarker);
    abstract void generateFooter(int mealExpenses, int total);
}
