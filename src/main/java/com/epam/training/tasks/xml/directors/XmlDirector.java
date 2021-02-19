package com.epam.training.tasks.xml.directors;

import com.epam.training.tasks.xml.exceptions.XmlParseException;
import com.epam.training.tasks.xml.entities.Deposit;
import com.epam.training.tasks.xml.exceptions.InvalidXmlFileException;
import com.epam.training.tasks.xml.parsers.XmlParser;
import com.epam.training.tasks.xml.validators.XmlValidator;

import java.util.List;

public class XmlDirector {

    private XmlParser parser;
    private XmlValidator validator;

    public XmlDirector(XmlParser parser, XmlValidator validator) {
        this.parser = parser;
        this.validator = validator;
    }

    public List<Deposit> createDeposits(String xmlPath) throws InvalidXmlFileException, XmlParseException {
        if (!validator.validate(xmlPath)) {
            throw new InvalidXmlFileException();
        }
        return parser.parse(xmlPath);
    }


}
