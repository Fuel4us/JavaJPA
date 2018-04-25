package eapli.ecafeteria.domain.kitchen;

import eapli.ecafeteria.domain.meals.Meal;
import eapli.framework.domain.ddd.AggregateRoot;
import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.Version;

@Entity
public class CanteenShift implements AggregateRoot<Calendar>, Serializable{

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
    @OneToMany
    private Meal mealCode;
    private String description;

    protected CanteenShift() {
        // for ORM
    }

    public CanteenShift(Calendar date, Meal mealCode, String description) {
        if (date == null) {
            throw new IllegalArgumentException();
        }
        this.dateCS = date;
        this.mealCode = mealCode;
        this.description = description;
    }

    public Meal meal() {
        return this.mealCode;
    }

    public String description() {
        return this.description;
    }

    public void changeDescriptionTo(String newDescription) {
        this.description = newDescription;
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
        return id().equals(cs.id());
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
        return id().equals(other.id());
    }

    @Override
    public int hashCode() {
        return this.dateCS.hashCode();
    }

}
