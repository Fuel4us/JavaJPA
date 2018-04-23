/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafetaria.domain.finance;

import eapli.framework.domain.money.Money;
import eapli.framework.util.DateTime;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Currency;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 *
 * @author Hernani Gil
 */
public class Transaction implements Serializable{
    private Money quantity;
    private Calendar date;
    @Id
    @GeneratedValue
    private Long id;

    public Transaction() {
        //For ORM
    }

    public Transaction(double quantityDouble, Currency currency) {
        quantity = new Money(quantityDouble, currency);
        date= DateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
