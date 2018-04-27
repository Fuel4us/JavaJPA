package eapli.ecafeteria.domain.kitchen;

import eapli.ecafetaria.domain.finance.WorkSession;
import static eapli.ecafeteria.domain.kitchen.CanteenShiftState.OPEN;
import static eapli.ecafeteria.domain.kitchen.CanteenShiftState.CLOSED;
import eapli.framework.domain.ddd.AggregateRoot;
import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.Version;

@Entity
public class CanteenShift implements AggregateRoot<Calendar>, Serializable {

    private static final long serialVersionUID = 1L;

    // ORM primary key
    @Id
    @GeneratedValue
    private Long pk;
    @Version
    private Long version;

    // business ID
    @Column(unique = true)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Calendar dateCS;

    @OneToOne
    private CanteenShiftState cfs;

    @OneToMany
    private WorkSession ws;

    protected CanteenShift() {
        // for ORM
    }

    public CanteenShift(Calendar date, CanteenShiftState cfs, WorkSession ws) {
        if (date == null) {
            throw new IllegalArgumentException();
        }
        this.dateCS = date;
        this.cfs = cfs;
        this.ws = ws;
    }
    
    public CanteenShift(CanteenShiftState cfs, WorkSession ws) {
        this.dateCS = Calendar.getInstance();
        this.cfs = cfs;
        this.ws = ws;
    }

    public CanteenShiftState canteenShiftState() {
        return this.cfs;
    }

    public WorkSession workSession() {
        return this.ws;
    }

    @Override
    public Calendar id() {
        return this.dateCS;
    }

    @Override
    public boolean is(Calendar date) {
        return (date.getTime() == this.dateCS.getTime());
    }

    @Override
    public boolean sameAs(Object other) {
        // FIXME implement this method
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
        return this.dateCS.hashCode();
    }

    public boolean open() {
        if (this.cfs == CLOSED) {
            this.cfs = OPEN;
            return true;
        }
        return false;
    }

    public boolean close() {
        if (this.cfs == OPEN) {
            this.cfs = CLOSED;
            return true;
        }
        return false;
    }
}
