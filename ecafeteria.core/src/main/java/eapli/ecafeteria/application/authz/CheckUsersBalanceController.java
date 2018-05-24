package eapli.ecafeteria.application.authz;

import eapli.ecafeteria.domain.authz.SystemUser;
import eapli.ecafeteria.domain.authz.Username;
import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.ecafeteria.domain.cafeteriauser.MecanographicNumber;
import eapli.ecafeteria.domain.movement.BalanceService;
import eapli.ecafeteria.persistence.CafeteriaUserRepository;
import eapli.ecafeteria.persistence.PersistenceContext;

import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.swing.text.html.parser.Entity;
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

        try {
            Optional<CafeteriaUser> opCU = cafeteriaUser.findByUsername(username);
            if (opCU.isPresent()) {
                return opCU.get();
            } else {
                return null;
            }

        } catch (NoResultException e){
            System.out.println("\nBe sure you are logged in as a regular user!");
        }

        return null;
    }

    /**
     * Method to get the mecanographic number so I can get userbalance
     * @param cafeteriaUser
     * @return
     */
    public MecanographicNumber getUsersMecanographicNumber(CafeteriaUser cafeteriaUser) {

        if (cafeteriaUser == null) {
            return new MecanographicNumber(null);
        }

        MecanographicNumber mecanographicNumber = new MecanographicNumber(cafeteriaUser.mecanographicNumber().number());

        return mecanographicNumber;
    }

    /**
     * Final method to get the balance
     */
    public void getUserBalance () {
        try {

            MecanographicNumber mu = getUsersMecanographicNumber(cu);

            double amount = BalanceService.balance(mu);

            // decimal format to only show 2 digits after decimal
            System.out.println("\nYour balance is " +new DecimalFormat("##.##").format(amount)+ "€");

            if (amount > 100) {
                System.out.println("\nSomeone is loaded righ here....");
            } else if(amount < 0){
                System.out.println("\nDude set your priorities straight ...");
            } else if(amount == 0) {
                System.out.println("\nDeposit!!!!!!");
            }

        } catch (Exception e) {
            System.out.println("\nMecanographic number is not valid or doesn't exist please change users or call administration!");

        }


    }
}
