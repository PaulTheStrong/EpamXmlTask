package com.epam.training.tasks.xml.parsers;

import com.epam.training.tasks.xml.entities.Deposit;
import com.epam.training.tasks.xml.handlers.DepositHandler;
import org.apache.log4j.Logger;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SaxParser implements XmlParser {

    public static final Logger LOGGER = Logger.getRootLogger();

    public List<Deposit> parse(String xmlPath) {
        List<Deposit> deposits = new ArrayList<Deposit>();
        DepositHandler handler = new DepositHandler();
        try {
            XMLReader reader = XMLReaderFactory.createXMLReader();
            reader.setContentHandler(handler);
            reader.parse(xmlPath);
            deposits = handler.getDeposits();
        } catch (SAXException e) {
            LOGGER.error("Error creating XML Reader");
        } catch (IOException e) {
            LOGGER.error("Error parsing xml file");
        }
        return deposits;
    }
}
