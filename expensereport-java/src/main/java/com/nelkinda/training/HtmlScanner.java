package com.nelkinda.training;

class HtmlScanner extends Scanner {

    HtmlScanner(DateHelper dateHelper) {
        super(dateHelper);
    }

    @Override
    String scanReport() {
        return this.content;
    }

    @Override
    void generateHeader() {
        String header = "<!DOCTYPE html><html lang=\"en\"><head>" +
                "<title>Expenses " + this.dateHelper.getDateNow() + "</title>" +
                "</head><body><h1>Expenses " + this.dateHelper.getDateNow() + "</h1>";
        String table = "<table><thead>" +
                "<tr><th scope=\"col\">Type</th><th scope=\"col\">Amount</th><th scope=\"col\">Over Limit</th></tr>" +
                "</thead><tbody>";

        content = header + table;
    }

    @Override
    void generateExpense(int amount, String expenseName, String mealOverExpensesMarker) {
        content += "<tr><td>" + expenseName + "</td><td>" + amount + "</td><td>" + mealOverExpensesMarker + "</td></tr>";
    }

    @Override
    void generateFooter(int mealExpenses, int total) {
        String tableClosing = "</tbody></table>";
        String totalExpenses = "<p>Meal expenses: " + mealExpenses + "</p><p>Total expenses: " + total + "</p>";
        String bodyClosing = "</body></html>";

        content += tableClosing + totalExpenses + bodyClosing;
    }
}
