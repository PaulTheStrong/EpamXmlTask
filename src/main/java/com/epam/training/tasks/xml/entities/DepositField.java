package com.epam.training.tasks.xml.entities;

public enum DepositField {
    DEPOSITS("deposits"),
    ACCOUNT_ID("account-id"),
    BANK_NAME("bank-name"),
    AMOUNT("amount"),
    PROFITABILITY("profitability"),
    INFO("info"),
    CURRENCY("currency"),
    START_DATE("start-date"),
    END_DATE("end-date"),
    DEPOSIT("deposit"),
    TERM_DEPOSIT("term-deposit")
    ;

    private final String value;
    private DepositField(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
