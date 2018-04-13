package eapli.ecafeteria.domain.booking;

import eapli.framework.domain.ddd.AggregateRoot;
import java.io.Serializable;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 *
 * @author Mário Vaz
 */

public class Booking implements AggregateRoot<String>, Serializable{

    @Id
    @GeneratedValue
    private int bookingID;
    
    private String id;
    
    @Override
    public boolean sameAs(Object other) {
        return false;
    }

    @Override
    public String id() {
        return this.id;
    }
}
