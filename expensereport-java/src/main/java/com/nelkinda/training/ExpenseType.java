package com.nelkinda.training;

enum ExpenseType {
    DINNER, BREAKFAST, CAR_RENTAL;

    public String getName() {
        return switch (this) {
            case DINNER -> "Dinner";
            case BREAKFAST -> "Breakfast";
            case CAR_RENTAL -> "Car Rental";
        };
    }
}
