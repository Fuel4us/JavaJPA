package eapli.ecafeteria.application.menus;

import eapli.ecafeteria.domain.menus.Menu;
import eapli.ecafeteria.persistence.MealRepository;
import eapli.ecafeteria.persistence.MenuRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.domain.Designation;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import java.util.Date;

/**
 *
 * @author Pedro Alves <1150372@isep.ipp.pt>
 */
public class EditMenuController {

    private final MenuRepository menuRepository = PersistenceContext.repositories().menus();

    private final MealRepository mealRepository = PersistenceContext.repositories().meals();

    public boolean alterarNome(Menu menu, String nome) throws DataIntegrityViolationException, DataConcurrencyException {
        if (menu.setName(Designation.valueOf(nome))) {
            this.menuRepository.save(menu);
            return true;
        }
        return false;
    }

    public boolean alterarDataBeg(Menu menu, Date aux) throws DataConcurrencyException, DataIntegrityViolationException {
        if (menu.setStartDate(aux)) {
            menu.valideMealsOfMenu();
            this.menuRepository.save(menu);
            return true;
        }
        return false;
    }

    public boolean alterarDataEnd(Menu menu, Date aux) throws DataConcurrencyException, DataIntegrityViolationException {
        if (menu.setEndDate(aux)) {
            menu.valideMealsOfMenu();
            this.menuRepository.save(menu);
            return true;
        }
        return false;
    }

}
