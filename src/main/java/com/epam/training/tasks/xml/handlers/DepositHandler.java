package com.epam.training.tasks.xml.handlers;

import com.epam.training.tasks.xml.entities.Currency;
import com.epam.training.tasks.xml.entities.Deposit;
import com.epam.training.tasks.xml.entities.DepositField;
import com.epam.training.tasks.xml.entities.TermDeposit;
import org.apache.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.EnumSet;
import java.util.List;

public class DepositHandler extends DefaultHandler {

    private static final Logger LOGGER = Logger.getRootLogger();
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    private static final String ACCOUNT_ID = "account-id";
    private static final String COUNTRY = "country";
    private static final String DEPOSIT = "deposit";
    private static final String TERM_DEPOSIT = "term-deposit";

    private final List<Deposit> deposits;
    private Deposit currentDeposit;

    private DepositField currentField;


    public DepositHandler() {
        deposits = new ArrayList<Deposit>();
    }

    public List<Deposit> getDeposits() {
        return deposits;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        if(localName.equals(DEPOSIT)) {
            currentDeposit = new Deposit();
            currentDeposit.setId(attributes.getValue(ACCOUNT_ID));
            if (attributes.getLength() == 2) {
                currentDeposit.setCountry(attributes.getValue(COUNTRY));
            }
        } else if (localName.equals(TERM_DEPOSIT)) {
            currentDeposit = new TermDeposit();
            currentDeposit.setId(attributes.getValue(ACCOUNT_ID));
            if (attributes.getLength() == 2) {
                currentDeposit.setCountry(attributes.getValue(COUNTRY));
            }
        } else {
            String fieldString = localName.toUpperCase().replace('-', '_');
            currentField = DepositField.valueOf(fieldString);
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        if (localName.equals(DEPOSIT) || localName.equals(TERM_DEPOSIT)) {
            deposits.add(currentDeposit);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        String data = new String(ch, start, length).trim();
        if (currentField != null) {
            switch (currentField) {
                case BANK_NAME:
                    currentDeposit.setBankName(data);
                    break;
                case AMOUNT:
                    currentDeposit.setAmount(Double.parseDouble(data));
                    break;
                case PROFITABILITY:
                    currentDeposit.setProfitability(Double.parseDouble(data));
                    break;
                case INFO:
                    currentDeposit.setInfo(data);
                    break;
                case START_DATE:
                    try {
                        Date startDate = DATE_FORMAT.parse(data);
                        ((TermDeposit)currentDeposit).setStartDate(startDate);
                    } catch (ParseException e) {
                        LOGGER.warn("IncorrectDateFormat in StartDate");
                    }
                    break;
                case END_DATE:
                    try {
                        Date startDate = DATE_FORMAT.parse(data);
                        ((TermDeposit)currentDeposit).setEndDate(startDate);
                    } catch (ParseException e) {
                        LOGGER.warn("IncorrectDateFormat in EndDate");
                    }
                    break;
                case CURRENCY:
                    currentDeposit.setCurrency(Currency.valueOf(data));
                    break;
            }
        }
        currentField = null;
    }
}
