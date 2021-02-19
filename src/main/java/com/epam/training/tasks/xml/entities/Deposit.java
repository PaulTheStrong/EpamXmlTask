package com.epam.training.tasks.xml.entities;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.text.SimpleDateFormat;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Deposit")
@XmlSeeAlso(TermDeposit.class)

public class Deposit {

    @XmlAttribute(required = true, name="account-id")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    private String id;
    @XmlAttribute(name="country")
    private String country = "Belarus";
    @XmlElement(required = true, name = "bank-name")
    private String bankName;
    @XmlElement(required = true)
    private String info;
    @XmlElement(required = true)
    private double amount;
    @XmlElement(required = true)
    private double profitability;
    @XmlElement(required = true)
    private Currency currency;

    public Deposit() {}

    public Deposit(String id, String country, String bankName, String info, double amount, double profitability, Currency currency) {
        this.id = id;
        this.country = country;
        this.bankName = bankName;
        this.info = info;
        this.amount = amount;
        this.profitability = profitability;
        this.currency = currency;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getProfitability() {
        return profitability;
    }

    public void setProfitability(double profitability) {
        this.profitability = profitability;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Deposit deposit = (Deposit) o;
        return Double.compare(deposit.amount, amount) == 0
                && Double.compare(deposit.profitability, profitability) == 0
                && id.equals(deposit.id)
                && country.equals(deposit.country)
                && bankName.equals(deposit.bankName)
                && info.equals(deposit.info)
                && currency == deposit.currency;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, country, bankName, info,
                            amount, profitability, currency);
    }
}
