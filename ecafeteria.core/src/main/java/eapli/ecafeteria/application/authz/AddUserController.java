package eapli.ecafeteria.application.authz;

import java.util.Calendar;
import java.util.Set;

import eapli.ecafeteria.domain.authz.ActionRight;
import eapli.ecafeteria.domain.authz.RoleType;
import eapli.ecafeteria.domain.authz.SystemUser;
import eapli.ecafeteria.domain.authz.SystemUserBuilder;
import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUserBuilder;
import eapli.ecafeteria.persistence.CafeteriaUserRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.ecafeteria.persistence.UserRepository;
import eapli.framework.application.Controller;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.util.DateTime;

/**
 *
 * Created by nuno on 21/03/16.
 */
public class AddUserController implements Controller {

    private final UserRepository userRepository = PersistenceContext.repositories().users();
    private final CafeteriaUserRepository cafeteriaUserRepository = PersistenceContext.repositories().cafeteriaUsers();

    /**
     * Get existing RoleTypes available to the user.
     *
     * @return a list of RoleTypes
     */
    public RoleType[] getRoleTypes() {
        return RoleType.nonUserValues();
    }
    
    /**
     * Method that will create and persist a new System User
     * @param username SystemUser's username
     * @param password SystemUser's password
     * @param firstName SystemUser's first name
     * @param lastName SystemUser's last name
     * @param email SystemUser's email
     * @param roles SystemUser's roles
     * @param createdOn SystemUser's date of creation
     * @param activateUser False value is always passed
     * @return CafeteriaUser's object with all the information set.
     * @throws DataIntegrityViolationException - Exception called when the Primary Key is repeated
     * @throws DataConcurrencyException - Exception called when database cannot update the object 
     */
    public SystemUser addUser(String username, String password, String firstName, String lastName,
            String email, Set<RoleType> roles, Calendar createdOn, boolean activateUser)
            throws DataIntegrityViolationException, DataConcurrencyException, IllegalArgumentException {
        AuthorizationService.ensurePermissionOfLoggedInUser(ActionRight.ADMINISTER);
        
        final SystemUserBuilder userBuilder = new SystemUserBuilder();
        userBuilder.withUsername(username).withPassword(password).withFirstName(firstName)
                .withLastName(lastName).withEmail(email).withCreatedOn(createdOn).withRoles(roles);

        if(activateUser) userBuilder.activator();
        
        return this.userRepository.save(userBuilder.build());
    }
    
    public SystemUser addUser(String username, String password, String firstName, String lastName,
            String email, Set<RoleType> roles, boolean activateUser)
            throws DataIntegrityViolationException, DataConcurrencyException {
        return addUser(username, password, firstName, lastName, email, roles, DateTime.now(), activateUser);
    }
    
    /**
     * Method that calls the private one which will create and persist a new Cafeteria User if data is valid.
     * @param username CafeteriaUser's username
     * @param password CafeteriaUser's password
     * @param firstName CafeteriaUser's first name
     * @param lastName CafeteriaUser's last name
     * @param email CafeteriaUser's email
     * @param roles CafeteriaUser's roles
     * @param mecanographicNumber CafeteriaUser's mecanographic number
     * @param activateUser False value is always passed
     * @return CafeteriaUser's object with all the information set.
     * @throws DataIntegrityViolationException - Exception called when the Primary Key is repeated
     * @throws DataConcurrencyException - Exception called when database cannot update the object
     */
    public CafeteriaUser addCafeteriaUser(String username, String password, String firstName, String lastName,
            String email, Set<RoleType> roles, String mecanographicNumber, boolean activateUser)
            throws DataIntegrityViolationException, DataConcurrencyException {
        return addCafeteriaUser(username, password, firstName, lastName, email, roles, DateTime.now(), mecanographicNumber, false);
    }
    
    private CafeteriaUser addCafeteriaUser(String username, String password, String firstName, String lastName,
            String email, Set<RoleType> roles, Calendar createdOn, String mecanographicNumber, boolean activateUser)
            throws DataIntegrityViolationException, DataConcurrencyException, IllegalArgumentException {
        AuthorizationService.ensurePermissionOfLoggedInUser(ActionRight.ADMINISTER);
        
        final CafeteriaUserBuilder cafeteriaUserBuilder = new CafeteriaUserBuilder();
        cafeteriaUserBuilder.withMecanographicNumber(mecanographicNumber)
                .withSystemUser(addUser(username, password, firstName, lastName, email, roles, createdOn, activateUser));
        
        return this.cafeteriaUserRepository.save(cafeteriaUserBuilder.build());
    }
}
