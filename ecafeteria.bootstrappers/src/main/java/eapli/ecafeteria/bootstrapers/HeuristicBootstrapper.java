package eapli.ecafeteria.bootstrapers;

import eapli.ecafeteria.application.authz.AuthorizationService;
import eapli.ecafeteria.domain.authz.ActionRight;
import eapli.ecafeteria.domain.kitchen.HeuristicA;
import eapli.ecafeteria.domain.kitchen.HeuristicB;
import eapli.ecafeteria.domain.kitchen.HeuristicConfiguration;
import eapli.ecafeteria.persistence.HeuristicRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.actions.Action;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author joaotiagow
 */
public class HeuristicBootstrapper implements Action {

    @Override
    public boolean execute() {
        register();
        return true;
    }

    private void register() {
        try {
            final HeuristicRepository heuristicRepository = PersistenceContext.repositories().heuristics();

            AuthorizationService.ensurePermissionOfLoggedInUser(ActionRight.ADMINISTER);

            HeuristicConfiguration entityA = new HeuristicConfiguration(new HeuristicA("Most recent"));
            heuristicRepository.save(entityA);
            HeuristicConfiguration entityB = new HeuristicConfiguration(new HeuristicB("Average"));
            heuristicRepository.save(entityB);
        } catch (final DataConcurrencyException | DataIntegrityViolationException ex) {
            Logger.getLogger(KitchenLimitBootstrapper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
