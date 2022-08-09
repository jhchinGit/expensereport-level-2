package com.nelkinda.training;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
class Expense {
    public static final int DINNER_LIMIT = 5000;
    public static final int BREAKFAST_LIMIT = 1000;
    private ExpenseType type;
    private int amount;

    public int getMealExpense() {
        if(isMealExpense()){
            return this.amount;
        }

        return 0;
    }

    public boolean isMealOverSpend(){
        boolean isDinnerOverSpend = this.type == ExpenseType.DINNER && this.amount > DINNER_LIMIT;
        boolean isBreakfastOverSpend = this.type == ExpenseType.BREAKFAST && this.amount > BREAKFAST_LIMIT;

        return isDinnerOverSpend || isBreakfastOverSpend;
    }

    private boolean isMealExpense() {
        return this.type == ExpenseType.DINNER || this.type == ExpenseType.BREAKFAST;
    }
}
