package eapli.ecafeteria.domain.kitchen;

import eapli.ecafeteria.domain.finance.WorkSession;
import static eapli.ecafeteria.domain.kitchen.CanteenShiftState.OPEN;
import static eapli.ecafeteria.domain.kitchen.CanteenShiftState.CLOSED;
import eapli.ecafeteria.persistence.CanteenShiftRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.domain.ddd.AggregateRoot;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Version;

@Entity
public class CanteenShift implements AggregateRoot<String>, Serializable {

    private static final long serialVersionUID = 1L;
    
    private final CanteenShiftRepository csRepository = PersistenceContext.repositories().canteenShift();

    // ORM primary key
    @Id
    @GeneratedValue
    private Long pk;
    @Version
    private Long version;

    // business ID
    @Column(unique = true)
    private String dateCS;

    //@OneToOne
    private CanteenShiftState cfs;

    @OneToOne
    private WorkSession ws;

    protected CanteenShift() {
        // for ORM
    }

    public CanteenShift(String dateCS, CanteenShiftState cfs, WorkSession ws) {
        if (dateCS == null) {
            throw new IllegalArgumentException();
        }
        if(csRepository.verifyByDate(dateCS)){
            this.dateCS = dateCS;
            this.cfs = cfs;
            this.ws = ws;
        }
    }

    public CanteenShiftState canteenShiftState() {
        return this.cfs;
    }

    public WorkSession workSession() {
        return this.ws;
    }

    @Override
    public String id() {
        return this.dateCS;
    }

    @Override
    public boolean is(String date) {
        return (this.dateCS.equals(date));
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

    public boolean isClosed() {
        return this.cfs == CLOSED;
    }
}
