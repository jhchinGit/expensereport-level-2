package com.nelkinda.training;

class CommonScanner extends Scanner {

    CommonScanner(DateHelper dateHelper) {
        super(dateHelper);
    }

    @Override
    String scanReport() {
        return this.content;
    }

    @Override
    void generateHeader() {
        content = "Expenses " + this.dateHelper.getDateNow() + "\n";
    }

    @Override
    void generateExpense(int amount, String expenseName, String mealOverExpensesMarker) {
        content += expenseName + "\t" + amount + "\t" + mealOverExpensesMarker + "\n";
    }

    @Override
    void generateFooter(int mealExpenses, int total) {
        content += "Meal expenses: " + mealExpenses  + "\nTotal expenses: " + total + "\n";
    }
}
