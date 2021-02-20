package com.epam.training.tasks.xml.entities;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

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
