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

    public SystemUser addUser(String username, String password, String firstName, String lastName,
            String email, Set<RoleType> roles, Calendar createdOn, boolean activateUser)
            throws DataIntegrityViolationException, DataConcurrencyException {
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
    
    public CafeteriaUser addCafeteriaUser(String username, String password, String firstName, String lastName,
            String email, Set<RoleType> roles, String mecanographicNumber, boolean activateUser)
            throws DataIntegrityViolationException, DataConcurrencyException {
        return addCafeteriaUser(username, password, firstName, lastName, email, roles, DateTime.now(), mecanographicNumber, false);
    }
    
    private CafeteriaUser addCafeteriaUser(String username, String password, String firstName, String lastName,
            String email, Set<RoleType> roles, Calendar createdOn, String mecanographicNumber, boolean activateUser)
            throws DataIntegrityViolationException, DataConcurrencyException {
        
        final CafeteriaUserBuilder cafeteriaUserBuilder = new CafeteriaUserBuilder();
        cafeteriaUserBuilder.withMecanographicNumber(mecanographicNumber)
                .withSystemUser(addUser(username, password, firstName, lastName, email, roles, createdOn, activateUser));
        
        return this.cafeteriaUserRepository.save(cafeteriaUserBuilder.build());
    }
}
