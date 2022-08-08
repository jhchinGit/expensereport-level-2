package com.nelkinda.training;

enum ExpenseType {
    DINNER, BREAKFAST, CAR_RENTAL;

    public String getName() {
        switch (this) {
            case DINNER:
                return "Dinner";
            case BREAKFAST:
                return "Breakfast";
            default:
                return "Car Rental";
        }
    }
}
