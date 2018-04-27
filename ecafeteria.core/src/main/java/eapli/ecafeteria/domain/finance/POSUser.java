/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.finance;

import eapli.ecafeteria.domain.authz.SystemUser;
import eapli.ecafeteria.domain.cafeteriauser.MecanographicNumber;
import eapli.framework.domain.ddd.AggregateRoot;
import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Version;

/**
 *
 * @author Hernani Gil
 */
@Entity
public class POSUser implements AggregateRoot<MecanographicNumber>, Serializable{
    @Version
    private Long version;
    
    @EmbeddedId
    private MecanographicNumber mecanographicNumber;
    
     /**
     * cascade = CascadeType.NONE as the systemUser is part of another aggregate
     */
    @OneToOne()
    private SystemUser systemUser;
    @Id
    private Long id;
    
    protected POSUser() {
        // for ORM only
    }
    
    public POSUser(SystemUser user, MecanographicNumber mecanographicNumber) {
        if (mecanographicNumber == null || user == null) {
            throw new IllegalArgumentException();
        }
        this.systemUser = user;
        this.mecanographicNumber = mecanographicNumber;
    }

    public SystemUser user() {
        return this.systemUser;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof POSUser)) {
            return false;
        }

        final POSUser other = (POSUser) o;
        return this.mecanographicNumber.equals(other.mecanographicNumber);
    }

    @Override
    public int hashCode() {
        return this.mecanographicNumber.hashCode();
    }

    @Override
    public boolean sameAs(Object other) {
        if (!(other instanceof POSUser)) {
            return false;
        }

        final POSUser that = (POSUser) other;
        if (this == that) {
            return true;
        }
        return (this.mecanographicNumber.equals(that.mecanographicNumber)
                && this.systemUser.sameAs(that.systemUser));
    }

    public MecanographicNumber mecanographicNumber() {
        return id();
    }

    @Override
    public MecanographicNumber id() {
        return this.mecanographicNumber;
    }
}
