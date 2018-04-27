package eapli.ecafeteria.bootstrapers;

import eapli.ecafeteria.domain.kitchen.Lot;
import eapli.ecafeteria.domain.kitchen.Material;
import eapli.ecafeteria.persistence.LotRepository;
import eapli.ecafeteria.persistence.MaterialRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.actions.Action;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;

/**
 * @author Gonçalo Silva (1161140)
 */
public class LotBootstrapper implements Action {

    @Override
    public boolean execute() {
        try {
            register();
        } catch (DataIntegrityViolationException | DataConcurrencyException ex) {
            return false;
        }

        return true;
    }

    public void register() throws DataIntegrityViolationException, DataConcurrencyException {
        final MaterialRepository materialRepository = PersistenceContext.repositories().materials();
        final LotRepository lotRepository = PersistenceContext.repositories().lots();

        for (Material material : materialRepository.findAll()) {
            int i = 1, j = i * 2;
            Lot lot = new Lot(i++, material, j);
            lotRepository.save(lot);
        }
    }
}
