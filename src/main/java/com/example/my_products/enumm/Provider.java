package com.example.my_products.enumm;

public enum Provider {  //enumeration (перечисление) = класс перечисления
    Арти_партс("Арти партс"),
    LadyLux("LadyLux"),
    OBi("OBi"),
    Батькин_Резерв("Батькин Резерв");

    private final String displayValue;

    Provider(String displayValue) {
        this.displayValue = displayValue;
    }

    public String getDisplayValue() {
        return displayValue;
    }
}
