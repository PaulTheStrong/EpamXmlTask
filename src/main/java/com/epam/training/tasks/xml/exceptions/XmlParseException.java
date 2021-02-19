package com.epam.training.tasks.xml.exceptions;

public class XmlParseException extends Exception {
    public XmlParseException() { }

    public XmlParseException(String s) {
        super(s);
    }

    public XmlParseException(String s, Throwable throwable) {
        super(s, throwable);
    }
}
