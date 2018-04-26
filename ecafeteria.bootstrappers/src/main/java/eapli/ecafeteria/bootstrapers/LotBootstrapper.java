package eapli.ecafeteria.bootstrapers;

import eapli.ecafeteria.application.kitchen.RegisterLotsUsedInMealController;
import eapli.ecafeteria.domain.kitchen.Material;
import eapli.ecafeteria.persistence.MaterialRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.actions.Action;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;

public class LotBootstrapper implements Action {

    @Override
    public boolean execute() {
        try {
            register();
        } catch (DataIntegrityViolationException ex) {
            return false;
        } catch (DataConcurrencyException ex) {
            return false;
        }
        
        return true;
    }

    public void register() throws DataIntegrityViolationException, DataConcurrencyException {
        final MaterialRepository materialRepository = PersistenceContext.repositories().materials();

        for(Material material : materialRepository.findAll()) {
            int i=1, j=i*2;
            new RegisterLotsUsedInMealController().registerLot(i++, material, j);
        }
    }
}
