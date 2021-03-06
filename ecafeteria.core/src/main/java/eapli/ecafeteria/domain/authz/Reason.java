package eapli.ecafeteria.domain.authz;

import eapli.framework.domain.ddd.AggregateRoot;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;

/**
 * This class is responsible for the reason why a user was deactivated
 *
 * @author pedromonteiro
 */
@Entity
public class Reason implements Serializable, AggregateRoot<Long> {

    private static final long serialVersionUID = 1L;

    @Version
    private Long version;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reasonId;
    @Column(name = "ReasonComment")
    private String comment;
    @Enumerated
    private ReasonType reasonType;

    /**
     * For ORM effect.
     */
    protected Reason() {
        //For ORM
    }

    /**
     * Constructor for reason must always hava a ReasonType predefined and a comment.
     *
     * @param rt Reason Type
     * @param comment String with the comment for the deactivation
     */
    public Reason(ReasonType rt, String comment) {
        this.reasonType = rt;
        this.comment = comment;
    }

    /**
     * Returns the reason ID
     *
     * @return Reason ID
     */
    @Override
    public Long id() {
        return reasonId;
    }

    protected String comment() {
        return comment;
    }

    public ReasonType reasonType() {
        return reasonType;
    }

    @Override
    public String toString() {
        return "Reason:" + reasonType.toString() + "\nComment: " + comment;
    }

    @Override
    public boolean sameAs(Object other) {
        if (!(other instanceof Reason)) {
            return false;
        }

        final Reason that = (Reason) other;
        if (this == that) {
            return true;
        }

        return /*id().equals(that.id()) &&*/ this.reasonType().equals(that.reasonType())
                && this.comment().equals(that.comment()); // FIX ME is id needed?
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.reasonId);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Reason other = (Reason) obj;

        return Objects.equals(this.reasonId, other.reasonId);
    }

}
