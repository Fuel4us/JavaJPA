package eapli.ecafeteria.bootstrapers;

import eapli.ecafeteria.domain.dishes.Allergens;
import eapli.ecafeteria.persistence.AllergenRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.actions.Action;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author João Pereira <1150478@isep.ipp.pt>
 */
public class AllergenBootstrapper implements Action {

    private final AllergenRepository allergRepository = PersistenceContext.repositories().allergen();

    @Override
    public boolean execute() {
        try {
            register(TestDataConstants.ALLERGEN_GLUTEN);
            register(TestDataConstants.ALLERGEN_CRUSTACEOS);
            register(TestDataConstants.ALLERGEN_PEIXES);
        } catch (DataConcurrencyException ex) {
            Logger.getLogger(AllergenBootstrapper.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DataIntegrityViolationException ex) {
            Logger.getLogger(AllergenBootstrapper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

    private void register(String allergName) throws DataConcurrencyException, DataIntegrityViolationException {
        Allergens allerg = new Allergens(allergName);

        allergRepository.save(allerg);
    }

}
