//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2021.02.18 at 09:11:56 PM MSK 
//


package com.epam.training.tasks.xml.entities;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.example.deposits package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _TermDeposit_QNAME = new QName("http://www.example.com/deposits", "term-deposit");
    private final static QName _Deposit_QNAME = new QName("http://www.example.com/deposits", "deposit");
    
    public ObjectFactory() { }

    public TermDeposit createTermDeposit() {
        return new TermDeposit();
    }

    public Deposit createDeposit() {
        return new Deposit();
    }

    public Deposits createDeposits() {
        return new Deposits();
    }

    @XmlElementDecl(namespace = "http://www.example.com/deposits", name = "term-deposit", substitutionHeadNamespace = "http://www.example.com/deposits", substitutionHeadName = "deposit")
    public JAXBElement<TermDeposit> createTermDeposit(TermDeposit value) {
        return new JAXBElement<TermDeposit>(_TermDeposit_QNAME, TermDeposit.class, null, value);
    }

    @XmlElementDecl(namespace = "http://www.example.com/deposits", name = "deposit")
    public JAXBElement<Deposit> createDeposit(Deposit value) {
        return new JAXBElement<Deposit>(_Deposit_QNAME, Deposit.class, null, value);
    }

}
