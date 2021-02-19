package com.epam.training.tasks.xml.exceptions;

public class InvalidXmlFileException extends Exception{
    public InvalidXmlFileException() {    }

    public InvalidXmlFileException(String s) {
        super(s);
    }

    public InvalidXmlFileException(String s, Throwable throwable) {
        super(s, throwable);
    }
}
