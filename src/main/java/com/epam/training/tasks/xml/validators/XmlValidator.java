package com.epam.training.tasks.xml.validators;

import org.apache.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;

public class XmlValidator {

    private static final String language = XMLConstants.W3C_XML_SCHEMA_NS_URI;
    private static final SchemaFactory factory = SchemaFactory.newInstance(language);

    public static final Logger LOGGER = Logger.getRootLogger();

    private final String schemaName;

    public XmlValidator(String schemaName){
        this.schemaName = schemaName;
    }

    public boolean validate(String xmlFile) {
        try {
            Schema schema = factory.newSchema(new File(schemaName));
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(new File(xmlFile)));
        } catch (SAXException | IOException e) {
            LOGGER.warn("Xml file is not valid: " + e.getMessage());
            return false;
        }
        return true;
    }

}
