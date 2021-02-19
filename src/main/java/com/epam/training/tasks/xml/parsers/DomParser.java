package com.epam.training.tasks.xml.parsers;

import com.epam.training.tasks.xml.exceptions.XmlParseException;
import com.epam.training.tasks.xml.entities.Currency;
import com.epam.training.tasks.xml.entities.Deposit;
import com.epam.training.tasks.xml.entities.DepositField;
import com.epam.training.tasks.xml.entities.TermDeposit;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class DomParser implements XmlParser {

    private static final Logger LOGGER = Logger.getRootLogger();
    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    private static final DocumentBuilderFactory FACTORY = DocumentBuilderFactory.newInstance();

    private static final String ACCOUNT_ID = "account-id";
    private static final String COUNTRY = "country";
    private static final String INFO = "info";
    private static final String AMOUNT = "amount";
    private static final String CURRENCY = "currency";
    private static final String PROFITABILITY = "profitability";
    private static final String BANK_NAME = "bank-name";
    private static final String START_DATE = "start-date";
    private static final String END_DATE = "end-date";
    private static final String TERM_DEPOSIT = "term-deposit";
    private static final String DEPOSIT = "deposit";

    public List<Deposit> parse(String xmlPath) throws XmlParseException {
        List<Deposit> deposits = new ArrayList<Deposit>();
        DocumentBuilder documentBuilder;
        try {
            documentBuilder = FACTORY.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            throw new XmlParseException("Error creating documentBuilder: ", e);
        }
        try {
            Document document = documentBuilder.parse(xmlPath);
            Element root = document.getDocumentElement();
            fillList(deposits, root);
        } catch (SAXException e) {
            LOGGER.error("File parsing error: " + e);
        } catch (IOException e) {
            LOGGER.error("File i/o error: " + e);
        } catch (ParseException e) {
            LOGGER.error("Parse exception: " + e);
        }
        return deposits;
    }

    private void fillList(List<Deposit> deposits, Element root) throws ParseException {
        NodeList depositsList = root.getElementsByTagName(DEPOSIT);
        for (int i = 0; i < depositsList.getLength(); i++) {
            Element depositElement = (Element) depositsList.item(i);
            Deposit deposit = createDeposit(depositElement);
            deposits.add(deposit);
        }

        depositsList = root.getElementsByTagName(TERM_DEPOSIT);
        for (int i = 0; i < depositsList.getLength(); i++) {
            Element depositElement = (Element) depositsList.item(i);
            Deposit deposit = createTermDeposit(depositElement);
            deposits.add(deposit);
        }
    }

    private Deposit createDeposit(Element depositElement) {
        Deposit deposit = createDepositByTag(depositElement.getTagName());

        String id = depositElement.getAttribute(ACCOUNT_ID);
        deposit.setId(id);

        String country = depositElement.getAttribute(COUNTRY);
        deposit.setCountry(country.equals("") ? "Belarus" : country);

        double amount = Double.parseDouble(getElementTextContent(depositElement, AMOUNT));
        deposit.setAmount(amount);

        double profitabilty = Double.parseDouble(getElementTextContent(depositElement, PROFITABILITY));
        deposit.setProfitability(profitabilty);

        String info = getElementTextContent(depositElement, INFO);
        deposit.setInfo(info);

        Currency currency = Currency.valueOf(getElementTextContent(depositElement, CURRENCY).toUpperCase());
        deposit.setCurrency(currency);

        String bankName = getElementTextContent(depositElement, BANK_NAME);
        deposit.setBankName(bankName);

        return deposit;
    }

    private Deposit createTermDeposit(Element termDepositElement) throws ParseException {
        TermDeposit termDeposit = (TermDeposit) createDeposit(termDepositElement);

        String startDate = getElementTextContent(termDepositElement, START_DATE);
        termDeposit.setStartDate(DATE_FORMAT.parse(startDate));

        String endDate = getElementTextContent(termDepositElement, END_DATE);
        termDeposit.setEndDate(DATE_FORMAT.parse(endDate));

        return termDeposit;
    }

    private String getElementTextContent(Element element, String elementName) {
        NodeList nodeList = element.getElementsByTagName(elementName);
        if (nodeList.getLength() == 0) {
            return "";
        }
        Node node = nodeList.item(0);
        return node.getTextContent();
    }

    private Deposit createDepositByTag(String tagName) {
        tagName = tagName.toUpperCase(Locale.ROOT);
        tagName = tagName.replace('-', '_');
        DepositField field = DepositField.valueOf(tagName);
        switch (field) {
            case TERM_DEPOSIT:
                return new TermDeposit();
            case DEPOSIT:
                return new Deposit();
            default:
                throw new EnumConstantNotPresentException(DepositField.class, tagName);
        }
    }
}
