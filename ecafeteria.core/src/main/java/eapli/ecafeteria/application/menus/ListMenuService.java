/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.menus;

import eapli.ecafeteria.application.authz.AuthorizationService;
import eapli.ecafeteria.domain.authz.ActionRight;
import eapli.ecafeteria.domain.menus.Menu;
import eapli.ecafeteria.persistence.MealRepository;
import eapli.ecafeteria.persistence.MenuRepository;
import eapli.ecafeteria.persistence.PersistenceContext;

/**
 *
 * @author Berccar
 */
public class ListMenuService {

    private static final MenuRepository menuRepository = PersistenceContext.repositories().menus();
    private static final MealRepository mealRepository = PersistenceContext.repositories().meals();

    public Iterable<Menu> unpublishedMenus() {
        AuthorizationService.ensurePermissionOfLoggedInUser(ActionRight.MANAGE_MENUS);

        return this.menuRepository.findByState(false);
    }
}
