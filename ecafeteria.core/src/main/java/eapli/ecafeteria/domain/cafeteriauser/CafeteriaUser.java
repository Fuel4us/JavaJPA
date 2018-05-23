package eapli.ecafeteria.domain.cafeteriauser;

import eapli.ecafeteria.domain.authz.SystemUser;
import eapli.framework.domain.ddd.AggregateRoot;
import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Version;

/**
 * A Cafeteria User.
 *
 * This class represents cafeteria users. It follows a DDD approach where User
 * is the root entity of the Cafeteria User Aggregate and all of its properties
 * are instances of value objects. A Cafeteria User references a System User
 *
 * This approach may seem a little more complex than just having String or
 * native type attributes but provides for real semantic of the domain and
 * follows the Single Responsibility Pattern
 *
 * @author Jorge Santos ajs@isep.ipp.pt
 *
 */
@Entity
public class CafeteriaUser implements AggregateRoot<MecanographicNumber>, Serializable {

    /**
     * Instance variable that defines the serial version.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Instance variable that defines the version.
     */
    @Version
    private Long version;

    /**
     * Variable that defines the mecanographic number of the cafeteria user.
     */
    @EmbeddedId
    private MecanographicNumber mecanographicNumber;
    
    /**
     * cascade = CascadeType.NONE as the systemUser is part of another aggregate
     */
    /**
     * Instance variable that defines the System User of the cafeteria user.
     */
    @OneToOne()
    private SystemUser systemUser;
    
    /**
     * Instance variable that defines the nutritional profile of the cafeteria user.
     */
    @OneToOne(cascade = CascadeType.ALL)
    private NutritionalProfile nutritionalProfile;

    /**
     * Instance variable that defines the profile of the cafeteria user.
     */
    @OneToOne(cascade = CascadeType.ALL)
    private Profile Profile;
    
    /**
     * Complete constructor of the class.
     * 
     * @param user
     * @param mecanographicNumber
     */
    public CafeteriaUser(SystemUser user, MecanographicNumber mecanographicNumber) {
        if (mecanographicNumber == null || user == null) {
            throw new IllegalArgumentException();
        }
        this.systemUser = user;
        this.mecanographicNumber = mecanographicNumber;
        this.nutritionalProfile = new NutritionalProfile();
        this.Profile = new Profile();
    }

    
    /**
     * Empty constructor of the class for the ORM.
     */
    protected CafeteriaUser() {
    }

    /**
     * Returns the system user.
     * @return
     */
    public SystemUser user() {
        return this.systemUser;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CafeteriaUser)) {
            return false;
        }

        final CafeteriaUser other = (CafeteriaUser) o;
        return this.mecanographicNumber.equals(other.mecanographicNumber);
    }

    @Override
    public int hashCode() {
        return this.mecanographicNumber.hashCode();
    }

    @Override
    public boolean sameAs(Object other) {
        if (!(other instanceof CafeteriaUser)) {
            return false;
        }

        final CafeteriaUser that = (CafeteriaUser) other;
        if (this == that) {
            return true;
        }
        return (this.mecanographicNumber.equals(that.mecanographicNumber)
                && this.systemUser.sameAs(that.systemUser));
    }

    /**
     * Returns the mecanographic number
     * @return
     */
    public MecanographicNumber mecanographicNumber() {
        return id();
    }
    
    /**
     * Returns the nutritional profile
     * @return
     */
    public NutritionalProfile accessNutritionalProfile(){
        return nutritionalProfile;
    }

    /**
     * Gives access to the Profile
     * @return Cafeteria User
     */
    public Profile accessProfile() {
        return this.Profile;
    }
    
    
    @Override
    public MecanographicNumber id() {
        return this.mecanographicNumber;
    }

    /**
     *
     * @return String with toString of parameter systemUser
     */
    @Override
    public String toString() {
        return systemUser.toString();
    }
    
}
