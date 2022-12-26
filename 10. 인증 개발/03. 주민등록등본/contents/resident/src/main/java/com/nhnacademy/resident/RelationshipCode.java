package com.nhnacademy.resident;

public enum RelationshipCode {
    FATHER("father", "부"),
    MOTHER("mother", "모"),
    SPOUSE("spouse", "배우자"),
    CHILD("child", "자녀");

    private final String enValue;
    private final String krValue;

    RelationshipCode(String enValue, String krValue) {
        this.enValue = enValue;
        this.krValue = krValue;
    }
}
