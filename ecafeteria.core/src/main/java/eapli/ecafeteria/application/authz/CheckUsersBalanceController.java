package eapli.ecafeteria.application.authz;

import eapli.ecafeteria.domain.authz.SystemUser;
import eapli.ecafeteria.domain.authz.Username;
import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.ecafeteria.domain.cafeteriauser.MecanographicNumber;
import eapli.ecafeteria.domain.movement.BalanceService;
import eapli.ecafeteria.persistence.CafeteriaUserRepository;
import eapli.ecafeteria.persistence.PersistenceContext;

import java.text.DecimalFormat;
import java.util.Optional;

/**
 * @author Gonçalo Fonseca - 1150503@isep.ipp.pt
 */
public class CheckUsersBalanceController {

    public final CafeteriaUserRepository cafeteriaUser = PersistenceContext.repositories().cafeteriaUsers();
    public final SystemUser su = AuthorizationService.session().authenticatedUser();
    public final CafeteriaUser cu = findCafetariaUser(su.username());

    /**
     * Gets a cafetariaUser from username
     * @param username
     * @return
     */
    public CafeteriaUser findCafetariaUser(Username username) {
        Optional<CafeteriaUser> opCU = cafeteriaUser.findByUsername(username);
        if(opCU.isPresent()) {
            return opCU.get();
        } else {
            return null;
        }
    }

    /**
     * Method to get the mecanographic number so I can get userbalance
     * @param cafeteriaUser
     * @return
     */
    public MecanographicNumber getUsersMecanographicNumber(CafeteriaUser cafeteriaUser) {

        if (cafeteriaUser == null) {
            System.out.println("User is null");
            return new MecanographicNumber(null);
        }

        MecanographicNumber mecanographicNumber = new MecanographicNumber(cafeteriaUser.mecanographicNumber().number());

        return mecanographicNumber;
    }

    /**
     * Final method to get the balance
     */
    public void getUserBalance () {
        double amount = BalanceService.balance(getUsersMecanographicNumber(cu));
        // decimal format to only show 2 digits after decimal
        System.out.println("\nO teu saldo é de " +new DecimalFormat("##.##").format(amount)+ "€");
    }
}
