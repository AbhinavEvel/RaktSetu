package com.rakthsetu.bloodbank.entity.enums;

public enum BloodGroup {
    A_POSITIVE("A+"), A_NEGATIVE("A-"),
    AB_POSITIVE("AB+"), AB_NEGATIVE("AB-"),
    B_POSITIVE("B+"), B_NEGATIVE("B-"),
    O_POSITIVE("O+"), O_NEGATIVE("O-");

    private final String dbValue; // MySQL ENUM me jo exact value store hogi

    BloodGroup(String dbValue) {
        this.dbValue = dbValue;
    }

    public String getDbValue() {
        return dbValue;
    }

    public static BloodGroup fromDbValue(String dbValue) {
        for (BloodGroup bg : values()) {
            if (bg.dbValue.equals(dbValue)) return bg;
        }
        throw new IllegalArgumentException("Unknown blood group: " + dbValue);
    }
}
