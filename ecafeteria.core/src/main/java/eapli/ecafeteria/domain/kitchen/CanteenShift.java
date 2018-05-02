package eapli.ecafeteria.domain.kitchen;

import eapli.ecafeteria.domain.finance.WorkSession;
import static eapli.ecafeteria.domain.kitchen.CanteenShiftState.OPEN;
import static eapli.ecafeteria.domain.kitchen.CanteenShiftState.CLOSED;
import eapli.framework.domain.ddd.AggregateRoot;
import eapli.framework.util.Strings;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class CanteenShift implements AggregateRoot<String>, Serializable {

    private static final long serialVersionUID = 1L;
    
    // ORM primary key
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pk;

    // business ID
    @Column(unique = true)
    private String canteenShiftDate;

    private CanteenShiftState canteenShiftState;

    @OneToOne
    private WorkSession workSession;

    protected CanteenShift() {
        // for ORM
    }

    public CanteenShift(String canteenShiftDate, CanteenShiftState canteenShiftState, WorkSession workSession) {
        if (Strings.isNullOrEmpty(canteenShiftDate)) {
            throw new IllegalArgumentException();
        }
        //if(PersistenceContext.repositories().canteenShift().verifyByDate(dateCS)){
            this.canteenShiftDate = canteenShiftDate;
            this.canteenShiftState = canteenShiftState;
            this.workSession = workSession;
        //}
    }

    /**
     * Canteen shift state initializes open 
     * @param canteenShiftDate
     * @param workSession
     */
    public CanteenShift(String canteenShiftDate, WorkSession workSession) {
        if (Strings.isNullOrEmpty(canteenShiftDate)) {
            throw new IllegalArgumentException();
        }
        //if(PersistenceContext.repositories().canteenShift().verifyByDate(dateCS)){
            this.canteenShiftDate = canteenShiftDate;
            this.canteenShiftState = OPEN;
            this.workSession = workSession;
        //}
    }
    
    public CanteenShiftState canteenShiftState() {
        return this.canteenShiftState;
    }

    public WorkSession workSession() {
        return this.workSession;
    }

    @Override
    public String id() {
        return this.canteenShiftDate;
    }

    @Override
    public boolean is(String date) {
        return (this.canteenShiftDate.equals(date));
    }

    @Override
    public boolean sameAs(Object other) {
        final CanteenShift cs = (CanteenShift) other;
        return this.id().equals(cs.id());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CanteenShift)) {
            return false;
        }

        final CanteenShift other = (CanteenShift) o;
        return this.id().equals(other.id());
    }

    @Override
    public int hashCode() {
        return this.canteenShiftDate.hashCode();
    }

    public boolean open() {
        if (this.canteenShiftState == CLOSED) {
            this.canteenShiftState = OPEN;
            return true;
        }
        return false;
    }

    public boolean close() {
        if (this.canteenShiftState == OPEN) {
            this.canteenShiftState = CLOSED;
            return true;
        }
        return false;
    }

    public boolean isClosed() {
        return this.canteenShiftState == CLOSED;
    }
}
