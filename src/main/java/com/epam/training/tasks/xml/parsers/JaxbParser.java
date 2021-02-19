package com.epam.training.tasks.xml.parsers;

import com.epam.training.tasks.xml.exceptions.XmlParseException;
import com.epam.training.tasks.xml.entities.Deposit;
import com.epam.training.tasks.xml.entities.Deposits;
import org.apache.log4j.Logger;

import javax.xml.bind.*;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JaxbParser implements XmlParser {

    private static final Logger LOGGER = Logger.getRootLogger();

    public List<Deposit> parse(String xmlPath) throws XmlParseException {
        Deposits deposits;
        List<Deposit> result = new ArrayList<>();
        try {
            JAXBContext context = JAXBContext.newInstance(Deposits.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            FileReader reader = new FileReader(xmlPath);
            deposits = (Deposits) unmarshaller.unmarshal(reader);
            System.out.println(deposits);
            for (JAXBElement<? extends Deposit> deposit : deposits.getDeposit()) {
                result.add(deposit.getValue());
            }
        } catch (JAXBException e) {
            throw new XmlParseException("Error creating jaxbContext : ", e);
        } catch (IOException e) {
            throw new XmlParseException("Cannot find file : ", e);
        }

        return result;
    }
}
