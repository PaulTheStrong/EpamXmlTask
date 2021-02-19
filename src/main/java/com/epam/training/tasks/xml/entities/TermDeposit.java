package com.epam.training.tasks.xml.entities;

import javax.xml.bind.annotation.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="TermDeposit")
public class TermDeposit extends Deposit {

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    @XmlElement(name="start-date", required = true)
    @XmlSchemaType(name="date")
    private Date startDate;
    @XmlElement(name="end-date", required = true)
    @XmlSchemaType(name="date")
    private Date endDate;

    public TermDeposit() {}

    public TermDeposit(String id, String country, String bankName, String info, double amount, double profitability, Currency currency, Date startDate, Date endDate) {
        super(id, country, bankName, info, amount, profitability, currency);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        TermDeposit that = (TermDeposit) o;
        return startDate.equals(that.startDate)
                && endDate.equals(that.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), startDate, endDate);
    }
}
