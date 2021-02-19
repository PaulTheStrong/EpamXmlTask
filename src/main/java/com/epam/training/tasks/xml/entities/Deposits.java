package com.epam.training.tasks.xml.entities;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "deposits")
public class Deposits {
    @XmlElementRef(name = "deposit", namespace="http://www.example.com/deposits", type = JAXBElement.class)
    protected List<JAXBElement<? extends Deposit>> deposits;

    public List<JAXBElement<? extends Deposit>> getDeposit() {
        if (deposits == null) {
            deposits = new ArrayList<JAXBElement<? extends Deposit>>();
        }
        return this.deposits;
    }


}
