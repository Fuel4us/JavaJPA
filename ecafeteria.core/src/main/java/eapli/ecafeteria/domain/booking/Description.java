/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.booking;

import eapli.framework.domain.ddd.ValueObject;
import eapli.framework.util.Strings;
import java.io.Serializable;
import javax.persistence.Embeddable;

/**
 *  Complaint Description ValueObject
 * 
 * @author Hernani Gil
 */


@Embeddable
public class Description implements ValueObject, Serializable{
    private String description;
    
    public Description(String description) {
        if (Strings.isNullOrEmpty(description)) {
            throw new IllegalArgumentException("Description should neither be null nor empty");
        }
        // FIXME validate invariants, i.e., description text regular
        // expression
        this.description = description;
    }
    
    protected Description() {
        // for ORM
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Description)) {
            return false;
        }

        final Description that = (Description) o;
        return this.description.equals(that.description);
    }

    @Override
    public int hashCode() {
        return this.description.hashCode();
    }

    @Override
    public String toString() {
        return this.description;
    }
    
    public String number(){
        return this.description;
    }
}
