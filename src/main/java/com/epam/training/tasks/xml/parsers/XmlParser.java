package com.epam.training.tasks.xml.parsers;

import com.epam.training.tasks.xml.exceptions.XmlParseException;
import com.epam.training.tasks.xml.entities.Deposit;

import java.util.List;

public interface XmlParser {

    List<Deposit> parse(String xmlPath) throws XmlParseException;

}
