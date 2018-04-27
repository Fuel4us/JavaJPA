/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.menus;

import eapli.ecafeteria.application.authz.AuthorizationService;
import eapli.ecafeteria.domain.authz.ActionRight;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.domain.menus.Menu;
import eapli.ecafeteria.persistence.MenuRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import java.util.Date;
import java.util.Set;

/**
 *
 * @author Pedro Alves <1150372@isep.ipp.pt>
 */
public class RegisterMenuController {

    private final MenuRepository menuRepository = PersistenceContext.repositories().menus();

    private final Menu menu = new Menu();

    public Menu registerMenu(Date mealDateBegMenu, Date mealDateEndMenu) throws DataConcurrencyException, DataIntegrityViolationException {
        AuthorizationService.ensurePermissionOfLoggedInUser(ActionRight.MANAGE_MENUS);
        return validateDates(mealDateBegMenu, mealDateEndMenu);
    }

    private Menu validateDates(Date endDate, Date begDate) throws DataConcurrencyException, DataIntegrityViolationException {
        final Menu newMenu = new Menu(begDate, endDate);
        return this.menuRepository.save(newMenu);
    }

}
